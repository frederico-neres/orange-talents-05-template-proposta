FROM centos
RUN yum install -y java-11 && export JAVA_HOME=/path/to/home/for/java11
RUN mkdir /app
COPY ./target/microservice_transacoes-0.0.1-SNAPSHOT.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]