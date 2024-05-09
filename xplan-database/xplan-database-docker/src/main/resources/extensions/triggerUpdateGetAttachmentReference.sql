---
-- #%L
-- xplan-database-docker - SQL Skripte zum Aufsetzen der Datenhaltung.
-- %%
-- Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
DO
$do$
  DECLARE
    u text = 'https://xplanbox.lat-lon.de/xplan-wms';
    i record;
  BEGIN
  FOR i IN SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema like 'xplansyn%' AND table_name  like 'xplan_%' AND (column_name = 'xplan_externereferenz' OR column_name LIKE 'xplan_raster%' OR column_name LIKE 'xplan_ref%')
  LOOP
   EXECUTE
        'CREATE OR REPLACE FUNCTION updateGetAttachmentUrl_' || i.column_name || '()
           RETURNS TRIGGER AS $$
           BEGIN
            If (NEW.' || i.column_name || ' is not null) THEN
              NEW.' || i.column_name || ' := replace( NEW.' || i.column_name || ', ''[/getAttachment?'', concat(''['',''' || u || ''',''/getAttachment?''));
            END IF;
        RETURN NEW;
    END;
  $$ LANGUAGE ''plpgsql'';';
    EXECUTE 
        'CREATE TRIGGER i_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE INSERT ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl_' || i.column_name || '();';
    EXECUTE
        'CREATE TRIGGER u_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE UPDATE ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl_' || i.column_name || '();';
  END LOOP;
  END
$do$;
