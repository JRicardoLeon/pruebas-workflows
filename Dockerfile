FROM eclipse-temurin:17-jre-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN apk add --no-cache maven
RUN mvn package -DskipTests 

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY application.properties . 
EXPOSE 8080 
ENTRYPOINT ["java", "-jar", "app.jar"]
