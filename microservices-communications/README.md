# [Session 5.1: Spring Boot - Microservices creation and communications](https://courses.cs.ut.ee/2023/esi/spring/Main/PS051)

### WebClient
WebClient is an interface representing the main entry point for performing web requests. It is a part of the Spring Web Reactive module and will be replacing the classic RestTemplate. Unlike RestTemplate (synchronous), WebClient is a reactive (asynchronous), non-blocking solution that works over the HTTP/1.1 protocol.

In this session, we are using WebClient to make a request from the product-service to inventory-service to fetch a quantity of a product based on its code, as shown in the figure

Dependency:
- Spring Web
- Spring Data JPA SQL
- PostgresSQL Driver SQL
- Lombok
- Validation I/O
- Spring Reactive Web
