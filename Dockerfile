#
# Build stage
#
# Use a base image with Java 11 installed
#FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory in the container
#WORKDIR /app

# Copy the Maven project files to the container
#COPY pom.xml .

# Download project dependencies

# Copy the application source code to the container
#COPY src ./src
#COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# Build the application
# Build the application
#RUN mvn package -DskipTests

# Set the entry point for the container
#ENTRYPOINT ["java", "-jar", "target/ProjectDeploy-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.4-openjdk-17-slim AS build

# Copy Maven files for dependency resolution
COPY pom.xml ./
COPY .mvn .mvn

# Copy application source code
COPY src src

# Build the project and create the executable JAR
RUN mvn clean install

# Stage 2: Run stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR ays-be

# Copy the JAR file from the build stage
COPY --from=build target/*.jar /app/ays-be.jar

# Expose port 9790
EXPOSE 9790

# Set the entrypoint command for running the application
ENTRYPOINT ["java", "-jar", "/app/ays-be.jar"]