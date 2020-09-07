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

To test, access the swagger documentation

```http://localhost:8080/swagger-ui/#/cliente-controller```

Access actuator to check if the service is online

```http://localhost:8080/actuator/health``` 

Available endpoints

POST ```http://localhost:8080/v1/clientes```
GET ```http://localhost:8080/v1/clientes/{id}```
GET ```http://localhost:8080/v1/clientes?cpf=??&nome=??```
PUT ```http://localhost:8080/v1/clientes/{id}```
DELETE ```http://localhost:8080/v1/clientes/{id}```
PATCH ```http://localhost:8080/v1/clientes/{id}```

Body test POST

```
{
    "nome": "NAME",
    "cpf": "02372201161",
    "dataNascimento": "1986-11-19"
}
```
