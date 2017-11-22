#!/bin/sh

set -e

##### CONSTANTS
HOST=localhost
PORT=5432
POSTGRES_USER=postgres

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='plu_blob'" )" = '1' ]
then
    echo "Database 'plu_blob' already exists"
else
    echo "Database 'plu_blob' does not exist. Will be created..."

    #Create ```INSPIRE``` databases:
    echo "01: create db with postgis extension"
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -f /xplan-sql-scripts/inspireplu/blob/02_create_plu_blob_db.sql
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -d plu_blob -c "CREATE EXTENSION postgis"

    echo "02: setup db"
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -d plu_blob -f /xplan-sql-scripts/inspireplu/blob/04_create_plu_blob_schema.sql
fi


if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='plu_canonical'" )" = '1' ]
then
    echo "Database 'plu_canonical' already exists"
else
    echo "Database 'plu_canonical' does not exist. Will be created..."

    #Create ```INSPIRE``` databases:
    echo "01: create db with postgis extension"
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -f /xplan-sql-scripts/inspireplu/canonical/02_create_plu_canonical_db.sql
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -d plu_canonical -c "CREATE EXTENSION postgis"

    echo "02: setup db"
    psql -q  -h $HOST -p $PORT -U $POSTGRES_USER -d plu_canonical -f /xplan-sql-scripts/inspireplu/canonical/04_create_plu_canonical_schema.sql
fi