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
SET SEARCH_PATH TO public, "$user","public";

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM databasechangeloglock;

-- Lock Database
UPDATE databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

-- SET SEARCH_PATH TO public, "$user","public";

-- SET SEARCH_PATH TO public, "$user","public";

-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: ./target/classes/7.1/changelog_v71.yaml
-- Ran at: 27.11.23, 11:58
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox-target
-- Liquibase version: 4.23.0
-- *********************************************************************

-- SET SEARCH_PATH TO public, "$user","public";

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-3::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-3', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3139, '9:b2960e38c879c8c4a82ee81a101bfaec', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-4::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-4', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3140, '9:9f74da3615ff700acc70d3ca17f322fb', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-5::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-5', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3141, '9:effa491ddde275757347e0de2cfc8b94', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-6::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-6', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3142, '9:d6a700a4048751b70bfe84a46222d3df', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-7::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-7', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3143, '9:c55ddd1f5d06d1ac62ad915a44607829', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-8::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-8', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3144, '9:f6272b2231fd967c2f35df8d8520a1b5', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-9::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-9', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3145, '9:f0b257a6181feb1433e2944fb03f9e27', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-10::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-10', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3146, '9:af248b91104e19a456b17e4705fc43c8', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-11::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-11', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3147, '9:8f9d56e750aa4cc153b7b14f230dbb71', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-12::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-12', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3148, '9:04e130e45f5f1353d4eec893686b3f46', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-13::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-13', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3149, '9:f2d8e20595ff395725b3602accdf0d84', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-14::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-14', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3150, '9:2f31da24d5fc5f71db9ecc7aa1e5d5bd', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-15::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-15', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3151, '9:7aa2bfa5cde953c9fec78b3e219f86fc', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-16::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-16', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3152, '9:48d748d34819084a9372abe9615de0d5', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-17::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-17', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3153, '9:a3aefeb387ead437aa34ff5882dc49f8', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-18::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-18', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3154, '9:a303cea24f7b9481347f93abe59e0213', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-19::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-19', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3155, '9:1d2b96d2e5a416c92fc50f0e440f600c', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-20::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-20', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3156, '9:214e8a91b72459ab6ce1e14511ba18f1', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-21::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-21', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3157, '9:fbd68369bcedb77702d0b93134c8ac5b', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-22::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-22', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3158, '9:5bea9b1eeeca208c2f6ed13c040a5fdb', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-23::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-23', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3159, '9:26a354693ec2f5572499cc9da38c414c', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-24::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-24', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3160, '9:5699d2c1856ed79a3dc44a16e8d2c148', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-25::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-25', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3161, '9:be36de01ec0342627494079f41c1faf1', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-26::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-26', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3162, '9:d8a9fb254c63208401967eb162ebbc9f', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-27::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-27', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3163, '9:7f6a22f3b9b017d46ecae428959ea7ec', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-28::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-28', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3164, '9:eb6d3d3f3562ca1b016ea2e74fe8f87a', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-29::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-29', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3165, '9:32393a090f2f918a6a0732973a5e522a', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-30::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-30', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3166, '9:ade6e22a9143afa060ef5d9b123ea6ff', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-31::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-31', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3167, '9:58f8a00b19b4db985415f76301fe9631', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-32::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-32', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3168, '9:cb661bc5d90958b42b98618b891ab277', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-33::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-33', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3169, '9:c264ba0337abec6d9027e2260c96f0a2', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-34::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-34', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3170, '9:a4a1bfaa335e56d712246b644c52bb1e', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-35::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-35', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3171, '9:c6bcc64611ee4b860df959cd9c9aef75', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-36::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-36', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3172, '9:4144d08c078542cab4ef18b7dd5ddcf7', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-37::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-37', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3173, '9:7996dc85cf65bfa4a5d253278a47ba15', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-38::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-38', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3174, '9:0d28aea2828e2592189b2d2fec91a0a9', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-39::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-39', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3175, '9:072b13ed7123c244affd79ee6e824762', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-40::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-40', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3176, '9:5b929244b3bfc1ab888b06dbd7c81d07', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-41::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-41', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3177, '9:43eb7d9a529e2e9cfd994c49091fde72', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-42::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-42', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3178, '9:f969ba02319f53cf4aad13a02ae4c702', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-43::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-43', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3179, '9:21e3a2167d879950ead63d83cee1ab9b', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-44::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-44', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3180, '9:ec840d6132f3aa7f5945cc7566644beb', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-45::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-45', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3181, '9:b0e49224c583cf7172aa2b066e4a6aac', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-46::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-46', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3182, '9:16ff8b415c61f810937af88c1a6c6e5c', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-47::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-47', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3183, '9:b9013ca887981428a0066ac99e7cce80', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-48::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-48', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3184, '9:0acb4c52857015daed25cabad7088ccb', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-49::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-49', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3185, '9:99bcafa14b1de2d795ce8699a9e87466', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-50::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-50', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3186, '9:071f94c9faeb7b77e00b7ac9f5362968', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-51::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-51', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3187, '9:a6ca0a9c668f078716bbce21f600c76b', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-52::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-52', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3188, '9:eb721c0439b995f674c6dd7d5cf19d4a', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-53::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-53', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3189, '9:672c8a7f7033f092d25e23fb57839d7a', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-54::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-54', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3190, '9:513ed1ffe0fe5bbd7569a860a0bd3703', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-55::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-55', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3191, '9:4b94ca36e1aecdb792bcc011c2698768', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-56::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-56', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3192, '9:5f00d26cddf0103b600cc0672d84b50b', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-57::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-57', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3193, '9:11de0a57ca21f0d173d94c23555b10dd', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-58::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-58', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3194, '9:c7178ee69dcd0fe525a64e0b315659c6', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-59::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-59', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3195, '9:d8cf028cbb92097850d5cc2f2495062d', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-60::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-60', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3196, '9:8e17181d667908704d14e24ee1e1db16', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-61::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-61', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3197, '9:af57fd406f02f017db30d2949e417db7', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-62::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-62', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3198, '9:12183d47fb2ed68d9114b13144180fb1', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-63::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-63', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3199, '9:2450f141aeafced5f84795fcb80ff8b1', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-64::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-64', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3200, '9:555077ea4346bf3db95cf1f3b7b9f4c2', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-65::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-65', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3201, '9:ae27bb6e55a6bbac9be8a640af72452e', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-66::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-66', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3202, '9:8a5969146bc667ded0b54237cae8196e', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-67::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-67', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3203, '9:53f64cfee1fa9febce466986087cc37d', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-68::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-68', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3204, '9:e2c5237778d124026341d1219307ee13', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-69::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-69', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3205, '9:6efe6c78382ad6d203916bad34dd785d', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-70::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-70', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3206, '9:fdc13930a20921a923536938cfbadb82', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-71::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-71', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3207, '9:68376ac5ed53f93e3512556e6a0a7f80', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-72::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-72', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3208, '9:c552bdd33ffac7e484790a52d9128214', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-73::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-73', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3209, '9:8e50ad437fe1d309d490fca7c1798a66', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-74::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-74', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3210, '9:ba17e03fe6169c9181a274a86df65106', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-75::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-75', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3211, '9:58bcf743e7143ed78fc5f0839f011729', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-76::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-76', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3212, '9:42eda3903e4d8eb3e069d213ae704132', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-77::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-77', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3213, '9:65d92d24a8d276c9a6a5bcbc2c6718a5', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-78::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-78', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3214, '9:338b2bea4eee298e8f730ee2540a0446', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-79::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-79', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3215, '9:56e63c60df935bddd6cdd58c39b4126f', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-80::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-80', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3216, '9:658745bb0631b317a8d756a9c316d109', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-81::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-81', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3217, '9:5b94462358cf63bf5a4f50ff188a8892', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-82::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-82', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3218, '9:319dd8d67eff8315efb2ae9915ba7f22', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-83::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-83', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3219, '9:d07f2dec7740c04e1eb091f44413ddc1', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-84::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-84', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3220, '9:a80f636b65de61e522f9d1630ec1d3e8', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-85::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-85', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3221, '9:9717ab0346cb13193bac749462c1e44b', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-86::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-86', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3222, '9:c6c9f554b3bed78a6666a614409310f6', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-87::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-87', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3223, '9:7562a190f31f00ee6c0f49c11a61bfdb', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-88::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-88', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3224, '9:c826745ca2897f4f8c89648c1f58e6ca', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-89::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-89', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3225, '9:b37e30954798d647ba21c1263d07d0e5', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-90::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-90', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3226, '9:ca35360127db2d58208edade6cb1081f', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-91::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-91', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3227, '9:cf337a6aadd7702fad93c61b7fbe65e5', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-92::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-92', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3228, '9:edfafa8d09f1d1b107e4aa6bb56a1e01', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-93::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-93', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3229, '9:585cebe2be5e28d021d026dfc8395337', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-94::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-94', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3230, '9:5ad2dec14651afeb3a972e5042d621de', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-95::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-95', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3231, '9:ecb646daf61ea357dfb24a15a577efb3', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-96::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-96', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3232, '9:6086b315ac22456c19a825bcb63eb4e2', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-97::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-97', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3233, '9:a52ee7f2a5db1b8a3917555d9733a6b0', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-98::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-98', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3234, '9:88767dd29090712713a14fb635a52aa0', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-99::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-99', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3235, '9:265e8c79484c49bab7233aee70ed5fb3', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-100::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-100', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3236, '9:1adcd140cc9c157e05d09c74863e5cb0', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-101::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_generischesobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-101', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3237, '9:09cb259e89dbfbb90e6cec126d1f7fda', 'addColumn tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-102::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-102', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3238, '9:7f975f43a625e9761b61afadd0c275c5', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-103::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-103', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3239, '9:e1c0cc2f2f303aae6082a9ebf2757fbb', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-104::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-104', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3240, '9:812df23759605561c9c80e07127b1175', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-105::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-105', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3241, '9:85b973615ea8416273f9a89c5ed1ae14', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-106::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_vongenehmigungausgenommen BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-106', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3242, '9:0eef907841865f54727e96917ba61be3', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-107::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-107', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3243, '9:bdd32e55c45b4f808959f80a7ea4beb9', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-108::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-108', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3244, '9:79434a1a493b6c3027a91b8ce0fff674', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-109::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-109', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3245, '9:66f5179b066dd300943507b0f89ee963', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-110::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-110', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3246, '9:782fd5c30ba2806d1957b36a75021774', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-111::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-111', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3247, '9:3c28c6ea5b9721a8f678878e9275c2c4', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-112::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-112', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3248, '9:ae50a77491fb8324b9c7d541baf5609c', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-113::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-113', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3249, '9:e8f358e7bd5959388554bb9a84d09c92', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-114::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-114', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3250, '9:a517274aec42f7878c93146256ed475e', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-115::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-115', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3251, '9:eba20599925ae52dbb6c235fac9ce5d4', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-116::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-116', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3252, '9:8ec4d87d4c1b6fba7fd189f831b68cee', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-117::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-117', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3253, '9:cdccd1f829948a71e8e244e9f3973898', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-118::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-118', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3254, '9:009e8da3791ef95a6857c999274a0d78', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-119::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-119', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3255, '9:5ba3bc9b89f12e2a38f35cb86ef2f236', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-120::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-120', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3256, '9:478dac711e7900503526221965774bf2', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-121::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-121', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3257, '9:3a87fd5d429efa6ac9b68347db78e21a', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-122::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-122', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3258, '9:3c31f028cd7289fe0f43c17df446685a', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-123::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-123', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3259, '9:0e20aec45d03c9920b819f8990839f9b', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-124::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-124', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3260, '9:dc0ad5ae97ccd9e8e01b3bd4e7d0c9b0', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-125::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-125', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3261, '9:7598cc1cf06c206ff192df5151048e86', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-126::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-126', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3262, '9:62bf43f6b53429a17c3d0c9b425b67f9', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-127::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-127', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3263, '9:dc92d60b5f9bdc1308d62227bed4a1ff', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-128::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-128', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3264, '9:f41694bc206f362fc77fffa6a6d8930b', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-129::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-129', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3265, '9:0fcad5278d487d76d1e38ed9305ae1c9', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-130::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-130', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3266, '9:004337b14cce63179a2f04ebcdf5b42c', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-131::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-131', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3267, '9:476172b588bdacfa0184114276126b50', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-132::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-132', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3268, '9:4cd9264f21b0fe13f02ab5c72eaef988', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-133::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-133', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3269, '9:c063bdde5e88b93a3f9b1c5e16ee3452', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-134::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-134', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3270, '9:373be27471a07efe00fcefc63282ac8d', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-135::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-135', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3271, '9:581ca27fbeb6601573f0f89b79f4b98c', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-136::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-136', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3272, '9:b3c3c862f57110bad3e4ede3be2608f6', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-137::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-137', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3273, '9:bc8bcaa4dcec3c3c80a11ecc394efca9', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-138::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-138', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3274, '9:34220c5b532fe9dd2297e92eaf18d47d', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-139::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-139', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3275, '9:a8e96dd3eb3a21d1a01855fc9b889790', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-140::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-140', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3276, '9:70096f0833ee41957b728b4d280c1f35', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-141::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-141', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3277, '9:515969a1ddb6ae3edc6aeb742b9389f4', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-142::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-142', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3278, '9:8182d7f53fee462c88a250b793dd7f40', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-143::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-143', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3279, '9:f2c8e14aa2b616599b02a8e0be67f75c', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-144::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-144', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3280, '9:fc260554198b2658758de1c84e165df3', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-145::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-145', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3281, '9:2835dbea4049201104b62ccc1c6ddd6e', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-146::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-146', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3282, '9:130aaa89df1494e8615c31ebeef7f492', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-147::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-147', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3283, '9:3b082c64259dad1743859421104dcebf', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-148::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-148', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3284, '9:d7e775bde22fde7230cc7f38490ba98f', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-149::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-149', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3285, '9:2c819192db31cb766a684a72edc489e4', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-150::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-150', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3286, '9:9937a40dfa2c22433fe57e33fc19db96', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-151::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-151', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3287, '9:639fa0f46f0106d22239989cb5a83cd6', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-152::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-152', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3288, '9:cfccfb2e696528bf5a65207a11918c28', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-153::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-153', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3289, '9:1f4c72056aaa4615373f76e0d847e852', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-154::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-154', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3290, '9:8a71aa23ed8f5ebc0e270f41f7b873cf', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-155::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-155', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3291, '9:6a4f155d0802dbfab0ce8125d6c6b4d2', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-156::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-156', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3292, '9:ae03b55c0b65876277780f13907fed44', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-157::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-157', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3293, '9:0c6b68ba5475794d7bbcf8fee842e660', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-158::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchfpspe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-158', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3294, '9:bff6e620ffa0f1e51d48b733cb48c2cc', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-159::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-159', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3295, '9:688a30270a4b5f363672fe95f62a374f', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-160::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-160', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3296, '9:0331544a897330758e161b35f326a04f', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-161::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-161', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3297, '9:d0a69d40f8f3ee4d5c87b38ae5da235d', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-162::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-162', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3298, '9:9c63038bf263d5dab8e9c22dc3b91db0', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-163::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-163', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3299, '9:0390b51978ee5d7c5d0fb7ff1bd6d38a', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-164::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-164', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3300, '9:9396ba78c6a5a49e867a665275c2f92f', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-165::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-165', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3301, '9:e0fe2a4226797d2ccc4d4167915f81ac', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-166::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-166', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3302, '9:c79fc8f2d8d0d9bb52f2cc3a2999945c', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-167::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-167', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3303, '9:67674ec402f027a6067887e252dc349c', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-168::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-168', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3304, '9:5afaafa59a19c1cf78a6facb775eb236', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-169::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-169', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3305, '9:20994091f29f45b7c73d603774db3771', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-170::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-170', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3306, '9:da0505cf59cd241452fbbe521c78e8be', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-171::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-171', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3307, '9:5419fdeeaffde8d1b765a4670a114ca5', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-172::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-172', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3308, '9:a13c68586cc2191ff06dabc0783f47b1', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-173::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-173', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3309, '9:d4564d9b39fc751ec73e01f483a5f947', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-174::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-174', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3310, '9:42a9d9532db8027c12e5f5ec20c1858d', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-175::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-175', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3311, '9:e01b588bb64f238a1a72fc78bbec125a', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-176::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-176', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3312, '9:a08185fedb6d650f6aa85e1abdf721f3', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-177::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-177', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3313, '9:c4494991492bc8509ed792004aa1b836', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-178::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-178', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3314, '9:6f2a8fcc8fb7dd2998b905e38d7ab4ab', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-179::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-179', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3315, '9:127eb54a5a95ec843460266a13ae4e2c', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-180::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-180', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3316, '9:e0775be485754fdaab56e4fdc13c60d4', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-181::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-181', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3317, '9:5dd561876ca6be46207e9b6a2c6b7505', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-182::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpabe TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-182', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3318, '9:2e31bfc8270809b472299f5c4ce338a7', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-183::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-183', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3319, '9:9133bc9fec84ade85ca64713fd60634a', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-184::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-184', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3320, '9:2acdb270ac696351a6f452ce02b11486', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-185::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-185', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3321, '9:05f5d3be4a93f8d498153d70dce651b3', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-186::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-186', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3322, '9:556e430ddc3eec51f5b9cdd3efbc36be', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-187::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-187', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3323, '9:43ad325efb2f1542a011f0b014ff7185', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-188::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-188', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3324, '9:ae8d92a29a2e9c706c7c1de790954da2', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-189::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-189', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3325, '9:32316f8395af2bf7c74c556534013283', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-190::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-190', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3326, '9:b28fece8179d134cb15f9c29d0012c73', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-191::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-191', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3327, '9:5bbb80037f063bbb4795e738ae3edd2c', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-192::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-192', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3328, '9:cd0066b6c516b0573872d8ed115c12ee', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-193::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-193', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3329, '9:a4bd02046c8ef59acd906561f6648843', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-194::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-194', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3330, '9:d5483b9dad22a7b0873923f03eb0e7af', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-195::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-195', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3331, '9:a1169cf063aa1163cfd206832e4bae49', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-196::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-196', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3332, '9:cfb6cb202a31b0d57c32574123706cfd', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-197::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_biotopverbundbiotopvernetzung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-197', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3333, '9:d8d4038d18bdea00446ac0d668e4a83d', 'addColumn tableName=xplan_lp_biotopverbundbiotopvernetzung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-198::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_eingriffsregelung ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-198', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3334, '9:d8cd64d13d1ed9018c25174129154821', 'addColumn tableName=xplan_lp_eingriffsregelung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-199::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_schutzbestimmterteilevonnaturundlandschaft ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-199', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3335, '9:92af24be0c6d4c8b82db88139f329da8', 'addColumn tableName=xplan_lp_schutzbestimmterteilevonnaturundlandschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-200::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_textabschnittobjekt ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-200', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3336, '9:9a46edb957bd2205c63e12b82431a356', 'addColumn tableName=xplan_lp_textabschnittobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-201::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_lp_zieleerfordernissemassnahmen ADD xplan_flaechenschluss BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-201', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3337, '9:0196022267c42701798086eb737f5978', 'addColumn tableName=xplan_lp_zieleerfordernissemassnahmen', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-202::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_bodenschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-202', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3338, '9:f927c5d84844553cc2f699d36a23a37f', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-203::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_denkmalschutzrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-203', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3339, '9:68ef531db264a9a024700eca67ea19ce', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-204::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_forstrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-204', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3340, '9:535cd4e50cfdb2f9c4952ad2649620ec', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-205::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gebiet ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-205', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3341, '9:3429e7bfff0d7cabf12fdd54962705dd', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-206::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gelaendemorphologie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-206', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3342, '9:c773e7231221b0181d30104a42504fb5', 'addColumn tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-207::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_gewaesser ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-207', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3343, '9:d3601fb3f517285308887382b68b1de7', 'addColumn tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-208::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_grenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-208', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3344, '9:78699d586fd031c1585985f7ecf3658d', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-209::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_linienobjekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-209', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3345, '9:e04251b3507d53bae552c4ce009b7a45', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-210::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_luftverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-210', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3346, '9:a4fd2294261bffe58e7feea583b2814b', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-211::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_objekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-211', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3347, '9:b60a7142d5c149961444391828da9730', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-212::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schienenverkehrsrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-212', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3348, '9:d396884265334e15a1e0998d99a3515b', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-213::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_schutzgebietwasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-213', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3349, '9:3e58eb13ef63f921d13cabfdd74fb6db', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-214::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sonstigesrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-214', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3350, '9:cac3838ff6a302b54dbef464d10bbec8', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-215::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-215', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3351, '9:e68d18c7bc9bb23511f1484f1ba0dd79', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-216::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-216', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3352, '9:d144a4a571b0af5b4585bb99d20aa4e9', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-217::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-217', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3353, '9:ba48efa4e8621ca26ee0468cfa535e3e', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-218::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-218', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3354, '9:e8415f494219c58153e143033fd98359', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-219::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-219', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3355, '9:e5b1df82859801d411cb214b14455a04', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-220::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspemassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-220', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3356, '9:794b37877751c41e188564b34fa1fbc1', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-221::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-221', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3357, '9:7b977830c5ea248a1b2427eb373b2a14', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-222::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-222', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3358, '9:a81fe45eabac0ba35f93fe7573d31e5c', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-223::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-223', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3359, '9:1a5df68e3093aeee485d39cc3e6a1d7e', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-224::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-224', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3360, '9:228ca4f46f33a581c932c17552cfb5f7', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-225::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpspeflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-225', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3361, '9:cb3575f6d2cf81610c8051831eb3f054', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-226::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-226', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3362, '9:6360d2e97cabdf315f0de0a5cdd5a63f', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-227::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-227', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3363, '9:405353e8bb5ab591f54c9fc582f35fbc', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-228::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-228', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3364, '9:834c84700515c999b9a83d6c2479c96e', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-229::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-229', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3365, '9:7013054534907fd342535673d479be01', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-230::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_wirdausgeglichendurchbpmassnahme TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-230', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3366, '9:05dde56c58f7f77ccbefe8b430e471a5', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-231::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-231', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3367, '9:0942578627e20db2f4f56ffc3a871c24', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-232::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-232', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3368, '9:9b8c19315e0db52631fc4e046b1fa33f', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-233::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-233', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3369, '9:32f0032ed4d12c18d03ca00b2d9ebb98', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-234::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-234', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3370, '9:aadea246aba1d83436c59eeafb392074', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-235::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_laermkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-235', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3371, '9:e32de25af8886c1fcafdb734cba6c11e', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-236::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-236', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3372, '9:f2b3e79995e7665e5a412f65a7f59d0b', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-237::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-237', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3373, '9:f5fc2999e700ff330d88734e0a18a280', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-238::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-238', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3374, '9:ad155ba3faf1096f047a53682a51cb0e', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-239::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-239', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3375, '9:a62357ad332a4bed8e25acbf7a43c6aa', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-240::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-240', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3376, '9:1b15c035bdbae99a9bbbd22586c4c779', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-241::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-241', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3377, '9:00a9b25087c79f97b861515334fdcdc1', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-242::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-242', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3378, '9:9b413bac9806ef4baa6f55302780b5ce', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-243::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-243', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3379, '9:9a5dab86379127d96fc2f581274647b8', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-244::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-244', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3380, '9:0c2eb8f6c003663c1862960e76ae5eff', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-245::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingent TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-245', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3381, '9:03596731239f0bfd06e9e04108c387bf', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-246::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-246', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3382, '9:f9a75c72fb1c3b7487c24e31a95e3206', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-247::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-247', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3383, '9:150e20cf8d4bbecfa120eda95056fd89', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-248::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-248', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3384, '9:d1267c8395ed5c643db1df1dd2fc4fa4', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-249::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-249', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3385, '9:d966a86a86358e920abbd22f2dd87a2b', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-250::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-250', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3386, '9:27416fec56d0af418ae9db95d8ea2e7a', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-251::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_baubeschraenkung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-251', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3387, '9:5242c17d2f30b85b7e37384573b273f7', 'addColumn tableName=xplan_so_baubeschraenkung', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-252::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_sichtflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-252', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3388, '9:69956df9537932e8843e120249280fdd', 'addColumn tableName=xplan_so_sichtflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-253::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_strassenverkehr ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-253', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3389, '9:fab1c0ccafe977a8ada7cc29df1b6e51', 'addColumn tableName=xplan_so_strassenverkehr', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-254::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_textabschnittflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-254', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3390, '9:3821e47e28c157d5f7906792ff2f3f7c', 'addColumn tableName=xplan_so_textabschnittflaeche', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-255::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE xplanvalidator.xplan_so_wasserwirtschaft ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-255', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3391, '9:907d2287134a23f97385bf289054f4fa', 'addColumn tableName=xplan_so_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-1::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

COMMENT ON TABLE xplanvalidator.features IS 'Feature ids for plans';

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-1', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3392, '9:d73ffbce6d46f5577a98cc92f857ce50', 'setTableRemarks tableName=features', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset 7.1/changelog_xplanvalidator.yaml::1699259368412-2::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

COMMENT ON TABLE xplanvalidator.plans IS 'Validated plans';

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1699259368412-2', 'lyn (generated)', '7.1/changelog_xplanvalidator.yaml', NOW(), 3393, '9:6ed09d07f9276c85d1441c647febba61', 'setTableRemarks tableName=plans', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398');

-- Changeset target/classes/7.1/changelog_v71.yaml::tagDatabase-v71::latlon
-- SET SEARCH_PATH TO public, "$user","public";

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID, TAG) VALUES ('tagDatabase-v71', 'latlon', 'target/classes/7.1/changelog_v71.yaml', NOW(), 3394, '9:6c69f956067b64453bb11be81da1a46e', 'tagDatabase', '', 'EXECUTED', NULL, NULL, '4.23.0', '1082729398', 'v_7.1');

-- Release Database Lock
-- SET SEARCH_PATH TO public, "$user","public";

UPDATE databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

-- SET SEARCH_PATH TO public, "$user","public";

