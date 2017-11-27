FROM postgres:9.6

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

ENV POSTGIS_VERSION=2.3

RUN apt-get update && apt-get -y install unzip
RUN apt-get update && apt-get -y install wget

RUN set -ex && echo "deb http://apt.postgresql.org/pub/repos/apt xenial-pgdg main" >> /etc/apt/sources.list && wget --quiet -O - http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc | apt-key add - && apt-get update && apt-get install -y postgresql-9.6-postgis-$POSTGIS_VERSION postgresql-contrib-9.6

COPY ./setup_db.sh /docker-entrypoint-initdb.d/setup_db.sh

# add xplan-sql-scripts
ADD target/xplan-sql-scripts-*.zip /xplan-sql-scripts/
RUN cd /xplan-sql-scripts/ && unzip -q xplan-sql-scripts-*.zip