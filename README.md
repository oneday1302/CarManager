# Car Manager
This is Spring Boot REST API application.

## About the project
This project is a Spring Boot REST API application that leverages OpenAPI 3 specifications to define and document its endpoints.
It uses SpringDoc to automatically generate OpenAPI documentation, and Swagger-UI to provide a user-friendly interface for exploring and testing the API.
Additionally, Auth0 is integrated for secure authentication and authorization.
#### Key Features
- Spring Boot: A robust framework to create stand-alone, production-grade Spring-based applications. It simplifies the setup and development process with embedded servers like Tomcat and Jetty.
- RESTful API: Design and implement RESTful web services following best practices for resource management, HTTP methods, and status codes.
- OpenAPI 3: Define your API endpoints, request and response models, and other metadata using the OpenAPI 3 specification. This standard ensures your API is well-documented and easy to understand.
- SpringDoc: A library that simplifies the integration of OpenAPI 3 with Spring Boot. It automatically generates OpenAPI documentation based on your code, reducing the need for manual annotations.
- Swagger-UI: An interactive user interface for exploring and testing the API. Swagger-UI is integrated with SpringDoc to provide real-time documentation and testing capabilities directly from your browser.
- Auth0: A flexible and powerful authentication and authorization platform. It provides secure access to your APIs, ensuring only authorized users can access specific endpoints

## Technologies used
- Java 17
- Spring Framework 6
- Spring Boot 3
- Spring Security 6
- SpringDoc 2
- Auth0
- JUnit 5
- FlyWay 9
- PostgreSQL
- Maven
- Docker

## Getting started
These instructions allow to get a copy of this project and run it on a local machine.
1. #### Clone source code from git
   ```
   git clone https://github.com/oneday1302/CarManager.git
   ```
2. #### Build project with Maven
   ```
   mvn package
   ```
3. #### Up docker-compose
   ```
   docker-compose up -d
   ```
4. #### Auth0
   Now application run on a webserver on port 8080 (http://localhost:8080/swagger-ui/index.html).
   Token for authorization:
   ```
   eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ikx1dW1lZlhkYjlYRWZqTWc0Q21EaCJ9.eyJpc3MiOiJodHRwczovL2Nhci1yZXN0LXNlcnZpY2UuZXUuYXV0aDAuY29tLyIsInN1YiI6Im4xVUFVT2ZCUDhzb0t1VlpRZW11aGVpbjhOeHY5SVE5QGNsaWVudHMiLCJhdWQiOiJodHRwczovL2NhcmFwaSIsImlhdCI6MTcyMDk0ODIxNCwiZXhwIjoxNzIxMDM0NjE0LCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiJuMVVBVU9mQlA4c29LdVZaUWVtdWhlaW44Tnh2OUlROSJ9.c1hBsK4Ads2iNWtuoFtoqo67F3IOdOvQ6zHZaeZpTBNQ5UYeM4igwFYH0U4HbioCtxWx48gYzdLINx5m3N0flPf5-1EBQ1SCrl32Z6o3V9_z3ZujAdi6ay-i20H6XtuYvDMzrQQfBSUhXHHYmKuqUkeeTBnvCGIG5a4ViCz0JPbqCua8ZskAwiKjJ3BzzeSFrPPAzFp5RewMIXFE8KBNSdpHZlUVOG4qVvfAC-lYoseTx7W88-Tkr7CKE0Siigv4K91PZVPNTMyLvoaf7jM3jmvkSBHUJbjPXDCy02xWBnR9dwnvezoLwbY5Js1Bzc3mSYSi0GUptJk2DjkDz469YA
   ```
