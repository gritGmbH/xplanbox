FROM postgis/postgis:14-3.3

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

COPY ./setup_db.sh /docker-entrypoint-initdb.d/setup_db.sh

# add xplan-sql-scripts
RUN mkdir /xplan-sql-scripts

COPY ./src/main/resources /xplan-sql-scripts/
