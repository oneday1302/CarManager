FROM openjdk:17-jdk-alpine
COPY ./target/carmanager-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.datasource.url=${DB_URL}", "-Dspring.datasource.username=${DB_USERNAME}", "-Dspring.datasource.password=${DB_PASSWORD}", "application.jar"]