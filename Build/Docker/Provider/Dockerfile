FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM amazoncorretto:19-alpine AS i_currency-rates-provider
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
COPY --from=MAVEN_BUILD /app/CurrencyRatesServices/ApplicationServices/CurrencyRatesProvider/target/currencyratesprovider-1.0.0.jar currencyratesprovider.jar
EXPOSE 8082
ENV TZ="Europe/Kiev"
ENTRYPOINT ["java", "-jar", "currencyratesprovider.jar"]