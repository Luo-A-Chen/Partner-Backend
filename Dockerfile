FROM maven:3.5-jdk-8-alpine as build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

CMD["java","-jar","/app/target/user-center-backend-0.0.1-SHAPSHOT.jar","--spring.profiles.active=prod"]