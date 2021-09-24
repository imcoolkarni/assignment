FROM adoptopenjdk/openjdk11
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4002,suspend=n

VOLUME /service1

#ARG JAR_FILE=target/service2-0.0.1-SNAPSHOT.jar
#
#ARG LOGBACK=/target/classes/logback.xml


COPY target/service1-0.0.1-SNAPSHOT.jar service1.jar
COPY target/classes/logback.xml /service1/logback.xml

ENTRYPOINT ["java","-Dlogging.config=/service1/logback.xml","-jar","service1.jar"]