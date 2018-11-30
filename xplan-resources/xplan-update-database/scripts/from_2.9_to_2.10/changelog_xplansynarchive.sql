-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 29.11.18 12:31
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk29
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk.fritz.box (192.168.178.152)', LOCKGRANTED = '2018-11-29 12:31:17.327' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynarchive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansynarchive.xml::1543491004010-1::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abgrabungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-1', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1, '8:683306063bd6dbf5735079ae57f92428', 'dropTable tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-2::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abstandsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-2', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 2, '8:35e731dd5cbf7cd0e0fad7fa890b6c1e', 'dropTable tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-3::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abstandsmass;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-3', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 3, '8:44332317b688fd2d7a0776b152b61ae7', 'dropTable tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-4::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-4', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 4, '8:dc69580bd76daff245ba9137b3bd4c65', 'dropTable tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-5::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-5', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 5, '8:08467785c5886959f2b0ec2ff9d78e41', 'dropTable tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-6::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-6', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 6, '8:9f33fd9f973e3ac53779873de3be5b88', 'dropTable tableName=xplan_bp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-7::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleichsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-7', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 7, '8:a0029bbd5ee128ef6874a24031a3bd04', 'dropTable tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-8::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-8', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 8, '8:d4db989ff5eacff4228b48b6e4a6c9af', 'dropTable tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-9::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bahnverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-9', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 9, '8:34a17fbc6a189ceea2817a061440e44d', 'dropTable tableName=xplan_bp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-10::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-10', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 10, '8:c0f4310d375354c46b4b19f6f36fbc86', 'dropTable tableName=xplan_bp_baugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-11::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-11', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 11, '8:d7fd266458424788fc36a313a73bbe5b', 'dropTable tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-12::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugrenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-12', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 12, '8:2d155a3b4c99c3b748a90d1f2b4ff142', 'dropTable tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-13::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baulinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-13', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 13, '8:48fa71642db3d5449df201b473db1b58', 'dropTable tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-14::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bauschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-14', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 14, '8:b674b3fdf28295d2aa7eff83ef0ab0dc', 'dropTable tableName=xplan_bp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-15::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-15', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 15, '8:5649c4737b0f14355b2de221d1e0a52d', 'dropTable tableName=xplan_bp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-16::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-16', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 16, '8:e11485387156cd1291a316f664a43309', 'dropTable tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-17::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-17', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 17, '8:e37b9855b5ceec527c27bc91a22f241f', 'dropTable tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-18::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-18', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 18, '8:5506754d2f737aff18f14cd1b8e91b57', 'dropTable tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-19::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzeinzelanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-19', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 19, '8:357134e5d20ff77d3e13a26ebedeefcb', 'dropTable tableName=xplan_bp_denkmalschutzeinzelanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-20::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzeinzelanlagepunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-20', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 20, '8:e2b20c400e1232d34d0e384b487906de', 'dropTable tableName=xplan_bp_denkmalschutzeinzelanlagepunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-21::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzensembleflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-21', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 21, '8:a5421a9324cf75868fa194948ef6d28c', 'dropTable tableName=xplan_bp_denkmalschutzensembleflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-22::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_einfahrtpunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-22', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 22, '8:b64d74d4a1230e24270e029d947739af', 'dropTable tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-23::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-23', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 23, '8:9f37d8834567cf325e2ab4aadbad8fc5', 'dropTable tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-24::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_eingriffsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-24', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 24, '8:d00398a2a0b4ee90e0afaef3b5089faf', 'dropTable tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-25::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-25', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 25, '8:e31f10687fc67017906ea807c8207232', 'dropTable tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-26::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_erneuerbareenergieflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-26', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 26, '8:0040a3e15cf39fca1c3b5c4742035b44', 'dropTable tableName=xplan_bp_erneuerbareenergieflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-27::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_fachgesetz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-27', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 27, '8:5a2ee3735cb81e129ccb04aa93834c49', 'dropTable tableName=xplan_bp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-28::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_festsetzungenbaugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-28', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 28, '8:57952ebb15b7dc231bf5a264e7d9a484', 'dropTable tableName=xplan_bp_festsetzungenbaugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-29::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-29', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 29, '8:2805f935b8c5a02a06f1b07235d6a8fa', 'dropTable tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-30::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_firstrichtungslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-30', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 30, '8:591983c4e8fa72e2bdccb7b84d1567f7', 'dropTable tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-31::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_foerderungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-31', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 31, '8:7c2135920d2cd26b1d0aa6037f8f099a', 'dropTable tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-32::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_freiflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-32', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 32, '8:f8919cbc6d73d57ef0415e73fa8fd25f', 'dropTable tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-33::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gebaeudeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-33', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 33, '8:64eacbfb061567fb9ea03520faab5320', 'dropTable tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-34::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-34', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 34, '8:a5ee4e98f0ef56f1ea6d55ab0dc387f6', 'dropTable tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-35::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-35', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 35, '8:4fcfc3415dc6fa7524270a00f7b6205e', 'dropTable tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-36::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-36', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 36, '8:b4146902df76393d1cda2b4c4fa0257b', 'dropTable tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-37::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-37', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 37, '8:8cd4b8f0602499121e62e52033c52766', 'dropTable tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-38::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gestaltungbaugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-38', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 38, '8:745b83a994efac5084ca3f5475b9074f', 'dropTable tableName=xplan_bp_gestaltungbaugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-39::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gewaesserflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-39', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 39, '8:a2b45b9488d43a20f1f012b7350ade8f', 'dropTable tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-40::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_grabungsschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-40', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 40, '8:3c3382e80e85e23cb3ccdabc2dbd1e86', 'dropTable tableName=xplan_bp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-41::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gruenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-41', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 41, '8:e082d735fb2d270bd23cff18f0a1a697', 'dropTable tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-42::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_hoehenmass;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-42', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 42, '8:1d3666b1be9c0a4702cdd474f4c59e79', 'dropTable tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-43::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_hoehenpunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-43', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 43, '8:1b04883ec0bb9d5dee3c18f58d3c9d6d', 'dropTable tableName=xplan_bp_hoehenpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-44::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_immissionsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-44', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 44, '8:b9ece06312084e31fabd6ea2cf3fa570', 'dropTable tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-45::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-45', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 45, '8:7fe01bba5640a261b5b82aa418e85156', 'dropTable tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-46::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-46', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 46, '8:33933102241e0733917b8ee51fbb2086', 'dropTable tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-47::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-47', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 47, '8:6896c7f04f321ca2eb3f83100101faaf', 'dropTable tableName=xplan_bp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-48::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-48', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 48, '8:503f297b518647377936143517ba6021', 'dropTable tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-49::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-49', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 49, '8:3a805b672867a2be6d63be4fa083f9c2', 'dropTable tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-50::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaftslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-50', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 50, '8:e61100729852a5f57aee40b0d01f0a2a', 'dropTable tableName=xplan_bp_landwirtschaftslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-51::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_luftreinhalteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-51', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 51, '8:62df998a2ce5c6cf8fe4ca34497a9e3d', 'dropTable tableName=xplan_bp_luftreinhalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-52::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_luftverkehrflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-52', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 52, '8:2678ebb28057e9f384b02dbef9fca7cc', 'dropTable tableName=xplan_bp_luftverkehrflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-53::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-53', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 53, '8:85e798d0fbad56267a9235871e35a7be', 'dropTable tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-54::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-54', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 54, '8:36a32c0b5b322d78748182f73e0453f4', 'dropTable tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-55::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nutzungsartengrenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-55', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 55, '8:fe9aafdbaf72c222dcdae4b42b7e2d22', 'dropTable tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-56::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-56', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 56, '8:f22f908dbd8eb8a20b7a334c5828bac1', 'dropTable tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-57::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-57', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 57, '8:29b2e22e907160785f9d9b366fd1e543', 'dropTable tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-58::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-58', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 58, '8:df49a32a4487dff05520c35a69013245', 'dropTable tableName=xplan_bp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-59::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-59', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 59, '8:ff1a4411690b4cb28dc4261801fbdb83', 'dropTable tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-60::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-60', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 60, '8:904f9f1e7a0b61e5abc3b92ddfb88696', 'dropTable tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-61::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-61', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 61, '8:f23e593c3fd34e915e1b2c516e0f89cc', 'dropTable tableName=xplan_bp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-62::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-62', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 62, '8:e38bea4864b6f640b1bf1e635d5816ef', 'dropTable tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-63::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-63', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 63, '8:8f3a0695aeb6600cfc45b450da958987', 'dropTable tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-64::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_speziellebauweise;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-64', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 64, '8:23d94d2458edff8423dce33e700ae930', 'dropTable tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-65::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-65', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 65, '8:94aa63da3c29a6ad7a6aa4f308eb0265', 'dropTable tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-66::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-66', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 66, '8:8424d161693b09dfde2dede1dbb6e3b1', 'dropTable tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-67::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenkoerper;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-67', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 67, '8:0c64c13b259f8ca56485a49df9b5797f', 'dropTable tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-68::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-68', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 68, '8:f023bc17c1c1594de34aa0b4226b082c', 'dropTable tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-69::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_technikbestimmteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-69', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 69, '8:798bf3547e7a1adb5b9a90beaba00603', 'dropTable tableName=xplan_bp_technikbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-70::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-70', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 70, '8:e8f1abdd3a2bdcdc2d6201e773d3bdea', 'dropTable tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-71::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-71', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 71, '8:c55aa95d3545d267c9d912369096f32c', 'dropTable tableName=xplan_bp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-72::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-72', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 72, '8:eef445883406f0bcbd9aafa252c8c6e3', 'dropTable tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-73::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-73', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 73, '8:b5c60a0ac65ffa142d5a03f41610f3e9', 'dropTable tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-74::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-74', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 74, '8:efb19c8f20d3beb64e215ff9dd47f4ad', 'dropTable tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-75::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_veraenderungssperre;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-75', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 75, '8:dcd127fd172812f45e1bd4d2b2568543', 'dropTable tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-76::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-76', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 76, '8:894b186e9be85502f081db8ab73afca1', 'dropTable tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-77::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-77', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 77, '8:d786385338250d80acf54f31aa0b8d1f', 'dropTable tableName=xplan_bp_verentsorgungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-78::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgungsleitunglinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-78', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 78, '8:52b19f72ce3b007458b32557b8913ae6', 'dropTable tableName=xplan_bp_verentsorgungsleitunglinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-79::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-79', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 79, '8:a82e4749867a0f64eff1a8aeaf571aa6', 'dropTable tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-80::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_vorbhochwschutzflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-80', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 80, '8:e1df5673fe08076d142a2c06e391696d', 'dropTable tableName=xplan_bp_vorbhochwschutzflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-81::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_waldflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-81', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 81, '8:8af63919a2a299c8f8c291108f19b3f5', 'dropTable tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-82::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wasserrechtlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-82', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 82, '8:f6eaedd32b11f88e239ac62caf45a82c', 'dropTable tableName=xplan_bp_wasserrechtlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-83::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-83', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 83, '8:98be4f2a38102c77caf762aa44c47f74', 'dropTable tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-84::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wegerecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-84', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 84, '8:28b0d7171c4cc7d6ca255aa10268b9ff', 'dropTable tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-85::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_abgrabung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-85', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 85, '8:2cf807aa7d7992c3a7f95680199a6097', 'dropTable tableName=xplan_fp_abgrabung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-86::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_abgrabungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-86', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 86, '8:dd2e991eef0203661263b21e433144c6', 'dropTable tableName=xplan_fp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-87::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_anpassungklimawandel;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-87', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 87, '8:aed530274fe4dd2bcaac640eca000a21', 'dropTable tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-88::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_aufschuettung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-88', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 88, '8:3109e7a0bde484098e9bb0b6bf96b126', 'dropTable tableName=xplan_fp_aufschuettung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-89::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_aufschuettungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-89', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 89, '8:0c21dd1f82b9a2e847390b05df7ba696', 'dropTable tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-90::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_ausgleichsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-90', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 90, '8:a8f35578b7bae737b5bcdfd23654a03b', 'dropTable tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-91::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bahnverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-91', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 91, '8:072b03c80792be468aa80c16d1566b6d', 'dropTable tableName=xplan_fp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-92::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bauschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-92', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 92, '8:1ab4e07fd79c0945d432b7cec43aac7b', 'dropTable tableName=xplan_fp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-93::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bebauungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-93', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 93, '8:7978444199e98cd500f01d4e572b64b6', 'dropTable tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-94::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-94', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 94, '8:ba49f827c24303a2cd5ba4a8b32d528d', 'dropTable tableName=xplan_fp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-95::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-95', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 95, '8:020a7c21802dca151743edd7367ca3dc', 'dropTable tableName=xplan_fp_bodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-96::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bodenschaetzeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-96', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 96, '8:8fe837beb6925f8926bae34854866d7c', 'dropTable tableName=xplan_fp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-97::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_denkmalschutzensemble;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-97', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 97, '8:57a64fdbd194282384434bb9314c8975', 'dropTable tableName=xplan_fp_denkmalschutzensemble', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-98::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_erhaltungssatzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-98', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 98, '8:e29a5e83c9243ba00dd7cedff23e387d', 'dropTable tableName=xplan_fp_erhaltungssatzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-99::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_fachgesetz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-99', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 99, '8:8a01310bf9f739c7834464065f9bc568', 'dropTable tableName=xplan_fp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-100::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gemeinbedarf;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-100', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 100, '8:e3730ed54aa276c8f79b8f8c6435360f', 'dropTable tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-101::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-101', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 101, '8:9461d4cab07ca2af932ed91f3196a9df', 'dropTable tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-102::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-102', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 102, '8:a0797078cd47dfcc7637eb927fe9b0d7', 'dropTable tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-103::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_grabungsschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-103', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 103, '8:81a0c1f9576417119b2107cfb4e9591d', 'dropTable tableName=xplan_fp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-104::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gruen;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-104', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 104, '8:c8625b60b3c377ee4a915091645f8fb4', 'dropTable tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-105::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_keinezentrabwasserbeseitigungflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-105', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 105, '8:1433a6fa72f0f31f0db0727c3dbe6639', 'dropTable tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-106::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_kennzeichnung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-106', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 106, '8:45d5024e989bb9a5143022790668234a', 'dropTable tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-107::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-107', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 107, '8:9785a9ffb8fa630a8f2591fefdefea26', 'dropTable tableName=xplan_fp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-108::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_landwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-108', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 108, '8:91760197ea13340473cbbaf6f85f4da8', 'dropTable tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-109::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-109', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 109, '8:d28d4283be78090178664096bffa7232', 'dropTable tableName=xplan_fp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-110::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_nutzungsbeschraenkungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-110', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 110, '8:959270ab9f1d8629bf59b29d6d7c21a4', 'dropTable tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-111::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-111', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 111, '8:72472dc31641cdf9bda989afb2862922', 'dropTable tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-112::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_privilegiertesvorhaben;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-112', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 112, '8:3062ea7e41c5387d7d505ca39641ca24', 'dropTable tableName=xplan_fp_privilegiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-113::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_priviligiertesvorhaben;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-113', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 113, '8:41565aba24aa7250d9dc9fd135adfe34', 'dropTable tableName=xplan_fp_priviligiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-114::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-114', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 114, '8:5b22740bbe71d4090ca19b31dba44e4b', 'dropTable tableName=xplan_fp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-115::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_schutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-115', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 115, '8:0c02f9101a4f5ec5441c34220a353675', 'dropTable tableName=xplan_fp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-116::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_schutzpflegeentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-116', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 116, '8:27232a0914f91d53a58eafc143d463e0', 'dropTable tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-117::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_spielsportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-117', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 117, '8:24e24f0ec1c247851cebaa55cd2edf4b', 'dropTable tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-118::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-118', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 118, '8:ae07647461e96983e945b57371b68237', 'dropTable tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-119::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-119', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 119, '8:dea00862420596724c188711a740959e', 'dropTable tableName=xplan_fp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-120::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_textlichedarstellungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-120', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 120, '8:37cee2c41638fc6f01eaf3383172575f', 'dropTable tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-121::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_unverbindlichevormerkung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-121', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 121, '8:3b3c5173febf671c03754e2c1f6a1fca', 'dropTable tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-122::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_verentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-122', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 122, '8:547c147d22cfc17cd2eba8c5252326f2', 'dropTable tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-123::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_vorbehalteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-123', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 123, '8:1fe31a8764c04ec9af720dbe5a0f0cf8', 'dropTable tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-124::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_vorbhochwschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-124', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 124, '8:0efef1770dbd95d5f6dc671a8d33c9c5', 'dropTable tableName=xplan_fp_vorbhochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-125::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_waldflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-125', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 125, '8:d8ac7e116647d8d855ccb295642523fc', 'dropTable tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-126::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_wasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-126', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 126, '8:5d75dd09dc41d78d560b3c6466133f4c', 'dropTable tableName=xplan_fp_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-127::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_wasserwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-127', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 127, '8:74dd275449ca662003137be0ed035a39', 'dropTable tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-128::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_zentralerversorgungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-128', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 128, '8:bd3d3648f5731d388ffa35a9ef48f18c', 'dropTable tableName=xplan_fp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-129::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_abgrenzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-129', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 129, '8:b8e107205fdbdb822b807cf6ced9c8bc', 'dropTable tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-130::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_allggruenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-130', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 130, '8:5dbfc6a2605ce8092d8a12aa1cb9cf78', 'dropTable tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-131::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_anpflanzungbindungerhaltung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-131', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 131, '8:5c3eb57d1ae2310e7fac227ae8618aa5', 'dropTable tableName=xplan_lp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-132::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_ausgleich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-132', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 132, '8:4cd9776057f21599147f9b1ae17a0d76', 'dropTable tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-133::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-133', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 133, '8:41e2c473cb585bf2722efce0a29e69ba', 'dropTable tableName=xplan_lp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-134::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_biotopverbundflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-134', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 134, '8:e7618978b17b848ccf4f543c77085059', 'dropTable tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-135::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_bodenschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-135', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 135, '8:972220f1b9c7746c10b6f9c6cfe403b4', 'dropTable tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-136::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_denkmalschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-136', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 136, '8:8d7d11661f0af6dba14f1e1e19f37346', 'dropTable tableName=xplan_lp_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-137::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_erholungfreizeit;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-137', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 137, '8:8317df898ac1cecb45fa1878c536e29b', 'dropTable tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-138::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_forstrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-138', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 138, '8:89d944aaa9ccec70f5b60fa52030c0a0', 'dropTable tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-139::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-139', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 139, '8:d7e8b29f9bb531c2dcecb6edf536eafb', 'dropTable tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-140::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_landschaftsbild;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-140', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 140, '8:0c52d2a2589cdc2e657b1dba2bee19ff', 'dropTable tableName=xplan_lp_landschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-141::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_biotopschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-141', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 141, '8:2c7a98d33e5ff65faffb01fe28a843b1', 'dropTable tableName=xplan_lp_nrw_biotopschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-142::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_brachflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-142', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 142, '8:f9e85268d676872a5beddd76c63f8a6f', 'dropTable tableName=xplan_lp_nrw_brachflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-143::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_elementekulturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-143', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 143, '8:f152f7edeb8f5775e73b6ffc6d6e2723', 'dropTable tableName=xplan_lp_nrw_elementekulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-144::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_forstlichefestsetzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-144', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 144, '8:95f3621ffb55fa8a2e046f5daccdfd4a', 'dropTable tableName=xplan_lp_nrw_forstlichefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-145::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_herrichtunggrundstueck;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-145', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 145, '8:fa9f7ca0a37de0ab2a999b4a1e996694', 'dropTable tableName=xplan_lp_nrw_herrichtunggrundstueck', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-146::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_pflegeanpflanzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-146', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 146, '8:121e75bbd1d009547fb6ad8df0b57f5c', 'dropTable tableName=xplan_lp_nrw_pflegeanpflanzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-147::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_pflegelandschaftsbild;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-147', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 147, '8:ead7ce7bd5755b6279726facd03e6c47', 'dropTable tableName=xplan_lp_nrw_pflegelandschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-148::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_schutzobjektlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-148', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 148, '8:0a5bfd4fd4455a1fac1be0d166db9f38', 'dropTable tableName=xplan_lp_nrw_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-149::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_sonstigemassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-149', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 149, '8:806be16e30f44cfd095cc4bc4e61d5c1', 'dropTable tableName=xplan_lp_nrw_sonstigemassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-150::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_strukturenelementebesiedelterbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-150', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 150, '8:39b52ab44b322c0d48693f95023870f0', 'dropTable tableName=xplan_lp_nrw_strukturenelementebesiedelterbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-151::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_temporaererlandschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-151', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 151, '8:9aa413e40303f19c6f30df6fe17a11b6', 'dropTable tableName=xplan_lp_nrw_temporaererlandschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-152::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_verpflichtungwrrl;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-152', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 152, '8:6a117d86f84e33547655ac53293debe9', 'dropTable tableName=xplan_lp_nrw_verpflichtungwrrl', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-153::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nutzungsausschluss;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-153', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 153, '8:0afca0c40100f8271729e588560d41ae', 'dropTable tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-154::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nutzungserfordernisregelung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-154', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 154, '8:4186b753a4d0c8179099e925c909b903', 'dropTable tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-155::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-155', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 155, '8:716764abcdb3196e41cddb33f3d271a6', 'dropTable tableName=xplan_lp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-156::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_planerischevertiefung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-156', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 156, '8:94ea2545e3e14fb1afc78ca325ed424b', 'dropTable tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-157::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-157', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 157, '8:7fcefdbf968c3b0c1f911c77903d93a9', 'dropTable tableName=xplan_lp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-158::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektbundesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-158', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 158, '8:7a4941de612a6cbac0162e5b6e3826f0', 'dropTable tableName=xplan_lp_schutzobjektbundesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-159::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektinternatrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-159', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 159, '8:13bf64c71e65eb9c181a43767d70f03a', 'dropTable tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-160::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-160', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 160, '8:6b26273904661d7f947d01130a255692', 'dropTable tableName=xplan_lp_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-161::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzpflegeentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-161', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 161, '8:b185604d233e8191cf94a639b2f79566', 'dropTable tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-162::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_sonstigeabgrenzuung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-162', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 162, '8:d01384f351371a5ef2e3a789e6e19ea4', 'dropTable tableName=xplan_lp_sonstigeabgrenzuung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-163::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_sonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-163', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 163, '8:d756cc564783c2b50653a84fa505704f', 'dropTable tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-164::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-164', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 164, '8:7e3554dfc779f55669ebd61bd8bbac39', 'dropTable tableName=xplan_lp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-165::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_textlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-165', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 165, '8:6fc658639de93f9b9c3700f4d2053e58', 'dropTable tableName=xplan_lp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-166::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-166', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 166, '8:3095fb1b2bd9df56be4c2240d1bcd5ab', 'dropTable tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-167::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-167', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 167, '8:7c3b204c622fc2a614f36520d7c2f74e', 'dropTable tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-168::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtsonstige;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-168', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 168, '8:579d7f65c31f88f84b586ea273b1d537', 'dropTable tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-169::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtwirtschaftabflusshochwschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-169', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 169, '8:44b5e2f71ba150983857ba14f7cff268', 'dropTable tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-170::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_zubegruenendegrundstueckflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-170', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 170, '8:0c78271d1b2188bab278e58e3a75d335', 'dropTable tableName=xplan_lp_zubegruenendegrundstueckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-171::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_zwischennutzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-171', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 171, '8:9d5de5f1dc37d79cb0d53c140c434594', 'dropTable tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-172::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_achse;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-172', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 172, '8:8e2bc00c6b172014a3599bc76f5e370b', 'dropTable tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-173::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-173', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 173, '8:9cb917fd8fae9f0352cbe1794773fa3d', 'dropTable tableName=xplan_rp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-174::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_bodenschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-174', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 174, '8:8152da5c11d28e6e7bf76da9a58885a0', 'dropTable tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-175::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_einzelhandel;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-175', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 175, '8:30ebbdb4d61ed19c572f7371a9d8aee2', 'dropTable tableName=xplan_rp_einzelhandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-176::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_energieversorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-176', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 176, '8:df8b1ba2697f78d48c00f941053a319b', 'dropTable tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-177::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_entsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-177', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 177, '8:1326f9ca188531bb2c4500bf5655cdbf', 'dropTable tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-178::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_entwicklungsschwerpunkte;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-178', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 178, '8:c54387a29bf4ed99f4fd2f0d511f54cf', 'dropTable tableName=xplan_rp_entwicklungsschwerpunkte', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-179::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_erholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-179', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 179, '8:8109919e191f8d36adb6d5cfb917b20a', 'dropTable tableName=xplan_rp_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-180::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_erneuerbareenergie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-180', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 180, '8:cf0d2154253bec1e798ffc43136d90c9', 'dropTable tableName=xplan_rp_erneuerbareenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-181::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_forstwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-181', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 181, '8:8879236d032ac073886ef3078b5ae714', 'dropTable tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-182::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_freiraum;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-182', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 182, '8:a48d64540d3acce25884a9f899d77d5e', 'dropTable tableName=xplan_rp_freiraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-183::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_freizeiterholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-183', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 183, '8:83b7560917a02b01e9648b05ad860da3', 'dropTable tableName=xplan_rp_freizeiterholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-184::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_funktionszuweisung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-184', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 184, '8:0f7555e5e6c412be187c8f75978a8239', 'dropTable tableName=xplan_rp_funktionszuweisung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-185::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gemeindefunktionsiedlungsentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-185', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 185, '8:9f8357c3daec7fb7082c3ed169353318', 'dropTable tableName=xplan_rp_gemeindefunktionsiedlungsentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-186::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-186', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 186, '8:e3fb05f55c89a6242cbb226e5d517172', 'dropTable tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-187::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-187', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 187, '8:535c8c1593995f41c73e7ca58bb37cae', 'dropTable tableName=xplan_rp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-188::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-188', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 188, '8:832b975d9f31f0a4b478dc531a294371', 'dropTable tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-189::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gruenzuggruenzaesur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-189', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 189, '8:acd0769cde915526d93c2305f7e8bfed', 'dropTable tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-190::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_hochwasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-190', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 190, '8:b65bbeb0a4b54d72f075365c3bf12c17', 'dropTable tableName=xplan_rp_hochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-191::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_industriegewerbe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-191', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 191, '8:7f575f5f09a0418370a83e5144714b41', 'dropTable tableName=xplan_rp_industriegewerbe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-192::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_klimaschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-192', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 192, '8:46e1f00fe1de28ee2005684d54820466', 'dropTable tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-193::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kommunikation;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-193', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 193, '8:f99e1ace6ec456b7887a1509614c2d54', 'dropTable tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-194::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kulturellessachgut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-194', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 194, '8:28fd25a3fec09d908955439d05307b68', 'dropTable tableName=xplan_rp_kulturellessachgut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-195::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kulturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-195', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 195, '8:5e613d8361cd5ae756af5d00480c6d6e', 'dropTable tableName=xplan_rp_kulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-196::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_laermschutzbauschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-196', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 196, '8:8a66b2a069fe0b429860fabb818a0611', 'dropTable tableName=xplan_rp_laermschutzbauschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-197::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-197', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 197, '8:4fb60bf9d0b5dd260b214f5d93f6f78e', 'dropTable tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-198::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_landwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-198', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 198, '8:52ab49487713daf33a858673c7ea4e66', 'dropTable tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-199::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_legendenobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-199', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 199, '8:792d63525bbd6ad1341a0c7a173fe115', 'dropTable tableName=xplan_rp_legendenobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-200::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-200', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 200, '8:d9674a8760810f54584e0f1695d432a3', 'dropTable tableName=xplan_rp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-201::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_naturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-201', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 201, '8:2109531bb0491b90b5ae3b09b6848c29', 'dropTable tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-202::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-202', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 202, '8:db6ed8b2af2acf0555b65cd0da123551', 'dropTable tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-203::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_asb;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-203', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 203, '8:785ebab4635f965e6cec68a5c4b98f23', 'dropTable tableName=xplan_rp_nrw_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-204::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_aufschuettungablagerung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-204', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 204, '8:b119e5a39fcda5e409518ec98d8f768f', 'dropTable tableName=xplan_rp_nrw_aufschuettungablagerung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-205::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_forstwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-205', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 205, '8:81116a0a6121f6fd6d75fcf4087188c2', 'dropTable tableName=xplan_rp_nrw_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-206::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_freiraumagrarbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-206', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 206, '8:f48c11949d0979f77ad82dedc031f0fe', 'dropTable tableName=xplan_rp_nrw_freiraumagrarbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-207::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_gib;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-207', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 207, '8:12e7dee477bce643ab20aa87d4b25a93', 'dropTable tableName=xplan_rp_nrw_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-208::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_grundwassergewaesserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-208', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 208, '8:0dcce6a309b41d89a61b9fd186a97447', 'dropTable tableName=xplan_rp_nrw_grundwassergewaesserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-209::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_laermschutzzone;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-209', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 209, '8:73957c1d5451202e00b64e9d261defaa', 'dropTable tableName=xplan_rp_nrw_laermschutzzone', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-210::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_landschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-210', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 210, '8:6cb0892e4b739f122e355c0ec1a0401c', 'dropTable tableName=xplan_rp_nrw_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-211::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-211', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 211, '8:3df652299b2a9cd732a45120fd124f27', 'dropTable tableName=xplan_rp_nrw_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-212::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_naturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-212', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 212, '8:e589d03a31a8379bb8340cb4ab9294f2', 'dropTable tableName=xplan_rp_nrw_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-213::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_oberflaechengewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-213', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 213, '8:4992a6573026dc0c8835ca353169cc41', 'dropTable tableName=xplan_rp_nrw_oberflaechengewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-214::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_oberflaechennahebodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-214', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 214, '8:873d390cc0927852a73370366fdd8b9d', 'dropTable tableName=xplan_rp_nrw_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-215::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-215', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 215, '8:95b0a702aae85c9029ea64bfe4654734', 'dropTable tableName=xplan_rp_nrw_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-216::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-216', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 216, '8:0c2007c3d688ebb8fadc6f3f413b75ff', 'dropTable tableName=xplan_rp_nrw_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-217::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigersiedlungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-217', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 217, '8:8a29009080befc2710e128c32a13add4', 'dropTable tableName=xplan_rp_nrw_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-218::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigezweckbindung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-218', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 218, '8:1c4b75cd8a4e40db27a7f947ccfbf818', 'dropTable tableName=xplan_rp_nrw_sonstigezweckbindung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-219::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-219', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 219, '8:0c03dd41c5b27e5d4afbf18cc5be60ed', 'dropTable tableName=xplan_rp_nrw_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-220::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-220', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 220, '8:eac72877095973b8a5ae501cdd18ccc9', 'dropTable tableName=xplan_rp_nrw_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-221::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_ueberschwemmungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-221', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 221, '8:710326667f28de9a46b3977b9e15a60a', 'dropTable tableName=xplan_rp_nrw_ueberschwemmungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-222::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-222', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 222, '8:1ae4ae8ee72e573307a6d1b5b1fcdb64', 'dropTable tableName=xplan_rp_nrw_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-223::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_zeitlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-223', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 223, '8:b87382139f9322f4e2100f540a694324', 'dropTable tableName=xplan_rp_nrw_zeitlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-224::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abfallentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-224', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 224, '8:103bc53a760a13a59659ec4d85a8a24a', 'dropTable tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-225::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abwasser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-225', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 225, '8:52d59f5520189f52dabb14051e85306a', 'dropTable tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-226::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_asb;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-226', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 226, '8:c47d2e6186659a558b49aa5ee93878d1', 'dropTable tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-227::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_erholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-227', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 227, '8:69acb52bbb05a99b2b3416423b63aef6', 'dropTable tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-228::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_gib;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-228', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 228, '8:061635c1e42ce7d7338ed62bb59b6eba', 'dropTable tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-229::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_landschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-229', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 229, '8:b73bcfd9956981b6266da0ae841fd339', 'dropTable tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-230::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-230', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 230, '8:069cf70fa21b80fe05ad11f42e53a7d2', 'dropTable tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-231::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_naturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-231', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 231, '8:5480e0c799a6c36e15699318763931b0', 'dropTable tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-232::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_oberflaechennahebodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-232', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 232, '8:9be219631dd0ca29dad12077f361fa06', 'dropTable tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-233::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamerwanderweg;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-233', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 233, '8:dcb2a584d504d65851c08711eed7f271', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-234::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamesportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-234', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 234, '8:8dfc2d1e5726cfc66ec697016ce0319a', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-235::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-235', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 235, '8:ed06c12ef431032109746bdbabd2885d', 'dropTable tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-236::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-236', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 236, '8:f2249dae95e83d119c3e68fa2b55c80f', 'dropTable tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-237::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-237', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 237, '8:1d89e709f880e0e036e9002fcda9ea4f', 'dropTable tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-238::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tiefliegenderohstoffe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-238', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 238, '8:da29743a17e5627350403390f9db400e', 'dropTable tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-239::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tourismus;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-239', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 239, '8:7efccb22e7aee950ba4790b34110a298', 'dropTable tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-240::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-240', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 240, '8:20766ab07e5c763f9bb1c6aab4c64233', 'dropTable tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-241::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-241', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 241, '8:561806f04f54586468c09814e2af50d3', 'dropTable tableName=xplan_rp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-242::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_planungsraum;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-242', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 242, '8:528f2f3c4e51eff928a888793b5fb9f9', 'dropTable tableName=xplan_rp_planungsraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-243::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_radwegwanderweg;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-243', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 243, '8:d36b9e3addd4dc1264f7886d25d86ec5', 'dropTable tableName=xplan_rp_radwegwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-244::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-244', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 244, '8:1faaa6bce12166975be2fdaf28502beb', 'dropTable tableName=xplan_rp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-245::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_raumkategorie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-245', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 245, '8:ad28fc493469182b120b162aee30ede0', 'dropTable tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-246::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rohstoff;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-246', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 246, '8:6d69be02ea568c12d1ab9556317abb6d', 'dropTable tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-247::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rohstoffsicherung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-247', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 247, '8:8ad0ca4fd76c03024cf606bf17be8393', 'dropTable tableName=xplan_rp_rohstoffsicherung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-248::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-248', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 248, '8:f07cbc0761a63bcd38ec260f1c852dff', 'dropTable tableName=xplan_rp_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-249::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_siedlung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-249', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 249, '8:4870982f9e07145b0d887a8d828942a6', 'dropTable tableName=xplan_rp_siedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-250::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-250', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 250, '8:361f7b637bebe71764f0e20b3a5d955a', 'dropTable tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-251::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigerfreiraumschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-251', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 251, '8:63d043bb4c8a93596bbffa0955134eee', 'dropTable tableName=xplan_rp_sonstigerfreiraumschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-252::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigerfreiraumstruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-252', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 252, '8:0b1c5f6ebabc2535cb134a5c38db8a54', 'dropTable tableName=xplan_rp_sonstigerfreiraumstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-253::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigersiedlungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-253', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 253, '8:532fa05ce8cb28423cee0901c3718b97', 'dropTable tableName=xplan_rp_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-254::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigesiedlungsstruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-254', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 254, '8:8f1f9a16425ea2e841dee95c4be342b0', 'dropTable tableName=xplan_rp_sonstigesiedlungsstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-255::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-255', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 255, '8:e4262edee509c874010e196ebc88b6d6', 'dropTable tableName=xplan_rp_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-256::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sozialeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-256', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 256, '8:99e58a1f507c31438f94df47ea13781a', 'dropTable tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-257::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sperrgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-257', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 257, '8:fb58c8a3792ec8bf4c3361f752d6bb5f', 'dropTable tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-258::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-258', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 258, '8:a82cbd3901d65ce5fdc18908aa5d7ad2', 'dropTable tableName=xplan_rp_sportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-259::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-259', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 259, '8:f0803adde29366e0dc6bc43b9b83a686', 'dropTable tableName=xplan_rp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-260::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-260', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 260, '8:07b7a4c1add7dc9d09332c9488453600', 'dropTable tableName=xplan_rp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-261::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_verkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-261', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 261, '8:54729d06d64fee9219677914fa797bd1', 'dropTable tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-262::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_vorbhochwasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-262', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 262, '8:ac34ff60daf312e156f2985a77705139', 'dropTable tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-263::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-263', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 263, '8:1dfec4c5338515edd24b6d2d5528d431', 'dropTable tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-264::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-264', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 264, '8:e8cebecb165a6eef0f2c3b601d723aa3', 'dropTable tableName=xplan_rp_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-265::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-265', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 265, '8:f0b1378d266c9d3460d41ed18bb9c749', 'dropTable tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-266::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_windenergie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-266', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 266, '8:0dd75e59588aad9ff4a4a596f2188827', 'dropTable tableName=xplan_rp_windenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-267::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_windenergienutzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-267', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 267, '8:e123431df67d85b46acbd2107fd52e09', 'dropTable tableName=xplan_rp_windenergienutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-268::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wohnensiedlung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-268', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 268, '8:0af62cda8c3798dca75cf879f7ea8020', 'dropTable tableName=xplan_rp_wohnensiedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-269::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_zentralerort;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-269', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 269, '8:59340b77f96c5c898b2c66b2f4954ee3', 'dropTable tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-270::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-270', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 270, '8:3724a3488def18e70109519569c4197a', 'dropTable tableName=xplan_so_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-271::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_bodenschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-271', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 271, '8:5aff6cfa28f37054e811aab3bfbe96a3', 'dropTable tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-272::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_denkmalschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-272', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 272, '8:4354f642e6e8ad61d27455252e458933', 'dropTable tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-273::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_forstrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-273', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 273, '8:7a0530b8b499559aab3ebc3eb57c4371', 'dropTable tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-274::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_gebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-274', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 274, '8:15e85073e28a5592d3af8094554019ac', 'dropTable tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-275::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-275', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 275, '8:5f27473937af889ae3522acc4dfb78e2', 'dropTable tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-276::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_linienobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-276', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 276, '8:8abd2a1ca3ad3dfb1c82f4dc74203fff', 'dropTable tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-277::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_luftverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-277', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 277, '8:f9ad6822d37b4498a72c684059037fa0', 'dropTable tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-278::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_objekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-278', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 278, '8:e7d9a2f2e28cd9897891645b067f7c4f', 'dropTable tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-279::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-279', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 279, '8:5dc79ff452f8f68ffa8f3edcb75cf3c3', 'dropTable tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-280::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-280', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 280, '8:ed6184ccfc11a3221571c5481e35a65f', 'dropTable tableName=xplan_so_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-281::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schienenverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-281', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 281, '8:c3a880bddba55186a6fa17f3fab3926b', 'dropTable tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-282::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietnaturschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-282', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 282, '8:6e667e3564ffcd3073f3122e84ee36e3', 'dropTable tableName=xplan_so_schutzgebietnaturschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-283::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietsonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-283', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 283, '8:96508c606369cdc9957678ca45ad84c9', 'dropTable tableName=xplan_so_schutzgebietsonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-284::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-284', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 284, '8:0b181b5b43e599f9bd5d4687bfbb75f6', 'dropTable tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-285::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_sonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-285', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 285, '8:9bfe688e089e00684dddd527291fbe75', 'dropTable tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-286::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_strassenverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-286', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 286, '8:346ca0491b706366921e110c5fa86892', 'dropTable tableName=xplan_so_strassenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-287::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-287', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 287, '8:aba6566042d02943db19d9b873dca08f', 'dropTable tableName=xplan_so_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-288::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_wasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-288', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 288, '8:997a38feeb3f0b256293ed230c8a6506', 'dropTable tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-289::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_begruendungabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-289', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 289, '8:b62174b6e1b43ac3559e12d07aea12ab', 'dropTable tableName=xplan_xp_begruendungabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-290::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_datumattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-290', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 290, '8:b53a9d157bc0275c5b395e925bfaf840', 'dropTable tableName=xplan_xp_datumattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-291::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_doubleattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-291', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 291, '8:a07cb442a3447eda1b0e60c377a9833a', 'dropTable tableName=xplan_xp_doubleattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-292::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_externereferenz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-292', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 292, '8:e02d369c814695aff08e6705ecbdcc65', 'dropTable tableName=xplan_xp_externereferenz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-293::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_externereferenzplan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-293', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 293, '8:4da7a3834ae9c9d26c83641185216cb9', 'dropTable tableName=xplan_xp_externereferenzplan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-294::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_fpo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-294', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 294, '8:1c36cdb36545894a97a71096d5c87604', 'dropTable tableName=xplan_xp_fpo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-295::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-295', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 295, '8:3a4a5021e24d50028c6e7bcd334100a8', 'dropTable tableName=xplan_xp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-296::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_hoehenangabe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-296', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 296, '8:e7f5b694e2e1d49d0997c61f47478b9c', 'dropTable tableName=xplan_xp_hoehenangabe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-297::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_integerattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-297', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 297, '8:56623b883f9b5fbeaa636b3897d39f9d', 'dropTable tableName=xplan_xp_integerattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-298::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_lpo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-298', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 298, '8:406b4b2f989de44e01c03431c545bddb', 'dropTable tableName=xplan_xp_lpo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-299::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_lto;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-299', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 299, '8:893b1f35222814bb2b2dc609ef01c31e', 'dropTable tableName=xplan_xp_lto', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-300::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_nutzungsschablone;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-300', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 300, '8:b180483a42de41f9ae49d1b24cf1ac39', 'dropTable tableName=xplan_xp_nutzungsschablone', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-301::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_ppo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-301', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 301, '8:28f6b183d78758d76de5e69a557238b5', 'dropTable tableName=xplan_xp_ppo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-302::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_praesentationsobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-302', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 302, '8:3677b231f8a202d4f003ac1d334a20e4', 'dropTable tableName=xplan_xp_praesentationsobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-303::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_pto;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-303', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 303, '8:d387b26e50adfe3d29eaf296055eb8a2', 'dropTable tableName=xplan_xp_pto', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-304::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_rasterdarstellung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-304', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 304, '8:6d1eb329b16db15a1cb131beea55c75f', 'dropTable tableName=xplan_xp_rasterdarstellung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-305::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_rasterplanbasis;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-305', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 305, '8:2590e2dd5d2db492ecc3176efac3fee4', 'dropTable tableName=xplan_xp_rasterplanbasis', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-306::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_stringattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-306', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 306, '8:89c4395d95219b1b253c7b79f256a7da', 'dropTable tableName=xplan_xp_stringattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-307::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-307', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 307, '8:595989da4def0f8fe86202f432edf644', 'dropTable tableName=xplan_xp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-308::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_urlattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-308', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 308, '8:27910458afed582235088564b2eefa83', 'dropTable tableName=xplan_xp_urlattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Changeset changelog_xplansynarchive.xml::1543491004010-309::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_verfahrensmerkmal;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543491004010-309', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 309, '8:53d02c8d8cdce9b29e106d984b163b64', 'dropTable tableName=xplan_xp_verfahrensmerkmal', '', 'EXECUTED', NULL, NULL, '3.6.2', '3491078943');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

