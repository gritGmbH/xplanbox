#!/bin/sh

set -e

##### CONSTANTS
HOST=localhost
PORT=5436
POSTGRES_USER=postgres
DATABASE=xplanbox

echo "01: create tables"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/01_create_tables.sql

echo "02 + 03: create trigger"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/02_create_trigger-functions.sql
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/03_create_trigger.sql