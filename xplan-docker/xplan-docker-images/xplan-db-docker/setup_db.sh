#!/bin/sh

set -e

##### CONSTANTS
PORT=5432
POSTGRES_USER=postgres

if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='xplanbox'" )" = '1' ]
then
    echo "Database already exists"
else
    echo "Database does not exist. Will be created..."

    #Create ```INSPIRE``` databases:
    echo "01: create db with postgis extension"
    psql -q -p $PORT -U $POSTGRES_USER -c "CREATE DATABASE xplanbox"
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -c "CREATE EXTENSION postgis"

    echo "02: setup dbs"
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan3/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan40/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan41/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan41nsm/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan50/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan51/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan52/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan53/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplan54/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/fix/xplansyn/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan3/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan40/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan41/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan41nsm/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan50/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan51/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan52/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan53/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplan54/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/pre/xplansyn/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan3/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan40/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan41/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan41nsm/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan50/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan51/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan52/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan53/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplan54/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/archive/xplansyn/create.sql
    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/xplanmgr/create.sql
fi