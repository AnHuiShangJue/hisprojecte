FROM livingobjects/jre8
VOLUME /tmp
ADD his-eureka-server-one-latest.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8760
