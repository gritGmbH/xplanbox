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
UPDATE databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'syn-172-101-000-001.res.spectrum.com (172.101.0.1)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

-- SET SEARCH_PATH TO public, "$user","public";

-- SET SEARCH_PATH TO public, "$user","public";

-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: ./target/classes/7.2/changelog_v72.yaml
-- Ran at: 07.05.24, 18:12
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox-target
-- Liquibase version: 4.25.0
-- *********************************************************************

-- SET SEARCH_PATH TO public, "$user","public";

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-001::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP VIEW IF EXISTS inspireplu.viewservice_zoning_element;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-001', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3395, '9:60c08631e97747b8d600c962b3d87340', 'dropView viewName=viewservice_zoning_element', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-495::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3263 DROP CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name__parentfk_fkey1;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-495', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3396, '9:52034997d014486127b3c0a69c78198c', 'dropForeignKeyConstraint baseTableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3263, constraintName=ad_address_ad_locator_ad_addresslocator_ad_name__parentfk_fkey1', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-496::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3232 DROP CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name_a_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-496', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3397, '9:b95feef06d1efcda823f621f46d5cbc7', 'dropForeignKeyConstraint baseTableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3232, constraintName=ad_address_ad_locator_ad_addresslocator_ad_name_a_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-497::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname_gn_geographicalname_gn_spe_6397 DROP CONSTRAINT ad_postaldescriptor_ad_postname_gn_geographicalna_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-497', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3398, '9:c5ae40e9b7a654460a104dd2b156089c', 'dropForeignKeyConstraint baseTableName=ad_postaldescriptor_ad_postname_gn_geographicalname_gn_spe_6397, constraintName=ad_postaldescriptor_ad_postname_gn_geographicalna_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-498::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority_au_residenc_22580 DROP CONSTRAINT au_administrativeunit_au_residenceofauthority_au__parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-498', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3399, '9:6496d123feda3b719ba85c58e0be3ea4', 'dropForeignKeyConstraint baseTableName=au_administrativeunit_au_residenceofauthority_au_residenc_22580, constraintName=au_administrativeunit_au_residenceofauthority_au__parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-499::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname_gn_geographicalname_gn_sp_9678 DROP CONSTRAINT net_network_net_geographicalname_gn_geographicaln_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-499', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3400, '9:78ae3a1d7ed23661076cb90e09eca4e4', 'dropForeignKeyConstraint baseTableName=net_network_net_geographicalname_gn_geographicalname_gn_sp_9678, constraintName=net_network_net_geographicalname_gn_geographicaln_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-500::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13048 DROP CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey1;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-500', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3401, '9:d0cc01a44d6e6115ad749a354dbe65b7', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13048, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey1', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-501::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13069 DROP CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey2;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-501', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3402, '9:275ad168b4c4342198765e57d0b2ad03', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13069, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey2', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-502::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13071 DROP CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey3;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-502', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3403, '9:9fbde6c8f391f0aec428dc114218bca6', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13071, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey3', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-503::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13018 DROP CONSTRAINT plu_officialdocumentation_plu_legislationcitation_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-503', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3404, '9:1f8545d3af875a3cba5e2af2cf44306c', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13018, constraintName=plu_officialdocumentation_plu_legislationcitation_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-504::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13094 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2__parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-504', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3405, '9:1047ee28697b90edd7fafbf6323fb7d7', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_document_13094, constraintName=plu_officialdocumentation_plu_plandocument_base2__parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-505::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13112 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey1;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-505', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3406, '9:bf771948abc983421410bf0696d56bc1', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_document_13112, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey1', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-506::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13129 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey2;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-506', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3407, '9:0159e52f6118e47b8d0c8079a362f499', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_document_13129, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey2', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-507::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13130 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey3;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-507', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3408, '9:b2bc42cb0aa11fd8a58f6db353121e9e', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_document_13130, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey3', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-508::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13131 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey4;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-508', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3409, '9:75bd93e0ee203f2d22bc0ffb9f6a717d', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13131, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey4', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-509::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13150 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey5;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-509', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3410, '9:efddd6d8c6ecba9b0db561930c44d6d3', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13150, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey5', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-510::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13169 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey6;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-510', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3411, '9:6805d02bcfdb8d5972ec0728bc857732', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13169, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey6', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-511::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13170 DROP CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey7;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-511', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3412, '9:cd82c922619863e0ce765f43122f763d', 'dropForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13170, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey7', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-512::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_hilucspresence_lunom_hilucspresence_13192 DROP CONSTRAINT plu_zoningelement_plu_hilucspresence_lunom_hilucs_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-512', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3413, '9:e3ca6716738ccad027282a70f795d580', 'dropForeignKeyConstraint baseTableName=plu_zoningelement_plu_hilucspresence_lunom_hilucspresence_13192, constraintName=plu_zoningelement_plu_hilucspresence_lunom_hilucs_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-513::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_specificpresence_lunom_specificpres_13205 DROP CONSTRAINT plu_zoningelement_plu_specificpresence_lunom_spec_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-513', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3414, '9:ea269f9d6f04fd296946aabade27c799', 'dropForeignKeyConstraint baseTableName=plu_zoningelement_plu_specificpresence_lunom_specificpres_13205, constraintName=plu_zoningelement_plu_specificpresence_lunom_spec_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-514::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_i_9518 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey2;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-514', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3415, '9:601a97f35b81a28ee8af51e5388457de', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_i_9518, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey2', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-515::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9530 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey3;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-515', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3416, '9:d8b364a4dbc33e7d083bae8f5822a2d0', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9530, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey3', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-516::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9566 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey4;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-516', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3417, '9:4d51bac81cc79a382f1810363b72d881', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9566, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey4', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-517::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9569 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey5;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-517', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3418, '9:cb76c5fcf316f01048417b3188b930a1', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9569, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey5', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-518::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9585 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey6;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-518', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3419, '9:a0151f719c85a1f6ed20d31e80ac6447', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9585, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey6', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-519::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9600 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey7;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-519', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3420, '9:137dcce1dbedf5a21af0258da53ef741', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9600, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey7', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-520::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_p_9648 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey8;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-520', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3421, '9:369615f04c77b23499b6b683bb7f042e', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_p_9648, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey8', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-521::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_a_9510 DROP CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citat_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-521', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3422, '9:ca00b15b35f086d0600722d23d681c4b', 'dropForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_a_9510, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_citat_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-522::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9705 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey3;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-522', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3423, '9:894f82409503cdc32df6477317899722', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9705, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey3', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-523::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9741 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey4;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-523', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3424, '9:edf7b11e372cd4cb2c5a5171e2f4b658', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9741, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey4', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-524::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9744 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey5;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-524', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3425, '9:41d935a3842d0b15ecb8051dba0231d1', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9744, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey5', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-525::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9760 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey6;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-525', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3426, '9:722d9883ab5c73a73bec0e2e34836b58', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9760, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey6', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-526::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9775 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey7;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-526', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3427, '9:0ef7e590329bf7b4e7828a2973cd5631', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9775, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey7', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-527::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_present_9823 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey8;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-527', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3428, '9:9b479f4a11a644b3335c49befca1b0c3', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_present_9823, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey8', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-528::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alterna_9686 DROP CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gm_parentfk_fkey;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-528', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3429, '9:4286e65a1bdbabd3d3ba3f6db619677a', 'dropForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alterna_9686, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_gm_parentfk_fkey', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-529::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3232;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-529', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3430, '9:836a1e97ae1fe0409912cbebcba8f25f', 'dropTable tableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3232', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-530::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3263;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-530', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3431, '9:d02be13295d8242e183802837b3dd952', 'dropTable tableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_address_3263', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-531::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.ad_postaldescriptor_ad_postname_gn_geographicalname_gn_spe_6397;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-531', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3432, '9:dee70e27acb75b78910a64db6a4cbb59', 'dropTable tableName=ad_postaldescriptor_ad_postname_gn_geographicalname_gn_spe_6397', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-532::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.au_administrativeunit_au_residenceofauthority_au_residenc_22580;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-532', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3433, '9:857c0866aed9a3241ea60ff21a1c1533', 'dropTable tableName=au_administrativeunit_au_residenceofauthority_au_residenc_22580', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-533::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.net_network_net_geographicalname_gn_geographicalname_gn_sp_9678;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-533', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3434, '9:acfccf1dd6a1ed66e532a44d9aa7fa04', 'dropTable tableName=net_network_net_geographicalname_gn_geographicalname_gn_sp_9678', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-534::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13018;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-534', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3435, '9:2a9fbc77a5fc4a800670a7de67113e62', 'dropTable tableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13018', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-535::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13048;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-535', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3436, '9:b757447747793b61d4e6076d4895a9c6', 'dropTable tableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13048', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-536::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13069;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-536', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3437, '9:1ccb0583b6d25b9a9946d8ac90fde51c', 'dropTable tableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13069', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-537::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2_l_13071;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-537', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3438, '9:d11f7f47605e7e7bdbc184cf8e1b4fd5', 'dropTable tableName=plu_officialdocumentation_plu_legislationcitation_base2_l_13071', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-538::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13094;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-538', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3439, '9:dea41f053783cf8d9ba2fc46b06b5c3a', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_document_13094', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-539::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13112;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-539', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3440, '9:9c45572f9f1f16bd4161879b43468f1f', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_document_13112', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-540::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13129;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-540', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3441, '9:37fc375de22eedf57eef45464666037f', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_document_13129', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-541::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_document_13130;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-541', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3442, '9:de6ff7581f5e0e48610e49bb8579bf7c', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_document_13130', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-542::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13131;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-542', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3443, '9:cb5bb3cb6279698983888e4b4f8c2200', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13131', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-543::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13150;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-543', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3444, '9:7994e0d492e9d844da99faaa07803392', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13150', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-544::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13169;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-544', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3445, '9:9ec19e40f9c7fd30224934c7fbd3e4d7', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13169', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-545::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legislat_13170;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-545', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3446, '9:2edd7363c7f188055c9f2cdda33b6f6e', 'dropTable tableName=plu_officialdocumentation_plu_plandocument_base2_legislat_13170', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-546::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_zoningelement_plu_hilucspresence_lunom_hilucspresence_13192;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-546', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3447, '9:9b958ba100c0c9ef7fa4f08e42a45ba6', 'dropTable tableName=plu_zoningelement_plu_hilucspresence_lunom_hilucspresence_13192', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-547::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.plu_zoningelement_plu_specificpresence_lunom_specificpres_13205;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-547', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3448, '9:1f223a5146b447b03f1257af079dbc76', 'dropTable tableName=plu_zoningelement_plu_specificpresence_lunom_specificpres_13205', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-548::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_a_9510;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-548', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3449, '9:c7b22d5e08ae7577c269045450192d77', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_a_9510', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-549::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9530;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-549', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3450, '9:df3f566b8881bf05e40f9db3602e9ae4', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9530', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-550::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9566;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-550', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3451, '9:d600782d8fa182203e232186408a2d86', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9566', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-551::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9569;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-551', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3452, '9:9e5fa570f3efc5fc9df4463b1a065c9d', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9569', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-552::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9585;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-552', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3453, '9:0e9ff9c23fcabdcff9b04c34560ae60a', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9585', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-553::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9600;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-553', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3454, '9:15145e0d76bf10f10e34a72d467a2123', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_c_9600', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-554::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_i_9518;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-554', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3455, '9:cf34742bc76d33a793081f6ab53e3909', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_i_9518', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-555::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_p_9648;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-555', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3456, '9:b29491a61336eb91fa0cdcf675361397', 'dropTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_p_9648', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-556::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alterna_9686;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-556', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3457, '9:ec71515f78fbdb3b63032cf19f708fbf', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alterna_9686', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-557::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9705;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-557', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3458, '9:d3ebe327dc4a8eed4964bcfb18e61943', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9705', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-558::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9741;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-558', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3459, '9:89cec3c8081d88bb293d61eea06a752e', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9741', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-559::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9744;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-559', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3460, '9:b9bbb9e43dd374513f403b1a5dbfb595', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9744', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-560::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9760;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-560', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3461, '9:96871cfb7f9381b88003baff3e5757ff', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9760', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-561::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9775;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-561', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3462, '9:6b863edc3d4fe1160a2525c7ede0ca1c', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_citedre_9775', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-562::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_present_9823;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-562', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3463, '9:11d0e24e202a1d58a3222e82c2065302', 'dropTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_present_9823', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-563::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_designator DROP COLUMN ad_addresslocator_ad_locatordesignator_ad_type_attr_gml_re_3231;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-563', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3464, '9:48c42c66bb5f8e5af448f8c9326b2748', 'dropColumn columnName=ad_addresslocator_ad_locatordesignator_ad_type_attr_gml_re_3231, tableName=ad_address_ad_locator_ad_addresslocator_ad_designator', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-564::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22590;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-564', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3465, '9:65bc9019028f08fe6d9cffc578ffa63e', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22590, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-565::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22591;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-565', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3466, '9:dcd07f4c32464a87335c7539366dddca', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22591, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-566::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22592;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-566', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3467, '9:29c7918b1e46c4ed35c425b8b81a5655', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22592, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-567::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22593;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-567', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3468, '9:2d5388235ea909d41273a1d5267456da', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22593, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-568::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22594;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-568', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3469, '9:ad3d85915d4108cd0ff57c3219862b56', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22594, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-569::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22595;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-569', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3470, '9:1aa13c8f92f2a94c6bf2c21f4655c9db', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22595, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-570::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22597;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-570', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3471, '9:c545aed3395b80c3ccad0faf06f23ee8', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22597, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-571::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22598;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-571', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3472, '9:9e31644830da0dae938d1dafe262c4db', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22598, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-572::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22599;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-572', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3473, '9:dbdbb7205a60724434986fc0cc56e894', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22599, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-573::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22600;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-573', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3474, '9:c31b6c47e59cdf8182511bf66540eb3a', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22600, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-574::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22601;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-574', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3475, '9:9240a61db56fc0572f22de5129263bfa', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22601, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-575::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22602;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-575', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3476, '9:3f10b78a217cc844ffd0a1e6170e6b3c', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_gr_22602, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-576::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_la_22550;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-576', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3477, '9:7f27a7d782b1ca9713197eb7c3921ec9', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_la_22550, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-577::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_la_22551;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-577', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3478, '9:279b0b29b2982d59ac31dec0315d2913', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_la_22551, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-578::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_la_22552;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-578', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3479, '9:831ef812396c232e06c10e79d7e82f12', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_la_22552, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-579::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22554;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-579', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3480, '9:0bf4efa3a200be1ada6eec7e3a46cd77', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22554, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-580::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22555;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-580', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3481, '9:32747418a9a421b3607e38f76d5422a5', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22555, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-581::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22556;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-581', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3482, '9:16dec007987be86d0f57b31a68a2e6e3', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22556, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-582::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22557;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-582', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3483, '9:f26f0b11486ce17d8192ab1cfaadb8b0', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22557, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-583::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22558;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-583', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3484, '9:31c08a268c960829e5d9aada59e07a52', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22558, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-584::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22559;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-584', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3485, '9:00cb40c7019e18558db3626ccec6a282', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22559, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-585::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22561;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-585', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3486, '9:a3c1be51dbdca7910b91fef2e1060b71', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22561, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-586::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22562;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-586', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3487, '9:75f3eb55d0be7a80471f8dc2b337ba9e', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22562, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-587::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22563;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-587', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3488, '9:8e41c963f1ff0d7c90b1125922f3d0ad', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22563, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-588::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22564;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-588', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3489, '9:7fca3a7fe22d31e8d2d9c4f79916fb8a', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22564, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-589::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22565;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-589', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3490, '9:309c4c0f7cebb7ffa2d05b45d159629c', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22565, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-590::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_na_22566;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-590', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3491, '9:e750d61bfa683d074cfdb8f57abc90b8', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_na_22566, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-591::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22571;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-591', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3492, '9:b6e8277fece18bdf27f37b643121b3cb', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22571, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-592::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22572;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-592', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3493, '9:c869b67211a4617a264d2ca9f7b2e334', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22572, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-593::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22574;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-593', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3494, '9:a6c7c687db313607fe76ad5b7b775c24', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22574, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-594::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22575;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-594', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3495, '9:19c62e4eb69c41c00a5163257039c974', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22575, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-595::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22576;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-595', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3496, '9:9ecee9ef04cb239cba0f84901904e4af', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22576, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-596::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22577;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-596', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3497, '9:b53f1e1d3065850b7e59cd061f7a997c', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22577, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-597::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22578;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-597', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3498, '9:e3eb82009799901f3301e8b3ac943e0e', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22578, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-598::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22579;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-598', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3499, '9:0c9286ff14bd3cd7a0a430e031d97115', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_pr_22579, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-599::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_so_22567;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-599', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3500, '9:24ee775f391d882147c0ee2539b310ef', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_so_22567, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-600::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_so_22568;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-600', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3501, '9:d4138f4c893a553caab1b3e0327fb896', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_so_22568, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-601::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority DROP COLUMN au_residenceofauthority_au_name_gn_geographicalname_gn_so_22569;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-601', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3502, '9:13855e61c4986e3ba74dcd8fd0ef81a1', 'dropColumn columnName=au_residenceofauthority_au_name_gn_geographicalname_gn_so_22569, tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-602::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13010;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-602', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3503, '9:19059001dc3a975462ee7be0fa166067', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13010, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-603::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13011;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-603', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3504, '9:79e10a4175588b0b0ee9f861a9881895', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13011, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-604::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13012;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-604', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3505, '9:54ed0e2653aa244eaee7a6288a2ec509', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13012, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-605::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13013;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-605', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3506, '9:15030bf1f1bc3b459e431917d9faf9cf', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13013, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-606::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13014;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-606', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3507, '9:70bad1f1f020e892f606839e4f4a2b29', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13014, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-607::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13015;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-607', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3508, '9:6b57c06c06389228e209329bb424a393', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_13015, tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-608::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22542;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-608', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3509, '9:93c2a991553e54747aeb84ff05215934', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22542, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-609::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22543;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-609', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3510, '9:a7e461afe3c582dabf473fde82fcab20', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22543, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-610::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22544;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-610', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3511, '9:9d45a2e8d04067a0ad311d85f7141c77', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22544, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-611::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22545;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-611', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3512, '9:e0a7d39d9e0fc09ad9792dec69d28f8c', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22545, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-612::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22546;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-612', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3513, '9:8cfae4022e308a12c4effb2332ea17de', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22546, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-613::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22547;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-613', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3514, '9:5881c85a13ec4fb01d0bba298541cf63', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofna_22547, tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-614::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6391;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-614', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3515, '9:62408d2d7b677525ceed6edef1e31aad', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6391, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-615::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6392;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-615', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3516, '9:2b18f49b8967b7481c14ac3caed7c20c', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6392, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-616::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6393;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-616', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3517, '9:033f9c3e46ba92297f811f05508c3a89', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6393, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-617::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6394;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-617', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3518, '9:a50dd864ffad078168c67d8ab809cc0a', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6394, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-618::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6395;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-618', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3519, '9:62b3285b14eb8104e661bafcd7c92eb4', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6395, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-619::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6396;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-619', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3520, '9:120f002db8d71acaf0a6c894f022de94', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_6396, tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-620::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9672;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-620', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3521, '9:b90b539332ac2c7f70ba8d9d2721e227', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9672, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-621::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9673;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-621', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3522, '9:3ca07234ad72767fe6b1a2de38abe5fd', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9673, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-622::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9674;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-622', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3523, '9:92dbfa1d24ca7f96cd143ae75d42a4f0', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9674, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-623::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9675;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-623', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3524, '9:0b4b3146dd4bf0ad1e4f3aaa627e05d3', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9675, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-624::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9676;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-624', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3525, '9:656770cbe5be4fbf50932773159aa9dc', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9676, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-625::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9677;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-625', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3526, '9:ec0bbaf80b0597f2e6503bf1c755640a', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9677, tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-626::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9839;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-626', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3527, '9:bafa431a8c986ac1e120d6817b57f1ee', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9839, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-627::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9840;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-627', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3528, '9:980eebee9066d4ef4f02ed002a0b3830', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9840, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-628::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9841;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-628', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3529, '9:b773edffb05935a090d30a2599805f6d', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9841, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-629::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9842;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-629', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3530, '9:f6edbfec82f983b61b66db152621fbbf', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9842, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-630::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9843;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-630', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3531, '9:24919db940cd8b5902aea340a2d42fe5', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9843, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-631::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9844;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-631', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3532, '9:ac8165e66f16e09b6a0307cdaa12e3f1', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9844, tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-632::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9847;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-632', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3533, '9:3ee0070e4d342e49204b75ab04f1e58a', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9847, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-633::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9848;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-633', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3534, '9:4c972aff0acbfd66815db0f22f9bc3c9', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9848, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-634::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9849;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-634', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3535, '9:50894e1bcba90dbf202e679451d0674c', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9849, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-635::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9850;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-635', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3536, '9:df593b697d93f588b235132485a293cc', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9850, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-636::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9851;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-636', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3537, '9:1027ae6bb754d926ef1f9bade724c01c', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9851, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-637::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name DROP COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9852;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-637', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3538, '9:faea65d5c4414648328c3f3a42486268', 'dropColumn columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofnam_9852, tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-638::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterations_13016;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-638', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3539, '9:fd3f180b159f10be4eccb544faae00ab', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterations_13016, tableName=gn_namedplace_gn_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-639::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterations_13017;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-639', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3540, '9:95b0193660024599ee3908151aeab4c3', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterations_13017, tableName=gn_namedplace_gn_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-640::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterations_22548;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-640', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3541, '9:ca8283654afbb3e64d073e0fd28d1f49', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterations_22548, tableName=au_administrativeunit_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-641::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterations_22549;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-641', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3542, '9:ad9a5cb523d4ab3055fc29637ebefa9d', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterations_22549, tableName=au_administrativeunit_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-642::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsc_9845;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-642', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3543, '9:ad09645b7d752bbc6b2d653949a322b0', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsc_9845, tableName=cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-643::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsc_9846;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-643', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3544, '9:9b92536f47cc673a3d9d8ab53bd52a28', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsc_9846, tableName=cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-644::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsc_9853;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-644', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3545, '9:a006d06fe1e76cf44699f7e69ce3b519', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsc_9853, tableName=au_condominium_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-645::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name_gn_geographicalname_gn_spelling DROP COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsc_9854;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-645', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3546, '9:901f99792470f60c7a8eb20e2a0bfa0c', 'dropColumn columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsc_9854, tableName=au_condominium_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-646::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12987;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-646', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3547, '9:7054e02ee72bc8246af3a93bc5644837', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12987, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-647::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12988;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-647', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3548, '9:db28f5f4022a5ff3cc54d7eef3c3c5de', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12988, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-648::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12989;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-648', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3549, '9:7887a5538716f0874f0fe6bbf0fd3454', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_d_12989, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-649::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12971;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-649', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3550, '9:c6d4212a12d24eba794862da3e80b497', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12971, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-650::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12972;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-650', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3551, '9:66f7db37ec97a5dc8f7576c1a6810bdb', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12972, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-651::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12973;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-651', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3552, '9:cf53ace8ec24c33357253298cd4539d6', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12973, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-652::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12974;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-652', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3553, '9:2ffa07281952227a3ed7ca803b09342f', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12974, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-653::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12975;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-653', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3554, '9:ef1b43cca749a7b855afa75058fef476', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12975, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-654::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12976;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-654', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3555, '9:a7e8afe4e6a8b438ec0b2fe1c4f0023c', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12976, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-655::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12977;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-655', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3556, '9:028d7398859b86c6e1917c7373a02fa0', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12977, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-656::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12978;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-656', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3557, '9:ef571ebe921a411f13a19d285d40adc3', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12978, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-657::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12979;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-657', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3558, '9:80920954f8ebc36f7f80c59efdf1330c', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12979, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-658::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12981;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-658', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3559, '9:aff80401e9630df57692fa1bbf9b4349', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12981, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-659::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12982;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-659', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3560, '9:e7018ddf35e3df03547bb570ced9c855', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12982, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-660::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12984;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-660', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3561, '9:41dcdafcc6f47578104c9881962de7c5', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12984, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-661::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12985;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-661', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3562, '9:a8cb6ae0280910fa07de91dd806106c1', 'dropColumn columnName=gn_leastdetailedviewingresolution_gmd_md_resolution_gmd_e_12985, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-662::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13007;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-662', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3563, '9:45b82fcee94ff8101705b18a2f1b86a0', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13007, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-663::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13008;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-663', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3564, '9:764edde0f9808c30dd9d2d0f16b3d9b7', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13008, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-664::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13009;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-664', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3565, '9:0aef74b4cc79a04311c4f684f9e6e002', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_di_13009, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-665::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12991;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-665', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3566, '9:f1332b2d0f5a628262f8f9705d644687', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12991, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-666::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12992;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-666', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3567, '9:9d255c81f83e5dcef4b07c55ae44ad46', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12992, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-667::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12993;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-667', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3568, '9:289cafe11b79401a0a493a1bbe50de6f', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12993, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-668::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12994;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-668', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3569, '9:94d5e9e66e197b9a79da569d11eb743f', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12994, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-669::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12995;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-669', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3570, '9:193b5a8a1b5ae28921211092f5bea8d0', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12995, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-670::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12996;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-670', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3571, '9:621ac858fba9c701494809540640eae6', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12996, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-671::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12997;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-671', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3572, '9:879125de05c0dd0228fb06ee82fca180', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12997, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-672::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12998;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-672', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3573, '9:75d5287ca2124d6d6d8d264926bae237', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12998, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-673::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12999;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-673', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3574, '9:a481e1434c482cd8c1a73002c2dce710', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_12999, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-674::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13001;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-674', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3575, '9:7d60b5c9ff56cfaf7ac6e4cb5aa4eb83', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13001, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-675::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13002;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-675', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3576, '9:0c9e49b3bf9e284f134d140b0c3b470b', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13002, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-676::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13004;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-676', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3577, '9:f61f9610170949c2522ad78d59d34b60', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13004, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-677::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace DROP COLUMN gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13005;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-677', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3578, '9:9163f160ab0e485c6298542690fa68c8', 'dropColumn columnName=gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_eq_13005, tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-678::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remote_13222;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-678', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3579, '9:b4af0f0b4b43ce894ea91d80a1cf295f', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remote_13222, tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-679::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remote_13227;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-679', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3580, '9:3ce9401503354bc8f36d359964c24aec', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remote_13227, tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-680::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remotes_9509;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-680', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3581, '9:23e044ee1a579df37876ae6e62117237', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remotes_9509, tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-681::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remotes_9668;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-681', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3582, '9:9366861b7407d7172fd0a2c930c5fd3a', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remotes_9668, tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-682::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remotes_9685;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-682', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3583, '9:d577b5c7ce3459e8e887c468e039fdf3', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remotes_9685, tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-683::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remotes_9859;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-683', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3584, '9:271282f70a7a06f0a7631e81a944a6a7', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remotes_9859, tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-684::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref DROP COLUMN net_linkreference_net_applicabledirection_attr_gml_remotes_9864;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-684', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3585, '9:5f96dba8bf9ef854f816b35c3d4f905d', 'dropColumn columnName=net_linkreference_net_applicabledirection_attr_gml_remotes_9864, tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-685::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gm_13219;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-685', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3586, '9:e2ce73600bb0706852d47e24522dc4ef', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gm_13219, tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-686::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gm_13224;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-686', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3587, '9:2b1ff3f2576647f0faadf8120062e3a3', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gm_13224, tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-687::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gml_9506;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-687', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3588, '9:537015a54192562df561a6ad66a9975c', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gml_9506, tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-688::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gml_9665;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-688', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3589, '9:5d6bc61ba95bd87d861933d5c5a4cb3f', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gml_9665, tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-689::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gml_9682;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-689', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3590, '9:78c62e5fc117c8c9b18f707a2e29390d', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gml_9682, tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-690::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gml_9856;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-690', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3591, '9:60389a52f9ab639f22b7595b07055592', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gml_9856, tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-691::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_gml_9861;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-691', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3592, '9:6e355b8fa98c66177a3d32be5f322e53', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_gml_9861, tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-692::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_ni_13218;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-692', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3593, '9:1a6995d4bea002de6b4a77109afa2cd2', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_ni_13218, tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-693::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_ni_13223;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-693', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3594, '9:1bbbd0ab4b198370cda4b6095784c94f', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_ni_13223, tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-694::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_nil_9505;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-694', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3595, '9:86c0d5042e4db05559ec7aac29841505', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_nil_9505, tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-695::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_nil_9664;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-695', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3596, '9:cf137a9fc79cf1478e4df01e61281517', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_nil_9664, tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-696::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_nil_9681;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-696', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3597, '9:da36ab37e17533f758a326f5353158f6', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_nil_9681, tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-697::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_nil_9855;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-697', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3598, '9:f8299f04b775268cb4b37c485f237e2d', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_nil_9855, tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-698::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref DROP COLUMN net_simplelinearreference_net_applicabledirection_attr_nil_9860;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-698', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3599, '9:b7e09ebc762309ceb001a0b7dd3129ec', 'dropColumn columnName=net_simplelinearreference_net_applicabledirection_attr_nil_9860, tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-699::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml_13221;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-699', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3600, '9:517842cc3821a5239c022c0081a7066e', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml_13221, tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-700::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml_13226;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-700', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3601, '9:c9649303947d79e1ce3661b30603b1ad', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml_13226, tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-701::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml__9508;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-701', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3602, '9:ffc6ed1636de9b31718ac2b605362a20', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml__9508, tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-702::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml__9667;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-702', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3603, '9:8e6d186526d26bbf278d5b9c4678ffe9', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml__9667, tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-703::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml__9684;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-703', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3604, '9:6aabe080398e2f0e9ea68f44ba17d848', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml__9684, tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-704::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml__9858;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-704', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3605, '9:52419f8d7440693ee573a27769220b18', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml__9858, tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-705::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_gml__9863;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-705', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3606, '9:8dbabe2119ea3b55abcacae88aa4fbc6', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_gml__9863, tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-706::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nil_13220;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-706', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3607, '9:767fefa7228cc8c943cd6c1f2796bf3b', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nil_13220, tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-707::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nil_13225;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-707', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3608, '9:c26b13de41dcc697fd8cbb057888a199', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nil_13225, tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-708::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nilr_9507;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-708', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3609, '9:c5dd399b42a10c6f1ac029b85417d028', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nilr_9507, tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-709::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nilr_9666;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-709', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3610, '9:8593d6629ad3f883d31eed2328eff2a0', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nilr_9666, tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-710::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nilr_9683;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-710', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3611, '9:bff3b5a21822c47ed4f68d091505742f', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nilr_9683, tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-711::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nilr_9857;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-711', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3612, '9:7e79f1d9fcff7b388c6b3b9028494c71', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nilr_9857, tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-712::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref DROP COLUMN net_simplepointreference_net_applicabledirection_attr_nilr_9862;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-712', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3613, '9:7702e64bbd4a66ed8665db64a577a65b', 'dropColumn columnName=net_simplepointreference_net_applicabledirection_attr_nilr_9862, tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-713::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmap_13215;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-713', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3614, '9:22c63b672b85bfc589e78ca0817277e0', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmap_13215, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-714::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmap_13216;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-714', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3615, '9:0d7330bd25ff7780ff2d42c255f4a19a', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmap_13216, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-715::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapu_9670;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-715', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3616, '9:6b95c2d9e8dff4e07e900c1115fb2d8d', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapu_9670, tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-716::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapu_9671;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-716', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3617, '9:43416aec52ad00cd64ba8a7f19eda4e1', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapu_9671, tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-717::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapur_115;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-717', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3618, '9:b39c3a6cedc47952a663cb761b12bb58', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapur_115, tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-718::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapur_116;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-718', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3619, '9:bbdb55c1c75973f4a2db23cd1c6b12b1', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapur_116, tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-719::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-719', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3620, '9:a4ca74c8869ac4844d684fd8f0869893', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri, tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-720::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-720', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3621, '9:d58f7789aa628a9570fb089dd048eece', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri, tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-721::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-721', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3622, '9:427c501f5d8e5933a71f60f20b7f0d42', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroudmapuri, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-722::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundma_13214;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-722', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3623, '9:1ccba12193ce9795445d4801cf51c959', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundma_13214, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-723::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan DROP COLUMN plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmap_9669;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-723', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3624, '9:e6b9f6e9a82bf760caae83385c913b87', 'dropColumn columnName=plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmap_9669, tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-724::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_dimensioningindication DROP COLUMN plu_dimensioningindicationcharactervalue_plu_indicationre_13217;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-724', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3625, '9:5ba07c0ddae753f589f4d60ddcaf1b35', 'dropColumn columnName=plu_dimensioningindicationcharactervalue_plu_indicationre_13217, tableName=plu_zoningelement_plu_dimensioningindication', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-725::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13189;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-725', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3626, '9:043a2dcbdb950e7ee8b3847fc2d3baf8', 'dropColumn columnName=plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13189, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-726::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13190;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-726', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3627, '9:61fd57760f0c1a5b2b72ac78ca37ad2a', 'dropColumn columnName=plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13190, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-727::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13191;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-727', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3628, '9:862912e82668a37a042d5aa7e25de897', 'dropColumn columnName=plu_hilucspresence_lunom_hilucspresence_lunom_orderedlist_13191, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-728::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13053;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-728', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3629, '9:9d931bf489bfe3064d0720554740375e', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13053, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-729::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13054;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-729', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3630, '9:fc55619bedd1f9d0349d82077422abf2', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13054, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-730::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13055;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-730', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3631, '9:02ce0f319f03d301f4baa0c2f2010178', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13055, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-731::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13057;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-731', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3632, '9:83e82a0ea836efc00474c9c83de694e1', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13057, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-732::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13058;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-732', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3633, '9:2fbf6708e225bc00c2d6ae0267e06507', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13058, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-733::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13060;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-733', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3634, '9:dccda0182b25b99e8fb986ec83322439', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13060, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-734::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13061;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-734', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3635, '9:990d51fbdae95066926a473128e26f37', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13061, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-735::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13062;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-735', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3636, '9:e127863da8aa81686b8e1d3e4745a7f0', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13062, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-736::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13064;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-736', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3637, '9:65ee2bf2b70663a4ad8470667bc41f0c', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13064, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-737::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13065;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-737', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3638, '9:9dfd798216caa19d9538cd76e2f147b8', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13065, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-738::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13066;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-738', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3639, '9:d5b76c718640d4eb8200884c6d92a11d', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13066, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-739::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13067;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-739', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3640, '9:1f3ea85107e67b55920c3e8ca313dfd6', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13067, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-740::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13068;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-740', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3641, '9:562fabff65f704f77435c38681344891', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13068, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-741::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13075;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-741', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3642, '9:6c2f9e5bf24ee92d8a0978ea30ded81a', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13075, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-742::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13076;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-742', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3643, '9:dda99224d595b4b8539c4851bd650855', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13076, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-743::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13077;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-743', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3644, '9:529f6f47b7c3086cb9aa1d9d14b178e5', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13077, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-744::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13078;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-744', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3645, '9:a65743d3ba21d32b4400f33799699cc6', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13078, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-745::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13079;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-745', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3646, '9:699892ebbc776d33e840336effcedbaa', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13079, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-746::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13080;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-746', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3647, '9:15e43f834751964436bcfbe70e93f3eb', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13080, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-747::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13081;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-747', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3648, '9:b1e3576a0162a1910e6120f4f782bd98', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13081, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-748::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_d_13082;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-748', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3649, '9:627a2c6010fcd1b40b6a86ae5e06dfe8', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_d_13082, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-749::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_i_13073;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-749', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3650, '9:0b7485081a61cdf3443e4e6c1fd73283', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_i_13073, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-750::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_j_13090;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-750', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3651, '9:56f2e41ecc9254a14b4e9ea971c04423', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_j_13090, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-751::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_j_13091;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-751', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3652, '9:a269d8de529aeac44ba1c0b9449bc968', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_j_13091, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-752::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_j_13092;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-752', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3653, '9:35befafe0f2c6b924f63ba9d5f8c41de', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_j_13092, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-753::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_j_13093;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-753', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3654, '9:2da755b9bfc1589a3738d90d8b679203', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_j_13093, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-754::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_l_13083;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-754', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3655, '9:92d16a46880520043a57a69c5f1186a8', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_l_13083, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-755::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_l_13084;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-755', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3656, '9:f60b236c567c55c2a4aeb1311f327edd', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_l_13084, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-756::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_l_13085;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-756', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3657, '9:9aee93f82ecf206c8b2ae041408234f8', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_l_13085, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-757::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_l_13086;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-757', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3658, '9:85ad24d1c072a770818df428c10e6dd9', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_l_13086, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-758::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_l_13087;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-758', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3659, '9:2624dcfcf1cab80055bb71adb4022d60', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_l_13087, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-759::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_o_13074;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-759', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3660, '9:bbe91ee3eb4b85e4422167020f167b1e', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_o_13074, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-760::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_s_13050;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-760', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3661, '9:d9700941ca9053fd74cccf2c94020d50', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_s_13050, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-761::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_s_13051;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-761', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3662, '9:50c851a3d47c48008308e80cd6578d82', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_s_13051, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-762::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_base2_s_13052;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-762', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3663, '9:466af989b8e43191de53540ee124b168', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_base2_s_13052, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-763::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13030;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-763', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3664, '9:9fbe7f2ed49b4762cbf7cfa070cc13ce', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13030, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-764::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13031;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-764', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3665, '9:05c4e8ec3da0959032b9da74aec27e72', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13031, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-765::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13032;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-765', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3666, '9:8f8db3433342b7d20750f861a88e869f', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13032, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-766::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13033;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-766', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3667, '9:c1c6e2f2e558592f44004eb60fbfa46a', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13033, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-767::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13034;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-767', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3668, '9:81dbe68bfe0fa9a2449ac75e68c40e27', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13034, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-768::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13035;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-768', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3669, '9:20398df7fbbd2b09a579bfbf79caf0ac', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13035, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-769::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13036;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-769', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3670, '9:393ba11a12d6e5f6147a5619abb60001', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13036, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-770::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13037;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-770', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3671, '9:f48059a3690ce24204227663eda80b80', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13037, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-771::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13038;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-771', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3672, '9:40d2035086a37daece410a5935fc5f34', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13038, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-772::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13039;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-772', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3673, '9:93138060ca1a87ba65683d02339a8e4e', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13039, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-773::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13041;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-773', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3674, '9:9de6f0cd15db5fb4048c42c7e7646da6', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13041, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-774::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13042;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-774', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3675, '9:acc5dd35ce914e041fc99c9a7e8e0cac', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13042, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-775::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13043;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-775', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3676, '9:efd73a5c171a6c62d24961ab14d05953', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13043, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-776::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13044;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-776', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3677, '9:73a2e6d06d75e82ff25062617681665c', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13044, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-777::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_des_13045;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-777', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3678, '9:96cc6bcfa52b729b8615560eab3935d1', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_des_13045, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-778::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_ide_13046;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-778', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3679, '9:a5e922ebebce95838ee56e1ef7836115', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_ide_13046, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-779::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_legislationcitation_base2_legislationcitation_gml_ide_13047;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-779', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3680, '9:5878abe09e3da3a96109aaf70cbbb884', 'dropColumn columnName=plu_legislationcitation_base2_legislationcitation_gml_ide_13047, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-780::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_attr_n_13115;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-780', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3681, '9:59508b5cd3ce489107a0f68fe0da5da1', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_attr_n_13115, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-781::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_attr_x_13116;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-781', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3682, '9:02d816f1b96c2be2053222bc37d3a7c3', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_attr_x_13116, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-782::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13117;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-782', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3683, '9:fe93818565a8efdd21ccbecdab6028f0', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13117, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-783::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13118;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-783', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3684, '9:c470e77190310e120b3b0f9cd31d8823', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13118, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-784::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13120;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-784', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3685, '9:3bda50dad0a06f15af5e251fb48db6a6', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13120, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-785::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13121;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-785', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3686, '9:f5e53a08a9694cd00199ad55352cab6e', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13121, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-786::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13122;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-786', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3687, '9:c24d164d8240637a8ee5b67e1492e9c6', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13122, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-787::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13124;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-787', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3688, '9:6147dbc6a5d5c3fc72d3e428e60c5d84', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13124, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-788::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13125;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-788', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3689, '9:a5226f4aa2235a337ab209741237cf15', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13125, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-789::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13126;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-789', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3690, '9:561770c9066c2d46d0e380eed1d308f1', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13126, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-790::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13127;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-790', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3691, '9:c80c2f4baa6fda1a93cc73771a5cb602', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13127, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-791::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13128;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-791', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3692, '9:685e3ce59a473b00ddf9a076b2797949', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_date_gmd_ci_13128, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-792::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_shortname_a_13113;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-792', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3693, '9:7ba4d4c9aa69491b1b534c61519c547f', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_shortname_a_13113, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-793::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_base2_shortname_a_13114;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-793', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3694, '9:83f2f269a30f24311bcb8e7f64d6ff7a', 'dropColumn columnName=plu_plandocument_base2_documentcitation_base2_shortname_a_13114, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-794::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13096;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-794', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3695, '9:53fd3a2453c0cb12a2147421b9cebcea', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13096, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-795::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13097;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-795', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3696, '9:b8e7d01924a1e29306edf74df666afc4', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13097, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-796::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13098;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-796', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3697, '9:f2ff69509fe8b888058a2644db033ed9', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13098, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-797::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13099;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-797', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3698, '9:cf009363275362e6568c95c75daaf071', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13099, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-798::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13100;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-798', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3699, '9:b82d923470de6f9982838e0b549f9ec6', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13100, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-799::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13101;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-799', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3700, '9:e7806ee0dfea4451e74c535eb808c0ce', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13101, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-800::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13102;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-800', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3701, '9:8097bb8eccbf32d7d7a2802cd79ba12d', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13102, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-801::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13103;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-801', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3702, '9:70814e06e546504bbc7b75eaa1e7a154', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13103, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-802::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_description_a_13104;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-802', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3703, '9:de072004a31ac7889b310067c08332eb', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_description_a_13104, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-803::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_descriptionre_13106;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-803', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3704, '9:1f908eefbc65330cc698efa49dee41dc', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_descriptionre_13106, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-804::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_descriptionre_13107;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-804', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3705, '9:1d3e5a6248e565db62e6add202029d0c', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_descriptionre_13107, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-805::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_descriptionre_13108;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-805', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3706, '9:aad800428839b28405f70cdac8f7919d', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_descriptionre_13108, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-806::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_descriptionre_13109;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-806', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3707, '9:0ca93b1c4fce30f2439f5dab7073f1b8', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_descriptionre_13109, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-807::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_descriptionre_13110;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-807', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3708, '9:21386c8be24b8ef35e02eac58c51896b', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_descriptionre_13110, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-808::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_documentcitation_gml_identifier_at_13111;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-808', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3709, '9:230b989555b90b2aa40015de27261415', 'dropColumn columnName=plu_plandocument_base2_documentcitation_gml_identifier_at_13111, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-809::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_att_13153;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-809', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3710, '9:ea1fab5b6f3bec811bf09f5a4e3405d6', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_att_13153, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-810::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_att_13154;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-810', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3711, '9:18e55a13ece4762bab703ff915623fb2', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_att_13154, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-811::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_att_13155;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-811', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3712, '9:b7e871468dcec55565c95c3aaa0b2733', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_att_13155, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-812::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13157;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-812', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3713, '9:54e98b2de23746a34bed34e046f777fe', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13157, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-813::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13158;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-813', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3714, '9:2ff60837d6280f94ddc1bfcd22ade394', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13158, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-814::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13160;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-814', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3715, '9:f74dbfee6f2605a25891987834b24e03', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13160, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-815::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13161;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-815', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3716, '9:9e335025f4cdac51fa3f13cbd5ce90f5', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13161, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-816::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13162;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-816', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3717, '9:da5c88fae4e248438700e3f7d14127ba', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13162, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-817::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13164;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-817', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3718, '9:3ed2314dc2102548760aa610f893c283', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13164, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-818::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13165;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-818', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3719, '9:70763a76a3e16d60ab0d84f4d73151b4', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13165, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-819::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13166;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-819', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3720, '9:5f25881ce1d90123bd67fe70fa0201f5', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13166, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-820::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13167;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-820', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3721, '9:fba7b6c5feb695f5858c50285acbe23a', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13167, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-821::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_date_gmd_13168;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-821', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3722, '9:270cf792bdf556f7c1fa6265bd7b0495', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_date_gmd_13168, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-822::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_dateente_13173;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-822', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3723, '9:c7c087b6e57a69017f268f907bae732f', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_dateente_13173, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-823::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_dateente_13174;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-823', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3724, '9:09d8658aec4e996fc150cbc07cf825e1', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_dateente_13174, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-824::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_dateente_13175;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-824', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3725, '9:82e9cd933887080eb6b3e3ebd1b88865', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_dateente_13175, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-825::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_dateente_13176;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-825', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3726, '9:8be5ad10f2f16a5a138ec0bab312c395', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_dateente_13176, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-826::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_daterepe_13177;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-826', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3727, '9:f40b12d8fa15cb612d50fef425fec96b', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_daterepe_13177, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-827::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_daterepe_13178;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-827', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3728, '9:c894d8ebd865355dec480fa98b170a79', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_daterepe_13178, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-828::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_daterepe_13179;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-828', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3729, '9:9320cf01059296b0af84537e5aaacdd4', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_daterepe_13179, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-829::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_identifi_13171;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-829', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3730, '9:4dd428f07c7db2eeb8ce1bb5c546a2f4', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_identifi_13171, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-830::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_journalc_13185;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-830', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3731, '9:a51c3467615d84ad61b8f9e246598080', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_journalc_13185, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-831::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_journalc_13186;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-831', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3732, '9:31d96c31a436aeadd077a2be625ee3ae', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_journalc_13186, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-832::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_journalc_13187;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-832', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3733, '9:b1b6074ad0f1daad1cde5136d58842a7', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_journalc_13187, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-833::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_journalc_13188;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-833', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3734, '9:48ac5da188d1c998a28fbc2e483651ad', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_journalc_13188, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-834::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_level_at_13180;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-834', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3735, '9:90181984597f9567b7943f88bab3a371', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_level_at_13180, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-835::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_level_at_13181;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-835', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3736, '9:5473b488cd098cb0d6836d01b2f0b309', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_level_at_13181, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-836::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_level_at_13182;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-836', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3737, '9:ee33b6abbafdbf58d234b52305a22862', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_level_at_13182, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-837::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_official_13172;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-837', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3738, '9:77b80b6efe9575d3fed490a63be58035', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_official_13172, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-838::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_shortnam_13151;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-838', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3739, '9:24f92141cf4db1a56ef3662fe29df6ae', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_shortnam_13151, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-839::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_base2_shortnam_13152;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-839', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3740, '9:254ac04136c45c4106294f8fc276f098', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_base2_shortnam_13152, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-840::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13134;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-840', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3741, '9:2e505459fdb12202d6c270c63b26ee49', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13134, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-841::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13135;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-841', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3742, '9:3dea3a207ea52d06d6fa3a1cbb791d5a', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13135, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-842::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13136;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-842', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3743, '9:6c162881e9f33a6be486f377cddb3a87', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13136, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-843::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13137;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-843', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3744, '9:c9de46dbefc63f6b31f4d3cf33ca9d08', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13137, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-844::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13138;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-844', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3745, '9:89babc84c79f78fd433d371bd12c0b2b', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13138, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-845::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13139;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-845', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3746, '9:91f079f6417a9dd71498ff132de21edc', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13139, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-846::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13140;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-846', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3747, '9:fd5d3168bbc9fa9b2e4e24903086c634', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13140, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-847::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13141;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-847', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3748, '9:1f2fde7ee28b0d43ddd0deb6381ea9db', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13141, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-848::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13142;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-848', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3749, '9:513667d78ee725dfc550495c239edb81', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13142, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-849::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13144;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-849', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3750, '9:587b26e0e1f816e632efc71b18d45ab6', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13144, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-850::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13145;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-850', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3751, '9:f9015ee9e668e300d88c52fafdad4b24', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13145, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-851::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13146;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-851', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3752, '9:87bc35fe61ab3ae751292dda0b780df4', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13146, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-852::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13147;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-852', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3753, '9:6eb5f265c3fface4e86635f95adeff6b', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13147, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-853::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_descriptio_13148;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-853', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3754, '9:a8c2c5ca0626ab4ee8969793cf185467', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_descriptio_13148, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-854::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation DROP COLUMN plu_plandocument_base2_legislationcitation_gml_identifier_13149;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-854', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3755, '9:093bfe7bf6fb8df95ecfb6f61019d3a0', 'dropColumn columnName=plu_plandocument_base2_legislationcitation_gml_identifier_13149, tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-855::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_specificpresence_lunom_specificpresence_lunom_ordered_13200;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-855', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3756, '9:cc33262e43abe18adb145f9dfabf2c8e', 'dropColumn columnName=plu_specificpresence_lunom_specificpresence_lunom_ordered_13200, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-856::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_specificpresence_lunom_specificpresence_lunom_ordered_13201;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-856', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3757, '9:21ba9990c815dff450f4de0e72be5357', 'dropColumn columnName=plu_specificpresence_lunom_specificpresence_lunom_ordered_13201, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-857::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_specificpresence_lunom_specificpresence_lunom_ordered_13202;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-857', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3758, '9:224b6ef74f884392a95664848ffb60c3', 'dropColumn columnName=plu_specificpresence_lunom_specificpresence_lunom_ordered_13202, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-858::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_specificpresence_lunom_specificpresence_lunom_ordered_13203;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-858', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3759, '9:c66d11aa3aa64f31a8de946227e774f4', 'dropColumn columnName=plu_specificpresence_lunom_specificpresence_lunom_ordered_13203, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-859::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement DROP COLUMN plu_specificpresence_lunom_specificpresence_lunom_ordered_13204;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-859', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3760, '9:33260525c2513387306a3d9c724408cb', 'dropColumn columnName=plu_specificpresence_lunom_specificpresence_lunom_ordered_13204, tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-860::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_gco_9511;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-860', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3761, '9:f3335ef0b4df4489d46ea45f70e9c247', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_gco_9511, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-861::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_gco_9687;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-861', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3762, '9:b629a367e70a3bd765faa2b97bc2b5a6', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_gco_9687, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-862::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_attr_9512;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-862', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3763, '9:fcafc15c318572aa1dd24cbb01c0a403', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_attr_9512, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-863::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_attr_9688;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-863', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3764, '9:cfe356283d88ed766c9dbbeb67a3fde4', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_attr_9688, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-864::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9513;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-864', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3765, '9:bfc73a79c90e54a53d4bf01bcd669ee6', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9513, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-865::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9514;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-865', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3766, '9:3cd46a879ef4bbaa2c61887c6ee6a34e', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9514, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-866::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9515;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-866', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3767, '9:d857cd1624cf49f62d34adbb94da0912', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9515, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-867::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9516;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-867', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3768, '9:4a094d4351205a3253c1ed68c797c9dd', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9516, tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-868::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9689;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-868', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3769, '9:3c131e6757614af64dd27e1d3d199db7', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9689, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-869::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9690;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-869', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3770, '9:cd5392e8ffa5982ce22191581f2c4b0a', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9690, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-870::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9691;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-870', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3771, '9:a8a47b6b2295b59399c00b15a84e2fed', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9691, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-871::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date DROP COLUMN tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9692;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-871', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3772, '9:1dc5ea805fa86d0a6dfe5d313df07d57', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gmd__9692, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-872::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gco__9662;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-872', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3773, '9:8799d336cc14abbd90fc672e61644cb6', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gco__9662, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-873::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gco__9837;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-873', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3774, '9:6ab6910ae1cd34700a7b4a5009fef6cf', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gco__9837, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-874::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_chara_9663;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-874', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3775, '9:08031edb0f71d12840e776fded4e1fa2', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_chara_9663, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-875::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_chara_9838;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-875', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3776, '9:155e3988187123a4f8430b5a31697941', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_chara_9838, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-876::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_nilr_9517;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-876', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3777, '9:758217c6322502df58c87888c118fb2e', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_nilr_9517, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-877::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_nilr_9693;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-877', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3778, '9:acc99ed12dd33847de0b4f71e2bd3b80', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_nilr_9693, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-878::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9694;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-878', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3779, '9:0b379cf38f170167ad1c57bd701c0746', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9694, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-879::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9695;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-879', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3780, '9:8d382757b54ec166bf753dbd39713cd5', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9695, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-880::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9696;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-880', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3781, '9:e0adc79e233c182027a47e1dbb75ffaa', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9696, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-881::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9697;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-881', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3782, '9:4f20e544cbe266822015b579227f73aa', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9697, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-882::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9698;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-882', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3783, '9:0bdc7cbb3ceaef6c379d78b9d2412210', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9698, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-883::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9699;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-883', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3784, '9:2532b42203294f2eba30bf4f6b0c48eb', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9699, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-884::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9700;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-884', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3785, '9:0133af954ee579ebfbedf518a5909420', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9700, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-885::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9701;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-885', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3786, '9:c0bf186aeb0ab11cbb8c2cd5b0f9317c', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9701, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-886::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9702;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-886', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3787, '9:0aaed377af16907cd82e31413860ae9f', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_authori_9702, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-887::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code_at_9703;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-887', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3788, '9:f9f20386e9a7594ca366a49e4624df86', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code_at_9703, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-888::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier DROP COLUMN tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code_gc_9704;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-888', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3789, '9:1c1bc17100e7af942c6ffe42760b7c52', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code_gc_9704, tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-889::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_othercitationdetails_attr_9660;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-889', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3790, '9:74ab8430f67ab5ea763edcfc990e0f5c', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_othercitationdetails_attr_9660, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-890::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_othercitationdetails_attr_9835;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-890', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3791, '9:0327b1a3136c3cef70a53cb25c1ccdee', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_othercitationdetails_attr_9835, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-891::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_othercitationdetails_gco__9661;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-891', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3792, '9:d8a509215871dd3f3d904ea3f37b7948', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_othercitationdetails_gco__9661, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-892::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_othercitationdetails_gco__9836;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-892', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3793, '9:23db3f46d30dcd8f957af90b29a24f6e', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_othercitationdetails_gco__9836, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-893::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_attr_9652;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-893', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3794, '9:d9cea03b81e0f7b1f1abacc52ca554b5', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_attr_9652, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-894::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_attr_9827;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-894', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3795, '9:bd7fa5feec22dcd5d9e783fe6541fbec', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_attr_9827, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-895::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9653;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-895', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3796, '9:7f0467145b352ca385b67ae7d4dd4762', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9653, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-896::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9654;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-896', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3797, '9:9e83a8e420ea0d268c8086e85620cc89', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9654, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-897::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9656;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-897', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3798, '9:697db3055797435300315a8618d00cbc', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9656, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-898::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9657;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-898', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3799, '9:47e0a90f320e7afd57ea9eb4f960f0c6', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9657, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-899::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9658;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-899', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3800, '9:6d9d683f86fe0864b80fc4f2aae7ace7', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9658, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-900::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9659;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-900', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3801, '9:a5acb467c10c5a0a73d015cdebc3a2bf', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9659, tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-901::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9828;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-901', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3802, '9:28c92890ae004f9ef242be508f99077b', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9828, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-902::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9829;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-902', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3803, '9:55024a59d7ff6f9292bd8b3bc77c0373', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9829, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-903::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9831;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-903', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3804, '9:ca9f94bad2005399020302a4d8fea5e7', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9831, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-904::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9832;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-904', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3805, '9:cbc5247c0e9bb03f91036e6152ee17c9', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9832, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-905::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9833;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-905', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3806, '9:9bd8d992fe70086a0503b6c6c2a16697', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9833, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-906::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority DROP COLUMN tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9834;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-906', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3807, '9:6b9d09ea4b65a9b4243fdad425bd010c', 'dropColumn columnName=tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gmd__9834, tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-907::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_language_attr_nil_63;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-907', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3808, '9:ec9d1c2c81b31c9580494f253e99b445', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_language_attr_nil_63, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-908::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_language_attr_xsi_64;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-908', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3809, '9:19c5e555526ca858f8216097fcb4e225', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_language_attr_xsi_64, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-909::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_namestatus_attr_g_71;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-909', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3810, '9:50bb34609c62e27204e8590d22bca79f', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_namestatus_attr_g_71, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-910::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_namestatus_attr_n_70;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-910', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3811, '9:fd15a3a3dc388b8878976330cf8b1677', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_namestatus_attr_n_70, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-911::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_namestatus_attr_o_69;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-911', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3812, '9:21451fe698c6722d596bbeccc586fd61', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_namestatus_attr_o_69, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-912::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_namestatus_attr_x_72;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-912', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3813, '9:d7d8cee0844706acb565e374f03b0ae9', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_namestatus_attr_x_72, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-913::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_nativeness_attr_g_67;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-913', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3814, '9:8f1aed4adcf3d1b3d5ed30e1cd05e47d', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_nativeness_attr_g_67, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-914::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_nativeness_attr_n_66;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-914', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3815, '9:fb5d3a7a54475d8900a018ca09699254', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_nativeness_attr_n_66, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-915::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_nativeness_attr_o_65;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-915', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3816, '9:580b07e27eb2970eb66046545d1afbae', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_nativeness_attr_o_65, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-916::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost DROP COLUMN tn_geographicalname_gn_geographicalname_gn_nativeness_attr_x_68;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-916', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3817, '9:733804ebc2cc3ddc5bae450955c204ca', 'dropColumn columnName=tn_geographicalname_gn_geographicalname_gn_nativeness_attr_x_68, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-58::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45934 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_l_45936 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_l_45937 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_l_45935 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45939 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45940 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45941 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45942 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45943 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45944 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45946 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45947 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45948 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45949 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45950 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_n_45951 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45953 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45954 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45952 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45956 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45957 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45960 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45961 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45959 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45963 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45964 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_p_45962 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45975 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45976 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45977 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45978 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45979 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45980 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45982 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45983 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45984 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45985 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45986 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_g_45987 TEXT, CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres__pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-58', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3818, '9:6498f811f8f3aaab78fe1cb4882d78c3', 'createTable tableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45934', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-59::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45965 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45967 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45969 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45970 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45968 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45972 BOOLEAN, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45973 TEXT, ad_addresslocator_ad_locatorname_gn_geographicalname_gn_s_45971 TEXT, CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-59', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3819, '9:70b7e9f6eea5d490068be118171872aa', 'createTable tableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45965', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-60::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.ad_postaldescriptor_ad_postname_gn_geographicalname_gn_sp_91801 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, gn_geographicalname_gn_spellingofname_gn_text TEXT, gn_geographicalname_gn_spellingofname_gn_script_attr_xsi_nil BOOLEAN, gn_geographicalname_gn_spellingofname_gn_script_attr_nilreason TEXT, gn_geographicalname_gn_spellingofname_gn_script TEXT, gn_geographicalname_gn_spellingofname_gn_transliterations_91802 BOOLEAN, gn_geographicalname_gn_spellingofname_gn_transliterations_91803 TEXT, gn_geographicalname_gn_spellingofname_gn_transliterationscheme TEXT, CONSTRAINT ad_postaldescriptor_ad_postname_gn_geographicalname_gn_sp__pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-60', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3820, '9:96e213059cafb0ef229dfff01c5a4a69', 'createTable tableName=ad_postaldescriptor_ad_postname_gn_geographicalname_gn_sp_91801', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-61::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.au_administrativeunit_au_residenceofauthority_au_residen_321496 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321498 TEXT, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321500 BOOLEAN, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321501 TEXT, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321499 TEXT, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321503 BOOLEAN, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321504 TEXT, au_residenceofauthority_au_name_gn_geographicalname_gn_s_321502 TEXT, CONSTRAINT au_administrativeunit_au_residenceofauthority_au_residen_3_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-61', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3821, '9:38dea41b7d91cce0c905c31a8f11ddb6', 'createTable tableName=au_administrativeunit_au_residenceofauthority_au_residen_321496', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-62::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.net_network_net_geographicalname_gn_geographicalname_gn__137785 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, gn_geographicalname_gn_spellingofname_gn_text TEXT, gn_geographicalname_gn_spellingofname_gn_script_attr_xsi_nil BOOLEAN, gn_geographicalname_gn_spellingofname_gn_script_attr_nilreason TEXT, gn_geographicalname_gn_spellingofname_gn_script TEXT, gn_geographicalname_gn_spellingofname_gn_transliteration_137786 BOOLEAN, gn_geographicalname_gn_spellingofname_gn_transliteration_137787 TEXT, gn_geographicalname_gn_spellingofname_gn_transliterationscheme TEXT, CONSTRAINT net_network_net_geographicalname_gn_geographicalname_gn__1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-62', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3822, '9:c588b78cd31096aa8f8d26fa1fbe7dfd', 'createTable tableName=net_network_net_geographicalname_gn_geographicalname_gn__137785', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-63::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183828 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_legislationcitation_base2_legislationcitation_attr_x_183829 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183830 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183831 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183832 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183833 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183834 TEXT, plu_legislationcitation_base2_legislationcitation_attr_x_183835 TEXT, plu_legislationcitation_base2_legislationcitation_attr_n_183836 TEXT, plu_legislationcitation_base2_legislationcitation_attr_g_183837 TEXT, plu_legislationcitation_base2_legislationcitation_attr_about TEXT, plu_legislationcitation_base2_legislationcitation_gml_ge_183839 TEXT, plu_legislationcitation_base2_legislationcitation_gml_ge_183838 TEXT, CONSTRAINT plu_officialdocumentation_plu_legislationcitation_base2__1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-63', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3823, '9:683acc46850046f82c5ab13ad97ab501', 'createTable tableName=plu_officialdocumentation_plu_legislationcitation_base2__183828', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-64::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183858 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_legislationcitation_base2_legislationcitation_attr_c_183859 TEXT, plu_legislationcitation_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_legislationcitation_base2___pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-64', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3824, '9:73e6d3093c77dad1caab6834d6b88f81', 'createTable tableName=plu_officialdocumentation_plu_legislationcitation_base2__183858', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-65::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183879 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_legislationcitation_base2_legislationcitation_attr_xsi_nil BOOLEAN, plu_legislationcitation_base2_legislationcitation_attr_n_183880 TEXT, plu_legislationcitation_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_legislationcitation_base2___pkey2 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-65', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3825, '9:2e320389ebb9ab704c50be13a6c84b4d', 'createTable tableName=plu_officialdocumentation_plu_legislationcitation_base2__183879', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-66::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183881 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_legislationcitation_base2_legislationcitation_attr_xsi_nil BOOLEAN, plu_legislationcitation_base2_legislationcitation_attr_n_183882 TEXT, plu_legislationcitation_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_legislationcitation_base2___pkey3 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-66', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3826, '9:6d1c43bf91499395adcde26af78bdb97', 'createTable tableName=plu_officialdocumentation_plu_legislationcitation_base2__183881', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-67::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183904 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_documentcitation_attr_xlink_type TEXT, plu_plandocument_base2_documentcitation_attr_xlink_href TEXT, plu_plandocument_base2_documentcitation_attr_xlink_role TEXT, plu_plandocument_base2_documentcitation_attr_xlink_arcrole TEXT, plu_plandocument_base2_documentcitation_attr_xlink_title TEXT, plu_plandocument_base2_documentcitation_attr_xlink_show TEXT, plu_plandocument_base2_documentcitation_attr_xlink_actuate TEXT, plu_plandocument_base2_documentcitation_attr_nilreason TEXT, plu_plandocument_base2_documentcitation_attr_gml_remoteschema TEXT, plu_plandocument_base2_documentcitation_attr_about TEXT, plu_plandocument_base2_documentcitation_gml_genericmetad_183905 TEXT, plu_plandocument_base2_documentcitation_gml_genericmetadata TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_documen_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-67', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3827, '9:ec449317bf49b67e54f31e2b06e92878', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_documen_183904', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-68::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183922 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_documentcitation_attr_codespace TEXT, plu_plandocument_base2_documentcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_documen__pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-68', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3828, '9:9fcd35b69864513f8def689b7e872c03', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_documen_183922', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-69::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183939 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_documentcitation_attr_xsi_nil BOOLEAN, plu_plandocument_base2_documentcitation_attr_nilreason TEXT, plu_plandocument_base2_documentcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_documen__pkey2 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-69', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3829, '9:39dab6cb978cc08cf22433fc1bb8ab99', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_documen_183939', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-70::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183940 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_documentcitation_attr_xsi_nil BOOLEAN, plu_plandocument_base2_documentcitation_attr_nilreason TEXT, plu_plandocument_base2_documentcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_documen__pkey3 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-70', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3830, '9:6aea418f8444434e05559f5b4b26c227', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_documen_183940', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-71::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183941 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_legislationcitation_attr_xlink_type TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_href TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_role TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_arcrole TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_title TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_show TEXT, plu_plandocument_base2_legislationcitation_attr_xlink_actuate TEXT, plu_plandocument_base2_legislationcitation_attr_nilreason TEXT, plu_plandocument_base2_legislationcitation_attr_gml_remo_183942 TEXT, plu_plandocument_base2_legislationcitation_attr_about TEXT, plu_plandocument_base2_legislationcitation_gml_genericme_183943 TEXT, plu_plandocument_base2_legislationcitation_gml_genericmetadata TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_legisla_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-71', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3831, '9:e5a30afbdb6b29e64b55554c2ab4f333', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183941', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-72::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183960 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_legislationcitation_attr_codespace TEXT, plu_plandocument_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_legisla__pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-72', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3832, '9:fee8239127c9aaf741afa611609d8cc2', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183960', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-73::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183979 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_legislationcitation_attr_xsi_nil BOOLEAN, plu_plandocument_base2_legislationcitation_attr_nilreason TEXT, plu_plandocument_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_legisla__pkey2 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-73', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3833, '9:db91d1a0fe8115d9eade4200d56a3dd8', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183979', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-74::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183980 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_plandocument_base2_legislationcitation_attr_xsi_nil BOOLEAN, plu_plandocument_base2_legislationcitation_attr_nilreason TEXT, plu_plandocument_base2_legislationcitation TEXT, CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_legisla__pkey3 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-74', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3834, '9:6e59cbcf44edd91ff67c8f00a756dcd1', 'createTable tableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183980', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-75::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_zoningelement_plu_hilucspresence_lunom_hilucspresenc_184002 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184004 BOOLEAN, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184005 TEXT, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184006 TEXT, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184007 TEXT, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184008 TEXT, plu_hilucspresence_lunom_hilucspresence_lunom_hilucsperc_184009 INTEGER, CONSTRAINT plu_zoningelement_plu_hilucspresence_lunom_hilucspresenc_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-75', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3835, '9:8012756a7cacbbcd40d14ab4a736db00', 'createTable tableName=plu_zoningelement_plu_hilucspresence_lunom_hilucspresenc_184002', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-76::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.plu_zoningelement_plu_specificpresence_lunom_specificpre_184015 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, plu_specificpresence_lunom_specificpresence_lunom_specif_184018 BOOLEAN, plu_specificpresence_lunom_specificpresence_lunom_specif_184019 TEXT, plu_specificpresence_lunom_specificpresence_lunom_specif_184020 TEXT, plu_specificpresence_lunom_specificpresence_lunom_specif_184021 TEXT, plu_specificpresence_lunom_specificpresence_lunom_specif_184022 TEXT, plu_specificpresence_lunom_specificpresence_lunom_specif_184023 INTEGER, CONSTRAINT plu_zoningelement_plu_specificpresence_lunom_specificpre_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-76', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3836, '9:c69fd5f4b2a293a6019c27d0fddc5dcb', 'createTable tableName=plu_zoningelement_plu_specificpresence_lunom_specificpre_184015', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-77::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137616 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gco_characterstring TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-77', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3837, '9:696a6ae3cdcbb9b37289787c16ce2437', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137616', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-78::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137624 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_xlink_type TEXT, tn_authority_gmd_ci_citation_attr_xlink_href TEXT, tn_authority_gmd_ci_citation_attr_xlink_role TEXT, tn_authority_gmd_ci_citation_attr_xlink_arcrole TEXT, tn_authority_gmd_ci_citation_attr_xlink_title TEXT, tn_authority_gmd_ci_citation_attr_xlink_show TEXT, tn_authority_gmd_ci_citation_attr_xlink_actuate TEXT, tn_authority_gmd_ci_citation_attr_uuidref TEXT, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_attr_id TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_attr_uuid TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137625 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137626 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137627 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137628 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137629 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137630 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137631 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137632 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137633 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code__137635 TEXT, tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code__137636 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-78', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3838, '9:af7add218aad3090ea37963bbffd2d32', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137624', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-79::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_xlink_type TEXT, tn_authority_gmd_ci_citation_attr_xlink_href TEXT, tn_authority_gmd_ci_citation_attr_xlink_role TEXT, tn_authority_gmd_ci_citation_attr_xlink_arcrole TEXT, tn_authority_gmd_ci_citation_attr_xlink_title TEXT, tn_authority_gmd_ci_citation_attr_xlink_show TEXT, tn_authority_gmd_ci_citation_attr_xlink_actuate TEXT, tn_authority_gmd_ci_citation_attr_uuidref TEXT, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_attr_id TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_attr_uuid TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137639 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137640 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137642 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137643 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137645 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137646 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137648 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137649 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137650 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137651 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137652 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137653 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137654 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137655 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137656 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137658 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137659 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137661 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137662 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137663 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137664 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137665 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137666 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137667 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137668 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137669 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137671 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137672 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137680 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137681 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137682 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137683 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137684 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137685 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137686 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137687 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137688 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137690 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137691 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137696 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137697 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137699 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137700 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137702 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137703 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137705 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137706 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137711 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137712 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137713 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137714 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137715 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137716 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137717 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137718 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137719 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137721 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137722 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137724 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137725 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137727 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137728 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137730 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137731 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137733 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137734 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137736 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137737 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137739 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137741 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137742 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137743 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137740 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137745 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137746 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137748 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137749 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137750 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137752 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137753 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137754 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137751 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey2 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-79', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3839, '9:1fe6c2a6686ba0c8a32d91efb1905520', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-80::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137673 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137674 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137675 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey3 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-80', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3840, '9:5c32b5246811a09c0b322df2993a9a07', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137673', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-81::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137676 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137677 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137678 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey4 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-81', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3841, '9:db011ceed1c423b03c778439cc3fe383', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137676', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-82::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137692 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137693 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137694 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey5 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-82', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3842, '9:851097dd9f647bd873eab7e420feec48', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137692', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-83::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137707 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137708 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137709 TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey6 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-83', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3843, '9:fa463a57c4b92f886caf2ebe3d1a0383', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137707', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-84::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137755 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137756 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137757 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137758 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode TEXT, CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd__pkey7 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-84', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3844, '9:6e314c715956726d62760948dcd70475', 'createTable tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137755', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-85::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alter_137793 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gco_characterstring TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alter_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-85', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3845, '9:af391b30be510ffd5bbb4479bf3ff0c3', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alter_137793', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-86::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_xlink_type TEXT, tn_authority_gmd_ci_citation_attr_xlink_href TEXT, tn_authority_gmd_ci_citation_attr_xlink_role TEXT, tn_authority_gmd_ci_citation_attr_xlink_arcrole TEXT, tn_authority_gmd_ci_citation_attr_xlink_title TEXT, tn_authority_gmd_ci_citation_attr_xlink_show TEXT, tn_authority_gmd_ci_citation_attr_xlink_actuate TEXT, tn_authority_gmd_ci_citation_attr_uuidref TEXT, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_attr_id TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_attr_uuid TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137815 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137816 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137818 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137819 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137821 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137822 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137824 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137825 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137826 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137827 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137828 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137829 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137830 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137831 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137832 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137834 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137835 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137837 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137838 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137839 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137840 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137841 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137842 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137843 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137844 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137845 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137847 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137848 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137856 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137857 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137858 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137859 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137860 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137861 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137862 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137863 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137864 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137866 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137867 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137872 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137873 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137875 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137876 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137878 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137879 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137881 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137882 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137887 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137888 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137889 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137890 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137891 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137892 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137893 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137894 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137895 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137897 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137898 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137900 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137901 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137903 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137904 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137906 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137907 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137909 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137910 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137912 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137913 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137915 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137917 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137918 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137919 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137916 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137921 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137922 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137924 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137925 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137926 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137928 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137929 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137930 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137927 TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-86', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3846, '9:ba951472f6cd14c79ffdd889a28d1cca', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-87::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137849 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137850 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137851 TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited__pkey1 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-87', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3847, '9:af3d50389f58f6cee74981dbeed5cc80', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137849', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-88::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137852 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137853 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137854 TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited__pkey2 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-88', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3848, '9:777a4f9a7054715e57235a12fdd2aa60', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137852', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-89::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137868 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137869 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137870 TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited__pkey3 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-89', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3849, '9:b42d58e611e705f8d867b094a431f462', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137868', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-90::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137883 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk INTEGER NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137884 TEXT, tn_authority_gmd_ci_citation_gmd_ci_responsibleparty_gmd_137885 TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited__pkey4 PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-90', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3850, '9:b227b87b6f3ba415990f79a61d1aba9b', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137883', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-91::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_prese_137931 (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, parentfk TEXT NOT NULL, num INTEGER NOT NULL, tn_authority_gmd_ci_citation_attr_gco_nilreason TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137932 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137933 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode_137934 TEXT, tn_authority_gmd_ci_citation_gmd_ci_presentationformcode TEXT, CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_prese_1_pkey PRIMARY KEY (id));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-91', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3851, '9:c1a1421ad6816eea2af5ca20524d8135', 'createTable tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_prese_137931', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-92::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_l_321467 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-92', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3852, '9:06e26fd831af739f9305f8eac0e45679', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-93::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_dimensioningindication ADD plu_dimensioningindicationcharactervalue_plu_indicationr_184027 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-93', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3853, '9:08301ec27a486a25ced357b8d445fbbd', 'addColumn tableName=plu_zoningelement_plu_dimensioningindication', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-94::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_designator ADD ad_addresslocator_ad_locatordesignator_ad_type_attr_gml_r_45933 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-94', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3854, '9:587805cab7ac60f28930729ae8c10b85', 'addColumn tableName=ad_address_ad_locator_ad_addresslocator_ad_designator', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-95::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_l_321468 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-95', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3855, '9:0333ef9fe3baf4d69f6078757b671667', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-96::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_l_321466 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-96', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3856, '9:1eac1781e075a476e40aa33ee9840db0', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-97::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_137953 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-97', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3857, '9:1c531a6eb6a539c0aebcc4ffe11734dd', 'addColumn tableName=cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-98::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_137961 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-98', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3858, '9:308591b1f04536303486b2b485bfefaa', 'addColumn tableName=au_condominium_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-99::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_183826 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-99', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3859, '9:d7314630e0f18b5a3803a3a405f077c4', 'addColumn tableName=gn_namedplace_gn_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-100::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_321464 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-100', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3860, '9:b6c854988100f4a4be00d1291c39156e', 'addColumn tableName=au_administrativeunit_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-101::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321470 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-101', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3861, '9:6c5a373907f0ea2d7224a09caff0d0a0', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-102::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_137954 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-102', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3862, '9:e600b4550ef05231cae82bb036fd7cfe', 'addColumn tableName=cp_cadastralzoning_cp_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-103::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_137962 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-103', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3863, '9:d220bfed2d9a117400088a502c06eba5', 'addColumn tableName=au_condominium_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-104::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_183827 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-104', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3864, '9:7f039e6b80e2d61a39961a809a4fc711', 'addColumn tableName=gn_namedplace_gn_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-105::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name_gn_geographicalname_gn_spelling ADD gn_geographicalname_gn_spellingofname_gn_transliteration_321465 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-105', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3865, '9:1341bc44e21aac7bacecbdcaf7dc7f71', 'addColumn tableName=au_administrativeunit_au_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-106::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321471 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-106', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3866, '9:dccfeb812991e3042f26d41e3887b991', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-107::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321472 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-107', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3867, '9:fea7d8e356ad8c9c57a22f245ba02e5a', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-108::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321473 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-108', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3868, '9:978ff25bcad895f69cbfa4c8374b6400', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-109::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321474 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-109', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3869, '9:e264e3b9abcd35f2135e825fc4f5813d', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-110::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_137611 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-110', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3870, '9:3b7213eeb4a66768fd45e987d1e438c3', 'addColumn tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-111::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_137771 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-111', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3871, '9:b34559b41b24211627793e97c0d34d00', 'addColumn tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-112::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_137788 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-112', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3872, '9:1786a3a1da5f7ea3142d611c66b248d4', 'addColumn tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-113::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_137963 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-113', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3873, '9:4cce3e5a3f81863d9bd5832dea3fe5d8', 'addColumn tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-114::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_137968 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-114', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3874, '9:90d0aa88b3121227b76a368e7432fd75', 'addColumn tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-115::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_184028 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-115', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3875, '9:d812a31ed6d806d367ed2f05c0083a42', 'addColumn tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-116::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_n_184033 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-116', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3876, '9:a26e1b784290a1050c7250e007244adf', 'addColumn tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-117::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321475 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-117', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3877, '9:f57ec8326617a1ca881e668e7bc37763', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-118::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_137612 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-118', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3878, '9:e174154991a714052920369513dd6e61', 'addColumn tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-119::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_137772 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-119', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3879, '9:3a0ea733d0235c585db61fe2a9948016', 'addColumn tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-120::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_137789 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-120', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3880, '9:97a2356182a9141a6f76944e423671d7', 'addColumn tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-121::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_137964 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-121', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3881, '9:6630e6ee8164bac98fffc425ff4c4e6e', 'addColumn tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-122::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_137969 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-122', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3882, '9:8dce1314f6ddc42f4d02ca138f6ff468', 'addColumn tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-123::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_184029 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-123', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3883, '9:4d43281155de9e43a62b1c7faa68962f', 'addColumn tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-124::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref ADD net_simplelinearreference_net_applicabledirection_attr_g_184034 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-124', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3884, '9:ca2154793e13bde9e4b5a14c0d6160d2', 'addColumn tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-125::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321477 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-125', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3885, '9:6ef157086d0cfce21f9f24c26a544dc5', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-126::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_g_137617 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-126', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3886, '9:0d0885f9607ec639be0f4fd9dfd7dd0a', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-127::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_date_attr_g_137794 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-127', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3887, '9:b4a217c38268e29a734307b89eecbbef', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-128::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137801 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-128', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3888, '9:95714f956d9e997234d1d388e342e2de', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-129::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321478 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-129', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3889, '9:c589b1ad16d9f1383dc34ffafe45e203', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-130::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137802 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-130', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3890, '9:6c2410d4180651ce31d59faae8ddbc81', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-131::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321479 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-131', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3891, '9:c1b54bd6eb059b4e9c791508f6432947', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-132::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137803 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-132', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3892, '9:88a02d23a8d896cded0d2eea4d5487f4', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-133::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_language_attr_xsi_63 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-133', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3893, '9:33a95f2e5f397a58bfa72d4322d2d454', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-134::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321480 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-134', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3894, '9:847df09c9d86b9265772e8e9765f4664', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-135::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_at_137618 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-135', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3895, '9:94e86c7f883a35df93249268376ea4e9', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-136::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_at_137795 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-136', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3896, '9:7118e822ee00f88296e68fede82a6b76', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-137::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137804 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-137', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3897, '9:e0c458d8f5e02d246734511694040b41', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-138::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_language_attr_nil_64 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-138', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3898, '9:9ba7c7190e3e79b92526a2de0bf728ea', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-139::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321481 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-139', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3899, '9:90448509eecf73fc1c9789f272d0c524', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-140::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137620 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-140', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3900, '9:10737ca67ff1b4b99a87d0ffc0a33170', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-141::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137797 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-141', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3901, '9:66d7554395ab4aad76bb9a61feeff2cb', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-142::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137805 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-142', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3902, '9:c099ba569f3db3cacca78e2e7e8c0377', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-143::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_n_321482 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-143', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3903, '9:fc1d81b1a0cf904cd75a2b5bca1608f6', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-144::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmapu_115 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-144', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3904, '9:8e6c85499e7a3336b0a2b54e5e05cde9', 'addColumn tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-145::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137621 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-145', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3905, '9:bc35f56c4028533d407344e99be42fc8', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-146::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137798 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-146', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3906, '9:7edbe92b821ec6555b9a3aeed1e245c3', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-147::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137806 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-147', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3907, '9:3302ebd7076d49af08e9be54945c546f', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-148::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_nativeness_attr_x_65 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-148', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3908, '9:f109d9b6de4434735eeb1a26fbe72f8e', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-149::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_s_321484 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-149', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3909, '9:fe406a1530b351258a3ad8e6a1ee991d', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-150::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183781 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-150', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3910, '9:4a7f99253357339965e84eb0cc6bda11', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-151::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmapu_116 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-151', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3911, '9:ed13d00693538063f994071e626b7afc', 'addColumn tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-152::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183841 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-152', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3912, '9:b8fc3db7424cc091a20e4f9cf743d342', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-153::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137622 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-153', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3913, '9:780dd89b54a64efea3d689223fd362a6', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-154::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137799 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-154', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3914, '9:be4e060257caadedb3c4df3f888f0a47', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-155::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137807 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-155', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3915, '9:309228b4f82716dfdc9e0121eb2dc9be', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-156::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_nativeness_attr_o_66 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-156', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3916, '9:c35047177e6e8cfcd57f244761d70856', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-157::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_s_321485 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-157', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3917, '9:502c52813b5af2ade6174e84e5e26459', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-158::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183782 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-158', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3918, '9:75c58c0025143bbd356fb79a640bfd75', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-159::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_supplementaryregulation ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmapuri TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-159', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3919, '9:862e7b5f368b26cbdcf4ba6ca2939ab6', 'addColumn tableName=plu_supplementaryregulation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-160::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183842 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-160', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3920, '9:d6a94a98911449646ad8c6a82ae1dd33', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-161::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137619 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-161', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3921, '9:4660dfeabafa08ab1df840bba01fed3a', 'addColumn tableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-162::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date ADD tn_authority_gmd_ci_citation_gmd_ci_date_gmd_datetype_gm_137796 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-162', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3922, '9:56cec11944f3fc1c666ad33acb8aab98', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_date', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-163::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137808 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-163', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3923, '9:05054c094034c57e3077049a672abc44', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-164::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_nativeness_attr_n_67 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-164', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3924, '9:6a4fce507f0dc6bc50f130717871832b', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-165::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_s_321483 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-165', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3925, '9:fdc2df1c952be0295c8c3278266ed60a', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-166::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183783 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-166', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3926, '9:d07d02b13d0cfc2f27d25208e0d5a0f6', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-167::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_hilucspresence_lunom_hilucspresence_lunom_orderedlis_183999 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-167', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3927, '9:361fd7aec184bc324c42a2f7e20711ea', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-168::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183843 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-168', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3928, '9:ba93459dc3ae2a2386a9dbcce05811ad', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-169::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_autho_137809 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-169', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3929, '9:6487548f69899efc3dc33e386baf9f80', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-170::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_nativeness_attr_g_68 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-170', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3930, '9:bbe27fc57d717922d5f5dcddf54b25cf', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-171::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321487 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-171', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3931, '9:c188f3c88ec9deb0d55c00eb0eee3bc3', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-172::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183821 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-172', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3932, '9:f0c6cf263c287895eda5ecc036a9cecd', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-173::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321459 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-173', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3933, '9:054de833aa038a7aad5e7bc3c7f76fed', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-174::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91796 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-174', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3934, '9:9deda2f0bbd34d9a32a1d98ce837af26', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-175::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183784 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-175', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3935, '9:c441552ff77e660315cae6f45d80c94a', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-176::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_hilucspresence_lunom_hilucspresence_lunom_orderedlis_184000 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-176', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3936, '9:0491b4a611d79ba5a51336139ad02e83', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-177::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183844 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-177', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3937, '9:a31cb5ce2175dcad7ebcaf7ca5a342c9', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-178::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code__137811 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-178', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3938, '9:9cd3fa8d2f4665a45cf0dee6da6f1395', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-179::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321488 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-179', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3939, '9:1a2a3ee1e27269b1f2d47cf1eebd4dab', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-180::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183822 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-180', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3940, '9:2d9980c137df191d4cb829cb963b3b3a', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-181::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321460 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-181', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3941, '9:14f315bb9effe4c751d8881796cb1a9f', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-182::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91797 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-182', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3942, '9:2e5c6b8b8ed8526a6f45be35b97f5b19', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-183::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183785 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-183', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3943, '9:8a50b7881d2a0505f41c52cbe82eb2a4', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-184::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_hilucspresence_lunom_hilucspresence_lunom_orderedlis_184001 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-184', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3944, '9:fde5dc238ed7df9e8a58bce62e355cbb', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-185::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183845 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-185', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3945, '9:d5780b5e54075ac8b34a8c18348e4caa', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-186::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier ADD tn_authority_gmd_ci_citation_gmd_md_identifier_gmd_code__137812 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-186', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3946, '9:18699f32978cfe66df121298ec5ea352', 'addColumn tableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_identifier', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-187::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321491 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-187', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3947, '9:a45aaadd01f35b032b852574a4dae2e8', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-188::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137780 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-188', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3948, '9:c08718940806859e854985995233fc83', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-189::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137948 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-189', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3949, '9:e4465bab5623c99f5656ac8038d6a4de', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-190::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137956 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-190', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3950, '9:0763d03a674e8f1caf726bda93e1bc05', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-191::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183820 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-191', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3951, '9:41dd925a66ade9d746b1d2920960dc77', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-192::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321458 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-192', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3952, '9:573e28517fa3f60b1725fafb74dd0a06', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-193::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91795 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-193', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3953, '9:0c181a1c5898555c38bafb5982d9cc60', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-194::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183786 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-194', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3954, '9:0fec14579731334498fa1a28e2f4c375', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-195::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183846 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-195', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3955, '9:b72dbccfc053543299ec123fc6fff10f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-196::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_namestatus_attr_x_69 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-196', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3956, '9:e9f9f2caf23daa0282a3efa9145ba4b8', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-197::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321492 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-197', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3957, '9:f8e3d0e1507f05d85fdaa652f317a63c', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-198::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137781 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-198', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3958, '9:06493a805e6da7b0b3314bb45eeab86e', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-199::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137949 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-199', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3959, '9:8a65efc7cccc47483024601c34406986', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-200::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137957 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-200', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3960, '9:45b0de85e57aedf0c09e0ae62767c17d', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-201::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183824 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-201', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3961, '9:03cde636b070cf7b720ea20c2f16e0b7', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-202::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321462 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-202', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3962, '9:8762039965c4bf6c640ecadccace2f2b', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-203::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91799 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-203', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3963, '9:c084e181f7062cdd904d6efdadb7ba79', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-204::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183787 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-204', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3964, '9:b5018a238460493ef38c55e807c15de3', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-205::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183847 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-205', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3965, '9:ed8ad39e477667976feebddd54a8c8e0', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-206::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_namestatus_attr_o_70 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-206', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3966, '9:5179e1744bed225e95abf8eb40722a4b', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-207::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321490 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-207', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3967, '9:46a6da229306aaae673f02967f0bf73e', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-208::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137779 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-208', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3968, '9:352dc27b0ab0fa4c2737db52d243ba4a', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-209::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137947 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-209', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3969, '9:d1edc64e6c6012be6a314595db832c63', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-210::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137955 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-210', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3970, '9:79163a97346d77c9c992db7977014ce3', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-211::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183825 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-211', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3971, '9:aa93b9a874610282a4dca13605b31fc0', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-212::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321463 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-212', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3972, '9:42ac2ef8400bcdffaf098637edc7c959', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-213::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91800 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-213', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3973, '9:11ebf11c939dd5de820406bd0453e166', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-214::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183788 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-214', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3974, '9:97384cba76ead02f0c6123982a9fa351', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-215::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183848 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-215', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3975, '9:6e620d22a111a8c4b99496060cf9024f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-216::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_ni_137623 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-216', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3976, '9:c092777a06de89cafc4181d2b0eec3d7', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-217::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_editiondate_attr_gco_ni_137800 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-217', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3977, '9:8a51b8f72f36565e14d6d4ce8d59c19d', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-218::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_namestatus_attr_n_71 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-218', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3978, '9:a049a956414daec2ff67835751778b73', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-219::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321494 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-219', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3979, '9:0996af633c3b5cf5de75fcb92841078b', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-220::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137783 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-220', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3980, '9:707e0ba62c277e45d5eff9234a4b389f', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-221::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137951 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-221', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3981, '9:44806b8d7901f71ee642fa6c2d6cc79d', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-222::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137959 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-222', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3982, '9:bc8346908fc26b5188cdded9fb3934d4', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-223::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace_gn_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_183823 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-223', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3983, '9:c1bb1c5cd09e6cd60da980a481e3fd8c', 'addColumn tableName=gn_namedplace_gn_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-224::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_321461 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-224', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3984, '9:46a56f54d2d2c284b96a3b232405afa5', 'addColumn tableName=au_administrativeunit_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-225::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofna_91798 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-225', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3985, '9:8f6c7faa1471ff0f3aafbfcbe0b72092', 'addColumn tableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-226::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183789 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-226', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3986, '9:9b34e14e802b6ed0689579974179a00a', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-227::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183849 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-227', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3987, '9:f4c6bcfbf321c3eb84552be23e9bf853', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-228::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ADD tn_geographicalname_gn_geographicalname_gn_namestatus_attr_g_72 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-228', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3988, '9:1967b10ff1c2f6c4bfebb5db94006b1a', 'addColumn tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-229::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321495 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-229', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3989, '9:f6efeaef1527dc2a08730cabf0d6165d', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-230::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137784 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-230', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3990, '9:72f4dc19f7d8f86331ea1456122fc16c', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-231::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137952 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-231', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3991, '9:c5d79790ca3ff8fc1762f05f4c14c30e', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-232::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137960 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-232', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3992, '9:7b68165b665bdf9ce35f3456d7ae708b', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-233::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183791 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-233', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3993, '9:06033fe5f34f8cd7c50e20a9e58ad0ac', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-234::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183840 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-234', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3994, '9:f01f11c5fe7aaecb18b26b3ce8d73171', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-235::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_specificpresence_lunom_specificpresence_lunom_ordere_184010 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-235', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3995, '9:8843a2b514f7769913e7af67bd50dcdd', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-236::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_p_321493 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-236', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3996, '9:7838a2d177ca5f8cd633e8b8baeeef43', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-237::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137782 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-237', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3997, '9:644eeaf6c31a3d5b91054a5fa22d27bd', 'addColumn tableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-238::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.cp_cadastralzoning_cp_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137950 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-238', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3998, '9:c2c8a40dbd5d80ea0101f943eb50c638', 'addColumn tableName=cp_cadastralzoning_cp_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-239::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_condominium_au_name ADD gn_geographicalname_gn_pronunciation_gn_pronunciationofn_137958 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-239', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 3999, '9:18c6db9e240d78ee23d4269f21d0456a', 'addColumn tableName=au_condominium_au_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-240::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183792 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-240', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4000, '9:868cff793cc93e4b314ffcabab3a3d5e', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-241::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183851 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-241', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4001, '9:b12e31e37be3b7b9ad817f6ad5fb9b37', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-242::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_specificpresence_lunom_specificpresence_lunom_ordere_184011 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-242', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4002, '9:a259cdb0609a75c33ed4ab1d5441ea89', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-243::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321506 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-243', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4003, '9:f3cc9f05def95761c62aaa5ee3caaf98', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-244::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183794 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-244', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4004, '9:1546dda1e1ab09e7c1764944d4b39017', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-245::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_137613 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-245', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4005, '9:b463d7486e09fa8f378eee4248fda9c6', 'addColumn tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-246::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_137773 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-246', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4006, '9:a1bbec01241b2c938ede0bff44ae9cf5', 'addColumn tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-247::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_137790 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-247', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4007, '9:10c4e9b534dc7c5f12716b09536907d5', 'addColumn tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-248::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_137965 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-248', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4008, '9:3a1e835c03d20f324c211b62b19b0abf', 'addColumn tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-249::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_137970 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-249', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4009, '9:1271e50983f534410c387cf70896760a', 'addColumn tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-250::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_184030 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-250', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4010, '9:f24ed9db8f1b3dcc6da61a8c67285076', 'addColumn tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-251::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_ni_184035 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-251', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4011, '9:22d96cc4d67b15f945943b6fdedceb20', 'addColumn tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-252::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183852 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-252', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4012, '9:4b0bd23780d63130f7680e4d303d7a90', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-253::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_specificpresence_lunom_specificpresence_lunom_ordere_184012 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-253', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4013, '9:baae91f02205ebc9b50850e7160f52e2', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-254::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321507 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-254', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4014, '9:02e5dd9811686ba8d7dd8a4372d50c2f', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-255::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183795 INTEGER;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-255', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4015, '9:39bf65825356d0d2e76693c3e9e26c0d', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-256::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_137614 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-256', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4016, '9:e1e1af2c458d3d20d9a31573ffb571f7', 'addColumn tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-257::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_137774 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-257', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4017, '9:469b8738521706b46eb1b9339613a592', 'addColumn tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-258::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_137791 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-258', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4018, '9:63a10d0cb17c2f683fe8877e71bf174d', 'addColumn tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-259::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_137966 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-259', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4019, '9:98b0be79a762db3625c53d5c680d0465', 'addColumn tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-260::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_137971 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-260', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4020, '9:c3a32be0e8ae46b898691f4aa56bfc53', 'addColumn tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-261::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_184031 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-261', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4021, '9:83c38ab7b11f2a56a3a23336524fee94', 'addColumn tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-262::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref ADD net_simplepointreference_net_applicabledirection_attr_gm_184036 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-262', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4022, '9:68213549ef8d3151f6d8e6196c8bf202', 'addColumn tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-263::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183853 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-263', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4023, '9:dd5c417d3c198d1285d1f250f782a946', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-264::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_specificpresence_lunom_specificpresence_lunom_ordere_184013 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-264', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4024, '9:caf4830ea5ba0f766ad281ad9810538a', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-265::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321508 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-265', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4025, '9:c4e186dba0746fa1985c10451892dc5b', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-266::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183797 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-266', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4026, '9:ee2059e113d9961ad06b5164eeaaeb6f', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-267::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183854 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-267', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4027, '9:3fdc2f5e625560b20732bc63e010ae73', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-268::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_specificpresence_lunom_specificpresence_lunom_ordere_184014 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-268', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4028, '9:55a6bff594348bf4f56f8da17d38bfd0', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-269::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321509 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-269', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4029, '9:b85296898ff5dd531f2652243c55bb53', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-270::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183799 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-270', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4030, '9:bdb515f43c6099b910bac83a01a7d75e', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-271::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_de_183855 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-271', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4031, '9:50e6c7dd503f290380830e5d59a3653b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-272::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321510 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-272', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4032, '9:9ffaa55a2fc80007abf14c03e6d546a1', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-273::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_leastdetailedviewingresolution_gmd_md_resolution_gmd__183798 numeric;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-273', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4033, '9:4b35017fb5ace3018c91d640cca4d0f8', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-274::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_id_183857 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-274', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4034, '9:ce566d5762ff01ee62004e3e0a9b0f0e', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-275::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321511 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-275', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4035, '9:0595169020ca41463ce7c814b84fab7d', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-276::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_gml_id_183856 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-276', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4036, '9:a52d9ca371bbe14baae1e943db52303d', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-277::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321513 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-277', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4037, '9:2ba36aa7e28b3c5a37523ddb57e10802', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-278::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321514 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-278', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4038, '9:3e75c1f0873058a302942e35b7709c56', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-279::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183861 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-279', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4039, '9:37980ad9472250dd96489e22baa862ff', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-280::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321515 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-280', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4040, '9:ab439716c6df8a2edc15a226246fccdf', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-281::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183801 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-281', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4041, '9:1e21d88c71b396cf0bf954eff97d2b21', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-282::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183862 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-282', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4042, '9:085c7d4620edad29a5a719ddacc3301a', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-283::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321516 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-283', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4043, '9:90b87c0d794eaa668176f35008a90f65', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-284::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183802 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-284', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4044, '9:3f80aed7be57f88e2c79a3dd305f6897', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-285::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183860 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-285', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4045, '9:b3c4b52c58da4e48e752af9b2f1305d1', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-286::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_at_137759 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-286', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4046, '9:8e70f5f59b1d95c64ea7e42e312e7585', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-287::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_at_137935 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-287', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4047, '9:2a35517466f90239388e05c3adfd5c65', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-288::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321517 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-288', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4048, '9:dbf5f2449a74d923a0829a636e6ecd55', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-289::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183803 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-289', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4049, '9:9dd2a5e38afe31531aa34412d5ba60a7', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-290::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183863 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-290', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4050, '9:0af1d7efc57deeb546525024707318a1', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-291::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137760 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-291', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4051, '9:645824a862dd06bc22d7995bcfc81930', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-292::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137936 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-292', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4052, '9:c63077d5fa08ff0604bfd72007c4a090', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-293::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority ADD au_residenceofauthority_au_name_gn_geographicalname_gn_g_321518 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-293', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4053, '9:a3365f045ea4b0aa7581cf8b7732c334', 'addColumn tableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-294::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183804 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-294', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4054, '9:e3a90cddf9f2bc333aa49a7a61291f9c', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-295::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183864 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-295', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4055, '9:999b335b5120315fcb68de3727955747', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-296::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137761 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-296', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4056, '9:7a96d074a2b08b6d81b7338880d500de', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-297::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137937 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-297', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4057, '9:2f528d9a835f88c87a65f2d1558ff9c4', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-298::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183805 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-298', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4058, '9:41b6443b70115e687a1d36400eb2c0c6', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-299::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183865 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-299', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4059, '9:4890e38c553eec9ab9441e5a973f2da6', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-300::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137763 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-300', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4060, '9:da8c07714d6cda789c90a7c3aed4ab9c', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-301::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137939 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-301', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4061, '9:455c4c8549ad547905f447e106226b45', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-302::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183806 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-302', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4062, '9:2cd22a5b6e0585b227321eb042875dde', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-303::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183867 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-303', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4063, '9:a568eaea9e08e72e844777b43e85a941', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-304::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137764 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-304', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4064, '9:c2f6799c7532cb78778b9556275c399f', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-305::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137940 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-305', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4065, '9:b2e0a77f0dc40b0375956526ece6e10d', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-306::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183807 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-306', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4066, '9:adaf6c97fa48212d1adff1f3e9daec6d', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-307::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183868 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-307', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4067, '9:7fee73a98f12dfa24023f8b38109dde6', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-308::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137765 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-308', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4068, '9:9c2e3bce682da52be9f766b13efbdfd3', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-309::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137941 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-309', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4069, '9:b923c9dcc724e0a4b013dcb3b9b0fdc1', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-310::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183808 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-310', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4070, '9:0036aa81c779f268b69c954a5251bbeb', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-311::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_137776 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-311', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4071, '9:62bd89ac666b3ba97f5b3b17f0118eca', 'addColumn tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-312::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183870 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-312', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4072, '9:7e8c8bbcd17f69021ed62eab762f2c83', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-313::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137766 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-313', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4073, '9:26555b7e25153a67dabeb0abbd0366d8', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-314::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_series_gmd_ci_series_gm_137942 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-314', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4074, '9:41e677453f4df36937a4144cb70c7258', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-315::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183809 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-315', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4075, '9:2a410579e6ed53b79b945f880a51e2db', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-316::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_137777 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-316', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4076, '9:4e30ea987143903b66e9a5cb591ba0e0', 'addColumn tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-317::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183871 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-317', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4077, '9:95a57844d52cdb6e5a28a85baac3392b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-318::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_othercitationdetails_at_137767 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-318', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4078, '9:8b8e2ead7d0f67ccce92907a737b03ef', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-319::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_othercitationdetails_at_137943 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-319', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4079, '9:76d9143e7f388594cab6876def60f777', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-320::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183811 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-320', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4080, '9:61364d456217b06e8244b18409dbbd67', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-321::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_137778 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-321', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4081, '9:6f44a8af1795179eb0756fd6b9678a09', 'addColumn tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-322::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183872 TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-322', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4082, '9:21d22fdbdedea00fe28a57ce97056442', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-323::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_othercitationdetails_gc_137768 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-323', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4083, '9:728073803bb24d8e7cbd485c75e80be0', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-324::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_othercitationdetails_gc_137944 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-324', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4084, '9:1bb2d3aac171a587d84af2532e9e2647', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-325::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183812 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-325', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4085, '9:ef451407e6db0d3f4d00d58c81a95c97', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-326::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_137615 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-326', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4086, '9:3b5f16f7f0b8c627a9ac0f200d61040d', 'addColumn tableName=tn_maintenanceauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-327::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_accessrestriction_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_137775 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-327', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4087, '9:bbd9fc11091ecc99a490ea54e7e3968c', 'addColumn tableName=tn_accessrestriction_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-328::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_137792 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-328', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4088, '9:73278e1ae0833a8900feceae38b173f1', 'addColumn tableName=tn_ownerauthority_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-329::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_verticalposition_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_137967 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-329', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4089, '9:31710f2c39e96e36f7fda54a4c1b19cf', 'addColumn tableName=tn_verticalposition_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-330::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_trafficflowdirection_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_137972 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-330', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4090, '9:b1ea96c7fcb37ab64330052b01208672', 'addColumn tableName=tn_trafficflowdirection_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-331::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_restrictionforvehicles_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_184032 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-331', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4091, '9:5d0750ab63a6eb0b80611fb72ac68f70', 'addColumn tableName=tn_restrictionforvehicles_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-332::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_conditionoffacility_net_networkref ADD net_linkreference_net_applicabledirection_attr_gml_remot_184037 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-332', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4092, '9:a62652d950dc376d106054f33f4ebffe', 'addColumn tableName=tn_conditionoffacility_net_networkref', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-333::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_spatialplan ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmapuri TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-333', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4093, '9:76683243bd3741e78eb56c68650d9b15', 'addColumn tableName=plu_spatialplan', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-334::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183874 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-334', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4094, '9:c17d57d93d23c0eeffd8820de6a38e3f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-335::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gc_137769 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-335', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4095, '9:38bd751a2a7d988ce8c89d017c566c4a', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-336::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_collectivetitle_attr_gc_137945 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-336', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4096, '9:2c391756927cf200492caca6f624aff7', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-337::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183814 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-337', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4097, '9:658ee640ca331d17077f4230892b11fc', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-338::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183876 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-338', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4098, '9:89755c46b8b61dbbfce87ab4a4766b16', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-339::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority ADD tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_cha_137770 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-339', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4099, '9:706f7e2c211cfc4c7df9bd87c8d2c820', 'addColumn tableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-340::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority ADD tn_authority_gmd_ci_citation_gmd_collectivetitle_gco_cha_137946 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-340', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4100, '9:e52803e02c74c5b0a2d067b8a280f136', 'addColumn tableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-341::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_e_183815 INTEGER;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-341', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4101, '9:2669d88555d3dfaee075f69512fa3f96', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-342::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_184024 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-342', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4102, '9:3be7a519fb23b437075568909d5c0da0', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-343::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183877 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-343', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4103, '9:3a5cc6b26e8e45e672eec36d4108295b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-344::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_d_183817 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-344', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4104, '9:6f933f5573f7177323d7d98960ee5797', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-345::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_184025 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-345', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4105, '9:396077c43ca420474a39f4caeff256c0', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-346::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183878 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-346', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4106, '9:7a3bf615255152d757da6245aa511389', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-347::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_d_183819 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-347', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4107, '9:b42b48a631f766668e08132fb5b91d7f', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-348::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundm_184026 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-348', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4108, '9:beda836b31d03fced43a5ca94406cde2', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-349::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183875 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-349', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4109, '9:661881f42ac2dca132ddd7989469c3a8', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-350::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.gn_namedplace ADD gn_mostdetailedviewingresolution_gmd_md_resolution_gmd_d_183818 numeric;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-350', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4110, '9:8372cf976163aa9df693f1d96b9872e2', 'addColumn tableName=gn_namedplace', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-351::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement ADD plu_backgroundmap_plu_backgroundmapvalue_plu_backgroundmapuri TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-351', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4111, '9:302d0b850b38b22dfd5c06fb51cfeaf6', 'addColumn tableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-352::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183883 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-352', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4112, '9:37b6487d6edbb8adbee78379d0336086', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-353::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183884 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-353', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4113, '9:2f13101ed846ce32186f32413a3b3fe5', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-354::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183886 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-354', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4114, '9:325380c02e602c130245919ac1f8a5a7', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-355::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183887 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-355', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4115, '9:3273f00b53406f24f490f9812b8ea578', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-356::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183888 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-356', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4116, '9:9380bc0d16a84ea4065a9554414ad81d', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-357::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183885 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-357', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4117, '9:8d3e84b1f390fb41b973eb93ccd69105', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-358::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183890 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-358', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4118, '9:90776bb78122018c265ac491561dc497', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-359::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183891 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-359', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4119, '9:c409a20505585a53e56ff244a5ae2ca2', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-360::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183892 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-360', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4120, '9:534ff16cf39512fdace740b0ce71f943', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-361::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183889 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-361', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4121, '9:b39f4f4ac13ab4a4f6a15ad23ffe3042', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-362::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183893 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-362', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4122, '9:7a25a169696d8bc15a2f700939158c39', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-363::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183894 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-363', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4123, '9:31a5ef2176adb99a9ae155265f27c96a', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-364::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183895 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-364', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4124, '9:ae655cd0da4b654ec659e427ee162641', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-365::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183896 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-365', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4125, '9:56ffa98714987cd8de83055e8b115783', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-366::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183897 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-366', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4126, '9:2ec1ee6c3c8b214efbb8f8bde5439c5f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-367::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183900 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-367', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4127, '9:4cd6bf0d5b2207e4b91faa1482dc1e3a', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-368::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183901 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-368', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4128, '9:f40bcc0f244b874a90ff2ffeaf984605', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-369::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183902 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-369', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4129, '9:84c801dd7b18138d777ff75e5db6c680', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-370::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_legislationcitation_base2_legislationcitation_base2__183903 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-370', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4130, '9:038d7f0706056818067dd1c50f9dfd26', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-371::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183906 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-371', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4131, '9:90d6a510b626d8965852357e09eaf837', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-372::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183907 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-372', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4132, '9:856f2123726f5f458fbb765d0641ff09', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-373::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183908 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-373', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4133, '9:e13682aec2795177d6b41830a6564674', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-374::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183909 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-374', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4134, '9:3f350392561135b0261e00679a927f44', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-375::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183910 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-375', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4135, '9:bb03c61cf8400849c7acaf492e237900', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-376::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183911 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-376', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4136, '9:0f030b108583440a65c94525a858ef35', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-377::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183912 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-377', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4137, '9:491b68a7146d08aa5dee27d569187007', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-378::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183913 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-378', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4138, '9:a8bbe28d4d1571760120cec68cd72654', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-379::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_description__183914 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-379', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4139, '9:f7f62953dd48c71bf9a22ed418924086', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-380::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_descriptionr_183916 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-380', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4140, '9:a71fb9c4720a17f301576644ffa6a552', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-381::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_descriptionr_183917 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-381', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4141, '9:45c15ae5b56f1bfe107e0a59faf04cfa', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-382::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_descriptionr_183918 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-382', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4142, '9:4ea213d439d3e5664764d98477aad89b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-383::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_descriptionr_183919 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-383', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4143, '9:0d9c207a1711fa49f740cc9039003cff', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-384::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_descriptionr_183920 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-384', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4144, '9:3a7a8e23dc09c8b4b5ccab49e82d5e1f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-385::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_gml_identifier_a_183921 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-385', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4145, '9:de831d00ffe949e2e4c744a7078da3a0', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-386::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_shortname__183923 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-386', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4146, '9:969014d18888cce8423c325067de6752', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-387::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_shortname__183924 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-387', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4147, '9:df7e975be8d548b0515b2cffb619421d', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-388::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_attr__183925 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-388', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4148, '9:1c04372103932b91d695cd18c600626f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-389::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_attr__183926 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-389', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4149, '9:84ce7feb59c894bee8caa7671197263c', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-390::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183927 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-390', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4150, '9:2be56e584e66da25cd66613a2fabb1a3', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-391::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183928 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-391', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4151, '9:dac324810951c0efdcd890340bd4185b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-392::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183930 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-392', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4152, '9:c01a4e8275e510f691c766241366e870', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-393::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183931 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-393', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4153, '9:1889e0e08062f3d300d23dfb8f6cb80b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-394::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183932 TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-394', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4154, '9:61fc1caaed413b18f03d735f83f933ff', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-395::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183934 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-395', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4155, '9:1e11d0ffef96018f8e97d4b80b1e21b6', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-396::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183936 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-396', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4156, '9:028c985f8d250f0d164c67526d68781b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-397::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183937 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-397', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4157, '9:4031df6926d8f8e29b80136f97088446', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-398::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183938 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-398', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4158, '9:c7373dd58aedc24d672dcd8a116a045f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-399::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_documentcitation_base2_date_gmd_c_183935 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-399', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4159, '9:da2cc8276aacc52aea2bcbf23c782ccc', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-400::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183944 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-400', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4160, '9:b3eb081c52a7a68ed0321f9eeb100a57', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-401::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183945 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-401', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4161, '9:b476c799765e3d041022cd8af0dc5ec3', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-402::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183946 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-402', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4162, '9:6b72d9f82e20c3d612454f59b213c961', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-403::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183947 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-403', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4163, '9:6e3a3796bab347f3404b62ee335cd3b0', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-404::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183948 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-404', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4164, '9:1c01bf6ee71088a9f30677bd7f328483', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-405::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183949 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-405', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4165, '9:b9885c83537f4264b933708a3ac44a43', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-406::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183950 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-406', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4166, '9:e96c63439bd260fecba78c170d5caa6f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-407::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183951 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-407', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4167, '9:393e1535b3e828bac8d954d91106c68b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-408::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183952 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-408', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4168, '9:4f35bd94ec01db778d23a4f93b03be49', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-409::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183954 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-409', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4169, '9:3b4939d8f38b1bd2b665be18f70e151b', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-410::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183955 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-410', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4170, '9:6d3832bad54fefef811e7e0fc918f232', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-411::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183956 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-411', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4171, '9:a3e314c3c50f1879c919da2de8b0c5b9', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-412::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183957 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-412', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4172, '9:58f2676aa4c815e39b5116c51b1bebc1', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-413::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_descripti_183958 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-413', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4173, '9:e6ed275dca7e8e1da1ec2b394079b227', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-414::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_gml_identifie_183959 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-414', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4174, '9:5dab055deb9cbb9412e0474df017248c', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-415::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_shortna_183961 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-415', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4175, '9:62b5aa7446785441d8352e51d18692b7', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-416::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_shortna_183962 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-416', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4176, '9:3fdb530776de504b2283f4b5290f6f41', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-417::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_at_183963 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-417', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4177, '9:fa5f2bf826047952c37f65c4db4230cb', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-418::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_at_183964 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-418', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4178, '9:ef3976472ec261eceadec27cb2fffdab', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-419::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_at_183965 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-419', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4179, '9:32ef541719791877d999ba3134816dfb', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-420::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183967 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-420', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4180, '9:f090ccddb6f6d3cbf7acce63c135d5ab', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-421::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183968 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-421', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4181, '9:669c0f73b12b9d7a2f21d3a2468e6f16', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-422::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183970 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-422', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4182, '9:19db255c1a69d58e4429e80d15e35974', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-423::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183971 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-423', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4183, '9:d182f95d031352ac197474a145b1203c', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-424::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183972 TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-424', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4184, '9:756374e9c39fa07fd12a16aa0c8bab3d', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-425::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183974 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-425', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4185, '9:8acbac313abdb52861b300174d0d8748', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-426::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183976 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-426', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4186, '9:d9883558ca2e5b0c7285186219ed712e', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-427::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183977 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-427', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4187, '9:9c9a594023617c4a061236f9ca1abd0a', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-428::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183978 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-428', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4188, '9:1cf3847d9a1bfddfad986980a28c198d', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-429::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_date_gm_183975 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-429', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4189, '9:decf6e92ff3c04305324b4ae251e99e2', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-430::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_identif_183981 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-430', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4190, '9:03fdc8d46ad018ea557aad47223ccc99', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-431::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_officia_183982 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-431', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4191, '9:8a899aa46fe2fadcf3939d3942e4943f', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-432::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_dateent_183984 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-432', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4192, '9:cda9cc26cdb46014d940bc16ac3c120a', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-433::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_dateent_183985 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-433', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4193, '9:5f63e5cbe176562239a07fa14807bb52', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-434::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_dateent_183986 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-434', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4194, '9:6a6b932b74919ca0114da66123cfd501', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-435::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_dateent_183983 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-435', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4195, '9:3c3acdaa5372760c7d0851ced49925a0', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-436::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_daterep_183987 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-436', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4196, '9:907f436f0be5fe9d73f41597381c13ec', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-437::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_daterep_183988 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-437', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4197, '9:04b340b858ec1278b3ac3ec8480bad96', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-438::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_daterep_183989 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-438', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4198, '9:a8f7a8b79e0e05a91ed68baede896d63', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-439::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_level_a_183990 BOOLEAN;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-439', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4199, '9:d1ba90794e89de5837df2bb67f28e8ea', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-440::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_level_a_183991 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-440', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4200, '9:57600b8a5d481a7ee14b5176c0571d2e', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-441::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_level_a_183992 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-441', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4201, '9:3c1fbdcef543ea8bcbc0c8517816fade', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-442::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_journal_183995 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-442', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4202, '9:567b7178be9312e906013f59d4f80bb8', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-443::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_journal_183996 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-443', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4203, '9:b121eefa709e670efc0b53cd9e4cf0b4', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-444::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_journal_183997 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-444', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4204, '9:d081ac22710f7cfb5a81f2725e4b5161', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-445::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation ADD plu_plandocument_base2_legislationcitation_base2_journal_183998 TEXT;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-445', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4205, '9:bf246598d0a00860bb2fa62d143be3aa', 'addColumn tableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-446::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_ad_address_ad_position_ad_geographicposition_ad_geometr_0 ON inspireplu.ad_address_ad_position USING gist (ad_geographicposition_ad_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-446', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4206, '9:b2048a107307a412a790c1b4d7f36c51', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-447::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_au_administrativeboundary_au_geometry_value ON inspireplu.au_administrativeboundary USING gist (au_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-447', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4207, '9:2a285c48edb05e31beb5d0ade03e52c1', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-448::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_au_administrativeunit_au_geometry_value ON inspireplu.au_administrativeunit USING gist (au_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-448', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4208, '9:396ceb0b79ec92bc59ad1899975ba02a', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-449::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_au_administrativeunit_au_residenceofauthority_au_reside_1 ON inspireplu.au_administrativeunit_au_residenceofauthority USING gist (au_residenceofauthority_au_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-449', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4209, '9:5af936d45ef6195895b75937a5fe3c90', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-450::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_au_condominium_au_geometry_value ON inspireplu.au_condominium USING gist (au_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-450', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4210, '9:f8afbdd55663e9c197e1959f85cfbdf7', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-451::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_cp_cadastralboundary_cp_geometry_value ON inspireplu.cp_cadastralboundary USING gist (cp_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-451', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4211, '9:578cab451fa5585002a0408688d64004', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-452::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_cp_cadastralparcel_cp_geometry_value ON inspireplu.cp_cadastralparcel USING gist (cp_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-452', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4212, '9:506c8a22d5a354f7c7b8632fdc3afdfc', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-453::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_cp_cadastralparcel_cp_referencepoint_value ON inspireplu.cp_cadastralparcel USING gist (cp_referencepoint_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-453', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4213, '9:fb4e5c6c9c2e1370a361795acd5d590a', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-454::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_cp_cadastralzoning_cp_geometry_value ON inspireplu.cp_cadastralzoning USING gist (cp_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-454', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4214, '9:66c4461772a31004b979ffc869589047', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-455::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_cp_cadastralzoning_cp_referencepoint_value ON inspireplu.cp_cadastralzoning USING gist (cp_referencepoint_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-455', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4215, '9:e35153fc4d9e2e2942a67930561b4ab5', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-456::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_gn_namedplace_gn_geometry_value ON inspireplu.gn_namedplace USING gist (gn_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-456', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4216, '9:38a568a1656f0e946564e8a3fc634f22', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-457::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_plu_spatialplan_plu_extent_value ON inspireplu.plu_spatialplan USING gist (plu_extent_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-457', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4217, '9:d1b7801cecee2b16bff013f6be67c5ee', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-458::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_plu_supplementaryregulation_plu_geometry_value ON inspireplu.plu_supplementaryregulation USING gist (plu_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-458', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4218, '9:6bcbc85304bc759af2318198fca712ed', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-459::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_plu_zoningelement_plu_geometry_value ON inspireplu.plu_zoningelement USING gist (plu_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-459', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4219, '9:b42b5c103c1a3c68866d4f3b7c7eabc8', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-460::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

CREATE INDEX IF NOT EXISTS spidx_tn_markerpost_tn_geometry_value ON inspireplu.tn_markerpost USING gist (tn_geometry_value);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-460', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4220, '9:808f9eac5f2b445c9544243c20fd13e6', 'sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-461::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45965 ADD CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name__parentfk_fkey1 FOREIGN KEY (parentfk) REFERENCES inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45934 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-461', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4221, '9:3f926b09a7b170515b4ae4e2048d6ddd', 'addForeignKeyConstraint baseTableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45965, constraintName=ad_address_ad_locator_ad_addresslocator_ad_name__parentfk_fkey1, referencedTableName=ad_address_ad_locator_ad_addresslocator_ad_n...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-462::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45934 ADD CONSTRAINT ad_address_ad_locator_ad_addresslocator_ad_name_a_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.ad_address_ad_locator_ad_addresslocator_ad_name (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-462', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4222, '9:f68e32c6f8db752c6c32a5ca42d14ee4', 'addForeignKeyConstraint baseTableName=ad_address_ad_locator_ad_addresslocator_ad_name_ad_addres_45934, constraintName=ad_address_ad_locator_ad_addresslocator_ad_name_a_parentfk_fkey, referencedTableName=ad_address_ad_locator_ad_addresslocator_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-463::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_postaldescriptor_ad_postname_gn_geographicalname_gn_sp_91801 ADD CONSTRAINT ad_postaldescriptor_ad_postname_gn_geographicalna_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.ad_postaldescriptor_ad_postname (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-463', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4223, '9:026f481f0672f81421515cb129d3d8e9', 'addForeignKeyConstraint baseTableName=ad_postaldescriptor_ad_postname_gn_geographicalname_gn_sp_91801, constraintName=ad_postaldescriptor_ad_postname_gn_geographicalna_parentfk_fkey, referencedTableName=ad_postaldescriptor_ad_postname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-464::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.au_administrativeunit_au_residenceofauthority_au_residen_321496 ADD CONSTRAINT au_administrativeunit_au_residenceofauthority_au__parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.au_administrativeunit_au_residenceofauthority (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-464', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4224, '9:da0c920c13415b4e14fe4abfb24f7285', 'addForeignKeyConstraint baseTableName=au_administrativeunit_au_residenceofauthority_au_residen_321496, constraintName=au_administrativeunit_au_residenceofauthority_au__parentfk_fkey, referencedTableName=au_administrativeunit_au_residenceofauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-465::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.net_network_net_geographicalname_gn_geographicalname_gn__137785 ADD CONSTRAINT net_network_net_geographicalname_gn_geographicaln_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.net_network_net_geographicalname (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-465', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4225, '9:5522dd0556058b59dab79ed97d7278c0', 'addForeignKeyConstraint baseTableName=net_network_net_geographicalname_gn_geographicalname_gn__137785, constraintName=net_network_net_geographicalname_gn_geographicaln_parentfk_fkey, referencedTableName=net_network_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-466::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183858 ADD CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey1 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-466', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4226, '9:e152df80153156561a89af4a4ddd199f', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2__183858, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey1, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-467::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183879 ADD CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey2 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-467', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4227, '9:131fe912848df085dea3d550f0e37f12', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2__183879, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey2, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-468::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183881 ADD CONSTRAINT plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey3 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-468', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4228, '9:2bf8e8700240e0a1f20350e5fb7ffcad', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2__183881, constraintName=plu_officialdocumentation_plu_legislationcitatio_parentfk_fkey3, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-469::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_legislationcitation_base2__183828 ADD CONSTRAINT plu_officialdocumentation_plu_legislationcitation_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-469', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4229, '9:4980625f2228db31b0767b3d9325b412', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_legislationcitation_base2__183828, constraintName=plu_officialdocumentation_plu_legislationcitation_parentfk_fkey, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-470::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183904 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2__parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-470', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4230, '9:2253e7419897a12583a287a22cf62b49', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_documen_183904, constraintName=plu_officialdocumentation_plu_plandocument_base2__parentfk_fkey, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-471::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183922 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey1 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-471', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4231, '9:167af8355b9d051608eac0b8fbea443b', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_documen_183922, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey1, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-472::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183939 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey2 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-472', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4232, '9:6823300ca85356b67a91098a5023f56a', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_documen_183939, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey2, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-473::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_documen_183940 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey3 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-473', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4233, '9:5fd7e86f6dc7a08ded0e4adca4d3be37', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_documen_183940, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey3, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-474::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183941 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey4 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-474', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4234, '9:7ac5602e87dfca439394fde8b3f13f00', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183941, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey4, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-475::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183960 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey5 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-475', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4235, '9:7a6ab143e6c057beb8b496936915bf31', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183960, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey5, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-476::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183979 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey6 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-476', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4236, '9:0362b38dc439946418a8ac588c6b45b5', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183979, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey6, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-477::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_officialdocumentation_plu_plandocument_base2_legisla_183980 ADD CONSTRAINT plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey7 FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_officialdocumentation (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-477', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4237, '9:080dd4c79d46aeb2d28204f3b0050876', 'addForeignKeyConstraint baseTableName=plu_officialdocumentation_plu_plandocument_base2_legisla_183980, constraintName=plu_officialdocumentation_plu_plandocument_base2_parentfk_fkey7, referencedTableName=plu_officialdocumentation', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-478::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_hilucspresence_lunom_hilucspresenc_184002 ADD CONSTRAINT plu_zoningelement_plu_hilucspresence_lunom_hilucs_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_zoningelement (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-478', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4238, '9:b7b0ae3cd1c1e190305773d04fcf45f3', 'addForeignKeyConstraint baseTableName=plu_zoningelement_plu_hilucspresence_lunom_hilucspresenc_184002, constraintName=plu_zoningelement_plu_hilucspresence_lunom_hilucs_parentfk_fkey, referencedTableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-479::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.plu_zoningelement_plu_specificpresence_lunom_specificpre_184015 ADD CONSTRAINT plu_zoningelement_plu_specificpresence_lunom_spec_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.plu_zoningelement (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-479', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4239, '9:727d62f6ed7e34a36b0597406d317d36', 'addForeignKeyConstraint baseTableName=plu_zoningelement_plu_specificpresence_lunom_specificpre_184015, constraintName=plu_zoningelement_plu_specificpresence_lunom_spec_parentfk_fkey, referencedTableName=plu_zoningelement', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-480::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137624 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey2 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-480', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4240, '9:a9e0a6374c2aa92886ffb89df1a352ce', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137624, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey2, referencedTableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-481::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey3 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-481', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4241, '9:514dafa88296549d5d692a5fb49908b6', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey3, referencedTableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-482::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137673 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey4 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-482', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4242, '9:bc874dcf2cb8456c2f031e28b289a364', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137673, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey4, referencedTableName=tn_maintenanceauthority_tn_authority_gmd_ci_...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-483::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137676 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey5 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-483', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4243, '9:47c5cb5bb582e2c4466891a5e7460153', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137676, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey5, referencedTableName=tn_maintenanceauthority_tn_authority_gmd_ci_...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-484::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137692 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey6 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-484', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4244, '9:78e4de01620528b6be8559f91a9ee9cf', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137692, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey6, referencedTableName=tn_maintenanceauthority_tn_authority_gmd_ci_...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-485::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137707 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey7 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137637 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-485', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4245, '9:c37cfaefd08c40472fa48cee775b5bd0', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137707, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey7, referencedTableName=tn_maintenanceauthority_tn_authority_gmd_ci_...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-486::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137755 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey8 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-486', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4246, '9:3c4955893889b183dec8f4e2fef4bef6', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137755, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_cita_parentfk_fkey8, referencedTableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-487::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137616 ADD CONSTRAINT tn_maintenanceauthority_tn_authority_gmd_ci_citat_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_maintenanceauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-487', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4247, '9:59b7504347ba7f5713c74553c62dfd0d', 'addForeignKeyConstraint baseTableName=tn_maintenanceauthority_tn_authority_gmd_ci_citation_gmd_137616, constraintName=tn_maintenanceauthority_tn_authority_gmd_ci_citat_parentfk_fkey, referencedTableName=tn_maintenanceauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-488::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey3 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-488', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4248, '9:09d93acae044c8796724ccb4f43d0991', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey3, referencedTableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-489::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137849 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey4 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-489', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4249, '9:8fe37473c0c2a522e516a3e1b5355483', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137849, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey4, referencedTableName=tn_ownerauthority_tn_authority_gmd_ci_citati...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-490::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137852 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey5 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-490', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4250, '9:11401a93e755f8d9a0372f8f802edbfc', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137852, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey5, referencedTableName=tn_ownerauthority_tn_authority_gmd_ci_citati...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-491::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137868 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey6 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-491', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4251, '9:c9740ba6302710abaa31fb27a9b55e35', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137868, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey6, referencedTableName=tn_ownerauthority_tn_authority_gmd_ci_citati...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-492::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137883 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey7 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137813 (id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-492', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4252, '9:1af785856b19e6f6e5485df7029e7385', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_cited_137883, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey7, referencedTableName=tn_ownerauthority_tn_authority_gmd_ci_citati...', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-493::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_prese_137931 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey8 FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-493', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4253, '9:84a455b9b9713c161df5ed6504e3167f', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_prese_137931, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_g_parentfk_fkey8, referencedTableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-494::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alter_137793 ADD CONSTRAINT tn_ownerauthority_tn_authority_gmd_ci_citation_gm_parentfk_fkey FOREIGN KEY (parentfk) REFERENCES inspireplu.tn_ownerauthority (attr_gml_id) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-494', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4254, '9:b8d9b25e4f00a3337432fe3e8bc82ee9', 'addForeignKeyConstraint baseTableName=tn_ownerauthority_tn_authority_gmd_ci_citation_gmd_alter_137793, constraintName=tn_ownerauthority_tn_authority_gmd_ci_citation_gm_parentfk_fkey, referencedTableName=tn_ownerauthority', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-1::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_41 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_41::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-1', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4255, '9:211662aa14a46daf2ebe687e43a73b5f', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_41, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-2::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_43 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_43::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-2', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4256, '9:497009c72e340672ac6bd7493527dbb6', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_43, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-3::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_48 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_48::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-3', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4257, '9:35447ce23c8ad9498a5495cb65fa65e0', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_48, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-4::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_50 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_50::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-4', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4258, '9:86b143a8ed13ae2b40713921704b024f', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_gram_50, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-5::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_1 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_1::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-5', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4259, '9:76e74bb9433cd496d29ce9a560bb1d3d', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_1, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-6::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_2 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_2::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-6', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4260, '9:3bd4d54c3961eb360c6a4bdc4e77a3c8', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_langu_2, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-7::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_12 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_12::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-7', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4261, '9:b7afad863b48b2ec2dec72fd282267df', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_12, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-8::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_14 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_14::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-8', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4262, '9:fd5fdd7da918b9bd5c6de244acfb79bb', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_name_14, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-9::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_5 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_5::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-9', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4263, '9:36f6711f18f9d69c24222f490dae1e1a', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_5, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-10::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_7 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_7::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-10', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4264, '9:2f481febfe927b19dc96910bf1aa2680', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_nativ_7, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-11::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_21 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_21::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-11', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4265, '9:a7533900392db3140900f7df1a49acbe', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_21, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-12::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_22 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_22::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-12', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4266, '9:218ee9633065546ea11423cb19414e0b', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_22, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-13::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_25 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_25::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-13', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4267, '9:184a78870951c17a453e7c2409adc172', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_25, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-14::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_26 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_26::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-14', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4268, '9:e55f54fae4eaf1f2d381ab3e787687a4', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_26, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-15::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_28 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_28::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-15', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4269, '9:479943f9553c6b83dd9b1e98b10c598a', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_28, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-16::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_29 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_29::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-16', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4270, '9:42bf5da3a1afe99f56173706f31f27a6', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_pron_29, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-17::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_18 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_18::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-17', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4271, '9:315f014c364c17045dbe18a9a91e88d0', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_18, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-18::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_19 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_19::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-18', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4272, '9:0d97242cd94a0fa73a0f65a19c825d6b', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_sour_19, tableName=ad_thoroughfarename_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-19::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30 ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_34 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_34::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-19', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4273, '9:1a0f1f25cf852f25cca333366f370d5c', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_34, tableName=ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-20::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30 ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_35 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_35::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-20', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4274, '9:c30ab56c17410403e503a7dab5f66b5e', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_35, tableName=ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-21::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30 ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_37 TYPE BOOLEAN USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_37::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-21', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4275, '9:e5c04c92c34f4aee829a5a90106bc617', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_37, tableName=ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-22::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30 ALTER COLUMN ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_38 TYPE TEXT USING (ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_38::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-22', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4276, '9:640a8271549fb9c319e649d7cabd734d', 'modifyDataType columnName=ad_thoroughfarenamevalue_ad_name_gn_geographicalname_gn_spel_38, tableName=ad_thoroughfarename_ad_name_ad_thoroughfarenamevalue_ad_name_30', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-23::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_105 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_105::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-23', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4277, '9:f36c747505ee2edec8b8646b8c4b0869', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_105, tableName=tn_transportnetwork_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-24::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_106 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_106::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-24', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4278, '9:33c34cd61157e6f9154dd0e54ff172c9', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_106, tableName=tn_transportnetwork_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-25::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_108 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_108::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-25', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4279, '9:ffe7c7c79023fb0ae699fb55af1e897f', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_108, tableName=tn_transportnetwork_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-26::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_109 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_109::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-26', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4280, '9:83fb9d87b3bb3e3d8335a0ed93cd2002', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_109, tableName=tn_transportnetwork_net_geographicalname', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-27::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_119 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_119::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-27', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4281, '9:a43b0770d3b6423ea8ec8dbdaed1cce1', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_119, tableName=ad_adminunitname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-28::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_120 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_120::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-28', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4282, '9:98681d0a1f601fe92e368be05a745362', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_120, tableName=ad_adminunitname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-29::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_122 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_122::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-29', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4283, '9:7489eec4d3b345f075f10f16ba8d9de9', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_122, tableName=ad_adminunitname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-30::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname_123 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname_123::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-30', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4284, '9:0e2c792534a5d30c3e85dea71c4988a9', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname_123, tableName=ad_adminunitname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-31::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname__56 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname__56::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-31', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4285, '9:b02a917a612180cc630b4322603d8021', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname__56, tableName=ad_addressareaname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-32::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname__57 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname__57::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-32', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4286, '9:09533f33dab115f9a3b5b0f014e509c7', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname__57, tableName=ad_addressareaname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-33::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname__59 TYPE BOOLEAN USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname__59::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-33', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4287, '9:298ab499d1111ca3f1dcc3672617283e', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname__59, tableName=ad_addressareaname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-34::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name ALTER COLUMN gn_geographicalname_gn_pronunciation_gn_pronunciationofname__60 TYPE TEXT USING (gn_geographicalname_gn_pronunciation_gn_pronunciationofname__60::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-34', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4288, '9:c529ebac9a2ca86f501ae2ecaf8cc8c2', 'modifyDataType columnName=gn_geographicalname_gn_pronunciation_gn_pronunciationofname__60, tableName=ad_addressareaname_ad_name', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-35::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname_gn_geographicalnam_110 ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsch_111 TYPE BOOLEAN USING (gn_geographicalname_gn_spellingofname_gn_transliterationsch_111::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-35', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4289, '9:5bd9de40cc0743f2355d348142c3d9c5', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsch_111, tableName=tn_transportnetwork_net_geographicalname_gn_geographicalnam_110', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-36::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_transportnetwork_net_geographicalname_gn_geographicalnam_110 ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsch_112 TYPE TEXT USING (gn_geographicalname_gn_spellingofname_gn_transliterationsch_112::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-36', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4290, '9:e90afa2fe6e742d49547199a0f81e94f', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsch_112, tableName=tn_transportnetwork_net_geographicalname_gn_geographicalnam_110', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-37::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name_gn_geographicalname_gn_spelling ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsch_124 TYPE BOOLEAN USING (gn_geographicalname_gn_spellingofname_gn_transliterationsch_124::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-37', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4291, '9:e211df2a835d734a1c3931bc08360e0f', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsch_124, tableName=ad_adminunitname_ad_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-38::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_adminunitname_ad_name_gn_geographicalname_gn_spelling ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsch_125 TYPE TEXT USING (gn_geographicalname_gn_spellingofname_gn_transliterationsch_125::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-38', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4292, '9:a190a5eb57ca0d3e66625ec1517c96e7', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsch_125, tableName=ad_adminunitname_ad_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-39::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name_gn_geographicalname_gn_spelling ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsche_61 TYPE BOOLEAN USING (gn_geographicalname_gn_spellingofname_gn_transliterationsche_61::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-39', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4293, '9:ac7997fcbb5aae2468e2119055f3069b', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsche_61, tableName=ad_addressareaname_ad_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-40::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.ad_addressareaname_ad_name_gn_geographicalname_gn_spelling ALTER COLUMN gn_geographicalname_gn_spellingofname_gn_transliterationsche_62 TYPE TEXT USING (gn_geographicalname_gn_spellingofname_gn_transliterationsche_62::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-40', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4294, '9:a204ed18b1c9ec4ce0ce13cc175b1686', 'modifyDataType columnName=gn_geographicalname_gn_spellingofname_gn_transliterationsche_62, tableName=ad_addressareaname_ad_name_gn_geographicalname_gn_spelling', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-41::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_grammaticalgender_93 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_grammaticalgender_93::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-41', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4295, '9:a8480219352849e0394f1eb082181b50', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_grammaticalgender_93, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-42::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_grammaticalgender_95 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_grammaticalgender_95::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-42', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4296, '9:bc3d2ff32727bfcf31c56622a1ffc095', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_grammaticalgender_95, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-43::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_grammaticalnumbe_101 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_grammaticalnumbe_101::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-43', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4297, '9:8cb9e22aa05574a3c345aa32cb3ba5b1', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_grammaticalnumbe_101, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-44::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_grammaticalnumber_99 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_grammaticalnumber_99::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-44', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4298, '9:746147ce754ec1001d332b61e4a2c1f3', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_grammaticalnumber_99, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-45::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_att_75 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_att_75::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-45', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4299, '9:6cd3385f0b5c1be5d4c6dcb56b4fef68', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_att_75, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-46::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_att_76 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_att_76::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-46', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4300, '9:2d41af86d302400de145c767584cfb52', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_att_76, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-47::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__79 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__79::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-47', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4301, '9:bbc0821dd4c3dffa8f526f0545f4b4ae', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__79, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-48::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__80 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__80::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-48', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4302, '9:67850fe948f89aaeb49e41bc01a513f9', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__80, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-49::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__82 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__82::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-49', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4303, '9:c0870166cde7127ef395fa2094ed63c3', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__82, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-50::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__83 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__83::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-50', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4304, '9:80da5c90091d003dfc02cc8a2cad7fb3', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_pronunciation_gn__83, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-51::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_73 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_73::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-51', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4305, '9:f64c4a9049ab70f54fdf5510e1a7931a', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_73, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-52::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_74 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_74::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-52', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4306, '9:1628b9a08bbf655895a6ba368233f571', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_sourceofname_attr_74, tableName=tn_markerpost', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-53::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84 ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_87 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_87::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-53', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4307, '9:335d6daff0bf17a04aaf063ff2c03e63', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_87, tableName=tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-54::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84 ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_88 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_88::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-54', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4308, '9:52d72b47a9414deeff58f060b76f2e57', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_88, tableName=tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-55::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84 ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_90 TYPE BOOLEAN USING (tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_90::BOOLEAN);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-55', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4309, '9:6460bc5730c88082528b90d1228ff9d4', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_90, tableName=tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-56::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

ALTER TABLE inspireplu.tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84 ALTER COLUMN tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_91 TYPE TEXT USING (tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_91::TEXT);

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-56', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4310, '9:3c51b0c19baaf715cdd4fd59aff07109', 'modifyDataType columnName=tn_geographicalname_gn_geographicalname_gn_spellingofname_gn_91, tableName=tn_markerpost_tn_geographicalname_gn_geographicalname_gn_spe_84', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_inspireplu.yaml::1712819118438-57::lyn (generated)
-- SET SEARCH_PATH TO public, "$user","public";

DROP VIEW IF EXISTS inspireplu.viewservice_zoning_element;

CREATE VIEW inspireplu.viewservice_zoning_element AS SELECT ze.attr_gml_id AS zoningelement_gml_id,
    ze.plu_geometry_value AS geometry,
    string_agg(DISTINCT zeh.href, '|'::text) AS hilucs,
    concat('{', ze.plu_inspireid_base_identifier_base_namespace, '}', ze.plu_inspireid_base_identifier_base_localid) AS inspireid,
    ze.plu_regulationnature_href AS regulationnature,
    array_to_string(array_agg(DISTINCT
        CASE
            WHEN (zedi.plu_dimensioningindicationvalue_plu_indicationreference IS NOT NULL) THEN zedi.plu_dimensioningindicationvalue_plu_indicationreference
            WHEN (zedi.plu_dimensioningindicationcharactervalue_plu_indicationr_184027 IS NOT NULL) THEN ((zedi.plu_dimensioningindicationcharactervalue_plu_indicationr_184027 || ': '::text) || zedi.plu_dimensioningindicationcharactervalue_plu_value)
            WHEN (zedi.plu_dimensioningindicationmeasurevalue_plu_indicationreference IS NOT NULL) THEN (((((zedi.plu_dimensioningindicationmeasurevalue_plu_indicationreference || ': '::text) || zedi.plu_dimensioningindicationmeasurevalue_plu_value) || ' ['::text) || zedi.plu_dimensioningindicationmeasurevalue_plu_value_attr_uom) || ']'::text)
            WHEN (zedi.plu_dimensioningindicationintegervalue_plu_indicationreference IS NOT NULL) THEN ((zedi.plu_dimensioningindicationintegervalue_plu_indicationreference || ': '::text) || zedi.plu_dimensioningindicationintegervalue_plu_value)
            WHEN (zedi.plu_dimensioningindicationrealvalue_plu_indicationreference IS NOT NULL) THEN ((zedi.plu_dimensioningindicationrealvalue_plu_indicationreference || ': '::text) || zedi.plu_dimensioningindicationrealvalue_plu_value)
            ELSE NULL::text
        END), ', '::text, ''::text) AS dimensioningindications,
    ze.plu_processstepgeneral_href AS processstepgeneral,
    string_agg(DISTINCT zeslu.href, '|'::text) AS specificlanduse,
    ze.plu_validfrom AS validfrom,
    ze.plu_validto AS validto,
    ze.plu_beginlifespanversion AS beginlifespanversion,
    ze.plu_endlifespanversion AS endlifespanversion
   FROM (((inspireplu.plu_zoningelement ze
     LEFT JOIN inspireplu.plu_zoningelement_plu_hilucslanduse zeh ON ((ze.attr_gml_id = zeh.parentfk)))
     LEFT JOIN inspireplu.plu_zoningelement_plu_specificlanduse zeslu ON ((ze.attr_gml_id = zeslu.parentfk)))
     LEFT JOIN inspireplu.plu_zoningelement_plu_dimensioningindication zedi ON ((ze.attr_gml_id = zedi.parentfk)))
  GROUP BY ze.attr_gml_id;

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1712819118438-57', 'lyn (generated)', '7.2/changelog_inspireplu.yaml', NOW(), 4311, '9:25910aa56ceba28d60a2a4e335723b06', 'createView viewName=viewservice_zoning_element', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset 7.2/changelog_grant_privileges.yaml::grant-privileges-to-application-database-user-72::latlon
-- SET SEARCH_PATH TO public, "$user","public";

GRANT USAGE ON SCHEMA inspireplu TO "${xplan.db.user}";

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA inspireplu TO "${xplan.db.user}";

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA inspireplu TO "${xplan.db.user}";

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('grant-privileges-to-application-database-user-72', 'latlon', '7.2/changelog_grant_privileges.yaml', NOW(), 4312, '9:9b64135fd7de5e19b1a93112ab63d1a7', 'sql; sql; sql', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664');

-- Changeset target/classes/7.2/changelog_v72.yaml::tagDatabase-v72::latlon
-- SET SEARCH_PATH TO public, "$user","public";

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID, TAG) VALUES ('tagDatabase-v72', 'latlon', 'target/classes/7.2/changelog_v72.yaml', NOW(), 4313, '9:b530702a29947b799e5d26e4a1fdd32c', 'tagDatabase', '', 'EXECUTED', NULL, NULL, '4.25.0', '5098353664', 'v_7.2');

-- Release Database Lock
-- SET SEARCH_PATH TO public, "$user","public";

UPDATE databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

-- SET SEARCH_PATH TO public, "$user","public";

