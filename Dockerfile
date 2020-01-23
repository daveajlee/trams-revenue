FROM openjdk:13-jdk-alpine
VOLUME /tmp
ADD target/trams-revenue.jar trams-revenue.jar
ENV JAVA_OPTS=""
ENV EUREKA_SERVICE_URL http://localhost:8761
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /trams-revenue.jar" ]