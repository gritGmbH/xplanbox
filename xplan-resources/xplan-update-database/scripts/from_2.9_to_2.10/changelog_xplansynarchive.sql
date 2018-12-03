-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 03.12.18 11:18
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk29
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk.fritz.box (192.168.178.152)', LOCKGRANTED = '2018-12-03 11:18:35.858' WHERE ID = 1 AND LOCKED = FALSE;

-- Adding missing databasechangelog.contexts column
-- Adding missing databasechangelog.labels column
-- Adding missing databasechangelog.deployment_id column
-- DatabaseChangeLog checksums are an incompatible version.  Setting them to null so they will be updated on next database update
ALTER TABLE xplansynarchive.databasechangelog ADD CONTEXTS VARCHAR(255);

ALTER TABLE xplansynarchive.databasechangelog ADD LABELS VARCHAR(255);

ALTER TABLE xplansynarchive.databasechangelog ADD DEPLOYMENT_ID VARCHAR(10);

UPDATE xplansynarchive.databasechangelog SET MD5SUM = NULL;

-- Changeset changelog_xplansynarchive.xml::1543832242710-1::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abgrabungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-1', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 929, '8:683306063bd6dbf5735079ae57f92428', 'dropTable tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-2::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abstandsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-2', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 930, '8:35e731dd5cbf7cd0e0fad7fa890b6c1e', 'dropTable tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-3::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_abstandsmass;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-3', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 931, '8:44332317b688fd2d7a0776b152b61ae7', 'dropTable tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-4::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-4', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 932, '8:dc69580bd76daff245ba9137b3bd4c65', 'dropTable tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-5::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-5', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 933, '8:08467785c5886959f2b0ec2ff9d78e41', 'dropTable tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-6::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-6', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 934, '8:9f33fd9f973e3ac53779873de3be5b88', 'dropTable tableName=xplan_bp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-7::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleichsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-7', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 935, '8:a0029bbd5ee128ef6874a24031a3bd04', 'dropTable tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-8::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-8', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 936, '8:d4db989ff5eacff4228b48b6e4a6c9af', 'dropTable tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-9::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bahnverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-9', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 937, '8:34a17fbc6a189ceea2817a061440e44d', 'dropTable tableName=xplan_bp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-10::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-10', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 938, '8:c0f4310d375354c46b4b19f6f36fbc86', 'dropTable tableName=xplan_bp_baugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-11::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-11', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 939, '8:d7fd266458424788fc36a313a73bbe5b', 'dropTable tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-12::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baugrenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-12', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 940, '8:2d155a3b4c99c3b748a90d1f2b4ff142', 'dropTable tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-13::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_baulinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-13', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 941, '8:48fa71642db3d5449df201b473db1b58', 'dropTable tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-14::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bauschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-14', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 942, '8:b674b3fdf28295d2aa7eff83ef0ab0dc', 'dropTable tableName=xplan_bp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-15::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-15', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 943, '8:5649c4737b0f14355b2de221d1e0a52d', 'dropTable tableName=xplan_bp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-16::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-16', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 944, '8:e11485387156cd1291a316f664a43309', 'dropTable tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-17::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-17', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 945, '8:e37b9855b5ceec527c27bc91a22f241f', 'dropTable tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-18::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-18', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 946, '8:5506754d2f737aff18f14cd1b8e91b57', 'dropTable tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-19::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzeinzelanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-19', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 947, '8:357134e5d20ff77d3e13a26ebedeefcb', 'dropTable tableName=xplan_bp_denkmalschutzeinzelanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-20::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzeinzelanlagepunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-20', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 948, '8:e2b20c400e1232d34d0e384b487906de', 'dropTable tableName=xplan_bp_denkmalschutzeinzelanlagepunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-21::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_denkmalschutzensembleflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-21', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 949, '8:a5421a9324cf75868fa194948ef6d28c', 'dropTable tableName=xplan_bp_denkmalschutzensembleflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-22::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_einfahrtpunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-22', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 950, '8:b64d74d4a1230e24270e029d947739af', 'dropTable tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-23::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-23', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 951, '8:9f37d8834567cf325e2ab4aadbad8fc5', 'dropTable tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-24::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_eingriffsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-24', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 952, '8:d00398a2a0b4ee90e0afaef3b5089faf', 'dropTable tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-25::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-25', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 953, '8:e31f10687fc67017906ea807c8207232', 'dropTable tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-26::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_erneuerbareenergieflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-26', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 954, '8:0040a3e15cf39fca1c3b5c4742035b44', 'dropTable tableName=xplan_bp_erneuerbareenergieflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-27::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_fachgesetz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-27', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 955, '8:5a2ee3735cb81e129ccb04aa93834c49', 'dropTable tableName=xplan_bp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-28::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_festsetzungenbaugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-28', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 956, '8:57952ebb15b7dc231bf5a264e7d9a484', 'dropTable tableName=xplan_bp_festsetzungenbaugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-29::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-29', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 957, '8:2805f935b8c5a02a06f1b07235d6a8fa', 'dropTable tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-30::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_firstrichtungslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-30', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 958, '8:591983c4e8fa72e2bdccb7b84d1567f7', 'dropTable tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-31::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_foerderungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-31', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 959, '8:7c2135920d2cd26b1d0aa6037f8f099a', 'dropTable tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-32::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_freiflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-32', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 960, '8:f8919cbc6d73d57ef0415e73fa8fd25f', 'dropTable tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-33::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gebaeudeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-33', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 961, '8:64eacbfb061567fb9ea03520faab5320', 'dropTable tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-34::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-34', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 962, '8:a5ee4e98f0ef56f1ea6d55ab0dc387f6', 'dropTable tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-35::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-35', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 963, '8:4fcfc3415dc6fa7524270a00f7b6205e', 'dropTable tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-36::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-36', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 964, '8:b4146902df76393d1cda2b4c4fa0257b', 'dropTable tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-37::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-37', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 965, '8:8cd4b8f0602499121e62e52033c52766', 'dropTable tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-38::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gestaltungbaugebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-38', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 966, '8:745b83a994efac5084ca3f5475b9074f', 'dropTable tableName=xplan_bp_gestaltungbaugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-39::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gewaesserflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-39', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 967, '8:a2b45b9488d43a20f1f012b7350ade8f', 'dropTable tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-40::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_grabungsschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-40', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 968, '8:3c3382e80e85e23cb3ccdabc2dbd1e86', 'dropTable tableName=xplan_bp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-41::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_gruenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-41', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 969, '8:e082d735fb2d270bd23cff18f0a1a697', 'dropTable tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-42::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_hoehenmass;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-42', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 970, '8:1d3666b1be9c0a4702cdd474f4c59e79', 'dropTable tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-43::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_hoehenpunkt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-43', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 971, '8:1b04883ec0bb9d5dee3c18f58d3c9d6d', 'dropTable tableName=xplan_bp_hoehenpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-44::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_immissionsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-44', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 972, '8:b9ece06312084e31fabd6ea2cf3fa570', 'dropTable tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-45::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-45', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 973, '8:7fe01bba5640a261b5b82aa418e85156', 'dropTable tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-46::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-46', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 974, '8:33933102241e0733917b8ee51fbb2086', 'dropTable tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-47::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-47', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 975, '8:6896c7f04f321ca2eb3f83100101faaf', 'dropTable tableName=xplan_bp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-48::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-48', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 976, '8:503f297b518647377936143517ba6021', 'dropTable tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-49::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-49', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 977, '8:3a805b672867a2be6d63be4fa083f9c2', 'dropTable tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-50::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_landwirtschaftslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-50', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 978, '8:e61100729852a5f57aee40b0d01f0a2a', 'dropTable tableName=xplan_bp_landwirtschaftslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-51::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_luftreinhalteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-51', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 979, '8:62df998a2ce5c6cf8fe4ca34497a9e3d', 'dropTable tableName=xplan_bp_luftreinhalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-52::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_luftverkehrflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-52', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 980, '8:2678ebb28057e9f384b02dbef9fca7cc', 'dropTable tableName=xplan_bp_luftverkehrflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-53::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-53', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 981, '8:85e798d0fbad56267a9235871e35a7be', 'dropTable tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-54::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-54', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 982, '8:36a32c0b5b322d78748182f73e0453f4', 'dropTable tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-55::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_nutzungsartengrenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-55', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 983, '8:fe9aafdbaf72c222dcdae4b42b7e2d22', 'dropTable tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-56::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-56', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 984, '8:f22f908dbd8eb8a20b7a334c5828bac1', 'dropTable tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-57::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-57', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 985, '8:29b2e22e907160785f9d9b366fd1e543', 'dropTable tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-58::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-58', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 986, '8:df49a32a4487dff05520c35a69013245', 'dropTable tableName=xplan_bp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-59::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-59', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 987, '8:ff1a4411690b4cb28dc4261801fbdb83', 'dropTable tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-60::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-60', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 988, '8:904f9f1e7a0b61e5abc3b92ddfb88696', 'dropTable tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-61::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-61', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 989, '8:f23e593c3fd34e915e1b2c516e0f89cc', 'dropTable tableName=xplan_bp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-62::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-62', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 990, '8:e38bea4864b6f640b1bf1e635d5816ef', 'dropTable tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-63::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-63', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 991, '8:8f3a0695aeb6600cfc45b450da958987', 'dropTable tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-64::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_speziellebauweise;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-64', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 992, '8:23d94d2458edff8423dce33e700ae930', 'dropTable tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-65::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-65', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 993, '8:94aa63da3c29a6ad7a6aa4f308eb0265', 'dropTable tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-66::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-66', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 994, '8:8424d161693b09dfde2dede1dbb6e3b1', 'dropTable tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-67::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenkoerper;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-67', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 995, '8:0c64c13b259f8ca56485a49df9b5797f', 'dropTable tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-68::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-68', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 996, '8:f023bc17c1c1594de34aa0b4226b082c', 'dropTable tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-69::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_technikbestimmteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-69', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 997, '8:798bf3547e7a1adb5b9a90beaba00603', 'dropTable tableName=xplan_bp_technikbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-70::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-70', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 998, '8:e8f1abdd3a2bdcdc2d6201e773d3bdea', 'dropTable tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-71::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-71', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 999, '8:c55aa95d3545d267c9d912369096f32c', 'dropTable tableName=xplan_bp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-72::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-72', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1000, '8:eef445883406f0bcbd9aafa252c8c6e3', 'dropTable tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-73::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-73', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1001, '8:b5c60a0ac65ffa142d5a03f41610f3e9', 'dropTable tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-74::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-74', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1002, '8:efb19c8f20d3beb64e215ff9dd47f4ad', 'dropTable tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-75::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_veraenderungssperre;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-75', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1003, '8:dcd127fd172812f45e1bd4d2b2568543', 'dropTable tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-76::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-76', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1004, '8:894b186e9be85502f081db8ab73afca1', 'dropTable tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-77::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-77', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1005, '8:d786385338250d80acf54f31aa0b8d1f', 'dropTable tableName=xplan_bp_verentsorgungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-78::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verentsorgungsleitunglinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-78', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1006, '8:52b19f72ce3b007458b32557b8913ae6', 'dropTable tableName=xplan_bp_verentsorgungsleitunglinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-79::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-79', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1007, '8:a82e4749867a0f64eff1a8aeaf571aa6', 'dropTable tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-80::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_vorbhochwschutzflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-80', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1008, '8:e1df5673fe08076d142a2c06e391696d', 'dropTable tableName=xplan_bp_vorbhochwschutzflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-81::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_waldflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-81', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1009, '8:8af63919a2a299c8f8c291108f19b3f5', 'dropTable tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-82::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wasserrechtlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-82', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1010, '8:f6eaedd32b11f88e239ac62caf45a82c', 'dropTable tableName=xplan_bp_wasserrechtlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-83::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-83', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1011, '8:98be4f2a38102c77caf762aa44c47f74', 'dropTable tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-84::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_bp_wegerecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-84', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1012, '8:28b0d7171c4cc7d6ca255aa10268b9ff', 'dropTable tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-85::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_abgrabung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-85', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1013, '8:2cf807aa7d7992c3a7f95680199a6097', 'dropTable tableName=xplan_fp_abgrabung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-86::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_abgrabungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-86', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1014, '8:dd2e991eef0203661263b21e433144c6', 'dropTable tableName=xplan_fp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-87::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_anpassungklimawandel;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-87', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1015, '8:aed530274fe4dd2bcaac640eca000a21', 'dropTable tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-88::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_aufschuettung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-88', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1016, '8:3109e7a0bde484098e9bb0b6bf96b126', 'dropTable tableName=xplan_fp_aufschuettung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-89::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_aufschuettungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-89', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1017, '8:0c21dd1f82b9a2e847390b05df7ba696', 'dropTable tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-90::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_ausgleichsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-90', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1018, '8:a8f35578b7bae737b5bcdfd23654a03b', 'dropTable tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-91::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bahnverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-91', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1019, '8:072b03c80792be468aa80c16d1566b6d', 'dropTable tableName=xplan_fp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-92::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bauschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-92', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1020, '8:1ab4e07fd79c0945d432b7cec43aac7b', 'dropTable tableName=xplan_fp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-93::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bebauungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-93', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1021, '8:7978444199e98cd500f01d4e572b64b6', 'dropTable tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-94::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-94', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1022, '8:ba49f827c24303a2cd5ba4a8b32d528d', 'dropTable tableName=xplan_fp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-95::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-95', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1023, '8:020a7c21802dca151743edd7367ca3dc', 'dropTable tableName=xplan_fp_bodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-96::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_bodenschaetzeflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-96', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1024, '8:8fe837beb6925f8926bae34854866d7c', 'dropTable tableName=xplan_fp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-97::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_denkmalschutzensemble;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-97', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1025, '8:57a64fdbd194282384434bb9314c8975', 'dropTable tableName=xplan_fp_denkmalschutzensemble', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-98::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_erhaltungssatzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-98', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1026, '8:e29a5e83c9243ba00dd7cedff23e387d', 'dropTable tableName=xplan_fp_erhaltungssatzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-99::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_fachgesetz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-99', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1027, '8:8a01310bf9f739c7834464065f9bc568', 'dropTable tableName=xplan_fp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-100::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gemeinbedarf;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-100', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1028, '8:e3730ed54aa276c8f79b8f8c6435360f', 'dropTable tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-101::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-101', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1029, '8:9461d4cab07ca2af932ed91f3196a9df', 'dropTable tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-102::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-102', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1030, '8:a0797078cd47dfcc7637eb927fe9b0d7', 'dropTable tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-103::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_grabungsschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-103', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1031, '8:81a0c1f9576417119b2107cfb4e9591d', 'dropTable tableName=xplan_fp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-104::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_gruen;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-104', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1032, '8:c8625b60b3c377ee4a915091645f8fb4', 'dropTable tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-105::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_keinezentrabwasserbeseitigungflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-105', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1033, '8:1433a6fa72f0f31f0db0727c3dbe6639', 'dropTable tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-106::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_kennzeichnung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-106', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1034, '8:45d5024e989bb9a5143022790668234a', 'dropTable tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-107::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-107', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1035, '8:9785a9ffb8fa630a8f2591fefdefea26', 'dropTable tableName=xplan_fp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-108::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_landwirtschaftsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-108', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1036, '8:91760197ea13340473cbbaf6f85f4da8', 'dropTable tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-109::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-109', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1037, '8:d28d4283be78090178664096bffa7232', 'dropTable tableName=xplan_fp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-110::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_nutzungsbeschraenkungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-110', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1038, '8:959270ab9f1d8629bf59b29d6d7c21a4', 'dropTable tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-111::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-111', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1039, '8:72472dc31641cdf9bda989afb2862922', 'dropTable tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-112::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_privilegiertesvorhaben;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-112', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1040, '8:3062ea7e41c5387d7d505ca39641ca24', 'dropTable tableName=xplan_fp_privilegiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-113::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_priviligiertesvorhaben;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-113', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1041, '8:41565aba24aa7250d9dc9fd135adfe34', 'dropTable tableName=xplan_fp_priviligiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-114::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-114', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1042, '8:5b22740bbe71d4090ca19b31dba44e4b', 'dropTable tableName=xplan_fp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-115::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_schutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-115', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1043, '8:0c02f9101a4f5ec5441c34220a353675', 'dropTable tableName=xplan_fp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-116::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_schutzpflegeentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-116', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1044, '8:27232a0914f91d53a58eafc143d463e0', 'dropTable tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-117::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_spielsportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-117', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1045, '8:24e24f0ec1c247851cebaa55cd2edf4b', 'dropTable tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-118::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-118', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1046, '8:ae07647461e96983e945b57371b68237', 'dropTable tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-119::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-119', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1047, '8:dea00862420596724c188711a740959e', 'dropTable tableName=xplan_fp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-120::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_textlichedarstellungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-120', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1048, '8:37cee2c41638fc6f01eaf3383172575f', 'dropTable tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-121::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_unverbindlichevormerkung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-121', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1049, '8:3b3c5173febf671c03754e2c1f6a1fca', 'dropTable tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-122::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_verentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-122', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1050, '8:547c147d22cfc17cd2eba8c5252326f2', 'dropTable tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-123::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_vorbehalteflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-123', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1051, '8:1fe31a8764c04ec9af720dbe5a0f0cf8', 'dropTable tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-124::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_vorbhochwschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-124', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1052, '8:0efef1770dbd95d5f6dc671a8d33c9c5', 'dropTable tableName=xplan_fp_vorbhochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-125::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_waldflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-125', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1053, '8:d8ac7e116647d8d855ccb295642523fc', 'dropTable tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-126::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_wasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-126', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1054, '8:5d75dd09dc41d78d560b3c6466133f4c', 'dropTable tableName=xplan_fp_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-127::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_wasserwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-127', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1055, '8:74dd275449ca662003137be0ed035a39', 'dropTable tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-128::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_fp_zentralerversorgungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-128', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1056, '8:bd3d3648f5731d388ffa35a9ef48f18c', 'dropTable tableName=xplan_fp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-129::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_abgrenzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-129', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1057, '8:b8e107205fdbdb822b807cf6ced9c8bc', 'dropTable tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-130::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_allggruenflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-130', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1058, '8:5dbfc6a2605ce8092d8a12aa1cb9cf78', 'dropTable tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-131::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_anpflanzungbindungerhaltung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-131', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1059, '8:5c3eb57d1ae2310e7fac227ae8618aa5', 'dropTable tableName=xplan_lp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-132::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_ausgleich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-132', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1060, '8:4cd9776057f21599147f9b1ae17a0d76', 'dropTable tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-133::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-133', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1061, '8:41e2c473cb585bf2722efce0a29e69ba', 'dropTable tableName=xplan_lp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-134::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_biotopverbundflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-134', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1062, '8:e7618978b17b848ccf4f543c77085059', 'dropTable tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-135::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_bodenschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-135', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1063, '8:972220f1b9c7746c10b6f9c6cfe403b4', 'dropTable tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-136::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_denkmalschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-136', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1064, '8:8d7d11661f0af6dba14f1e1e19f37346', 'dropTable tableName=xplan_lp_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-137::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_erholungfreizeit;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-137', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1065, '8:8317df898ac1cecb45fa1878c536e29b', 'dropTable tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-138::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_forstrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-138', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1066, '8:89d944aaa9ccec70f5b60fa52030c0a0', 'dropTable tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-139::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-139', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1067, '8:d7e8b29f9bb531c2dcecb6edf536eafb', 'dropTable tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-140::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_landschaftsbild;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-140', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1068, '8:0c52d2a2589cdc2e657b1dba2bee19ff', 'dropTable tableName=xplan_lp_landschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-141::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_biotopschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-141', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1069, '8:2c7a98d33e5ff65faffb01fe28a843b1', 'dropTable tableName=xplan_lp_nrw_biotopschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-142::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_brachflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-142', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1070, '8:f9e85268d676872a5beddd76c63f8a6f', 'dropTable tableName=xplan_lp_nrw_brachflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-143::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_elementekulturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-143', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1071, '8:f152f7edeb8f5775e73b6ffc6d6e2723', 'dropTable tableName=xplan_lp_nrw_elementekulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-144::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_forstlichefestsetzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-144', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1072, '8:95f3621ffb55fa8a2e046f5daccdfd4a', 'dropTable tableName=xplan_lp_nrw_forstlichefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-145::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_herrichtunggrundstueck;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-145', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1073, '8:fa9f7ca0a37de0ab2a999b4a1e996694', 'dropTable tableName=xplan_lp_nrw_herrichtunggrundstueck', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-146::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_pflegeanpflanzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-146', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1074, '8:121e75bbd1d009547fb6ad8df0b57f5c', 'dropTable tableName=xplan_lp_nrw_pflegeanpflanzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-147::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_pflegelandschaftsbild;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-147', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1075, '8:ead7ce7bd5755b6279726facd03e6c47', 'dropTable tableName=xplan_lp_nrw_pflegelandschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-148::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_schutzobjektlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-148', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1076, '8:0a5bfd4fd4455a1fac1be0d166db9f38', 'dropTable tableName=xplan_lp_nrw_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-149::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_sonstigemassnahme;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-149', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1077, '8:806be16e30f44cfd095cc4bc4e61d5c1', 'dropTable tableName=xplan_lp_nrw_sonstigemassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-150::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_strukturenelementebesiedelterbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-150', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1078, '8:39b52ab44b322c0d48693f95023870f0', 'dropTable tableName=xplan_lp_nrw_strukturenelementebesiedelterbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-151::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_temporaererlandschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-151', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1079, '8:9aa413e40303f19c6f30df6fe17a11b6', 'dropTable tableName=xplan_lp_nrw_temporaererlandschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-152::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nrw_verpflichtungwrrl;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-152', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1080, '8:6a117d86f84e33547655ac53293debe9', 'dropTable tableName=xplan_lp_nrw_verpflichtungwrrl', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-153::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nutzungsausschluss;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-153', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1081, '8:0afca0c40100f8271729e588560d41ae', 'dropTable tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-154::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_nutzungserfordernisregelung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-154', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1082, '8:4186b753a4d0c8179099e925c909b903', 'dropTable tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-155::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-155', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1083, '8:716764abcdb3196e41cddb33f3d271a6', 'dropTable tableName=xplan_lp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-156::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_planerischevertiefung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-156', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1084, '8:94ea2545e3e14fb1afc78ca325ed424b', 'dropTable tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-157::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-157', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1085, '8:7fcefdbf968c3b0c1f911c77903d93a9', 'dropTable tableName=xplan_lp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-158::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektbundesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-158', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1086, '8:7a4941de612a6cbac0162e5b6e3826f0', 'dropTable tableName=xplan_lp_schutzobjektbundesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-159::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektinternatrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-159', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1087, '8:13bf64c71e65eb9c181a43767d70f03a', 'dropTable tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-160::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzobjektlandesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-160', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1088, '8:6b26273904661d7f947d01130a255692', 'dropTable tableName=xplan_lp_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-161::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_schutzpflegeentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-161', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1089, '8:b185604d233e8191cf94a639b2f79566', 'dropTable tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-162::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_sonstigeabgrenzuung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-162', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1090, '8:d01384f351371a5ef2e3a789e6e19ea4', 'dropTable tableName=xplan_lp_sonstigeabgrenzuung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-163::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_sonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-163', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1091, '8:d756cc564783c2b50653a84fa505704f', 'dropTable tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-164::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-164', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1092, '8:7e3554dfc779f55669ebd61bd8bbac39', 'dropTable tableName=xplan_lp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-165::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_textlichefestsetzungsflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-165', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1093, '8:6fc658639de93f9b9c3700f4d2053e58', 'dropTable tableName=xplan_lp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-166::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-166', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1094, '8:3095fb1b2bd9df56be4c2240d1bcd5ab', 'dropTable tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-167::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-167', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1095, '8:7c3b204c622fc2a614f36520d7c2f74e', 'dropTable tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-168::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtsonstige;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-168', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1096, '8:579d7f65c31f88f84b586ea273b1d537', 'dropTable tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-169::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_wasserrechtwirtschaftabflusshochwschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-169', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1097, '8:44b5e2f71ba150983857ba14f7cff268', 'dropTable tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-170::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_zubegruenendegrundstueckflaeche;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-170', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1098, '8:0c78271d1b2188bab278e58e3a75d335', 'dropTable tableName=xplan_lp_zubegruenendegrundstueckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-171::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_lp_zwischennutzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-171', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1099, '8:9d5de5f1dc37d79cb0d53c140c434594', 'dropTable tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-172::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_achse;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-172', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1100, '8:8e2bc00c6b172014a3599bc76f5e370b', 'dropTable tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-173::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-173', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1101, '8:9cb917fd8fae9f0352cbe1794773fa3d', 'dropTable tableName=xplan_rp_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-174::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_bodenschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-174', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1102, '8:8152da5c11d28e6e7bf76da9a58885a0', 'dropTable tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-175::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_einzelhandel;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-175', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1103, '8:30ebbdb4d61ed19c572f7371a9d8aee2', 'dropTable tableName=xplan_rp_einzelhandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-176::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_energieversorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-176', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1104, '8:df8b1ba2697f78d48c00f941053a319b', 'dropTable tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-177::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_entsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-177', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1105, '8:1326f9ca188531bb2c4500bf5655cdbf', 'dropTable tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-178::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_entwicklungsschwerpunkte;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-178', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1106, '8:c54387a29bf4ed99f4fd2f0d511f54cf', 'dropTable tableName=xplan_rp_entwicklungsschwerpunkte', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-179::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_erholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-179', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1107, '8:8109919e191f8d36adb6d5cfb917b20a', 'dropTable tableName=xplan_rp_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-180::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_erneuerbareenergie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-180', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1108, '8:cf0d2154253bec1e798ffc43136d90c9', 'dropTable tableName=xplan_rp_erneuerbareenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-181::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_forstwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-181', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1109, '8:8879236d032ac073886ef3078b5ae714', 'dropTable tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-182::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_freiraum;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-182', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1110, '8:a48d64540d3acce25884a9f899d77d5e', 'dropTable tableName=xplan_rp_freiraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-183::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_freizeiterholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-183', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1111, '8:83b7560917a02b01e9648b05ad860da3', 'dropTable tableName=xplan_rp_freizeiterholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-184::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_funktionszuweisung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-184', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1112, '8:0f7555e5e6c412be187c8f75978a8239', 'dropTable tableName=xplan_rp_funktionszuweisung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-185::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gemeindefunktionsiedlungsentwicklung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-185', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1113, '8:9f8357c3daec7fb7082c3ed169353318', 'dropTable tableName=xplan_rp_gemeindefunktionsiedlungsentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-186::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_generischesobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-186', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1114, '8:e3fb05f55c89a6242cbb226e5d517172', 'dropTable tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-187::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-187', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1115, '8:535c8c1593995f41c73e7ca58bb37cae', 'dropTable tableName=xplan_rp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-188::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-188', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1116, '8:832b975d9f31f0a4b478dc531a294371', 'dropTable tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-189::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_gruenzuggruenzaesur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-189', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1117, '8:acd0769cde915526d93c2305f7e8bfed', 'dropTable tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-190::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_hochwasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-190', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1118, '8:b65bbeb0a4b54d72f075365c3bf12c17', 'dropTable tableName=xplan_rp_hochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-191::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_industriegewerbe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-191', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1119, '8:7f575f5f09a0418370a83e5144714b41', 'dropTable tableName=xplan_rp_industriegewerbe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-192::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_klimaschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-192', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1120, '8:46e1f00fe1de28ee2005684d54820466', 'dropTable tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-193::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kommunikation;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-193', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1121, '8:f99e1ace6ec456b7887a1509614c2d54', 'dropTable tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-194::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kulturellessachgut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-194', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1122, '8:28fd25a3fec09d908955439d05307b68', 'dropTable tableName=xplan_rp_kulturellessachgut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-195::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_kulturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-195', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1123, '8:5e613d8361cd5ae756af5d00480c6d6e', 'dropTable tableName=xplan_rp_kulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-196::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_laermschutzbauschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-196', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1124, '8:8a66b2a069fe0b429860fabb818a0611', 'dropTable tableName=xplan_rp_laermschutzbauschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-197::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_laermschutzbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-197', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1125, '8:4fb60bf9d0b5dd260b214f5d93f6f78e', 'dropTable tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-198::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_landwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-198', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1126, '8:52ab49487713daf33a858673c7ea4e66', 'dropTable tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-199::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_legendenobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-199', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1127, '8:792d63525bbd6ad1341a0c7a173fe115', 'dropTable tableName=xplan_rp_legendenobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-200::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-200', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1128, '8:d9674a8760810f54584e0f1695d432a3', 'dropTable tableName=xplan_rp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-201::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_naturlandschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-201', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1129, '8:2109531bb0491b90b5ae3b09b6848c29', 'dropTable tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-202::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-202', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1130, '8:db6ed8b2af2acf0555b65cd0da123551', 'dropTable tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-203::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_asb;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-203', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1131, '8:785ebab4635f965e6cec68a5c4b98f23', 'dropTable tableName=xplan_rp_nrw_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-204::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_aufschuettungablagerung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-204', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1132, '8:b119e5a39fcda5e409518ec98d8f768f', 'dropTable tableName=xplan_rp_nrw_aufschuettungablagerung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-205::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_forstwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-205', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1133, '8:81116a0a6121f6fd6d75fcf4087188c2', 'dropTable tableName=xplan_rp_nrw_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-206::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_freiraumagrarbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-206', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1134, '8:f48c11949d0979f77ad82dedc031f0fe', 'dropTable tableName=xplan_rp_nrw_freiraumagrarbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-207::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_gib;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-207', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1135, '8:12e7dee477bce643ab20aa87d4b25a93', 'dropTable tableName=xplan_rp_nrw_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-208::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_grundwassergewaesserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-208', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1136, '8:0dcce6a309b41d89a61b9fd186a97447', 'dropTable tableName=xplan_rp_nrw_grundwassergewaesserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-209::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_laermschutzzone;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-209', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1137, '8:73957c1d5451202e00b64e9d261defaa', 'dropTable tableName=xplan_rp_nrw_laermschutzzone', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-210::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_landschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-210', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1138, '8:6cb0892e4b739f122e355c0ec1a0401c', 'dropTable tableName=xplan_rp_nrw_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-211::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-211', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1139, '8:3df652299b2a9cd732a45120fd124f27', 'dropTable tableName=xplan_rp_nrw_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-212::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_naturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-212', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1140, '8:e589d03a31a8379bb8340cb4ab9294f2', 'dropTable tableName=xplan_rp_nrw_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-213::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_oberflaechengewaesser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-213', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1141, '8:4992a6573026dc0c8835ca353169cc41', 'dropTable tableName=xplan_rp_nrw_oberflaechengewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-214::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_oberflaechennahebodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-214', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1142, '8:873d390cc0927852a73370366fdd8b9d', 'dropTable tableName=xplan_rp_nrw_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-215::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-215', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1143, '8:95b0a702aae85c9029ea64bfe4654734', 'dropTable tableName=xplan_rp_nrw_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-216::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-216', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1144, '8:0c2007c3d688ebb8fadc6f3f413b75ff', 'dropTable tableName=xplan_rp_nrw_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-217::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigersiedlungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-217', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1145, '8:8a29009080befc2710e128c32a13add4', 'dropTable tableName=xplan_rp_nrw_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-218::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstigezweckbindung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-218', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1146, '8:1c4b75cd8a4e40db27a7f947ccfbf818', 'dropTable tableName=xplan_rp_nrw_sonstigezweckbindung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-219::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-219', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1147, '8:0c03dd41c5b27e5d4afbf18cc5be60ed', 'dropTable tableName=xplan_rp_nrw_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-220::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-220', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1148, '8:eac72877095973b8a5ae501cdd18ccc9', 'dropTable tableName=xplan_rp_nrw_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-221::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_ueberschwemmungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-221', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1149, '8:710326667f28de9a46b3977b9e15a60a', 'dropTable tableName=xplan_rp_nrw_ueberschwemmungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-222::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-222', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1150, '8:1ae4ae8ee72e573307a6d1b5b1fcdb64', 'dropTable tableName=xplan_rp_nrw_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-223::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nrw_zeitlinie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-223', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1151, '8:b87382139f9322f4e2100f540a694324', 'dropTable tableName=xplan_rp_nrw_zeitlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-224::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abfallentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-224', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1152, '8:103bc53a760a13a59659ec4d85a8a24a', 'dropTable tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-225::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abwasser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-225', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1153, '8:52d59f5520189f52dabb14051e85306a', 'dropTable tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-226::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_asb;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-226', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1154, '8:c47d2e6186659a558b49aa5ee93878d1', 'dropTable tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-227::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_erholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-227', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1155, '8:69acb52bbb05a99b2b3416423b63aef6', 'dropTable tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-228::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_gib;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-228', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1156, '8:061635c1e42ce7d7338ed62bb59b6eba', 'dropTable tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-229::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_landschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-229', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1157, '8:b73bcfd9956981b6266da0ae841fd339', 'dropTable tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-230::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-230', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1158, '8:069cf70fa21b80fe05ad11f42e53a7d2', 'dropTable tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-231::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_naturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-231', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1159, '8:5480e0c799a6c36e15699318763931b0', 'dropTable tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-232::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_oberflaechennahebodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-232', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1160, '8:9be219631dd0ca29dad12077f361fa06', 'dropTable tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-233::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamerwanderweg;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-233', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1161, '8:dcb2a584d504d65851c08711eed7f271', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-234::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamesportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-234', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1162, '8:8dfc2d1e5726cfc66ec697016ce0319a', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-235::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-235', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1163, '8:ed06c12ef431032109746bdbabd2885d', 'dropTable tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-236::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-236', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1164, '8:f2249dae95e83d119c3e68fa2b55c80f', 'dropTable tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-237::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-237', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1165, '8:1d89e709f880e0e036e9002fcda9ea4f', 'dropTable tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-238::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tiefliegenderohstoffe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-238', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1166, '8:da29743a17e5627350403390f9db400e', 'dropTable tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-239::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tourismus;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-239', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1167, '8:7efccb22e7aee950ba4790b34110a298', 'dropTable tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-240::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-240', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1168, '8:20766ab07e5c763f9bb1c6aab4c64233', 'dropTable tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-241::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-241', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1169, '8:561806f04f54586468c09814e2af50d3', 'dropTable tableName=xplan_rp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-242::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_planungsraum;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-242', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1170, '8:528f2f3c4e51eff928a888793b5fb9f9', 'dropTable tableName=xplan_rp_planungsraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-243::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_radwegwanderweg;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-243', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1171, '8:d36b9e3addd4dc1264f7886d25d86ec5', 'dropTable tableName=xplan_rp_radwegwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-244::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-244', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1172, '8:1faaa6bce12166975be2fdaf28502beb', 'dropTable tableName=xplan_rp_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-245::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_raumkategorie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-245', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1173, '8:ad28fc493469182b120b162aee30ede0', 'dropTable tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-246::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rohstoff;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-246', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1174, '8:6d69be02ea568c12d1ab9556317abb6d', 'dropTable tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-247::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_rohstoffsicherung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-247', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1175, '8:8ad0ca4fd76c03024cf606bf17be8393', 'dropTable tableName=xplan_rp_rohstoffsicherung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-248::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-248', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1176, '8:f07cbc0761a63bcd38ec260f1c852dff', 'dropTable tableName=xplan_rp_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-249::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_siedlung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-249', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1177, '8:4870982f9e07145b0d887a8d828942a6', 'dropTable tableName=xplan_rp_siedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-250::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-250', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1178, '8:361f7b637bebe71764f0e20b3a5d955a', 'dropTable tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-251::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigerfreiraumschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-251', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1179, '8:63d043bb4c8a93596bbffa0955134eee', 'dropTable tableName=xplan_rp_sonstigerfreiraumschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-252::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigerfreiraumstruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-252', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1180, '8:0b1c5f6ebabc2535cb134a5c38db8a54', 'dropTable tableName=xplan_rp_sonstigerfreiraumstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-253::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigersiedlungsbereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-253', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1181, '8:532fa05ce8cb28423cee0901c3718b97', 'dropTable tableName=xplan_rp_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-254::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstigesiedlungsstruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-254', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1182, '8:8f1f9a16425ea2e841dee95c4be342b0', 'dropTable tableName=xplan_rp_sonstigesiedlungsstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-255::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-255', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1183, '8:e4262edee509c874010e196ebc88b6d6', 'dropTable tableName=xplan_rp_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-256::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sozialeinfrastruktur;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-256', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1184, '8:99e58a1f507c31438f94df47ea13781a', 'dropTable tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-257::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sperrgebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-257', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1185, '8:fb58c8a3792ec8bf4c3361f752d6bb5f', 'dropTable tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-258::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_sportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-258', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1186, '8:a82cbd3901d65ce5fdc18908aa5d7ad2', 'dropTable tableName=xplan_rp_sportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-259::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-259', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1187, '8:f0803adde29366e0dc6bc43b9b83a686', 'dropTable tableName=xplan_rp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-260::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-260', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1188, '8:07b7a4c1add7dc9d09332c9488453600', 'dropTable tableName=xplan_rp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-261::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_verkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-261', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1189, '8:54729d06d64fee9219677914fa797bd1', 'dropTable tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-262::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_vorbhochwasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-262', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1190, '8:ac34ff60daf312e156f2985a77705139', 'dropTable tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-263::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-263', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1191, '8:1dfec4c5338515edd24b6d2d5528d431', 'dropTable tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-264::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-264', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1192, '8:e8cebecb165a6eef0f2c3b601d723aa3', 'dropTable tableName=xplan_rp_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-265::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wasserwirtschaft;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-265', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1193, '8:f0b1378d266c9d3460d41ed18bb9c749', 'dropTable tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-266::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_windenergie;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-266', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1194, '8:0dd75e59588aad9ff4a4a596f2188827', 'dropTable tableName=xplan_rp_windenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-267::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_windenergienutzung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-267', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1195, '8:e123431df67d85b46acbd2107fd52e09', 'dropTable tableName=xplan_rp_windenergienutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-268::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_wohnensiedlung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-268', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1196, '8:0af62cda8c3798dca75cf879f7ea8020', 'dropTable tableName=xplan_rp_wohnensiedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-269::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_rp_zentralerort;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-269', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1197, '8:59340b77f96c5c898b2c66b2f4954ee3', 'dropTable tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-270::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_bereich;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-270', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1198, '8:3724a3488def18e70109519569c4197a', 'dropTable tableName=xplan_so_bereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-271::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_bodenschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-271', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1199, '8:5aff6cfa28f37054e811aab3bfbe96a3', 'dropTable tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-272::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_denkmalschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-272', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1200, '8:4354f642e6e8ad61d27455252e458933', 'dropTable tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-273::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_forstrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-273', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1201, '8:7a0530b8b499559aab3ebc3eb57c4371', 'dropTable tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-274::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_gebiet;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-274', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1202, '8:15e85073e28a5592d3af8094554019ac', 'dropTable tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-275::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-275', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1203, '8:5f27473937af889ae3522acc4dfb78e2', 'dropTable tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-276::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_linienobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-276', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1204, '8:8abd2a1ca3ad3dfb1c82f4dc74203fff', 'dropTable tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-277::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_luftverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-277', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1205, '8:f9ad6822d37b4498a72c684059037fa0', 'dropTable tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-278::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_objekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-278', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1206, '8:e7d9a2f2e28cd9897891645b067f7c4f', 'dropTable tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-279::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_plan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-279', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1207, '8:5dc79ff452f8f68ffa8f3edcb75cf3c3', 'dropTable tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-280::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_rasterplanaenderung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-280', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1208, '8:ed6184ccfc11a3221571c5481e35a65f', 'dropTable tableName=xplan_so_rasterplanaenderung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-281::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schienenverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-281', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1209, '8:c3a880bddba55186a6fa17f3fab3926b', 'dropTable tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-282::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietnaturschutzrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-282', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1210, '8:6e667e3564ffcd3073f3122e84ee36e3', 'dropTable tableName=xplan_so_schutzgebietnaturschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-283::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietsonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-283', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1211, '8:96508c606369cdc9957678ca45ad84c9', 'dropTable tableName=xplan_so_schutzgebietsonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-284::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_schutzgebietwasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-284', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1212, '8:0b181b5b43e599f9bd5d4687bfbb75f6', 'dropTable tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-285::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_sonstigesrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-285', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1213, '8:9bfe688e089e00684dddd527291fbe75', 'dropTable tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-286::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_strassenverkehrsrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-286', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1214, '8:346ca0491b706366921e110c5fa86892', 'dropTable tableName=xplan_so_strassenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-287::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-287', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1215, '8:aba6566042d02943db19d9b873dca08f', 'dropTable tableName=xplan_so_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-288::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_so_wasserrecht;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-288', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1216, '8:997a38feeb3f0b256293ed230c8a6506', 'dropTable tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-289::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_begruendungabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-289', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1217, '8:b62174b6e1b43ac3559e12d07aea12ab', 'dropTable tableName=xplan_xp_begruendungabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-290::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_datumattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-290', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1218, '8:b53a9d157bc0275c5b395e925bfaf840', 'dropTable tableName=xplan_xp_datumattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-291::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_doubleattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-291', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1219, '8:a07cb442a3447eda1b0e60c377a9833a', 'dropTable tableName=xplan_xp_doubleattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-292::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_externereferenz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-292', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1220, '8:e02d369c814695aff08e6705ecbdcc65', 'dropTable tableName=xplan_xp_externereferenz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-293::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_externereferenzplan;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-293', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1221, '8:4da7a3834ae9c9d26c83641185216cb9', 'dropTable tableName=xplan_xp_externereferenzplan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-294::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_fpo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-294', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1222, '8:1c36cdb36545894a97a71096d5c87604', 'dropTable tableName=xplan_xp_fpo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-295::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_grenze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-295', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1223, '8:3a4a5021e24d50028c6e7bcd334100a8', 'dropTable tableName=xplan_xp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-296::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_hoehenangabe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-296', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1224, '8:e7f5b694e2e1d49d0997c61f47478b9c', 'dropTable tableName=xplan_xp_hoehenangabe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-297::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_integerattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-297', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1225, '8:56623b883f9b5fbeaa636b3897d39f9d', 'dropTable tableName=xplan_xp_integerattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-298::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_lpo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-298', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1226, '8:406b4b2f989de44e01c03431c545bddb', 'dropTable tableName=xplan_xp_lpo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-299::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_lto;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-299', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1227, '8:893b1f35222814bb2b2dc609ef01c31e', 'dropTable tableName=xplan_xp_lto', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-300::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_nutzungsschablone;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-300', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1228, '8:b180483a42de41f9ae49d1b24cf1ac39', 'dropTable tableName=xplan_xp_nutzungsschablone', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-301::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_ppo;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-301', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1229, '8:28f6b183d78758d76de5e69a557238b5', 'dropTable tableName=xplan_xp_ppo', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-302::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_praesentationsobjekt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-302', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1230, '8:3677b231f8a202d4f003ac1d334a20e4', 'dropTable tableName=xplan_xp_praesentationsobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-303::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_pto;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-303', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1231, '8:d387b26e50adfe3d29eaf296055eb8a2', 'dropTable tableName=xplan_xp_pto', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-304::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_rasterdarstellung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-304', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1232, '8:6d1eb329b16db15a1cb131beea55c75f', 'dropTable tableName=xplan_xp_rasterdarstellung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-305::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_rasterplanbasis;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-305', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1233, '8:2590e2dd5d2db492ecc3176efac3fee4', 'dropTable tableName=xplan_xp_rasterplanbasis', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-306::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_stringattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-306', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1234, '8:89c4395d95219b1b253c7b79f256a7da', 'dropTable tableName=xplan_xp_stringattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-307::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_textabschnitt;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-307', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1235, '8:595989da4def0f8fe86202f432edf644', 'dropTable tableName=xplan_xp_textabschnitt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-308::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_urlattribut;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-308', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1236, '8:27910458afed582235088564b2eefa83', 'dropTable tableName=xplan_xp_urlattribut', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Changeset changelog_xplansynarchive.xml::1543832242710-309::lgvxplanisk (generated)
DROP TABLE xplansynarchive.xplan_xp_verfahrensmerkmal;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543832242710-309', 'lgvxplanisk (generated)', 'changelog_xplansynarchive.xml', NOW(), 1237, '8:53d02c8d8cdce9b29e106d984b163b64', 'dropTable tableName=xplan_xp_verfahrensmerkmal', '', 'EXECUTED', NULL, NULL, '3.6.2', '3832317380');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

