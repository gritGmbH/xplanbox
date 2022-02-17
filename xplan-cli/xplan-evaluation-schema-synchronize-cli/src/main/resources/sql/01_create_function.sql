---
-- #%L
-- xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
-- %%
-- Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
-- %%
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
-- #L%
---
CREATE OR REPLACE FUNCTION xplanevaluation.clone_schema(source_schema text, dest_schema text) RETURNS void AS
$BODY$
DECLARE
  tableName text;
BEGIN
    EXECUTE 'CREATE SCHEMA ' || dest_schema ;

    FOR tableName IN
        SELECT TABLE_NAME::text FROM information_schema.TABLES WHERE table_schema = source_schema AND TABLE_NAME LIKE 'xplan_%'
    LOOP
        EXECUTE 'CREATE TABLE ' || dest_schema || '.' || tableName || ' (LIKE ' || source_schema || '.' || tableName || ' INCLUDING CONSTRAINTS INCLUDING INDEXES INCLUDING DEFAULTS)';
    END LOOP;

END;
$BODY$
LANGUAGE plpgsql VOLATILE
