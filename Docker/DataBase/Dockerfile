FROM postgres:16.0 AS i_currency-rates-postgresql-db
EXPOSE 5432
ENV POSTGRES_DB="currency-rates-db"
ENV POSTGRES_PASSWORD="your-password"
ENV POSTGRES_USER="admin"
ENV PGDATA="/var/lib/postgresql/data/pgdata"
ENV CONTAINER_NAME="currency-rates-postgresql-db"
ENV PORT="5432"
ENV PATH_DATABASE="jdbc:postgresql://${CONTAINER_NAME}:${PORT}/${POSTGRES_DB}"