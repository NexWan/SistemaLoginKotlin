FROM postgres:latest
EXPOSE 5433
CMD ["-p", "5433"]
ENV POSTGRES_USER=wan
ENV POSTGRES_PASSWORD=dockerprueba
ENV POSTGRES_DB=postgres
LABEL authors="lcont"
COPY ./db.sql /docker-entrypoint-initdb.d/init.sql

