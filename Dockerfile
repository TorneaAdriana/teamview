FROM openjdk:18

ADD target/demo-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

CMD exec java -jar /app.jar