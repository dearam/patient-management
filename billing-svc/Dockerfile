# ---------- Stage 1: Build ----------
FROM gradle:8.7-jdk17 AS builder

WORKDIR /app

# Copy root gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy full module
COPY billing-svc ./billing-svc

# Build only billing-svc module
RUN ./gradlew :billing-svc:bootJar --no-daemon


# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/billing-svc/build/libs/*.jar app.jar

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]