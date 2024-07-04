FROM openjdk:17-jdk-alpine
COPY ./target/carmanager-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]