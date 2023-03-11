FROM amazoncorretto:17-alpine-jdk
# 如果使用Mac M1，本地环境请使用arm64v8的image
# FROM arm64v8/amazoncorretto:17
ENV jarName="web-app-1.0-SNAPSHOT.jar"
WORKDIR /app/
COPY build/libs/$jarName /app/application.jar
COPY scripts/entrypoint.sh /app/entrypoint.sh

ENTRYPOINT ["/bin/sh", "/app/entrypoint.sh"]
