FROM amazoncorretto:11
RUN mkdir -p /DB
WORKDIR /
COPY target/awsome-pastebin-0.0.1-SNAPSHOT.jar /awsome-pastebin.jar
CMD java -jar awsome-pastebin.jar