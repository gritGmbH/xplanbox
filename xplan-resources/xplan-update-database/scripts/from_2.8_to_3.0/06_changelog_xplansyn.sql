-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansyn.xml
-- Ran at: 29.10.19 16:04
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox213
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE IF NOT EXISTS xplansyn.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansyn.databasechangeloglock;

INSERT INTO xplansyn.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansyn.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = '172.22.0.1 (172.22.0.1)', LOCKGRANTED = '2019-10-29 16:04:11.502' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE IF NOT EXISTS xplansyn.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansyn.xml::1572361327594-1::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_abgrabungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-1', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 1, '8:f65322e442e66ef7c6353c6ca073fc1f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-2::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-2', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 2, '8:4266bc12fc8a3210423631e2b581b40a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-3::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-3', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 3, '8:06f3c90e4c81ee9ae9689f093bba807a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-4::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_aufschuettungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-4', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 4, '8:8fb45fe1b327696cd89e4029eafe2d5d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-5::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-5', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 5, '8:6d84f44cac3df7ec20302062342f9ed4', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-6::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-6', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 6, '8:5f2e6e1ad7b09c31f5961b3c981287c7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-7::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsmassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-7', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 7, '8:c6c4c7aa3d779e6f9bbbe2f941020754', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-8::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_bahnverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-8', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 8, '8:ebbbc5fd0616686e5cea5051daecaf58', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-9::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baugebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-9', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 9, '8:8f3e3bd796d223ad8c0362ca9840281e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-10::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-10', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 10, '8:4e2f03048fa70f39d1655dcbe752193c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-11::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baugrenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-11', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 11, '8:c5d3db7dcf5bbe72da0e587f39e30d42', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-12::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baulinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-12', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 12, '8:59582f425eb0732e30485fbf7172d1aa', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-13::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_bauschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-13', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 13, '8:81c097579116c5621724e6abe9cfbba3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-14::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_bereichohneeinausfahrtlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-14', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 14, '8:3235ab08a9ded3f06bbe9933a15d160a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-15::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-15', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 15, '8:25b02e07d3b9d931866cd374a19f5563', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-16::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_bodenschaetzeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-16', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 16, '8:8206256d36349742955a498af960f9f1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-17::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlagepunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-17', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 17, '8:1c74743921d05aa2a1c0a230f40e58f5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_denkmalschutzeinzelanlagepunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-18::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzensembleflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-18', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 18, '8:d214642d5892b1b4cdc7980d1ef12942', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_denkmalschutzensembleflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-19::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtpunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-19', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 19, '8:61326ce332d9864247ad0430d37db9cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-20::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtsbereichlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-20', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 20, '8:fd344b5431ffea4472b621f4776af77b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-21::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_erhaltungsbereichflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-21', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 21, '8:c088595112162e41bfc8c8dde92df7cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-22::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_erneuerbareenergieflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-22', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 22, '8:17c5acd3eea0b884c84aabdec8ddb077', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_erneuerbareenergieflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-23::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_fachgesetz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-23', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 23, '8:4979fd2f37cc32890ebc203aa0c89073', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-24::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_firstrichtungslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-24', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 24, '8:b9792a32f6834b012be973b35272533d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-25::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_foerderungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-25', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 25, '8:c55753bef1b7e6981e2feb0d27e4815c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-26::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_freiflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-26', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 26, '8:625312407f91cc60c67f8e09f38ea37c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-27::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gebaeudeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-27', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 27, '8:ecfa2bc84b60af7bda76933a17288722', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-28::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-28', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 28, '8:f39916dce3c1c74ebf9654b029b47db3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-29::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-29', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 29, '8:2bce4a639fe53d9a502ce057f84593a7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-30::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-30', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 30, '8:b6f15b99b919210e4d7b767fc47a16fd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-31::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gewaesserflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-31', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 31, '8:d58504eee476b87271473b48290ad559', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-32::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_grabungsschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-32', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 32, '8:20e39182f6a8f1e4a38cb68e8cf9f6e3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-33::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-33', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 33, '8:c9c509d31fd12193294d7855f1b16366', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-34::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenpunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-34', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 34, '8:5c31db9e0fc52cfb79745527b12e4d31', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_hoehenpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-35::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_immissionsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-35', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 35, '8:b1545bc2656c10bef215200b99706689', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-36::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_kennzeichnungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-36', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 36, '8:07a8fe69f718f311a14a40c9e42813f5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-37::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-37', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 37, '8:e335d2cbca580cbd33cc4ccf973b0a3a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-38::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-38', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 38, '8:39ab9eceb8ca549366166668178f9f02', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-39::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-39', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 39, '8:dfde28907abd9e1a1332e577f20e9102', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_landwirtschaftslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-40::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_luftreinhalteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-40', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 40, '8:375b00efffe7ca317c94d86643793d46', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_luftreinhalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-41::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_luftverkehrflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-41', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 41, '8:101f80f207e4d8acaaa6666cc40a7db6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_luftverkehrflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-42::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenausschlussflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-42', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 42, '8:257ce5ca2f5a684fcbf007e56dbaa579', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-43::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-43', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 43, '8:b88d06c86392a0a204f0be965e825f4a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-44::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_nutzungsartengrenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-44', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 44, '8:39491c514fb05661fc05dc33fe8e43d7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-45::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_persgruppenbestimmteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-45', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 45, '8:435578ed0bed33828b0a08160586be01', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-46::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_rekultivierungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-46', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 46, '8:45d9a75c33287efd52dc09c6c29f0611', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-47::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_schutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-47', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 47, '8:2940e42ed8df21825176c444bead1b5e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-48::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-48', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 48, '8:9eb314b2020ef29b18aa8f60b2e6b46e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-49::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsmassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-49', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 49, '8:41437d759a69cb1e07d3be51ec4a6b59', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-50::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-50', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 50, '8:8ad32317eb2e9b636be0a3012cce2ce5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-51::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-51', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 51, '8:02cd611b591f63e97f99aa4097f7bb6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-52::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_strassenbegrenzungslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-52', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 52, '8:b6957fd3796197807ca1cc1920c77321', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-53::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_strassenkoerper DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-53', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 53, '8:2a32db45b1b2ae1c3ff662aaefd511b4', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-54::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-54', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 54, '8:55164314b7be20922c34aff0d1dca576', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-55::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_technikbestimmteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-55', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 55, '8:f68521306d18e683b2104d3038de0aa3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_technikbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-56::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_textlichefestsetzungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-56', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 56, '8:a0e4461ca364641d2ca30c0070288bf8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-57::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-57', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 57, '8:a893fd05d17e8c5dbb9919465dde9638', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-58::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_unverbindlichevormerkung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-58', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 58, '8:401b44bd2195750a23812f3c0651479a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-59::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-59', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 59, '8:13d4deba076e70fc6413b2f4777d9ab0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-60::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-60', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 60, '8:d434ff2b9c9b93b9e54629c2e5a3b34b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-61::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsleitunglinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-61', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 61, '8:4fedba544340bc58365c7c000126fcfb', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgungsleitunglinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-62::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-62', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 62, '8:f3da4e2ca2b55eb15bd5c5c4ebf6f060', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-63::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_vorbhochwschutzflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-63', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 63, '8:dedc815efce98ed81be354f7e17f7f93', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_vorbhochwschutzflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-64::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_waldflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-64', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 64, '8:bed0dd87f7d6cf6048491e66f129d74f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-65::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_wasserrechtlichefestsetzungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-65', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 65, '8:729b89c3c5655a543e53f38dc9034427', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wasserrechtlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-66::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_wasserwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-66', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 66, '8:fc93efd5443e120cf179785ffc7d83db', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-67::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-67', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 67, '8:aecae77e9ce2f3d29482bd2cbd21305f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-68::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-68', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 68, '8:3113fa4585bfb4a22f14eb1816c8cc8f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-69::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-69', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 69, '8:2e5d4a83948189ae3182840769906605', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-70::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-70', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 70, '8:d364dc28973a69908390e74da37b2ac1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-71::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bahnverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-71', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 71, '8:86343ecea10a00a436706b318ad22114', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-72::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bauschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-72', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 72, '8:3c702d2e4813f1d8e8b6555f84092033', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-73::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-73', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 73, '8:5152c69d99273aae3f5879eee71e8bcb', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-74::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetzeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-74', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 74, '8:42985e0ccce3bb479fddeeb6201438a5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-75::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_denkmalschutzensemble DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-75', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 75, '8:e22c6183e195377dbc4a0b703b6a2579', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_denkmalschutzensemble', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-76::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_erhaltungssatzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-76', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 76, '8:6cdc2c83ec997cb9b9fba35224dc5473', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_erhaltungssatzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-77::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_fachgesetz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-77', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 77, '8:159db1fb25ce7b18cfe3966f8857a196', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-78::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-78', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 78, '8:eb67b4a8530cec0b8b61b35bbecaec7d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-79::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-79', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 79, '8:65755bc01392f390bb2256022628fa78', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-80::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-80', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 80, '8:f94eb4a66256ad1db65419788da42ab2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-81::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_grabungsschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-81', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 81, '8:2e8c8d3e46044124a8b7228376b45cb8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-82::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gruen DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-82', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 82, '8:65d80c0fc753edbe6415692f51a7ceb9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-83::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-83', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 83, '8:c45e55dfc2ae0b67799b70e39bda1efc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-84::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-84', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 84, '8:1df48d5a53e36ca99c9fa7f00ad48b19', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-85::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-85', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 85, '8:1541c9d9ef9121b87ebb44ad6f74fced', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-86::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-86', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 86, '8:e6df5e7763eada1b5ec26567c930c589', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-87::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-87', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 87, '8:54dfb6e6931bd4d4ce98e4d679c5b112', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-88::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-88', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 88, '8:d15682e6f3ce9264d386c476a3befedf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-89::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_priviligiertesvorhaben DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-89', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 89, '8:851a1caa3c00a40bc5b6a1785483bc7b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_priviligiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-90::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_schutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-90', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 90, '8:28d1efcda37e2e4c293b7a13abc7c558', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-91::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-91', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 91, '8:7a5fff6a62b0891ea588d0c2711a7487', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-92::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-92', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 92, '8:13deb203782ee113c9d8a771d03c7639', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-93::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-93', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 93, '8:2f3ef570ec734050757987ebb7fdd1b3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-94::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-94', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 94, '8:b8f711eb2cb77e9328808317bc19233a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-95::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-95', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 95, '8:b148a321b688793dbb3f835dba1c508f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-96::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-96', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 96, '8:93ccf58c1df56ca2a1cc373fec34be6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-97::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-97', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 97, '8:01abe04248837a4f19b02a344a0fc27a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-98::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_vorbhochwschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-98', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 98, '8:b1fe000c077c29c6033d6e9d432b6998', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_vorbhochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-99::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-99', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 99, '8:d8611f9ae10e485e30109312f20c2910', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-100::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_wasserrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-100', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 100, '8:4d8be5824e00cfd694999ff762b15596', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-101::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-101', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 101, '8:157e46d0124c2c50f8910928578505be', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-102::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_abgrenzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-102', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 102, '8:8d1dd744b2a0959c10144ac748bf4c91', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-103::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_allggruenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-103', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 103, '8:9d34ad8a65ab7e17c98289185e90c126', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-104::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_ausgleich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-104', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 104, '8:77ed46c2c5fa74b3e488d98efb8da290', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-105::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_biotopverbundflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-105', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 105, '8:aba4c7e8ff0f146ed3b9b8661203e5f8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-106::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_bodenschutzrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-106', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 106, '8:dccdb456d3da2c24e5ffea2dca3452e2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-107::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_denkmalschutzrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-107', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 107, '8:93cbd710ceac495c48874fcdfe456d2a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-108::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_erholungfreizeit DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-108', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 108, '8:9bae634c1261062eafd619fb3ddb0849', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-109::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_forstrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-109', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 109, '8:f3edfce16ab5e94eacbbcf710b983a36', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-110::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-110', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 110, '8:bdca6df987273f954a1caaf770e017dc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-111::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_biotopschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-111', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 111, '8:e2b7a70ce92fa21ce15f4abded56c349', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_biotopschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-112::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_brachflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-112', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 112, '8:6f31cca5f70260cb48842fcd2691b05d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_brachflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-113::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_elementekulturlandschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-113', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 113, '8:a637be14b03258e3463914182f22797e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_elementekulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-114::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_forstlichefestsetzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-114', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 114, '8:391c196444c3a7591025804f3410aaf9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_forstlichefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-115::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_herrichtunggrundstueck DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-115', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 115, '8:1cbcc88148c290e4c7c9e2e71890fa1c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_herrichtunggrundstueck', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-116::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegeanpflanzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-116', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 116, '8:590bcd6311cfe90828861833ca88dd6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_pflegeanpflanzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-117::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegelandschaftsbild DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-117', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 117, '8:145027dee4c87c83739568a2e5816f6b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_pflegelandschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-118::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_schutzobjektlandesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-118', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 118, '8:f58c1e9d937c980cf9976e402415120d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-119::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_sonstigemassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-119', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 119, '8:8a0f8a5512638722157ee702fd7570a3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_sonstigemassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-120::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_strukturenelementebesiedelterbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-120', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 120, '8:9f9da9712e907ad8973c56f512b881e9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_strukturenelementebesiedelterbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-121::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_temporaererlandschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-121', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 121, '8:ebb56e86bdf6395be561e8c99c91cb28', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_temporaererlandschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-122::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_verpflichtungwrrl DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-122', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 122, '8:853e44715135c4cf30b330bcd4011447', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_verpflichtungwrrl', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-123::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungsausschluss DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-123', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 123, '8:f5997322b69bb51e97646192097aefdd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-124::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungserfordernisregelung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-124', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 124, '8:130bb4c70baca1abe446fdbdc474ba0c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-125::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_planerischevertiefung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-125', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 125, '8:977cd276eea1538b720482be7d7ad059', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-126::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektbundesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-126', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 126, '8:7536a2e5d066cfd13589cc75c110f0d2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzobjektbundesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-127::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektinternatrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-127', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 127, '8:f0987bbf726a10c9a6ddc0de6794075f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-128::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_schutzpflegeentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-128', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 128, '8:8ae45d86553d420d40c127b7d4304fdf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-129::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-129', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 129, '8:635955c727ed571981f99a9a037acbd8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-130::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-130', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 130, '8:85b384b71f807a7ee0986ed037696670', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-131::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-131', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 131, '8:dd1e47fe6fd2381e3c85aea137510713', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-132::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtsonstige DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-132', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 132, '8:40b140c0f52eaeec5932ce561e001bb6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-133::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtwirtschaftabflusshochwschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-133', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 133, '8:3054f836a857f31cffbe69edea610fe5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-134::lyn (generated)
ALTER TABLE xplansyn.xplan_lp_zwischennutzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-134', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 134, '8:a34d385b47758c0c22d06b67a6b085a9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-135::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_achse DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-135', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 135, '8:7010a5c66d15be826f1b006e51fddfa3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-136::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_bodenschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-136', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 136, '8:fba89ebf052081d948626d9b5704cf9f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-137::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_energieversorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-137', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 137, '8:eba403d133332f8674656528621390dd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-138::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_entsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-138', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 138, '8:8939fa4b3d9afad767370c7ed4272333', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-139::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_entwicklungsschwerpunkte DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-139', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 139, '8:aeda81e391b29a9000e89b99388609cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_entwicklungsschwerpunkte', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-140::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_forstwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-140', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 140, '8:a4fab2be7582da39df6a96cebd387941', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-141::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_freizeiterholung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-141', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 141, '8:e2b45750a6f1110ca189b877ba840921', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_freizeiterholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-142::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_gemeindefunktionsiedlungsentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-142', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 142, '8:36f04eb5e3506a917bcf63a1245abe0a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_gemeindefunktionsiedlungsentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-143::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-143', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 143, '8:391aa1dbdde896d9b3cb5627c4525a5c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-144::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_grenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-144', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 144, '8:ed39d5c2ded04723f60c78afc3284011', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-145::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_gruenzuggruenzaesur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-145', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 145, '8:d1e33008c319a3754ae8da2f368c02cd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-146::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_klimaschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-146', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 146, '8:0939c24fec4d9db5854e2157db2072d9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-147::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_kommunikation DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-147', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 147, '8:e2669451e7791f3086208edacddeed46', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-148::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-148', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 148, '8:e98c2e7449582aced3ab9cfd96995d41', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-149::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_landwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-149', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 149, '8:dbab71cd978a839a7619fc78c7dfafc3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-150::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_naturlandschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-150', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 150, '8:6b80eef7ab27b0a18caba072527b23b8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-151::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_naturschutzrechtlichesschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-151', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 151, '8:736cbb252e8baf1a22ae619998d7b3ed', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-152::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_asb DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-152', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 152, '8:6c52d4ab9df9b5c7c062e6df3da94153', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-153::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_aufschuettungablagerung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-153', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 153, '8:56b9333bf49c7b7ba62feff8f68da64f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_aufschuettungablagerung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-154::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_forstwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-154', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 154, '8:f468918579adccb29d29be732cd6b7a1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-155::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_freiraumagrarbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-155', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 155, '8:569c55edc07d80b63ce8c1640a1b9e77', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_freiraumagrarbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-156::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_gib DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-156', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 156, '8:04615a46317584251a3dd2af35d6dbba', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-157::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_grundwassergewaesserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-157', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 157, '8:b0c62500a7bf92afce46aecedf4ec99b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_grundwassergewaesserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-158::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_laermschutzzone DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-158', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 158, '8:37a71d5fd11885af9de1da57d5cf9b85', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_laermschutzzone', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-159::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_landschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-159', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 159, '8:62aa949c736332ae6ed5f2c4589593a5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-160::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-160', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 160, '8:ebb8c5064331d0ca55c1dea0a5342b87', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-161::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_naturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-161', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 161, '8:c9b2622439b3a80a3756de1dd7a6551d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-162::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechengewaesser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-162', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 162, '8:55d24b733f34ce222814bcbf0f862cdd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_oberflaechengewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-163::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechennahebodenschaetze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-163', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 163, '8:d2ac13b715b13dad8027ee97224ca62b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-164::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_schienenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-164', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 164, '8:34024676a07300013534202d0dfbb822', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-165::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-165', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 165, '8:9318346f846bc478f7e31286eed9c5fa', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-166::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigersiedlungsbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-166', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 166, '8:82e1b0e03850d1375a205f762c90ac98', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-167::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigezweckbindung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-167', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 167, '8:8b61a7a1ff991afdd958d45858e1ebac', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigezweckbindung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-168::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-168', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 168, '8:4faea1dc6da97a618b3028349c9de606', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-169::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-169', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 169, '8:94ede6da73b593bbccfa060f7d99440c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-170::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_ueberschwemmungsbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-170', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 170, '8:fb59208371b7a11c4c76fd8be1f5bdb0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_ueberschwemmungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-171::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_wasserverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-171', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 171, '8:3266b94995650482b8c362c5d302b221', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-172::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_zeitlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-172', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 172, '8:ac9c2acdf37c31ad4aec03a3a1f980fd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_zeitlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-173::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abfallentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-173', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 173, '8:9e9419ae82cdce2ecd94e6efa5dea4e6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-174::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abwasser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-174', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 174, '8:d1167fabcb58dc6e8436032e48879151', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-175::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_asb DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-175', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 175, '8:f86741e4fe9abf2873379f3b7335dbfc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-176::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_erholung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-176', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 176, '8:8e08847f0c312bcc7e125a4529a7beaf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-177::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_gib DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-177', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 177, '8:6eb19681407bbadb146c7587ebe9dd63', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-178::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_landschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-178', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 178, '8:79c127751d2abdbe8f6a0f406c81c583', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-179::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-179', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 179, '8:13bded69be20c12247a6986d110323f0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-180::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_naturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-180', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 180, '8:9aac45f584a6fcc525504eb842f935bd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-181::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_oberflaechennahebodenschaetze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-181', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 181, '8:689df34d8550062282f8da2c28cc1000', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-182::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamerwanderweg DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-182', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 182, '8:03108c249cb2fdda033810865c9bf573', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-183::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamesportanlage DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-183', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 183, '8:40bf8c88362edd02e1371760260f5358', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-184::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_schienenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-184', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 184, '8:52d5d0c25b65c22ba4bcbd660057c5a3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-185::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_sonstverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-185', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 185, '8:2258f8786efd0c29f5f543fe1a60f2b2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-186::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-186', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 186, '8:6f2c9a348ac78492b4d52a4c6af4a219', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-187::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tiefliegenderohstoffe DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-187', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 187, '8:e032fc57fd5b1725bed84f8295769525', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-188::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tourismus DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-188', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 188, '8:a4d0c28e9eb48a364c855b958ef438a8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-189::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_wasserverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-189', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 189, '8:d628f0bb45dce3cd30fcf421ba1bf1df', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-190::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-190', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 190, '8:9e51ed2fedc8d5878f2b8a8eec232823', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-191::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoffsicherung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-191', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 191, '8:d9201658a0aeeb93240da8ebf1bb934f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_rohstoffsicherung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-192::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-192', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 192, '8:667b1716835193f6f8132e1e66b75146', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-193::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigerfreiraumstruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-193', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 193, '8:dd2c134a565c9a65cda873270eb3f4b9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigerfreiraumstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-194::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigesiedlungsstruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-194', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 194, '8:f96e2a39e4d50a8ac524f50bfcab9772', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigesiedlungsstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-195::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_sozialeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-195', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 195, '8:c0432312fd68e2b5145b36553526b381', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-196::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_verkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-196', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 196, '8:7a0fbac8f7e830af6be977f6d986582f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-197::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_vorbhochwasserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-197', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 197, '8:e5e9b933ad4e8787f9e94c246ecfd55d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-198::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_wasserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-198', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 198, '8:7d75736764aa3d79e47863ca770b3f22', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-199::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_wasserwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-199', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 199, '8:5b950a33abdadea4f550c8e2c274e230', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-200::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_windenergie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-200', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 200, '8:48fbb73abdf61bf190116cfa7c7766e0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_windenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-201::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_windenergienutzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-201', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 201, '8:6c78d10e62efed2bb1165075b9fa3156', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_windenergienutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-202::lyn (generated)
ALTER TABLE xplansyn.xplan_rp_zentralerort DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-202', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 202, '8:0914427e66c679fc8dce2691d06b622c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Changeset changelog_xplansyn.xml::1572361327594-203::lyn (generated)
ALTER TABLE xplansyn.xplan_xp_grenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361327594-203', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 203, '8:ade3c0fed4e56410b6224f0254d17378', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_xp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361451940');

-- Release Database Lock
UPDATE xplansyn.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

