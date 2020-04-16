FROM openjdk:8-jdk-slim
COPY build/libs/*.jar /opt/app.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/opt/app.jar"]
