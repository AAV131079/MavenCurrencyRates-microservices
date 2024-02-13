FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM amazoncorretto:19-alpine AS i_currency-rates-gateway
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
COPY --from=MAVEN_BUILD /app/CurrencyRatesServices/UtilityServices/CurrencyRatesGateway/target/currencyratesgateway-1.0.0.jar currencyratesgateway.jar
EXPOSE 8080
ENV TZ="Europe/Kiev"
ENTRYPOINT ["java", "-jar", "currencyratesgateway.jar"]