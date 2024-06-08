FROM maven:3.6.3-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package

 Package stage
FROM openjdk:17
ARG JAR_FILE=./target/enoca-task-0.0.1-SNAPSHOT.jar
COPY ./target/enoca-task-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "application.jar"]