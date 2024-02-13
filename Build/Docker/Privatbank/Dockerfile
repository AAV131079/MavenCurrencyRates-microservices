FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM amazoncorretto:19-alpine AS i_privatbank_service
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
COPY --from=MAVEN_BUILD /app/CurrencyRatesServices/ApplicationServices/PrivatbankService/target/privatbankservice-1.0.0.jar privatbankservice.jar
EXPOSE 8084
ENV TZ="Europe/Kiev"
ENTRYPOINT ["java", "-jar", "privatbankservice.jar"]