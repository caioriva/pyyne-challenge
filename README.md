# pyyne-challenge
Private repository for Pyyne's challenge

## Requirements

For building and running the application you need:

- [Spring Boot 2.7.5](http://projects.spring.io/spring-boot/)
- [OpenJDK 19.0.1](https://jdk.java.net/19/)
- [Maven 3.8.1](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.pyyne.bankmanager.BankManagerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Testing the endpoints locally

There are also several ways to hit the endpoints locally but it's recommended to use [Postman](https://www.postman.com/downloads/) because this repository already includes the exported Postman collection [Pyyne Challenge.postman_collection.json](./Pyyne Challenge.postman_collection.json).

OBS: it's important to use it because values from some path variables and request parameters require a specific formatting in some endpoints.
