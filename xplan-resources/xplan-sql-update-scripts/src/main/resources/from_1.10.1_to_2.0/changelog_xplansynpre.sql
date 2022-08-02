-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynpre.xml
-- Ran at: 23.03.16 16:26
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk26
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS xplansynpre.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynpre.databasechangeloglock;

INSERT INTO xplansynpre.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynpre.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%2)', LOCKGRANTED = '2016-03-23 16:26:12.986' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynpre.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Changeset changelog_xplansynpre.xml::1458746541113-1::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abgrabungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-1', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 1, '7:c4a67a7852bc4d5d29e1eb46df5c75aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-2::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-2', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 2, '7:3b03c02c1c9a99342406fd9c4b39948b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-3::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-3', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 3, '7:0e6d9714bc265feb0192963de73ea33a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-4::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-4', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 4, '7:e9c87e5190e1c88047e0bbae76fe9db7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-5::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_aufschuettungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-5', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 5, '7:2ec425423e49909261705881b7dfcfa0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-6::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-6', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 6, '7:c1bc893f2f0f5ccc47372de1c8dc8327', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-7::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-7', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 7, '7:9c29003aea08b75d22b2c08dc7af6dbf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-8::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-8', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 8, '7:831989282ac5c625fc353285240e0da6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-9::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bahnverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-9', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 9, '7:0b806af4ba140a95af9701ba801d5f8a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-10::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-10', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 10, '7:6274cece1def0fe7c4af17633e2f4061', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-11::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-11', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 11, '7:49e3c015e8f93b487c645957218e4c01', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-12::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugrenze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-12', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 12, '7:8a08d296669fa1603e0fc4373fc327d7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-13::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baulinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-13', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 13, '7:1567fed01a8f020317c199afd8950bde', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-14::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bauschutzbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-14', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 14, '7:7ac7eded991c76d69ecfd5869b3522b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-15::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-15', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 15, '7:2a4686391bfe096f3cdcfd0b8be5a783', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-16::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-16', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 16, '7:b58d47e98146340d05c43b717ca6c082', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-17::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_besonderernutzungszweckflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-17', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 17, '7:533e55a2122d749f015e2d1292f8551f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-18::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bodenschaetzeflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-18', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 18, '7:827011c0fd5437d3cc95999bc20a5749', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-19::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzeinzelanlage ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-19', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 19, '7:76aca749c5c6a02de3d5f4a3566652cb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-20::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzeinzelanlagepunkt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-20', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 20, '7:cfeadf49e07affbe1278557effea6d37', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-21::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzensembleflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-21', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 21, '7:e941118648ee34482af170e10dca6b88', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-22::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtpunkt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-22', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 22, '7:480bdddb90c8561672ca2574348f192f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-23::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtsbereichlinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-23', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 23, '7:6aef1eb5acd6bacfbdc8bf35c38c8348', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-24::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_eingriffsbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-24', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 24, '7:86e228154aa42cdfcaeb8c2bb976ace3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-25::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_erhaltungsbereichflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-25', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 25, '7:87b98cbca2bf111a1723cb5460182de4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-26::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_erneuerbareenergieflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-26', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 26, '7:c1e3176e0f151d2797ea47ece14af9d3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-27::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_fachgesetz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-27', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 27, '7:3d16f0c1b2a3a49139bd97df71938b08', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-28::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungenbaugebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-28', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 28, '7:f1af226d04d615f7a19d8e28d87d69d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-29::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-29', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 29, '7:36d8f024d552adb6b21d5e8d5e3dde73', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-30::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_firstrichtungslinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-30', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 30, '7:49bba256eda161d3d8271d8dcd6d9ed4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-31::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_foerderungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-31', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 31, '7:18bf0ff0211e526ab3b6e81e7b7fd2c1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-32::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_freiflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-32', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 32, '7:3c3ec6ef12077aa248f4ec84dc7dcb1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-33::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gebaeudeflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-33', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 33, '7:bcf1df07c73c209040148bbaf261b256', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-34::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinbedarfsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-34', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 34, '7:d82069dc25544bd331ed1e224fbc8e7c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-35::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-35', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 35, '7:2e1629fb177d7f9a4c3bb1e99ba5cf23', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-36::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-36', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 36, '7:a49eaf03ef830cb754c17e27f19d3f6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-37::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-37', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 37, '7:d9ccc8b65338a080ca3a93cf72817d5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-38::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gestaltungbaugebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-38', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 38, '7:6fc7ba46e916f6b1643799022a39911d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-39::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gewaesserflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-39', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 39, '7:8d37b1bc2da18c60568a4cfdfb397b6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-40::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_grabungsschutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-40', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 40, '7:7c30dba6b02801decb23413c9f956df8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-41::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gruenflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-41', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 41, '7:7773e92321181172dec77fbda3c82cc5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-42::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-42', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 42, '7:281e0fdba6f9a5d039f4af85d32ac03f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-43::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenpunkt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-43', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 43, '7:90286291f54bd43ec1210255eeadfa3a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-44::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-44', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 44, '7:d5335883c4a95f37beff239a6c801236', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-45::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kennzeichnungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-45', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 45, '7:e2c0a5528c6566d6be15dad28f689bd2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-46::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kleintierhaltungflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-46', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 46, '7:5542764304c0b4c8110d0aa8e8fe5378', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-47::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_laermschutzbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-47', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 47, '7:018e7a2e3b7b06eed99fb7a87ba45145', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-48::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-48', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 48, '7:f0dbee4a1085e13bddb9f62ce0bc7b80', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-49::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaftsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-49', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 49, '7:3e6f1badbf25dd0a9ae3cfd4131df612', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-50::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaftslinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-50', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 50, '7:ad7de0505ec611aeeab246b2eaadb63b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-51::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_luftreinhalteflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-51', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 51, '7:08ec399840afd9bd927ea35ab2ae4296', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-52::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_luftverkehrflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-52', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 52, '7:06211bd2cffc76e810de6c80278de647', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-53::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-53', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 53, '7:28e6df23f74464b850a664114259ca25', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-54::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-54', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 54, '7:f7b9d52713620b814d3aa162c3e13360', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-55::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nutzungsartengrenze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-55', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 55, '7:425e5bb774a62d2d780e4c8f3e22e192', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-56::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_persgruppenbestimmteflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-56', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 56, '7:444d947fc67fe7bdd5dd458ca1f3d1e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-57::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_plan ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-57', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 57, '7:23793c80178a52678843b9b6138f55de', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-58::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_rasterplanaenderung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-58', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 58, '7:f94c28f69650a23d67485f2978d03dac', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-59::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_regelungvergnuegungsstaetten ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-59', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 59, '7:944af06cd6b6475afe3e6cc36c9c0cf4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-60::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_rekultivierungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-60', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 60, '7:9fbf1599c041dc25d38b56da95b6a9a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-61::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-61', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 61, '7:ef77bebf47fe9be71be2dec3cf232a63', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-62::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-62', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 62, '7:651e2dbe62eb21315f5608b8017ab340', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-63::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-63', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 63, '7:f7014bef145a97bfcdd64b697b779405', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-64::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_speziellebauweise ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-64', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 64, '7:7cdaf22ee331cbbb42e7805725eddace', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-65::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_spielsportanlagenflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-65', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 65, '7:6bc630d2e8a06b2a7737131dab4334fd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-66::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenbegrenzungslinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-66', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 66, '7:f2b5641077164e651a6828ea911d7e5e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-67::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-67', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 67, '7:a9bf5416caca3c493e21a003d99be7f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-68::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenverkehrsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-68', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 68, '7:1ee556bfcee1a0fc486c91eb94fcd798', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-69::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_technikbestimmteflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-69', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 69, '7:2c89832e1eddf36af2d3f124a67348e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-70::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_textabschnitt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-70', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 70, '7:8409f79d011462f2ddc2b5b7d221a418', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-71::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_textlichefestsetzungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-71', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 71, '7:bd15774b2697ac0ee8066997ec28121f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-72::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-72', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 72, '7:f8d5096987f7a6a87883ef5f1792d8eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-73::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-73', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 73, '7:5ee5738275a9ba124ceea2928df4471a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-74::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_veraenderungssperre ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-74', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 74, '7:467d3324d19296edd6ce2bbe64de21aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-75::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-75', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 75, '7:4b39efb26719e8c38182449e600df13f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-76::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-76', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 76, '7:c2c051a4875d947037c17c235289b5ff', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-77::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgungsleitunglinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-77', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 77, '7:9743066c5aa45631fe5351ed2134283c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-78::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-78', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 78, '7:8e7add9747471432e2d8f4ceae071c4a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-79::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_vorbhochwschutzflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-79', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 79, '7:ad25db40dccff3ad02b55532ad66e06e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-80::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_waldflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-80', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 80, '7:c41cb216259ccd78fb4c6b1cff297dba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-81::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wasserrechtlichefestsetzungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-81', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 81, '7:3da78c1baae999a467c3406502d17cf7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-82::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wasserwirtschaftsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-82', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 82, '7:7827b4b88db77229699a447f70287481', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-83::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-83', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 83, '7:41ebf481f4f36fcf4ae277f55f92a05f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-84::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-84', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 84, '7:4d4e757a1e91a7752ef7c5531f2e78d0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-85::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-85', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 85, '7:62108fa783c4f11afcda0e86177356aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-86::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-86', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 86, '7:4c87d0bde6fd7d5ef4419787359259e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-87::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-87', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 87, '7:fa5a6c78b2298fac29cca9f775150216', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-88::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-88', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 88, '7:239f7950d99266fe58d565dd07b507c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-89::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-89', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 89, '7:258e8567a173a898934ca966369566e6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-90::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bahnverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-90', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 90, '7:42b48816d81a2ceaf5e61f7671f218b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-91::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bauschutzbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-91', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 91, '7:12891f62b9356a8d1b56b2ee1005f62f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-92::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bebauungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-92', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 92, '7:0cdb8ae13dc4d2ad90a013201720f994', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-93::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-93', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 93, '7:fe2f6d404756cdbff15698bc41c526c9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-94::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-94', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 94, '7:a95000aad5987f41c0b09e80737b7f14', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-95::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetzeflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-95', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 95, '7:260bf5b67486245ecf1cfdd559113eed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-96::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_denkmalschutzensemble ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-96', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 96, '7:f37ea9be9551d176a83d487808b6ce7c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-97::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_erhaltungssatzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-97', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 97, '7:7ec580852389d44c3d9820c1a2c416b0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-98::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_fachgesetz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-98', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 98, '7:d5a6803bd5abe6e5e5b20aedb22b820e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-99::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-99', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 99, '7:02765e17239ee9231e70f32193ed7fbf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-100::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-100', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 100, '7:dbdf6727e47b13867f6201d9506a6783', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-101::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-101', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 101, '7:a27f0b8055e685af20d4348213500ded', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-102::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_grabungsschutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-102', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 102, '7:3897d08e854d3b48b97391afd5ad1c06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-103::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-103', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 103, '7:9abec00d367d4bb6fe9317f82edcf27f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-104::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-104', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 104, '7:9d08fb3713329f7a18f1c62d9e0a0132', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-105::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-105', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 105, '7:d30513ace8748fcbce19202188a74c7f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-106::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_laermschutzbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-106', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 106, '7:c767c4751626ed5405ab5187a1cec40d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-107::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_landwirtschaftsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-107', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 107, '7:c5e86d9946d9c9e9cbf37b0528739698', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-108::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_luftverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-108', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 108, '7:7bfa004b2f6fcb6e19a433cc8d188875', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-109::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-109', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 109, '7:6ef0a6900d5bbd6885ca92d852c891f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-110::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_plan ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-110', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 110, '7:61ae4a02f7642dbd3ff7df61b6c8dd58', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-111::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-111', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 111, '7:a4ffac491ee9136ed05aaa32a922e08f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-112::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_priviligiertesvorhaben ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-112', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 112, '7:0210e59136e30a0c9db621a5c3ebe04b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-113::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_rasterplanaenderung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-113', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 113, '7:dc2a7ce771c4a37884b968d5d4d23060', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-114::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-114', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 114, '7:2c2c0ad64cb370554fe5e396e35287f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-115::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-115', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 115, '7:058592afae9ced11bfd0744c064d6fac', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-116::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-116', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 116, '7:4d4d156140c833d29e5b12ecadee291f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-117::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-117', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 117, '7:b64f05d4572f10c846702d14501ce8c0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-118::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textabschnitt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-118', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 118, '7:b08a0e439612a2bbdaa972fb10def594', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-119::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textlichedarstellungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-119', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 119, '7:d608a06da96a7f4829c1558d688164b8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-120::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-120', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 120, '7:fb8ddb809b4210bae6e8e1eaa5c56390', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-121::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-121', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 121, '7:588e121d92eb438c5f670ddc9cdb0ec5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-122::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbehalteflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-122', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 122, '7:2fbe79a3d0c231793825eb423d5b4695', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-123::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbhochwschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-123', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 123, '7:91f4b535794764883a32603164c7d8be', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-124::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_waldflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-124', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 124, '7:0963d400e2ea2a2ef46f0fa6bc789dad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-125::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-125', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 125, '7:5e186a07ffb8c7969742d1f2ca763a46', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-126::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-126', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 126, '7:38fdb35ed0945b71ee6182c18a403011', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-127::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-127', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 127, '7:78197d407f0533a93499d0cc47162a3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-128::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_abgrenzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-128', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 128, '7:24977535151e12f6acbec9945618a393', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-129::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_allggruenflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-129', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 129, '7:298070b0311e62bce16e3922da8014ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-130::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-130', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 130, '7:6e3602f60e7f7c86b63d50408461dc38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-131::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-131', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 131, '7:64a4c06d855fde8de1f9dfe5d2676d0f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-132::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-132', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 132, '7:75ae0337f94379aa0ddf49c3534e4c5a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-133::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-133', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 133, '7:02e573d17fb71101cbf5fef74ccac5bc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-134::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-134', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 134, '7:fb8dc4db7f327ddf13e54ffdc3de38eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-135::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_denkmalschutzrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-135', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 135, '7:1813b3f8d94c310c5a3da8273e67e7b8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-136::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-136', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 136, '7:a347cdc55a522cd75754f0bf7b638ae7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-137::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-137', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 137, '7:2149545ef280a0455b0d5c945ac99e1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-138::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-138', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 138, '7:033632da97743e927cf9689983e05bd3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-139::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-139', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 139, '7:0f2785d8a4735eb96e9b3f418d151d08', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-140::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_biotopschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-140', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 140, '7:f8ad63e13a23c47dd18e634d2c59de47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-141::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_brachflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-141', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 141, '7:a50320227d1ea23988d7e8bb0e654c17', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-142::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_elementekulturlandschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-142', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 142, '7:c9842eadd112c1f1ff47bbf3db1f7289', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-143::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_forstlichefestsetzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-143', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 143, '7:6a63f9edf4d70da831de35901141a793', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-144::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_herrichtunggrundstueck ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-144', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 144, '7:956681b60f76b4473c5a98ca6d81f4fd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-145::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_pflegeanpflanzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-145', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 145, '7:8028266bd28a28a90f39fc65f3bea028', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-146::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_pflegelandschaftsbild ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-146', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 146, '7:c5aa7e3eea6ae2d940b41bb2b13e581f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-147::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_schutzobjektlandesrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-147', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 147, '7:55106c55efade303fdfcfbabd4494871', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-148::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_sonstigemassnahme ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-148', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 148, '7:f3e46c3c7074a03fd74a911c2fea78dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-149::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_strukturenelementebesiedelterbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-149', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 149, '7:a3ea62ca272ff25d1018dc5471686ffd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-150::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_temporaererlandschaftsschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-150', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 150, '7:3e444152a76480d6470d0c20797cf21c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-151::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_verpflichtungwrrl ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-151', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 151, '7:0f31f8e78e690fdb0240ab5638d91293', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-152::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-152', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 152, '7:3e9b1da0564fa19ddc77aa7c5615442a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-153::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-153', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 153, '7:dcee9d988f21fa25ebbf0b485e123344', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-154::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_plan ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-154', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 154, '7:16d44c31d213058c215034acfb5dfa84', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-155::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-155', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 155, '7:06e67d8866ef43325b2144499a27e019', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-156::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_rasterplanaenderung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-156', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 156, '7:7e8643588afa664389e9133a0ca26394', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-157::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektbundesrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-157', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 157, '7:c8eddc31dce53477d6e80269bc6b4c1b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-158::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-158', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 158, '7:49074e1498933037e9cc151492ab1dee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-159::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-159', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 159, '7:030e11d70e72ec35039a6f8b8286320a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-160::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigeabgrenzuung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-160', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 160, '7:965ea8125a22359e977babe1ae9824d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-161::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-161', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 161, '7:4ae923bee8b630dbb61e84c84299b550', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-162::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textabschnitt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-162', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 162, '7:46c47c7c7e77d657a9f3051786aebc29', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-163::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-163', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 163, '7:ff847789bc91b4a586036c60b1ed87d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-164::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-164', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 164, '7:431c65059a83bd7a15d2c5e9df740662', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-165::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-165', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 165, '7:d78402adf27af2e8a8c976e4527c6203', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-166::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-166', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 166, '7:8a2fd3d604e5574bb8bbb4b06d6c6070', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-167::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-167', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 167, '7:ad4a8307d87d27f36a31dfc7ec8830e2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-168::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-168', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 168, '7:2d3d7dc2773319a549e6b8b08cd38305', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-169::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-169', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 169, '7:6d5bd37cdff368f2ba6854caa89ee752', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-170::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-170', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 170, '7:5a04f33744af8d86d8ae72cffbcd5369', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-171::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-171', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 171, '7:bf255c270f8a2d636d84b4a7863280ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-172::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-172', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 172, '7:8dd85faaa269cb9a98a38212c68a47f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-173::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-173', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 173, '7:8e8e59fb3759f0864e85e5ca9c8738b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-174::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-174', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 174, '7:73d2179b39a31b0c1847492ab6df80f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-175::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entwicklungsschwerpunkte ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-175', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 175, '7:0597b8bf99110ddbf4b2622a73407323', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-176::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-176', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 176, '7:8319666b7aa0091ba78c53ac1936ea46', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-177::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freizeiterholung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-177', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 177, '7:3b0ab353423ab04058bd393e43584836', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-178::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-178', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 178, '7:5727991a324f80637fcafe277f811226', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-179::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-179', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 179, '7:1d99f873b82a84bdcbbe996537f8fc39', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-180::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-180', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 180, '7:627aceced209d6ae31433da07f23ef20', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-181::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-181', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 181, '7:dbd96eab98c6f3ea5013fc3192beca03', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-182::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-182', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 182, '7:f6a6fad6b1c457c9dad1c66111b0ce8f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-183::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-183', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 183, '7:adaa49b2b60e47432346bf0fcc21f6bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-184::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-184', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 184, '7:3bd9281b46e569a63032b3999190ab44', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-185::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturellessachgut ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-185', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 185, '7:61ecf736386a597956278d779b625b47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-186::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-186', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 186, '7:3a8727331a1bad2e1c1e38be2d46986a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-187::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-187', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 187, '7:391257dc019a76a79d0d1d67ddf3851d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-188::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-188', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 188, '7:10d7c4813d80719f8af9534050f5d26f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-189::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-189', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 189, '7:279679d5bf51484bc8d3f6daec2e636c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-190::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_asb ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-190', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 190, '7:2bb871cf857b88b5b8f43bfc57cab214', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-191::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_aufschuettungablagerung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-191', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 191, '7:f85748db6021804f8ba84e2f2ecc8065', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-192::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_forstwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-192', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 192, '7:7031b2550cd6eeee32e67c2f28cdf71e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-193::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_freiraumagrarbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-193', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 193, '7:7f24839fe7089018f934c558f52b2ec3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-194::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_gib ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-194', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 194, '7:dce20386badf191ad485e5884223fefb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-195::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_grundwassergewaesserschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-195', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 195, '7:6ca7b30fd849a5dca2cfba19412c514a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-196::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_laermschutzzone ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-196', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 196, '7:84395e8f3e60a24f0c22fbf2fd74a1b5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-197::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_landschaftsschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-197', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 197, '7:b09a5d10d0a4f6adb9811b8df288f753', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-198::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_luftverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-198', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 198, '7:834de403d6e850e6bc114262f979b9c5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-199::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_naturschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-199', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 199, '7:179a481ce783e1c96d48edb645c81f9b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-200::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_oberflaechengewaesser ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-200', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 200, '7:b223ff40a802caf5590bcad18ea1e665', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-201::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_oberflaechennahebodenschaetze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-201', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 201, '7:735ce31ce672cc1931aade6dc68add33', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-202::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_schienenverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-202', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 202, '7:da89e52a062f8aa2866ff13f09054b3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-203::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigeinfrastruktur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-203', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 203, '7:6873fc653e0492a352e52e802b557a3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-204::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigersiedlungsbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-204', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 204, '7:10f2285514bb33037c001abdc296fbfd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-205::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigezweckbindung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-205', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 205, '7:3de1fae472959ae05bfce7597a4346c2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-206::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-206', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 206, '7:0f9da9e7802aae8f885d1ffb2aefa59a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-207::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_strassenverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-207', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 207, '7:12feb2a9f80a574a4bd9e5d468faaf52', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-208::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_ueberschwemmungsbereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-208', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 208, '7:319ef13444f6f77110daa1949bf9977b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-209::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_wasserverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-209', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 209, '7:5ff6fa6c78805d72b5b8508957faa792', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-210::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_zeitlinie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-210', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 210, '7:92824d160819bcbff37a6d80f7851112', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-211::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_abfallentsorgung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-211', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 211, '7:24ec0a1f78288ab1a12d5b89734d79f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-212::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_abwasser ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-212', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 212, '7:2242e2795b8fc36379a0d8210b04058c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-213::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_asb ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-213', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 213, '7:f1fc60fc10983141e4f346e84ea3fefe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-214::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_erholung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-214', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 214, '7:955e762a9156a95d43b8cb548f833778', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-215::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_gib ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-215', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 215, '7:0ae72688b9e3013a8b3b76e3c7f60cc7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-216::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_landschaftsschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-216', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 216, '7:d8131673e4f9e5f4639e0a3df5321677', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-217::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_luftverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-217', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 217, '7:2b9558cf44073e22c96909dd35b7c725', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-218::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_naturschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-218', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 218, '7:b1c0e6685db52365e3e39998fa81a319', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-219::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_oberflaechennahebodenschaetze ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-219', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 219, '7:9e1023a395413f331355eee6d82b1578', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-220::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_regionalbedeutsamerwanderweg ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-220', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 220, '7:3c09a5937a4fbfb50348db6a87853784', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-221::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_regionalbedeutsamesportanlage ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-221', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 221, '7:ce5b0fa9833824c0618e3b7ae6feb820', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-222::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_schienenverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-222', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 222, '7:4d2eac724e9c30914327da08a32eb103', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-223::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_sonstverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-223', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 223, '7:565ada852b7cf8ac5c4fb7dd3af434da', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-224::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_strassenverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-224', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 224, '7:d15be625041b58b047cc666655a15c54', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-225::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_tiefliegenderohstoffe ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-225', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 225, '7:b947fd6fecbb4a7757279c0a2411d61d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-226::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_tourismus ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-226', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 226, '7:5d6620818d44cc2d08fa2ae4737c8fcd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-227::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_wasserverkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-227', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 227, '7:646366a3d9e9107f951b8df4583cc6fb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-228::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-228', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 228, '7:c61f6e67b80acecadd00564df56898f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-229::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rasterplanaenderung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-229', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 229, '7:9d72937ab9bba3c9b49d671627b447b0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-230::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-230', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 230, '7:1af5dbb4cbfea5e4f0bb5f162ac80d48', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-231::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoffsicherung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-231', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 231, '7:462117b582035866920e630accebe3ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-232::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-232', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 232, '7:c5d142a2d38f216661c3b7936f2896a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-233::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumstruktur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-233', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 233, '7:b0cbd48678f54c67a39d6cca797f9364', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-234::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigesiedlungsstruktur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-234', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 234, '7:e3f46634f24de73e0cf5dba577d5de0d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-235::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-235', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 235, '7:98671b5093b683f3f0526c8fd210d3c7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-236::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-236', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 236, '7:bb538e0641a068810c1962656e82060a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-237::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_textabschnitt ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-237', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 237, '7:b7dd2e0e44fdb70f1478c225520515b8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-238::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-238', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 238, '7:fd67827c0b4203b28294ea5054aa3a32', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-239::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_vorbhochwasserschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-239', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 239, '7:0a07eaee702fbafac9b1abb2ca83d203', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-240::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-240', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 240, '7:1a4249f7ba047d68fce5d7e58caf62a2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-241::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-241', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 241, '7:2ad70bff5695c83d53b2d160299c7ebd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-242::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergie ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-242', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 242, '7:b88bf2ca142a35a58ad9ab3ce77ea6b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-243::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergienutzung ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-243', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 243, '7:bba9a26ca49ca42156816a84f4caa1a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-244::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-244', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 244, '7:cb5dbab610b0061bb592d6ba56093b1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-245::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bereich ADD xplan_wmssortdate date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-245', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 245, '7:aa399c9b04a8a7a209be7a94fd092ead', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-246::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-246', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 246, '7:3174b6f7fd0d86c1e28e4449655091d3', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-247::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-247', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 247, '7:cf24a0bffe674013d7ffc94c88bf5ec7', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-248::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-248', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 248, '7:4bf17bc02a5598c3ae341dac8bbf1a2e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-249::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-249', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 249, '7:fe4cdc8a677bc83a7b74074e460acef8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-250::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entwicklungsschwerpunkte DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-250', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 250, '7:b7c807abffcbed1307c6e780ae5dcfc4', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-251::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-251', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 251, '7:062879312021601db2f045761b0a0394', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-252::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freizeiterholung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-252', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 252, '7:612956970acafd8aa8a98b6d008bf58d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-253::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gemeindefunktionsiedlungsentwicklung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-253', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 253, '7:855448cd0e6456f9b6c5d78ca6f95a59', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-254::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-254', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 254, '7:6950fe8c4fa9612d2c53c59fe1ef1204', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-255::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-255', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 255, '7:450c6936545d6db0998c5275ea9aaee6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-256::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-256', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 256, '7:f451a7c5f39402d84a2596f80597fa15', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-257::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-257', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 257, '7:e20122d2078030f53e1a38de8e8f8d90', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-258::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-258', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 258, '7:0d0afe602aa9617cc470d5ad560fe3b4', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-259::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-259', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 259, '7:e2372de36ba4e943915193469b26232b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-260::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturellessachgut DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-260', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 260, '7:d8fbc8db4a6918f6872cb9ea64adbfa8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-261::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-261', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 261, '7:2937e03e6d5e10d99a86be42b381deb9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-262::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-262', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 262, '7:16efcbe3b8700fb3cadd058077dc3d04', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-263::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-263', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 263, '7:c008fc5aa3a80a3db4a9fe6b0cf8bd9f', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-264::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-264', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 264, '7:80935d0e9077d21fc3ce87a118507fb8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-265::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_asb DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-265', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 265, '7:196cff05d104fe4d7583b46c5183ca5c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-266::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_aufschuettungablagerung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-266', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 266, '7:0beb0970b16545dc21a1f483d037aa7a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-267::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_forstwirtschaft DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-267', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 267, '7:0714158c5646181d0927ae8c30cfb709', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-268::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_freiraumagrarbereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-268', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 268, '7:d5297e2245f419c68a3ecad7fbf5d758', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-269::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_gib DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-269', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 269, '7:d7c0442cb7a43d0c248cc3c3c690a0b6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-270::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_grundwassergewaesserschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-270', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 270, '7:01ae31559f130bde8163307b81339224', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-271::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_laermschutzzone DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-271', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 271, '7:9f4282dfa31a6874eb1e08dc51350769', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-272::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_landschaftsschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-272', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 272, '7:bfa3c7fb25218aebfff62f03a14c1509', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-273::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_luftverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-273', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 273, '7:80df6901370d5891236c3d9210c76068', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-274::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_naturschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-274', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 274, '7:fa4ec0fbc06cbe6d6f1e3d4b26d3a25b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-275::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_oberflaechengewaesser DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-275', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 275, '7:cb4979788a36202e4527df2301cff7d6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-276::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_oberflaechennahebodenschaetze DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-276', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 276, '7:dc8a3b0e471fa94d1a6fa8e9e4133ea1', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-277::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_schienenverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-277', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 277, '7:ea9cb9846468f952d1a2d0fa83e2d4ef', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-278::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigeinfrastruktur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-278', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 278, '7:387f0c1985008f2d2b5a7a36f39fc0fa', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-279::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigersiedlungsbereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-279', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 279, '7:6db50b8da759e32c72dffb1a15330630', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-280::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstigezweckbindung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-280', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 280, '7:c2624d7af886ff5ba9628d2df4174df7', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-281::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_sonstverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-281', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 281, '7:228c6d61f64806b089c3c26fb370898b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-282::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_strassenverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-282', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 282, '7:427e9ecf3c71372731e90835a36a8c78', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-283::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_ueberschwemmungsbereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-283', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 283, '7:941b0c6c0e241d3b5d2120b7aab85f1e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-284::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_wasserverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-284', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 284, '7:5a7b31dfd2f54788128459bf531e5ac2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-285::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nrw_zeitlinie DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-285', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 285, '7:aa6799f87cf23ba1ff803138c794c5c7', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-286::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_abfallentsorgung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-286', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 286, '7:da9e2e22549e211de1a58f0dded0389b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-287::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_abwasser DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-287', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 287, '7:fb20c87e4984e05557746c57ab59d62b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-288::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_asb DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-288', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 288, '7:22189c925144f5b57da871364227744d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-289::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_erholung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-289', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 289, '7:1dcd55124c8e3a1af98d7147d88b1a94', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-290::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_gib DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-290', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 290, '7:d72ff960d503ed14718ef873caf6f74f', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-291::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_landschaftsschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-291', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 291, '7:9037afdd83b5a78a6ffb519e0d6d1046', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-292::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_luftverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-292', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 292, '7:731926d140df18dc3c3dc879ef809edf', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-293::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_naturschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-293', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 293, '7:75302c8097875560ea7e008b0915268c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-294::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_oberflaechennahebodenschaetze DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-294', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 294, '7:80f5246e0eda48ca8c3cf9c6bfd8f40e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-295::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_regionalbedeutsamerwanderweg DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-295', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 295, '7:56a22abe6b954057b5f7cff866f09b74', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-296::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_regionalbedeutsamesportanlage DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-296', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 296, '7:2e79a034cefc21b062303a28cc10a33e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-297::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_schienenverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-297', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 297, '7:7ddb780f86a7553c93a6db8dd2c01eaa', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-298::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_sonstverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-298', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 298, '7:6de88f3da277b926f7b4e942ccc3266c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-299::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_strassenverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-299', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 299, '7:b785d814d2fe957773508cac088e12a5', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-300::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_tiefliegenderohstoffe DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-300', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 300, '7:a27016703c30dfe4ba563365396907e8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-301::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_tourismus DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-301', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 301, '7:934bddd4988b51fb8412f5beaba8a650', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-302::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_nsm_wasserverkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-302', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 302, '7:c4f364f36c135f64f27f39d5d2199b27', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-303::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-303', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 303, '7:0471a1943d84ffc9b642daa7f1785398', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-304::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoffsicherung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-304', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 304, '7:c66d21c50ac416e07668872f3d55e7a7', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-305::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-305', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 305, '7:82d217fd11f5f8bdb589b61c336c0f3b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-306::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumstruktur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-306', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 306, '7:9db6bdcf44aff94d9d60bbb04545c724', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-307::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigesiedlungsstruktur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-307', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 307, '7:cbdd962b698ce3452509cf1c0f45c2ec', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-308::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-308', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 308, '7:55392c97891ece418618c68d8a7e304d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-309::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-309', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 309, '7:c16d6c687ee3205c5c825cd14b31457b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-310::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_textabschnitt DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-310', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 310, '7:edb1c260c4cb5a236ec337cdd1f69778', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-311::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-311', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 311, '7:88c7296e4428a441493455bb3d744060', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-312::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_vorbhochwasserschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-312', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 312, '7:925934ea7e3e17a807b6263094f2d5c2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-313::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-313', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 313, '7:e82bc81299e54d047f682f513abf7fbb', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-314::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-314', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 314, '7:9c729c41f1734ba0a0dc77b5be4f7e3d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-315::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergie DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-315', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 315, '7:73ee51f2d0dbcf2c064e63f8d045c723', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-316::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergienutzung DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-316', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 316, '7:bc5920c2014010d9ba3a50d312e0ac17', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-317::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-317', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 317, '7:380b19a584fea7be882ff4a14c1078d2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-318::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bereich DROP COLUMN xplan_datumdesinkrafttretens;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-318', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 318, '7:b7431d64fc708a75aab3b945841b4a75', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-319::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_abgrenzung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-319', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 319, '7:5c5050e598b6d8fb5355f553c38b4bc9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-320::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_allggruenflaeche DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-320', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 320, '7:15846290f012554702a0463a7282c9a6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-321::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-321', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 321, '7:7eb3f52ca23eaf38a276d6f142527aea', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-322::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-322', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 322, '7:3d3f30e22b227f9ac7379bc90a963fc3', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-323::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bereich DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-323', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 323, '7:7bed7674ee42a4807c6c7cab6814ecd3', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-324::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-324', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 324, '7:40272f748dc72e68f1b0db6265cd2d96', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-325::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-325', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 325, '7:07110e00307b556396d3fb622cb83f91', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-326::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_denkmalschutzrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-326', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 326, '7:753c93fd87f833d5fc6f9f96a9d65cc9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-327::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-327', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 327, '7:9d16dfe12676f495b2c0b1ffd2645d69', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-328::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-328', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 328, '7:ca5b5b730295ca3fb05eb4ec87d9d408', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-329::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-329', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 329, '7:a75fdcd29aeebc0ea4145c52f98559be', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-330::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-330', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 330, '7:c81cabad41ae25de883fc99de185094b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-331::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_biotopschutz DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-331', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 331, '7:6c016a0f1e8e22b777fad64f9891884b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-332::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_brachflaeche DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-332', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 332, '7:d680486082ec61122d94502be2a7b2e1', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-333::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_elementekulturlandschaft DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-333', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 333, '7:da9d6768e710c13f1389fc3e3e952ea0', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-334::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_forstlichefestsetzung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-334', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 334, '7:bbe194912e95a7b67f59e682c5c95bec', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-335::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_herrichtunggrundstueck DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-335', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 335, '7:88d4ed93168d10bf10e033acaec62b24', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-336::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_pflegeanpflanzung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-336', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 336, '7:ebe4faec6cafaa8a97bec0ce4e6c6a99', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-337::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_pflegelandschaftsbild DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-337', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 337, '7:26c35a8351bda707835c9c17947d368c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-338::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_schutzobjektlandesrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-338', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 338, '7:225181ab6ee769ff637acff008562978', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-339::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_sonstigemassnahme DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-339', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 339, '7:19c5e66ac3586f283674f0e10272fb4e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-340::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_strukturenelementebesiedelterbereich DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-340', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 340, '7:83d3e87686a16a3e2efaf7be91ae56bc', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-341::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_temporaererlandschaftsschutz DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-341', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 341, '7:94c563dc2d3665cc247f2e782a815168', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-342::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nrw_verpflichtungwrrl DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-342', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 342, '7:e753d30a807ec22ce2144a99eace2187', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-343::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-343', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 343, '7:24495dfb3fc101fc75365989acee0ab2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-344::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-344', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 344, '7:40cb6d494e3c8234d6695d7576b93fe9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-345::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-345', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 345, '7:eb29831dbb8f3f7ef2652465c75ab8fd', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-346::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektbundesrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-346', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 346, '7:bf37dbfb746c49b18ab1f335133c5cdb', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-347::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-347', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 347, '7:be914e15e024d67910a576fd45a84948', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-348::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-348', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 348, '7:4e12bf98d6e44ebe42336f24432dbbf4', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-349::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigeabgrenzuung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-349', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 349, '7:be909ef513d89d81ea7e3c7a60f3a0a1', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-350::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-350', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 350, '7:3fc91475e40a7eb3f6ce0290a223e793', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-351::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textabschnitt DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-351', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 351, '7:d8b324316bc1b8774cca5996f1f67524', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-352::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-352', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 352, '7:e9662242a051a5226468324edad27917', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-353::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-353', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 353, '7:d00a44e471d44b1f3caf082fdf96df56', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-354::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-354', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 354, '7:55266cff33023b470f33591fe5fa939a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-355::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-355', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 355, '7:ad850b1a398fd4e96aaff7bb5e9e2c56', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-356::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-356', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 356, '7:ca7a2e09306c9a6b5ab8dc8d69fa7fa9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-357::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-357', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 357, '7:4d571e82b3b34344112e68443d7e61d6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-358::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung DROP COLUMN xplan_inkrafttretendatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-358', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 358, '7:7387d3d164f428e8b1959ec45f1f0baf', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-359::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abgrabungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-359', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 359, '7:d1fa33edb22670d1e994dd29c43086f4', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-360::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-360', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 360, '7:3cabaf996ee79ce0f8cd299764c1fe33', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-361::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-361', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 361, '7:9ee7d0b35689c6039fa3171013d9804e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-362::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-362', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 362, '7:1e6658afdbc644f56abca3acd52b98e0', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-363::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_aufschuettungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-363', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 363, '7:16988922a8f8259fb88e3dc41eec3ebf', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-364::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleich DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-364', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 364, '7:0c2cb76ec256eb56d566d66a8c7681c0', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-365::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-365', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 365, '7:767720d7157c6f50f5b0624c69a25d95', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-366::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-366', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 366, '7:afe27d1d909b171359dd81c634c43ecc', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-367::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bahnverkehr DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-367', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 367, '7:829ef35b5f0ec59d90701980e75e4b09', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-368::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebiet DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-368', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 368, '7:617b0426cfebe2ea6289b0f190680581', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-369::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-369', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 369, '7:0e1804c2fa8c4afd6ec8911e9676e049', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-370::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugrenze DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-370', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 370, '7:1b6db934dba51194e354f6c3e12362d5', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-371::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baulinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-371', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 371, '7:4bae3bd31ae0602eba002037718f386a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-372::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bauschutzbereich DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-372', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 372, '7:53246713f8ac187a4b0843dfe6021d13', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-373::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereich DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-373', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 373, '7:0c1f531a53df188eb920711d70c190f6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-374::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereichohneeinausfahrtlinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-374', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 374, '7:048bb9d1f3cecef5e64d8b2e64c52359', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-375::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_besonderernutzungszweckflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-375', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 375, '7:3b3713d289c261d1f3e4667bac64a0f8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-376::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bodenschaetzeflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-376', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 376, '7:f993e11333ee6f66928aa72ce23532ca', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-377::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzeinzelanlage DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-377', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 377, '7:dad63425cea339b78132ac15f6d1ded9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-378::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzeinzelanlagepunkt DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-378', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 378, '7:dbeba20df2cc57e7489196ca3f5ac374', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-379::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_denkmalschutzensembleflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-379', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 379, '7:945c0839bf7472a303f37a1997eb0397', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-380::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtpunkt DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-380', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 380, '7:0aaffe5de3ecd6ad584c61b1986256db', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-381::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtsbereichlinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-381', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 381, '7:e01ea22f9125056ab723eee9d13eda51', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-382::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_eingriffsbereich DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-382', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 382, '7:aaab74bcf831172839d1c2cbdb73df1d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-383::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_erhaltungsbereichflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-383', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 383, '7:c03861f91c144cdde5c4070084eaadcb', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-384::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_erneuerbareenergieflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-384', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 384, '7:3dfc51fedd664eebdcfaf5713f4c970d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-385::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_fachgesetz DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-385', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 385, '7:cd002345401b5b3267d24d44df0bf261', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-386::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungenbaugebiet DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-386', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 386, '7:85b8b079452ccda10234eb28e6a79124', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-387::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-387', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 387, '7:2a484db39a773b8522dadf21c18a0fff', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-388::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_firstrichtungslinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-388', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 388, '7:f40fbd50bf3f750f1321ac54c669bf55', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-389::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_foerderungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-389', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 389, '7:6d4c3574205016daec1bdee6db05c1a2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-390::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_freiflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-390', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 390, '7:b86206b906c9fa0a89eb712ca92ac0f3', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-391::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gebaeudeflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-391', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 391, '7:e32cf871e12d354b228ed20d7a56a11d', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-392::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinbedarfsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-392', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 392, '7:aadc4be4e0a293a3d7e339dbaf7c8cdf', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-393::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-393', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 393, '7:fcc8591a871738e6b2f269f271c698c0', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-394::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-394', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 394, '7:d54525c6cd85ea06f09e30e453383fac', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-395::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-395', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 395, '7:3dd573026430bf4ef2322a48fd789469', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-396::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gestaltungbaugebiet DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-396', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 396, '7:995abf558dca3cfdea0e552f9e81fbec', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-397::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gewaesserflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-397', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 397, '7:299a6fc451f677fc6fa20aae55b686a2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-398::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_grabungsschutzgebiet DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-398', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 398, '7:31bd649d3c37b3ae76d6d72079bd9a8b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-399::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gruenflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-399', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 399, '7:79cb5e8a48b7abf12a10719457b6b03a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-400::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-400', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 400, '7:1ba4d8f379b775d29ac5dc30282776a2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-401::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenpunkt DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-401', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 401, '7:4453e43ab298f99b99c5c425d6bb1efb', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-402::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-402', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 402, '7:edf92e250de99fadfb0bc67ee30c24b9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-403::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kennzeichnungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-403', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 403, '7:140311fd54e918890c9b70b615b7e03c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-404::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kleintierhaltungflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-404', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 404, '7:220f7f65f0ea15ada8b33ed7a1e52849', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-405::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_laermschutzbereich DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-405', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 405, '7:7b8dc62c1c02eaaefc4e9128b4d5d079', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-406::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-406', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 406, '7:36df81dd27fccda8dc786ac9a75493cd', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-407::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaftsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-407', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 407, '7:819491705fa409cb6ee0196d70a72c58', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-408::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaftslinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-408', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 408, '7:e86b2378a0ffc1a4c19cc6f216ef22dd', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-409::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_luftreinhalteflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-409', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 409, '7:3c7f971e973179cb8d37b7a4101b880c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-410::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_luftverkehrflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-410', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 410, '7:fa6f1f340e9e99a63a49c55713fa9e3e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-411::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenausschlussflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-411', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 411, '7:097d8c044f14f370784ba0ab05cf43b3', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-412::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-412', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 412, '7:7bad4060f4c906e86188e799b7c55966', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-413::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nutzungsartengrenze DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-413', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 413, '7:57cc773a2e056c020b4038b38c978b0f', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-414::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_persgruppenbestimmteflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-414', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 414, '7:34af1c6968e6f81c1201dcfc40b46d5b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-415::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_regelungvergnuegungsstaetten DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-415', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 415, '7:034f11a0ccf18f46a70546f0c5a86913', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-416::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_rekultivierungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-416', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 416, '7:52545dcb1e886f355f13375425acb93a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-417::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzgebiet DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-417', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 417, '7:d4c8f2fa34dadabbdfc3df45cc41d38e', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-418::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-418', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 418, '7:560292e0fae9d63645e68246fe4a86cd', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-419::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-419', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 419, '7:d8ab4efb94e6340cede9f6697d3dd9fb', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-420::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_speziellebauweise DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-420', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 420, '7:05ce49536081163b4920fef20aec30a1', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-421::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_spielsportanlagenflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-421', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 421, '7:095bdfc955128da2371657eea2c86b39', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-422::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenbegrenzungslinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-422', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 422, '7:c9f031c8f7ca07c75e0c1420d9b53e0c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-423::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-423', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 423, '7:d3c623c17e21a195a9321852a1dab579', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-424::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenverkehrsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-424', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 424, '7:3c8fd07c3a9dbada620eb2c799aa972c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-425::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_technikbestimmteflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-425', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 425, '7:7ff86cfe71a7784500bb13d15ee4dc59', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-426::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_textabschnitt DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-426', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 426, '7:604fbe0d6b27a513f5360ee5183cf013', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-427::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_textlichefestsetzungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-427', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 427, '7:b18691c8a07b64c3f4b12df605670e5b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-428::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-428', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 428, '7:d68505d4d59c2aabe97e26d4cbcc5f25', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-429::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-429', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 429, '7:a13d9174f5e4879d22766dd2de93e2ea', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-430::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_veraenderungssperre DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-430', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 430, '7:5e18fa51b9f4cfd4df3317d66d73d042', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-431::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-431', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 431, '7:c90c13cab5c8c5540f4c7d7f7dc3013a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-432::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-432', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 432, '7:ee8895f61593baf441de20084b042eda', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-433::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgungsleitunglinie DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-433', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 433, '7:bb7656174b4500522b9d929ca4d1256c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-434::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verkehrsflaechebesondererzweckbestimmung DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-434', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 434, '7:b54959d5543f1046d27d239666c60804', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-435::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_vorbhochwschutzflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-435', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 435, '7:59f59e2b27dd5d9e2ce4698d828ed4aa', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-436::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_waldflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-436', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 436, '7:db7bfc09c6bba23ff6e5350bd764cc6c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-437::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wasserrechtlichefestsetzungsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-437', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 437, '7:01357eb4da98fed79eaf243cff6d3542', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-438::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wasserwirtschaftsflaeche DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-438', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 438, '7:f319e873bc165715b0d264b423ee1110', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-439::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht DROP COLUMN xplan_inkrafttretensdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-439', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 439, '7:fb30f19cba430bc48c2a8f00d3b846ec', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-440::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-440', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 440, '7:002e30016bbe97461b1946e2d0926c15', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-441::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabungsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-441', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 441, '7:9c5e3fc06fc9c2c60c2a077f90b49828', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-442::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-442', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 442, '7:c21e00679557ac459b2d27bc7339cd24', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-443::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-443', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 443, '7:48e4631438256aa5366c600a1579147b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-444::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettungsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-444', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 444, '7:24c70774570a02bda515e870b404af6a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-445::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-445', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 445, '7:f876e9abf6bb913df18f618231ccaaf6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-446::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bahnverkehr DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-446', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 446, '7:7ad4d077c1b950cf5137dd546546b561', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-447::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bauschutzbereich DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-447', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 447, '7:ca37d5bdf2e2e19fed2a00cf603737ea', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-448::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bebauungsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-448', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 448, '7:f68850792068cfba3494c14cbdfc7f20', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-449::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bereich DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-449', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 449, '7:7001df43db8cb1eb518105245cab1ca6', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-450::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-450', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 450, '7:2604926deda3eec6bddf0feee8f21921', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-451::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetzeflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-451', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 451, '7:0d783886822d424a9177bf28bf59b482', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-452::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_denkmalschutzensemble DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-452', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 452, '7:e3ba8a018bd2be63736cb463014347c9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-453::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_erhaltungssatzung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-453', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 453, '7:f8bc0e5f30cbebb5c58a61bb12f845c8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-454::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_fachgesetz DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-454', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 454, '7:15faf486129e91bc09421de2ed2989c5', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-455::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-455', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 455, '7:2640cde4b7c8c98ce5ece7b4c2dd4a38', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-456::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-456', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 456, '7:07026d8a563d28a60e67fccd34ff4486', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-457::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-457', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 457, '7:f075ec81fd7f6d052e7d148962d87952', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-458::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_grabungsschutzgebiet DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-458', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 458, '7:7436393ad22097dacfa41df4c8bf372c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-459::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-459', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 459, '7:43e59d61f9adca214c3243dae2487052', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-460::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_keinezentrabwasserbeseitigungflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-460', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 460, '7:a05829e89959b8ff230e15a7b5c8a50a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-461::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-461', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 461, '7:31b88496a11701585c89bf9ad79c8de1', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-462::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_laermschutzbereich DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-462', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 462, '7:7d6694962b38ed7fd8453562c5dca9ab', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-463::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_landwirtschaftsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-463', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 463, '7:6a470aa1b349ad7fd64ee25014bc2860', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-464::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_luftverkehr DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-464', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 464, '7:b21c01394fda9e0955ad17044c8478b9', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-465::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_nutzungsbeschraenkungsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-465', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 465, '7:dc61be1fe642914a01c0844dea643a5f', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-466::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-466', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 466, '7:9b718ea4911f6ed6017e05943de26936', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-467::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_priviligiertesvorhaben DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-467', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 467, '7:ef3e74cad18a0e9cbe1f384a6f517036', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-468::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzgebiet DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-468', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 468, '7:95c0cb49b8cd38ba7845b85ceeb365b0', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-469::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-469', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 469, '7:59e6e70d51c45f1e1417ae0f2233c0d8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-470::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-470', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 470, '7:bfc503ebfd072375b4063908938f97f2', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-471::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-471', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 471, '7:54e944c4abecdb9c3e77f06c8681220b', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-472::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textabschnitt DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-472', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 472, '7:8dce588aa0ef50b2f217ecc534c2538a', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-473::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textlichedarstellungsflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-473', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 473, '7:8e1939b1d416ca00d245f47ac31f80c8', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-474::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-474', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 474, '7:ec2b89fa2cfdee87a6527884e022f506', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-475::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-475', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 475, '7:9446564960fe64333df16032f72f0444', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-476::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbehalteflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-476', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 476, '7:53c34d0e89577ddb3b3f18055ba1ad30', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-477::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbhochwschutz DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-477', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 477, '7:1be90cd0e9043bd81935b37216fe5119', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-478::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_waldflaeche DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-478', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 478, '7:03272fcc3e5287f51c6f83970f520077', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-479::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserrecht DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-479', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 479, '7:3b8e1d4500e4dcb8b437faf7adfa9f3c', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-480::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-480', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 480, '7:ec5c958ff57055a950399662fd3b4598', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-481::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich DROP COLUMN xplan_wirksamkeitsdatum;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-481', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 481, '7:eec3871c4fb94e79d65e30169a250aaf', 'dropColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-482::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_fpo ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-482', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 482, '7:5e1a59b2a4ec744384d9b661163b63a3', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-483::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_lpo ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-483', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 483, '7:a0817395d6827a474d9168cdc60e4da1', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-484::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_lto ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-484', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 484, '7:2384cc55fd6672aafad9e13bc9eb8810', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-485::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_nutzungsschablone ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-485', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 485, '7:a2e0731f1fe08eefe7c6692dfb147375', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-486::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_ppo ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-486', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 486, '7:6e1cb2e4d1d8fa80cf1c7ed6b574a7ce', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-487::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_praesentationsobjekt ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-487', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 487, '7:5cb844972f0be97add10b99581d6cd1a', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1458746541113-488::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_pto ALTER COLUMN xplan_index TYPE TEXT USING (xplan_index::text);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1458746541113-488', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 488, '7:4c67c92e673b90606f904e8c77df602f', 'modifyDataType', '', 'EXECUTED', '3.3.2');

-- Release Database Lock
UPDATE xplansynpre.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

