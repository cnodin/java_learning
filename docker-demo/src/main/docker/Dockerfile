FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD docker-demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]