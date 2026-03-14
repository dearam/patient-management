# ---------- Stage 1: Build ----------
FROM gradle:8.7-jdk17 AS builder

WORKDIR /app

# Copy root gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy full module
COPY api-gateway ./api-gateway

# Build only api-gateway module
RUN ./gradlew :api-gateway:bootJar --no-daemon


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/api-gateway/build/libs/*.jar app.jar

EXPOSE 4004

ENTRYPOINT ["java", "-jar", "app.jar"]