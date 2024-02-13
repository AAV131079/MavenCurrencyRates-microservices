FROM maven:3.9.3 AS MAVEN_BUILD
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package
FROM amazoncorretto:19-alpine AS i_monobank_service
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
COPY --from=MAVEN_BUILD /app/CurrencyRatesServices/ApplicationServices/MonobankService/target/monobankservice-1.0.0.jar monobankservice.jar
EXPOSE 8085
ENV TZ="Europe/Kiev"
ENTRYPOINT ["java", "-jar", "monobankservice.jar"]