# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container
COPY target/weather-analyzer-0.0.1-SNAPSHOT.jar /app/weather-analyzer.jar

# Expose port 8080 
EXPOSE 8080

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "weather-analyzer.jar"]
