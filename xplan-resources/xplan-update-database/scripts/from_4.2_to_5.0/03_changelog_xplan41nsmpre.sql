-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplan41nsmpre.xml
-- Ran at: 18.03.22, 07:38
-- Against: postgres@jdbc:postgresql://localhost:5439/xplanbox
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplan41nsmpre.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplan41nsmpre.databasechangeloglock;

INSERT INTO xplan41nsmpre.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplan41nsmpre.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2022-03-18 07:38:56.85' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplan41nsmpre.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplan41nsmpre.xml::1647585350807-1::lyn (generated)
ALTER TABLE xplan41nsmpre.gml_objects DROP CONSTRAINT gml_objects_ft_type_fkey;

INSERT INTO xplan41nsmpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585350807-1', 'lyn (generated)', 'changelog_xplan41nsmpre.xml', NOW(), 1, '8:b72c0c0142a648d5a2dd8ddfa9edc0f5', 'dropForeignKeyConstraint baseTableName=gml_objects, constraintName=gml_objects_ft_type_fkey', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585537356');

-- Changeset changelog_xplan41nsmpre.xml::1647585350807-2::lyn (generated)
ALTER TABLE xplan41nsmpre.gml_objects DROP CONSTRAINT gml_objects_gml_id_key;

INSERT INTO xplan41nsmpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585350807-2', 'lyn (generated)', 'changelog_xplan41nsmpre.xml', NOW(), 2, '8:696b2059e12b8275be2cf6b457071afb', 'dropUniqueConstraint constraintName=gml_objects_gml_id_key, tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585537356');

-- Changeset changelog_xplan41nsmpre.xml::1647585350807-3::lyn (generated)
DROP TABLE xplan41nsmpre.feature_types;

INSERT INTO xplan41nsmpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585350807-3', 'lyn (generated)', 'changelog_xplan41nsmpre.xml', NOW(), 3, '8:54533b1d53f0a77a255e615b8353e5af', 'dropTable tableName=feature_types', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585537356');

-- Changeset changelog_xplan41nsmpre.xml::1647585350807-4::lyn (generated)
DROP TABLE xplan41nsmpre.gml_objects;

INSERT INTO xplan41nsmpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585350807-4', 'lyn (generated)', 'changelog_xplan41nsmpre.xml', NOW(), 4, '8:ce6206e1178dd14409dbd9ab837c06d8', 'dropTable tableName=gml_objects', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585537356');

-- Release Database Lock
UPDATE xplan41nsmpre.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

