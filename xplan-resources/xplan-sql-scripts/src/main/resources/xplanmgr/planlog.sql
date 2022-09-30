---
-- #%L
-- xplan-sql-scripts - SQL Skripte zum Aufsetzen der Datenhaltung.
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
SET search_path TO xplanmgr,public;


CREATE TABLE xplanmgr.plansLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    xp_type text NOT NULL,
    bbox geometry,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp
);

COMMENT ON TABLE xplanmgr.plansLog IS 'Logs inserted, updated and deleted plans';

CREATE OR REPLACE FUNCTION xplanmgr.plansLog()
  RETURNS trigger AS
$$
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanmgr.plansLog (xplanmgrid, xp_version, xp_type, bbox, newplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5, $6, $7)' USING NEW.id, NEW.xp_version, NEW.xp_type, NEW.bbox, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'INSERT INTO xplanmgr.plansLog (xplanmgrid, xp_version, xp_type, bbox, newplanstatus, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5, $6, $7, $8)' USING NEW.id, NEW.xp_version, NEW.xp_type, NEW.bbox, NEW.planstatus, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'INSERT INTO xplanmgr.plansLog (xplanmgrid, xp_version, xp_type, bbox, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5, $6, $7)' USING OLD.id, OLD.xp_version, OLD.xp_type, OLD.bbox, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER xplanmgr_planslog AFTER INSERT OR UPDATE OR DELETE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE xplanmgr.plansLog();
