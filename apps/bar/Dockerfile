FROM adoptopenjdk/openjdk8:alpine
EXPOSE 8080

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# AppDynamics Machine Agent
COPY --chown=spring AppServerAgent/ /opt/appdynamics/
ENV JAVA_OPTS -javaagent:/opt/appdynamics/javaagent.jar

# ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]