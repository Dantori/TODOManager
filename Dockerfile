FROM amazoncorretto:21.0.1
COPY target/todomanager-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]