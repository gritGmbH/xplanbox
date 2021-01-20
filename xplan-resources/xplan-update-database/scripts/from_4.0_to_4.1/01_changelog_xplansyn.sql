-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansyn.xml
-- Ran at: 20.01.21 15:16
-- Against: postgres@jdbc:postgresql://localhost:5437/xplanbox401
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansyn.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansyn.databasechangeloglock;

INSERT INTO xplansyn.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansyn.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2021-01-20 15:16:30.857' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansyn.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansyn.xml::1611152004192-1::lyn (generated)
CREATE TABLE xplansyn.xplan_bp_flaecheohnefestsetzung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_rechtsverbindlich TEXT, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_flaecheohnefestsetzung_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-1', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 1, '8:8989f6f195a570f529007b46b3081251', 'createTable tableName=xplan_bp_flaecheohnefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-2::lyn (generated)
CREATE TABLE xplansyn.xplan_bp_zentralerversorgungsbereich (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_rechtsverbindlich TEXT, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_zentralerversorgungsbereich_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-2', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 2, '8:fae8d9e32acef906851c8a275dd206ef', 'createTable tableName=xplan_bp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-3::lyn (generated)
CREATE TABLE xplansyn.xplan_fp_darstellungnachlandesrecht (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_rechtsverbindlich TEXT, xplan_informell TEXT, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertnachrichtlichzubereich TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_spezifischepraegung TEXT, xplan_spezifischepraegungcode TEXT, xplan_gehoertzufp_bereich TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_wirdausgeglichendurchspe TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_vongenehmigungausgenommen TEXT, xplan_detailzweckbestimmung TEXT, xplan_detailzweckbestimmungcode TEXT, xplan_kurzbeschreibung TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_fp_darstellungnachlandesrecht_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-3', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 3, '8:a628c47fd686b35cb027a92d608dc4b6', 'createTable tableName=xplan_fp_darstellungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-4::lyn (generated)
CREATE TABLE xplansyn.xplan_fp_flaecheohnedarstellung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_rechtsverbindlich TEXT, xplan_informell TEXT, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertnachrichtlichzubereich TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_spezifischepraegung TEXT, xplan_spezifischepraegungcode TEXT, xplan_gehoertzufp_bereich TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_wirdausgeglichendurchspe TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_vongenehmigungausgenommen TEXT, xplan_detailzweckbestimmung TEXT, xplan_detailzweckbestimmungcode TEXT, xplan_kurzbeschreibung TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_fp_flaecheohnedarstellung_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-4', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 4, '8:af1f506447fda3f0eeb2577e60222b95', 'createTable tableName=xplan_fp_flaecheohnedarstellung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-5::lyn (generated)
CREATE TABLE xplansyn.xplan_so_gelaendemorphologie (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_sonstrechtscharakter TEXT, xplan_sonstrechtscharaktercode TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_artderfestlegung TEXT, xplan_artderfestlegungcode TEXT, xplan_detailartderfestlegung TEXT, xplan_detailartderfestlegungcode TEXT, xplan_name TEXT, xplan_nummer TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_so_gelaendemorphologie_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-5', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 5, '8:399c84a3aa01375dffbd19f63a3ccb53', 'createTable tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-6::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-6', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 6, '8:492ca9cba13e548f24ab39c3d1886c1e', 'addColumn tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-7::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_art TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-7', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 7, '8:3b497385320c933fd8a2eff03cad8076', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-8::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_zentralerversorgungsbereich ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-8', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 8, '8:92e7fbbf5dd50957cc7c2845b1d613c1', 'addColumn tableName=xplan_fp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-9::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_artcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-9', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 9, '8:f098acbbe7c60d3cadb6e882f0dfecc6', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-10::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_massnahme TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-10', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 10, '8:d00ee23fc0f57e1daf800f29259f66d2', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-11::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-11', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 11, '8:142d573eee239ece47df6ec7a414788f', 'addColumn tableName=xplan_fp_abgrabung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-12::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaft ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-12', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 12, '8:8044a67b7a37d79073eb754067c0a931', 'addColumn tableName=xplan_fp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-13::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_knotenpunkt TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-13', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 13, '8:bc355c7290e850385052102f0fbdeee2', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-14::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_massnahmecode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-14', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 14, '8:e848dec4cacc821fc4f1c5c8c5028147', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-15::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-15', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 15, '8:5e53c61b18172e246c23466c16960428', 'addColumn tableName=xplan_fp_aufschuettung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-16::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetze ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-16', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 16, '8:56e1ee2be28a098c920f7aa99a1c8653', 'addColumn tableName=xplan_fp_bodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-17::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-17', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 17, '8:ae1b75048e7a2869df75a7387f5ab712', 'addColumn tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-18::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_detailmassnahme TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-18', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 18, '8:72f6ffcec03d2cb15bcc5132842068b5', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-19::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_knotenpunktcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-19', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 19, '8:6afc2265896d1d65149bcfefd7f480c4', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-20::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_detailmassnahmecode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-20', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 20, '8:46d64c58a6a309921782a692179ef086', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-21::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_geschwindigkeit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-21', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 21, '8:10a6fcf346116251661f0a501da5b690', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-22::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_geschwindigkeituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-22', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 22, '8:55f3531a0d4d485f966c6ffb8eac92c8', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-23::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-23', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 23, '8:625a43e4e320e9897bd6cbd41b81cdae', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-24::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_schenkellaenge numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-24', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 24, '8:9cf3f28798613f5d1a108bd08a0e68a2', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-25::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_sichtflaeche ADD xplan_schenkellaengeuom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-25', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 25, '8:cd808d68ec31ae98951d511210085bbe', 'addColumn tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-26::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-26', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 26, '8:26996144d9490107ac2bdca00501e462', 'addColumn tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-27::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-27', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 27, '8:805eb6c088f30b7a0c13dcd9239443e6', 'addColumn tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-28::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_veraenderungssperrebeschlussdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-28', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 28, '8:3eff8a440ec1facf89c03ec83ad12831', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-29::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_privilegiertesvorhaben ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-29', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 29, '8:26e1436a020fbdcebafc58d685d0fa9b', 'addColumn tableName=xplan_fp_privilegiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-30::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-30', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 30, '8:d73f01e7d7f339472a77ccd9753312c4', 'addColumn tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-31::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_veraenderungssperrestartdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-31', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 31, '8:68ccfd3c6ea56f052d9f15e531bf510a', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-32::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-32', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 32, '8:122f4caa99b23446a7be42d5d3d374f8', 'addColumn tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-33::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-33', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 33, '8:99e8348bcf5f955d3ca63ef420899c3f', 'addColumn tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-34::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-34', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 34, '8:63ceae32c75f3b6642b3cf6dddc86cac', 'addColumn tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-35::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-35', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 35, '8:03e5baef609bb8ef6a4d93b93d2280c2', 'addColumn tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-36::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-36', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 36, '8:834c386fc3b3974c85cdda978335bb80', 'addColumn tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-37::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-37', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 37, '8:38894badb884cdc28b091eefcdc7efb9', 'addColumn tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-38::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-38', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 38, '8:b0554154487b0abcf9cb2798a3531406', 'addColumn tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-39::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-39', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 39, '8:2cfc8500a2a3c0fbc53815e5c4e31b4b', 'addColumn tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-40::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-40', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 40, '8:9d99c052933a6151db3f463da1278661', 'addColumn tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-41::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-41', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 41, '8:79e93081b217fa9cc2122a8452b43805', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-42::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_detailliertesondernutzung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-42', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 42, '8:b24de987f347444ead8dc75ea74dbcea', 'addColumn tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-43::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_detailliertesondernutzungcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-43', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 43, '8:a02d5b51861a6638788e54fc9afdcab3', 'addColumn tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-44::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-44', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 44, '8:4b1d9a1e9c65c13dd72cde0c2f540788', 'addColumn tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-45::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-45', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 45, '8:e297c5e071b15386e54d9df222557a4c', 'addColumn tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-46::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-46', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 46, '8:0c725842bd8cc714727b5fd67298e5df', 'addColumn tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-47::lyn (generated)
ALTER TABLE xplansyn.xplan_fp_gruen ADD xplan_vongenehmigungausgenommen TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-47', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 47, '8:b293ba066b73b781f1243ffa8c9c7ea9', 'addColumn tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-48::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_veraenderungssperrebeschlussdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-48', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 48, '8:af731c9385f3f029a0bf9fe0a0bbff8b', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-49::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_veraenderungssperreenddatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-49', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 49, '8:d338274605ba4506df8b96aa879096f3', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-50::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_verlaengerungveraenderungssperre TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-50', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 50, '8:11393b6cf99f9b4a05ba13148b2a47d0', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-51::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_verlaengerungveraenderungssperrecode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-51', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 51, '8:b46b3c03bfcc44e210b0916c12df4bdc', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-52::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-52', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 52, '8:618c5022075087213fc392cfcd010e69', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-53::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-53', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 53, '8:9026b96f00d63fab67e17de109ebcea6', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-54::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-54', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 54, '8:618726681c2fc81443bb0a63c3dd29ae', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-55::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-55', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 55, '8:ceb8a07fb655b8e4cfb894e3448151d2', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-56::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-56', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 56, '8:8a4b9efb0187626fd16d2d6f2068e66c', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-57::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-57', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 57, '8:ce13e9e18d9bcd5b6a8be2aaceb4dffb', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-58::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-58', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 58, '8:ebe552b70c394c5254f64afd0ec72ecd', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-59::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-59', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 59, '8:6423aa28d8ae5971fe6f277796dd53ec', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-60::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-60', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 60, '8:3ec1f278db0d9e89d4bf12b389ad995d', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-61::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-61', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 61, '8:96349936629e3b73fe2c12e3bb48dcb8', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-62::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-62', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 62, '8:6d939f6fce70d7b6b6150006cba1447b', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-63::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-63', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 63, '8:bc0ccfd2b26988f0f6715a550060cbe9', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-64::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-64', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 64, '8:c2f606b11c8a8f146945dc5f2abe9038', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-65::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-65', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 65, '8:7e830c77f82f617996f7ecda8f387f58', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-66::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-66', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 66, '8:465ae13f6820dbf0ac694100bc3082b6', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-67::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-67', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 67, '8:d3f1d1965df27eb48028aad2be9cd407', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-68::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_mingrwohneinheit numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-68', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 68, '8:af27119998fb4058bba73bec20744917', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-69::lyn (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_mingrwohneinheituom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-69', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 69, '8:4f47487c37e083f8fd4f12b80733ed34', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-70::lyn (generated)
CREATE INDEX spatial_idx_318 ON xplansyn.xplan_bp_flaecheohnefestsetzung USING GIST (xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-70', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 70, '8:89656ce67ea2be0f705d925f6f5a7f5c', 'createIndex indexName=spatial_idx_318, tableName=xplan_bp_flaecheohnefestsetzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-71::lyn (generated)
CREATE INDEX spatial_idx_319 ON xplansyn.xplan_bp_zentralerversorgungsbereich USING GIST (xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-71', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 71, '8:38ec7639de12c2e198a7f79dfb10d2db', 'createIndex indexName=spatial_idx_319, tableName=xplan_bp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-72::lyn (generated)
CREATE INDEX spatial_idx_320 ON xplansyn.xplan_fp_darstellungnachlandesrecht USING GIST (xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-72', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 72, '8:b9921894e5da074aaebfeeea80d3c725', 'createIndex indexName=spatial_idx_320, tableName=xplan_fp_darstellungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-73::lyn (generated)
CREATE INDEX spatial_idx_322 ON xplansyn.xplan_so_gelaendemorphologie USING GIST (xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-73', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 73, '8:8303ccea83958d36ff840f4fff81ed80', 'createIndex indexName=spatial_idx_322, tableName=xplan_so_gelaendemorphologie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Changeset changelog_xplansyn.xml::1611152004192-74::lyn (generated)
DROP INDEX xplansyn.spatial_idx_319;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1611152004192-74', 'lyn (generated)', 'changelog_xplansyn.xml', NOW(), 74, '8:9118fe339cf8417d99355a5f6683d2e6', 'dropIndex indexName=spatial_idx_319, tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1152191336');

-- Release Database Lock
UPDATE xplansyn.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

