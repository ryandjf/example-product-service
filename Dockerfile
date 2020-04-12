FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
