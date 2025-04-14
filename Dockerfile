FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Bağımlılıkları indir
COPY pom.xml .
RUN mvn dependency:go-offline

# Kaynakları kopyala ve derle
COPY src ./src
RUN mvn clean package -DskipTests


# 2. Aşama: Küçük bir OpenJDK imajı ile sadece jar'ı çalıştır
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/TourConnect-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
