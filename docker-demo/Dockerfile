FROM anapsix/alpine-java:8_server-jre_unlimited
MAINTAINER spock.wang@wanwuyunlian.com
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8701
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]