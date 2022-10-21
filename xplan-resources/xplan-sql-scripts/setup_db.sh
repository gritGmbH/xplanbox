#!/bin/sh

set -e

##### CONSTANTS
PORT=5432
POSTGRES_USER=postgres

DB_NAME="${DB_NAME:-xplanbox}"


if [ "$( psql -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" )" = '1' ]
then
    echo "Database '$DB_NAME' already exists"
else
    echo "Database '$DB_NAME' does not exist. Will be created..."
    
	if [ "$DB_NAME" = "xplanbox" ]
    then
	    #Create ```INSPIRE``` databases:
	    echo "01: create db with postgis extension"
	    psql -q -p $PORT -U $POSTGRES_USER -c "CREATE DATABASE xplanbox"
	    psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -c "CREATE EXTENSION postgis"
	
	    echo "02: setup dbs"
	    cd /xplan-sql-scripts && psql -q -p $PORT -U $POSTGRES_USER -d xplanbox -f /xplan-sql-scripts/create.sql
	elif [ "$DB_NAME" = "inspireplu" ]
    then
	    #Create ```INSPIRE``` databases:
	    echo "01: create db with postgis extension"
	    psql -q -p $PORT -U $POSTGRES_USER -f /xplan-sql-scripts/inspireplu/02_create_inspireplu_db.sql
	    psql -q -p $PORT -U $POSTGRES_USER -d inspireplu -c "CREATE EXTENSION postgis"
	
	    echo "02: setup db"
	    psql -q -p $PORT -U $POSTGRES_USER -d inspireplu -f /xplan-sql-scripts/inspireplu/04_create_inspireplu_schema.sql
	    psql -q -p $PORT -U $POSTGRES_USER -d inspireplu -f /xplan-sql-scripts/inspireplu/05_create_inspireplu_view.sql
	else
		echo "Unexpected database: '$DB_NAME'! Allowed values: 'xplanbox' (default), 'inspireplu'"
		exit 1
	fi
fi
