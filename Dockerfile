# FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-slim
FROM gradle:7.3.0-jdk11-alpine

USER gradle
WORKDIR /home/gradle

# RUN groupadd -g 1000 ubuntu && useradd -u 1000 -g ubuntu -s /bin/sh ubuntu
COPY --chown=gradle:gradle . /home/gradle
# COPY --chown=ubuntu . /home/ubuntu
# USER ubuntu
# WORKDIR /home/ubuntu

ENTRYPOINT ["sh"]
# ENTRYPOINT ["./gradlew"]
# CMD ["test"]
