FROM openjdk:21-jdk
LABEL authors="bernardo"
WORKDIR /app
COPY  target/sghss-0.0.1-SNAPSHOT.jar sghss.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sghss.jar"]