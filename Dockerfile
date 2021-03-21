FROM openjdk:11-jdk
WORKDIR /app
COPY target/craft-beer-1.0.jar /app/craft-beer-app.jar
CMD ["java", "-jar", "craft-beer-app.jar"]