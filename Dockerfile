#
# Build stage
#
# Use a base image with Java 11 installed
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .

# Download project dependencies

# Copy the application source code to the container
COPY src ./src

# Build the application

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "target/ProjectDeploy-0.0.1-SNAPSHOT.jar"]