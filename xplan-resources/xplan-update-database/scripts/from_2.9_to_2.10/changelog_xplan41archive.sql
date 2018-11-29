-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplan41archive.xml
-- Ran at: 29.11.18 12:30
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk29
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplan41archive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplan41archive.databasechangeloglock;

INSERT INTO xplan41archive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplan41archive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk.fritz.box (192.168.178.152)', LOCKGRANTED = '2018-11-29 12:30:46.616' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplan41archive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplan41archive.xml::1543490717485-1::lgvxplanisk (generated)
ALTER TABLE xplan41archive.gml_objects DROP CONSTRAINT gml_objects_ft_type_fkey;

INSERT INTO xplan41archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490717485-1', 'lgvxplanisk (generated)', 'changelog_xplan41archive.xml', NOW(), 1, '8:b72c0c0142a648d5a2dd8ddfa9edc0f5', 'dropForeignKeyConstraint baseTableName=gml_objects, constraintName=gml_objects_ft_type_fkey', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491048006');

-- Changeset changelog_xplan41archive.xml::1543490717485-2::lgvxplanisk (generated)
ALTER TABLE xplan41archive.gml_objects DROP CONSTRAINT gml_objects_gml_id_key;

INSERT INTO xplan41archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490717485-2', 'lgvxplanisk (generated)', 'changelog_xplan41archive.xml', NOW(), 2, '8:696b2059e12b8275be2cf6b457071afb', 'dropUniqueConstraint constraintName=gml_objects_gml_id_key, tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491048006');

-- Changeset changelog_xplan41archive.xml::1543490717485-3::lgvxplanisk (generated)
DROP TABLE xplan41archive.feature_types;

INSERT INTO xplan41archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490717485-3', 'lgvxplanisk (generated)', 'changelog_xplan41archive.xml', NOW(), 3, '8:54533b1d53f0a77a255e615b8353e5af', 'dropTable tableName=feature_types', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491048006');

-- Changeset changelog_xplan41archive.xml::1543490717485-4::lgvxplanisk (generated)
DROP TABLE xplan41archive.gml_objects;

INSERT INTO xplan41archive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543490717485-4', 'lgvxplanisk (generated)', 'changelog_xplan41archive.xml', NOW(), 4, '8:ce6206e1178dd14409dbd9ab837c06d8', 'dropTable tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491048006');

-- Release Database Lock
UPDATE xplan41archive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

