#!/bin/sh

set -e

##### CONSTANTS
PORT=5432
POSTGRES_USER=postgres

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='inspireplu'" )" = '1' ]
then
    echo "Database 'inspireplu' already exists"
else
    echo "Database 'inspireplu' does not exist. Will be created..."

    #Create ```INSPIRE``` databases:
    echo "01: create db with postgis extension"
    psql -q -p $PORT -U $POSTGRES_USER -f /xplan-sql-scripts/inspireplu/02_create_inspireplu_db.sql
    psql -q -p $PORT -U $POSTGRES_USER -d inspireplu -c "CREATE EXTENSION postgis"

    echo "02: setup db"
    psql -q -p $PORT -U $POSTGRES_USER -d inspireplu -f /xplan-sql-scripts/inspireplu/04_create_inspireplu_schema.sql
fi