FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_currency-rates-provider
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/CurrencyRatesProvider/target/currencyratesprovider-1.0.0.jar currencyratesprovider.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "currencyratesprovider.jar"]