FROM adoptopenjdk/openjdk11:slim
VOLUME /tmp
ADD ./target/ofac-opensource-1.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]