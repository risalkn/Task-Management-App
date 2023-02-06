# define base docker image
FROM openjdk:11
VOLUME /temp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]