# Spring Boot for PlataformBuilders

This project aims to present a complete POC (crud) of spring-boot 2.3 using
Stacks:

- Spring Boot 2.3
- Spring Actuator 
- Spring Data
- Spring Hateoas
- Swagger 
- Postgres

**To run the project, first build the infrastructure using docker**

 ```docker run --name postgres-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=plataformbuilders -p 5416:5432 -d postgres```
 
This will build a container with postgres installed
After that, clone this code make a
 
```mvn clean install ``` <br>
```mvn spring-boot: run```

Access actuator to check if the service is online

```http://localhost:8080/actuator/health``` 

To test, access the swagger documentation

```http://localhost:8080/swagger-ui/#/cliente-controller```

Available endpoints

POST ```http://localhost:8080/v1/clientes``` <br>
GET ```http://localhost:8080/v1/clientes/{id}```<br>
GET ```http://localhost:8080/v1/clientes?cpf=??&nome=??```<br>
PUT ```http://localhost:8080/v1/clientes/{id}```<br>
DELETE ```http://localhost:8080/v1/clientes/{id}```<br>
PATCH ```http://localhost:8080/v1/clientes/{id}```<br>

Body test POST

```
{
    "nome": "NAME",
    "cpf": "02372201161",
    "dataNascimento": "1986-11-19"
}
```
