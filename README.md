# Rock Paper Scissors REST API

A rock-paper-scissors game backend written on Java.

Tech stack: Java 17, spring-boot, PostgreSQL, Docker

## How to run the backend

### Maven + Spring-Boot
Prerequisites: installed docker jdk 17+, maven, git

```
cd <your project dir> 
git clone https://github.com/alexeymoskvinov/rock-paper-scissors.git
cd rock-paper-scissors
```
#### To run with integration tests (started docker is needed)
```
mvn clean install
mvn spring-boot:run
```

#### To run without integration tests
```
mvn clean install -DskipTests
mvn spring-boot:run
```