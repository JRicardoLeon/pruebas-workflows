FROM maven:3.8.5-jdk-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn package -DskipTests 

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY application.properties . 
EXPOSE 8080 
ENTRYPOINT ["java", "-jar", "app.jar"]
