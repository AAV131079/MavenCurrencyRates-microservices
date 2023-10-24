FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_currency-rates-gateway
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/CurrencyRatesGateway/target/currencyratesgateway-1.0.0.jar currencyratesgateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "currencyratesgateway.jar"]