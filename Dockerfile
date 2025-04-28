FROM eclipse-temurin:17
LABEL maintainer="lucastoledo358@gmail.com"
WORKDIR /app
COPY target/sileo-0.0.1-SNAPSHOT.jar /app/sileo.jar
ENTRYPOINT ["java", "-jar", "sileo.jar"]
EXPOSE 8080