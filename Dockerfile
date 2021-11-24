FROM openjdk:11
WORKDIR /app
COPY ./src /app
RUN javac *.java
ENTRYPOINT ["java", "Main"]