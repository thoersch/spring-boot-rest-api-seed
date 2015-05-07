## Description

This is a seed and example project for building a RESTful API using the following technologies:

* Java 8
* Spring Boot
* Jersey
* Hibernate
* Jackson
* Spring DI
* Postgresql

## Install Postgresql
The seed project is using PostgreSQL 9.3+ and can be installed quite easily on mac, linux or windows following a [guide](https://www.codefellows.org/blog/three-battle-tested-ways-to-install-postgresql)

## Create the database user

```
CREATE ROLE "SpringBootUser" LOGIN
  ENCRYPTED PASSWORD 'md513445691374efba1aaee7b0912e63af3'
  SUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;
```

## Create the database

```
CREATE DATABASE "SpringBootRestApi"
  WITH ENCODING='UTF8'
       OWNER="SpringBootUser"
       CONNECTION LIMIT=-1;
```

## Build the project

```
mvn clean install
```

## Run the migrations

```
mvn liquibase:update -P local
```

## Running the API

Start the service by running the following command:

```
java -jar target/spring-boot-rest-api-seed-1.0-SNAPSHOT.jar
```

You can now test the service by consuming the api on port 8080. Some routes you can try in your browser (GET requests):


* http://127.0.0.1:8080/users

* http://127.0.0.1:8080/users/1

* http://127.0.0.1:8080/books

* http://127.0.0.1:8080/books/2

* http://127.0.0.1:8080/users/1/books


You can add new content by posting payloads like below:

```
POST 127.0.0.1:8080/users
Content-Type: application/json
{
  "firstName": "you",
  "lastName": "here",
  "emailAddress": "you.here@example.com",
  "profilePicture": "yourface.png"
}
```
## License

Copyright © 2014 Tyler Hoersch

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
