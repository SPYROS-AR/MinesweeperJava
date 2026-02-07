# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the project (skipping tests to speed it up)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the previous stage
# Note: pom.xml defines finalName as 'MinesweeperJava'
COPY --from=build /app/target/MinesweeperJava.jar .

# Copy the 'lib' folder since your pom.xml configures the classpath to look there
COPY --from=build /app/target/lib ./lib

# Command to run the application
CMD ["java", "-jar", "MinesweeperJava.jar"]
