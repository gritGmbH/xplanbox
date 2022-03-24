-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplan41nsm.xml
-- Ran at: 08.04.15 11:59
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/updatemgr011
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS xplan41nsm.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplan41nsm.databasechangeloglock;

INSERT INTO xplan41nsm.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplan41nsm.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%2)', LOCKGRANTED = '2015-04-08 11:59:13.547' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplan41nsm.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Release Database Lock
UPDATE xplan41nsm.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

