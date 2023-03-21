# Java back-end developer test

## Introduction

- A HTTP-based mini game back-end in Java which registers game scores for different users and levels, with the capability to return high score lists per level.

## Requirements developed

- Login with key to 10min
- Post a user's score to a level with key
- Get a high score list for a level limited in 15 and order by score

## Tools and Libs used (respecting the requests)

- Java 17
- Maven
- HttpServer
- JUnit5 (test)
- Mockito (test)
- Spring-test (test)

## To compile
```
./mvnw compile
```

## To run
```
java -classpath "target/classes" com.king.HighscoreServer
```

## To test
```
./mvnw test
```

## Api requests

- Login
```
GET -> http://localhost:8081/<userId>/login
```
```
Body answer -> <keyUser>
```

- Post score
```
POST -> http://localhost:8081/<level>/score?sessionkey=<keyUser>
```

- Get score list
```
GET -> http://localhost:8081/<level>/highscorelist
```
```
Body answer -> <scoreList>
```

## Doc

[See in](doc/index.html)