FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_http-client
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/HTTPClient/target/httpclient-1.0.0.jar httpclient.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "httpclient.jar"]