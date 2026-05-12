# Base image
FROM eclipse-temurin:25
# Copy app jar into docker container
COPY ./target/weather-app-0.0.1-SNAPSHOT.jar weather-app.jar
# Command to actually run the jar
ENTRYPOINT ["java", "-jar", "/weather-app.jar"]