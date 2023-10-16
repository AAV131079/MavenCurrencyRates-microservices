FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM openjdk:19-jdk-alpine AS i_currency-rates-consumer
RUN apk update
RUN apk add curl
COPY --from=MAVEN_BUILD /app/CurrencyRatesConsumer/target/currencyratesconsumer-1.0.0.jar currencyratesconsumer.jar
EXPOSE 8081
ENV DB_SCHEMAS="app"
ENV CONTAINER_NAME="currency-rates-postgresql-db"
ENV DB_PORT="5432"
ENV POSTGRES_DB="currencyratesdb"
ENV POSTGRES_USER="aav"
ENV POSTGRES_PASSWORD="13101979"
ENV PATH_DATABASE="jdbc:postgresql://${CONTAINER_NAME}:${DB_PORT}/${POSTGRES_DB}"
ENTRYPOINT ["java", "-jar", "currencyratesconsumer.jar"]