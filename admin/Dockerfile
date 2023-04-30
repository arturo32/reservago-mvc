FROM postgres:15.2-alpine

COPY create_admin_db.sql /docker-entrypoint-initdb.d/create_admin_db.sql

ENV PGDATA=/data
ENV POSTGRES_PASSWORD=postgres