FROM openjdk:11.0.7
ARG JAR_FILE=target/ms-transaction-*.jar

ENV JAVA_OPTS="-Xms64m -Xmx256m"

COPY ${JAR_FILE} ms-transaction.jar

ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -server -jar ms-transaction.jar
