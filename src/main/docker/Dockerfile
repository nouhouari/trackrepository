FROM java:8-jdk-alpine

LABEL maintainer "houarinourredine@gmail.com"

ENV JAVA_OPTS="" \
    KAFKA_AUTO_RESET=earliest \
    KAFKA_GROUPID=trackGroup \
    KAFKA_TRACK_TOPIC=trackTopic \
    KAFKA_SERVER_HOST=10.200.10.1 \
    KAFKA_SERVER_PORT=9092 \
    KAFKA_ADVERTISER_HOST=10.200.10.1 \
    ADSB_SERVER_HOST=pub-vrs.adsbexchange.com \
    ADSB_SERVER_PORT=32005 \
    DB_HOST=localhost \
    DB_PORT=5432 \
    DB_USER=trackrepository \
    DB_PASSWORD=trackrepository

VOLUME /tmp
ADD trackrepository-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dapp.port=${app.port}", "-jar","/app.jar"]