---
-- #%L
-- xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
-- %%
-- Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
CREATE OR REPLACE FUNCTION xplanmgr.updateTransformToolPlanPlanLog()
  RETURNS trigger AS
$$
DECLARE
  fids text[];
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, newplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING NEW.id, NEW.xp_version, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'SELECT ARRAY(SELECT fid FROM xplanmgr.features WHERE plan= $1)' INTO fids USING NEW.id;
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, newplanstatus, oldplanstatus, operation, datum, fids) VALUES($1, $2, $3, $4, $5, $6, $7)' USING NEW.id, NEW.xp_version, NEW.planstatus, OLD.planstatus, TG_OP, now(), fids;
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'SELECT ARRAY(SELECT fid FROM xplanmgr.features WHERE plan= $1)' INTO fids USING OLD.id;
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, oldplanstatus, operation, datum, fids) VALUES($1, $2, $3, $4, $5, $6)' USING OLD.id, OLD.xp_version, OLD.planstatus, TG_OP, now(), fids;
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;
