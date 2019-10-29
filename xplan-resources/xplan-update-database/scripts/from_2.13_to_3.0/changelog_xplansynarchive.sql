-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 29.10.19 16:04
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox213
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansynarchive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynarchive.databasechangeloglock;

INSERT INTO xplansynarchive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = '172.22.0.1 (172.22.0.1)', LOCKGRANTED = '2019-10-29 16:04:13.693' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynarchive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansynarchive.xml::1572361434180-1::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-1', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 1, '8:f65322e442e66ef7c6353c6ca073fc1f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-2::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-2', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 2, '8:4266bc12fc8a3210423631e2b581b40a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-3::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-3', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 3, '8:06f3c90e4c81ee9ae9689f093bba807a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-4::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-4', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 4, '8:8fb45fe1b327696cd89e4029eafe2d5d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-5::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-5', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 5, '8:6d84f44cac3df7ec20302062342f9ed4', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-6::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-6', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 6, '8:5f2e6e1ad7b09c31f5961b3c981287c7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-7::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-7', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 7, '8:c6c4c7aa3d779e6f9bbbe2f941020754', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-8::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bahnverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-8', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 8, '8:ebbbc5fd0616686e5cea5051daecaf58', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-9::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-9', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 9, '8:8f3e3bd796d223ad8c0362ca9840281e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-10::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-10', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 10, '8:4e2f03048fa70f39d1655dcbe752193c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-11::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-11', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 11, '8:c5d3db7dcf5bbe72da0e587f39e30d42', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-12::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-12', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 12, '8:59582f425eb0732e30485fbf7172d1aa', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-13::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bauschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-13', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 13, '8:81c097579116c5621724e6abe9cfbba3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-14::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-14', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 14, '8:3235ab08a9ded3f06bbe9933a15d160a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-15::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-15', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 15, '8:25b02e07d3b9d931866cd374a19f5563', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-16::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-16', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 16, '8:8206256d36349742955a498af960f9f1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-17::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_denkmalschutzeinzelanlagepunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-17', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 17, '8:1c74743921d05aa2a1c0a230f40e58f5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_denkmalschutzeinzelanlagepunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-18::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_denkmalschutzensembleflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-18', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 18, '8:d214642d5892b1b4cdc7980d1ef12942', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_denkmalschutzensembleflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-19::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-19', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 19, '8:61326ce332d9864247ad0430d37db9cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-20::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-20', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 20, '8:fd344b5431ffea4472b621f4776af77b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-21::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-21', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 21, '8:c088595112162e41bfc8c8dde92df7cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-22::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erneuerbareenergieflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-22', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 22, '8:17c5acd3eea0b884c84aabdec8ddb077', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_erneuerbareenergieflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-23::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_fachgesetz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-23', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 23, '8:4979fd2f37cc32890ebc203aa0c89073', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-24::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-24', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 24, '8:b9792a32f6834b012be973b35272533d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-25::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-25', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 25, '8:c55753bef1b7e6981e2feb0d27e4815c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-26::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-26', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 26, '8:625312407f91cc60c67f8e09f38ea37c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-27::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-27', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 27, '8:ecfa2bc84b60af7bda76933a17288722', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-28::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-28', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 28, '8:f39916dce3c1c74ebf9654b029b47db3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-29::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-29', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 29, '8:2bce4a639fe53d9a502ce057f84593a7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-30::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-30', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 30, '8:b6f15b99b919210e4d7b767fc47a16fd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-31::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-31', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 31, '8:d58504eee476b87271473b48290ad559', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-32::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_grabungsschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-32', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 32, '8:20e39182f6a8f1e4a38cb68e8cf9f6e3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-33::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-33', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 33, '8:c9c509d31fd12193294d7855f1b16366', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-34::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenpunkt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-34', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 34, '8:5c31db9e0fc52cfb79745527b12e4d31', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_hoehenpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-35::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-35', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 35, '8:b1545bc2656c10bef215200b99706689', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-36::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-36', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 36, '8:07a8fe69f718f311a14a40c9e42813f5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-37::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-37', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 37, '8:e335d2cbca580cbd33cc4ccf973b0a3a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-38::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-38', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 38, '8:39ab9eceb8ca549366166668178f9f02', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-39::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-39', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 39, '8:dfde28907abd9e1a1332e577f20e9102', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_landwirtschaftslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-40::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_luftreinhalteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-40', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 40, '8:375b00efffe7ca317c94d86643793d46', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_luftreinhalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-41::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_luftverkehrflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-41', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 41, '8:101f80f207e4d8acaaa6666cc40a7db6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_luftverkehrflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-42::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-42', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 42, '8:257ce5ca2f5a684fcbf007e56dbaa579', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-43::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-43', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 43, '8:b88d06c86392a0a204f0be965e825f4a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-44::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-44', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 44, '8:39491c514fb05661fc05dc33fe8e43d7', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-45::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-45', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 45, '8:435578ed0bed33828b0a08160586be01', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-46::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-46', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 46, '8:45d9a75c33287efd52dc09c6c29f0611', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-47::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-47', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 47, '8:2940e42ed8df21825176c444bead1b5e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-48::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-48', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 48, '8:9eb314b2020ef29b18aa8f60b2e6b46e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-49::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-49', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 49, '8:41437d759a69cb1e07d3be51ec4a6b59', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-50::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-50', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 50, '8:8ad32317eb2e9b636be0a3012cce2ce5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-51::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-51', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 51, '8:02cd611b591f63e97f99aa4097f7bb6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-52::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-52', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 52, '8:b6957fd3796197807ca1cc1920c77321', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-53::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-53', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 53, '8:2a32db45b1b2ae1c3ff662aaefd511b4', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-54::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-54', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 54, '8:55164314b7be20922c34aff0d1dca576', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-55::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technikbestimmteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-55', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 55, '8:f68521306d18e683b2104d3038de0aa3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_technikbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-56::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-56', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 56, '8:a0e4461ca364641d2ca30c0070288bf8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-57::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-57', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 57, '8:a893fd05d17e8c5dbb9919465dde9638', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-58::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-58', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 58, '8:401b44bd2195750a23812f3c0651479a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-59::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-59', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 59, '8:13d4deba076e70fc6413b2f4777d9ab0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-60::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-60', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 60, '8:d434ff2b9c9b93b9e54629c2e5a3b34b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-61::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgungsleitunglinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-61', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 61, '8:4fedba544340bc58365c7c000126fcfb', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verentsorgungsleitunglinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-62::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-62', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 62, '8:f3da4e2ca2b55eb15bd5c5c4ebf6f060', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-63::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_vorbhochwschutzflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-63', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 63, '8:dedc815efce98ed81be354f7e17f7f93', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_vorbhochwschutzflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-64::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-64', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 64, '8:bed0dd87f7d6cf6048491e66f129d74f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-65::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserrechtlichefestsetzungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-65', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 65, '8:729b89c3c5655a543e53f38dc9034427', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wasserrechtlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-66::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-66', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 66, '8:fc93efd5443e120cf179785ffc7d83db', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-67::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-67', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 67, '8:aecae77e9ce2f3d29482bd2cbd21305f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-68::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_abgrabungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-68', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 68, '8:3113fa4585bfb4a22f14eb1816c8cc8f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-69::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_aufschuettungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-69', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 69, '8:2e5d4a83948189ae3182840769906605', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-70::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_ausgleichsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-70', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 70, '8:d364dc28973a69908390e74da37b2ac1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-71::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_bahnverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-71', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 71, '8:86343ecea10a00a436706b318ad22114', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-72::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_bauschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-72', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 72, '8:3c702d2e4813f1d8e8b6555f84092033', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-73::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_bebauungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-73', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 73, '8:5152c69d99273aae3f5879eee71e8bcb', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-74::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_bodenschaetzeflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-74', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 74, '8:42985e0ccce3bb479fddeeb6201438a5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-75::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_denkmalschutzensemble DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-75', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 75, '8:e22c6183e195377dbc4a0b703b6a2579', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_denkmalschutzensemble', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-76::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_erhaltungssatzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-76', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 76, '8:6cdc2c83ec997cb9b9fba35224dc5473', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_erhaltungssatzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-77::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_fachgesetz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-77', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 77, '8:159db1fb25ce7b18cfe3966f8857a196', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-78::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_gemeinbedarf DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-78', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 78, '8:eb67b4a8530cec0b8b61b35bbecaec7d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-79::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-79', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 79, '8:65755bc01392f390bb2256022628fa78', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-80::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_gewaesser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-80', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 80, '8:f94eb4a66256ad1db65419788da42ab2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-81::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_grabungsschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-81', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 81, '8:2e8c8d3e46044124a8b7228376b45cb8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-82::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_gruen DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-82', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 82, '8:65d80c0fc753edbe6415692f51a7ceb9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-83::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_keinezentrabwasserbeseitigungflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-83', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 83, '8:c45e55dfc2ae0b67799b70e39bda1efc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-84::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_kennzeichnung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-84', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 84, '8:1df48d5a53e36ca99c9fa7f00ad48b19', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-85::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-85', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 85, '8:1541c9d9ef9121b87ebb44ad6f74fced', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-86::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_landwirtschaftsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-86', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 86, '8:e6df5e7763eada1b5ec26567c930c589', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-87::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-87', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 87, '8:54dfb6e6931bd4d4ce98e4d679c5b112', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-88::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_nutzungsbeschraenkungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-88', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 88, '8:d15682e6f3ce9264d386c476a3befedf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-89::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_priviligiertesvorhaben DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-89', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 89, '8:851a1caa3c00a40bc5b6a1785483bc7b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_priviligiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-90::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_schutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-90', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 90, '8:28d1efcda37e2e4c293b7a13abc7c558', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-91::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_schutzpflegeentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-91', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 91, '8:7a5fff6a62b0891ea588d0c2711a7487', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-92::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_spielsportanlage DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-92', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 92, '8:13deb203782ee113c9d8a771d03c7639', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-93::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-93', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 93, '8:2f3ef570ec734050757987ebb7fdd1b3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-94::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_textlichedarstellungsflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-94', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 94, '8:b8f711eb2cb77e9328808317bc19233a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-95::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_unverbindlichevormerkung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-95', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 95, '8:b148a321b688793dbb3f835dba1c508f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-96::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_verentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-96', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 96, '8:93ccf58c1df56ca2a1cc373fec34be6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-97::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_vorbehalteflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-97', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 97, '8:01abe04248837a4f19b02a344a0fc27a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-98::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_vorbhochwschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-98', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 98, '8:b1fe000c077c29c6033d6e9d432b6998', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_vorbhochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-99::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_waldflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-99', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 99, '8:d8611f9ae10e485e30109312f20c2910', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-100::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_wasserrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-100', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 100, '8:4d8be5824e00cfd694999ff762b15596', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-101::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_wasserwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-101', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 101, '8:157e46d0124c2c50f8910928578505be', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-102::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_abgrenzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-102', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 102, '8:8d1dd744b2a0959c10144ac748bf4c91', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-103::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_allggruenflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-103', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 103, '8:9d34ad8a65ab7e17c98289185e90c126', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-104::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_ausgleich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-104', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 104, '8:77ed46c2c5fa74b3e488d98efb8da290', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-105::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_biotopverbundflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-105', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 105, '8:aba4c7e8ff0f146ed3b9b8661203e5f8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-106::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_bodenschutzrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-106', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 106, '8:dccdb456d3da2c24e5ffea2dca3452e2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-107::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_denkmalschutzrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-107', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 107, '8:93cbd710ceac495c48874fcdfe456d2a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-108::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_erholungfreizeit DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-108', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 108, '8:9bae634c1261062eafd619fb3ddb0849', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-109::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_forstrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-109', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 109, '8:f3edfce16ab5e94eacbbcf710b983a36', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-110::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-110', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 110, '8:bdca6df987273f954a1caaf770e017dc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-111::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_biotopschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-111', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 111, '8:e2b7a70ce92fa21ce15f4abded56c349', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_biotopschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-112::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_brachflaeche DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-112', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 112, '8:6f31cca5f70260cb48842fcd2691b05d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_brachflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-113::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_elementekulturlandschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-113', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 113, '8:a637be14b03258e3463914182f22797e', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_elementekulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-114::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_forstlichefestsetzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-114', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 114, '8:391c196444c3a7591025804f3410aaf9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_forstlichefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-115::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_herrichtunggrundstueck DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-115', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 115, '8:1cbcc88148c290e4c7c9e2e71890fa1c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_herrichtunggrundstueck', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-116::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_pflegeanpflanzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-116', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 116, '8:590bcd6311cfe90828861833ca88dd6d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_pflegeanpflanzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-117::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_pflegelandschaftsbild DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-117', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 117, '8:145027dee4c87c83739568a2e5816f6b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_pflegelandschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-118::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_schutzobjektlandesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-118', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 118, '8:f58c1e9d937c980cf9976e402415120d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-119::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_sonstigemassnahme DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-119', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 119, '8:8a0f8a5512638722157ee702fd7570a3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_sonstigemassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-120::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_strukturenelementebesiedelterbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-120', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 120, '8:9f9da9712e907ad8973c56f512b881e9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_strukturenelementebesiedelterbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-121::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_temporaererlandschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-121', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 121, '8:ebb56e86bdf6395be561e8c99c91cb28', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_temporaererlandschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-122::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nrw_verpflichtungwrrl DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-122', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 122, '8:853e44715135c4cf30b330bcd4011447', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nrw_verpflichtungwrrl', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-123::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nutzungsausschluss DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-123', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 123, '8:f5997322b69bb51e97646192097aefdd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-124::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_nutzungserfordernisregelung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-124', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 124, '8:130bb4c70baca1abe446fdbdc474ba0c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-125::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_planerischevertiefung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-125', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 125, '8:977cd276eea1538b720482be7d7ad059', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-126::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_schutzobjektbundesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-126', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 126, '8:7536a2e5d066cfd13589cc75c110f0d2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzobjektbundesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-127::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_schutzobjektinternatrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-127', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 127, '8:f0987bbf726a10c9a6ddc0de6794075f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-128::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_schutzpflegeentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-128', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 128, '8:8ae45d86553d420d40c127b7d4304fdf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-129::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_sonstigesrecht DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-129', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 129, '8:635955c727ed571981f99a9a037acbd8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-130::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-130', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 130, '8:85b384b71f807a7ee0986ed037696670', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-131::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_wasserrechtschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-131', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 131, '8:dd1e47fe6fd2381e3c85aea137510713', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-132::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_wasserrechtsonstige DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-132', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 132, '8:40b140c0f52eaeec5932ce561e001bb6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-133::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_wasserrechtwirtschaftabflusshochwschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-133', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 133, '8:3054f836a857f31cffbe69edea610fe5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-134::lyn (generated)
ALTER TABLE xplansynarchive.xplan_lp_zwischennutzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-134', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 134, '8:a34d385b47758c0c22d06b67a6b085a9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-135::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_achse DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-135', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 135, '8:7010a5c66d15be826f1b006e51fddfa3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-136::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_bodenschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-136', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 136, '8:fba89ebf052081d948626d9b5704cf9f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-137::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-137', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 137, '8:eba403d133332f8674656528621390dd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-138::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_entsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-138', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 138, '8:8939fa4b3d9afad767370c7ed4272333', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-139::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_entwicklungsschwerpunkte DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-139', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 139, '8:aeda81e391b29a9000e89b99388609cf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_entwicklungsschwerpunkte', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-140::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_forstwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-140', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 140, '8:a4fab2be7582da39df6a96cebd387941', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-141::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_freizeiterholung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-141', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 141, '8:e2b45750a6f1110ca189b877ba840921', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_freizeiterholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-142::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gemeindefunktionsiedlungsentwicklung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-142', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 142, '8:36f04eb5e3506a917bcf63a1245abe0a', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_gemeindefunktionsiedlungsentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-143::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_generischesobjekt DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-143', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 143, '8:391aa1dbdde896d9b3cb5627c4525a5c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-144::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_grenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-144', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 144, '8:ed39d5c2ded04723f60c78afc3284011', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-145::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gruenzuggruenzaesur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-145', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 145, '8:d1e33008c319a3754ae8da2f368c02cd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-146::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_klimaschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-146', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 146, '8:0939c24fec4d9db5854e2157db2072d9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-147::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_kommunikation DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-147', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 147, '8:e2669451e7791f3086208edacddeed46', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-148::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_laermschutzbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-148', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 148, '8:e98c2e7449582aced3ab9cfd96995d41', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-149::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_landwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-149', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 149, '8:dbab71cd978a839a7619fc78c7dfafc3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-150::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturlandschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-150', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 150, '8:6b80eef7ab27b0a18caba072527b23b8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-151::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-151', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 151, '8:736cbb252e8baf1a22ae619998d7b3ed', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-152::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_asb DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-152', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 152, '8:6c52d4ab9df9b5c7c062e6df3da94153', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-153::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_aufschuettungablagerung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-153', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 153, '8:56b9333bf49c7b7ba62feff8f68da64f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_aufschuettungablagerung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-154::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_forstwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-154', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 154, '8:f468918579adccb29d29be732cd6b7a1', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-155::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_freiraumagrarbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-155', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 155, '8:569c55edc07d80b63ce8c1640a1b9e77', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_freiraumagrarbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-156::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_gib DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-156', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 156, '8:04615a46317584251a3dd2af35d6dbba', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-157::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_grundwassergewaesserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-157', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 157, '8:b0c62500a7bf92afce46aecedf4ec99b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_grundwassergewaesserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-158::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_laermschutzzone DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-158', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 158, '8:37a71d5fd11885af9de1da57d5cf9b85', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_laermschutzzone', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-159::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_landschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-159', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 159, '8:62aa949c736332ae6ed5f2c4589593a5', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-160::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-160', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 160, '8:ebb8c5064331d0ca55c1dea0a5342b87', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-161::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_naturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-161', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 161, '8:c9b2622439b3a80a3756de1dd7a6551d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-162::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_oberflaechengewaesser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-162', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 162, '8:55d24b733f34ce222814bcbf0f862cdd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_oberflaechengewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-163::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_oberflaechennahebodenschaetze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-163', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 163, '8:d2ac13b715b13dad8027ee97224ca62b', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-164::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_schienenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-164', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 164, '8:34024676a07300013534202d0dfbb822', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-165::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_sonstigeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-165', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 165, '8:9318346f846bc478f7e31286eed9c5fa', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-166::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_sonstigersiedlungsbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-166', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 166, '8:82e1b0e03850d1375a205f762c90ac98', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-167::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_sonstigezweckbindung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-167', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 167, '8:8b61a7a1ff991afdd958d45858e1ebac', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstigezweckbindung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-168::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_sonstverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-168', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 168, '8:4faea1dc6da97a618b3028349c9de606', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-169::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-169', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 169, '8:94ede6da73b593bbccfa060f7d99440c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-170::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_ueberschwemmungsbereich DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-170', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 170, '8:fb59208371b7a11c4c76fd8be1f5bdb0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_ueberschwemmungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-171::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_wasserverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-171', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 171, '8:3266b94995650482b8c362c5d302b221', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-172::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nrw_zeitlinie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-172', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 172, '8:ac9c2acdf37c31ad4aec03a3a1f980fd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nrw_zeitlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-173::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_abfallentsorgung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-173', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 173, '8:9e9419ae82cdce2ecd94e6efa5dea4e6', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-174::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_abwasser DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-174', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 174, '8:d1167fabcb58dc6e8436032e48879151', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-175::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_asb DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-175', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 175, '8:f86741e4fe9abf2873379f3b7335dbfc', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-176::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_erholung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-176', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 176, '8:8e08847f0c312bcc7e125a4529a7beaf', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-177::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_gib DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-177', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 177, '8:6eb19681407bbadb146c7587ebe9dd63', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-178::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_landschaftsschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-178', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 178, '8:79c127751d2abdbe8f6a0f406c81c583', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-179::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_luftverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-179', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 179, '8:13bded69be20c12247a6986d110323f0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-180::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_naturschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-180', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 180, '8:9aac45f584a6fcc525504eb842f935bd', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-181::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_oberflaechennahebodenschaetze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-181', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 181, '8:689df34d8550062282f8da2c28cc1000', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-182::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamerwanderweg DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-182', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 182, '8:03108c249cb2fdda033810865c9bf573', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-183::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamesportanlage DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-183', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 183, '8:40bf8c88362edd02e1371760260f5358', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-184::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_schienenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-184', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 184, '8:52d5d0c25b65c22ba4bcbd660057c5a3', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-185::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_sonstverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-185', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 185, '8:2258f8786efd0c29f5f543fe1a60f2b2', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-186::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_strassenverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-186', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 186, '8:6f2c9a348ac78492b4d52a4c6af4a219', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-187::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_tiefliegenderohstoffe DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-187', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 187, '8:e032fc57fd5b1725bed84f8295769525', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-188::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_tourismus DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-188', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 188, '8:a4d0c28e9eb48a364c855b958ef438a8', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-189::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_nsm_wasserverkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-189', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 189, '8:d628f0bb45dce3cd30fcf421ba1bf1df', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-190::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_raumkategorie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-190', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 190, '8:9e51ed2fedc8d5878f2b8a8eec232823', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-191::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_rohstoffsicherung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-191', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 191, '8:d9201658a0aeeb93240da8ebf1bb934f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_rohstoffsicherung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-192::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-192', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 192, '8:667b1716835193f6f8132e1e66b75146', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-193::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigerfreiraumstruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-193', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 193, '8:dd2c134a565c9a65cda873270eb3f4b9', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigerfreiraumstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-194::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigesiedlungsstruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-194', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 194, '8:f96e2a39e4d50a8ac524f50bfcab9772', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sonstigesiedlungsstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-195::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sozialeinfrastruktur DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-195', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 195, '8:c0432312fd68e2b5145b36553526b381', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-196::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_verkehr DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-196', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 196, '8:7a0fbac8f7e830af6be977f6d986582f', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-197::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_vorbhochwasserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-197', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 197, '8:e5e9b933ad4e8787f9e94c246ecfd55d', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-198::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserschutz DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-198', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 198, '8:7d75736764aa3d79e47863ca770b3f22', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-199::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserwirtschaft DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-199', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 199, '8:5b950a33abdadea4f550c8e2c274e230', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-200::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_windenergie DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-200', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 200, '8:48fbb73abdf61bf190116cfa7c7766e0', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_windenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-201::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_windenergienutzung DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-201', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 201, '8:6c78d10e62efed2bb1165075b9fa3156', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_windenergienutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-202::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_zentralerort DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-202', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 202, '8:0914427e66c679fc8dce2691d06b622c', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-203::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_grenze DROP COLUMN xplan_symbolposition;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-203', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 203, '8:ade3c0fed4e56410b6224f0254d17378', 'dropColumn columnName=xplan_symbolposition, tableName=xplan_xp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-204::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_1;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-204', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 204, '8:f4ba5f12746c8bb55bb7ace137696bc6', 'dropIndex indexName=spatial_idx_1, tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-205::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_10;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-205', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 205, '8:59fce6c9fdfddb1fada2f2dc14a0ee54', 'dropIndex indexName=spatial_idx_10, tableName=xplan_bp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-206::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_103;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-206', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 206, '8:d008f1af75ad11a1247f511ed1785dcd', 'dropIndex indexName=spatial_idx_103, tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-207::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_105;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-207', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 207, '8:61737cb951e258342928773ca1d1135e', 'dropIndex indexName=spatial_idx_105, tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-208::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_107;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-208', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 208, '8:017a0622817ffd1540a834a5c281e22b', 'dropIndex indexName=spatial_idx_107, tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-209::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_109;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-209', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 209, '8:73de585e897cffcdb5b1e9d2c060b75b', 'dropIndex indexName=spatial_idx_109, tableName=xplan_bp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-210::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_111;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-210', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 210, '8:fafca2905338f925bc7f4b5fa8f407fa', 'dropIndex indexName=spatial_idx_111, tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-211::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_113;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-211', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 211, '8:3b421b041da32b9a167c043b5a95f8a6', 'dropIndex indexName=spatial_idx_113, tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-212::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_115;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-212', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 212, '8:aa782bed1c2b78b5a65627441dbd008f', 'dropIndex indexName=spatial_idx_115, tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-213::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_117;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-213', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 213, '8:572aaadb231b2d5b9ffe71b7d2a35c2f', 'dropIndex indexName=spatial_idx_117, tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-214::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_119;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-214', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 214, '8:708d6e7cd209384f03d63ae4f535d42e', 'dropIndex indexName=spatial_idx_119, tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-215::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_12;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-215', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 215, '8:5e25e2a7ea9658f6f744dcd8b60b400a', 'dropIndex indexName=spatial_idx_12, tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-216::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_121;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-216', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 216, '8:ce89dcce46fbb17976006195bef39db8', 'dropIndex indexName=spatial_idx_121, tableName=xplan_bp_technikbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-217::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_123;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-217', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 217, '8:a7af44946b3c33825001043efac1f3d0', 'dropIndex indexName=spatial_idx_123, tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-218::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_125;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-218', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 218, '8:af6215d89f868b985f66d4ba23e79230', 'dropIndex indexName=spatial_idx_125, tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-219::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_127;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-219', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 219, '8:659fb3d066ba910b8d81fcb919f23c3d', 'dropIndex indexName=spatial_idx_127, tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-220::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_129;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-220', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 220, '8:1821f8ff64829f7b1194d2eaa1d74efa', 'dropIndex indexName=spatial_idx_129, tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-221::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_131;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-221', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 221, '8:442d4c1d537a8a21bffde9b78eebf101', 'dropIndex indexName=spatial_idx_131, tableName=xplan_bp_verentsorgungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-222::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_133;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-222', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 222, '8:b7c1525fd89d90ca68f2c44bb6ef114c', 'dropIndex indexName=spatial_idx_133, tableName=xplan_bp_verentsorgungsleitunglinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-223::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_136;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-223', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 223, '8:373c9a1aea79aed252df8aaf0be35798', 'dropIndex indexName=spatial_idx_136, tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-224::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_138;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-224', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 224, '8:cc5eeee400e144a0325c22d59473ab2a', 'dropIndex indexName=spatial_idx_138, tableName=xplan_bp_vorbhochwschutzflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-225::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_14;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-225', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 225, '8:2e3aae655c0bc0b440458da8db8e2d28', 'dropIndex indexName=spatial_idx_14, tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-226::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_140;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-226', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 226, '8:8f6567e43b86de7fb5407b9d7917e57a', 'dropIndex indexName=spatial_idx_140, tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-227::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_142;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-227', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 227, '8:ddd60aa94fbd987ea73c899edf25515a', 'dropIndex indexName=spatial_idx_142, tableName=xplan_bp_wasserrechtlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-228::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_144;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-228', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 228, '8:e486d24ea7fa097d9eb5b53d469585a9', 'dropIndex indexName=spatial_idx_144, tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-229::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_146;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-229', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 229, '8:60078fa56cb02ef695ba560e57333ecc', 'dropIndex indexName=spatial_idx_146, tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-230::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_149;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-230', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 230, '8:0e771b62128ac4b39579c465899ef123', 'dropIndex indexName=spatial_idx_149, tableName=xplan_fp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-231::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_153;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-231', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 231, '8:e6ec6184906d20f7c8292eb7c4c98bdd', 'dropIndex indexName=spatial_idx_153, tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-232::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_155;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-232', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 232, '8:594fc28dbd8d32e8cad0ed404ffab1ba', 'dropIndex indexName=spatial_idx_155, tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-233::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_157;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-233', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 233, '8:08827217b144e2de546b22d8b952c0c2', 'dropIndex indexName=spatial_idx_157, tableName=xplan_fp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-234::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_159;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-234', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 234, '8:1d475f5ef1de8493e6d3b4c46ae5300c', 'dropIndex indexName=spatial_idx_159, tableName=xplan_fp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-235::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_16;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-235', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 235, '8:3a2860fbfdb2193a464b1594a245afca', 'dropIndex indexName=spatial_idx_16, tableName=xplan_bp_bahnverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-236::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_161;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-236', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 236, '8:26789eed16ecf39e23bd1b039e19ae0e', 'dropIndex indexName=spatial_idx_161, tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-237::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_165;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-237', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 237, '8:2481adaacb0c8b7ceeaaeee37356f124', 'dropIndex indexName=spatial_idx_165, tableName=xplan_fp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-238::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_167;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-238', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 238, '8:33afaaf5ed8e266c3397677182143252', 'dropIndex indexName=spatial_idx_167, tableName=xplan_fp_denkmalschutzensemble', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-239::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_169;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-239', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 239, '8:db7d5b2f671dd76ac066c69b4a1451c0', 'dropIndex indexName=spatial_idx_169, tableName=xplan_fp_erhaltungssatzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-240::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_171;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-240', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 240, '8:0b4f0b25c7b730a1bc3f33d1f20f358e', 'dropIndex indexName=spatial_idx_171, tableName=xplan_fp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-241::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_173;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-241', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 241, '8:4a691445cc61cfe2df8b3d42b5253144', 'dropIndex indexName=spatial_idx_173, tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-242::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_175;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-242', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 242, '8:2014ed1c708308acc15be90ecb8651f4', 'dropIndex indexName=spatial_idx_175, tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-243::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_177;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-243', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 243, '8:8eb89d6b485a7b26b39c801c3dcbfa6a', 'dropIndex indexName=spatial_idx_177, tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-244::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_179;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-244', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 244, '8:0fa32098d93d6aca57a82662fee19eb4', 'dropIndex indexName=spatial_idx_179, tableName=xplan_fp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-245::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_18;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-245', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 245, '8:39fa568b6897c4567998d8d1c342310b', 'dropIndex indexName=spatial_idx_18, tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-246::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_181;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-246', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 246, '8:36cd0ad2e0e831890e3198fc9b8c7ef9', 'dropIndex indexName=spatial_idx_181, tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-247::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_183;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-247', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 247, '8:564d74329207255a404f95e4cdfb5b39', 'dropIndex indexName=spatial_idx_183, tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-248::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_185;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-248', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 248, '8:6f0c3a9321f43ccb7bc8d7656ad50893', 'dropIndex indexName=spatial_idx_185, tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-249::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_187;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-249', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 249, '8:df12a0d38159b9f8530f8340600ebea1', 'dropIndex indexName=spatial_idx_187, tableName=xplan_fp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-250::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_189;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-250', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 250, '8:44b4d4afaabb0a2dbcc6ba65c776cce6', 'dropIndex indexName=spatial_idx_189, tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-251::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_191;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-251', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 251, '8:cf1350e7e6a27eb0325545f1c32991c9', 'dropIndex indexName=spatial_idx_191, tableName=xplan_fp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-252::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_193;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-252', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 252, '8:1e715d7c5e07d6bead4c1e825e5130ab', 'dropIndex indexName=spatial_idx_193, tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-253::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_197;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-253', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 253, '8:dcb83fa9610e78c589374393c1b03ba8', 'dropIndex indexName=spatial_idx_197, tableName=xplan_fp_priviligiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-254::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_20;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-254', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 254, '8:e6076ad6f25b9513db9a80c7dd202342', 'dropIndex indexName=spatial_idx_20, tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-255::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_200;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-255', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 255, '8:941a2eeac6118583e056e12c63929ab0', 'dropIndex indexName=spatial_idx_200, tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-256::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_202;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-256', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 256, '8:7a7004909fdb4a91635a4aedd565a7b9', 'dropIndex indexName=spatial_idx_202, tableName=xplan_fp_schutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-257::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_204;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-257', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 257, '8:987d0cb3ab0f384d26a3dcdc774093ec', 'dropIndex indexName=spatial_idx_204, tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-258::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_206;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-258', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 258, '8:21f1c8196e3323eb0e7e7615fcf472d2', 'dropIndex indexName=spatial_idx_206, tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-259::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_208;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-259', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 259, '8:3e31744422305ff4ac2f49d4082ad465', 'dropIndex indexName=spatial_idx_208, tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-260::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_210;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-260', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 260, '8:3535355851d17871848401026692610f', 'dropIndex indexName=spatial_idx_210, tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-261::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_212;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-261', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 261, '8:7d183a69ca2a2aaf6a81ac920fe40277', 'dropIndex indexName=spatial_idx_212, tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-262::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_214;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-262', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 262, '8:230c96b8068c22fbbb70bc9645b177f1', 'dropIndex indexName=spatial_idx_214, tableName=xplan_fp_vorbhochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-263::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_216;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-263', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 263, '8:2e01c24988b07f916b4538bac63298ef', 'dropIndex indexName=spatial_idx_216, tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-264::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_218;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-264', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 264, '8:05b6530cb074344ba26621d37b8ec3b7', 'dropIndex indexName=spatial_idx_218, tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-265::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_22;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-265', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 265, '8:551d1a1833162fa9dddf8370c3f23068', 'dropIndex indexName=spatial_idx_22, tableName=xplan_bp_baugebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-266::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_220;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-266', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 266, '8:d37d8443b66c61f7b483cfecaf9d59fc', 'dropIndex indexName=spatial_idx_220, tableName=xplan_fp_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-267::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_222;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-267', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 267, '8:fd9640006be6cdae34e51228a8d0a81b', 'dropIndex indexName=spatial_idx_222, tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-268::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_225;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-268', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 268, '8:e1381d33eea0ceb291d1aefea5bd5158', 'dropIndex indexName=spatial_idx_225, tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-269::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_227;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-269', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 269, '8:8bde758b34a202610be49792f7a3ac14', 'dropIndex indexName=spatial_idx_227, tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-270::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_230;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-270', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 270, '8:38ec859058bafd9c7cfde257da19cf63', 'dropIndex indexName=spatial_idx_230, tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-271::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_233;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-271', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 271, '8:b18259776582c7b4a4b98edbd1967746', 'dropIndex indexName=spatial_idx_233, tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-272::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_235;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-272', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 272, '8:631fc6e134c73af790aaab836e75f717', 'dropIndex indexName=spatial_idx_235, tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-273::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_237;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-273', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 273, '8:0a0ddf9f679b271e7f08114d90eb7060', 'dropIndex indexName=spatial_idx_237, tableName=xplan_lp_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-274::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_239;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-274', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 274, '8:69e02062ea3ff9cacfd07a399b8a949f', 'dropIndex indexName=spatial_idx_239, tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-275::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_24;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-275', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 275, '8:6dbe75e6b634ac0b5cb68a2878db8d55', 'dropIndex indexName=spatial_idx_24, tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-276::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_241;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-276', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 276, '8:cd94611ef9df2c2a9567454e8b01ad80', 'dropIndex indexName=spatial_idx_241, tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-277::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_243;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-277', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 277, '8:9382eabfc73d1ce13e11e0dd93582a04', 'dropIndex indexName=spatial_idx_243, tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-278::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_246;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-278', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 278, '8:094896f03d7efb310944d203ed611f82', 'dropIndex indexName=spatial_idx_246, tableName=xplan_lp_nrw_biotopschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-279::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_248;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-279', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 279, '8:618383564fbd610fcbd226b36815becf', 'dropIndex indexName=spatial_idx_248, tableName=xplan_lp_nrw_brachflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-280::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_250;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-280', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 280, '8:c354c3ca8cfed32f0db96208ea369319', 'dropIndex indexName=spatial_idx_250, tableName=xplan_lp_nrw_elementekulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-281::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_252;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-281', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 281, '8:8a51b1250dfcf45a905b2d1fec43343d', 'dropIndex indexName=spatial_idx_252, tableName=xplan_lp_nrw_forstlichefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-282::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_254;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-282', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 282, '8:b2be1354ed45860353f15e1ed7262443', 'dropIndex indexName=spatial_idx_254, tableName=xplan_lp_nrw_herrichtunggrundstueck', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-283::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_256;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-283', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 283, '8:eef00136e722e25e5615b9830c20e28d', 'dropIndex indexName=spatial_idx_256, tableName=xplan_lp_nrw_pflegeanpflanzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-284::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_258;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-284', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 284, '8:8ea38d7ee89930ea4224a2f4ab45044a', 'dropIndex indexName=spatial_idx_258, tableName=xplan_lp_nrw_pflegelandschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-285::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_26;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-285', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 285, '8:168f523b6ac2f15d8a9bdf792f00fae0', 'dropIndex indexName=spatial_idx_26, tableName=xplan_bp_bauschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-286::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_260;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-286', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 286, '8:18102e3a3de3edd97f3192b34f928af4', 'dropIndex indexName=spatial_idx_260, tableName=xplan_lp_nrw_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-287::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_262;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-287', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 287, '8:64cb129e4587120e4fdedf7faed080bc', 'dropIndex indexName=spatial_idx_262, tableName=xplan_lp_nrw_sonstigemassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-288::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_264;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-288', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 288, '8:938d94cd02104f8661be807add7dbbde', 'dropIndex indexName=spatial_idx_264, tableName=xplan_lp_nrw_strukturenelementebesiedelterbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-289::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_266;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-289', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 289, '8:06bdc18588d11ddbc26482a1cfd137e3', 'dropIndex indexName=spatial_idx_266, tableName=xplan_lp_nrw_temporaererlandschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-290::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_268;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-290', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 290, '8:df992761360549bbee10e83112f07fbe', 'dropIndex indexName=spatial_idx_268, tableName=xplan_lp_nrw_verpflichtungwrrl', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-291::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_270;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-291', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 291, '8:1bdf47b0f53d3f2a987ffaa3510faa7a', 'dropIndex indexName=spatial_idx_270, tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-292::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_272;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-292', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 292, '8:f6aa80a6db4477b23a0a1bcc74ac2f12', 'dropIndex indexName=spatial_idx_272, tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-293::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_275;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-293', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 293, '8:78675c4c6e33ef35c533621a845c776e', 'dropIndex indexName=spatial_idx_275, tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-294::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_278;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-294', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 294, '8:99f415851e87f34b7e81dfc7f1a97f94', 'dropIndex indexName=spatial_idx_278, tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-295::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_280;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-295', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 295, '8:6d4d7cc5fe13e55e439b50b21a4e1eaa', 'dropIndex indexName=spatial_idx_280, tableName=xplan_lp_schutzobjektbundesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-296::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_282;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-296', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 296, '8:eeb0a5850aef1beccacd66b7e87019dd', 'dropIndex indexName=spatial_idx_282, tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-297::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_284;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-297', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 297, '8:6d8f0515afc3f6b52522ac0b25b1c5f5', 'dropIndex indexName=spatial_idx_284, tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-298::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_287;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-298', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 298, '8:2d6864add15c56f189765dfa896f0bdb', 'dropIndex indexName=spatial_idx_287, tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-299::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_289;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-299', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 299, '8:b3a42d794a7aa76e95068dd6ca78aff5', 'dropIndex indexName=spatial_idx_289, tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-300::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_29;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-300', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 300, '8:087b1bb5b5615631fac2726b78fafe4b', 'dropIndex indexName=spatial_idx_29, tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-301::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_291;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-301', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 301, '8:6c6a7672d94d08b8ca921172fa69c251', 'dropIndex indexName=spatial_idx_291, tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-302::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_293;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-302', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 302, '8:d73865ce2ec0f01b8a02c6931acf1b21', 'dropIndex indexName=spatial_idx_293, tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-303::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_296;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-303', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 303, '8:bb4b2d1d401315abc197a772bbf19128', 'dropIndex indexName=spatial_idx_296, tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-304::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_298;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-304', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 304, '8:fd7f3e4ab355ab72478177ccd2b4d7d8', 'dropIndex indexName=spatial_idx_298, tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-305::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_3;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-305', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 305, '8:a0c34181693e309100ea754f50a99c0e', 'dropIndex indexName=spatial_idx_3, tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-306::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_301;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-306', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 306, '8:88fa57d8aa80b0bcd1a200d067132ba1', 'dropIndex indexName=spatial_idx_301, tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-307::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_303;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-307', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 307, '8:14d44985afc7ca3dc3b855ca2912cb64', 'dropIndex indexName=spatial_idx_303, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-308::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_305;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-308', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 308, '8:9fd8a7fb6c2782a3ea0781a6b1db3cf4', 'dropIndex indexName=spatial_idx_305, tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-309::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_307;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-309', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 309, '8:3177d6d829f683f35229b7b174edab6b', 'dropIndex indexName=spatial_idx_307, tableName=xplan_rp_entwicklungsschwerpunkte', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-310::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_309;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-310', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 310, '8:336c048a86a138738c21a0e601913f5d', 'dropIndex indexName=spatial_idx_309, tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-311::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_31;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-311', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 311, '8:18d73daf2e8ecbd2c9af32edca7ce40e', 'dropIndex indexName=spatial_idx_31, tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-312::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_311;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-312', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 312, '8:fbbe3d02c2ef309bc452727810acabc6', 'dropIndex indexName=spatial_idx_311, tableName=xplan_rp_freizeiterholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-313::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_313;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-313', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 313, '8:31f4e68795ed297ec417e9f9c8b0baed', 'dropIndex indexName=spatial_idx_313, tableName=xplan_rp_gemeindefunktionsiedlungsentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-314::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_315;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-314', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 314, '8:9536d6bb20ca3ffd7e018653ae07212a', 'dropIndex indexName=spatial_idx_315, tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-315::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_318;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-315', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 315, '8:dcf653c1eb4d1c265dfabaf596bdbdf3', 'dropIndex indexName=spatial_idx_318, tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-316::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_320;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-316', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 316, '8:b0f0f9aaf115495d086c1009fb9166f8', 'dropIndex indexName=spatial_idx_320, tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-317::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_322;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-317', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 317, '8:4b1909792204730bd52051d92e4b9e3f', 'dropIndex indexName=spatial_idx_322, tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-318::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_324;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-318', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 318, '8:2c53a8ced62e90da8563e71a9857cdc6', 'dropIndex indexName=spatial_idx_324, tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-319::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_327;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-319', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 319, '8:933e75044d65167dd6702287a3f3f636', 'dropIndex indexName=spatial_idx_327, tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-320::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_329;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-320', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 320, '8:606eea080d68b1c3b0a2850b213980e5', 'dropIndex indexName=spatial_idx_329, tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-321::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_33;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-321', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 321, '8:cd424b8a2c7ebc0233dab3fbfbcf7568', 'dropIndex indexName=spatial_idx_33, tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-322::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_331;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-322', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 322, '8:df9efe2fc38c7d5d710e4f20c68ff05f', 'dropIndex indexName=spatial_idx_331, tableName=xplan_rp_nrw_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-323::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_333;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-323', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 323, '8:eccf0f563b5f70849d4dd31ba9551ae0', 'dropIndex indexName=spatial_idx_333, tableName=xplan_rp_nrw_aufschuettungablagerung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-324::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_335;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-324', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 324, '8:fd2744c162e4d2a00ff775a70d803623', 'dropIndex indexName=spatial_idx_335, tableName=xplan_rp_nrw_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-325::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_337;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-325', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 325, '8:f7c53c00ce83dd3fbba11bf429a45e73', 'dropIndex indexName=spatial_idx_337, tableName=xplan_rp_nrw_freiraumagrarbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-326::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_339;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-326', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 326, '8:639fd9b5ccf361156471f18ff94eb82e', 'dropIndex indexName=spatial_idx_339, tableName=xplan_rp_nrw_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-327::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_341;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-327', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 327, '8:ebde45be88834222b7d3711ec9e5726e', 'dropIndex indexName=spatial_idx_341, tableName=xplan_rp_nrw_grundwassergewaesserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-328::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_343;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-328', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 328, '8:d93d3a8aaa48a990d879c8635b9d9721', 'dropIndex indexName=spatial_idx_343, tableName=xplan_rp_nrw_laermschutzzone', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-329::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_345;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-329', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 329, '8:1bc624eedf2fd4f067506c3fdcd22347', 'dropIndex indexName=spatial_idx_345, tableName=xplan_rp_nrw_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-330::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_347;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-330', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 330, '8:09addbed92efd2bb12605dc6be3f0221', 'dropIndex indexName=spatial_idx_347, tableName=xplan_rp_nrw_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-331::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_349;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-331', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 331, '8:30d189d7894e7c6cd5dd365070f2487b', 'dropIndex indexName=spatial_idx_349, tableName=xplan_rp_nrw_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-332::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_351;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-332', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 332, '8:8b28eb46dc72f77d317116079af29f55', 'dropIndex indexName=spatial_idx_351, tableName=xplan_rp_nrw_oberflaechengewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-333::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_353;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-333', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 333, '8:3f2d3ff752249bf14b1b4aebcfe5d397', 'dropIndex indexName=spatial_idx_353, tableName=xplan_rp_nrw_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-334::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_355;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-334', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 334, '8:839e4ef1689e213e3960b2217b0ef551', 'dropIndex indexName=spatial_idx_355, tableName=xplan_rp_nrw_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-335::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_357;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-335', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 335, '8:10a3d25668b1138c80cbf4085e9298d1', 'dropIndex indexName=spatial_idx_357, tableName=xplan_rp_nrw_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-336::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_359;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-336', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 336, '8:8024bc30c8b715bc6e5e2ed678dbe4d3', 'dropIndex indexName=spatial_idx_359, tableName=xplan_rp_nrw_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-337::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_36;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-337', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 337, '8:77aa67eb0872c792b7c1978b6bd55865', 'dropIndex indexName=spatial_idx_36, tableName=xplan_bp_denkmalschutzeinzelanlagepunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-338::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_361;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-338', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 338, '8:26e92f16d79f5db21fe698d2928ef066', 'dropIndex indexName=spatial_idx_361, tableName=xplan_rp_nrw_sonstigezweckbindung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-339::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_363;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-339', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 339, '8:85e3213012ed8825cea73d5f7f010506', 'dropIndex indexName=spatial_idx_363, tableName=xplan_rp_nrw_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-340::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_365;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-340', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 340, '8:b3f35ff0bd8e32d55f54ae5174b7fc05', 'dropIndex indexName=spatial_idx_365, tableName=xplan_rp_nrw_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-341::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_367;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-341', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 341, '8:233fc7f0c9af90d9bcbc0b34dfcfab19', 'dropIndex indexName=spatial_idx_367, tableName=xplan_rp_nrw_ueberschwemmungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-342::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_369;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-342', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 342, '8:c20072107c1780924f2f43d44838ba55', 'dropIndex indexName=spatial_idx_369, tableName=xplan_rp_nrw_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-343::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_371;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-343', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 343, '8:19c9bdad488de1619d7dfd8398122689', 'dropIndex indexName=spatial_idx_371, tableName=xplan_rp_nrw_zeitlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-344::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_373;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-344', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 344, '8:3e0813730a5c2f7b59afe984de3a8da7', 'dropIndex indexName=spatial_idx_373, tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-345::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_375;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-345', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 345, '8:fa084f52da2bae900a9affa042c60b28', 'dropIndex indexName=spatial_idx_375, tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-346::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_379;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-346', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 346, '8:d97790b33eee8274850590d4b15e7f5c', 'dropIndex indexName=spatial_idx_379, tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-347::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_38;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-347', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 347, '8:71ea16690c97b0975383baa2e4a5367d', 'dropIndex indexName=spatial_idx_38, tableName=xplan_bp_denkmalschutzensembleflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-348::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_381;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-348', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 348, '8:ea666dd5a199a9e068c00d27e859f8d8', 'dropIndex indexName=spatial_idx_381, tableName=xplan_rp_rohstoffsicherung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-349::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_383;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-349', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 349, '8:825faefef9e04e5b98b370cbf69838f0', 'dropIndex indexName=spatial_idx_383, tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-350::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_385;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-350', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 350, '8:123e466f44782e42ea5843a3e06aac4c', 'dropIndex indexName=spatial_idx_385, tableName=xplan_rp_sonstigesiedlungsstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-351::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_387;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-351', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 351, '8:32928d8a176297717246c8e8c740c5cf', 'dropIndex indexName=spatial_idx_387, tableName=xplan_rp_sonstigerfreiraumstruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-352::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_389;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-352', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 352, '8:8f721c93713f99e241512a31db9fb259', 'dropIndex indexName=spatial_idx_389, tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-353::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_392;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-353', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 353, '8:24fff2aca493cb74dac6afee5821c7e3', 'dropIndex indexName=spatial_idx_392, tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-354::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_394;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-354', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 354, '8:1ed7b9f73a84c29ad5d8e417c49321b8', 'dropIndex indexName=spatial_idx_394, tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-355::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_396;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-355', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 355, '8:d1d9bd2a28fef203bcc8cb47f0cd5927', 'dropIndex indexName=spatial_idx_396, tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-356::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_398;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-356', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 356, '8:b62458b44ece15e652cadfd2e42dc364', 'dropIndex indexName=spatial_idx_398, tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-357::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_40;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-357', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 357, '8:0dcf5f359b700ea0b3bca510b112182e', 'dropIndex indexName=spatial_idx_40, tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-358::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_400;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-358', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 358, '8:804327ef6ed51a94c16fdfb5949361e5', 'dropIndex indexName=spatial_idx_400, tableName=xplan_rp_windenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-359::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_402;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-359', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 359, '8:0adc3119e990a33fc2594f8d06cf9584', 'dropIndex indexName=spatial_idx_402, tableName=xplan_rp_windenergienutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-360::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_404;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-360', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 360, '8:c683e3e47a7f8e1172eee69fb3879534', 'dropIndex indexName=spatial_idx_404, tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-361::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_42;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-361', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 361, '8:d76de8baa07bfcc41b036bc4daa02ba4', 'dropIndex indexName=spatial_idx_42, tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-362::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_424;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-362', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 362, '8:539bbf6d253fea330dca89269973cb13', 'dropIndex indexName=spatial_idx_424, tableName=xplan_xp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-363::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_431;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-363', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 363, '8:f7591848064247ee39fb6049c9078dce', 'dropIndex indexName=spatial_idx_431, tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-364::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_433;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-364', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 364, '8:d7053dc8715a7d229a96c1d7505e1cca', 'dropIndex indexName=spatial_idx_433, tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-365::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_435;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-365', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 365, '8:264504d7f5a6306a6697dc827b193e3b', 'dropIndex indexName=spatial_idx_435, tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-366::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_437;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-366', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 366, '8:705ea600ffc4109ee7247141d67d74d9', 'dropIndex indexName=spatial_idx_437, tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-367::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_439;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-367', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 367, '8:605b350ffe577ede1ea886a649f2661f', 'dropIndex indexName=spatial_idx_439, tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-368::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_441;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-368', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 368, '8:12d29267b9057dd82655f91a2b97d57e', 'dropIndex indexName=spatial_idx_441, tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-369::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_443;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-369', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 369, '8:9c674c7ee34a046e8dc5e671cd95e987', 'dropIndex indexName=spatial_idx_443, tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-370::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_445;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-370', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 370, '8:0b1a20a6fa3389319be576346cc7fd26', 'dropIndex indexName=spatial_idx_445, tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-371::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_447;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-371', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 371, '8:4d4c20cfe200200a40fdf9706c9b060c', 'dropIndex indexName=spatial_idx_447, tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-372::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_449;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-372', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 372, '8:38bc7571edf7be549dce19f458c8668f', 'dropIndex indexName=spatial_idx_449, tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-373::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_45;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-373', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 373, '8:6cf7fb4fd9de062a0c346a5c7388a9d7', 'dropIndex indexName=spatial_idx_45, tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-374::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_451;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-374', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 374, '8:825cabb273f5834b104ca7f918df3494', 'dropIndex indexName=spatial_idx_451, tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-375::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_453;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-375', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 375, '8:d280d68a3a51f28edfa9273ba63e3190', 'dropIndex indexName=spatial_idx_453, tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-376::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_455;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-376', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 376, '8:31db0b92b1e3d118168f0c5456094400', 'dropIndex indexName=spatial_idx_455, tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-377::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_457;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-377', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 377, '8:8cbca291c1ae4c580893743c1e9b4bd7', 'dropIndex indexName=spatial_idx_457, tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-378::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_459;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-378', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 378, '8:d038ec6e15890fb0b04614fc5f9b8059', 'dropIndex indexName=spatial_idx_459, tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-379::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_461;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-379', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 379, '8:45bd21a712624663f31bd23d74c1b098', 'dropIndex indexName=spatial_idx_461, tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-380::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_463;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-380', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 380, '8:b40b1d97ddc49f50d76bc7c95a83a972', 'dropIndex indexName=spatial_idx_463, tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-381::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_47;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-381', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 381, '8:61ddd6c880aa65493adee9c2edc13e7d', 'dropIndex indexName=spatial_idx_47, tableName=xplan_bp_erneuerbareenergieflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-382::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_49;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-382', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 382, '8:52a6c272835fd6e621fbe65527e6095e', 'dropIndex indexName=spatial_idx_49, tableName=xplan_bp_fachgesetz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-383::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_52;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-383', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 383, '8:99c9c3f05b3c0239b4ba941e0453b8b3', 'dropIndex indexName=spatial_idx_52, tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-384::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_54;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-384', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 384, '8:da9f5d2c3d6a065e1952c452701c7204', 'dropIndex indexName=spatial_idx_54, tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-385::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_56;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-385', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 385, '8:665e6db6087b6c6d3f149b44abca3730', 'dropIndex indexName=spatial_idx_56, tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-386::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_58;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-386', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 386, '8:1946fffb0b9881eae9d8101f3067288f', 'dropIndex indexName=spatial_idx_58, tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-387::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_6;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-387', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 387, '8:cbc45b5aaba750d6f60e521c8329c4bb', 'dropIndex indexName=spatial_idx_6, tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-388::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_60;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-388', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 388, '8:f7ac375def0c5d9ce97808b4fa44b26a', 'dropIndex indexName=spatial_idx_60, tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-389::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_62;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-389', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 389, '8:8f2fcf22ceb20711dd2c9ef96f93cad0', 'dropIndex indexName=spatial_idx_62, tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-390::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_65;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-390', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 390, '8:b8dcf978b08c6fa0ef717d8e54ce93cb', 'dropIndex indexName=spatial_idx_65, tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-391::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_67;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-391', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 391, '8:7cdcb6486b69eda1321fbeb71e75d289', 'dropIndex indexName=spatial_idx_67, tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-392::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_69;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-392', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 392, '8:469543a8e30f7caa5c3e10e440c4914a', 'dropIndex indexName=spatial_idx_69, tableName=xplan_bp_grabungsschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-393::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_71;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-393', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 393, '8:e25e67db3d0574f9e1d4b463a5459864', 'dropIndex indexName=spatial_idx_71, tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-394::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_74;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-394', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 394, '8:9890bc4decf9986487d3c1586b277c8d', 'dropIndex indexName=spatial_idx_74, tableName=xplan_bp_hoehenpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-395::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_76;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-395', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 395, '8:d334b22ecda199c047da65d4e0f60f01', 'dropIndex indexName=spatial_idx_76, tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-396::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_78;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-396', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 396, '8:86860e4717e4b5bcdcc681bf701ce395', 'dropIndex indexName=spatial_idx_78, tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-397::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_8;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-397', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 397, '8:b6e7294d6c9ca4282be46028583655e6', 'dropIndex indexName=spatial_idx_8, tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-398::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_81;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-398', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 398, '8:33eb09fbce55be03b17a5b1e8f58d99e', 'dropIndex indexName=spatial_idx_81, tableName=xplan_bp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-399::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_84;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-399', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 399, '8:7ea2796591674472b7c803b2e4a9daf7', 'dropIndex indexName=spatial_idx_84, tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-400::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_86;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-400', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 400, '8:0dbb2dc5b2aab7985b7fc29fcffa28ce', 'dropIndex indexName=spatial_idx_86, tableName=xplan_bp_landwirtschaftslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-401::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_88;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-401', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 401, '8:380a428559f200e4d886bb2ad06eff4c', 'dropIndex indexName=spatial_idx_88, tableName=xplan_bp_luftreinhalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-402::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_90;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-402', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 402, '8:542d74eaedf080ea1347d089f71a79da', 'dropIndex indexName=spatial_idx_90, tableName=xplan_bp_luftverkehrflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-403::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_92;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-403', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 403, '8:8907a7230c8d2240153dd5384dab26bd', 'dropIndex indexName=spatial_idx_92, tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-404::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_94;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-404', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 404, '8:139cec29e571b07732e4504889c5d2db', 'dropIndex indexName=spatial_idx_94, tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-405::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_96;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-405', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 405, '8:d19a893a7ab5598054ba1e805ea2b5de', 'dropIndex indexName=spatial_idx_96, tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Changeset changelog_xplansynarchive.xml::1572361434180-406::lyn (generated)
DROP INDEX xplansynarchive.spatial_idx_98;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1572361434180-406', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 406, '8:48883f841ff9028d269c72f249a83f2c', 'dropIndex indexName=spatial_idx_98, tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '2361454137');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

