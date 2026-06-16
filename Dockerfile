# === STAGE 1: Build & Compile the Source Code ===
FROM --platform=linux/amd64 maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /


# Cache dependencies by copying only the pom file first
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the executable JAR file
COPY src ./src
RUN mvn clean package -DskipTests

# === STAGE 2: Lightweight Runtime Environment ===
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build target/classifiedsapi-*.jar app.jar


# Expose port 8080 for your REST API / Swagger
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]
