FROM openjdk:17

COPY ./target/bloggable.jar bloggable.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./bloggable.jar"]