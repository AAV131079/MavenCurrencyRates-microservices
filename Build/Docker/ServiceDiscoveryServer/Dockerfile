FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM amazoncorretto:19-alpine AS i_service-discovery-server
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
COPY --from=MAVEN_BUILD /app/CurrencyRatesServices/UtilityServices/ServiceDiscoveryServer/target/servicediscoveryserver-1.0.0.jar servicediscoveryserver.jar
EXPOSE 8761
ENV TZ="Europe/Kiev"
ENTRYPOINT ["java", "-jar", "servicediscoveryserver.jar"]