FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_service-discovery-server
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/ServiceDiscoveryServer/target/servicediscoveryserver-1.0.0.jar servicediscoveryserver.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "servicediscoveryserver.jar"]