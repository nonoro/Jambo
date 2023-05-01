FROM openjdk:11
COPY build/libs/application.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]

