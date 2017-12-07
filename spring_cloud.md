## Spring Cloud/Microservices/Eureka Handout
### Software Entwicklung Open Source
### Adrian Aulbach & Simon Schmid

### Microservices
Unter Microservice-Architektur versteht man generell eine komplexe Applikation, die in viele kleine, voneinander unabhängige Services unterteilt ist, die alle die folgenden Kriterien erfüllen:
1.	Jeder Service ist verantwortlich für nur eine einzige Funktionalität.
2.	Jeder Service stellt in sich eine eigene, komplette Applikation dar
3.	Jeder Service stellt nach aussen ein stabiles Interface zur Verfügung, normalerweise eine REST API
4.	Die Kommunikation erfolgt über HTTP(s)
5.	Die Services sind technisch voneinander unabhängig
6.	Die Entwickler der jeweiligen Services sind im Normalfall auch für deren Betrieb und Wartung zuständig (devops)

![alt text][p1]

[p1]: documentation/images/microservices_arch_1.png?raw=true "Picture 1: Microservices Architecture"

[p2]: https://github.com/schms27/bfh.shorty/documentation/images/microservices_arch_2.png "Picture 2: Microservices Architecture with Spring Cloud"
