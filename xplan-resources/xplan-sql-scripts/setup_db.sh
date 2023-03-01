#!/bin/sh

set -e

##### CONSTANTS
POSTGRES_USER=postgres
DB_NAME=xplanbox

if [ "$( psql -U $POSTGRES_USER -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" )" = '1' ]
then
    echo "Database $DB_NAME already exists"
else
    echo "Database $DB_NAME does not exist. Will be created..."
    
    echo "01: create db $DB_NAME"
    psql -q -U $POSTGRES_USER -c "CREATE DATABASE $DB_NAME;"

    echo "02: install postgis extension"
    psql -q -U $POSTGRES_USER -d $DB_NAME -c "CREATE EXTENSION IF NOT EXISTS postgis;"

    echo "03: create user xplanbox"
    psql -q -U $POSTGRES_USER -c "CREATE USER xplanbox WITH PASSWORD 'xplanbox' NOCREATEDB;"

    echo "04: grant permissions to user xplanbox on database $DB_NAME"
    psql -q -U $POSTGRES_USER -c "GRANT CONNECT ON DATABASE $DB_NAME TO xplanbox;"
    psql -q -U $POSTGRES_USER -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO xplanbox;"
    psql -q -U $POSTGRES_USER -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO xplanbox;"
fi
