# ===== Stage 1: Build =====
FROM gradle:8.14-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean bootJar --no-daemon

# ===== Stage 2: Run =====
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]