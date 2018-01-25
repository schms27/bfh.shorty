## Dokumentation 'Shorty' URL Shortener
### Software Entwicklung Open Source
### Adrian Aulbach & Simon Schmid
---

### Zweck der Applikation

### Domain Model

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


