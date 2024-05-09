---
-- #%L
-- xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
SELECT xplanevaluation.clone_schema('xplansynpre','xplanevaluationxplansynpre');
SELECT xplanevaluation.clone_schema('xplansyn','xplanevaluationxplansyn');
SELECT xplanevaluation.clone_schema('xplansynarchive','xplanevaluationxplansynarchive');

CREATE TABLE xplanevaluation.planTableLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp
);

COMMENT ON TABLE xplanevaluation.planTableLog IS 'Logs inserted, updated and deleted plans';
