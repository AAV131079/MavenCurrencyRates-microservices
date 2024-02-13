FROM postgres:16-alpine AS i_currency-rates-postgresql-db
RUN apk update
RUN apk add curl
RUN apk add --no-cache tzdata
EXPOSE 5432
ENV TZ="Europe/Kiev"
ENV DB_SCHEMAS="app"
ENV POSTGRES_DB="currencyratesdb"
ENV POSTGRES_USER="aav"
ENV POSTGRES_PASSWORD="13101979"
ENV PGDATA="/var/lib/postgresql/data/pgdata"
ENV CONTAINER_NAME="currency-rates-postgresql-db"
ENV DB_PORT="5432"
ENV PATH_DATABASE="jdbc:postgresql://${CONTAINER_NAME}:${DB_PORT}/${POSTGRES_DB}"