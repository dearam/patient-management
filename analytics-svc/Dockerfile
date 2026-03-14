# ---------- Stage 1: Build ----------
FROM gradle:8.7-jdk17 AS builder

WORKDIR /app

# Copy root gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy full module
COPY analytics-svc ./analytics-svc

# Build only analytics-svc module
RUN ./gradlew :analytics-svc:bootJar --no-daemon


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/analytics-svc/build/libs/*.jar app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]