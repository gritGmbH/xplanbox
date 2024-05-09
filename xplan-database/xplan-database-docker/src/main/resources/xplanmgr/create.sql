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
/* --- BEGIN schema setup --- */

CREATE SCHEMA xplanmgr;
SET search_path TO xplanmgr,public;

/* --- END schema setup --- */

CREATE TABLE plans (
    id serial PRIMARY KEY,
    import_date timestamp NOT NULL,
    xp_version text NOT NULL,
    xp_type text NOT NULL,
    name text,
    nummer text,
    internalid text,
    gkz text,
    has_raster boolean NOT NULL,
    rechtsstand text,
    release_date date,
    sonst_plan_art text,
    planstatus text,
    district text,
    wmsSortDate date,
    gueltigkeitBeginn timestamp,
    gueltigkeitEnde timestamp,
    inspirepublished boolean DEFAULT FALSE,
    bbox geometry
);
COMMENT ON TABLE plans IS 'Imported plans';

CREATE TABLE features (
    plan integer references plans ON DELETE CASCADE,
    fid text NOT NULL,
    num integer NOT NULL
);
COMMENT ON TABLE features IS 'Feature ids for plans';

CREATE TYPE artefacttype AS ENUM ('XPLANGML', 'RASTERBASIS');

CREATE CAST (varchar AS xplanmgr.artefacttype) WITH INOUT AS IMPLICIT;

CREATE TABLE artefacts (
    plan integer references plans ON DELETE CASCADE,
    filename text NOT NULL,
    data bytea NOT NULL,
    num integer NOT NULL,
    mimetype text NOT NULL,
    length bigint NOT NULL default -1,
    artefacttype artefacttype
);
COMMENT ON TABLE artefacts IS 'Plan artefacts';

CREATE TABLE planwerkwmsmetadata (
    plan integer references plans ON DELETE CASCADE,
    title text,
    resourceidentifier text,
    datametadataurl text,
    servicemetadataurl text
);
COMMENT ON TABLE planwerkwmsmetadata IS 'Metadata of plans provided in the capabilities of the PlanwerkWMS';

CREATE TABLE bereiche (
    plan integer references plans ON DELETE CASCADE,
    nummer text NOT NULL,
    name text,
    PRIMARY KEY (plan,nummer)
);
COMMENT ON TABLE bereiche IS 'Plan Bereiche';
