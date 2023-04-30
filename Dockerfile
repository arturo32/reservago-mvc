FROM postgres:15.2-alpine

COPY create_reservago_mvc_db.sql /docker-entrypoint-initdb.d/create_reservago_mvc_db.sql

ENV PGDATA=/data
ENV POSTGRES_PASSWORD=postgres
