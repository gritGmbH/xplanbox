---
-- #%L
-- xplan-cli - Kommandozeilenwerkzeuge fuer die xPlanBox
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
CREATE OR REPLACE FUNCTION xplanevaluation.insertPlanLog()
  RETURNS trigger AS
$$
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, newplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING NEW.id, NEW.xp_version, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, newplanstatus, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5, $6)' USING NEW.id, NEW.xp_version, NEW.planstatus, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING OLD.id, OLD.xp_version, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;
