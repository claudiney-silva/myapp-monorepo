FROM adoptopenjdk/openjdk8:alpine
EXPOSE 8082

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# AppDynamics Machine Agent
ADD AppServerAgent.zip /opt/appdynamics/
RUN unzip /opt/appdynamics/AppServerAgent.zip -d /opt/appdynamics/ && rm -f /opt/appdynamics/AppServerAgent.zip
RUN chwn -R spring:spring /opt/appdynamics/
#COPY --chown=spring AppServerAgent/ /opt/appdynamics/
ENV JAVA_OPTS -Dappagent.start.timeout=90 -javaagent:/opt/appdynamics/javaagent.jar

# ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]