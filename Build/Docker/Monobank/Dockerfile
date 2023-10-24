FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_monobank_service
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/MonobankService/target/monobankservice-1.0.0.jar monobankservice.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "monobankservice.jar"]