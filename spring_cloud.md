## Spring Cloud/Microservices/Eureka Handout
### Software Entwicklung Open Source
### Adrian Aulbach & Simon Schmid
---

### Microservices
Unter Microservice-Architektur versteht man generell eine komplexe Applikation, die in viele kleine, voneinander unabhängige Services unterteilt ist, die alle die folgenden Kriterien erfüllen:
1.	Jeder Service ist verantwortlich für nur eine einzige Funktionalität.
2.	Jeder Service stellt in sich eine eigene, komplette Applikation dar
3.	Jeder Service stellt nach aussen ein stabiles Interface zur Verfügung, normalerweise eine REST API
4.	Die Kommunikation erfolgt über HTTP(s)
5.	Die Services sind technisch voneinander unabhängig
6.	Die Entwickler der jeweiligen Services sind im Normalfall auch für deren Betrieb und Wartung zuständig (devops)

![img architecture][p1]
*Bild 1: Microservices Architektur*

#### Vorteile gegenüber «monolithisch» gebauten Applikationen
Durch die Unabhängigkeit der einzelnen Services voneinander, ist es möglich, diese je nach Anforderung an die jeweilige Aufgabe des Services technisch komplett verschieden aufzubauen und unabhängig voneinander weiterzuentwickeln.

Dazu bietet es sich an, ab einer gewissen Grösse für jeden Service ein eigenes Entwickler- und Operationsteam zu haben. Dadurch kann das benötigte Know-How konzentriert werden und die Weiterentwicklung, Wartung und Betrieb des Services liegt immer in Händen von Spezialisten.

Ein einzelner Dienst ist im gesamten Applikationskontext immer relativ klein und kann so bestmöglich auf die zu erfüllende Aufgabe zugeschnitten werden.

#### Neue Herausforderungen
Weil die einzelnen Microservices nur über REST miteinander kommunizieren, kann es sein, dass sich die Latenz des Netzwerks spürbar auf die Performance auswirkt. Dies kommt natürlich besonders dann zum Tragen, wenn die Services physisch auf weit auseinanderliegenden Servern laufen.

Das Load-Balancing von Microservices ist herausfordernd und kaum manuell zu bewältigen, dazu braucht es Hilfstools, die Last automatisch zwischen den Services verteilen.

Das Monitoring von Microservices ist komplexer als bei monolithischen Applikationen und braucht ebenfalls Hilfstools.
Entwickler werden zu devops, müssen sich also entsprechend in neue Arbeitsfelder einarbeiten und zurechtfinden.

Die Fehlertoleranz und ‘Selbstheilung’ muss ebenfalls durch Hilfsdienste verbessert werden, damit bei Versagen eines einzelnen Dienstes nicht das ganze System versagt bzw. sich möglichste schnell wieder regeneriert.

So soll im Beispiel in obiger Grafik der Statistics Service eine Anfrage Anfrage auch beantworten können, wenn der Account Service nicht erreichbar ist, auch wenn dann Teile der Antwort fehlen. Ansonsten würde eine Störung bei einem einzelnen Service das ganze System lahmlegen.

### Spring Cloud
Um die vorher erwähnten Herausforderungen besser meistern zu können, wurde Spring Cloud entwickelt. Spring Cloud ist ein Teil von Spring IO und beherbergt unter sich eine grössere Anzahl von weiteren Tools, um eine komplette Microservice-Applikation erstellen und betreiben zu können. Die zuvor gezeigte Microservices Architektur könnte mit den Erweiterungen von Spring Cloud in etwa so aussehen:

![img architecture spring cloud][p2]
*Bild 2: Microservices Architektur mit Spring Cloud*

Wir wollen kurz auf die wichtigsten Bestandteile von Spring Cloud eingehen:
 
#### [Eureka][r4]
Da die IP Adressen der einzelnen Dienste nicht als fix angenommen werden können, muss ein Hilfsdienst bestehen, bei dem sich die Services registrieren können und der die Requests an den richtigen Empfänger weiterleitet. Dies funktioniert auch, wenn die einzelnen Dienste unter dynamisch ändernden IP-Adressen erreichbar sind.

Eureka wird selber ebenfalls als Service konfiguriert und gestartet und stellt ein Web-Userinterface zur Verfügung, wo die registrierten Dienste angezeigt und verwaltet werden können. Dies ist zwar nur in vereinfachter Art und Weise möglich, bietet aber trotzdem schon einiges an Information:
![img registry_service_dashboard][p3]
*Bild 3: Eureka Service Dashboard*

##### Beispiel
Eureka Server (Registry Service):
```java
   @SpringBootApplication
   @EnableEurekaServer
   public class RegistryServiceApplication {
	     public static void main(String[] args) {
		      SpringApplication.run(RegistryServiceApplication.class, args);
	     }
  }
```
Eureka Client (registrierter Service):
```java
  @SpringBootApplication
  @EnableDiscoveryClient
  public class TargetserviceApplication {
	    public static void main(String[] args) {
		      SpringApplication.run(TargetserviceApplication.class, args);
	    }
  }
```
#### [Zuul][r2]
Zuul kann als zentrale Anlaufstelle einer Microservice Architektur angeschaut werden. Zuul nimmt die Requests entgegen und leitet sie an den jeweiligen Service weiter. Die API einer Microservice-Application wird im Zuul-Service definiert. Zuul ist wie die Domain-Services ebenfalls ein Service, der von Eureka verwaltet wird. 

##### Beispiel
Frontend-Service:
```java
    @SpringBootApplication
    @EnableDiscoveryClient
    @EnableZuulProxy
    public class FrontendServiceApplication {
	      public static void main(String[] args) {
		      SpringApplication.run(FrontendServiceApplication.class, args);
	      }
    }
```
API-Config:
```yml
# API Proxy configuration
zuul:
  routes:
    target-service:
      path: /targets/**
      serviceId: target-service
      stripPrefix: false
    user-service:
      path: /users/**
      serviceId: user-service
      stripPrefix: false

```

#### [Hystrix][r3]

Hystrix ist eine von Netflix (ursprünglich für den Eigenbedarf) entwickelte Bibliothek, die die Fehlertoleranz von Microservic-Architekturen erhöhen soll.
Dazu werden beispielsweise Abhängigkeiten vorübergehend getrennt, wenn ein Service nicht verfügbar ist (Circuit-Breaker, "Sicherung"), Anfragen zurückgewiesen, wenn der zuständige Service überlastet ist.
Zudem kann das System in "Bulkheads" unterteilt werden. Dies sind Gruppen von Ressourcen, die von einander unabhängig und isoliert sind, so dass sich Fehler nicht von einem Bulkhead zum anderen ausbreiten können.

#### [Turbine][r5]

Turbine ist eine ursprünglich ebenfalls von Netflix entwickelte Bibliothek um die Metriken aller laufenden Dienste zusammenzutragen und auszuwerten. Der Dschungel von Services in einer grossen Microservice App ist unüberschaubar und Turbine setzt genau da an, um aus dem riesigen Strom von Daten die relevanten herauszuholen und anzuziegen, so dass der DevOps stets Herr der Lage bleibt.

#### Config Service

#### Auth Service


#### Referenzen
##### Literatur & Web
Webartikel zu Spring Cloud [DZone Artikel][r1]  
[Eureka Wiki][r4]  
[Zuul Wiki][r2]  
[Hystrix Wiki][r3]  
[Turbine Wiki][r5]  

##### Bilder
Bild 1: Microservices Architecture [Link][r1]  
Bild 2: Microservices Architektur mit Spring Cloud [Link][r1]  
Bild 3: Eureka Service Dashboard (Screenshot aus Übung für Projektarbeit)

[p1]: documentation/images/microservices_arch_1.png?raw=true "Picture 1: Microservices Architecture"
[p2]: documentation/images/microservices_arch_2.png?raw=true "Picture 2: Microservices Architecture with Spring Cloud"
[p3]: documentation/images/registry_dashboard.png?raw=true "Picture 3: Eureka Service Dashboard"

[r1]: https://dzone.com/articles/microservice-architecture-with-spring-cloud-and-do
[r2]: https://github.com/Netflix/zuul/wiki
[r3]: https://github.com/Netflix/Hystrix/wiki
[r4]: https://github.com/Netflix/eureka/wiki
[r5]: https://github.com/Netflix/Turbine/wiki
