FROM openjdk:8

WORKDIR /opt/spring_boot

COPY /target/spring-boot*.jar delivery-0.1.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD ["java", "-jar", "delivery-0.0.1.jar"]
