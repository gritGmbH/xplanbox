-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansyn.xml
-- Ran at: 03.12.18 11:41
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk29
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Lock Database
UPDATE xplansyn.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk.fritz.box (192.168.178.152)', LOCKGRANTED = '2018-12-03 11:41:37.218' WHERE ID = 1 AND LOCKED = FALSE;

-- Adding missing databasechangelog.contexts column
-- Adding missing databasechangelog.labels column
-- Adding missing databasechangelog.deployment_id column
-- DatabaseChangeLog checksums are an incompatible version.  Setting them to null so they will be updated on next database update
ALTER TABLE xplansyn.databasechangelog ADD CONTEXTS VARCHAR(255);

ALTER TABLE xplansyn.databasechangelog ADD LABELS VARCHAR(255);

ALTER TABLE xplansyn.databasechangelog ADD DEPLOYMENT_ID VARCHAR(10);

UPDATE xplansyn.databasechangelog SET MD5SUM = NULL;

-- Changeset changelog_xplansyn.xml::1543833329759-1::lgvxplanisk (generated)
CREATE TABLE xplansyn.xplan_bp_nichtueberbaubaregrundstuecksflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_reftextinhalt TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_nichtueberbaubaregrundstuecksflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-1', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 929, '8:9b830b966d1f32b363b72171ae37a48b', 'createTable tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-2::lgvxplanisk (generated)
CREATE TABLE xplansyn.xplan_fp_landwirtschaft (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_spezifischepraegung TEXT, xplan_spezifischepraegungcode TEXT, xplan_reftextinhalt TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_wirdausgeglichendurchspe TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_zweckbestimmung TEXT, xplan_zweckbestimmungcode TEXT, xplan_detailliertezweckbestimmung TEXT, xplan_detailliertezweckbestimmungcode TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_fp_landwirtschaft_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-2', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 930, '8:5dde90b8724cbc51b8c9a061a19863c2', 'createTable tableName=xplan_fp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-3::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_gesetzlichegrundlagecode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-3', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 931, '8:a15e2fb812f174b4852aa7dbbf5f89ce', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-4::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_technischemassnahmenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-4', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 932, '8:d070cc1458a4317d4fbc65baa1bea978', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-5::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektlandesrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-5', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 933, '8:90032c8b1138c625284c8b4d0a8cf84b', 'addColumn tableName=xplan_lp_schutzobjektlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-6::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_einzelhandel ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-6', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 934, '8:5a9b63f7898479a1c765e8e58cb6d262', 'addColumn tableName=xplan_rp_einzelhandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-7::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_erholung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-7', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 935, '8:fc1b8363ca4029f6f9de6434ca90fc88', 'addColumn tableName=xplan_rp_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-8::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_erneuerbareenergie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-8', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 936, '8:b51750b1fe630ad7a5482e1d064c63cf', 'addColumn tableName=xplan_rp_erneuerbareenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-9::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_freiraum ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-9', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 937, '8:8e2a54bcdd04d04bfcc8773e93126db8', 'addColumn tableName=xplan_rp_freiraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-10::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_funktionszuweisung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-10', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 938, '8:47bdee0d111abda8ee97a31a3ea36b0a', 'addColumn tableName=xplan_rp_funktionszuweisung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-11::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_industriegewerbe ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-11', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 939, '8:b3d1039504826cca6fdb48e9656052b8', 'addColumn tableName=xplan_rp_industriegewerbe', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-12::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kulturlandschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-12', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 940, '8:e71acd1d19d6a8afaf04c99444cc2001', 'addColumn tableName=xplan_rp_kulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-13::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_laermschutzbauschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-13', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 941, '8:1669db80521a58dc6a7cbc8b0668b9b6', 'addColumn tableName=xplan_rp_laermschutzbauschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-14::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_luftverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-14', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 942, '8:95f8e1f2f94aba81b5fd6a2d0a0b6844', 'addColumn tableName=xplan_rp_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-15::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_planungsraum ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-15', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 943, '8:3c13cec368935e99cf18cccfbfd72388', 'addColumn tableName=xplan_rp_planungsraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-16::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_radwegwanderweg ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-16', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 944, '8:3b040227e07e4cbffaf0f30dce9494ba', 'addColumn tableName=xplan_rp_radwegwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-17::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoff ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-17', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 945, '8:7a06acc9287594e1ab10749f0845b18e', 'addColumn tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-18::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_schienenverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-18', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 946, '8:0cec6c6bac3822a237cbbe65f8cb4c11', 'addColumn tableName=xplan_rp_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-19::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_siedlung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-19', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 947, '8:e091aeebef113ca39557e924cc98ac44', 'addColumn tableName=xplan_rp_siedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-20::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigersiedlungsbereich ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-20', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 948, '8:c55e0a7c3458187e358cefb35f2282a0', 'addColumn tableName=xplan_rp_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-21::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-21', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 949, '8:27c46f597acdecca12dd51572c9a50bc', 'addColumn tableName=xplan_rp_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-22::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sportanlage ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-22', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 950, '8:00dd6154e1fe7c0a1aa960462592053b', 'addColumn tableName=xplan_rp_sportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-23::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_strassenverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-23', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 951, '8:0950f22629b47c19b57fa5ff8351d1da', 'addColumn tableName=xplan_rp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-24::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-24', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 952, '8:74bab82e7dc43698c3c90c577fe14ae5', 'addColumn tableName=xplan_rp_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-25::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsmass ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-25', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 953, '8:a3704d2331f1f1c27f41e99691740e25', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-26::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_eingriffsbereich ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-26', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 954, '8:8ede31be24d42dc8cc578ddb95219b75', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-27::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungnachlandesrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-27', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 955, '8:46e554e9c342d9aa49371e8970bb3fa7', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-28::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-28', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 956, '8:353d7f259884d966370804711b239c29', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-29::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenmass ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-29', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 957, '8:0387b048964fb6891362bdd0495b8ab3', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-30::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kleintierhaltungflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-30', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 958, '8:327f567253b4272d58800ba44dbb2320', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-31::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-31', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 959, '8:f47966bfb2b591a939af4b4b6220f495', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-32::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_regelungvergnuegungsstaetten ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-32', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 960, '8:a1ddbe709b26e24587863e215b8ea6f3', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-33::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-33', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 961, '8:cdd9c5de03ee890a7a4b02ac721aecd2', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-34::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_startbedingung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-34', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 962, '8:3dc58ca0ed8e5a4d4f553de0de015786', 'addColumn tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-35::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-35', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 963, '8:01824468fc31bb19b4f5d5aa99b269fe', 'addColumn tableName=xplan_so_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-36::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-36', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 964, '8:6f50a4c7356c33bd147570a2a73520a9', 'addColumn tableName=xplan_so_denkmalschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-37::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-37', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 965, '8:1f212cc35e5c91153f245b09bfa6fcbd', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-38::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-38', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 966, '8:420a990e2d7e5e76a950626d196fcf27', 'addColumn tableName=xplan_so_gebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-39::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-39', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 967, '8:c86e1e7521b9671770df708bc4531e75', 'addColumn tableName=xplan_so_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-40::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-40', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 968, '8:db08c0ffcb0b54cb758028780615055d', 'addColumn tableName=xplan_so_linienobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-41::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-41', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 969, '8:a9ea3125eafcc6e69497a3682081f08e', 'addColumn tableName=xplan_so_luftverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-42::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-42', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 970, '8:e04953c3923b0138d0c5c977030c06ab', 'addColumn tableName=xplan_so_objekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-43::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-43', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 971, '8:1dd739b277b1188da727772a858dd528', 'addColumn tableName=xplan_so_schienenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-44::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietnaturschutzrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-44', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 972, '8:fc73f5f4c23747757503eb1dea6ecd9f', 'addColumn tableName=xplan_so_schutzgebietnaturschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-45::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietsonstigesrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-45', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 973, '8:b070700e274838113ac9c926e52282f0', 'addColumn tableName=xplan_so_schutzgebietsonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-46::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-46', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 974, '8:3687a811bc49086c5867210f6a5824d8', 'addColumn tableName=xplan_so_schutzgebietwasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-47::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-47', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 975, '8:a097bd5fc4e140b2a35dcad0756d620b', 'addColumn tableName=xplan_so_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-48::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_strassenverkehrsrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-48', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 976, '8:2a7d1d1fc2b957b05d98baaa65821b51', 'addColumn tableName=xplan_so_strassenverkehrsrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-49::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-49', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 977, '8:446d450391b3b74520ab08c8a2284d43', 'addColumn tableName=xplan_so_wasserrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-50::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_endebedingung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-50', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 978, '8:39af11316e67a7fdcff8d5d4f34c3c8c', 'addColumn tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-51::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-51', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 979, '8:7fa237aec794a750ea28b2541023846f', 'addColumn tableName=xplan_fp_abgrabung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-52::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-52', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 980, '8:c1a98bb95dd6b73d0444bbe988f6a63c', 'addColumn tableName=xplan_fp_anpassungklimawandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-53::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-53', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 981, '8:e82b08f4a5bdd8ef8af9c9e0d7ae38d1', 'addColumn tableName=xplan_fp_aufschuettung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-54::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-54', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 982, '8:e5a26f283cd48370d1fd0f2d11c25a57', 'addColumn tableName=xplan_fp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-55::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-55', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 983, '8:be7550b678ebdd14b0d668bc4656734e', 'addColumn tableName=xplan_fp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-56::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-56', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 984, '8:1f4c5cabfba381d4a640a3625c01fbad', 'addColumn tableName=xplan_fp_bebauungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-57::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetze ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-57', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 985, '8:28f9d88e7dfa97aabb553163756ec85e', 'addColumn tableName=xplan_fp_bodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-58::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-58', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 986, '8:356b47a5d6d7b328c41cb1c66743e355', 'addColumn tableName=xplan_fp_gemeinbedarf', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-59::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-59', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 987, '8:5de9d57b3894caeb2e170157b4cd5a6d', 'addColumn tableName=xplan_fp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-60::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-60', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 988, '8:95d784de098a162a5c2e3634e3beae00', 'addColumn tableName=xplan_fp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-61::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gruen ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-61', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 989, '8:5db622cfc5fd4172cb9e5b198f133529', 'addColumn tableName=xplan_fp_gruen', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-62::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-62', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 990, '8:070dff8eef6f1de052fcf7afc937f1a0', 'addColumn tableName=xplan_fp_keinezentrabwasserbeseitigungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-63::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-63', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 991, '8:0b0c671013359c371fe3c8d688f73b3f', 'addColumn tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-64::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-64', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 992, '8:055440024551dcfaa4a2a1dde7607fac', 'addColumn tableName=xplan_fp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-65::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-65', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 993, '8:3098e66800f3ad9e7347b51736c39b45', 'addColumn tableName=xplan_fp_nutzungsbeschraenkungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-66::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_privilegiertesvorhaben ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-66', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 994, '8:8bbba80bb98a9b0143cbad94234e3be7', 'addColumn tableName=xplan_fp_privilegiertesvorhaben', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-67::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-67', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 995, '8:5b6551633bb0ddf4532e2f059bd4376f', 'addColumn tableName=xplan_fp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-68::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-68', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 996, '8:92a72be214c4835e1841c9f0adaeb272', 'addColumn tableName=xplan_fp_spielsportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-69::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-69', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 997, '8:45b276879749d7ce696dae82fe19a300', 'addColumn tableName=xplan_fp_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-70::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-70', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 998, '8:889443403d7af1b7dd9a91bb31f10cf3', 'addColumn tableName=xplan_fp_textlichedarstellungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-71::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-71', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 999, '8:59045d8e99afc2d412fab5a375fae49a', 'addColumn tableName=xplan_fp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-72::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-72', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1000, '8:a4ac40dc4c061f7edb83a891202f8d4c', 'addColumn tableName=xplan_fp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-73::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-73', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1001, '8:85cab4155170f423259c5c3bea138fae', 'addColumn tableName=xplan_fp_vorbehalteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-74::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-74', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1002, '8:579e934b4b33413d98751a80d4ca2e16', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-75::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-75', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1003, '8:cc00da2b7de8ccd5ab3b473fd8636327', 'addColumn tableName=xplan_fp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-76::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_zentralerversorgungsbereich ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-76', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1004, '8:bfe841ac5ad3feb038cbff76827f51e5', 'addColumn tableName=xplan_fp_zentralerversorgungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-77::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_abgrenzung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-77', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1005, '8:5a1688756c759b02f77abf1449874a77', 'addColumn tableName=xplan_lp_abgrenzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-78::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_allggruenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-78', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1006, '8:2fab467f14d1e4f974defe3babed1bab', 'addColumn tableName=xplan_lp_allggruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-79::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-79', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1007, '8:578384a6c92037476d5fc503af18934d', 'addColumn tableName=xplan_lp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-80::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_ausgleich ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-80', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1008, '8:42d712631e07a3563d71f5d98fe1607f', 'addColumn tableName=xplan_lp_ausgleich', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-81::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_biotopverbundflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-81', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1009, '8:1788fd595ac5f22fc78a9305468e9a9d', 'addColumn tableName=xplan_lp_biotopverbundflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-82::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bodenschutzrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-82', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1010, '8:bad4176ab6b902f33d436ad8bd533826', 'addColumn tableName=xplan_lp_bodenschutzrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-83::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_erholungfreizeit ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-83', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1011, '8:727c53b1ef7f154a7a0baea7887f8cce', 'addColumn tableName=xplan_lp_erholungfreizeit', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-84::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_forstrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-84', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1012, '8:42ae9fdbc3f8010acaf918e11924f816', 'addColumn tableName=xplan_lp_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-85::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_generischesobjekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-85', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1013, '8:3d073d9aeead2b8f5504dd99fb7439dd', 'addColumn tableName=xplan_lp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-86::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_landschaftsbild ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-86', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1014, '8:0d482bf1137645286df57195d86d718e', 'addColumn tableName=xplan_lp_landschaftsbild', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-87::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungsausschluss ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-87', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1015, '8:52f54e2391b7d2810f739ae8b2a73f53', 'addColumn tableName=xplan_lp_nutzungsausschluss', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-88::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungserfordernisregelung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-88', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1016, '8:76ab498cd4f18205714d4766185a45b3', 'addColumn tableName=xplan_lp_nutzungserfordernisregelung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-89::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_planerischevertiefung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-89', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1017, '8:7d583e82088e7680d614b556d5241b0d', 'addColumn tableName=xplan_lp_planerischevertiefung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-90::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektinternatrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-90', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1018, '8:af3a3cd9c21bfd34a3cb394cd4180c62', 'addColumn tableName=xplan_lp_schutzobjektinternatrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-91::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzpflegeentwicklung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-91', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1019, '8:61554a8044633f4adc3b367a31d2acda', 'addColumn tableName=xplan_lp_schutzpflegeentwicklung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-92::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigesrecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-92', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1020, '8:d76314e656272d0dcfb6ca64ea2356ce', 'addColumn tableName=xplan_lp_sonstigesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-93::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textlichefestsetzungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-93', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1021, '8:f8d70e9da31c960c144d2067fbb41493', 'addColumn tableName=xplan_lp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-94::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-94', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1022, '8:b4a9073976f95256ba8868011415f4d2', 'addColumn tableName=xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-95::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtschutzgebiet ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-95', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1023, '8:960d08daa23ef0a61173782940ce8fe7', 'addColumn tableName=xplan_lp_wasserrechtschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-96::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtsonstige ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-96', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1024, '8:9fb98b2255fb5b16d79fb00b8689d76d', 'addColumn tableName=xplan_lp_wasserrechtsonstige', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-97::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-97', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1025, '8:6541428b6b89c45f2b81e882f2fc0920', 'addColumn tableName=xplan_lp_wasserrechtwirtschaftabflusshochwschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-98::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-98', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1026, '8:890f787c779a70a4b91ec3add4052286', 'addColumn tableName=xplan_lp_zubegruenendegrundstueckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-99::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zwischennutzung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-99', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1027, '8:3b0f2269ad24596f7dce46e208eaa6e4', 'addColumn tableName=xplan_lp_zwischennutzung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-100::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-100', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1028, '8:9853b5df321d4dee94c745dd5337bfe8', 'addColumn tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-101::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bodenschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-101', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1029, '8:4c0c62396d05707253178453de5fa83a', 'addColumn tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-102::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_energieversorgung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-102', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1030, '8:e8d32ce0e41b4ad32289c54959d09aee', 'addColumn tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-103::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entsorgung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-103', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1031, '8:5da8df87149f10bae3e6ac899eb4d4fb', 'addColumn tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-104::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_forstwirtschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-104', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1032, '8:39bb95bbd31594e5400bd62bd643d56f', 'addColumn tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-105::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_generischesobjekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-105', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1033, '8:2bcc56b4fc80490edb80549a2138f51f', 'addColumn tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-106::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gewaesser ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-106', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1034, '8:be95b7abaf1e8131e99bd344e4727dae', 'addColumn tableName=xplan_rp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-107::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_grenze ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-107', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1035, '8:43270fc474bfba842d86fb561aa4d812', 'addColumn tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-108::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gruenzuggruenzaesur ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-108', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1036, '8:8dbc325774526201f0c1c432dc516667', 'addColumn tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-109::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_klimaschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-109', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1037, '8:f04e618db2705eb1634f22d0fa53626d', 'addColumn tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-110::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kommunikation ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-110', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1038, '8:24669e6811b3853984f00de63151dd76', 'addColumn tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-111::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_landwirtschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-111', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1039, '8:65ee2bc29f26304b492d25ba22b7c351', 'addColumn tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-112::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturlandschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-112', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1040, '8:c2ca3aa3417377e30ce2eecd90229ed8', 'addColumn tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-113::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-113', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1041, '8:3ad714b7da7236dfdbfc78154bd3171d', 'addColumn tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-114::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-114', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1042, '8:27db937c9a18d0e49368089c62e85239', 'addColumn tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-115::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigeinfrastruktur ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-115', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1043, '8:f684656d9a0787bf97e853a803547c0c', 'addColumn tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-116::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sozialeinfrastruktur ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-116', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1044, '8:d082f1f4b05e5791a4df76ebed61631c', 'addColumn tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-117::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-117', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1045, '8:6d4abf1a4f00da9b7bd69fc55e50efea', 'addColumn tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-118::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_verkehr ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-118', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1046, '8:c57f4e6c63289444f93a8aa9d23e2421', 'addColumn tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-119::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-119', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1047, '8:8dd0cd7d31384349f1201ba2429a0af4', 'addColumn tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-120::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserwirtschaft ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-120', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1048, '8:ccdbf83c1647b680c0f37c20ca1488a7', 'addColumn tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-121::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_zentralerort ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-121', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1049, '8:765e23f71e084cd30958b40fd184afe5', 'addColumn tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-122::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_hochwasserschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-122', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1050, '8:9a7560ff7f373806422ff25c0bfe0095', 'addColumn tableName=xplan_rp_hochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-123::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigerfreiraumschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-123', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1051, '8:560196d89766a8a293b471251d02e887', 'addColumn tableName=xplan_rp_sonstigerfreiraumschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-124::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wohnensiedlung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-124', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1052, '8:79aefab29ea32102c890c8efae32541d', 'addColumn tableName=xplan_rp_wohnensiedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-125::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-125', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1053, '8:fe5b5d48112c0b3ca64d3a8880eb29ef', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-126::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abgrabungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-126', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1054, '8:d9ad93849d73afadcf2fe15144c2b8f1', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-127::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-127', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1055, '8:2bbc0af0f804ca5913ff4876b2cc7351', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-128::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-128', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1056, '8:17b2f1e111aa8bd028e959f9449f1967', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-129::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_aufschuettungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-129', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1057, '8:67fa699358bc3413f69fb6871e45e3ae', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-130::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-130', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1058, '8:fac27ce15bdd817fda75da20cde2f642', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-131::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsmassnahme ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-131', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1059, '8:611ab676dfafbe7f8a590a4cf654fdc2', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-132::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-132', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1060, '8:87aec599f1345a8e0ca74a9628f7aa81', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-133::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugrenze ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-133', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1061, '8:999219ad2ff61d651b861e0bbede4a7b', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-134::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baulinie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-134', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1062, '8:c3bb4ba374905dcb221e13924fa0b6eb', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-135::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-135', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1063, '8:da7e1284fb3cd37e7643fa87ba40c856', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-136::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-136', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1064, '8:8d27fe8bdfdaa6913314727e8e916529', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-137::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bodenschaetzeflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-137', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1065, '8:6ed523e31ae925929b0f4e8ff6c89438', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-138::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtpunkt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-138', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1066, '8:3e3f525aea955135f10199e5e5a5d8b8', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-139::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtsbereichlinie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-139', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1067, '8:7e7a53b21c11db193c2b90487507f288', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-140::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erhaltungsbereichflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-140', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1068, '8:075e6414d1de812d66cebe0f3515f423', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-141::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_firstrichtungslinie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-141', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1069, '8:cda65e71a20087aa38fbc6079dcc89ce', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-142::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_foerderungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-142', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1070, '8:2afbd532aab46b17119aa61a293b92fd', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-143::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_freiflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-143', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1071, '8:6df1c61ba48fe1de41ef768638a4fab2', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-144::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gebaeudeflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-144', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1072, '8:62e15adae7070c7ddf24755d0bea3289', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-145::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-145', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1073, '8:e17ddc743e570d482233a81b7af11ef4', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-146::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-146', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1074, '8:227dea4ee7d32b348381d78e98c48326', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-147::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_generischesobjekt ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-147', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1075, '8:211ce103dd4f1f86fbbf1c6763b50777', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-148::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gewaesserflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-148', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1076, '8:5d6512e71476744a3e384e41d495fff9', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-149::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-149', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1077, '8:0b6ae95a543e601c440ffe8fc3644e30', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-150::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_immissionsschutz ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-150', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1078, '8:4109fb3d4692269b049fe3fd50542c69', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-151::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kennzeichnungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-151', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1079, '8:b90b1b672145698b5fdb762011142451', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-152::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-152', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1080, '8:e7d267ced9595a3edd8f68a3aad97b97', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-153::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-153', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1081, '8:cc629a47c3f95f4ac28e7cde9d67caf3', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-154::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nutzungsartengrenze ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-154', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1082, '8:16ab772ef4632c840d5f4aff7844a821', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-155::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_persgruppenbestimmteflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-155', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1083, '8:dbbb35e7ea7258de26ed015ec4b9acc1', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-156::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rekultivierungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-156', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1084, '8:9566b0a64695025c1b5a22a99035585a', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-157::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-157', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1085, '8:918f573005c7c5fa485807c553bdbf36', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-158::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-158', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1086, '8:95a1987b0c06b91c777897e0bd24f20b', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-159::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-159', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1087, '8:a0d4232383d35e4a7cb6212009a0e483', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-160::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-160', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1088, '8:f607f71b63bb08d96644d739a4080929', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-161::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenbegrenzungslinie ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-161', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1089, '8:83156c093674f033ae08899b2682b619', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-162::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenkoerper ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-162', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1090, '8:93c0e0108840733783ceb803cd1141ae', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-163::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-163', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1091, '8:b28dd74bc4381e8842562fd92e327f3a', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-164::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textlichefestsetzungsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-164', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1092, '8:cfd37c91ffe67e9c769c95f5f100aa49', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-165::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-165', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1093, '8:415bbe856a8188da66ca7e886aa59ecf', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-166::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_unverbindlichevormerkung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-166', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1094, '8:91b646f947819eb4308e2502106dd694', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-167::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-167', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1095, '8:83186e36d6d374ba41d7f94e340a7de0', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-168::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-168', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1096, '8:497c113a8b9e7d9c5f4f521e55cfe798', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-169::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_waldflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-169', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1097, '8:8961299dab39223daff582be1ddad1c1', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-170::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserwirtschaftsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-170', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1098, '8:039dbfef35585e0fd6841ed65f620853', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-171::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-171', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1099, '8:9437d57a07ba0c5b892958b921fdc54c', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-172::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_plan ADD xplan_technischerplanersteller TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-172', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1100, '8:05a24d8e797ceec6b7f0557dfcfea14f', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-173::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_plan ADD xplan_gemeinde TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-173', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1101, '8:8ee2b1802eabee4b2bb7327797252311', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-174::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_plan ADD xplan_planaufstellendegemeinde TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-174', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1102, '8:6040898d2f0a3efb609de30d1a985d86', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-175::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_aufschuettungsmaterial TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-175', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1103, '8:9cf67c3e78b25bb75d45ef9bee78f4b1', 'addColumn tableName=xplan_fp_aufschuettung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-176::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_mindesthoeheuom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-176', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1104, '8:209bf705defb67b40b0ecb88b888852c', 'addColumn tableName=xplan_lp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-177::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_anzahl INTEGER;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-177', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1105, '8:9ffa9223eea97237547b441709544030', 'addColumn tableName=xplan_lp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-178::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoff ADD xplan_detaillierterrohstofftyp TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-178', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1106, '8:26b183f3526ac55bd4acfb1ad4013a45', 'addColumn tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-179::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_plan ADD xplan_technischerplanersteller TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-179', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1107, '8:824f3a26112dd0efc99fa29030334724', 'addColumn tableName=xplan_lp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-180::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise ADD xplan_wegerecht TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-180', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1108, '8:6c6f5bcf7d7667134479626fb3983f85', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-181::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-181', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1109, '8:7c40ca15cc4c9ac1bb9d3c52f3337a9d', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-182::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_wirddargestelltdurch TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-182', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1110, '8:b0cfce2bf5913356b3f9d7c33257e311', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-183::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_refbegruendunginhalt TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-183', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1111, '8:843c7707ad8ee5e70f4a64d68dfb4202', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-184::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-184', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1112, '8:86630c21e2183b662cd5ff5e3314118f', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-185::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-185', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1113, '8:1bd724f9bfa793ece043bd926aad55f9', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-186::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_aufschrift TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-186', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1114, '8:f5b235b293ae625da08e04c8b2a170a6', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-187::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht ADD xplan_istschmal TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-187', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1115, '8:004022e6f8532a36174cb39ffd883972', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-188::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_rechtscharakter TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-188', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1116, '8:b7d68d340c066f9099a8f3ac4eadc4fe', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-189::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_rechtscharaktercode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-189', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1117, '8:875cb967a177c1968400cc06eee35df5', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-190::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_technischerplanersteller TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-190', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1118, '8:4d0099775e2381cbb2dcd5cf6544f7b3', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-191::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_planaufstellendegemeinde TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-191', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1119, '8:b1c3f05edbe4c0ca0a8dfb15d51b5fdb', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-192::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_reftextinhalt TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-192', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1120, '8:3795ceb3e675ec8d60562d87c784465e', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-193::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_plan ADD xplan_technischerplanersteller TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-193', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1121, '8:03407b5663970be3b90fb393e90b0394', 'addColumn tableName=xplan_rp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-194::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_plan ADD xplan_genehmigungsbehoerde TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-194', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1122, '8:ccd5090a06b7a92084f208bc1ed6d06b', 'addColumn tableName=xplan_rp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-195::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionbaunvodatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-195', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1123, '8:179f757d161e32e98152facdec7868f0', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-196::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_wirdausgeglichendurchabe TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-196', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1124, '8:aea49e17a611eb2b42a3710413e6c200', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-197::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_mindesthoehe numeric;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-197', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1125, '8:d823f51c88ec17c6403eeaf26718548e', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-198::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionbaunvotext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-198', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1126, '8:01d4d6bad157291b52f76416fa26cd70', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-199::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_wirdausgeglichendurchspemassnahme TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-199', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1127, '8:e37d00952dff21374e2d031cf214ea22', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-200::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_mindesthoeheuom TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-200', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1128, '8:02f9fc6f9e4187c457f4615e27bfdb0e', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-201::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionbaugbdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-201', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1129, '8:a8783f832225ebf4ff8059e13c04c056', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-202::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_wirdausgeglichendurchspeflaeche TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-202', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1130, '8:bc16e2b8b3718eb4b295464cc208c958', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-203::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_anzahl INTEGER;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-203', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1131, '8:2855735fa907495f6519bbdb1adbe31c', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-204::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_flussrichtung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-204', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1132, '8:727cdc0c944e1b7ea358308a3eae5cab', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-205::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionbaugbtext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-205', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1133, '8:be690d604bbb8b362d625ba31f3139cb', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-206::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionsonstrechtsgrundlagedatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-206', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1134, '8:4f8a1a620a363a8fc2022c1ae5f88f09', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-207::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_versionsonstrechtsgrundlagetext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-207', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1135, '8:288467f5386837ebc74e5d49149513a8', 'addColumn tableName=xplan_fp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-208::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_technischerplanersteller TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-208', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1136, '8:ef1e1444bae93681e7be8ea7bb4ae1fb', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-209::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_planaufstellendegemeinde TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-209', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1137, '8:ca619d0c1f47ebf8a35a64da016faada', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-210::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionbaunvodatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-210', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1138, '8:ca2bcd23ec41f06ce25b2a0d53b4768b', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-211::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionbaunvotext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-211', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1139, '8:0645a37dc4b6f930b34dc24d80cc2201', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-212::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionbaugbdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-212', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1140, '8:4949c75a972eeb80860d762390d848c5', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-213::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionbaugbtext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-213', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1141, '8:194112e4acdb299d703c6cccf1ab9308', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-214::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionsonstrechtsgrundlagedatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-214', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1142, '8:04109f8b5311f864958f3babeda981c2', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-215::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_versionsonstrechtsgrundlagetext TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-215', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1143, '8:f3074d645f807017bd81bbc1d0d8212d', 'addColumn tableName=xplan_bp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-216::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_zugunstenvon TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-216', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1144, '8:7952ac69a5cd58ddeb351563abeb64e8', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-217::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_dachgestaltung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-217', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1145, '8:9fb7dc171111f41c24f4cbcdbf4c5bdb', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-218::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dachgestaltung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-218', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1146, '8:105407bac3abca6dd1b0656134027e35', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-219::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_dachgestaltung TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-219', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1147, '8:895f7f1ac85437299116d52a7267310a', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-220::lgvxplanisk (generated)
CREATE INDEX spatial_idx_491 ON xplansyn.xplan_bp_nichtueberbaubaregrundstuecksflaeche(xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-220', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1148, '8:ddede2a6af4e931921ce947cc5606fd0', 'createIndex indexName=spatial_idx_491, tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Changeset changelog_xplansyn.xml::1543833329759-221::lgvxplanisk (generated)
CREATE INDEX spatial_idx_492 ON xplansyn.xplan_fp_landwirtschaft(xplan_position);

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1543833329759-221', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1149, '8:3c328a4259ed2026f41fa85e9e7f52ca', 'createIndex indexName=spatial_idx_492, tableName=xplan_fp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '3833698746');

-- Release Database Lock
UPDATE xplansyn.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

