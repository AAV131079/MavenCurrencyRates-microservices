FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_nbu_service
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/NBUService/target/nbuservice-1.0.0.jar nbuservice.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "nbuservice.jar"]