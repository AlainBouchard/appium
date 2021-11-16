FROM gradle:7.3.0-jdk11-alpine

USER gradle
WORKDIR /home/gradle

COPY --chown=gradle:gradle . /home/gradle

ENTRYPOINT ["./gradlew"]
CMD ["test", "--info"]
