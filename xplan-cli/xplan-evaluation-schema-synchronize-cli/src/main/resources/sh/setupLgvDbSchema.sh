#!/bin/sh

set -e

##### CONSTANTS
HOST=localhost
PORT=5436
POSTGRES_USER=postgres
DATABASE=xplanbox

echo "00: create schema"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/00_create_schema.sql

echo "01: create function"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/01_create_function.sql

echo "02: create tables"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/02_create_tables.sql

echo "03 + 04: create trigger"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/03_create_trigger-function.sql
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/04_create_trigger.sql