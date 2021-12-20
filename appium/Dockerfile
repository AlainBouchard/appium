FROM gradle:6.6.1-jdk11

USER gradle
WORKDIR /home/gradle

COPY --chown=gradle:gradle . /home/gradle

ENTRYPOINT ["./gradlew"]
CMD ["test", "--info"]
