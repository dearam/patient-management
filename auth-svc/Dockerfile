# ---------- Stage 1: Build ----------
FROM gradle:8.7-jdk17 AS builder

WORKDIR /app

# Copy root gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy full module
COPY auth-svc ./auth-svc

# Build only auth-svc module
RUN ./gradlew :auth-svc:bootJar --no-daemon


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/auth-svc/build/libs/*.jar app.jar

EXPOSE 4005

ENTRYPOINT ["java", "-jar", "app.jar"]