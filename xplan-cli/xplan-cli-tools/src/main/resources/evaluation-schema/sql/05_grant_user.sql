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
GRANT USAGE ON SCHEMA xplanevaluation,xplanevaluationxplansyn,xplanevaluationxplansynarchive,xplanevaluationxplansynpre TO "$DB_USER";
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA xplanevaluation,xplanevaluationxplansyn,xplanevaluationxplansynarchive,xplanevaluationxplansynpre TO "$DB_USER";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA xplanevaluation,xplanevaluationxplansyn,xplanevaluationxplansynarchive,xplanevaluationxplansynpre TO "$DB_USER";
