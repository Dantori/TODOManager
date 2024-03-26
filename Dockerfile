FROM amazoncorretto:21.0.1
COPY target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]