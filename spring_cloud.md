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

### Spring Cloud
Um die vorher erwähnten Herausforderungen besser meistern zu können, wurde Spring Cloud entwickelt. Spring Cloud ist ein Teil von Spring IO und beherbergt unter sich eine grössere Anzahl von weiteren Tools, um eine komplette Microservice-Applikation erstellen und betreiben zu können. Die zuvor gezeigte Microservices Architektur könnte mit den Erweiterungen von Spring Cloud in etwa so aussehen:

![img architecture spring cloud][p2]

Wir wollen kurz auf die wichtigsten Bestandteile von Spring Cloud eingehen:
 
#### Eureka
Da die IP Adressen der einzelnen Dienste nicht als fix angenommen werden können, muss ein Hilfsdienst bestehen, bei dem sich die Services registrieren können und der die Requests an den richtigen Empfänger weiterleitet. Dies funktioniert auch, wenn die einzelnen Dienste unter dynamisch ändernden IP-Adressen erreichbar sind.

<span style="background-color: #FFFF00">Beispiel einfügen</span>

#### Hysterix

#### Turbine

#### Config Service

#### Auth Service





[p1]: documentation/images/microservices_arch_1.png?raw=true "Picture 1: Microservices Architecture"
[p2]: documentation/images/microservices_arch_2.png?raw=true "Picture 2: Microservices Architecture with Spring Cloud"
