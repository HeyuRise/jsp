FROM openjdk:8-jre-alpine

WORKDIR /app

ADD . /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 7080

ENTRYPOINT ["java","-jar","app.jar"]