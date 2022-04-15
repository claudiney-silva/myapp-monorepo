FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8081

# AppDynamics Machine Agent
COPY AppServerAgent/ /opt/appdynamics/
ENV JAVA_OPTS -javaagent:/opt/appdynamics/javaagent.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["java", "$JAVA_OPTS", "-jar", "/app.jar"]