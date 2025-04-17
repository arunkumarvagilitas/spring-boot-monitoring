# Build stage - using official Maven image with Temurin JDK
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package -DskipTests

# Run stage - using official Eclipse Temurin JRE
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "app.jar"]