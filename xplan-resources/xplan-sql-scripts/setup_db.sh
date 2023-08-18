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
    
    echo "01: create init user $DB_INIT_USER and app user $DB_USER"
    psql -q -c "CREATE USER \"$DB_INIT_USER\" PASSWORD '$DB_INIT_PASSWORD';"
    psql -q -c "CREATE USER \"$DB_USER\" PASSWORD '$DB_PASSWORD';"

    echo "02: create db $DB_NAME with owner $DB_INIT_USER"
    psql -q -c "GRANT \"$DB_INIT_USER\" TO \"$PGUSER\";"
    psql -q -c "CREATE DATABASE \"$DB_NAME\" OWNER \"$DB_INIT_USER\";"

    echo "03: install postgis extension"
    psql -q -d $DB_NAME -c "CREATE EXTENSION IF NOT EXISTS postgis;"

    echo "04: grant connect to user $DB_USER on database $DB_NAME"
    psql -q -c "GRANT CONNECT ON DATABASE \"$DB_NAME\" TO \"$DB_USER\";"
fi