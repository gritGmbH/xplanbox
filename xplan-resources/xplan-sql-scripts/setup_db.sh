#!/bin/sh

set -e

##### CONSTANTS
PORT=5432
POSTGRES_USER=postgres

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='xplanbox'" )" = '1' ]
then
    echo "Database xplanbox already exists"
else
    echo "Database xplanbox does not exist. Will be created..."
    
    #Create ```INSPIRE``` databases:
    echo "01: create db with postgis extension"
    psql -q -p $PORT -U $POSTGRES_USER -c "CREATE DATABASE xplanbox"
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -c "CREATE EXTENSION postgis"
    echo "02: setup xplan schemas"
    cd /xplan-sql-scripts && psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/create.sql
    echo "03: setup inspireplu schema"
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/inspireplu/01_create_inspireplu_schema.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/inspireplu/02_create_inspireplu_view.sql

fi
