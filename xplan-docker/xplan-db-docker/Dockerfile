FROM mdillon/postgis:9.3

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

RUN apt-get update && apt-get install unzip

COPY ./setup_db.sh /docker-entrypoint-initdb.d/setup_db.sh

# add xplan-sql-scripts
ADD target/xplan-sql-scripts-*.zip /xplan-sql-scripts/
RUN cd /xplan-sql-scripts/ && unzip -q xplan-sql-scripts-*.zip