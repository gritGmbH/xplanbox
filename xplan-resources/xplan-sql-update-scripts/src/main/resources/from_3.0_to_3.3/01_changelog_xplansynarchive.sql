-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 10.02.20 15:01
-- Against: postgres@jdbc:postgresql://localhost:5432/xplanbox31
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansynarchive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynarchive.databasechangeloglock;

INSERT INTO xplansynarchive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2020-02-10 15:01:27.689' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynarchive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansynarchive.xml::1581343261688-2::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_abweichungvonbaugrenze (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_abweichungvonbaugrenze_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-2', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 1, '8:8dc52b67a8648be8928046ac887a8e65', 'createTable tableName=xplan_bp_abweichungvonbaugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-3::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_abweichungvonueberbauberergrundstuecksflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_abweichungvonueberbauberergrundstuecksflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-3', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 2, '8:88d932fd166a29c6df4a428ac482810b', 'createTable tableName=xplan_bp_abweichungvonueberbauberergrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-4::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_richtungssektorgrenze (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_richtungssektorgrenze_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-4', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 3, '8:6dba749a04a296df0a786ede30013e67', 'createTable tableName=xplan_bp_richtungssektorgrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-5::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_sichtflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_sichtflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-5', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 4, '8:fc620847abdb74ad40abfb5968618580', 'createTable tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-6::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_zusatzkontingentlaerm (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_bezeichnung TEXT, xplan_richtungssektor TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_zusatzkontingentlaerm_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-6', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 5, '8:995437ae9e0863b525e89d7941a918ce', 'createTable tableName=xplan_bp_zusatzkontingentlaerm', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-7::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_zusatzkontingentlaermflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_flaechenschluss TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_zusatzkontingentlaermflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-7', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 6, '8:7905ac95171e9df6509ce14bea944017', 'createTable tableName=xplan_bp_zusatzkontingentlaermflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-8::lyn (generated)
CREATE TABLE xplansynarchive.xplan_so_bauverbotszone (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_sonstrechtscharakter TEXT, xplan_sonstrechtscharaktercode TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_artderfestlegung TEXT, xplan_artderfestlegungcode TEXT, xplan_detailartderfestlegung TEXT, xplan_detailartderfestlegungcode TEXT, xplan_rechtlichegrundlage TEXT, xplan_rechtlichegrundlagecode TEXT, xplan_name TEXT, xplan_nummer TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_so_bauverbotszone_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-8', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 7, '8:162668e8b83adae4abe7adc8d4a92907', 'createTable tableName=xplan_so_bauverbotszone', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-9::lyn (generated)
CREATE TABLE xplansynarchive.xplan_so_gewaesser (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_aufschrift TEXT, xplan_ebene INTEGER, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_sonstrechtscharakter TEXT, xplan_sonstrechtscharaktercode TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_artderfestlegung TEXT, xplan_artderfestlegungcode TEXT, xplan_detailartderfestlegung TEXT, xplan_detailartderfestlegungcode TEXT, xplan_name TEXT, xplan_nummer TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_so_gewaesser_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-9', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 8, '8:b84a0c699611e49869672b3ef37d6007', 'createTable tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-10::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_lto ADD xplan_anchorx numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-10', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 9, '8:6f773adfba77f256fe3c6bfddccca00a', 'addColumn tableName=xplan_xp_lto', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-11::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_pto ADD xplan_anchorx numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-11', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 10, '8:ec61a7ad694cfad1416572667fc6f220', 'addColumn tableName=xplan_xp_pto', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-12::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_nutzungsschablone ADD xplan_anchorx numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-12', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 11, '8:5183d30171140660c0a7cf60ef01cf5e', 'addColumn tableName=xplan_xp_nutzungsschablone', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-13::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_lto ADD xplan_anchory numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-13', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 12, '8:489d641b3f7ac70693f92464df81c585', 'addColumn tableName=xplan_xp_lto', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-14::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_pto ADD xplan_anchory numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-14', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 13, '8:ce7965309f88acf52b07dfa55bd53f39', 'addColumn tableName=xplan_xp_pto', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-15::lyn (generated)
ALTER TABLE xplansynarchive.xplan_xp_nutzungsschablone ADD xplan_anchory numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-15', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 14, '8:3bfbdc8bbcfcd5b3e962c24290067ed8', 'addColumn tableName=xplan_xp_nutzungsschablone', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-16::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-16', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 15, '8:570f7002fc345f1db47fe490c972d749', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-17::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-17', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 16, '8:ca66f718b0cc3fabddef4c06620f5c2e', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-18::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-18', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 17, '8:ef8950897e610568b6dd456234e279d7', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-19::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-19', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 18, '8:3e954e87690c00f93826c064524c9f76', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-20::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-20', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 19, '8:ce1c0caefb78e80be25d8c2b3504fad0', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-21::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-21', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 20, '8:5ae5f032e1331cce19d059baf0a8c281', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-22::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-22', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 21, '8:9bacdc93dd22d806149b857cdc2ca249', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-23::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-23', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 22, '8:5f4e21ca4c2589a26dfb1f301f340c69', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-24::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-24', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 23, '8:c48783578636d4ed615f88379ae04d99', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-25::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-25', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 24, '8:5cc23b9ecaf5a535792abadda98b5493', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-26::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-26', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 25, '8:61ca8e701e25a9f0d683d44f6ceb377e', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-27::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-27', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 26, '8:ceefdd6a293f220e009bb7374cea76cb', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-28::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-28', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 27, '8:e34668b915dcacec910216ab3ea8e51c', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-29::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-29', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 28, '8:77cee425056df23c95c7277850361f70', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-30::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-30', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 29, '8:e71b68be73e79007f9814470ae54b2c9', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-31::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-31', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 30, '8:020b4948daf92daca68b36b3ea649f41', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-32::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-32', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 31, '8:ec1b981d98c1985f0baeccf658e68889', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-33::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-33', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 32, '8:a21f3751cfd89c768a7ffddda4731f3e', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-34::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-34', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 33, '8:8f79eab7520b1332962bad133fbdd025', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-35::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-35', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 34, '8:fd1148069392446cadad35477ab8843b', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-36::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-36', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 35, '8:7e0f91b8d4a40ab09ea0f82d4ce0f3db', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-37::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-37', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 36, '8:c23c9c5f39296bff2d9918d63bfb6304', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-38::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-38', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 37, '8:06d703b04f68ac316e1173a743e5acf3', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-39::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-39', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 38, '8:14ecd4059c5897e88199d5a77ed9234a', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-40::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-40', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 39, '8:58826d4f766110fad7b9efb6e1da995c', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-41::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-41', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 40, '8:6d39abf06c20db063263e5a291be00bb', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-42::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-42', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 41, '8:2d7cc1048a042ccc171fe59a0208c2a3', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-43::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-43', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 42, '8:86962344d5614d50a40242b5942e407c', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-44::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-44', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 43, '8:eb78332e3df747b3dadbb610dcc78bf8', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-45::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-45', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 44, '8:5cad83203862f5139d403dc16b7c5c21', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-46::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-46', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 45, '8:7a221c78bd6dbd847f85976c5d52524e', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-47::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-47', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 46, '8:83e3017e0c384702c2c582d4df99aaa2', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-48::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-48', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 47, '8:58c7b1bf0aade1d2ea827ed8d6293a47', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-49::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-49', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 48, '8:e252b345534e5c49e2644c5434880002', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-50::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-50', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 49, '8:3fbce9f242303a627388c725e8b33e8b', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-51::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-51', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 50, '8:3f5497fe1309b112b55663e7963b19ae', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-52::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-52', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 51, '8:d26f7ea4d7592e9f5899abe664ae821c', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-53::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-53', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 52, '8:6fabf2fe42b49f1ec53229c14fb307ab', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-54::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-54', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 53, '8:c7c5db131275edd915e7c97e7eb767b7', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-55::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-55', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 54, '8:05c153020077bafa2e47e3e3999077a0', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-56::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-56', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 55, '8:116e92024799669df423da511f452116', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-57::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-57', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 56, '8:3ca12392a1ca51d8151868aa3b03cc6e', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-58::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-58', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 57, '8:4e53b61c5934cb87fac4b249b5018141', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-59::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-59', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 58, '8:a41b4c3de516882b4083e5fb35c50188', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-60::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-60', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 59, '8:64727998cf49846ef724876e353414d4', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-61::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-61', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 60, '8:d06d9e0517a80e2df155bd67ad70bae8', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-62::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-62', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 61, '8:f8019978253cf49cffc8ea3be835b50e', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-63::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-63', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 62, '8:5f729e959694b673e66fb5c788e2d5f9', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-64::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-64', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 63, '8:727646f63ded5370062e53632183180a', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-65::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-65', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 64, '8:c9ee199ae691aca58fbc750a7a514687', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-66::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-66', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 65, '8:27f33e49b4c15d592e70202879490d6d', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-67::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-67', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 66, '8:c44ef46ccff98a418aa9be89435b982b', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-68::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-68', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 67, '8:f082d892b5770dea5ed561a7c7af873d', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-69::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-69', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 68, '8:08599a91f37ba4a5a8410468d390c373', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-70::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-70', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 69, '8:4a5529d8894c6c4cd70b9eea14cc5eb8', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-71::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-71', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 70, '8:66ba75014998f49821b63ebd13a98ef3', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-72::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-72', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 71, '8:b3a4da8fe5ec9b6abf440d53c8ee4d86', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-73::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-73', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 72, '8:2863c5ad818680a5a69ae7805e2bbf59', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-74::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-74', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 73, '8:d6b33d9164562ca07271c49312cb6d44', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-75::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-75', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 74, '8:52d2cf20624ae67e94a0ae0be60b3c42', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-76::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-76', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 75, '8:0dab4383780d3e6ef9c501ed5cef86ac', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-77::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-77', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 76, '8:33290b330753603e911a86a9ecbce867', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-78::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-78', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 77, '8:c93166805cf14a5575e0fdcab0d69eb5', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-79::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-79', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 78, '8:ff2019c073e41955d1cf00dff9afa2a8', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-80::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-80', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 79, '8:daef5926a5b72f012548e0368ad88a45', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-81::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-81', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 80, '8:9f9e0368eb4e2fdc66104906842ee46f', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-82::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-82', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 81, '8:e484a6c3cbcb8aebe1236adf1689194a', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-83::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-83', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 82, '8:ea3d5e058184ce1aac6bb2365e932f7b', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-84::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-84', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 83, '8:c39dc4623b21f0d42a3710fbc624d84c', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-85::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-85', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 84, '8:a3366b90840b8562410d07956a51c68d', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-86::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-86', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 85, '8:7abcb72946a09cf63d2360f36dbccf1e', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-87::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-87', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 86, '8:e7e8c27a8c266887082370ec52af6351', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-88::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-88', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 87, '8:ff5b86c71bc00bec76a2d2bbc48fd49e', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-89::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-89', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 88, '8:e9ae155cf586c6a715782434a4ffeed4', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-90::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-90', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 89, '8:81b33f19ffa29d858e9f6b104ff8430c', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-91::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-91', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 90, '8:06863755b4d074421281da64f7d78528', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-92::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-92', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 91, '8:d9ef885806bb7b8d41071a0185d24222', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-93::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-93', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 92, '8:40400bfd36d2a374d72dc66c135941f5', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-94::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-94', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 93, '8:4752d91abda7ac89fbda76e525055c1a', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-95::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-95', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 94, '8:fc142c34c20df94d22d629d229bf1116', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-96::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-96', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 95, '8:ddb53ab321f3e259c4a7cde5263f8365', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-97::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-97', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 96, '8:eece84076e9dc0012611729ed52c9e23', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-98::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-98', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 97, '8:cd5bc33533767f0de740ecf9645bb515', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-99::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-99', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 98, '8:f7e364d8d7dc7cfa6393d5deb4b1decd', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-100::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-100', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 99, '8:aa427ec4ad4f3bb6202eac253fb6cef9', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-101::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-101', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 100, '8:c2d34ed98142e45c17063b981aa493cb', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-102::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-102', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 101, '8:1bb1d07e05f8e03e5e8e6282ec1c891c', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-103::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-103', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 102, '8:5c7b4cf62ce2e20c5973252f87f8903d', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-104::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-104', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 103, '8:7a7139488b0bd1b7158f52e55226423a', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-105::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-105', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 104, '8:d61bb45064bc56e53d04051fb3b279a1', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-106::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-106', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 105, '8:c1e18d24921b0cf6ff450dae6a743606', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-107::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-107', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 106, '8:5e8a28d5f549c9ee1019e44a70377ea9', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-108::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-108', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 107, '8:293856ddc09cde986ae41f98834adc69', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-109::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-109', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 108, '8:e120b16dbde3004709226569d1d400b4', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-110::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-110', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 109, '8:c50e957a196036a38590385b0c72225c', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-111::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-111', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 110, '8:a2c67aef7f4fe83eba11007e192eeb2a', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-112::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-112', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 111, '8:41de8416cfde69c52e1f3adb9c7710e7', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-113::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-113', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 112, '8:8b2d548facbe1265ec99ea0a663015d2', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-114::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-114', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 113, '8:40e6c6ede764d3f5b9a4672c48815180', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-115::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-115', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 114, '8:456e72db0f14c208821392e68091c1b3', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-116::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_technischemassnahmenflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-116', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 115, '8:1d4617e27a2ec8d3d0c64a76a91a4600', 'addColumn tableName=xplan_bp_technischemassnahmenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-117::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-117', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 116, '8:20e733240d5e36bc30a9f310753eddf1', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-118::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-118', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 117, '8:4cf7e1291561425eae20adaa7ab10627', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-119::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-119', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 118, '8:dabcc99e9c58a61bf2a41fc22562ca28', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-120::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_eingriffsbereich ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-120', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 119, '8:d12536e3cd7b759bd35eb4a29589e3bf', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-121::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-121', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 120, '8:d4c9097238454c1995b561ccfd27b00e', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-122::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-122', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 121, '8:da376a68e95c99dd3e6a1cd5503206dd', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-123::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenmass ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-123', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 122, '8:c91cf1d010d87c8ef4f9b8af0d0801be', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-124::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-124', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 123, '8:fffaf6326b5839278cb569d080a4eeaf', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-125::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaft ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-125', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 124, '8:9e281f260c43ec7b5a3d18c85111db2d', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-126::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-126', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 125, '8:5be87869d2071e4f59204d56c61d8740', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-127::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_veraenderungssperre ADD xplan_laermkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-127', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 126, '8:6eb2318cc080cc1e1dd745b5928ca694', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-128::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-128', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 127, '8:34e9ad0cdb9dafdeccdb313c18024481', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-129::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-129', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 128, '8:689e3dd172b47beac35d9b93dee0c1d0', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-130::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-130', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 129, '8:d005393bd4131a05b73ef0801c2792c8', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-131::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-131', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 130, '8:3a8df556e6e76ea03e097f786ff81349', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-132::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-132', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 131, '8:86ca73b904a7ec01abd7dff760e8c0d8', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-133::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-133', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 132, '8:c878fdb9722178d863f4236a944d616c', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-134::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-134', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 133, '8:494118c25d79b7302f6f685d4cb6b5de', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-135::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-135', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 134, '8:80f508bcd6435338b1b6a584414f0032', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-136::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-136', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 135, '8:9b0470e6aa400d309bbbbb1050741c84', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-137::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-137', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 136, '8:9483bcde9773c3ade45c403e11972c89', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-138::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-138', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 137, '8:1c71b57e64b92f6d0345a4cee76108c8', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-139::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-139', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 138, '8:ddfcb2456fed94d42988bbb7a77b9abd', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-140::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-140', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 139, '8:90f462ba33ee8877167c8ef467e75aad', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-141::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-141', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 140, '8:4ddce46f7ef568b57dbaf9edbf9e7a24', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-142::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-142', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 141, '8:ce962f903cd9c8aac49297b018360d55', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-143::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-143', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 142, '8:e02c35ee3a0553aad18bdae27ad1d862', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-144::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-144', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 143, '8:15430520d2ca62f056c035bf708f2a51', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-145::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-145', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 144, '8:7a29c628126b57899df77b99a1196899', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-146::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-146', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 145, '8:f583ecdd073da616f4d6f727c784b9b6', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-147::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-147', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 146, '8:0bd8053e972c203b2ae4f9967e70bf6b', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-148::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-148', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 147, '8:1f6525a497edfd827e9782ca0c0e372e', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-149::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-149', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 148, '8:c531cbf50447b938980b354fb3039d69', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-150::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-150', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 149, '8:df91669c50142f02d35a69693bd3a996', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-151::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-151', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 150, '8:8a6e0a86d5ddebec7d17790f820a14d4', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-152::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-152', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 151, '8:db8779390b237143c478f1e01c05ffa8', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-153::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-153', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 152, '8:b55496363cd51c90df75061d3c6fc9ea', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-154::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-154', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 153, '8:1b40849af77e553323f37a45afa78b59', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-155::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-155', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 154, '8:cf832777c2ccac60470266026f4a85b2', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-156::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-156', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 155, '8:aed63de3e9abdf7cd3693479d1883c4f', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-157::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-157', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 156, '8:81673a17587fe08aa3ef0b511b605553', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-158::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-158', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 157, '8:579313e40913ecf922f241900cd3c3e6', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-159::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-159', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 158, '8:525b5575f12731937fbdf27db6e1d671', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-160::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-160', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 159, '8:f933baf5159462075d9e2b43e95b497f', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-161::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-161', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 160, '8:46be2c7f1c167b9270f62a7f135620d2', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-162::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-162', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 161, '8:f45765c8c09e4b72e230648d2ce20874', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-163::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-163', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 162, '8:2b6299aa21fd1946e21ba297a8f911da', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-164::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-164', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 163, '8:7da934c2d224c252a88aa9a6b7926c81', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-165::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-165', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 164, '8:484228f6a876d1531ae2d098177dfb32', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-166::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-166', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 165, '8:b0b52f00ab04e2f30c06b481f966c0e2', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-167::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-167', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 166, '8:5575284da5452401b2e47c18eb0902f7', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-168::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-168', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 167, '8:bbdb87d0a54707d30f5bf9f9adc97d26', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-169::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-169', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 168, '8:d26fc62ce3790fcaad19f7cf2fb75680', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-170::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-170', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 169, '8:6af257b6d03b7ba66ba3a9a53232b2c1', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-171::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-171', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 170, '8:854e6fd5a1ef10081d0e400a71cfc647', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-172::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-172', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 171, '8:ae95af119b33df3d3e4f18b8c533e33b', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-173::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-173', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 172, '8:99a43413a3731d8e4e1921313e4de38f', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-174::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-174', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 173, '8:b3a36c6818e313b2cd98f042899be1fa', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-175::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-175', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 174, '8:4b6bb9827e63afa648168eaedc3e7e13', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-176::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-176', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 175, '8:b1a231a4056d128fee495b0afee58fd4', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-177::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_eingriffsbereich ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-177', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 176, '8:d721186a24b4112effde08f9ee505f35', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-178::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-178', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 177, '8:1c2861f56c740667f68c2c4a1ffcb92c', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-179::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-179', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 178, '8:19a6a6c6bc956fde84dc2f8e04e46b5b', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-180::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenmass ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-180', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 179, '8:e0051cae86014b8563a6043f87fdd3ce', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-181::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-181', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 180, '8:0c4ba3e1c238f85102c6c686da6cd994', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-182::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaft ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-182', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 181, '8:6d768b98ebe44db16f3e11728d734049', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-183::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-183', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 182, '8:f82dc820bb1535987bf0981c71209d2d', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-184::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_veraenderungssperre ADD xplan_laermkontingentgebiet TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-184', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 183, '8:9b3beea4723f3833bbac1a1ce848e4bf', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-185::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaftsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-185', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 184, '8:afca87c1d8353177202eb469b48fdd1a', 'addColumn tableName=xplan_bp_landwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-186::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-186', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 185, '8:5abab1e877ab21dede82443980228752', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-187::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-187', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 186, '8:8a4d8a827279aa12a1adcdf417aac2e2', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-188::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-188', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 187, '8:0f782acd47c6c9c405b905c748282faf', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-189::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-189', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 188, '8:b389ca6de4f1779617c9621d06567e92', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-190::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-190', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 189, '8:7e7acd2fd36d1f3cf71d86dd51686092', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-191::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-191', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 190, '8:1e1374f546a08dde38fe492d17641595', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-192::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-192', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 191, '8:7111f51d257fe7cc1afc2e535f3f82dd', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-193::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-193', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 192, '8:26d6223b3ecd1ff00809822afd7f67aa', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-194::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-194', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 193, '8:587445d9948a06e03412b186ad354dee', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-195::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-195', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 194, '8:858e5623bd30ea81602833b1c67f3c5d', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-196::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-196', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 195, '8:99adbd6bce247b5b2dae566fdaf45a23', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-197::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-197', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 196, '8:6a094662dd0a9fab6fb32cb915c7b9c1', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-198::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-198', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 197, '8:02906286318d0fbfa0caf01d83e28782', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-199::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-199', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 198, '8:af90f349e8e86a51cd1216f58f44d289', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-200::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-200', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 199, '8:7dfd5b7f51eb9622a34377db3930bf2c', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-201::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-201', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 200, '8:7a444cbea05f741db8043f311379120e', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-202::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-202', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 201, '8:1aa1e90c1412a2b20ede791ba97d1271', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-203::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-203', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 202, '8:e08ea73c3719f1323768c18d32065869', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-204::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-204', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 203, '8:7caea53d8424392b4158b301728574b0', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-205::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-205', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 204, '8:d80ef81e9c3f1031f0a0268b7d42f709', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-206::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-206', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 205, '8:df1033c62dcdbd364126a276bde115ab', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-207::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-207', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 206, '8:9cefacade06b3bfb98e65e6ecb5069e5', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-208::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-208', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 207, '8:c293c6ceb55e2bb8023a25b0377ada3c', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-209::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-209', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 208, '8:f1beef8c11092283df74bed125d97b6e', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-210::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-210', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 209, '8:6a6ebb479af241d43af3b337affe341e', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-211::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-211', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 210, '8:7cc59f534185a44d740a0b2ed5489fd6', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-212::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-212', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 211, '8:fc6972b0a6f406f2753e833a19583079', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-213::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-213', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 212, '8:db4e48ba69ec7ea2408e333147df7fbe', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-214::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-214', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 213, '8:b4bfe6a8cba1ce511286043c23e054af', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-215::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-215', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 214, '8:a6f6657761c9184c55d4fd886f5436d6', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-216::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-216', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 215, '8:a195a745468f9501037d647405f131c0', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-217::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-217', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 216, '8:7c34ced35a8c2db5b771084c097c8a1e', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-218::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-218', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 217, '8:a48f45c93a2165c910379f95495506e9', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-219::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-219', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 218, '8:4f30870b87ef821a72097462e2a89c29', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-220::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-220', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 219, '8:dfacbcc9e8f9fb274f6bfd645db203cc', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-221::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-221', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 220, '8:211a8838585c28170cc569accdc0379b', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-222::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-222', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 221, '8:8109288e0a6adc5a1f5e36ce97def640', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-223::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-223', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 222, '8:b417ee41f979ee3aa5ced23e09a8b75d', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-224::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-224', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 223, '8:ffd32d24f752cb03ce1cb3c8fc5e7476', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-225::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-225', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 224, '8:3a28ad373d2bc345bca148b780353255', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-226::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-226', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 225, '8:335d58edfcb771a502acdd741f226b1e', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-227::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-227', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 226, '8:2115d36fa29e491639275e9d5bb2eba3', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-228::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-228', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 227, '8:2561c95edfaaa0f63895ecf8510e26a5', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-229::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-229', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 228, '8:813fb060c24e0b2f37e8fac33e2e5929', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-230::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-230', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 229, '8:e71fde7ecaed27da363d9e268d65bdf0', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-231::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-231', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 230, '8:fac2a04354dcc06451302c54c41eb937', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-232::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-232', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 231, '8:8d2747e2ddc2304e0267322cdd0afa7f', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-233::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abgrabungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-233', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 232, '8:70ab54d5a79be3ad340bdf31ca38c4d7', 'addColumn tableName=xplan_bp_abgrabungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-234::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-234', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 233, '8:cb7b608c929dd4432ca76cb1e196500e', 'addColumn tableName=xplan_bp_abstandsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-235::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_anpflanzungbindungerhaltung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-235', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 234, '8:1aaa8021695f51bf9ba38ca759bcc74c', 'addColumn tableName=xplan_bp_anpflanzungbindungerhaltung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-236::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_aufschuettungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-236', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 235, '8:2eb47c596b56a996b4df9705927354c5', 'addColumn tableName=xplan_bp_aufschuettungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-237::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-237', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 236, '8:a519796018817642a53128f260cbf55a', 'addColumn tableName=xplan_bp_ausgleichsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-238::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ausgleichsmassnahme ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-238', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 237, '8:7738bc1726aa987a53569d5c9c3923ca', 'addColumn tableName=xplan_bp_ausgleichsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-239::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-239', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 238, '8:b8bccd433bb5f44a8f5c655f952ce829', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-240::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugrenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-240', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 239, '8:32bf12a2ed762f26cb5806e7fb42bbee', 'addColumn tableName=xplan_bp_baugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-241::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baulinie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-241', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 240, '8:14cecf963cf04e0ec689577a82e7c735', 'addColumn tableName=xplan_bp_baulinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-242::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-242', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 241, '8:aae5716160a8fb4c5ae0e4e074442757', 'addColumn tableName=xplan_bp_bereichohneeinausfahrtlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-243::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-243', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 242, '8:a19a067375b4069721730b79a54cde9a', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-244::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_bodenschaetzeflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-244', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 243, '8:7cb6b4730a8091f267eb27dec15133f3', 'addColumn tableName=xplan_bp_bodenschaetzeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-245::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtpunkt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-245', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 244, '8:c8aae17922c3e294360b7dba7bf007a7', 'addColumn tableName=xplan_bp_einfahrtpunkt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-246::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_einfahrtsbereichlinie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-246', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 245, '8:5dabf26b21a79d29b27650d10e9b2e3a', 'addColumn tableName=xplan_bp_einfahrtsbereichlinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-247::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_erhaltungsbereichflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-247', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 246, '8:7c96acecbfce51c84098ed7ee5e28181', 'addColumn tableName=xplan_bp_erhaltungsbereichflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-248::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_firstrichtungslinie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-248', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 247, '8:2de131b5f43a6b486b1f0b4e6ba90f9f', 'addColumn tableName=xplan_bp_firstrichtungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-249::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_foerderungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-249', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 248, '8:859500629414411c6193e9638f79bef0', 'addColumn tableName=xplan_bp_foerderungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-250::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_freiflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-250', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 249, '8:c83ca6ebb601577def6b9bb00e8fd3d7', 'addColumn tableName=xplan_bp_freiflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-251::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gebaeudeflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-251', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 250, '8:7f45ca01d749955c6cc5bbd0562a42d1', 'addColumn tableName=xplan_bp_gebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-252::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-252', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 251, '8:97067d4a5934f0884ad425992d1974bd', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-253::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-253', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 252, '8:6e0bef449dabe393c796f2da9d5fb4af', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-254::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_generischesobjekt ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-254', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 253, '8:ba8ceb2b5f2d16cdb25007ffbe5b793b', 'addColumn tableName=xplan_bp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-255::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gewaesserflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-255', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 254, '8:1ee28fce4e496ca3d6a6e0c1a469e3d6', 'addColumn tableName=xplan_bp_gewaesserflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-256::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gruenflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-256', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 255, '8:45b8f5c61ac368292000ca750df2eee9', 'addColumn tableName=xplan_bp_gruenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-257::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-257', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 256, '8:721e3b105a77c9141edf3cfe1f58cc1e', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-258::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-258', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 257, '8:66836efa4a748085528846a69c94fb9d', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-259::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-259', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 258, '8:b6f93cb934e0ccc0ccac602aecb358c0', 'addColumn tableName=xplan_bp_nebenanlagenausschlussflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-260::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nebenanlagenflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-260', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 259, '8:8caec5720044b2eb26d0e9892553c03d', 'addColumn tableName=xplan_bp_nebenanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-261::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nutzungsartengrenze ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-261', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 260, '8:a67f0d7e8d31a9ec117fb8cdb28fee62', 'addColumn tableName=xplan_bp_nutzungsartengrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-262::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_persgruppenbestimmteflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-262', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 261, '8:0b11ffdbf9ed478db4fda3450631d70e', 'addColumn tableName=xplan_bp_persgruppenbestimmteflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-263::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_rekultivierungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-263', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 262, '8:f5cddc4e9d99593be7892de7c2d9200f', 'addColumn tableName=xplan_bp_rekultivierungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-264::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-264', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 263, '8:62230f2b083b8bcb797123227f8d2f26', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-265::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-265', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 264, '8:86594269512b37e25022f346b954c1a5', 'addColumn tableName=xplan_bp_schutzpflegeentwicklungsmassnahme', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-266::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_speziellebauweise ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-266', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 265, '8:e8bc1e677a882d4c3b3ae5fc526867ef', 'addColumn tableName=xplan_bp_speziellebauweise', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-267::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_spielsportanlagenflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-267', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 266, '8:38cd1e3b5f0babbb7238fd744613933f', 'addColumn tableName=xplan_bp_spielsportanlagenflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-268::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenbegrenzungslinie ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-268', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 267, '8:5257ac117d1cd146c741f041e01294f3', 'addColumn tableName=xplan_bp_strassenbegrenzungslinie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-269::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenkoerper ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-269', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 268, '8:8296e1f85e1f9635b31199b7c671d4cc', 'addColumn tableName=xplan_bp_strassenkoerper', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-270::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_strassenverkehrsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-270', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 269, '8:b2fb07ee92344961c2b04958688ca413', 'addColumn tableName=xplan_bp_strassenverkehrsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-271::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_textlichefestsetzungsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-271', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 270, '8:a2037c2db40531c0ca33478acd7c7b8a', 'addColumn tableName=xplan_bp_textlichefestsetzungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-272::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-272', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 271, '8:a84d3f77c7b302fbc4ade7c327609f4e', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-273::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_unverbindlichevormerkung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-273', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 272, '8:9852aca32501d582e2e125bccf4a90d3', 'addColumn tableName=xplan_bp_unverbindlichevormerkung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-274::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verentsorgung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-274', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 273, '8:8f40316f712bcf8da065b99b4ec8eb35', 'addColumn tableName=xplan_bp_verentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-275::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-275', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 274, '8:b7c771cbef7b8fa91287360c4d13e271', 'addColumn tableName=xplan_bp_verkehrsflaechebesondererzweckbestimmung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-276::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-276', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 275, '8:7af5bb6aafa1caf31a8dbf2ed458009f', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-277::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wasserwirtschaftsflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-277', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 276, '8:947cb08aa9d8e5343bb98951b0a05e74', 'addColumn tableName=xplan_bp_wasserwirtschaftsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-278::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_wegerecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-278', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 277, '8:b20e997c9a566fbed659cbcddd0946a9', 'addColumn tableName=xplan_bp_wegerecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-279::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-279', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 278, '8:b2676bb690ff517d099c769fe031a40b', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-280::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_eingriffsbereich ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-280', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 279, '8:29d49ca1ad8b424f2249a6c914a052bd', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-281::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-281', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 280, '8:103a0f3ae9f41a21261d7cbb345ddb5f', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-282::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-282', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 281, '8:48eb1388d3a6db01b3dc0ff3f01bc687', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-283::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenmass ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-283', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 282, '8:8442e7e409a81e08a9871123e556b02a', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-284::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-284', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 283, '8:a9aa83e50f2099326aa2c1ce96d6ace1', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-285::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaft ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-285', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 284, '8:c9e3937c4d90cb2841961d40db243ecd', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-286::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-286', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 285, '8:64a930c70a0d676721042a856890a058', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-287::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_veraenderungssperre ADD xplan_zusatzkontingent TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-287', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 286, '8:5bb6822c5fe855ced3426684ca2ec384', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-288::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-288', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 287, '8:b5a2d20363da9d871a79837ba5b5111b', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-289::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_eingriffsbereich ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-289', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 288, '8:8ae1c6b79b68f3d76473aa6aa02fd499', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-290::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-290', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 289, '8:12d5209c2f019667fb3ef58250be18b2', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-291::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-291', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 290, '8:9ffcb371349323e6b336b7ec05eb1cdc', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-292::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenmass ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-292', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 291, '8:1fb90d778ad4b38b005262cb980eded0', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-293::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-293', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 292, '8:8d2b7653fc134f999f6769afb29d35e6', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-294::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaft ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-294', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 293, '8:4540704a2664e686432eeafae32c5bd3', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-295::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-295', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 294, '8:1a1d8d05f98ca3a704060df37b01ebbe', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-296::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_veraenderungssperre ADD xplan_zusatzkontingentflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-296', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 295, '8:23769865e8ef6af2a215400c7047c08b', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-297::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-297', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 296, '8:c32204b53c4cf34faf9935184459279b', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-298::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_eingriffsbereich ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-298', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 297, '8:b62fb925e743bbe24d5920ea8f380733', 'addColumn tableName=xplan_bp_eingriffsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-299::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_festsetzungnachlandesrecht ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-299', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 298, '8:89e59b9febe7181baff4af8cec6be123', 'addColumn tableName=xplan_bp_festsetzungnachlandesrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-300::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-300', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 299, '8:aff42779763be918bc84e7002b69c9e3', 'addColumn tableName=xplan_bp_gemeinschaftsanlagenzuordnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-301::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_hoehenmass ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-301', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 300, '8:d6395ccd1e3cf331530836c2619794b6', 'addColumn tableName=xplan_bp_hoehenmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-302::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kleintierhaltungflaeche ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-302', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 301, '8:1fb3a1d2497a28a7adbc688d9e8e942f', 'addColumn tableName=xplan_bp_kleintierhaltungflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-303::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_landwirtschaft ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-303', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 302, '8:765ca2318d38c87ac67bbb8fe06dbd0a', 'addColumn tableName=xplan_bp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-304::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_regelungvergnuegungsstaetten ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-304', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 303, '8:e6c6e379aea907cefda0c30ded9ec528', 'addColumn tableName=xplan_bp_regelungvergnuegungsstaetten', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-305::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_veraenderungssperre ADD xplan_richtungssektorgrenze TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-305', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 304, '8:4b0ce3af6904769dc5758a6f6ae9833b', 'addColumn tableName=xplan_bp_veraenderungssperre', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-306::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_planungsraum ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-306', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 305, '8:1b66de911cdf174d1c02c51840ac96e8', 'addColumn tableName=xplan_rp_planungsraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-307::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_freiraum ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-307', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 306, '8:6e8252152e00eb028a503a6adafe1d4b', 'addColumn tableName=xplan_rp_freiraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-308::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_laermschutzbauschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-308', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 307, '8:901e8d0f19c2f41c631aed1e471938b8', 'addColumn tableName=xplan_rp_laermschutzbauschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-309::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_siedlung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-309', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 308, '8:3917bc031b4c1955275fa19d19400d4c', 'addColumn tableName=xplan_rp_siedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-310::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigerfreiraumschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-310', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 309, '8:40f850be36f5a04ceab505bf9548eaeb', 'addColumn tableName=xplan_rp_sonstigerfreiraumschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-311::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigersiedlungsbereich ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-311', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 310, '8:c2993fafcfee17177e16fa35004db6d4', 'addColumn tableName=xplan_rp_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-312::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_planungsraum ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-312', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 311, '8:9418dafc8549fe99a52e3c2c12b72eef', 'addColumn tableName=xplan_rp_planungsraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-313::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_nutzung TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-313', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 312, '8:902ba84b813765f44e6bb835e1d8245a', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-314::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_funktionszuweisung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-314', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 313, '8:214590e9fd6820a6ae589bb16f35b27a', 'addColumn tableName=xplan_rp_funktionszuweisung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-315::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_freiraum ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-315', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 314, '8:7bc87de47af02f14f3ecc48adbd8402a', 'addColumn tableName=xplan_rp_freiraum', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-316::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_laermschutzbauschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-316', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 315, '8:03bdb5b19ba6c556b36b8b549d18262e', 'addColumn tableName=xplan_rp_laermschutzbauschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-317::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_siedlung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-317', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 316, '8:a2bf433553cefb3bf89f2648c6b345f3', 'addColumn tableName=xplan_rp_siedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-318::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigerfreiraumschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-318', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 317, '8:2d2d50a0583c99195c83631b48e2d33a', 'addColumn tableName=xplan_rp_sonstigerfreiraumschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-319::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigersiedlungsbereich ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-319', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 318, '8:581c67fd3cc93b4bca708f18a70a176b', 'addColumn tableName=xplan_rp_sonstigersiedlungsbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-320::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_nichtueberbaubaregrundstuecksflaeche ADD xplan_nutzungcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-320', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 319, '8:76196230eafbc28bbe0cdad648cc3b12', 'addColumn tableName=xplan_bp_nichtueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-321::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_einzelhandel ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-321', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 320, '8:2119d2f393a58ce90bd223d6d85dea5e', 'addColumn tableName=xplan_rp_einzelhandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-322::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_erneuerbareenergie ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-322', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 321, '8:381c275fcc45800a4f43244f79178fd7', 'addColumn tableName=xplan_rp_erneuerbareenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-323::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_hochwasserschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-323', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 322, '8:b9bec5c932d4ca26e1028aec574925be', 'addColumn tableName=xplan_rp_hochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-324::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_industriegewerbe ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-324', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 323, '8:750f4f38e6317b58dc7d15e5f1bfcb1f', 'addColumn tableName=xplan_rp_industriegewerbe', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-325::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_kulturlandschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-325', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 324, '8:9c0084bf1d3a9c153bcdf7c591421891', 'addColumn tableName=xplan_rp_kulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-326::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_radwegwanderweg ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-326', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 325, '8:ad79573278aeef45ce0a3c812526465c', 'addColumn tableName=xplan_rp_radwegwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-327::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigeinfrastruktur ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-327', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 326, '8:038a3547895fb4721dce4974b5a2d70d', 'addColumn tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-328::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sportanlage ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-328', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 327, '8:a01545bceecc9d735aa61e5147ba5f73', 'addColumn tableName=xplan_rp_sportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-329::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wohnensiedlung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-329', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 328, '8:01f9c9d53b4085777834516f9ad9fd51', 'addColumn tableName=xplan_rp_wohnensiedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-330::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_funktionszuweisung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-330', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 329, '8:026cd03956093ac6fcde491907a43e7a', 'addColumn tableName=xplan_rp_funktionszuweisung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-331::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_plan ADD xplan_versionbaugbdatum date;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-331', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 330, '8:a5042eabbe9dcfd29bc4562f341c1d77', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-332::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_einzelhandel ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-332', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 331, '8:b967d895b4885415613bc870b92d574d', 'addColumn tableName=xplan_rp_einzelhandel', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-333::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_erneuerbareenergie ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-333', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 332, '8:1d16fc45040a0000723487d8604b8436', 'addColumn tableName=xplan_rp_erneuerbareenergie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-334::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_hochwasserschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-334', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 333, '8:94db9cec880ed6a2ee62258be7093de5', 'addColumn tableName=xplan_rp_hochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-335::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_industriegewerbe ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-335', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 334, '8:09f8b9ab1607a37e4b457bf11b07ea86', 'addColumn tableName=xplan_rp_industriegewerbe', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-336::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_kulturlandschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-336', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 335, '8:e48f5642216af96c5b574932be7dc2be', 'addColumn tableName=xplan_rp_kulturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-337::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_radwegwanderweg ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-337', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 336, '8:fa233a46be79ff1891e822abcc9f11c9', 'addColumn tableName=xplan_rp_radwegwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-338::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sonstigeinfrastruktur ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-338', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 337, '8:617aceb0f3f97453f9efad78b2592807', 'addColumn tableName=xplan_rp_sonstigeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-339::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sportanlage ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-339', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 338, '8:02ab8c8549c2d2c3f9b58378f6be27e7', 'addColumn tableName=xplan_rp_sportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-340::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wohnensiedlung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-340', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 339, '8:c127eeb802b8be3d6ff611c22c611e3f', 'addColumn tableName=xplan_rp_wohnensiedlung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-341::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_plan ADD xplan_versionbaugbtext TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-341', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 340, '8:0c36e060a371a3c2cff2304d2643a7f9', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-342::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_kommunikation ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-342', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 341, '8:907aeed9b9954cccc0acab0f0154a963', 'addColumn tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-343::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sozialeinfrastruktur ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-343', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 342, '8:71739b031f4c8be888e09b3c7eca20b1', 'addColumn tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-344::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserwirtschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-344', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 343, '8:fe2e55c62784dd54ad8200b4618caff1', 'addColumn tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-345::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_plan ADD xplan_versionsonstrechtsgrundlagedatum date;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-345', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 344, '8:4ad8bf64dff1c79548760850893db66d', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-346::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_funktion TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-346', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 345, '8:dd32486ba60e2981749d7541a3e77d0c', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-347::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gewaesser ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-347', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 346, '8:8a18bd283a76262e9f1aee23ed929165', 'addColumn tableName=xplan_rp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-348::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_kommunikation ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-348', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 347, '8:da3797000af0e529e75d6550ccf250e1', 'addColumn tableName=xplan_rp_kommunikation', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-349::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sozialeinfrastruktur ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-349', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 348, '8:d7b2fd0726c924694ebb333111f322c3', 'addColumn tableName=xplan_rp_sozialeinfrastruktur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-350::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserwirtschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-350', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 349, '8:af584d9f0ebcb857a336fdf6841fec39', 'addColumn tableName=xplan_rp_wasserwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-351::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_plan ADD xplan_versionsonstrechtsgrundlagetext TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-351', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 350, '8:a137aa3e696a0d0158529f96b39fb39e', 'addColumn tableName=xplan_so_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-352::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_funktioncode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-352', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 351, '8:57ed3a59c218615083aea84f480d1e57', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-353::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_erholung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-353', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 352, '8:bd594d58b31bf85835426f064f22abbe', 'addColumn tableName=xplan_rp_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-354::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_generischesobjekt ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-354', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 353, '8:f664cb3b01036b91dc69366bca70a35a', 'addColumn tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-355::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gruenzuggruenzaesur ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-355', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 354, '8:88c6c27449ec9db7089fdcd4dcdbec5b', 'addColumn tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-356::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_klimaschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-356', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 355, '8:0aca7ce88809d424d1f8046813ce4711', 'addColumn tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-357::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturlandschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-357', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 356, '8:f95c4d4044e8d04c4d3cac0c8404b3e0', 'addColumn tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-358::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sperrgebiet ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-358', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 357, '8:68d8f3bb598ef6ec7dd7e6b06ae1277d', 'addColumn tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-359::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gewaesser ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-359', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 358, '8:0715742f4ed5a5c210ec83406f617589', 'addColumn tableName=xplan_rp_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-360::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_betreten TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-360', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 359, '8:9357db4a4800c59b8bd5c46b2872bb26', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-361::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_erholung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-361', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 360, '8:f678a14110f829cbd6e11947436dcf9c', 'addColumn tableName=xplan_rp_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-362::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_generischesobjekt ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-362', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 361, '8:909ba96bb1738c5eac29d1d15d64dccb', 'addColumn tableName=xplan_rp_generischesobjekt', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-363::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_gruenzuggruenzaesur ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-363', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 362, '8:c681ab9d481dedf461fa18cdf35ae9a0', 'addColumn tableName=xplan_rp_gruenzuggruenzaesur', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-364::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_klimaschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-364', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 363, '8:cf96a27e210b5ca4afe4a1477f688b5b', 'addColumn tableName=xplan_rp_klimaschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-365::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturlandschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-365', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 364, '8:91329f55ba01cf343b282ce35fafaddc', 'addColumn tableName=xplan_rp_naturlandschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-366::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sperrgebiet ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-366', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 365, '8:9e2a8cae4a54c0e2329e8135bb40dcf8', 'addColumn tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-367::lyn (generated)
ALTER TABLE xplansynarchive.xplan_so_forstrecht ADD xplan_betretencode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-367', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 366, '8:04851ad9072bba407f670782e5e52e56', 'addColumn tableName=xplan_so_forstrecht', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-368::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_achse ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-368', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 367, '8:a0ec7a325c602d508d40446e986cec52', 'addColumn tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-369::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_bodenschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-369', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 368, '8:a7a336a88e04733c62811f460be8ec37', 'addColumn tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-370::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_forstwirtschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-370', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 369, '8:cf13370c5138b25ef8ee334f23999a6d', 'addColumn tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-371::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_grenze ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-371', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 370, '8:3334d0082b51175af07fecc027101a91', 'addColumn tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-372::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_landwirtschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-372', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 371, '8:83a97e0405de1be3933c8ac9474f21db', 'addColumn tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-373::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_raumkategorie ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-373', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 372, '8:5b4b55cfefa89b40cc9f222d0e5ed0b2', 'addColumn tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-374::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_verkehr ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-374', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 373, '8:e0fa86de96678adde941e94b6a04caae', 'addColumn tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-375::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_achse ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-375', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 374, '8:9d87e06f5e151155ac6acd8e890ea65c', 'addColumn tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-376::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_bodenschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-376', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 375, '8:901fb1628cc41a31796671b7578b6eb8', 'addColumn tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-377::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_forstwirtschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-377', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 376, '8:3fc8d9e14567e72e8f32e206d26fc3a8', 'addColumn tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-378::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_grenze ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-378', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 377, '8:6ebc918f2698c035aa5d2349a85db080', 'addColumn tableName=xplan_rp_grenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-379::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_landwirtschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-379', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 378, '8:c8362344645ce20bb0a0b053052b8305', 'addColumn tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-380::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_raumkategorie ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-380', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 379, '8:c8b974854b5177e267a96406a11c341e', 'addColumn tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-381::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-381', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 380, '8:c2175dcbc1e2c4e3e481203af91afaf0', 'addColumn tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-382::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_zentralerort ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-382', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 381, '8:a7bc00aae3077de51d37a3828b8c2d0a', 'addColumn tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-383::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_verkehr ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-383', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 382, '8:12bdc550687972069031e29780bc7af8', 'addColumn tableName=xplan_rp_verkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-384::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_entsorgung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-384', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 383, '8:fecc485d4e7581865bb6fc6b37fc2a7b', 'addColumn tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-385::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-385', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 384, '8:cc7d0fe947ef6817ccbaac1c2c0eddd0', 'addColumn tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-386::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-386', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 385, '8:b35fbed426abb74e09c0704c8ddba52f', 'addColumn tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-387::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_zentralerort ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-387', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 386, '8:6308c7aad4afedf2b8fa5ed2002acc21', 'addColumn tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-388::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-388', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 387, '8:6ee8973dc4b5f2753a8f7a9ef751ead0', 'addColumn tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-389::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_entsorgung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-389', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 388, '8:ab9fda8d9b1c08fd4d4724dbde4b7733', 'addColumn tableName=xplan_rp_entsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-390::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-390', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 389, '8:b17236a4b417353ba04f88e03b0200de', 'addColumn tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-391::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-391', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 390, '8:342df16b15f839cc764bdef9edbba038', 'addColumn tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-392::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_typ TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-392', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 391, '8:21c88b0ae8aeae5fc6ed53e2f39ca75a', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-393::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_kennzeichnung ADD xplan_istverdachtsflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-393', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 392, '8:521148249aa9a53599e1702a51ad4acd', 'addColumn tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-394::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_rohstoff ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-394', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 393, '8:051249147583ed24e045bde936629b5c', 'addColumn tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-395::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_abstandsmass ADD xplan_typcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-395', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 394, '8:5345a89b45113826202769e7f770cd8e', 'addColumn tableName=xplan_bp_abstandsmass', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-396::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_rohstoff ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-396', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 395, '8:c250a68a84484708a6ecd6c656ba58b9', 'addColumn tableName=xplan_rp_rohstoff', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-397::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_kennzeichnung ADD xplan_nummer TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-397', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 396, '8:d228c5618a1446bf52ba26d902c2651b', 'addColumn tableName=xplan_fp_kennzeichnung', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-398::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_waldflaeche ADD xplan_eigentumsart TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-398', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 397, '8:efabe143634a6a09013fc7da3e3aab8e', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-399::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_waldflaeche ADD xplan_eigentumsartcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-399', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 398, '8:1a8f1476b916ca03b9f8ca8d7b93551f', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-400::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_waldflaeche ADD xplan_betreten TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-400', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 399, '8:d5743c78c596356fb271ff82a2308dab', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-401::lyn (generated)
ALTER TABLE xplansynarchive.xplan_fp_waldflaeche ADD xplan_betretencode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-401', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 400, '8:23c12a9febc96508d61e3adb30cdbf39', 'addColumn tableName=xplan_fp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-402::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_istverdachtsflaeche TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-402', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 401, '8:5f21faca96b0f1024a550f4c5718eeec', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-403::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_technvorkehrung TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-403', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 402, '8:02c443d6cfa50595a6f7c36e2cb8ea15', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-404::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_kennzeichnungsflaeche ADD xplan_nummer TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-404', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 403, '8:f50f88bac761b1de64fd0741a56b8144', 'addColumn tableName=xplan_bp_kennzeichnungsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-405::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_technvorkehrungcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-405', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 404, '8:d0b75e23a5407cd57861fddede818768', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-406::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_detailliertetechnvorkehrung TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-406', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 405, '8:8f82e9e038dbacaa4f3c453416d83b2f', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-407::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_detailliertetechnvorkehrungcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-407', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 406, '8:394e24eb75cf21c3d301463a9ae5def8', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-408::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_typ TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-408', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 407, '8:fa3a5cccf8e51eb8d292272ad6bb1e3c', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-409::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_immissionsschutz ADD xplan_typcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-409', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 408, '8:987afaec5f8e88fe9d8abe038f5e6256', 'addColumn tableName=xplan_bp_immissionsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-410::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_eigentumsart TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-410', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 409, '8:9ce5bc4b0fdf8a03c1a7a9fdedd33799', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-411::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_eigentumsartcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-411', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 410, '8:752ce28c7b876bf366f95c238eddffc9', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-412::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_betreten TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-412', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 411, '8:2bb5fbb1392a3dc5bba6a8af1949957a', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-413::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_waldflaeche ADD xplan_betretencode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-413', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 412, '8:a38fce96de7dd580f911007e3dfb778d', 'addColumn tableName=xplan_bp_waldflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-414::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_bauweise TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-414', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 413, '8:7595b5d842923efab42b89ffd6b4b54a', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-415::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_bauweisecode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-415', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 414, '8:f1698fa2113590d46f2c8e00d39f0266', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-416::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_bebauungsart TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-416', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 415, '8:5669d43ab0218607c1a1d3a01e6d2331', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-417::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_bebauungsartcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-417', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 416, '8:19de67269652a3dfbc21b34d92c8adc1', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-418::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_abweichendebauweise TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-418', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 417, '8:bca28174600d6db250f267b74aa47832', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-419::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_besonderernutzungszweckflaeche ADD xplan_abweichendebauweisecode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-419', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 418, '8:b5ce1dea4cce1c2ffa4db6a451ae1310', 'addColumn tableName=xplan_bp_besonderernutzungszweckflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-420::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnmin numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-420', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 419, '8:002392c2979bdfb006a64499001b8fde', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-421::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnminuom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-421', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 420, '8:e7c9e7979a072e4496c14c5af025a15c', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-422::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnmax numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-422', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 421, '8:a03ee3e6ab6d6e6eaf648c95b27f5836', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-423::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnmaxuom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-423', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 422, '8:d443df0597c61f6d4a974d2a0cc1f50a', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-424::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dn numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-424', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 423, '8:ef8170f83e3f03497d93a23297efcbb6', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-425::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnuom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-425', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 424, '8:c3ce53404838446586b2bf16628913d3', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-426::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnzwingend numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-426', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 425, '8:2b7b54abf0d26acb53244f5df02a3866', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-427::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dnzwingenduom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-427', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 426, '8:c5a102c651302763a44089fa4f3b0d2b', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-428::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_fr numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-428', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 427, '8:61b0869a29efdc4e6c895a0c3670a250', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-429::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_fruom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-429', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 428, '8:776e2e92f50a5e7b70b843e3206c1029', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-430::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dachform TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-430', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 429, '8:036bd1c727f5a48cf823a3d40a7e0488', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-431::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dachformcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-431', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 430, '8:8826bc039693029b7a0ed780253623e7', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-432::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_detailliertedachform TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-432', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 431, '8:14d880f102019ea05c1c72369225769a', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-433::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_detailliertedachformcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-433', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 432, '8:b0c96e93b8a7290138f86d626ddc339f', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-434::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_bauweise TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-434', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 433, '8:e3cfe9bfbd9daf9661cba0225af285d4', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-435::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_vf numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-435', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 434, '8:b19f327d39256235fb0b73de5fbbe4bf', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-436::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_bauweisecode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-436', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 435, '8:c1163159d0af084e85a4bde8eddc1ba4', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-437::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_vfuom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-437', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 436, '8:e9f79815592b544fdf2e279b459dfc1b', 'addColumn tableName=xplan_bp_ueberbaubaregrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-438::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_abweichendebauweise TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-438', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 437, '8:360d18e0a98a819f78eb31569392b4c7', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-439::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_abweichendebauweisecode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-439', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 438, '8:a47f618a996a1deb17fc0470d0305c74', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-440::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_bebauungsart TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-440', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 439, '8:ea62f01c1e9b1abc056d604344cec649', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-441::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_bebauungsartcode TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-441', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 440, '8:a87152a8a95b9319edfeab1684eb8203', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-442::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_gemeinbedarfsflaeche ADD xplan_dachgestaltung TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-442', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 441, '8:22ab13ee4d250b9c1c7573c31c4b075e', 'addColumn tableName=xplan_bp_gemeinbedarfsflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-443::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_vf numeric;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-443', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 442, '8:115eeca76f55550b1ce2fe17ec3467da', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-444::lyn (generated)
ALTER TABLE xplansynarchive.xplan_bp_baugebietsteilflaeche ADD xplan_vfuom TEXT;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-444', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 443, '8:3be376c0b5354afc9cacabe5e71c0b51', 'addColumn tableName=xplan_bp_baugebietsteilflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-445::lyn (generated)
CREATE INDEX spatial_idx_493 ON xplansynarchive.xplan_bp_abweichungvonbaugrenze(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-445', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 444, '8:48967d23451c46a303fd30cd1deb6482', 'createIndex indexName=spatial_idx_493, tableName=xplan_bp_abweichungvonbaugrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-446::lyn (generated)
CREATE INDEX spatial_idx_494 ON xplansynarchive.xplan_bp_abweichungvonueberbauberergrundstuecksflaeche(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-446', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 445, '8:c24ab9705055917c8b857242eb09bdfd', 'createIndex indexName=spatial_idx_494, tableName=xplan_bp_abweichungvonueberbauberergrundstuecksflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-447::lyn (generated)
CREATE INDEX spatial_idx_495 ON xplansynarchive.xplan_bp_richtungssektorgrenze(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-447', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 446, '8:3352c55599a8c7488e02b9e6a68b0a68', 'createIndex indexName=spatial_idx_495, tableName=xplan_bp_richtungssektorgrenze', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-448::lyn (generated)
CREATE INDEX spatial_idx_496 ON xplansynarchive.xplan_bp_zusatzkontingentlaermflaeche(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-448', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 447, '8:4f5fd6b46be486aac2f27aaaff57c2d9', 'createIndex indexName=spatial_idx_496, tableName=xplan_bp_zusatzkontingentlaermflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-449::lyn (generated)
CREATE INDEX spatial_idx_497 ON xplansynarchive.xplan_bp_zusatzkontingentlaerm(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-449', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 448, '8:5248585b9aaba833668659d63c2d1677', 'createIndex indexName=spatial_idx_497, tableName=xplan_bp_zusatzkontingentlaerm', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-450::lyn (generated)
CREATE INDEX spatial_idx_498 ON xplansynarchive.xplan_bp_sichtflaeche(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-450', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 449, '8:1bb1e6dc6656ec52b002a37a430445c9', 'createIndex indexName=spatial_idx_498, tableName=xplan_bp_sichtflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-451::lyn (generated)
CREATE INDEX spatial_idx_499 ON xplansynarchive.xplan_so_bauverbotszone(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-451', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 450, '8:80c5adadba4a89b60f6369f15c771fca', 'createIndex indexName=spatial_idx_499, tableName=xplan_so_bauverbotszone', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-452::lyn (generated)
CREATE INDEX spatial_idx_500 ON xplansynarchive.xplan_so_gewaesser(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-452', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 451, '8:5bd8e54eed050aeb45632b9849efd4e8', 'createIndex indexName=spatial_idx_500, tableName=xplan_so_gewaesser', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Changeset changelog_xplansynarchive.xml::1581343261688-1::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_plan ALTER COLUMN xplan_amtlicherschluessel TYPE TEXT USING (xplan_amtlicherschluessel::TEXT);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1581343261688-1', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 452, '8:fe3376e26b69a95dca64f029b149279e', 'modifyDataType columnName=xplan_amtlicherschluessel, tableName=xplan_rp_plan', '', 'EXECUTED', NULL, NULL, '3.6.2', '1343288179');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

