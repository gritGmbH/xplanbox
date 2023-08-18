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
CREATE TABLE xplanmgr.transformToolPlanTableLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp,
    fids text []
);

COMMENT ON TABLE xplanmgr.transformToolPlanTableLog IS 'Logs inserted, updated and deleted plans for XPlanTransformCLI';
