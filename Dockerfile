FROM openjdk:11-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/jumia-excercise-0.0.1-SNAPSHOT.jar /app/jumia-exercise.jar
WORKDIR /app
ENTRYPOINT java -jar jumia-exercise.jar