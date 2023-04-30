FROM postgres:15.2-alpine

COPY create_checkout_db.sql /docker-entrypoint-initdb.d/create_checkout_db.sql

ENV PGDATA=/data
ENV POSTGRES_PASSWORD=postgres
