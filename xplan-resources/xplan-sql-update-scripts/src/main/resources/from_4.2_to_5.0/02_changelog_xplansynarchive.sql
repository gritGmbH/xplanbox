-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 18.03.22, 07:39
-- Against: postgres@jdbc:postgresql://localhost:5439/xplanbox
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansynarchive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynarchive.databasechangeloglock;

INSERT INTO xplansynarchive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2022-03-18 07:39:18.406' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynarchive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansynarchive.xml::1647585522758-1::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_wohngebaeudeflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_flaechenschluss TEXT, xplan_dnmin numeric, xplan_dnminuom TEXT, xplan_dnmax numeric, xplan_dnmaxuom TEXT, xplan_dn numeric, xplan_dnuom TEXT, xplan_dnzwingend numeric, xplan_dnzwingenduom TEXT, xplan_fr numeric, xplan_fruom TEXT, xplan_dachform TEXT, xplan_dachformcode TEXT, xplan_detailliertedachform TEXT, xplan_detailliertedachformcode TEXT, xplan_maxzahlwohnungen INTEGER, xplan_fmin numeric, xplan_fminuom TEXT, xplan_fmax numeric, xplan_fmaxuom TEXT, xplan_bmin numeric, xplan_bminuom TEXT, xplan_bmax numeric, xplan_bmaxuom TEXT, xplan_tmin numeric, xplan_tminuom TEXT, xplan_tmax numeric, xplan_tmaxuom TEXT, xplan_gfzmin numeric, xplan_gfzmax numeric, xplan_gfz numeric, xplan_gfz_ausn numeric, xplan_gfmin numeric, xplan_gfminuom TEXT, xplan_gfmax numeric, xplan_gfmaxuom TEXT, xplan_gf numeric, xplan_gfuom TEXT, xplan_gf_ausn numeric, xplan_gf_ausnuom TEXT, xplan_bmz numeric, xplan_bmz_ausn numeric, xplan_bmmin numeric, xplan_bmminuom TEXT, xplan_bmmax numeric, xplan_bmmaxuom TEXT, xplan_bm numeric, xplan_bmuom TEXT, xplan_bm_ausn numeric, xplan_bm_ausnuom TEXT, xplan_grzmin numeric, xplan_grzmax numeric, xplan_grz numeric, xplan_grz_ausn numeric, xplan_grmin numeric, xplan_grminuom TEXT, xplan_grmax numeric, xplan_grmaxuom TEXT, xplan_gr numeric, xplan_gruom TEXT, xplan_gr_ausn numeric, xplan_gr_ausnuom TEXT, xplan_zmin INTEGER, xplan_zmax INTEGER, xplan_zzwingend INTEGER, xplan_z INTEGER, xplan_z_ausn INTEGER, xplan_abweichungbaunvo TEXT, xplan_abweichungbaunvocode TEXT, xplan_bauweise TEXT, xplan_bauweisecode TEXT, xplan_abweichendebauweise TEXT, xplan_abweichendebauweisecode TEXT, xplan_vertikaledifferenzierung TEXT, xplan_bebauungsart TEXT, xplan_bebauungsartcode TEXT, xplan_bebauungvorderegrenze TEXT, xplan_bebauungvorderegrenzecode TEXT, xplan_bebauungrueckwaertigegrenze TEXT, xplan_bebauungrueckwaertigegrenzecode TEXT, xplan_bebauungseitlichegrenze TEXT, xplan_bebauungseitlichegrenzecode TEXT, xplan_abweichungtext TEXT, xplan_refgebaeudequerschnitt TEXT, xplan_artderbaulichennutzung TEXT, xplan_artderbaulichennutzungcode TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_zumin INTEGER, xplan_zumax INTEGER, xplan_zuzwingend INTEGER, xplan_zu INTEGER, xplan_zu_ausn INTEGER, xplan_zugunstenvon TEXT, xplan_z_staffel INTEGER, xplan_wohnnutzungwgstrasse TEXT, xplan_wohnnutzungwgstrassecode TEXT, xplan_zwohn INTEGER, xplan_gfantwohnen numeric, xplan_gfantwohnenuom TEXT, xplan_gfwohnen numeric, xplan_gfwohnenuom TEXT, xplan_gfantgewerbe numeric, xplan_gfantgewerbeuom TEXT, xplan_gfgewerbe numeric, xplan_gfgewerbeuom TEXT, xplan_dachgestaltung TEXT, xplan_vf numeric, xplan_vfuom TEXT, xplan_mingrwohneinheit numeric, xplan_mingrwohneinheituom TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_wohngebaeudeflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-1', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 1, '8:30bf948577b95712edab24e1a29c756b', 'createTable tableName=xplan_bp_wohngebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-2::lyn (generated)
CREATE INDEX spatial_idx_501 ON xplansynarchive.xplan_bp_wohngebaeudeflaeche(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-2', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 2, '8:fbe0ad3fea9bc16386eb3c77140e3501', 'createIndex indexName=spatial_idx_501, tableName=xplan_bp_wohngebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-3::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abfallentsorgung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-3', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 3, '8:103bc53a760a13a59659ec4d85a8a24a', 'dropTable tableName=xplan_rp_nsm_abfallentsorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-4::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_abwasser;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-4', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 4, '8:52d59f5520189f52dabb14051e85306a', 'dropTable tableName=xplan_rp_nsm_abwasser', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-5::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_asb;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-5', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 5, '8:c47d2e6186659a558b49aa5ee93878d1', 'dropTable tableName=xplan_rp_nsm_asb', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-6::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_erholung;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-6', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 6, '8:69acb52bbb05a99b2b3416423b63aef6', 'dropTable tableName=xplan_rp_nsm_erholung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-7::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_gib;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-7', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 7, '8:061635c1e42ce7d7338ed62bb59b6eba', 'dropTable tableName=xplan_rp_nsm_gib', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-8::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_landschaftsschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-8', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 8, '8:b73bcfd9956981b6266da0ae841fd339', 'dropTable tableName=xplan_rp_nsm_landschaftsschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-9::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_luftverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-9', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 9, '8:069cf70fa21b80fe05ad11f42e53a7d2', 'dropTable tableName=xplan_rp_nsm_luftverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-10::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_naturschutz;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-10', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 10, '8:5480e0c799a6c36e15699318763931b0', 'dropTable tableName=xplan_rp_nsm_naturschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-11::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_oberflaechennahebodenschaetze;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-11', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 11, '8:9be219631dd0ca29dad12077f361fa06', 'dropTable tableName=xplan_rp_nsm_oberflaechennahebodenschaetze', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-12::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamerwanderweg;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-12', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 12, '8:dcb2a584d504d65851c08711eed7f271', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamerwanderweg', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-13::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_regionalbedeutsamesportanlage;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-13', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 13, '8:8dfc2d1e5726cfc66ec697016ce0319a', 'dropTable tableName=xplan_rp_nsm_regionalbedeutsamesportanlage', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-14::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_schienenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-14', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 14, '8:ed06c12ef431032109746bdbabd2885d', 'dropTable tableName=xplan_rp_nsm_schienenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-15::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_sonstverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-15', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 15, '8:f2249dae95e83d119c3e68fa2b55c80f', 'dropTable tableName=xplan_rp_nsm_sonstverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-16::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_strassenverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-16', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 16, '8:1d89e709f880e0e036e9002fcda9ea4f', 'dropTable tableName=xplan_rp_nsm_strassenverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-17::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tiefliegenderohstoffe;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-17', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 17, '8:da29743a17e5627350403390f9db400e', 'dropTable tableName=xplan_rp_nsm_tiefliegenderohstoffe', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-18::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_tourismus;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-18', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 18, '8:7efccb22e7aee950ba4790b34110a298', 'dropTable tableName=xplan_rp_nsm_tourismus', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-19::lyn (generated)
DROP TABLE xplansynarchive.xplan_rp_nsm_wasserverkehr;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-19', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 19, '8:20766ab07e5c763f9bb1c6aab4c64233', 'dropTable tableName=xplan_rp_nsm_wasserverkehr', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-20::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet DROP COLUMN xplan_besonderezweckbestimmungnsg_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-20', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 20, '8:0ac19cc0e272475d1eb16c044afa4975', 'dropColumn columnName=xplan_besonderezweckbestimmungnsg_nsm, tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-21::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_naturschutzrechtlichesschutzgebiet DROP COLUMN xplan_besonderezweckbestimmungnsg_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-21', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 21, '8:f66fd1a411981b1cc6931aaf04710635', 'dropColumn columnName=xplan_besonderezweckbestimmungnsg_nsmcode, tableName=xplan_rp_naturschutzrechtlichesschutzgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-22::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_zentralerort DROP COLUMN xplan_detailfunktion_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-22', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 22, '8:983b6b2704f977235542155cab2305b5', 'dropColumn columnName=xplan_detailfunktion_nsm, tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-23::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_zentralerort DROP COLUMN xplan_detailfunktion_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-23', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 23, '8:44c1f5750aa5b3a9d705d2057582516d', 'dropColumn columnName=xplan_detailfunktion_nsmcode, tableName=xplan_rp_zentralerort', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-24::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung DROP COLUMN xplan_detailtyp_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-24', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 24, '8:62f8230012d988eb3d91197696d10689', 'dropColumn columnName=xplan_detailtyp_nsm, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-25::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung DROP COLUMN xplan_detailtyp_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-25', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 25, '8:4f5fd59ec77cdc601eb6a52d47030b98', 'dropColumn columnName=xplan_detailtyp_nsmcode, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-26::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sperrgebiet DROP COLUMN xplan_sperrgebiettyp_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-26', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 26, '8:ad0642b67dfe53e49b63d9111dc1bfb8', 'dropColumn columnName=xplan_sperrgebiettyp_nsm, tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-27::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_sperrgebiet DROP COLUMN xplan_sperrgebiettyp_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-27', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 27, '8:01b1b2828aa55e642b4f74d0d8af1619', 'dropColumn columnName=xplan_sperrgebiettyp_nsmcode, tableName=xplan_rp_sperrgebiet', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-28::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung DROP COLUMN xplan_status_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-28', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 28, '8:24605dc04c0382bde74df96299cac2e1', 'dropColumn columnName=xplan_status_nsm, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-29::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_energieversorgung DROP COLUMN xplan_status_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-29', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 29, '8:21b4ea278c2ebdedac7773382510bfac', 'dropColumn columnName=xplan_status_nsmcode, tableName=xplan_rp_energieversorgung', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-30::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_laermschutzbereich DROP COLUMN xplan_typls_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-30', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 30, '8:5173c1462d233f17d32110fabad92c52', 'dropColumn columnName=xplan_typls_nsm, tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-31::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_laermschutzbereich DROP COLUMN xplan_typls_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-31', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 31, '8:4ea7e927b9c185e4fd50c7398053cced', 'dropColumn columnName=xplan_typls_nsmcode, tableName=xplan_rp_laermschutzbereich', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-32::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_landwirtschaft DROP COLUMN xplan_typlw_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-32', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 32, '8:73c520b1596e193e9c0b47d06866fc7f', 'dropColumn columnName=xplan_typlw_nsm, tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-33::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_landwirtschaft DROP COLUMN xplan_typlw_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-33', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 33, '8:ed4bf373495583af2d4c8be97a713c70', 'dropColumn columnName=xplan_typlw_nsmcode, tableName=xplan_rp_landwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-34::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_raumkategorie DROP COLUMN xplan_typrk_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-34', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 34, '8:6acb198b695d2e5dc187222ef8209556', 'dropColumn columnName=xplan_typrk_nsm, tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-35::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_raumkategorie DROP COLUMN xplan_typrk_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-35', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 35, '8:06eda07c1efb19facbeea90297209659', 'dropColumn columnName=xplan_typrk_nsmcode, tableName=xplan_rp_raumkategorie', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-36::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_achse DROP COLUMN xplan_weitererachsentyp_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-36', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 36, '8:05633d4c55a741efb06fba9e712b6c31', 'dropColumn columnName=xplan_weitererachsentyp_nsm, tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-37::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_achse DROP COLUMN xplan_weitererachsentyp_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-37', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 37, '8:4c007fcb1bd2e6310f8131f201797e74', 'dropColumn columnName=xplan_weitererachsentyp_nsmcode, tableName=xplan_rp_achse', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-38::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_bodenschutz DROP COLUMN xplan_zweckbestimmungbs_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-38', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 38, '8:25f838411d9a40c534412ba929d9b48c', 'dropColumn columnName=xplan_zweckbestimmungbs_nsm, tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-39::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_bodenschutz DROP COLUMN xplan_zweckbestimmungbs_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-39', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 39, '8:bfd931bb857b4a1a0e8e1c847c09d1ba', 'dropColumn columnName=xplan_zweckbestimmungbs_nsmcode, tableName=xplan_rp_bodenschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-40::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_forstwirtschaft DROP COLUMN xplan_zweckbestimmungfw_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-40', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 40, '8:adf1203bc0e0859f9a6444c00d1b600c', 'dropColumn columnName=xplan_zweckbestimmungfw_nsm, tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-41::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_forstwirtschaft DROP COLUMN xplan_zweckbestimmungfw_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-41', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 41, '8:fe6ee9efe2da7a7a9904ae35b489acba', 'dropColumn columnName=xplan_zweckbestimmungfw_nsmcode, tableName=xplan_rp_forstwirtschaft', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-42::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_vorbhochwasserschutz DROP COLUMN xplan_zweckbestimmunghws_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-42', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 42, '8:364f5572198a8ee4955e66c888f41d4f', 'dropColumn columnName=xplan_zweckbestimmunghws_nsm, tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-43::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_vorbhochwasserschutz DROP COLUMN xplan_zweckbestimmunghws_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-43', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 43, '8:71f46a288180f3007d931326c96b5682', 'dropColumn columnName=xplan_zweckbestimmunghws_nsmcode, tableName=xplan_rp_vorbhochwasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-44::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserschutz DROP COLUMN xplan_zweckbestimmungws_nsm;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-44', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 44, '8:98401a2f35f610c45b5b4905e13a2fb2', 'dropColumn columnName=xplan_zweckbestimmungws_nsm, tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Changeset changelog_xplansynarchive.xml::1647585522758-45::lyn (generated)
ALTER TABLE xplansynarchive.xplan_rp_wasserschutz DROP COLUMN xplan_zweckbestimmungws_nsmcode;

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1647585522758-45', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 45, '8:7ac353545bb9ca1338a56883c85bf214', 'dropColumn columnName=xplan_zweckbestimmungws_nsmcode, tableName=xplan_rp_wasserschutz', '', 'EXECUTED', NULL, NULL, '3.6.2', '7585558935');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

