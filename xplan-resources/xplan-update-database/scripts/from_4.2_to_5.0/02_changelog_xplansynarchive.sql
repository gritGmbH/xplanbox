-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynarchive.xml
-- Ran at: 12.01.22, 07:36
-- Against: postgres@jdbc:postgresql://localhost:5433/xplanbox43
-- Liquibase version: 3.6.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansynarchive.databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynarchive.databasechangeloglock;

INSERT INTO xplansynarchive.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'cpe-172-101-0-1.maine.res.rr.com (172.101.0.1)', LOCKGRANTED = '2022-01-12 07:36:56.508' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynarchive.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Changeset changelog_xplansynarchive.xml::1641969382479-1::lyn (generated)
CREATE TABLE xplansynarchive.xplan_bp_wohngebaeudeflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INTEGER, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_textabschnitte TEXT, xplan_begruendungabschnitte TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INTEGER, xplan_gehoertzubereich TEXT, xplan_hatgenerattribut TEXT, xplan_aufschrift TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_laermkontingent TEXT, xplan_laermkontingentgebiet TEXT, xplan_zusatzkontingent TEXT, xplan_zusatzkontingentflaeche TEXT, xplan_richtungssektorgrenze TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_flaechenschluss TEXT, xplan_dnmin numeric, xplan_dnminuom TEXT, xplan_dnmax numeric, xplan_dnmaxuom TEXT, xplan_dn numeric, xplan_dnuom TEXT, xplan_dnzwingend numeric, xplan_dnzwingenduom TEXT, xplan_fr numeric, xplan_fruom TEXT, xplan_dachform TEXT, xplan_dachformcode TEXT, xplan_detailliertedachform TEXT, xplan_detailliertedachformcode TEXT, xplan_maxzahlwohnungen INTEGER, xplan_fmin numeric, xplan_fminuom TEXT, xplan_fmax numeric, xplan_fmaxuom TEXT, xplan_bmin numeric, xplan_bminuom TEXT, xplan_bmax numeric, xplan_bmaxuom TEXT, xplan_tmin numeric, xplan_tminuom TEXT, xplan_tmax numeric, xplan_tmaxuom TEXT, xplan_gfzmin numeric, xplan_gfzmax numeric, xplan_gfz numeric, xplan_gfz_ausn numeric, xplan_gfmin numeric, xplan_gfminuom TEXT, xplan_gfmax numeric, xplan_gfmaxuom TEXT, xplan_gf numeric, xplan_gfuom TEXT, xplan_gf_ausn numeric, xplan_gf_ausnuom TEXT, xplan_bmz numeric, xplan_bmz_ausn numeric, xplan_bmmin numeric, xplan_bmminuom TEXT, xplan_bmmax numeric, xplan_bmmaxuom TEXT, xplan_bm numeric, xplan_bmuom TEXT, xplan_bm_ausn numeric, xplan_bm_ausnuom TEXT, xplan_grzmin numeric, xplan_grzmax numeric, xplan_grz numeric, xplan_grz_ausn numeric, xplan_grmin numeric, xplan_grminuom TEXT, xplan_grmax numeric, xplan_grmaxuom TEXT, xplan_gr numeric, xplan_gruom TEXT, xplan_gr_ausn numeric, xplan_gr_ausnuom TEXT, xplan_zmin INTEGER, xplan_zmax INTEGER, xplan_zzwingend INTEGER, xplan_z INTEGER, xplan_z_ausn INTEGER, xplan_abweichungbaunvo TEXT, xplan_abweichungbaunvocode TEXT, xplan_bauweise TEXT, xplan_bauweisecode TEXT, xplan_abweichendebauweise TEXT, xplan_abweichendebauweisecode TEXT, xplan_vertikaledifferenzierung TEXT, xplan_bebauungsart TEXT, xplan_bebauungsartcode TEXT, xplan_bebauungvorderegrenze TEXT, xplan_bebauungvorderegrenzecode TEXT, xplan_bebauungrueckwaertigegrenze TEXT, xplan_bebauungrueckwaertigegrenzecode TEXT, xplan_bebauungseitlichegrenze TEXT, xplan_bebauungseitlichegrenzecode TEXT, xplan_abweichungtext TEXT, xplan_refgebaeudequerschnitt TEXT, xplan_artderbaulichennutzung TEXT, xplan_artderbaulichennutzungcode TEXT, xplan_wirddargestelltdurch TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_zumin INTEGER, xplan_zumax INTEGER, xplan_zuzwingend INTEGER, xplan_zu INTEGER, xplan_zu_ausn INTEGER, xplan_zugunstenvon TEXT, xplan_z_staffel INTEGER, xplan_wohnnutzungwgstrasse TEXT, xplan_wohnnutzungwgstrassecode TEXT, xplan_zwohn INTEGER, xplan_gfantwohnen numeric, xplan_gfantwohnenuom TEXT, xplan_gfwohnen numeric, xplan_gfwohnenuom TEXT, xplan_gfantgewerbe numeric, xplan_gfantgewerbeuom TEXT, xplan_gfgewerbe numeric, xplan_gfgewerbeuom TEXT, xplan_dachgestaltung TEXT, xplan_vf numeric, xplan_vfuom TEXT, xplan_mingrwohneinheit numeric, xplan_mingrwohneinheituom TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY, CONSTRAINT xplan_bp_wohngebaeudeflaeche_pkey PRIMARY KEY (attr_gml_id));

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1641969382479-1', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 1, '8:30bf948577b95712edab24e1a29c756b', 'createTable tableName=xplan_bp_wohngebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1969417010');

-- Changeset changelog_xplansynarchive.xml::1641969382479-2::lyn (generated)
CREATE INDEX spatial_idx_501 ON xplansynarchive.xplan_bp_wohngebaeudeflaeche(xplan_position);

INSERT INTO xplansynarchive.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1641969382479-2', 'lyn (generated)', 'changelog_xplansynarchive.xml', NOW(), 2, '8:fbe0ad3fea9bc16386eb3c77140e3501', 'createIndex indexName=spatial_idx_501, tableName=xplan_bp_wohngebaeudeflaeche', '', 'EXECUTED', NULL, NULL, '3.6.2', '1969417010');

-- Release Database Lock
UPDATE xplansynarchive.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

