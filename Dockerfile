FROM adoptopenjdk:8-jre-openj9

EXPOSE 8762

ADD build/libs/*.jar gateway-server.jar

ENTRYPOINT ["java", "-jar", "gateway-server.jar"]
