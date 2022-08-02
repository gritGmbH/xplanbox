-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplanmgr.xml
-- Ran at: 08.04.15 11:59
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/updatemgr011
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS xplanmgr.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplanmgr.databasechangeloglock;

INSERT INTO xplanmgr.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplanmgr.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%2)', LOCKGRANTED = '2015-04-08 11:59:16.094' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplanmgr.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Changeset changelog_xplanmgr.xml::1428411308665-1::lgvxplanisk (generated)
ALTER TABLE xplanmgr.plans ADD gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplanmgr.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411308665-1', 'lgvxplanisk (generated)', 'changelog_xplanmgr.xml', NOW(), 1, '7:af4fd5ec70033544b1525a1cf307eda4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplanmgr.xml::1428411308665-2::lgvxplanisk (generated)
ALTER TABLE xplanmgr.plans ADD gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplanmgr.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411308665-2', 'lgvxplanisk (generated)', 'changelog_xplanmgr.xml', NOW(), 2, '7:de3699564e27ce756c2b2f8e3dd04793', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplanmgr.xml::1428411308665-3::lgvxplanisk (generated)
ALTER TABLE xplanmgr.plans ADD planstatus TEXT;

INSERT INTO xplanmgr.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411308665-3', 'lgvxplanisk (generated)', 'changelog_xplanmgr.xml', NOW(), 3, '7:80a95d6c25ba02fbcfdde8395f733f98', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Release Database Lock
UPDATE xplanmgr.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

