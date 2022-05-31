-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplanmgr.xml
-- Ran at: 18.03.22, 07:39
-- Against: postgres@jdbc:postgresql://localhost:5439/xplanbox
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS xplanmgr.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplanmgr.databasechangeloglock;

INSERT INTO xplanmgr.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplanmgr.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2022-03-18 07:39:19.565' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplanmgr.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplanmgr.xml::1647585525114-1::lyn (generated)
ALTER TABLE xplanmgr.plans DROP COLUMN ade;

INSERT INTO xplanmgr.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585525114-1', 'lyn (generated)', 'changelog_xplanmgr.xml', NOW(), 1, '8:6dc9cd957a29d7d9a63476c818a57252', 'dropColumn columnName=ade, tableName=plans', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585560122');

-- Release Database Lock
UPDATE xplanmgr.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

