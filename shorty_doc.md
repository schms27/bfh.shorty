## Dokumentation 'Shorty' URL Shortener
### Software Entwicklung Open Source
### Adrian Aulbach & Simon Schmid
---

### Zweck der Applikation

Shorty ist ein URL-Shortener mit Microservice-Architektur, der es einem registrierten User ermöglichen soll, beliebige 
URL's zu kürzen und zu speichern. Ausserdem sollen je nachdem, von welchem Gerät aus die gekürze URL aufgerufen wird, auf eine
andere URL weitergeleitet werden können. Beispielsweise auf eine mobile Version der Website wenn vom Handy aus aufgerufen, auf Desktop Version
wenn vom Laptop aufgerufen.  
Wir setzten im Rahmen dieser Semesterarbeit nur das Backend mit Spring Cloud um, ein grosser Teil der Businesslogik müsste in einem nächsten Schritt
frontendseitig implementiert werden (z.b. Angular 5 Webapp).

### Domain Model

![img domain model][p1]  
*Bild 1: Domain Model Url-Shortener*

### Umgesetzte Services
Da wir das Backend mit Spring Cloud als Microservice-Architektur umgesetzt haben, wurde die Applikation in die folgenden Services aufgeteilt:

##### User Service
Die Entität 'User' wird über einen eigenen Dienst verwaltet, da der User vom Modell her unabhängig von den anderen Entitäten existiert.
Das heisst, der User existiert auch, wenn er keine zugehörigen Shortlinks hat.

REST API:  

| Aktion (CRUD)         | HTTP-Method   | URL           |
| ----------------------|---------------|---------------|
| Create                | POST          |/users/        |
| Read All              | GET           |/users/        |
| Read One              | GET           |/users/\{id\}  |
| Update                | PUT           |/users/\{id\}  |
| Delete                | DELETE        |/users/\{id\}  |

#### Shortlink Service
Mit dem Shortlink Service wird die Entität 'Shortlink' inklusive ihrer zugehörigen Entität 'Domain' sowie einer Liste von 'Targets' verwaltet.
Dies aus dem Grund, weil diese beiden Entitäten eine Composite Aggregation zu Shortlink bilden, d.h die Existenz eines Targets macht nur zusammen 
mit einem Shortlink Sinn, dasselbe bei der Domain-Entity.  

REST API:  

| Aktion (CRUD)         | HTTP-Method   | URL                  |
| ----------------------|---------------|----------------------|
| Create                | POST          |/shortLinks/          |
| Read All              | GET           |/shortLinks/          |
| Read One              | GET           |/shortLinks/\{id\}    |
| Update                | PUT           |/shortLinks/\{id\}    |
| Delete                | DELETE        |/shortLinks/\{id\}    |

#### User-Shortlink Service
Die Aggreagation von Usern und zugehörigen Shortlinks wird über diesen Dienst gemacht.

REST API: 

| Aktion (CRUD)         | HTTP-Method   | URL                       |
| ----------------------|---------------|---------------------------|
| Read All              | GET           |/userShortLink/            |
| Read One              | GET           |/userShortLink/\{userid\}  |

#### Frontend Service
Dieser Service dient als zentrale 'Anlaufstelle' um die Requests entgegenzunehmen und an den entsprechenden zuständigen Service weiterzuleiten.
Der Dienst verwendet dazu Zuul.
Der Dienst läuft unter Port 8080.

#### Registry Service
Alle Services (ausser der Registry Service selbst) sind Eureka Clients und registrieren sich beim Registry Service (Eureka Server).
Dies bringt den Vorteil, dass die einzelnen Dienst nicht über ihre IP-Adressen angesprochen werden müssen, sondern dazu der Dienstname verwendet werden kann.
Der Registry Service selbst stellt unter Port 1111 ein Webinterface zur Verfügung, welches eine Übersicht über die registrierten Dienste bietet.

#### Monitoring Service
Der Monitoring Service dient der Überwachung der Services per Turbine/Hystrix. Das Hystrix Dashboard ist unter  
[:9999/hystrix][r1]
erreichbar.

Die folgenden Methoden/API-Calls werden im Hystix Dashboard angezeigt:

| API                   | Methode       |
| ----------------------|---------------|
| /userShortLink        | POST          |

```diff
- TODO: ergänzen
```

### Testing
Zum Testen der Applikation haben wir einerseits Postman benutzt (API Calls), andererseits haben wir einen
kurzen Belastungstest mit [JMeter][r3] durchgeführt. Dies auch, um herauszufinden, ob das Load-Balancing auch bei etwas mehr
Requests noch funktioniert und wie sich das Hystrix-Dashboard dabei verhält. 

|Test | Concurrent Threads    | Wiederholungen    | Fehlerquote |
|-----| ----------------------|-------------------|-------------|
|#1   | 2                     | 2500              |0.00 %       |
|#2   | 500                   | 10                |34.89 %      |

Auffallend ist hier natürlich die hohe Fehlerquote bei Test 2 mit vielen Threads mit jeweils weniger requests.

Folgende Screenshots zeigen grafisch das Hystrix Dashboard von den beiden Tests:

![img hystrix_1][p2]  
*Bild 2: Hystrix Dashboard Test 1*

![img hystrix_2][p3]  
*Bild 3: Hystrix Dashboard Test 2*

Die HTML-Reports der beiden Tests können mit folgenden Links aufgerufen werden:  

[Report Test #1][r4]  
[Report Test #2][r5]

### Probleme und Herausforderungen

1. Problem: ID's von Usern wurden nicht gemappt, wenn der Userservice vom User-Shortlinkservice aufgerufen wurde.  
Lösung:  
UserServiceApplication von RepositoryRestConfigurerAdapter erben lassen, um folgende Methode überschreiben zu können:
    ```java
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	config.exposeIdsFor(User.class);
    }
    ```
2. Problem: Circuit Breaker/Fallback in user-shortlink-service ShortLinkClient.java funktioniert nur, wenn der Dienst aus der
IDE heraus gestartet wird. Beim Einsatz von Docker wird der Circuit nicht geöffnet und das Fallback nicht ausgelöst, wenn der
short-link-service nicht antwortet.  
Vermutung:  
In UserShortlinkController.java wird das Interface ShortLinkClient.java per @Autowired injected, allerdings ist das Bean dazu mit
der Fallbackklasse nicht mehr eindeutig definiert ud kann wahrscheinlich zur Laufzeit nicht korrekt instanziert werden.  
Lösung:  
nach mehreren Stunden Suche aufgegeben, wahrscheinlich müsste dafür eine Feign Configuration erstellt werden:
[Github Issue zum Thema][r2]


### Installationsanleitung
Projekt von GitHub holen:
```bash
git clone https://github.com/schms27/bfh.shorty
```

Ins Projek wechseln, Projekt builden:
```bash
mvn clean
mvn install
```

Docker Container starten:  
(mit Windows Powershell verwenden da es mit IntelliJ-Console offenbar nicht funktioniert...)
```bash
docker-compose up
```

Alle Docker Container stoppen (können mit docker-compose start wieder gestartet werden)
```bash
docker-compose stop
```

### Debug Notes

#### Hystrix Dashboard
http://localhost:9999/hystrix

Stream auf:  
http://localhost:9999/turbine.stream  
einstellen.  

Momentan wird nur user-shortlink-service (http://localhost:8080/userShortLink) auf dem Monitor überwacht.

#### H2 Zugriff
Die verwendete In-Memory Datenbank H2 kann für die jeweiligen Services wie folgt erreicht werden:

| Service               | URL                       |
| ----------------------|---------------------------| 
| Shortlink-Service     | http://localhost:3333/h2  |


Dazu jeweils als JDBC URL 'jdbc:h2:mem:testdb' angeben  

Username: sa   
Password:

#### Referenzen
##### Literatur & Web
*[Github Issue zum Thema Fallback/Circuit Breaker][r2]
*[Apache JMeter][r3]

##### Bilder



[p1]: documentation/images/domain_model_urlShortener.jpg?raw=true "Bild 1: Domain Model Url-Shortener"
[p2]: documentation/images/hystrix_dashboard_test_1.PNG?raw=true "Bild 2: Hystrix Dashboard Test 1"
[p3]: documentation/images/hystrix_dashboard_test_2.PNG?raw=true "Bild 3: Hystrix Dashboard Test 2"

[r1]: http://localhost:9999/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9999%2Fturbine.stream&delay=1000&title=Shorty%20Application
[r2]: https://github.com/spring-cloud/spring-cloud-netflix/issues/899
[r3]: http://jmeter.apache.org/
[r4]: http://htmlpreview.github.io/?https://github.com/schms27/bfh.shorty/blob/master/documentation/tests/JMeter_test_1/index.html
[r5]: http://htmlpreview.github.io/?https://github.com/schms27/bfh.shorty/blob/master/documentation/tests/JMeter_test_2/index.html