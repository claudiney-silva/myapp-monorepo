FROM adoptopenjdk/openjdk11:armv7l-ubuntu-jre11u-2022-02-11-03-47-beta-nightly
EXPOSE 8081

# AppDynamics Machine Agent
COPY AppServerAgent/ /opt/appdynamics/
ENV JAVA_OPTS -javaagent:/opt/appdynamics/javaagent.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]