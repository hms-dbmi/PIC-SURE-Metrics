FROM openjdk:21-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw clean package -DskipTests

VOLUME /logs

EXPOSE 8080

# Set environment variables with default values
ENV USER_LOG_PATH=/logs/user-interactions
ENV ERROR_LOG_PATH=/logs/errors
RUN ls -l /app/target
# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/target/metrics-0.0.1-SNAPSHOT.jar"]