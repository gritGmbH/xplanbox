#!/bin/sh

set -e

# CONSTANTS for setting environment variables used by psql client
PGUSER=postgres
DB_NAME=xplanbox

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" )" = '1' ]
then
    echo "Database $DB_NAME already exists"
else
    echo "Database $DB_NAME does not exist. Will be created..."
    
    echo "01: create db $DB_NAME"
    psql -q -c "CREATE DATABASE $DB_NAME;"

    echo "02: install postgis extension"
    psql -q -d $DB_NAME -c "CREATE EXTENSION IF NOT EXISTS postgis;"

    echo "03: create user initxplanbox"
    psql -q -c "CREATE USER initxplanbox WITH PASSWORD 'initxplanbox' NOCREATEDB;"

    echo "04: grant permissions to user initxplanbox on database $DB_NAME"
    psql -q -c "ALTER DATABASE $DB_NAME OWNER TO initxplanbox;"

    echo "05: create user xplanbox"
    psql -q -c "CREATE USER xplanbox WITH PASSWORD 'xplanbox' NOCREATEDB;"

    echo "06: grant permissions to user xplanbox on database $DB_NAME"
    psql -q -c "GRANT CONNECT ON DATABASE $DB_NAME TO xplanbox;"
    psql -q -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO xplanbox;"
    psql -q -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO xplanbox;"
    psql -q -c "GRANT USAGE ON SCHEMA public TO xplanbox;"
fi



