#!/bin/sh

###
# #%L
# xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
# %%
# Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
# %%
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
# #L%
###

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

echo "05: grant"
psql -q -h $HOST -p $PORT -U $POSTGRES_USER -d $DATABASE -f ${BASH_SOURCE%/*}../sql/05_grant_user.sql