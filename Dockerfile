FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY pom.xml .
RUN apk add --no-cache maven
RUN mvn package -DskipTests
COPY application.properties .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
