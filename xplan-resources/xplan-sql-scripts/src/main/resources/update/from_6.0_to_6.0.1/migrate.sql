---
-- #%L
-- xplan-sql-scripts - SQL Skripte zum Aufsetzen der Datenhaltung.
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
-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: ./target/classes/6.0.1/changelog_v601.yaml
-- Ran at: 20.03.23, 11:14
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox-target
-- Liquibase version: 4.15.0
-- *********************************************************************

SET SEARCH_PATH TO public, "$user","public";

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM databasechangeloglock;

-- Lock Database
UPDATE databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

-- SET SEARCH_PATH TO public, "$user","public";

-- SET SEARCH_PATH TO public, "$user","public";

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-1::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-1', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1610, '8:b0a50253216e676bb9565f6621f99068', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-2::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-2', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1611, '8:d7836ad9917f5e055a289431407aebd0', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-3::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-3', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1612, '8:b26658fea316bae3a8a06f2a2fd3ec98', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-4::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-4', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1613, '8:c87f04fb127f4f702b7ae137883bda5d', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-5::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-5', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1614, '8:a5f7f0314352e6bea2c03cd8e66b9522', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-6::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-6', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1615, '8:4c924cee4756231e13e4d8b4c8c49826', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-7::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-7', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1616, '8:a9cc8e6094e783ef514290f7d1bc85ac', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-8::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-8', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1617, '8:39f90750d1cf92a0a1f3dd0b433e43c6', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-9::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-9', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1618, '8:42a6f89af9a75a45c6eb6cb9cb1944e5', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-10::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-10', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1619, '8:e0b53425a3a9f5b560014994c689b24c', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-11::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-11', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1620, '8:d1a4d8267f1173a96c452524cfee8d88', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-12::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-12', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1621, '8:8dfd727f79edecea9e3fabbb35043f57', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-13::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-13', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1622, '8:b69937e16f72e047f5019ea540ad52d8', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-14::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-14', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1623, '8:05bdb22962f5bd951830b13d6f6c9344', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-15::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-15', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1624, '8:6c7d960cd44d39e4f9e7ff720dacc5c2', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-16::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-16', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1625, '8:da6590a0dd1ae4d069219f9c12853ec5', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-17::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-17', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1626, '8:4e430a89a1f28fa535d795eacd518a63', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-18::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-18', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1627, '8:28fdf63e5d69630456f9046ab9619f2a', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-19::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-19', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1628, '8:b22a93f9430cd411dbfcee967ea56303', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-20::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-20', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1629, '8:edca8e400ffe883a0f81bd517a6bea60', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-21::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-21', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1630, '8:59ba935d1f35685cf50cd03ef6f53421', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-22::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-22', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1631, '8:07634417945b6cd50d3ec9842d12531f', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-23::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-23', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1632, '8:f314514ea0d5118e56a59d9d7af84591', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-24::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-24', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1633, '8:8a4cabae21dcba416b2a91ea19e2aa3c', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-25::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-25', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1634, '8:4b0e352de96e4d8b7fc9490028bf06f2', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-26::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-26', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1635, '8:fafcddcfaa0d9f2b363f3cf385dc5bc2', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-27::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-27', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1636, '8:a077cf1f7de0b8b55edd37dabb3fb2d1', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-28::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-28', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1637, '8:3831155eb7907940f54d2db420e01d1b', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-29::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-29', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1638, '8:b2d012ba22b4a2f5caad38027cca5b89', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-30::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-30', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1639, '8:94d847c8cfd7740af1f7dfc18770773e', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-31::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-31', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1640, '8:8e7b1efd94a84c9893ebe0799a272bd0', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-32::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-32', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1641, '8:10d3fb627342677d668214dcf3e516ae', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-33::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-33', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1642, '8:c2f4dae4f621bb43d5f663f6da3b4d72', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-34::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-34', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1643, '8:08762ceaeda809ec225ef0daa6b7c104', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-35::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-35', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1644, '8:9faaf6b8e3fd43d0d10d28c68489de98', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-36::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-36', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1645, '8:6cd7526f70eec3bb241e976a5323e179', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-37::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-37', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1646, '8:9b675ce3a665ce49a91aab47c4143316', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-38::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-38', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1647, '8:de5e6c16f25a2f40519123fa59786596', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-39::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-39', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1648, '8:6562e0e00e8c8e1ea00308da1c951772', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-40::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-40', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1649, '8:eabc216bc8d8f501b7fa145aa4462e3a', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-41::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-41', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1650, '8:b2f27fcd339486c3519012f0c7d9a3ba', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-42::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-42', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1651, '8:bbcfca87e57f8ac35d0abd3cf5033f83', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-43::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-43', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1652, '8:3dfd542c546d40bb831ba1448c2fe62f', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-44::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-44', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1653, '8:eefa9c02043b3bb431e875a47c1f571e', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-45::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-45', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1654, '8:5939cc66099c5fff62ed41680ced41c9', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-46::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-46', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1655, '8:388dbf6131e879fefcd2105f5689c97f', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-47::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-47', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1656, '8:8eb87228020ff81c7fb42a5f72cb44a5', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-48::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-48', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1657, '8:a6f3c9c73031cf407a75948dbb3bcfb5', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-49::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-49', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1658, '8:b80f22d5ea2d25f44a8cdf6e8e1a630b', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-50::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-50', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1659, '8:25c080119d5d00dedf6cf775ca4e9139', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-51::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-51', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1660, '8:283b5bd3ed431b654c7a0d2ff2bf85e6', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-52::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-52', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1661, '8:7feea04945a923aac26f816a00e2fff7', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-53::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-53', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1662, '8:51f786da9dc3cf459f29ad5884489752', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-54::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-54', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1663, '8:2d9a27bd58a4284cf724d848ac026770', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-55::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-55', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1664, '8:90a22d6f77d9e2328b7f96ee24d2b56c', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-56::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-56', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1665, '8:c883657937777a074a2b7fa7423dcb8e', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-57::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-57', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1666, '8:66973a95e62fad9992fab938d05c5a6d', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-58::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-58', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1667, '8:9fe3c0e46d7aa2f044c6bc3202d40c57', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-59::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-59', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1668, '8:c7bd1e52ba5152692363f2b0d11653e0', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-60::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-60', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1669, '8:a4614add70b00e0f8ddc6dcb96ee1ccd', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-61::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-61', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1670, '8:0186085f5ee2e5a2ff22a3b97281a646', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-62::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-62', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1671, '8:5497dc5602ae672ba1cab222cc477fe9', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-63::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-63', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1672, '8:d3ff5f93ce7484435eb1e90209f34d50', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-64::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-64', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1673, '8:540d84820876c6504cb9fdc06cb472ab', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-65::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-65', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1674, '8:4c2a84d733363bdda90b5f47e11ce988', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-66::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-66', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1675, '8:8554f37ca4f78fd2651fe67f0461ff8c', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-67::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-67', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1676, '8:2c8ae5caa6e12ed439b08b2bec28351e', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-68::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-68', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1677, '8:903c9950ce7bc89629b918e37d4e46a2', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-69::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-69', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1678, '8:5fb2d233fb04243412cbffa352584325', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-70::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-70', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1679, '8:bd3907b2b30fd09495f734436c6e6546', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-71::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-71', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1680, '8:d995d77e9d12533a041038cf9b7f4409', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-72::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-72', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1681, '8:0e233c249c7e74486656959787705bda', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-73::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-73', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1682, '8:a87dc8749b12dd44f2a977f7aafd6528', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-74::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-74', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1683, '8:bc75be5e4b55df26fe2233342c8f7355', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-75::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-75', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1684, '8:81139fe24da56e54f1d0c900eabfacc1', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-76::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-76', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1685, '8:42c2db5ba1ba9fffa7bc3e27f8072f01', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-77::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-77', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1686, '8:46bd674dc1df46b6b15457a611ce2683', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-78::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-78', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1687, '8:5c3d3458c76494554ebfc5be5e3e6382', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-79::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-79', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1688, '8:510922ff12e536a834c9e96a17cf0a59', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-80::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-80', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1689, '8:d7fac95334c133276fadfab440c2e09a', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-81::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-81', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1690, '8:499983ebc70abd403a50bdbdf19bb8d1', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-82::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-82', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1691, '8:9988bcac947402784e664a64c64ff599', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-83::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-83', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1692, '8:b77749c8f681277ae0f0ee2a8bd3a653', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-84::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-84', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1693, '8:9d483569272e702ed78e979728e26bd9', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-85::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-85', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1694, '8:29482080964fe4c2a058128aab1f94d6', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-86::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-86', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1695, '8:3d3969b359f90d228faf945ef548ff9d', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-87::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-87', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1696, '8:c435b64a0a06250392c393c93f2770e7', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-88::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-88', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1697, '8:8370e3935f4a0e0cbd79ebbd8ef1abc4', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-89::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-89', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1698, '8:fb5263ea3c1aaf28f31be2d79f6658ec', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-90::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-90', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1699, '8:c95d7a9dbb56115dd469d8fcb840d0ef', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-91::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-91', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1700, '8:32f46125d55d6b566178004c5d4f729e', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-92::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-92', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1701, '8:a88c82835f88866130a1e1291fcfef3a', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-93::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-93', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1702, '8:31024e21e505a530d36cc67c832d9e57', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-94::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-94', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1703, '8:e3af66b307124c8317c05b1120ce7b9f', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-95::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-95', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1704, '8:53e6085f9dd121a2c8dbfe921d052643', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-96::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-96', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1705, '8:d5af313ad47227f54d0aa88352109d5d', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-97::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-97', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1706, '8:af97145290a88e09b129875b7fa2aa06', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-98::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-98', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1707, '8:a73222b1fc35336f00d950851fc248d0', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-99::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_generischesobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-99', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1708, '8:8fdcd62afa64f4c50187f5c7076eb29f', 'addColumn tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-100::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-100', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1709, '8:a528125cf62600d9b9c410e33797076d', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-101::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-101', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1710, '8:350d3ac92b3777d9e2720eb6c3a31499', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-102::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-102', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1711, '8:5c9ca28aedd65d2b4b80270c766c6675', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-103::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-103', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1712, '8:a8b8730a445d05a57d3dcb369f43d434', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-104::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-104', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1713, '8:a070119aa2d1d092a7062a29b178fe9c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-105::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-105', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1714, '8:da2855f5b1769798eef70c562e7d56ab', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-106::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-106', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1715, '8:4d8351a3c5c033ba0e1f3abb4783b3e8', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-107::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-107', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1716, '8:b34ad05fea8c433eb499e61c34b3cd9f', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-108::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-108', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1717, '8:ef16ccc06dcbfc1e87963fc9cb77f8a8', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-109::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-109', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1718, '8:b513ee62f2278d5d6da6c88e2ed075a7', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-110::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-110', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1719, '8:6345a251b4169a8bf921bee6c3ea64bc', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-111::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-111', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1720, '8:4d87567116602bd175452767bad87e94', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-112::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-112', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1721, '8:71f9cacc227632a704e7eccf0d0a63e3', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-113::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-113', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1722, '8:fd88b540ecdc2cdd8616eff531e67f3b', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-114::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-114', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1723, '8:635deba5e84814ec44900433a6ea7d43', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-115::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-115', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1724, '8:775fc9f34d56716d10ae658452426be8', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-116::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-116', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1725, '8:e88e6c54d8f776864a3b54949b6c2ece', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-117::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-117', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1726, '8:04f72c37e6d0f593b558e959f5591e91', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-118::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-118', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1727, '8:44c3801d3ceec8e74dd0582252f29ede', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-119::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-119', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1728, '8:3166ad2f3c159de0232e34b9d3e52889', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-120::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-120', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1729, '8:51635548ba1ed52d998a7c200f2e23a3', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-121::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-121', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1730, '8:08aa4714b1bf63bc0bf83643b5610e4e', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-122::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-122', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1731, '8:4af2860993dfa92069c672924874b008', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-123::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-123', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1732, '8:092459fd188b509d95728ebae88037b9', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-124::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-124', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1733, '8:d3a8aa1b14d75927f2658ec8c511d60e', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-125::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-125', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1734, '8:97ed69ed4ed57433bdf2d84fbc9b1f86', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-126::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-126', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1735, '8:e512956157043790706b9b358b405a56', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-127::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-127', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1736, '8:8957288dbf319b7215cea499e5e6f64e', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-128::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-128', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1737, '8:453a4f37026db11d0750e3fe25483571', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-129::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-129', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1738, '8:0be04572d7f572b9026d308bc0103499', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-130::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-130', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1739, '8:383aa91533e44b26abf16d8ca5d49669', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-131::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-131', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1740, '8:a622cd2bba9d5eeb38ff0db8050c430d', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-132::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-132', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1741, '8:6372edd7434b5195109a30271595a3c9', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-133::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-133', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1742, '8:44d4091116959384c3354a29ef00c8ec', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-134::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-134', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1743, '8:e669e2c5aa3c2bcc1509eea8407ab795', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-135::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-135', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1744, '8:f7a678999bd832759992b71c2b5675db', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-136::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-136', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1745, '8:dcd7b27ff2a6fe9cc63f41abfb145b69', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-137::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-137', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1746, '8:fd1926f0f5fdd9303599e542ca7cc5ed', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-138::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-138', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1747, '8:72becd667cd2efacb090e8c360d47b59', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-139::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-139', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1748, '8:6e326adf48cff07fd6dd49e9e0589839', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-140::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-140', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1749, '8:ccd022c6c2a01551a48fcca8c321de20', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-141::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-141', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1750, '8:28c209595eeb3211753288db20b3d69e', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-142::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-142', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1751, '8:02b2919b7435a21afbfa4f474786213f', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-143::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-143', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1752, '8:e27951a0fc1ca4484881c34723afa9de', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-144::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-144', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1753, '8:da9eea2057ff89b37fc17e86341c2a2e', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-145::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-145', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1754, '8:0f7463d3d42dcfce1510f6ee13f1f470', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-146::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-146', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1755, '8:0f02f72370d2d91897ea5c77b7e5f19a', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-147::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-147', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1756, '8:f3c608b257df2b384a524ab6dee9318a', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-148::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-148', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1757, '8:2b76d3f1531aadd335f916a10ca704c5', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-149::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-149', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1758, '8:fb2622e980cab95b2189c6b14747f51b', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-150::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-150', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1759, '8:50130ca7c49ec4a793a7afc9e9437f8c', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-151::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-151', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1760, '8:678b0a2cc9f80eace57ca06fd10ed3bc', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-152::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-152', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1761, '8:f3a62f8eaa35343de46bc1e2276a1d70', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-153::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-153', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1762, '8:40879d98c410e014c0db94e37a6be4fd', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-154::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-154', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1763, '8:048999c6c44a1e37d6911a445f06abb9', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-155::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-155', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1764, '8:74dce04e72cdf1a873cfd801cf75f52f', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-156::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-156', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1765, '8:22175e85fb394b83c053349f87016b0c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-157::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-157', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1766, '8:42d4b567bba2589dee29ccf6df6a6ebe', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-158::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-158', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1767, '8:711c34db9778cb296e1cf601782f491e', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-159::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-159', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1768, '8:95d8aa5ed5eba0f8acc4181f6f3987c8', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-160::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-160', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1769, '8:cc4e375cd9f1ab828914f44e190343aa', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-161::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-161', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1770, '8:2e8533d644f6f83d9d9ab97687b6607f', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-162::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-162', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1771, '8:06c047fc3106da34340259462dd36a4b', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-163::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-163', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1772, '8:5609f6f73c15892ecc7113cc28ce2070', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-164::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-164', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1773, '8:d71980276a24b243458611e8c2859f8e', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-165::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-165', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1774, '8:e936b512160256bc1340909d3247e7d7', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-166::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-166', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1775, '8:e9dea1595d716794d1a30bd1199bb8aa', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-167::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-167', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1776, '8:d1315e56a3b9781df9c441a0a991f0ac', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-168::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-168', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1777, '8:46ca09778a97d50979aff36c6d48942e', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-169::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-169', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1778, '8:0048b992a5af24c1b03dbb8fa5bf02e0', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-170::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-170', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1779, '8:39a63e2c67660c58b7ae174f492f45ce', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-171::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-171', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1780, '8:9744bda718dd924fc69bb33654b035b9', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-172::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-172', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1781, '8:f9d9ff87470c7a0fdfc101d31734c136', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-173::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-173', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1782, '8:9fba88f083b0b8535ac345943cefbc6e', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-174::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-174', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1783, '8:1cbee08faffbc94f63357f21e900b50e', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-175::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-175', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1784, '8:bca0484bc46c60c28a8f45a11d53314f', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-176::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-176', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1785, '8:41b4fbddb846780ee85b242eea34db00', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-177::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-177', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1786, '8:104a8ca64b87ca1ba3e085c4469ba192', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-178::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-178', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1787, '8:b7a0edd48efdc41ddcad1ccc7ca27e6d', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-179::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-179', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1788, '8:7a6468c860304efbb83808860113781e', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-180::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-180', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1789, '8:6bcc43769b1b2cc56ea607ba585bce08', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-181::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-181', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1790, '8:1bdd3b907d13cd5d5c17306fde37d228', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-182::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-182', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1791, '8:c65a649d21e27f2bfbac0f511d23e1f7', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-183::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-183', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1792, '8:1d4f977c70e600a9bc224378112fdebc', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-184::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-184', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1793, '8:78412ffd436b073437ebf45e18687242', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-185::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-185', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1794, '8:721c53e586a3feb21d34829595add8ac', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-186::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-186', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1795, '8:20e5ced0a1e819ca162c8881057a0862', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-187::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-187', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1796, '8:3b20f8971b4203824dc0dffa6670af84', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-188::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-188', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1797, '8:bfba3d0c83093939f69772ee987f35c0', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-189::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-189', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1798, '8:ebe99fbf1fdf0bbf7b909d8e7b25b6d1', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-190::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-190', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1799, '8:c616e5c172e1cfce5fe6750d3a278f2c', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-191::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-191', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1800, '8:c64a594acf49b3ebd9cdda24a58f77ff', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-192::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-192', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1801, '8:8aeab907c746689addbaedc004bb42af', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-193::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-193', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1802, '8:6781684d86787120eb3d0e3b47b0a568', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-194::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-194', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1803, '8:649e3365f9b1dc480206250acaeb55f9', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-195::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_biotopverbundbiotopvernetzung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-195', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1804, '8:4eafec31e1d78ea1bc1a42eb3397af4f', 'addColumn tableName=xplan_lp_biotopverbundbiotopvernetzung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-196::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_eingriffsregelung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-196', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1805, '8:c1f99e53281acc5bb010ffb64de4b4a3', 'addColumn tableName=xplan_lp_eingriffsregelung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-197::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_schutzbestimmterteilevonnaturundlandschaft ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-197', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1806, '8:b1a5f927ee0fdf27e43fad151d9f4d1e', 'addColumn tableName=xplan_lp_schutzbestimmterteilevonnaturundlandschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-198::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_textabschnittobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-198', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1807, '8:8fecc88ec53b9a18ab6604b55901386b', 'addColumn tableName=xplan_lp_textabschnittobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-199::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_lp_zieleerfordernissemassnahmen ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-199', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1808, '8:2812f7c234368a92a18bd8f57dc9c582', 'addColumn tableName=xplan_lp_zieleerfordernissemassnahmen', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-200::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-200', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1809, '8:875facad2987d541d050d40a1e63eaf2', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-201::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-201', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1810, '8:fc7963318c9ed625b1b60dd806e63989', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-202::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-202', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1811, '8:22c3dfdfd95c5b3146bc507e2f7de7c4', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-203::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-203', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1812, '8:c438facd33439db8432f7ba0b110d11c', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-204::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gelaendemorphologie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-204', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1813, '8:7f7bd76165e62d5f3c10775dc3e09b1a', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-205::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_gewaesser ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-205', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1814, '8:a9b2198f139bffea6eb22e5d7c4aa4b7', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-206::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-206', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1815, '8:b70dfac0a632964cf13e776d0f5da8e2', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-207::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-207', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1816, '8:6392d482d04877e5c8108c4fd263685c', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-208::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-208', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1817, '8:0e4bf1b453874c93c47f3cdd425a7ffd', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-209::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-209', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1818, '8:204895c1a9fb27b945008bbfa2a28114', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-210::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-210', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1819, '8:55e3ec6cc74c7c2682c1025c50957d4f', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-211::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-211', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1820, '8:b71da43c4c116a9b73dda4b621d1fd95', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-212::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-212', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1821, '8:78586171bd728974bc9c9ab639640517', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-213::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-213', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1822, '8:3b03af387ddd4a4622b6e7547de64146', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-214::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-214', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1823, '8:97558b6a0b1632dcd315e3a6e07fd385', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-215::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-215', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1824, '8:06e0221ebb79af7f04854f64a79a237a', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-216::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-216', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1825, '8:69d33b7ea80facf770a8ce2e02f8caaa', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-217::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-217', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1826, '8:b864ceccba4000038802572a80aecb01', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-218::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-218', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1827, '8:510b0456863cd6d781938ee07f6ff5ce', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-219::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-219', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1828, '8:8b564afd678cb055ed1b32dd40101dbf', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-220::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-220', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1829, '8:3e33b1f80c0ac7517a1107415ff12d9d', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-221::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-221', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1830, '8:394d531c132d7d1da606588fd553f34a', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-222::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-222', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1831, '8:b4cfc4984781ae74ae9e2b1483959afb', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-223::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-223', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1832, '8:88458812da749b2b538b1059406d9ad1', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-224::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-224', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1833, '8:872ba9f9758e4b1d83d5ea798d7d7670', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-225::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-225', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1834, '8:92286056824b5ef1fb03c46e415f4835', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-226::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-226', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1835, '8:187b936a92ebdf45f00ad0a01a2eec22', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-227::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-227', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1836, '8:e832a6bb7006ba4dadd288a337f53625', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-228::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-228', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1837, '8:36e3036a55309c151424dcdb860d74fa', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-229::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-229', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1838, '8:d153e595f3d6f7f4f1f0dad790a19e2c', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-230::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-230', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1839, '8:ceae44822706983e049f2de267c3083f', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-231::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-231', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1840, '8:427b19d86e02b35830fe6f854e66b236', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-232::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-232', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1841, '8:67ae179e7b14eecb44029344fefca266', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-233::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-233', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1842, '8:582ba2ac02412ba41379134fccb6d4bc', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-234::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-234', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1843, '8:2738a6c1ffe95881f711ecbd9c64bd99', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-235::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-235', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1844, '8:ecb02ddd846d7488c17cf63658b90a3f', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-236::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-236', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1845, '8:a6beb6eb8bbfc24be48475c66717d575', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-237::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-237', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1846, '8:384e7a6af84c708202d848acaf627930', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-238::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-238', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1847, '8:ad44d9ac09e410ba0c7a717489a4b355', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-239::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-239', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1848, '8:93ad115abf061f533eea7a2fd7f58e6d', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-240::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-240', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1849, '8:6c06fc1f3965712dc6fac6f89083037d', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-241::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-241', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1850, '8:ef4d9f1f2da148be2c49d39c03a0cf5b', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-242::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-242', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1851, '8:21ac9cf5a2432902bcc93a8238859d0e', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-243::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-243', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1852, '8:8e8a206330701f3585f07592635da287', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-244::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-244', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1853, '8:0e46c3079d6d4a3c6227f1199d8ac8f6', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-245::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-245', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1854, '8:86c671e04bf418a0117182381efba19d', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-246::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-246', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1855, '8:5cca142eebeec6e429f3c259218fd841', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-247::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-247', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1856, '8:a70236462746db1976df39ea430b31bc', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-248::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-248', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1857, '8:758e0609356c05c2c6ac95074b663e8e', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-249::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_baubeschraenkung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-249', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1858, '8:6b652ad348dc5e129bc44765daea65cc', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-250::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_sichtflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-250', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1859, '8:129dc418363ba14a9a44a63a4a4b57cd', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-251::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_strassenverkehr ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-251', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1860, '8:bec67a23c2aff24f7a58ebdddfa186a6', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-252::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_textabschnittflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-252', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1861, '8:45a97cd38acea5a49b43b5c4a9081384', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansyn.yaml::1679305287979-253::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansyn.xplan_so_wasserwirtschaft ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305287979-253', 'lyn (generated)', '6.0.1/changelog_xplansyn.yaml', NOW(), 1862, '8:ac56047d96de0698e413804f701fa081', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-1::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-1', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1863, '8:ce36d4f02d8b2a932c5ec2adaaa226ac', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-2::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-2', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1864, '8:6d9959ddf52d55cf107d008be556fa81', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-3::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-3', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1865, '8:0db6dd65e4d2324ac5230560a39bfd0b', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-4::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-4', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1866, '8:b8af4893ef42d2c36503f2da643d2028', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-5::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-5', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1867, '8:0f9ded1235826816be1a429f821cb865', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-6::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-6', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1868, '8:15806067f7713cd0f12d12dd7fa0ff7d', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-7::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-7', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1869, '8:fa87181d7ab7ebc5a54b0c60997fb0b2', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-8::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-8', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1870, '8:6928727d551b8fc3661f37a6f9174ded', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-9::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-9', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1871, '8:c2f4d52435edb4ae34ccae8243949b9f', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-10::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-10', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1872, '8:c37f56da824507873e08b65cd393f566', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-11::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-11', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1873, '8:4ae0c21a830766e8d79ec53ff5db2fa8', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-12::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-12', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1874, '8:0153461e5b66b7c2aece68edc1df70d2', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-13::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-13', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1875, '8:9af81a5cdecc8286a024c66e27ab9bed', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-14::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-14', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1876, '8:a51247cbd2f232336bd4417759707147', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-15::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-15', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1877, '8:27df78de21280a25f33e391f325e1103', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-16::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-16', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1878, '8:1f9f8cdd5c8bdd235a8235ae15459f28', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-17::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-17', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1879, '8:0ffe4bd542e87e2b0c7563eed94e2064', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-18::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-18', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1880, '8:1433ca2deebcab6df55251a08402e2cd', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-19::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-19', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1881, '8:005e9857cd2f99d54d6633c7493728a1', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-20::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-20', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1882, '8:ec7e34a8dc9cd9bcd3d1ff01100b3d86', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-21::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-21', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1883, '8:6447ba7b4979887a9ed386f65841100d', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-22::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-22', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1884, '8:13613a1447db60d3f2db4f5d3e66de7b', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-23::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-23', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1885, '8:37e548bdad354a766850f27352f33341', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-24::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-24', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1886, '8:97d15a594fde9e679524d05aefcd0469', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-25::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-25', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1887, '8:e43c03a775e7ecf3dd41f06ca37450ad', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-26::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-26', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1888, '8:ab08aae5970fb3f28e465d85f92d4853', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-27::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-27', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1889, '8:aa33cff7edf9818676040a0c08b15c60', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-28::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-28', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1890, '8:42ceb5b6c750d1e4abbcc5c45acdd082', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-29::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-29', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1891, '8:7d19afab64857de5d5360e99c8106c46', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-30::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-30', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1892, '8:05e192316ec68e93e11b5f0aa8280eb2', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-31::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-31', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1893, '8:bef131a97ffcdc4d403be218a0fadf0f', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-32::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-32', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1894, '8:faa96fc16301e32cc2fee0daee357207', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-33::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-33', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1895, '8:007e2b1826735452d7f5028159d86868', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-34::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-34', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1896, '8:58eff5f1c72eb05a027192c0d06bc066', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-35::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-35', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1897, '8:df530d88abba2b1b8d5dc318c71c081c', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-36::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-36', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1898, '8:97599f678e6a050d46c9751441bcc61e', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-37::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-37', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1899, '8:7edb9dab2a38a05bd5a53419af8a7013', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-38::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-38', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1900, '8:4181cc9f07432952db6a2da0a033297d', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-39::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-39', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1901, '8:1d48cf67e56dfed6dabf7fe2e5036a5a', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-40::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-40', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1902, '8:7a36b3d9e73e5f5ef4005aa773cad2d7', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-41::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-41', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1903, '8:c6eb27f3b8b9a0f935edac0caf9973ae', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-42::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-42', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1904, '8:07c07603a3fdaebd0a374ded3afb48b3', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-43::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-43', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1905, '8:ad2dda6af53728f116b7510eca0775ab', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-44::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-44', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1906, '8:f2fb4f0f15aa405cf441e05242fc1958', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-45::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-45', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1907, '8:81d3a9330198470bc56aa56d10d4dcaa', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-46::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-46', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1908, '8:5c6f026bd98ad38e60e2bfcc437a4ae8', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-47::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-47', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1909, '8:966792a46237db7ec2097d79d2cf5058', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-48::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-48', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1910, '8:17ea86cc0eb93f0aa6df1ed832d906b8', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-49::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-49', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1911, '8:0f100f66cd9ef73e195b2475ecdd04ab', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-50::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-50', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1912, '8:81350bdee5e95a1778a84c5a5f72d531', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-51::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-51', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1913, '8:2420ca119f8b28c21f9c458f61c55edf', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-52::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-52', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1914, '8:3bdca9bbfb6260e5858cbda2c736a40e', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-53::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-53', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1915, '8:467630144b587fe5db98a620654c8118', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-54::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-54', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1916, '8:b3854580684418680a3ceac536d6fc91', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-55::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-55', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1917, '8:037e72686f89de88a0e99629ebba2857', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-56::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-56', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1918, '8:8ace1263a264ca16c911e6bed7d4722e', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-57::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-57', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1919, '8:665d635fc5d41995d462e0e2a3501b19', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-58::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-58', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1920, '8:221b81f4acb70ec88c0352fdb99a96b8', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-59::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-59', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1921, '8:c243cc7bdff19ae9efe619454f774689', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-60::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-60', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1922, '8:6f011bc4c1befe6cb818119c77f04f04', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-61::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-61', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1923, '8:e2a5f69c71f086a047f4c6d9729d5b3f', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-62::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-62', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1924, '8:263bf85a91760ca874a44b3e18d2b383', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-63::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-63', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1925, '8:43107195867b22b33604136c13f1399a', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-64::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-64', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1926, '8:89c76f52a9bf44995e25cc870fcba83f', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-65::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-65', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1927, '8:92f92c4eccf0d609a8650ee9fa7292b4', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-66::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-66', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1928, '8:4e0f1a23398ad5bf8f4a6fa48962adb4', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-67::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-67', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1929, '8:df737fb2378c97157d7e2be4a6c8ff30', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-68::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-68', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1930, '8:2f63810a72a5c652aac6319271ca677b', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-69::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-69', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1931, '8:ae5a73ef5ee98f601c0a35c0f1a68da8', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-70::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-70', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1932, '8:8d144e5a508fa2536ca518d7fe636111', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-71::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-71', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1933, '8:93adc120db9350c2baac9a5e9e939518', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-72::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-72', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1934, '8:ee88b00c3ae1e22b71ca2bb6c2f7f818', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-73::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-73', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1935, '8:84b43b73c6781f2b64b90d2fd03a5021', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-74::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-74', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1936, '8:a7d454430dcd8ccc799113ae9a7ce185', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-75::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-75', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1937, '8:2db030b384247c136e0b970c177675f3', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-76::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-76', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1938, '8:e95648e520e9ae73d2a3de0bb93e3e58', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-77::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-77', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1939, '8:105a93a818ca94336e921f78ded807d6', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-78::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-78', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1940, '8:2dde25587f5598864d8923ba47db54fa', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-79::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-79', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1941, '8:8b5594736e1fbb0a1a0a6872f63352b3', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-80::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-80', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1942, '8:58ffbca4f7d1f99689717959f64c1d6a', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-81::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-81', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1943, '8:415e32284186e0f182f540202d595a77', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-82::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-82', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1944, '8:28a3742af21bc21d689e2c37c655d871', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-83::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-83', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1945, '8:17b77241400202cf8c0e79eb0df10b7e', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-84::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-84', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1946, '8:b85fe2f5e3cd523d1693bef8cf0e5847', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-85::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-85', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1947, '8:02ee4e7be016605f131cf77995343838', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-86::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-86', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1948, '8:36354cf8d9ab58155151deea52c14dfb', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-87::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-87', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1949, '8:e7b809c65a3bbb9cc3c3113ddd7483cb', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-88::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-88', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1950, '8:a31b2ababd4c7f85c960572f182c589c', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-89::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-89', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1951, '8:0659141e1da6b3fbe6a1999bfed4b46a', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-90::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-90', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1952, '8:3ae1ddf37df1b4a7f6de4189d9363dcf', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-91::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-91', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1953, '8:0837098ff272a053d55e226df606966f', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-92::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-92', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1954, '8:8fe873f2dde806a5b49d0bffd5ad1256', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-93::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-93', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1955, '8:bb391a5e0889e6f292ac704f8edf382a', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-94::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-94', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1956, '8:ff289ce71ed78ae76ce5165ae8b09775', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-95::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-95', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1957, '8:5580401a014dc142244f88852b0e1f47', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-96::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-96', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1958, '8:c2b8c132c73101c61d9166b75544a0a2', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-97::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-97', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1959, '8:71f674ee4d9cab226a988df5b1ae2e59', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-98::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-98', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1960, '8:22f0c4480ae9824b495976cf2b06b213', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-99::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_generischesobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-99', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1961, '8:e3104e7958ed63b1e9a10534f3e629c7', 'addColumn tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-100::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-100', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1962, '8:de51a3be54f457b77cfde0de5d6bc412', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-101::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-101', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1963, '8:0cdffb4adc211be4ae174f80edd98940', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-102::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-102', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1964, '8:aa431316a51cf3c04203bfc189a95a55', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-103::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-103', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1965, '8:60ed46460523d5cd0dc06d0cb1966629', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-104::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-104', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1966, '8:9ea456915d04ef8c986cefc1b827002d', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-105::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-105', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1967, '8:cd6ba59d4ee1d9e3e8af821b5f43cefc', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-106::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-106', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1968, '8:94ec086164d28ad5d82795929072994c', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-107::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-107', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1969, '8:5d928e4f554fd318e6e917d178644ac6', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-108::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-108', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1970, '8:a939dcc3550e035cc8f2fadd73763924', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-109::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-109', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1971, '8:b49dd796560347b15acb6491adc17458', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-110::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-110', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1972, '8:fb3f16c742322cccfc15e50835d73153', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-111::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-111', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1973, '8:58b70f23062d7da495a3a7c58e4fbd6e', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-112::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-112', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1974, '8:9644b08123d5e7a2864ceb372fab9b9b', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-113::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-113', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1975, '8:a7da9684d03a723146cd9b7c6ba06af3', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-114::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-114', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1976, '8:d1bd3617f24425ec1f9681b5deaa3def', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-115::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-115', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1977, '8:ac1e2a01a151d8e8d97396bccad55434', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-116::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-116', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1978, '8:2a323819b28809194a4c0bedb7bdf709', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-117::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-117', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1979, '8:757a70f0026fca7fccc36fcbe01ea4a2', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-118::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-118', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1980, '8:d30399d18a3fd802c25a76deb205080d', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-119::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-119', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1981, '8:869933865a2aa467f1d6dbbe107ead1c', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-120::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-120', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1982, '8:c29980b17757833d49878d269bdc5a76', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-121::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-121', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1983, '8:7c482adaf718956f5fe5fc87f06052c6', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-122::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-122', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1984, '8:c3c3820aafe2f572e15e7d812bd846e3', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-123::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-123', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1985, '8:33b4f1a01190d87df0a817cff25be541', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-124::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-124', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1986, '8:adec28e97ad279be44c084c97dd51ae5', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-125::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-125', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1987, '8:9807b1842d02125e185b22c2eba0ddcf', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-126::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-126', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1988, '8:0cd94df7025d69e6fa6a3efc0e1c5ca2', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-127::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-127', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1989, '8:fee6d46a9705cce98b3ce4c94b766eb0', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-128::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-128', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1990, '8:6ddedc898649bf02aaa1939cd686b94d', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-129::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-129', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1991, '8:2266b8910708f7786fbf46be9a1a9eb8', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-130::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-130', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1992, '8:a955a441ae3da1324f5c75a7b4c01356', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-131::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-131', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1993, '8:3af2e6d39346ee2f0279a714432a0c7b', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-132::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-132', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1994, '8:1c9b65fdbe48eca97e28946c77885f22', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-133::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-133', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1995, '8:fe4f50dc156df9723a6546a37aa3da97', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-134::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-134', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1996, '8:3694fafd75de717f6194fa12a02e64e3', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-135::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-135', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1997, '8:95c9ae82f8dd8c47152d8aa4bc00d622', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-136::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-136', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1998, '8:bdd72b86cdf231cc957ed87c40bd6469', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-137::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-137', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 1999, '8:c42c309850062dcd1ee36c0dd2d6ed6c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-138::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-138', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2000, '8:312f454effb652a8e511c6fe697a2d78', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-139::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-139', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2001, '8:97590a29e7beb154ef3ec56211092f6b', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-140::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-140', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2002, '8:b56f9ebe269269e639f8144023d63005', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-141::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-141', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2003, '8:3cd0c9a4b3e287d2990acfb762640331', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-142::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-142', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2004, '8:815a9fbe9c4ede522daf3ba0fe4a6a6f', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-143::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-143', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2005, '8:9aa736313c325497c7dfb1022a4f6ec9', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-144::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-144', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2006, '8:a5b7471a1dfe0362f31f8d529d05f6e8', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-145::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-145', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2007, '8:3bfdbebfc11a8a85f9a5445e8ca4eed9', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-146::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-146', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2008, '8:54e21549608ae383eab32220cca55ae9', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-147::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-147', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2009, '8:846bce494e8ffb01c3a224adb47b448e', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-148::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-148', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2010, '8:8364254b3f9abddf7eecef887356878a', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-149::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-149', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2011, '8:179efd9c2bc22bd3eda2303e22188a52', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-150::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-150', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2012, '8:54c20f643e9c733ce988845ceb5892df', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-151::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-151', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2013, '8:1dbb2fb3381546216e37d39ab8f71c08', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-152::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-152', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2014, '8:b66cdd0008a7044bd54e3e1bee9fdb35', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-153::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-153', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2015, '8:91c5ad62c64ef9c2319895e0edb416f2', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-154::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-154', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2016, '8:15e75804a76f8ff05813b5632ee94846', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-155::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-155', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2017, '8:6bddc6a9c2004a836ead200ea18b619d', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-156::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-156', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2018, '8:59ebaa4b2e30392c165c68eb99cc3fe7', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-157::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-157', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2019, '8:f62679def73492f90c493033bffb2d88', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-158::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-158', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2020, '8:fd5e8f66c74c1d9b99e6eba1cb98c7e8', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-159::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-159', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2021, '8:c472a69ec88379ff60065430d88a4c63', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-160::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-160', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2022, '8:2036d3e06fbbde609271fa2792fc3458', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-161::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-161', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2023, '8:dbaa5ef5d4a6d40bc642e920669fd287', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-162::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-162', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2024, '8:6a393eba09171b6a9af8a4b0450a3bdf', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-163::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-163', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2025, '8:ea42137869c7f65fe7aaff85c7578246', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-164::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-164', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2026, '8:a30f377029d682a7917401a68950e993', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-165::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-165', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2027, '8:1050e4e441d630bd7217cd19b0646bdf', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-166::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-166', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2028, '8:0d1818bf7afe5e0d32ebca9289204f1b', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-167::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-167', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2029, '8:3edcbdf59bc26bb814dd1885893e8908', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-168::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-168', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2030, '8:92978bea6b2096f0b7379a9b1c7f571f', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-169::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-169', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2031, '8:3b925fc56dcba3fdcb5da7b05d8f989a', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-170::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-170', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2032, '8:1e71742ec6d09052b513e49680bf3a3b', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-171::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-171', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2033, '8:43f82fc492466286379d4ac55998d6a8', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-172::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-172', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2034, '8:deb20e6f32b87a09d26a5f640972a642', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-173::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-173', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2035, '8:d6687c852911c4f069078e47b6b04f88', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-174::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-174', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2036, '8:a156c377f853aabf16f4bc5aa40cc6d7', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-175::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-175', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2037, '8:ecae9faea195899b394bef4c8de2818b', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-176::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-176', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2038, '8:e05e47d1cf8593412ebed19d84d8afd9', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-177::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-177', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2039, '8:7b037232072dc8d542391e2da3f45c9f', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-178::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-178', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2040, '8:855a4f0e8b924ff4150bdd767b6cca4c', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-179::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-179', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2041, '8:2d6d1e35787dcc7279230bab2f717bf8', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-180::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-180', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2042, '8:0a45fb65c5280f94c9cfa2cbe704df73', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-181::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-181', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2043, '8:bb549a4834f12b21b5d55b65a7582b7c', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-182::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-182', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2044, '8:8922b240513fbe865f0099215a130752', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-183::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-183', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2045, '8:c6ef10d12061b2e13add71dfbc94873f', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-184::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-184', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2046, '8:e3023dc5c564ce21923777b7ea2af745', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-185::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-185', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2047, '8:55ba5e4b371808afdae83590c17f05f2', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-186::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-186', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2048, '8:100378ffafa0fc67348ec43518b15e47', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-187::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-187', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2049, '8:ba0962e931f991066468ede73db03671', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-188::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-188', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2050, '8:e1963937618eff2e698eb54f8c3bc286', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-189::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-189', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2051, '8:051f0accb404ef89281dc9d46afdf6a5', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-190::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-190', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2052, '8:dcf186a14fab1bbcb75e2e85f96f905e', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-191::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-191', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2053, '8:53c9be6a111cb45bc07a226a3c4c07f5', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-192::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-192', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2054, '8:e368bacae6b587b30abda5446e9c0e2c', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-193::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-193', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2055, '8:1620bafe50389fd737d0fe5bebace02e', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-194::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-194', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2056, '8:c6a3c40524a95aacd85e8bbbf63217a1', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-195::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_biotopverbundbiotopvernetzung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-195', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2057, '8:0ed552857bc9ac873e1c38d3291406d1', 'addColumn tableName=xplan_lp_biotopverbundbiotopvernetzung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-196::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_eingriffsregelung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-196', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2058, '8:60ad1a2647941d1588266283349dc55e', 'addColumn tableName=xplan_lp_eingriffsregelung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-197::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_schutzbestimmterteilevonnaturundlandschaft ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-197', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2059, '8:94644e8b0d189fff51387741d5852e7f', 'addColumn tableName=xplan_lp_schutzbestimmterteilevonnaturundlandschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-198::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_textabschnittobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-198', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2060, '8:9742fa000d27cd928feb9f0b78ca3ecb', 'addColumn tableName=xplan_lp_textabschnittobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-199::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_lp_zieleerfordernissemassnahmen ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-199', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2061, '8:2cbbef4d0b515696416d48fc4ab0523e', 'addColumn tableName=xplan_lp_zieleerfordernissemassnahmen', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-200::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_bodenschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-200', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2062, '8:43dec0fac878125b61f049fab59b580a', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-201::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_denkmalschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-201', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2063, '8:b13d3b0e61685b92394708fceb19bef9', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-202::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-202', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2064, '8:f2b48b22a1f03daf543c3f9e8bf4eea5', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-203::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gebiet ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-203', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2065, '8:810163f89d37f6a535255575949294d6', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-204::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gelaendemorphologie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-204', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2066, '8:3ddec0cc29d1ae1b923298b4ac550d52', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-205::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_gewaesser ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-205', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2067, '8:66a0bc43ab9440b0c88400fd4843895d', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-206::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_grenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-206', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2068, '8:0c697c3f617dce93f55a077c237b7ad6', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-207::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_linienobjekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-207', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2069, '8:7c24ee317a0a502f836f7a53d57f529e', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-208::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_luftverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-208', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2070, '8:ea67dbfe795f59a1032d1c0edc73f7e0', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-209::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_objekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-209', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2071, '8:14e4874ad6adffef702d075b07bafd54', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-210::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schienenverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-210', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2072, '8:abc0ff520fcfc3e654118959e3f757b4', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-211::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-211', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2073, '8:bdacf183624aae378a03e62a931aef72', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-212::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sonstigesrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-212', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2074, '8:33778e45564e1c876dd9e77d5f017872', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-213::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-213', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2075, '8:81fbdffaa544c69a19de275ddcb1b54d', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-214::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-214', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2076, '8:fa32e985bc147f65ed61ff30fdd971e2', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-215::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-215', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2077, '8:048e1d06a520929c60fe4fdada56be9d', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-216::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-216', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2078, '8:95eb9ac4ca822b877281a2717f9ee82d', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-217::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-217', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2079, '8:f8814614b34fdd4fb65ac0fbaeb9262c', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-218::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-218', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2080, '8:9d8a67cd49ebc074a74926b08b5578c2', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-219::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-219', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2081, '8:afdf97f5b1f96d314bb2e43b2c376a2b', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-220::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-220', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2082, '8:9b06abd0dc06a88ad5b9554434ae11fe', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-221::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-221', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2083, '8:64cbe9a7f8dec2ebb606bf0075a83948', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-222::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-222', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2084, '8:176859adb482b1a985cf1ce85e191ddf', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-223::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-223', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2085, '8:38dfd8f1abfdde897b8d166f65048654', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-224::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-224', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2086, '8:be6d95230e730bf25d00092bf78728bf', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-225::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-225', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2087, '8:3f50fac86a8241ef695c5c57b6aa05ed', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-226::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-226', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2088, '8:da1f934d99e85703a8ebf942cafe7a43', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-227::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-227', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2089, '8:72d0641966d62034390b1b0ded7a17d1', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-228::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-228', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2090, '8:929fabcd676d0cb4241a40f2e454dab5', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-229::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-229', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2091, '8:565c52dcba30f74c77ed8f26af5a3754', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-230::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-230', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2092, '8:ee244cbe2c1450e5056846196220ca25', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-231::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-231', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2093, '8:7d98555c6e362084b454999ebc24bbf7', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-232::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-232', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2094, '8:2761f09597c7b7f9c672f1ba5abd1804', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-233::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-233', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2095, '8:2906a61f47f1db5e304908e70f1344ff', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-234::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-234', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2096, '8:fdc58d4270f7aa52255ba35d14c7fa3d', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-235::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-235', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2097, '8:3bb27c8ad59f9cea099f99928adffa0d', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-236::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-236', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2098, '8:acfcb40e6b55b66c57c402835c3c58a9', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-237::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-237', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2099, '8:2d8ff6c9da9dc82e1c26fb5141e629d2', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-238::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-238', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2100, '8:8ea3a8bb2771650fa35c4152e0e46c7c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-239::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-239', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2101, '8:00ffc3461d2182e64174c531ccec6a8b', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-240::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-240', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2102, '8:251ca34d2864f4b514f509619c5574fe', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-241::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-241', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2103, '8:085927e46abe9d57eaa1c0e513161479', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-242::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-242', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2104, '8:19cda25654f0f6b4b40d5a7fef34b5cc', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-243::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-243', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2105, '8:5ef3c5d1e08965fd6d2df401538b932e', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-244::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-244', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2106, '8:46152932901f3069be46d72ec767f043', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-245::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-245', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2107, '8:558f37496e276aed11963d41d5fbafa6', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-246::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-246', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2108, '8:fcaf4053be1b7550ff8af5dfb5617925', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-247::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-247', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2109, '8:b8b792a402c3974da7e0eb9b9afabc48', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-248::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-248', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2110, '8:c5e0aa80faf6ec69fec6aab136ecc5ca', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-249::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_baubeschraenkung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-249', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2111, '8:44b26ddf4d8367e4db5a5eb3a0041d4e', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-250::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_sichtflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-250', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2112, '8:95660a7e0ed2b185f61673f379a27fa0', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-251::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_strassenverkehr ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-251', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2113, '8:ead084931c8cad276d1dc48e323dea92', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-252::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_textabschnittflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-252', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2114, '8:8e9304e3aa16a93fa3f2004cce367759', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynarchive.yaml::1679305315645-253::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynarchive.xplan_so_wasserwirtschaft ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305315645-253', 'lyn (generated)', '6.0.1/changelog_xplansynarchive.yaml', NOW(), 2115, '8:0968318868b343140b6420f603da65a6', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-1::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-1', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2116, '8:cebf9178a7bde5e323e50b04596bfc3c', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-2::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-2', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2117, '8:f5fc650db7a2e959cb8c3f34f78b7942', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-3::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-3', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2118, '8:ed18601eddb96ecbd25319d442da5699', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-4::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-4', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2119, '8:8e3cb201a5c948f15d595a61652ba844', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-5::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-5', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2120, '8:e42d737cc39908390d7eb785c72f9b01', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-6::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-6', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2121, '8:bb4479b9a403a530329f726032e96575', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-7::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-7', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2122, '8:889858632cec605745a00ec6640b0c9d', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-8::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-8', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2123, '8:9f13937723e781478a315f5295e8eec3', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-9::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-9', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2124, '8:23924af16bb6cc2629bae8a68a003d57', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-10::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-10', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2125, '8:94a49f18387f33ac6136df7bbf15c867', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-11::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-11', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2126, '8:3922ad7305e08c5d1f20de3040305552', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-12::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-12', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2127, '8:08cfd89972a0f5081c2d271756d9f0f2', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-13::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-13', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2128, '8:94ba9b9752add5494e0effc6bc4a5b78', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-14::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-14', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2129, '8:9995d2b888e3a3d9e9744f6fe5a0a8fc', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-15::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-15', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2130, '8:10126a1164e3c1d915b291db443710fb', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-16::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-16', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2131, '8:3cf373c14b585192afcfd804acf1a096', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-17::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-17', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2132, '8:d1ad7625c86dc9bde40987cc22f434f6', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-18::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-18', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2133, '8:c827c5f8c07680409f0caeaba554ef24', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-19::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-19', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2134, '8:7aae88ffa65f4b5bf93463c34e1dca20', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-20::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-20', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2135, '8:49e0e0bdac575f3343e41cb4da30ba42', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-21::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-21', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2136, '8:137f04704b4228f3f1146f505398c5b4', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-22::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-22', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2137, '8:0f40b4b4ff6505bf84025040f885bfe4', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-23::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-23', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2138, '8:62bc8ebae022a3bb9ad1045daa754720', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-24::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-24', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2139, '8:a2bbe84a6018b6717afd932229629ad1', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-25::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-25', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2140, '8:cf84bb6996f5e09d9e273f3e6e386f83', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-26::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-26', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2141, '8:d5ac1ea8994c1811d192177d98512be4', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-27::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-27', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2142, '8:eb1ef0c6e76d58c25993117ba1f7f2a1', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-28::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-28', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2143, '8:7f8fa6e0b8f875ee3bb5f48b59f2f66b', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-29::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-29', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2144, '8:4ae3cdacfb96eba8c4bb0248cab263d1', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-30::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-30', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2145, '8:7b07a108a1ddeb7c9ddde98cbe8e2efa', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-31::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-31', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2146, '8:f047891c5ee42cd49a2359abd2bb4a5d', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-32::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-32', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2147, '8:058fd2fbb8417e6f862fb793c52374a5', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-33::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-33', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2148, '8:b79da84a9e8a446028e01d2e54ea78b9', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-34::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-34', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2149, '8:3350f27a1b0d67da7cbf7ae499f3ebfa', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-35::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-35', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2150, '8:9d7a5b2fe4569e57193f6cc395708f19', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-36::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-36', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2151, '8:6c2a0abb78ec917c9de05cd4f0c2739a', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-37::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-37', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2152, '8:fc74192891d4815c802e3e8325551ed6', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-38::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-38', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2153, '8:1270adb4e750fc82ac6f35d249951a97', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-39::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-39', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2154, '8:759ff5501642f51d1dedb98815cb544f', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-40::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-40', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2155, '8:d8defa3099fcaf96d7e13b6695e43a63', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-41::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-41', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2156, '8:8e4ddfe6a944acf2f845967d94b9543c', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-42::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-42', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2157, '8:62890600c868f128b05d41bc85c07575', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-43::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-43', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2158, '8:fc956316a9d84762a0a2f47980874082', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-44::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-44', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2159, '8:8177b2e3c016e28db4f8dfaabf309967', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-45::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-45', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2160, '8:dca1ecca8adcb81c606ad4eff4d23b33', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-46::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-46', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2161, '8:ec64ce95a2af0a77e53c456d07ab40c9', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-47::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-47', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2162, '8:736fe1661549d942de1e700d036822ab', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-48::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-48', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2163, '8:f1ddb7f72240567d7b40f984e071effa', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-49::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-49', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2164, '8:0cf6c86a2c5a9f8c430714dd949606ce', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-50::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-50', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2165, '8:321a0d47e6890e34747d47acfc088e54', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-51::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-51', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2166, '8:0501177c2c0695462de978a5b97feca7', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-52::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-52', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2167, '8:4a4a1af12752671d424cc6024885ac72', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-53::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-53', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2168, '8:82c397d8c9006cc58ffbd8290d991475', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-54::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-54', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2169, '8:7d203ba70ef3b168b1f4e08d1622cd98', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-55::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-55', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2170, '8:813bf1ce7ba4d12cf917b8e613e649ec', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-56::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-56', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2171, '8:bc1bf27b6c47104967de357f565ff09f', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-57::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-57', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2172, '8:35e5a565b355cb7aaf8304864bf9fde6', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-58::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-58', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2173, '8:c729da089a18396e3c6518cb69c82954', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-59::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-59', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2174, '8:1c3533fc1f3781d875aec2d4224759d0', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-60::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-60', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2175, '8:4ed591ef1d02eab37ea6a319c7c6a5e9', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-61::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-61', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2176, '8:b8eda2b5b8abede09a81ee330645c7f3', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-62::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-62', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2177, '8:cec18a6c76257b1533090767bf3c4d85', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-63::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-63', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2178, '8:ddcad27997b64ecb09d3910bcb1d6880', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-64::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-64', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2179, '8:c9276743038db1c07eff94cf86725cdf', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-65::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-65', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2180, '8:04448b86401a7ccb544cedd48523854f', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-66::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-66', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2181, '8:c18e0b0728b2a07df54a05ba96c3ebc0', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-67::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-67', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2182, '8:1e76f0b1972293effeb9c619cf8b74bf', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-68::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-68', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2183, '8:29e4cfd629bc1d4b51a9b4afa2788d2d', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-69::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-69', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2184, '8:547690a8fc327f0975ccec603196922d', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-70::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-70', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2185, '8:01e7755a9c6bda391218eafc33952fdc', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-71::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-71', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2186, '8:827c0ed75ccb4d90a0cf8dec2a7a9951', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-72::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-72', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2187, '8:e1d72977534751fd827e6cf03b661cd1', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-73::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-73', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2188, '8:19889de1dfab953871a3e9e44b944b71', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-74::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-74', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2189, '8:c104395460ce4473c1c661021f0b4592', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-75::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-75', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2190, '8:605ec8912f31ce12c308e8568b831228', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-76::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-76', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2191, '8:f349a4639dcd60d0eb10afe5d0025e1f', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-77::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-77', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2192, '8:d41b53f0e6eaa6976afb76927d25def3', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-78::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-78', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2193, '8:820fe68c07fc046118b5c827d7d514c2', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-79::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-79', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2194, '8:ed2feb4b541bb22a4e78be25de7f0234', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-80::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-80', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2195, '8:16fdd1d9528785e99885ff0896596297', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-81::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-81', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2196, '8:567eb8e4aef450a3140e23224ed3d11e', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-82::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-82', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2197, '8:a37ad9b478c22f517dfa160f68677337', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-83::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-83', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2198, '8:a79c193467c3ec3e611a6913c34a75dc', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-84::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-84', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2199, '8:db051f91997c72f1ab405a5c9822360f', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-85::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-85', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2200, '8:815976059d7297c6afe9bd79e834413b', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-86::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-86', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2201, '8:e4fcce174615936036feddac6956dd0c', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-87::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-87', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2202, '8:7ba8c2177f73c319ed70a3f6704e4c67', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-88::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-88', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2203, '8:cd4244623dfa61bb3ce8d488854f3185', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-89::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-89', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2204, '8:d7b1acf11ed99e9060c3ec0060201905', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-90::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-90', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2205, '8:dfae5c2892c85f68994ca24e7f0dd39c', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-91::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-91', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2206, '8:b5810cdef5c9f73429d320f018380a58', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-92::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-92', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2207, '8:32116c9b8c2a93648499e085f29c66c0', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-93::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-93', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2208, '8:eda67abfea2705df32ddc716ba3109ab', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-94::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-94', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2209, '8:249afdd94c21e4cfacfb436863aa0625', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-95::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-95', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2210, '8:f272de23467ab07457030cfea68358d9', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-96::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-96', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2211, '8:c3c08128614713f3bc793050373511f0', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-97::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-97', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2212, '8:7e2bf9e0376d4e275a0d8070e571905c', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-98::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-98', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2213, '8:f9e15fce3a398ca71d187d3bd0de729d', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-99::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-99', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2214, '8:6a4e4e4ae4c832aaebac0b6a71fc0369', 'addColumn tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-100::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-100', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2215, '8:cb672b874c852010d62b471177a9e256', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-101::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-101', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2216, '8:2456009d6d17b1810daccea70c0bac65', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-102::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-102', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2217, '8:80cf2c4e68faa9a0ec9c9e4868193f39', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-103::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-103', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2218, '8:2123749648f5a8ef5b4bcf83d5c0bda4', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-104::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-104', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2219, '8:d3379cb7591727840ac2bf385f5c2c9c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-105::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-105', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2220, '8:fabc4fd971c91fe5a042517d4a49b166', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-106::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-106', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2221, '8:3b8f53b5b17f70db2a80ac565045785a', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-107::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-107', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2222, '8:2fe2a10564e9d309668ae4c52f097d7a', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-108::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-108', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2223, '8:1d8f6f12829a784de2361b2969994891', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-109::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-109', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2224, '8:f75409ae3583704087ba591a9ae81dec', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-110::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-110', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2225, '8:39aa6b014692caf5a566c503af4dfe46', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-111::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-111', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2226, '8:4b0d6e092236992232ca8c9b8b215bb9', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-112::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-112', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2227, '8:3eeb79ec6e37acea4b638cb0635d6b59', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-113::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-113', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2228, '8:1a37ed1d7022e98618249fe671c3f699', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-114::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-114', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2229, '8:601f6505a37f2466257e69c4c4650c36', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-115::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-115', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2230, '8:32e2232189ef3d4fdc19ceb898df7fb5', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-116::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-116', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2231, '8:88f66f49bea142d2cb7293e34e122c3d', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-117::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-117', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2232, '8:f6a90de20477cf2fa7fd8d9c7bf1ee1d', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-118::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-118', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2233, '8:ffd6f14f2305c2f3adec6af1d0d779de', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-119::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-119', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2234, '8:2aa9511897525016835eb3a91bfeb592', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-120::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-120', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2235, '8:710114dd0edd10efbc0955077a20d3aa', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-121::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-121', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2236, '8:b6a9319117f5863d2bba4bebe4f34403', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-122::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-122', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2237, '8:9601017ea9668c4d7662a19422231b7e', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-123::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-123', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2238, '8:a4b12196196d7c58760313fc6c093d46', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-124::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-124', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2239, '8:0658fe864916bc8b963393ada4753401', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-125::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-125', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2240, '8:49551f330f0ca484940ff2dc47af32c3', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-126::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-126', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2241, '8:67a48c383a02b577b6a77a70b9cab0fc', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-127::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-127', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2242, '8:b320594a8ad444ca693c925819b4b57f', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-128::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-128', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2243, '8:97a6d7dab7f07a521128ff5b0980b901', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-129::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-129', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2244, '8:a8b2104d3e44feb742d57530445df946', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-130::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-130', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2245, '8:22749cea2344712320e94eeb55b0f6eb', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-131::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-131', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2246, '8:6724b5ad6738e49638d55f765338a9b8', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-132::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-132', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2247, '8:f79ea91e398d7da3b7f06eabcf88652f', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-133::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-133', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2248, '8:27ec4ed0c24621451e7e3a7dfbece71d', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-134::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-134', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2249, '8:45786dfb3d567faaf6c595b38ee44bb6', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-135::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-135', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2250, '8:d191de340efacef9c6e46ea8e25f16f8', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-136::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-136', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2251, '8:2318fd30473e75929ac228c53657c5e5', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-137::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-137', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2252, '8:7ebc2a903a27e52e20bf4b018a55953c', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-138::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-138', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2253, '8:d9492e499cf41a67d2d0781ed569cf98', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-139::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-139', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2254, '8:16d6babf36fa827a2ad4654b41e9ec25', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-140::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-140', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2255, '8:be2777e29f0df1af3ffde87f34c19e1d', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-141::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-141', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2256, '8:7b537b44dda5155b1673e19bb87adc5a', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-142::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-142', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2257, '8:1a1a322ff70412fa06a4ac0ec3cec30d', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-143::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-143', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2258, '8:8b1a7460c19c51a56cbb9aed522ee288', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-144::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-144', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2259, '8:131ba2e7d684d6ba13c3b8b660fe142b', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-145::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-145', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2260, '8:46579c93ef6d6959c693baca9aafe735', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-146::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-146', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2261, '8:3dcc83d2b2c9801f787d033086460076', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-147::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-147', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2262, '8:33e9aa56c61cc75828a54a7d6451b4c7', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-148::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-148', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2263, '8:80360122a43bb950030a640abd1dede4', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-149::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-149', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2264, '8:5e1e923ee1153d0a0e3aeb3e83a42f32', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-150::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-150', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2265, '8:4c393dba9e12ed61a158efc1137e2430', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-151::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-151', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2266, '8:65968dd8231d965f48ad1dfc957c6e8e', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-152::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-152', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2267, '8:60e652b15e90f8b0fc90efbf265024d8', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-153::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-153', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2268, '8:6b9e9b1df0e7b6bc6e43906e7e4a572b', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-154::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-154', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2269, '8:3f2925495fd91fc8176ac8055d180226', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-155::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-155', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2270, '8:189a862e4f697b65b743bc8e03f2c43c', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-156::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-156', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2271, '8:b913fed939ab4c8db3f7b6677875aa15', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-157::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-157', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2272, '8:e7f48dc96bc3fcbcb79d742cd86956bf', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-158::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-158', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2273, '8:b942d21effff02b773977d79ae1f73c2', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-159::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-159', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2274, '8:487fed26eb5c89419524238f1dba1309', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-160::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-160', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2275, '8:31df8ad4aa553b4750ea59a7fb415874', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-161::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-161', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2276, '8:6f4ace5ab5606bffd2527c4e2484adaf', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-162::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-162', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2277, '8:3fea916d62abbd42fc454c12172bcc04', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-163::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-163', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2278, '8:8381ae1774ca9dbd54b467ca5933d902', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-164::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-164', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2279, '8:f63ae33713883ca7a6e67c5505b592f0', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-165::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-165', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2280, '8:306af44b2c6791daf44a3ce1a308cc9f', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-166::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-166', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2281, '8:dec1ddc7a404ad19ce14a84143accced', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-167::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-167', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2282, '8:682b595dc25443dde292b1ea44e2ef1b', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-168::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-168', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2283, '8:3be45afabb074e8c5cc3aaf91fd8e71b', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-169::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-169', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2284, '8:36b2ee9b27f50427c1466ddd6208b33b', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-170::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-170', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2285, '8:41215b1ff47a1ee7a9989ff49a5644ff', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-171::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-171', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2286, '8:de8fe72dc106577e7173461eeb8e9d31', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-172::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-172', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2287, '8:9ca9df6e51803512a867cfd099fde457', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-173::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-173', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2288, '8:419547f5ddfbb7e668df31a9551102b0', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-174::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-174', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2289, '8:6ad69b524b003595a0ff6309260e6fdf', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-175::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-175', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2290, '8:b75b9f2c6f46ca7667965db014c539a6', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-176::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-176', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2291, '8:4a0fd5cec7b60c465bba57c0d43dadef', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-177::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-177', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2292, '8:f3db341fbee18f11fd6edcfd8370f802', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-178::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-178', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2293, '8:609c478f7fc5d3e8d42bc36d5977eb19', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-179::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-179', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2294, '8:8b8b44ff538619bd06adbd6faef15b58', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-180::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-180', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2295, '8:a7231fa0cb298ffc97f08697c8def006', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-181::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-181', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2296, '8:e9457960e016e5e77a5936af303d25f1', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-182::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-182', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2297, '8:7907d882afc0c6b97d781410659dcec4', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-183::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-183', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2298, '8:b715bc48f66f36e4ff559f933ae25592', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-184::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-184', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2299, '8:0c251cd741a2040e9dba8937c408a8aa', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-185::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-185', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2300, '8:76aaebab28d93b64a118dcc5e05781a9', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-186::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-186', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2301, '8:0e458d2f5770ca8a7a268c52e35349cc', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-187::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-187', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2302, '8:74a7bab06dd7f49f060556be2f99b979', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-188::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-188', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2303, '8:cb74189bdabe09a40c66801b90dc4988', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-189::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-189', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2304, '8:a34e9888eb7f7db4d9235eb14f74b566', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-190::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-190', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2305, '8:82c435eca36f3f6d9c6c989c6b9ecc7a', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-191::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-191', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2306, '8:6280bc760f12a270454a46c48e63e4c4', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-192::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-192', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2307, '8:fb0f93a5e374adecf7567e1469b2e0cb', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-193::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-193', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2308, '8:473b8f9a0874596361264e3f17aeaaf0', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-194::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-194', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2309, '8:87d6afa4da9700c7847918f0eddfac3d', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-195::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_biotopverbundbiotopvernetzung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-195', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2310, '8:02a3fa63ca130c45d7a77a23aecadc38', 'addColumn tableName=xplan_lp_biotopverbundbiotopvernetzung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-196::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_eingriffsregelung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-196', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2311, '8:b6da19642a0c4a90543244215bc658f5', 'addColumn tableName=xplan_lp_eingriffsregelung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-197::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_schutzbestimmterteilevonnaturundlandschaft ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-197', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2312, '8:53e7dc10c3a9c286d8f178e23059b713', 'addColumn tableName=xplan_lp_schutzbestimmterteilevonnaturundlandschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-198::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_textabschnittobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-198', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2313, '8:fce6baf231fd2b87cf030e45d8c2ee79', 'addColumn tableName=xplan_lp_textabschnittobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-199::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_lp_zieleerfordernissemassnahmen ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-199', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2314, '8:0c3475ac4738e5fdb96bc3e8771aea57', 'addColumn tableName=xplan_lp_zieleerfordernissemassnahmen', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-200::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-200', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2315, '8:3d5352b85f39e6672d434839803082fb', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-201::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-201', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2316, '8:fc96429a56fcfebd7ff383a9fccc11a3', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-202::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-202', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2317, '8:5b3780efa338b4eb4ae565795cbdf616', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-203::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-203', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2318, '8:7b497f5d048eaf4387e8aadf734bb40e', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-204::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gelaendemorphologie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-204', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2319, '8:449f0ba358afc0bb1175a7206d2a66aa', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-205::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_gewaesser ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-205', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2320, '8:eb2b378fb62572894150ad2e93178c08', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-206::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-206', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2321, '8:dbe34e19d19f1e9377d70bcf111171c9', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-207::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-207', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2322, '8:b09779d1216afb63e1cac40c91cfa500', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-208::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-208', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2323, '8:da55d4a9ffe1c03e4b8b69b1e26f6441', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-209::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-209', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2324, '8:b96b152eabf1977ef1d08408f18de992', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-210::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-210', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2325, '8:c34db4cedb9cce87d318deb538596ab4', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-211::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-211', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2326, '8:4a647c40d9d4624a476a9cd59949dbf7', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-212::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-212', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2327, '8:3892d29cb283f1640175655882b079a1', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-213::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-213', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2328, '8:02bafde61a1d1b1e76d2f19707186a15', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-214::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-214', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2329, '8:73643e366b14a59d15d793b494f6ff77', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-215::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-215', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2330, '8:1da17ea64f4ef9836f86b0574232edae', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-216::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-216', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2331, '8:fd6b69360eb2ca2798affa4e2b561d36', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-217::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-217', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2332, '8:e70581f3b39d2929c1402fbf9b322ccd', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-218::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-218', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2333, '8:e99463feea1f61c82257650d04b25b52', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-219::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-219', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2334, '8:55717f410fa79598fb4f606c799121f1', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-220::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-220', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2335, '8:266dbd6634f801ac5423ac88ff8594f2', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-221::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-221', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2336, '8:67514099a707a69d37ef5602e96c3283', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-222::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-222', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2337, '8:edc442bdcedab11cd3b3fe31b74d218b', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-223::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-223', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2338, '8:e4d7abf1eb4a30167fd8c378c2c1d3b0', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-224::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-224', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2339, '8:b956a47f86ad3d8771bfdf7d90bc99fb', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-225::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-225', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2340, '8:41c527c9ca27fc1835527293c9c52613', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-226::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-226', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2341, '8:87022a5d48158d693ef8703777ce30da', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-227::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-227', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2342, '8:17055e4357ea78bfce5d50d955e9122d', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-228::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-228', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2343, '8:f7d8cb3b806c7260eddcd954caab0590', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-229::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-229', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2344, '8:387b74a38bd10929d4c43ba49842ffff', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-230::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-230', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2345, '8:2995740f009a2b969674f88c92d68cd0', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-231::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-231', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2346, '8:4b666a17dc31e313a3ac3ee99ee09b8f', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-232::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-232', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2347, '8:d8beace36cbb9ca30fc012e6b58fe097', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-233::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-233', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2348, '8:ba469b1fa2298cbe7f822dcb6f2abbe2', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-234::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-234', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2349, '8:a28289041ab072f3ee6aad10fa35e3fa', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-235::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-235', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2350, '8:4e47ae15b1331e1919fb9ae7522cb3ba', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-236::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-236', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2351, '8:6e5dc7247f225bf9695042dc1c33d303', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-237::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-237', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2352, '8:5d93496f832bf0a889b6e7c60428fccd', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-238::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-238', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2353, '8:3630954abb8d6be19806317920fbcdb4', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-239::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-239', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2354, '8:2ec36b3ea8c2286267aaec98790231fe', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-240::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-240', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2355, '8:018ab7a72fff37bb7d809811fe277fd0', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-241::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-241', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2356, '8:204a8e18054d0147e0b2cab325241915', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-242::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-242', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2357, '8:040372625ad2d68537e356036d752820', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-243::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-243', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2358, '8:609b97619ddcdf963e89d4bb59cab2c1', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-244::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-244', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2359, '8:6508679dc6fb8cf0ea348a9d6ccfe135', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-245::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-245', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2360, '8:14e2c238ee42686761e71301d81fccb4', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-246::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-246', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2361, '8:0fbc9353053fffba25b45b3864a5e4ec', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-247::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-247', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2362, '8:bfa80b97e4aa447b282ed3dc7e7a5c09', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-248::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-248', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2363, '8:c740b22e58fc9ed844e2641fa87da83d', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-249::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_baubeschraenkung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-249', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2364, '8:2f49b675252ca3ff2b67e0784d43f096', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-250::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_sichtflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-250', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2365, '8:e9575bf40dea39c1b48ab70c6f258e4c', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-251::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_strassenverkehr ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-251', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2366, '8:e1537780f130230a5930560d88454d8c', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-252::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_textabschnittflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-252', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2367, '8:2dfed9f1027da1392ebd723b3164dd27', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset 6.0.1/changelog_xplansynpre.yaml::1679305301993-253::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplansynpre.xplan_so_wasserwirtschaft ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1679305301993-253', 'lyn (generated)', '6.0.1/changelog_xplansynpre.yaml', NOW(), 2368, '8:c33a794348a0c4f28df688e0cb694470', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430');

-- Changeset ./target/classes/6.0.1/changelog_v601.yaml::tagDatabase-v601::lyn
-- SET SEARCH_PATH TO public, "$user","public";

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID, TAG) VALUES ('tagDatabase-v601', 'lyn', './target/classes/6.0.1/changelog_v601.yaml', NOW(), 2369, '8:fa5124442d0d0a9db765110f16c1cefc', 'tagDatabase', '', 'EXECUTED', NULL, NULL, '4.15.0', '9307250430', 'v_6.0.1');

-- Release Database Lock
-- SET SEARCH_PATH TO public, "$user","public";

UPDATE databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

-- SET SEARCH_PATH TO public, "$user","public";

