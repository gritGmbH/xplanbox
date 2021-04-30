FROM postgis/postgis:11-2.5

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

RUN apt-get update && apt-get -y install unzip

COPY ./setup_db.sh /docker-entrypoint-initdb.d/setup_db.sh

# add xplan-sql-scripts
ADD target/xplan-sql-scripts-*.zip /xplan-sql-scripts/
RUN cd /xplan-sql-scripts/ && unzip -q xplan-sql-scripts-*.zip
