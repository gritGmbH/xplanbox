-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplan41nsmpre.xml
-- Ran at: 23.03.16 16:25
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk26
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplan41nsmpre.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplan41nsmpre.databasechangeloglock;

INSERT INTO xplan41nsmpre.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplan41nsmpre.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%2)', LOCKGRANTED = '2016-03-23 16:25:45.894' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplan41nsmpre.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Release Database Lock
UPDATE xplan41nsmpre.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

