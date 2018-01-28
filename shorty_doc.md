## Dokumentation 'Shorty' URL Shortener
### Software Entwicklung Open Source
### Adrian Aulbach & Simon Schmid
---

### Zweck der Applikation

### Domain Model

![img domain model][p1]  
*Bild 1: Domain Model Url-Shortener*

### Umgesetzte Services

#### User Service
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
http://localhost:9999/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9999%2Fturbine.stream&delay=1000&title=Shorty%20Application
erreichbar.
Die folgenden Methoden/API-Calls werden im Hystix Dashboard angezeigt:

| API                   | Methode       |
| ----------------------|---------------|
| /userShortLink        | POST          |

```diff
- TODO: ergänzen
```


### Probleme und Herausforderungen

- ID's von Usern wurden nicht gemappt, wenn der Userservice vom User-Shortlinkservice aufgerufen wurde.  

Lösung: 
UserServiceApplication von RepositoryRestConfigurerAdapter erben lassen, um folgende Methode überschreiben zu können:

```java
@Override
public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	config.exposeIdsFor(User.class);
}
```

### Installationsanleitung

Projekt builden, Console:
```bash
mvn clean
mvn install
```

Docker Container starten, Console bzw. Powershell da es mit IntelliJ-Console auf Windows offenbar nicht funktioniert:
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
##### Bilder

[p1]: documentation/images/domain_model_urlShortener.jpg?raw=true "Bild 1: Domain Model Url-Shortener"

[r1]: http://localhost:9999/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9999%2Fturbine.stream&delay=1000&title=Shorty%20Application
