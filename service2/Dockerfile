FROM adoptopenjdk/openjdk11
EXPOSE 9091
ENV JAVA_TOOL_OPTIONS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4002,suspend=n

VOLUME /service2

#ARG JAR_FILE=target/service2-0.0.1-SNAPSHOT.jar
#
#ARG LOGBACK=/target/classes/logback.xml


COPY target/service2-0.0.1-SNAPSHOT.jar service2.jar
COPY target/classes/logback.xml /service2/logback.xml

ENTRYPOINT ["java","-Dlogging.config=/service2/logback.xml","-jar","service2.jar"]