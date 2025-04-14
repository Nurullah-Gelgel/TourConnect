# 1. Aşama: Maven ile projeyi derle
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Aşama: Küçük bir OpenJDK imajı ile sadece jar'ı çalıştır
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/TourConnect-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
