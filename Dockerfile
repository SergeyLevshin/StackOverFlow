FROM openjdk:11
ARG JAR_FILE=build/libs/Stackoverflow-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]