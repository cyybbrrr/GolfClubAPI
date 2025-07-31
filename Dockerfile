
FROM openjdk:17
COPY target/golfclub-api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
