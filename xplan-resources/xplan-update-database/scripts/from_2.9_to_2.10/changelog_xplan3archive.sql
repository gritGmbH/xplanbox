-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplan3archive.xml
-- Ran at: 29.11.18 12:30
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk29
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplan3archive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplan3archive.databasechangeloglock;

INSERT INTO xplan3archive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplan3archive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk.fritz.box (192.168.178.152)', LOCKGRANTED = '2018-11-29 12:30:27.837' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplan3archive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplan3archive.xml::1543490696893-1::lgvxplanisk (generated)
ALTER TABLE xplan3archive.gml_objects DROP CONSTRAINT gml_objects_ft_type_fkey;

INSERT INTO xplan3archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490696893-1', 'lgvxplanisk (generated)', 'changelog_xplan3archive.xml', NOW(), 1, '8:b72c0c0142a648d5a2dd8ddfa9edc0f5', 'dropForeignKeyConstraint baseTableName=gml_objects, constraintName=gml_objects_ft_type_fkey', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491029066');

-- Changeset changelog_xplan3archive.xml::1543490696893-2::lgvxplanisk (generated)
ALTER TABLE xplan3archive.gml_objects DROP CONSTRAINT gml_objects_gml_id_key;

INSERT INTO xplan3archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490696893-2', 'lgvxplanisk (generated)', 'changelog_xplan3archive.xml', NOW(), 2, '8:696b2059e12b8275be2cf6b457071afb', 'dropUniqueConstraint constraintName=gml_objects_gml_id_key, tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491029066');

-- Changeset changelog_xplan3archive.xml::1543490696893-3::lgvxplanisk (generated)
DROP TABLE xplan3archive.feature_types;

INSERT INTO xplan3archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490696893-3', 'lgvxplanisk (generated)', 'changelog_xplan3archive.xml', NOW(), 3, '8:54533b1d53f0a77a255e615b8353e5af', 'dropTable tableName=feature_types', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491029066');

-- Changeset changelog_xplan3archive.xml::1543490696893-4::lgvxplanisk (generated)
DROP TABLE xplan3archive.gml_objects;

INSERT INTO xplan3archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490696893-4', 'lgvxplanisk (generated)', 'changelog_xplan3archive.xml', NOW(), 4, '8:ce6206e1178dd14409dbd9ab837c06d8', 'dropTable tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491029066');

-- Release Database Lock
UPDATE xplan3archive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

