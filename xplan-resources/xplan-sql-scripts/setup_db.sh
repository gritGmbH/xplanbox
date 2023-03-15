#!/bin/sh

set -e

# CONSTANTS for setting environment variables used by psql client
PGUSER=postgres
DB_NAME="${XPLAN_DB_NAME:-xplanbox}"
DB_USER="${XPLAN_DB_USER:-xplanbox}"
DB_PASSWORD="${XPLAN_DB_PASSWORD:-xplanbox}"
DB_INIT_USER="${XPLAN_DB_INIT_USER:-initxplanbox}"
DB_INIT_PASSWORD="${XPLAN_DB_INIT_PASSWORD:-initxplanbox}"

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" )" = '1' ]
then
    echo "Database $DB_NAME already exists"
else
    echo "Database $DB_NAME does not exist. Will be created..."
    
    echo "01: create db $DB_NAME"
    psql -q -c "CREATE DATABASE \"$DB_NAME\"";

    echo "02: install postgis extension"
    psql -q -d $DB_NAME -c "CREATE EXTENSION IF NOT EXISTS postgis;"

    echo "03: create user $DB_INIT_USER"
    psql -q -c "CREATE USER \"$DB_INIT_USER\" LOGIN NOCREATEDB INHERIT PASSWORD '$DB_INIT_PASSWORD';"

    echo "04: grant permissions to user $DB_INIT_USER on database $DB_NAME"
    psql -q -c "ALTER DATABASE \"$DB_NAME\" OWNER TO \"$DB_INIT_USER\";"
    psql -q -c "ALTER USER \"$DB_INIT_USER\" WITH SUPERUSER;"

    echo "05: create user $DB_USER"
    psql -q -c "CREATE USER \"$DB_USER\" LOGIN NOCREATEDB NOINHERIT PASSWORD '$DB_PASSWORD';"

    echo "06: grant permissions to user $DB_USER on database $DB_NAME"
    psql -q -c "GRANT CONNECT ON DATABASE \"$DB_NAME\" TO \"$DB_USER\";"
    psql -q -c "ALTER DEFAULT PRIVILEGES FOR USER \"$DB_USER\" IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO \"$DB_USER\";"
    psql -q -c "GRANT USAGE ON SCHEMA public TO \"$DB_USER\";"
    psql -q -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO \"$DB_USER\";"
fi