FROM gradle:7.2-jdk11
WORKDIR /app
EXPOSE 8080 5005
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dblabla", "-jar", "/app/build/libs/application-0.0.1-SNAPSHOT.jar"]
