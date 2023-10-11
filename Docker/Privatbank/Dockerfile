FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_privatbank_service
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/PrivatbankService/target/privatbankservice-1.0.0.jar privatbankservice.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "privatbankservice.jar"]