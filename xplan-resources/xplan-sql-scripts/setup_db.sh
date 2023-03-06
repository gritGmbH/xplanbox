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
    psql -q -c "CREATE USER initxplanbox LOGIN NOCREATEDB INHERIT PASSWORD 'initxplanbox';"

    echo "04: grant permissions to user initxplanbox on database $DB_NAME"
    psql -q -c "ALTER DATABASE $DB_NAME OWNER TO initxplanbox;"
    psql -q -c "ALTER USER initxplanbox WITH SUPERUSER;"

    echo "05: create user xplanbox"
    psql -q -c "CREATE USER xplanbox LOGIN NOCREATEDB NOINHERIT PASSWORD 'xplanbox';"

    echo "06: grant permissions to user xplanbox on database $DB_NAME"
    psql -q -c "GRANT CONNECT ON DATABASE $DB_NAME TO xplanbox;"
    psql -q -c "ALTER DEFAULT PRIVILEGES FOR USER xplanbox IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO xplanbox;"
    psql -q -c "GRANT USAGE ON SCHEMA public TO xplanbox;"
    psql -q -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO xplanbox;"
fi



