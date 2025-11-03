# Stage 1: Build image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged jar
COPY target/spring-converter-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8085

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]
