---
-- #%L
-- xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
CREATE TRIGGER insert_xplanmgr_plans_transform_tool AFTER INSERT OR UPDATE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE xplanmgr.updateTransformToolPlanPlanLog();

CREATE TRIGGER delete_xplanmgr_plans_transform_tool BEFORE DELETE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE xplanmgr.updateTransformToolPlanPlanLog();
