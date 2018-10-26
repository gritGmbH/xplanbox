-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansynpre.xml
-- Ran at: 25.10.18 15:51
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/lgvxplanisk282
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansynpre.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansynpre.databasechangeloglock;

INSERT INTO xplansynpre.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansynpre.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%eth0)', LOCKGRANTED = '2018-10-25 15:51:10.535' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansynpre.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Changeset changelog_xplansynpre.xml::1540475405117-1::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_bp_technischemassnahmenflaeche (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_wirdausgeglichendurchflaeche TEXT, xplan_wirdausgeglichendurchabe TEXT, xplan_wirdausgeglichendurchspemassnahme TEXT, xplan_wirdausgeglichendurchspeflaeche TEXT, xplan_wirdausgeglichendurchmassnahme TEXT, xplan_flaechenschluss TEXT, xplan_zweckbestimmung TEXT, xplan_zweckbestimmungcode TEXT, xplan_technischemassnahme TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-1', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 1, '7:2b042f42fb9031948efa01152b81fc5a', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-2::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_lp_schutzobjektlandesrecht (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_gehoertzubereich TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_reftextinhalt TEXT, xplan_flussrichtung TEXT, xplan_nordwinkel TEXT, xplan_nordwinkeluom TEXT, xplan_detailtyp TEXT, xplan_detailtypcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-2', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 2, '7:6ad03b92917d3c64a68561f580c8683e', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-3::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_einzelhandel (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_bauhoehenbeschraenkung INT4, xplan_istsiedlungsbeschraenkung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-3', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 3, '7:4df35268a5199d727bc0164a2cd6da39', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-4::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_erholung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typerholung TEXT, xplan_typerholungcode TEXT, xplan_typtourismus TEXT, xplan_typtourismuscode TEXT, xplan_besonderertyp TEXT, xplan_besonderertypcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-4', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 4, '7:8787a262c741e5282ab2a166dcc9995b', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-5::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_erneuerbareenergie (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-5', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 5, '7:223c337a5ea0804820951955187cf0de', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-6::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_freiraum (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-6', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 6, '7:65be8da809f46362d62c6dd7a1d42b22', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-7::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_funktionszuweisung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_bezeichnung TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-7', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 7, '7:2c05417a55ea57a34f4cd860c4d50afa', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-8::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_hochwasserschutz (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-8', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 8, '7:a497c34fdca2f81522ccaaf98f9e2df5', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-9::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_industriegewerbe (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_bauhoehenbeschraenkung INT4, xplan_istsiedlungsbeschraenkung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-9', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 9, '7:f2cad3674f037ab86c710f0e2cdeedb5', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-10::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_kulturlandschaft (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-10', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 10, '7:53d9020e3d244f2a514f0b061128a7f4', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-11::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_laermschutzbauschutz (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-11', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 11, '7:40a5779e9d3af4a285b80cf81aca7979', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-12::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_legendenobjekt (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_legendenbezeichnung TEXT, xplan_reflegendenbild TEXT, xplan_gehoertzupraesentationsobjekt TEXT);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-12', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 12, '7:ee63f7c75369b24c9358bcfeec32b944', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-13::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_luftverkehr (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_allgemeinertyp TEXT, xplan_allgemeinertypcode TEXT, xplan_status TEXT, xplan_statuscode TEXT, xplan_bezeichnung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-13', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 13, '7:d9512da2e7482200aaa5d7ec282bee99', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-14::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_planungsraum (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_planungsraumbeschreibung TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-14', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 14, '7:a213bffe431d0790f8ae839211a07a89', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-15::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_radwegwanderweg (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-15', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 15, '7:f5b60f661de8159885d313047aab929f', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-16::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_rohstoff (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_rohstofftyp TEXT, xplan_rohstofftypcode TEXT, xplan_folgenutzung TEXT, xplan_folgenutzungcode TEXT, xplan_folgenutzungtext TEXT, xplan_zeitstufe TEXT, xplan_zeitstufecode TEXT, xplan_zeitstufetext TEXT, xplan_tiefe TEXT, xplan_tiefecode TEXT, xplan_bergbauplanungtyp TEXT, xplan_bergbauplanungtypcode TEXT, xplan_istaufschuettungablagerung TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-16', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 16, '7:39d76c160407f8953a4b1ca84330930b', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-17::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_schienenverkehr (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_allgemeinertyp TEXT, xplan_allgemeinertypcode TEXT, xplan_status TEXT, xplan_statuscode TEXT, xplan_bezeichnung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_besonderertyp TEXT, xplan_besonderertypcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-17', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 17, '7:47f50276da1c1109d17e0e90defbe421', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-18::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_siedlung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_bauhoehenbeschraenkung INT4, xplan_istsiedlungsbeschraenkung TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-18', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 18, '7:1e4a1cf82c956217d2b7d7ee55e183b9', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-19::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_sonstigerfreiraumschutz (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-19', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 19, '7:9ef98c72f5cff6259e4cf123b0974345', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-20::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_sonstigersiedlungsbereich (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_bauhoehenbeschraenkung INT4, xplan_istsiedlungsbeschraenkung TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-20', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 20, '7:4bb30eaa16caec0fcb2ef65d7ddc97fc', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-21::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_sonstverkehr (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_allgemeinertyp TEXT, xplan_allgemeinertypcode TEXT, xplan_status TEXT, xplan_statuscode TEXT, xplan_bezeichnung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-21', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 21, '7:cd6ad2e365c1ccbcd996b30083b7275b', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-22::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_sportanlage (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_istausgleichsgebiet TEXT, xplan_imverbund TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-22', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 22, '7:3f67620e6c5fc47c559d6bb6d5013544', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-23::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_strassenverkehr (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_allgemeinertyp TEXT, xplan_allgemeinertypcode TEXT, xplan_status TEXT, xplan_statuscode TEXT, xplan_bezeichnung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_besonderertyp TEXT, xplan_besonderertypcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-23', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 23, '7:3b19ce7a2b85f972c484439b9091d60d', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-24::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_wasserverkehr (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_allgemeinertyp TEXT, xplan_allgemeinertypcode TEXT, xplan_status TEXT, xplan_statuscode TEXT, xplan_bezeichnung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-24', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 24, '7:54d5ee2cc9838cd7b0ad4f8fe1edf545', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-25::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_rp_wohnensiedlung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_wmssortdate date, xplan_uuid TEXT, xplan_text TEXT, xplan_rechtsstand TEXT, xplan_rechtsstandcode TEXT, xplan_gesetzlichegrundlage TEXT, xplan_gesetzlichegrundlagecode TEXT, xplan_gliederung1 TEXT, xplan_gliederung2 TEXT, xplan_ebene INT4, xplan_hatgenerattribut TEXT, xplan_hoehenangabe TEXT, xplan_externereferenz TEXT, xplan_wirddargestelltdurch TEXT, xplan_refbegruendunginhalt TEXT, xplan_startbedingung TEXT, xplan_endebedingung TEXT, xplan_rechtscharakter TEXT, xplan_rechtscharaktercode TEXT, xplan_konkretisierung TEXT, xplan_gebietstyp TEXT, xplan_gebietstypcode TEXT, xplan_kuestenmeer TEXT, xplan_bedeutsamkeit TEXT, xplan_bedeutsamkeitcode TEXT, xplan_istzweckbindung TEXT, xplan_reftextinhalt TEXT, xplan_flaechenschluss TEXT, xplan_flussrichtung TEXT, xplan_bauhoehenbeschraenkung INT4, xplan_istsiedlungsbeschraenkung TEXT, xplan_typ TEXT, xplan_typcode TEXT, xplan_position GEOMETRY);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-25', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 25, '7:a65f2173626347662c44e9c32dbe955a', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-26::lgvxplanisk (generated)
CREATE TABLE xplansynpre.xplan_xp_rasterdarstellung (attr_gml_id TEXT NOT NULL, xplan_gmlid TEXT, xplan_gmlname TEXT, xplan_gmldescription TEXT, xplan_xpversion TEXT, xplan_xpplantype TEXT, xplan_xpplanname TEXT, xplan_mgr_planid INT4, xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE, xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE, xplan_refscan TEXT, xplan_reftext TEXT, xplan_reflegende TEXT, xplan_scanurl TEXT, xplan_georefurl TEXT, xplan_legendeurl TEXT, xplan_texturl TEXT);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-26', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 26, '7:2e72ff903dc28c2f754d409450bfd8ed', 'createTable', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-27::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_abfalltyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-27', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 27, '7:30378819b8edb6e62441515cdda8855c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-28::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_abfalltypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-28', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 28, '7:bea74dfa3a8dbbaaefbe86fcdfc30e4c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-29::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_abweichendebauweise TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-29', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 29, '7:5ed702f6c0cd030fc56f4892162c86d7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-30::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_abweichendebauweisecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-30', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 30, '7:f2a227c298604fc844b948b774f6d934', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-31::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_allgemeinertyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-31', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 31, '7:0ae163d0bb8b6fb137c1fdb0f23ec2be', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-32::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_allgemeinertypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-32', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 32, '7:d12b3b0e7377ee68c24e53fafbff8a76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-33::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_amtlicherschluessel INT4;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-33', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 33, '7:86223c5292a238b332aa4e50a2f452a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-34::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_baumart TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-34', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 34, '7:74ec433aa7f30b41cd633da066007312', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-35::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_baumartcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-35', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 35, '7:7b30d44482f414e59e57b1bdc354701f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-36::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bauweise TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-36', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 36, '7:8b4cdf91d512311dac93c53b6cb62bc3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-37::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bauweisecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-37', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 37, '7:dfb9bfcbcd38bd7f156a65d88b78432b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-38::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungrueckwaertigegrenze TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-38', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 38, '7:d574af2ed5ab4f275fe76dad8bee1f66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-39::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungrueckwaertigegrenzecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-39', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 39, '7:1a922c868f6022a8ee0a33ea09562115', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-40::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungsart TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-40', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 40, '7:7cb619da65bee3fc44e41bc3e02bed47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-41::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungsartcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-41', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 41, '7:5cdc7c1fa194e7da78c6944ba70ef60c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-42::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungseitlichegrenze TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-42', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 42, '7:388933b7386c7f990fbe8eca82a96949', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-43::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungseitlichegrenzecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-43', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 43, '7:73241c09e020c19ead7ffb9bbbd5c5b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-44::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungvorderegrenze TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-44', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 44, '7:9ea69aa6381af408eb6bdf52438f24de', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-45::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_bebauungvorderegrenzecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-45', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 45, '7:804d8de44287ceacc0c0c30e2c364d47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-46::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-46', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 46, '7:43a9e94b9758c17cad11708eb50a4376', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-47::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-47', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 47, '7:5d3297be320757686c018e67b698aa9e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-48::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-48', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 48, '7:cfdd45cffc8ab7ecfb0a7028c21fb66c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-49::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-49', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 49, '7:a679f78a4d05a74e80547880ff879628', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-50::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-50', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 50, '7:c4ec83b0c934a5e1d666d879f84a74b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-51::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-51', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 51, '7:cab5bdb56e951b18bac45a99364f7d20', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-52::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-52', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 52, '7:8e928036bd15b9fdd874c9ba713fd307', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-53::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-53', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 53, '7:a519ec37a5bb6bd6de5364578dddfb49', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-54::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-54', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 54, '7:4e05ebccf21a337de89cc48269f24f13', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-55::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-55', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 55, '7:49a1503a21ad54693387df72bd2bcdec', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-56::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-56', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 56, '7:cad0a4e3d2cdaa815c2c5cb461a2ac5a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-57::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-57', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 57, '7:5c07ff185075b232d2a7f0426f2fb31b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-58::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-58', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 58, '7:c1b4c0cca27d7b7e5485e0a0ecc4bb33', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-59::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-59', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 59, '7:18d5c490747a585676c63cb108ee5652', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-60::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-60', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 60, '7:b09f3ee06612314d236152add0bc4e43', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-61::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-61', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 61, '7:829d2f04d51cecd31ad4fc5433aa6127', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-62::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-62', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 62, '7:787848210cf75bdcb14ecec9bb20b6ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-63::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-63', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 63, '7:4c06fe967b666fb880368d734014891b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-64::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-64', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 64, '7:e0bba2e7e219db125346364feddd430b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-65::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-65', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 65, '7:8728a2b82844b7aa4333d8bd8353dc0a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-66::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-66', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 66, '7:b470b54671f7f84c3eab35109dd7e434', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-67::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_bedeutsamkeit TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-67', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 67, '7:e4ae228019cc12f386a21deea317cc3d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-68::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-68', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 68, '7:b892ab1f7053177719e190352d5f5d17', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-69::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-69', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 69, '7:5ba97717c3c3152445b802a0e20791f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-70::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-70', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 70, '7:786f3ea973030dcc6133faaa50f664dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-71::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-71', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 71, '7:4b6eefadec8c4c7c0f7a60c7bb8544cd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-72::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-72', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 72, '7:55b492af00bcc9cf3c52bf6a2658e751', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-73::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-73', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 73, '7:856405f57cbd67d4be7173ed50abf91e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-74::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-74', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 74, '7:fd9511deb33004cffd5436ba6d40edf5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-75::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-75', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 75, '7:b7b65fd323623fc34a666687020e3811', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-76::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-76', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 76, '7:61b06ed91ce04ba66ba5bad1e9366cb3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-77::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-77', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 77, '7:5684667fcbe0dd485cafc5afbe4155d3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-78::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-78', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 78, '7:15f2624bbbfe136bace2c9f17674317c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-79::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-79', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 79, '7:dda5c8b65f8c5c4fd96c2d1daa51d199', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-80::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-80', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 80, '7:043e5a4492cbd86d7d715db02b346161', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-81::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-81', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 81, '7:56b5532fe31a4b4388bb98afc588d8ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-82::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-82', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 82, '7:b666dd55c090a5586cb2eeda564cdc60', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-83::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-83', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 83, '7:9fbfdc3c45578af7a13cd66d4828254f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-84::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-84', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 84, '7:9a40cf7679d7cc384b7200896b387be2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-85::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-85', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 85, '7:70833b24794609230f654d322ba7c691', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-86::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-86', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 86, '7:6637cc53f36296c5a6c58dc49802651f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-87::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-87', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 87, '7:874816a43d92e0f17910d120906de5ad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-88::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-88', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 88, '7:0dc5a8c61ca7b2e77b33d6f20a0b58f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-89::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_bedeutsamkeitcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-89', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 89, '7:4f0ef9372c86b4d4c9ea1b3f478ca51b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-90::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_besonderertyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-90', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 90, '7:5742008b952851290b93dbcd9f58c662', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-91::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_besonderertypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-91', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 91, '7:1ba365082356f9e1085de753c85c9131', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-92::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_bezeichnung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-92', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 92, '7:004161279c0c1ba1f4b3ecdf4fa079e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-93::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dachform TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-93', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 93, '7:cfa2d4ee1d6074ad7b0fc2b2107945c2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-94::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dachformcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-94', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 94, '7:7360be6d7780ec3299c43e719540a455', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-95::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_detailliertedachform TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-95', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 95, '7:38d6157d7bbbc3fbbb44788f1907aa9c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-96::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_detailliertedachformcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-96', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 96, '7:15db5f175cdd63e011d2191965b1eaf2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-97::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dn numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-97', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 97, '7:bc7842854b61f334ffb282d92023b1ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-98::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnmax numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-98', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 98, '7:fda8b87d6bac77cd82d57e1c02ec106a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-99::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnmaxuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-99', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 99, '7:34bba24438df932b9a6cd585547af442', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-100::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnmin numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-100', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 100, '7:24685c8c6b2c20f75ccf84308eab8486', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-101::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnminuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-101', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 101, '7:3b0c7dcf6b333aa099893ba92eb55aeb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-102::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-102', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 102, '7:7ae78d8372b944ca78b581873bfb9f1f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-103::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnzwingend numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-103', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 103, '7:7e4f2d3f41cc149ecaf75b2fb71bef6e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-104::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_dnzwingenduom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-104', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 104, '7:3b7195442f098cd216cb3a7245c238ab', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-105::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-105', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 105, '7:044860af8bc0b61a1fe2ef5cacaa3237', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-106::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-106', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 106, '7:12721b479924fe527d0cfa4f3b6cc3e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-107::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-107', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 107, '7:76fa3e35fea5f937f6dd209a4395a2a7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-108::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-108', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 108, '7:4f1f4be4df4413518f9490abccf9946e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-109::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-109', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 109, '7:5500d190ea4d636f07c17e5af9d09113', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-110::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-110', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 110, '7:9f1e6d1ffd1580593e3f13875d1608f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-111::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bebauungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-111', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 111, '7:c6dd4d20cc7aadeab342db9bfce51057', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-112::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-112', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 112, '7:8bdd0deec35a17370f9a3a87a56654fa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-113::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetzeflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-113', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 113, '7:4ba942d055e0efce68241b6ee3fe8839', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-114::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-114', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 114, '7:27267f2acee490d2e265603e50561a8d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-115::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-115', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 115, '7:bd4d403ef0551d10f4bda0a9a1503e1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-116::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-116', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 116, '7:cfe12cd8363e376159914008ccf6ac2a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-117::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-117', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 117, '7:f83262641bba6058ed0af8125242f622', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-118::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-118', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 118, '7:afc353f174f458fabdba857b6e26e640', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-119::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-119', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 119, '7:837ddd982394660367c1472493ee0241', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-120::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_landwirtschaftsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-120', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 120, '7:09356565a1cbe68c42729e1aecd24e88', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-121::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-121', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 121, '7:5017f58b2227a924a0ed006f9b1956ae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-122::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-122', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 122, '7:42360d49f8cf4890b02452c4007c3b96', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-123::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_priviligiertesvorhaben ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-123', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 123, '7:2e399f05791da29f61fd0fab32b4e72e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-124::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-124', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 124, '7:6b6f895a71feb0b7f5380f4be85e7bd5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-125::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-125', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 125, '7:a6e2db51453ff37a85ad480a65c60dde', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-126::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-126', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 126, '7:c01e599f7e8341aebf0de2ab6051f8f1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-127::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textlichedarstellungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-127', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 127, '7:c53fd2c53087cda07c59c4c1628e8c6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-128::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-128', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 128, '7:f4edf876b5403b919194f7d8fffb98d0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-129::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-129', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 129, '7:12947b37dc52248e5ebb5fe70a326e64', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-130::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbehalteflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-130', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 130, '7:3b078b769a6be04ea9fcf6dff22ac2fd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-131::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_waldflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-131', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 131, '7:9529a5e5056bf4dad4104cbd979ca3f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-132::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-132', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 132, '7:c2cf954e140b6fa0bdffca424e248f4b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-133::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_abgrenzung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-133', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 133, '7:c111e25a6b635c8470c3d66fd2a844b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-134::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_allggruenflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-134', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 134, '7:3969157351bd872cbd44a1f2d222f4ca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-135::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-135', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 135, '7:896074cc33a830535b0d6ba1fb906d09', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-136::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-136', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 136, '7:27839eb7362e5bd49dd710eb05b0fb4b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-137::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-137', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 137, '7:f8928f9d66dbf42f7130d39f0c6da743', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-138::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-138', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 138, '7:9f95fdc84f969d86cdcd5f44f2c4109f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-139::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_denkmalschutzrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-139', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 139, '7:53844c9ba4baf4cbdf6b6cfd81766919', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-140::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-140', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 140, '7:a2b9f9c3bfa80cf5995b94a37166fe36', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-141::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-141', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 141, '7:722ba4ed0bfd74f563896d58f60b0bf5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-142::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-142', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 142, '7:3304e5ec9df2b1879d887e3e98b79592', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-143::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-143', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 143, '7:35097eb60cdd92b8f207d31aa284ce98', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-144::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-144', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 144, '7:189a673e968c49efd5bdb68ffb85bffd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-145::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-145', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 145, '7:a3f2c146acfef7422177f37ceeed4fc5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-146::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-146', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 146, '7:c8229e81b216f981cdc0a179afdad564', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-147::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektbundesrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-147', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 147, '7:61ae06698d1bdbef96355c5364034953', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-148::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-148', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 148, '7:9759a788ac42570b7e651e097a2952c0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-149::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-149', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 149, '7:03ebf279051160fdb6cfb8205d16600e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-150::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-150', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 150, '7:3bd7ec87d7c33fff28555da012136ec4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-151::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-151', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 151, '7:26bd0acf9972346114660afe1b975c0d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-152::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-152', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 152, '7:f9ee94f956efc5935454c8dd75d838e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-153::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-153', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 153, '7:d3a230634bc01f2753576fcc58519841', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-154::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-154', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 154, '7:19a919e49de9a9fc1b957208425bf9e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-155::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-155', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 155, '7:ff13d450571baf36cc83b7761d3f560c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-156::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-156', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 156, '7:da4ed123aebd4205315e1f17067ce85b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-157::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-157', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 157, '7:241bf296d3521069d7290dacb196440e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-158::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-158', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 158, '7:32b91e6a477762211cb1bfad6fb6a7b6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-159::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-159', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 159, '7:be7d41849964391b16c7d03f9cb675f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-160::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-160', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 160, '7:b15b00e68d6e5a961fec02e7e6975c2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-161::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-161', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 161, '7:67116e0a9f33add49b0a4b60bb007892', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-162::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-162', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 162, '7:b9d718ef36bc44e3f7e89063d4c2d4c7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-163::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freizeiterholung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-163', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 163, '7:affc53ba5eab55ceadf0943e5aac71bc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-164::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-164', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 164, '7:84cb0ad69dff616710c19df7e37d25f9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-165::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-165', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 165, '7:574abc2b64bde86f52f7199056e3235e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-166::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-166', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 166, '7:129de7e52a2009639101c13ca54b4f94', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-167::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-167', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 167, '7:0e5a282b50e385303ea2abfd919c280d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-168::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-168', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 168, '7:486bd8b81a37c2c9db8116b4dcdcc392', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-169::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-169', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 169, '7:152e602685c8e62c8a7296128a565fce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-170::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-170', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 170, '7:12f396a71aa5b51af311eab224dd9156', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-171::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturellessachgut ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-171', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 171, '7:eee43fa7d973ccf3d15f6e3aad2931dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-172::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbereich ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-172', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 172, '7:1d2281f1a311de26753f4d4ea6961679', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-173::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-173', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 173, '7:165e7288d118a41db83ab7b0c97478f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-174::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-174', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 174, '7:10beb3b1a2cb06c8899de318c0eefae8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-175::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-175', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 175, '7:7dd14bbb2181813256fd8b51ef751a69', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-176::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-176', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 176, '7:553e47144ce24c1f7a16f4b9396c0080', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-177::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoffsicherung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-177', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 177, '7:840a29de733acddfe97b9b9ad9101244', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-178::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-178', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 178, '7:a717840abccc670730d8c40dcd5c827d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-179::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumstruktur ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-179', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 179, '7:82afb8cb9fa6040fe7ac8b648f143fb6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-180::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigesiedlungsstruktur ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-180', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 180, '7:a522093df0fe0a9d9e660b0859df687a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-181::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-181', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 181, '7:5c256de96a8e50d246ececb44aa2d2f6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-182::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-182', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 182, '7:454b3638ac93e40c15dab9eac87f892d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-183::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-183', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 183, '7:5646e9b9d784ddf5993780b1e712018d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-184::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_vorbhochwasserschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-184', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 184, '7:cb7a0097885b0fc0b699ced5a9fb0c9c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-185::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-185', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 185, '7:1a85536454d11afc6641f022364d98f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-186::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-186', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 186, '7:be4612ff540476d0256408a50b0bda3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-187::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergienutzung ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-187', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 187, '7:f512a33c6164ec3679857db58c9c0c83', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-188::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-188', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 188, '7:cd18cbf55e5c3d5f67af9fbbd9fa6f62', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-189::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-189', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 189, '7:c86d8e9c9399e00a0a5b3c1ca8ec3216', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-190::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-190', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 190, '7:18c8871b6b616e2fdd6bdacf8e73ded5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-191::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-191', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 191, '7:db48569aa0d7602f68fa566dbb375703', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-192::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-192', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 192, '7:fcc1db7f878b29873a735894c304d0c7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-193::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-193', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 193, '7:227153935775622b7b9a9dda6a431e2c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-194::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-194', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 194, '7:1d051ae2c0b1bd33cd1dc9f333d01496', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-195::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-195', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 195, '7:6572a52c439bb2927fab93fc43739468', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-196::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-196', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 196, '7:64476efbecf89e168356380de4f52e7c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-197::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-197', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 197, '7:07f86c2db5c3a50cb6c74e7d5cc438d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-198::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-198', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 198, '7:ccc902ecc9d284db9006d98faf4d25e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-199::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-199', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 199, '7:205942aa4a303a38588f2ae81bf93c2a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-200::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-200', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 200, '7:eeb865fbb4112e4280298c23d65fb56d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-201::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-201', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 201, '7:7468f49ed96d0847c85417ece86fe0c8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-202::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-202', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 202, '7:fe80b7ee6adf5f52220c175998765baf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-203::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_endebedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-203', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 203, '7:6ad307bbd8bb93299870026a17a4aece', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-204::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abgrabungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-204', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 204, '7:1ba1ae0a909cd6810c88ebc3f87580bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-205::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-205', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 205, '7:47b406a31c7a46632cbd5ca6f2f4b1ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-206::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-206', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 206, '7:522a80bb14635921a8c3f75f0e5287ff', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-207::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-207', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 207, '7:aa6786af7796c4c853e6ce9e165fd171', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-208::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_aufschuettungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-208', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 208, '7:183876729eeab378c6611d507b32b795', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-209::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-209', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 209, '7:03f3afa8435d0c520fc05b4045693408', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-210::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-210', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 210, '7:dc490fe13dc8833861410227e0971e65', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-211::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-211', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 211, '7:db36e5c57abb78cb0f1e598cf4f5d10b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-212::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugrenze ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-212', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 212, '7:f42c4735f1fde0e1a40b11281fffdd56', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-213::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baulinie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-213', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 213, '7:8374cad1f2fe5eb29b5312eb63ad0a6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-214::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-214', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 214, '7:2a6ed2db7ee1e9a69deaaedf7bb4017d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-215::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_besonderernutzungszweckflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-215', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 215, '7:8436812fed2f3e6294a47ed6415fb8c0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-216::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bodenschaetzeflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-216', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 216, '7:53f10c85301f72412c10d0cc5ffdebd7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-217::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtpunkt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-217', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 217, '7:9c63eb0da13bfc88b6346393357b17bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-218::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtsbereichlinie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-218', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 218, '7:05b2d6998af18b2e6b96dace0776c050', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-219::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_eingriffsbereich ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-219', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 219, '7:591b586e3385bb602c19ca69d3f5882a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-220::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_erhaltungsbereichflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-220', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 220, '7:c09b31db0277c37dc9e15fad42d6ae28', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-221::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-221', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 221, '7:1439f2f00ba423307e494f294a290b7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-222::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_firstrichtungslinie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-222', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 222, '7:edda88d064e1e9f26b52192444e571bb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-223::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_foerderungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-223', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 223, '7:b3a0658afdcd552a9c2b660086985095', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-224::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_freiflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-224', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 224, '7:e9ee74a1c2c24ce9a3b66214e1a94987', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-225::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gebaeudeflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-225', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 225, '7:b21c756b642573e87a03b0b862ddd027', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-226::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinbedarfsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-226', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 226, '7:7d9a9f90fcfa90848a978a81415e6454', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-227::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-227', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 227, '7:9fadcd870f1ede1c1704444b43e11502', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-228::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-228', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 228, '7:a2594d397ce7da8b3a03410b52022f38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-229::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-229', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 229, '7:b18f3669bee0ebfd54184cb90def4de6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-230::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gewaesserflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-230', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 230, '7:250f8d0eaee8970564f94738768fd2a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-231::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gruenflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-231', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 231, '7:ce4c228799c00f7acd4acef3e6787e22', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-232::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-232', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 232, '7:1d1b72599176212f515f45a4351358f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-233::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-233', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 233, '7:3e7a1045948466d86ad568f7a0f0d760', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-234::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kennzeichnungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-234', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 234, '7:b0515652b37e702d2b6325923867a07c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-235::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kleintierhaltungflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-235', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 235, '7:da603ed182c93bb95437cbdde1edd076', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-236::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-236', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 236, '7:c4c29216183adb3ef0ebdc5c5aa698fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-237::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-237', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 237, '7:20193eb5a21590fbbb92eb16d42d46a6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-238::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nebenanlagenflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-238', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 238, '7:60717fda62f9320624b65c28213d8764', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-239::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_nutzungsartengrenze ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-239', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 239, '7:c4a7e76b8a8c7c748858404127d5712f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-240::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_persgruppenbestimmteflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-240', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 240, '7:a7d8fecbffba4a582c40dd42cb6c5906', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-241::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_regelungvergnuegungsstaetten ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-241', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 241, '7:f9c9b44af1c41694fd13ad335691e0a2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-242::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_rekultivierungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-242', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 242, '7:99acba6773df84de85b3fde6ddd3ab61', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-243::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-243', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 243, '7:b778cc906e0109661b8842511e9d520a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-244::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-244', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 244, '7:04f0e629aa9d8e6ebc6c0541f70954f1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-245::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_speziellebauweise ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-245', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 245, '7:024f5c31a70a96f095403fb5ca77f232', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-246::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_spielsportanlagenflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-246', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 246, '7:2cf7072e982506f1758923d7383a1fa9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-247::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenbegrenzungslinie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-247', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 247, '7:66122ec57da036cf500cd569a41993ae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-248::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-248', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 248, '7:c7f81bc1eabcac0d2a1319721586f7e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-249::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenverkehrsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-249', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 249, '7:57597e31a9cb55b11991f0cff6e976c1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-250::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_textlichefestsetzungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-250', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 250, '7:f86cc365ae308e3a6b0850a35a27391f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-251::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-251', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 251, '7:9d667f54f4cf98441d3bd2bb455fdd9f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-252::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-252', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 252, '7:3d1008d5e174291951e76095eef44032', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-253::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_veraenderungssperre ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-253', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 253, '7:ac1c27042aea37912766586bcbf5ce0c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-254::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-254', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 254, '7:c919fa143a3dc4908a73c1e7eae9a945', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-255::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-255', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 255, '7:f7ba2fe877a41541746b22ff3b5333b5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-256::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_waldflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-256', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 256, '7:a5a2cc6821bca78ba519aee249763e47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-257::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wasserwirtschaftsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-257', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 257, '7:d398ea2cfe2f558d96677f234eefb463', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-258::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-258', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 258, '7:0a686028d881ec587a0f35653c0d9408', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-259::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-259', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 259, '7:08c0d31627c373e610c0c696eeb992c0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-260::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-260', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 260, '7:fd8bcea09ecad30fcf1a5e65a0c8d309', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-261::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-261', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 261, '7:146e00799eb5fa847ffc5d027cf390b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-262::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-262', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 262, '7:f8f0ad9a8e73e6daaf7b25d06e4c1387', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-263::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-263', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 263, '7:ac9a6ed11240aba3311156cc36ac3f57', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-264::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bebauungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-264', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 264, '7:244ad06c0cb49c730069755d8a43baaa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-265::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-265', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 265, '7:38c95b9416e304589f1bc49640e04305', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-266::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-266', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 266, '7:0bf8cb5ba2c980c80b93ed51b2dd4477', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-267::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-267', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 267, '7:99032b8583af5577f39a734f8f3bfc27', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-268::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-268', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 268, '7:f404cbd7648122222f1867b583d0f805', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-269::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-269', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 269, '7:18a8ed903364a6dc4e45033e5eac705e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-270::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-270', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 270, '7:fbb900b7fdc6e936aaa6b0720be0b261', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-271::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-271', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 271, '7:1026612cccb56bef0fb7a4ba68b7c8b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-272::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_landwirtschaftsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-272', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 272, '7:58c01ba5245d075eb059add39b72fb3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-273::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-273', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 273, '7:434c32bbf832b82b8c0ac3fd7689e620', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-274::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-274', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 274, '7:23892c997d0d863559c84ea53d3ae9b6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-275::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-275', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 275, '7:99fdea64a49917af3c7cf1c67e37e1d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-276::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-276', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 276, '7:cab39f029bb49c4645f7bedef00279be', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-277::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-277', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 277, '7:8587bf0424c8077da6f7a2976d6ce166', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-278::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textlichedarstellungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-278', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 278, '7:61df3dc44370a6d9a88ed52da9138a43', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-279::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-279', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 279, '7:b5e251262c7a55debb3c92cb12a7fdbe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-280::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-280', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 280, '7:948539dba256a44f3e2afe099d8a1478', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-281::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbehalteflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-281', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 281, '7:c9afa7d44ccf1335e260bbb28d697b74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-282::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_waldflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-282', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 282, '7:3e4c93542391ce1666530d76601d84e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-283::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-283', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 283, '7:0db3b3c46a01e5b13176fb88132832ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-284::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-284', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 284, '7:41ae0c94556469be0ef6beecb9173a52', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-285::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_abgrenzung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-285', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 285, '7:90fbf552c056e6caa89bd9a6c4299972', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-286::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_allggruenflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-286', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 286, '7:8834c8d337b0e373f2f7d0c98aa6d16e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-287::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-287', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 287, '7:6705f4d54b0896fc375f5f302ad542a0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-288::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-288', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 288, '7:3c12d14496f376dd64a789fccd957e1a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-289::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-289', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 289, '7:4e5ba4195f2c1820f555e6058d5f6827', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-290::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-290', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 290, '7:9ddfc75fdce7aee90103ba4b2a983112', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-291::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-291', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 291, '7:1ad506535b0ff5b7c29a1b8420895cef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-292::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-292', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 292, '7:9ccd6eaee148aa96be156534f145dc76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-293::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-293', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 293, '7:7e72baadb83664899ef0384cb2b1a20e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-294::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-294', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 294, '7:11145842c973e4082400773d0e22c082', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-295::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-295', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 295, '7:9f11a7983dccd5c6ea592ae74e24ee63', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-296::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-296', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 296, '7:ababdc17f4ec7b30ed7b3dfe36f8dff2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-297::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-297', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 297, '7:ec7402f070b6169724a341c55e98e9a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-298::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-298', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 298, '7:fdf6b37e485b2a5876a864dbc1f3ae3a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-299::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-299', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 299, '7:836244855b0c82b01bcb0bba2f8b5b39', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-300::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-300', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 300, '7:bb147835f3185245992da7a86a304889', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-301::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-301', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 301, '7:cf48635da9da9fcda2dae58397a81ce8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-302::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-302', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 302, '7:6d1bb7cbfc75fef0056877752528a0ba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-303::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-303', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 303, '7:32f3246293992a6f28c130466050ad78', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-304::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-304', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 304, '7:1fd02ab3e26d7637187cca51de5b6fb0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-305::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-305', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 305, '7:dacf6b413b15fda859918921710fb20d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-306::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-306', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 306, '7:fdfd256baa4724c11c32266f0978d886', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-307::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-307', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 307, '7:4bd5044fc4920bb4d5fbb428f674b4f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-308::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-308', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 308, '7:7d372dc3095970b21e33ec4b2fa342aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-309::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-309', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 309, '7:cfe0d38b5beb21077230eb8e9009f55d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-310::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-310', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 310, '7:4fe3bc197f882992bf75d34f7eff1b71', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-311::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-311', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 311, '7:febd3dda68960ab86d3cb166e149acae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-312::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-312', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 312, '7:47dca3ac87bc36ed0cd8c38cad158820', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-313::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freizeiterholung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-313', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 313, '7:68c5fd8d099c72a37cc4b6f6754bb5d2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-314::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-314', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 314, '7:8e91a38dcf4edb81f0156f29b4cf3f5b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-315::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-315', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 315, '7:7ed349fdd31d49f5098e466c04e2760b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-316::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-316', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 316, '7:9d7dde82a58b7d3ffaaca5e00165b56c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-317::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-317', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 317, '7:1dc7b1f433cb87f4d69522bb274dac31', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-318::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-318', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 318, '7:3a7c2962365b7fb4a319694fc4f7e893', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-319::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-319', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 319, '7:7150216a8a3bd7ffaf8587d4afe19949', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-320::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-320', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 320, '7:c32c1380013a2da2b62518426f9eb495', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-321::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturellessachgut ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-321', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 321, '7:20b05c03118d66268fd5d4f9d95d4546', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-322::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbereich ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-322', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 322, '7:ee32792db8d4d88c5f8db0d7d4d19c09', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-323::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-323', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 323, '7:366e9f0670da74af7a81d21751ffe2b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-324::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-324', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 324, '7:42c7fa13c540580167e0f53e76108de4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-325::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-325', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 325, '7:dd4e87c8a11899579a8c5e0575fa95c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-326::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-326', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 326, '7:567b4cb768966ebc24b4fdd0acd53351', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-327::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-327', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 327, '7:714d39516cfba1ca6cd1b86d0dfeba4a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-328::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoffsicherung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-328', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 328, '7:9a185dd787d9ad670b531071f55d0560', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-329::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-329', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 329, '7:a57e4bcb2be5c37ded0b796745b01efc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-330::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumstruktur ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-330', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 330, '7:1189db71f4423b1a2f923046e88a21a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-331::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigesiedlungsstruktur ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-331', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 331, '7:64b3973d0f4c9fd62a971e4546f5355d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-332::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-332', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 332, '7:ad6717b0e86bdd2b0e951a4775e8f89d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-333::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-333', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 333, '7:1c9edfde4b535ca84e297850b0358d85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-334::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-334', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 334, '7:2ab4699547c1e440046867b82607a734', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-335::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_vorbhochwasserschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-335', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 335, '7:ed6f9a8a65ee334d9f0e430fe2f0dcdd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-336::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-336', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 336, '7:846eb164f1024694cf7b04e263d53379', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-337::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-337', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 337, '7:dcf93bc447541414d62d4f3571a735bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-338::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergienutzung ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-338', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 338, '7:f3060818975996bf16a40eb2d1c26f90', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-339::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-339', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 339, '7:b52ad8a777b913696bf7f5d05951799e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-340::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-340', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 340, '7:d70de8c31cd669921aea3cf49abc9048', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-341::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-341', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 341, '7:489e1dcf20fa68eb127d37133c2b52e7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-342::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-342', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 342, '7:daf6c2fffb4bf6c27c83da6ea113e45b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-343::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-343', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 343, '7:946eaa9d3ec7d6cf607a241206583992', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-344::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-344', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 344, '7:785e430e36578d1cdf5c0fc06bd4e449', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-345::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-345', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 345, '7:f89c6ea6c8461d679a30756f1e3192b0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-346::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-346', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 346, '7:225dc92bbe494c5df02dd32855ed7548', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-347::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-347', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 347, '7:d16bf72f8520f0093b130943c4574e02', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-348::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-348', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 348, '7:7c08e2fb71adc67b3d6ab36b266e330c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-349::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-349', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 349, '7:c0b64df5f56bad05171e72770db87394', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-350::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-350', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 350, '7:7c1fd4ecc35e818b3040c3aca78a4234', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-351::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-351', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 351, '7:5b55f038b9d3d9e550f799d83e71a114', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-352::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-352', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 352, '7:e1bc8c5ca1b8c0a35cd0e1804bdc7ab0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-353::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-353', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 353, '7:177d8e9e8246a4ad91441beb51d49cc3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-354::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_externereferenz TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-354', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 354, '7:00a90427fcfc74e8bc1795b158eeb5c7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-355::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-355', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 355, '7:50c71be04299d638098a9578d9d85620', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-356::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-356', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 356, '7:8c73b6a05aa721d5a004fc2bd34c0e1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-357::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-357', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 357, '7:f1f1d0d348447bb91118cd5eb9f8bc03', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-358::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-358', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 358, '7:0ec216ad6190f151c18309cffa88cc10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-359::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-359', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 359, '7:d96678f4f4cd746b941af240a11f1fb9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-360::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-360', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 360, '7:d836ad931cff33c70d9a96a897c56373', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-361::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-361', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 361, '7:9527226128d94a2fe8228b38de0f2f91', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-362::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-362', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 362, '7:b4d28598b5b576bcdfed0ff80832b6d7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-363::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-363', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 363, '7:e05686c8fa109ae07147c9e082315922', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-364::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-364', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 364, '7:37cad9e99e84d13943075264615990a6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-365::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-365', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 365, '7:f74c8c76da602c72c6806688008b611f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-366::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-366', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 366, '7:a2618dd68b064396e63e20359446e2f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-367::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-367', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 367, '7:540d4b38220acdfdcf785fa9c0e63c7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-368::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-368', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 368, '7:af6521f9739995cee46c5f8614d3655e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-369::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-369', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 369, '7:3d6d875df56c6f3868ebe95d897ffcae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-370::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-370', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 370, '7:b4ff28f2bf590398bdd3be3b4991e4fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-371::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-371', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 371, '7:3a1706f1d2c9b00347c0f83518e002f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-372::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-372', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 372, '7:6e697e23fa306e920bd969810736cd3a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-373::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-373', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 373, '7:07a135b0c96ea8c78523043dc371c146', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-374::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-374', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 374, '7:25a9a1b9ae4910f58aee94b8014985f9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-375::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-375', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 375, '7:1afd0e382472f06eaa3a26b6c96bf020', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-376::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_flaechenschluss TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-376', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 376, '7:d9b57c4aacb80e511ff4384688851777', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-377::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-377', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 377, '7:6a0c0378996946cf473f35cc25828efb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-378::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-378', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 378, '7:c76b7da34998fbc40b6d1aa465f99fe4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-379::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-379', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 379, '7:81ce471bc98147588d47f3cf7e067b4d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-380::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-380', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 380, '7:49c21af8a4ce7e92a1d28e1c2f4a0c64', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-381::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-381', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 381, '7:d9e409165d9b3d69254b899794547e8c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-382::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-382', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 382, '7:2050747abc601bc84bff0be583ea7319', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-383::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-383', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 383, '7:4657448086867bbf50ef00df4f027980', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-384::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-384', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 384, '7:9e7351d56cfcdcec83e61728e8a1de00', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-385::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-385', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 385, '7:d0d71d469e225c7d40d3a98d2e652120', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-386::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-386', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 386, '7:b1992533de79c2e7b487efcd75ed7999', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-387::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-387', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 387, '7:d9f7686b3f2dfbbbad36a02920d04266', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-388::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-388', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 388, '7:cb47f9eb51f54fa0f130c7f3c149615c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-389::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-389', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 389, '7:5ae5e2c6f7342cb9c994c2521496aa6e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-390::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-390', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 390, '7:f52daf002aa775ffef7713ab8d1f2acd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-391::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-391', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 391, '7:a19f20e822ec0671eb31251a5f6c5177', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-392::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-392', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 392, '7:8ec77026a46d38e9c201ad2098c1efcb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-393::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-393', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 393, '7:295f1834fb42985dbd8a989bdb81ea83', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-394::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-394', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 394, '7:b8a906a059f45bd30d9a6d8867494c4f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-395::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-395', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 395, '7:19b209cdc029939032b480f5004df7e5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-396::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-396', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 396, '7:7d54a0efac9072784a5225f6ff3852fe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-397::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-397', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 397, '7:24e822cf8d6d5decd9c4702f335bea5b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-398::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-398', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 398, '7:ef0a446adafc1fcdd1c305c4825875b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-399::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-399', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 399, '7:11885a230b787423357fc006fd47010a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-400::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-400', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 400, '7:9b9cf1b0086bf0113704fbf63f5d775f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-401::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-401', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 401, '7:8194c51f507a1c79556825c691873442', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-402::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-402', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 402, '7:7f8f706db2644d70f55cc158de28a112', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-403::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-403', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 403, '7:fe6da3ebc2593fbb2f9bdeae859f6202', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-404::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-404', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 404, '7:8fd857c4eb9a6bb61eba73a784727722', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-405::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-405', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 405, '7:9d2122e4a2a280e0e4f407f80d889c77', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-406::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-406', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 406, '7:33a46d90126e451dda09faafd0b9ad76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-407::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-407', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 407, '7:3236878d367feeea3aab9cddaa5f9d0e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-408::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-408', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 408, '7:513c496084647b13941527447359f7c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-409::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-409', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 409, '7:9cacc122343285c23ffd8617782e1f30', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-410::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-410', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 410, '7:62c163d503ff3928387f2d9514b6e09f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-411::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-411', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 411, '7:6a742d768687dce5721fa96e6a8fb712', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-412::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-412', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 412, '7:0932a62c88d2696dc5dbc2ce1e5fd27f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-413::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-413', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 413, '7:9f69568fae0c537a2da794850ddb25e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-414::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-414', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 414, '7:5799aea71f8ca621f329cbc5117e25e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-415::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-415', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 415, '7:9cb08366be7fa655374a6bc9ab995154', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-416::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-416', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 416, '7:e79da480e62f84d33323283056a30ead', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-417::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-417', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 417, '7:fe9a7dbf0467bc0dda5a75826db28827', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-418::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-418', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 418, '7:cd7ff4ddddf1ade7df9f1ecd85b20cf8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-419::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-419', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 419, '7:957e6999174f77839a85ad5b58e057e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-420::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-420', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 420, '7:a0ce2e77762113a6342a36c87eb20baf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-421::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-421', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 421, '7:068a0ba436c866a74b62b4a3474b6d56', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-422::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-422', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 422, '7:51404b9399742b89dfa09b4f5a677a60', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-423::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-423', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 423, '7:4ca808533d8d785a796f7072cfcec0b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-424::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-424', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 424, '7:99276b5f40306b89bd07c9f583fd81d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-425::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-425', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 425, '7:31bfe9c47f90a2170c59c62b33c1bece', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-426::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-426', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 426, '7:10c194e755dc8411111044852bd52905', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-427::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-427', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 427, '7:e4d96c0af6099726403b5de90352e539', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-428::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-428', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 428, '7:1c58dc55775d347f5c9f69162e733c10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-429::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-429', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 429, '7:cb0dce9a83d923535cb82b1f0ea3a240', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-430::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-430', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 430, '7:5a4b396b3129920b270dce5ae24ebeaf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-431::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-431', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 431, '7:9d24361f5d331403fa0c79856199b2b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-432::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-432', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 432, '7:602d864b23d1984daf7ed4fe64fd7a53', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-433::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-433', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 433, '7:07c34d88dcb6454e2e031f35ee8f6b5d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-434::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-434', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 434, '7:b0a349c24bb7ab09039d67e028159b59', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-435::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-435', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 435, '7:2a44a9a823c6ff4215590446c134ece8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-436::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-436', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 436, '7:7bf356f16e2052898a6bcae93ed45d7d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-437::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-437', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 437, '7:be4f9470174a42d9fc9756b516dc2f00', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-438::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-438', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 438, '7:a3f9699a2959f22fc30e7f69c3aad6f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-439::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-439', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 439, '7:5b138f83678a57bc666b33f57754e29d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-440::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-440', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 440, '7:cf8412434bed5cd7bbca45761547d951', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-441::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-441', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 441, '7:ac9006cf48130d1f7101fc929752d447', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-442::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-442', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 442, '7:6b3331d1557f61fea81839358eac3128', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-443::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-443', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 443, '7:02d8faad8ce282d26f5e5683049a1ec1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-444::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-444', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 444, '7:366abe9e3bc37d8b3bf4f0ce38ccc314', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-445::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-445', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 445, '7:c8d658276d0d46f719f3101a86da9869', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-446::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-446', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 446, '7:8e0d47f461413b27e03c811a19fb15ad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-447::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-447', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 447, '7:f36deaa8db5fe38a660e5994fd15e31f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-448::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-448', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 448, '7:ecd59f960d0f177973d1410bd4aceb97', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-449::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-449', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 449, '7:8a5930bae776704acd1bddfaec7b7645', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-450::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-450', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 450, '7:e3581fa3433c5b4570722e5bc82992e6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-451::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-451', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 451, '7:4183af27a1271e44a5c070e3f241d977', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-452::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-452', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 452, '7:74e64dd793ed1354ba533d3bc4147265', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-453::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-453', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 453, '7:0e2b115782e8d09838cc0599f8fbbaa6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-454::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-454', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 454, '7:edd39e6cc7f6ca0c0f951861215815d4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-455::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-455', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 455, '7:0721d0607bd490c0901c54836dd76aaf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-456::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-456', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 456, '7:5573bb229e6be8829156669ed31e298f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-457::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-457', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 457, '7:4d796533a3f690aecea7d42eb83aa318', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-458::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_flussrichtung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-458', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 458, '7:eb094db9961584139a1dc84f6a0b1cbd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-459::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_fr numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-459', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 459, '7:2e1293820dfd227ddc960bf30c807587', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-460::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_fruom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-460', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 460, '7:621041da45c9d3790e7f94e3760f109c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-461::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-461', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 461, '7:a4a4d3c737a64b74a338f40a346345a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-462::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-462', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 462, '7:8b98c8632ab5b2b7e483b0234fd2f0cb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-463::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-463', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 463, '7:76d602873443f3c4625682afc7494a0b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-464::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-464', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 464, '7:23ddd1e1e563895fa922588ac1fc4744', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-465::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-465', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 465, '7:4a4059ef53d5c899d4f8619684cb032f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-466::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-466', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 466, '7:ce76712a218784f10b89335c2b8b2011', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-467::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-467', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 467, '7:aa84aeb08d13f127c7106789552bdeaa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-468::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_gebietstyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-468', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 468, '7:17ddfb21d47c65fbdc8bce7f71d8a0ca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-469::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-469', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 469, '7:4b803de8c49729b40c8c776865396bf2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-470::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-470', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 470, '7:9cbc856f49ab1ea0f6807e67d5f4d847', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-471::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-471', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 471, '7:8d617ec76d4bd40d29aab5f35470fe4b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-472::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-472', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 472, '7:ab71d0b39984c51c76486fe5a73705fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-473::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-473', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 473, '7:bce386a40ac445bd0e753948337313f1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-474::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-474', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 474, '7:61f7edb9594cadeec77cb2a56097cfc3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-475::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-475', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 475, '7:a95f88a8859007349b4c72ad9b49b0c8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-476::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_gebietstypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-476', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 476, '7:ea8de31dd45d80c7b6aebd08aa5979ad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-477::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-477', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 477, '7:445c8d4b9991a2cfa96756a147674f92', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-478::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_eingriffsbereich ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-478', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 478, '7:46d8b95b2cab2bbd4e2efa21020cf6f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-479::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-479', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 479, '7:d0e3bd47019afbba00c9fa7f33974318', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-480::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-480', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 480, '7:834cfeccd77aac35fb78b6943c97e2a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-481::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_kleintierhaltungflaeche ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-481', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 481, '7:69a1532bd26a82562d5f144eb77d81bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-482::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-482', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 482, '7:25601592adef3b34259712bb0e238863', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-483::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_regelungvergnuegungsstaetten ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-483', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 483, '7:cf809af28c91c4e5e1c98d022a9601b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-484::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_veraenderungssperre ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-484', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 484, '7:2fccfa23b352052739d5e2685e3db39c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-485::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-485', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 485, '7:86d15a6d5b1d4eebf175f79d7d94ce76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-486::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-486', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 486, '7:136c9fbff031b0056dcbb71536378a07', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-487::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-487', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 487, '7:8f3a2fb06587de1a71d9c85b7340f28c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-488::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-488', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 488, '7:740e29b93a29a41fcaaa9819502146f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-489::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-489', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 489, '7:62f80e52486c3e04c6d8dedd08efe283', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-490::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-490', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 490, '7:9c1942342a249d99943ddc72f5ae0a8a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-491::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-491', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 491, '7:9d8e6ba0dff65e8de091ca7ccaafe42f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-492::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-492', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 492, '7:8553f62f4d13a4a0076f574af9939188', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-493::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-493', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 493, '7:3c09d8cf7e44a2570320a57c96535212', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-494::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-494', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 494, '7:7ef12cf76304f9c7b2c009ac8f32a417', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-495::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-495', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 495, '7:be4209e88a37ee816ac138b7513f1dde', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-496::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-496', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 496, '7:999475312446af1f7511ddd0a42319a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-497::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-497', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 497, '7:c495815773abfe1e2c71cd95288685e2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-498::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-498', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 498, '7:44eda59c7d12493e8b9bd801ea685600', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-499::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-499', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 499, '7:4a09a44467170d29c480b669bc9ba557', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-500::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-500', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 500, '7:8c2bea353adde9b00bcd28399157a753', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-501::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-501', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 501, '7:6c341717ee4cb5f1c4fb1dd86d0866d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-502::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-502', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 502, '7:b92b2bccb1ec5265cd3deafc5fd580a9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-503::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-503', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 503, '7:264c70d385fd67be17bc160d3ceb533c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-504::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-504', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 504, '7:050c4ce174db48d23063fb9876aaef66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-505::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-505', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 505, '7:57b7a352c3caf2b074320c34583ba396', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-506::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-506', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 506, '7:8a1a0a97dfa687d512c67e3cc6f75066', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-507::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-507', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 507, '7:0e4ef663361a8a3b42a53a8bb7333e51', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-508::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-508', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 508, '7:86dc5da893b3a0d4060d7ff32de7d130', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-509::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-509', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 509, '7:73b19af587f263bb53d79dba4f88363f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-510::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-510', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 510, '7:18ec830a30df7435fe56ab2d2d727474', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-511::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_gehoertzubereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-511', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 511, '7:e1a2c3322be71d55c40e81ba78a75a09', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-512::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bereich ADD xplan_geltungsmassstab INT4;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-512', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 512, '7:7194cbc4717bf16b2c057d33d83ff137', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-513::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_genehmigungsdatum date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-513', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 513, '7:0b26569ded2c655bf94a1ca4f1f6fc16', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-514::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_gewaessertyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-514', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 514, '7:a764e8118e880b40c63ff20f9bd8ef5d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-515::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfantgewerbe numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-515', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 515, '7:0654dd1f2db08361fa02e561f16afe57', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-516::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfantgewerbe numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-516', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 516, '7:bd2853fe4a6230f84235e7bdc4d7c26a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-517::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfantgewerbeuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-517', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 517, '7:82fe345da6d9c921fdd557dd70971f96', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-518::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfantgewerbeuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-518', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 518, '7:f13599e104192ee6ed2648012bdfb8e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-519::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfantwohnen numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-519', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 519, '7:6ceaee36f98fb29114f6d11dda8fb814', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-520::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfantwohnen numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-520', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 520, '7:2297aa029e9549bfaf32eda5dbf080aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-521::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfantwohnenuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-521', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 521, '7:a43198ef777ac8a5068df86b651b0cfa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-522::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfantwohnenuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-522', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 522, '7:2461641c823a0ebc8a8463f5ff3c5631', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-523::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfgewerbe numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-523', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 523, '7:85ce22d98e5ca986c4b77913971ab429', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-524::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfgewerbe numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-524', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 524, '7:1512651e4bfcb7a261fe61b79b04bb10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-525::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfgewerbeuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-525', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 525, '7:ec3c21673e5ba6ca9692df33b92cf030', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-526::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfgewerbeuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-526', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 526, '7:124ccfcec860b825ecfeb3427f35f7a9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-527::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfwohnen numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-527', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 527, '7:5a429597b7ee9af5734f9b74d436ceed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-528::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfwohnen numeric;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-528', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 528, '7:badb693067b7b9e7d82aec0d360a4e8f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-529::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_gfwohnenuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-529', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 529, '7:5e8e16590044bbee9daaaf9b712fa634', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-530::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gfwohnenuom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-530', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 530, '7:59c7cb1157f7f7e78ab789737d7c0560', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-531::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_ppo ADD xplan_hat TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-531', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 531, '7:6d74207837406f1a2c2238c7e4dc980c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-532::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-532', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 532, '7:2f86a723a0697a6039b0dc628d28d86f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-533::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-533', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 533, '7:66cee9a992f65b16d1df215facd78a6f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-534::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-534', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 534, '7:31e63f10da8c3f8dc3c0d50bdecd6100', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-535::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-535', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 535, '7:fec8d605e79d0241dbd11cc66243b182', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-536::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-536', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 536, '7:b16794e93803f4c9015abc2d3b6d2686', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-537::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-537', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 537, '7:dad2b78b2aed6956cea2e71bf67df021', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-538::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-538', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 538, '7:3d89fd1654616ac32e69e8e1071ba506', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-539::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-539', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 539, '7:a59c82535b8b8a43fde3758ffc21ba99', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-540::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_imverbund TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-540', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 540, '7:10bdbdefa861690acf3bb8cf1d747fe6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-541::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_istaufschuettungablagerung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-541', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 541, '7:5e37b5e56695b38764d14511939700e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-542::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_istkernzone TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-542', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 542, '7:be61685e46993c9e49885346047ca17b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-543::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-543', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 543, '7:93c74fc9d24caa9b2129ccdde843ab80', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-544::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-544', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 544, '7:ba246a01a1e78142d2e70bc9f16b7fd5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-545::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-545', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 545, '7:d8e5a72663efedb0a2735e0a454a7d76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-546::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-546', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 546, '7:99c162054865296eb8b09b0ef728d6aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-547::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-547', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 547, '7:c99ddc221b82980846b695da2956ccee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-548::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-548', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 548, '7:9241b25d9d7c348153806e85aab2f55e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-549::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-549', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 549, '7:43c5ddaba9443dc770bccfe5cd60108c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-550::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-550', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 550, '7:f0ee095270a0b2d6ae3f2b254c53ab2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-551::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-551', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 551, '7:4de3d7ad638042bfd4885feb635c6da3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-552::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-552', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 552, '7:2a6be4549b69f563a0929289e2f23857', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-553::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-553', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 553, '7:0a9ba15d5e88d53e2cf423be15fc5f70', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-554::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-554', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 554, '7:c708e4b4d204e3bd7a333c2f6e163e4e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-555::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-555', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 555, '7:b03c6b3290b97f29ef3ba83b4973e7ac', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-556::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-556', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 556, '7:f06ced97118b65c7af90c9290c221589', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-557::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-557', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 557, '7:f8151be371fec09019e2769090e13a0a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-558::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-558', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 558, '7:cefcfeaa8525882382bee01223103bc3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-559::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-559', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 559, '7:6c1eec39ca16e42d01608b40c3a3c1d1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-560::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-560', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 560, '7:a276d3135cbdee5cfd83e3a15ba93f85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-561::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-561', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 561, '7:427f6e8cc95efa0a39108cb606f7a8b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-562::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-562', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 562, '7:2b15d4da217d0a6f0e7fe4d37ed937c6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-563::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-563', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 563, '7:4f0007386953880f3fc7266b5681ca74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-564::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_istzweckbindung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-564', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 564, '7:20e8d86b5f2d728bb3a8f243b10e8829', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-565::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-565', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 565, '7:baedaa50216a74f90217c6b7c66b855d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-566::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-566', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 566, '7:3f73b6735bb3dc438b2041b116299ff7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-567::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-567', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 567, '7:8531b5a00422096046ca58a04d2ed83a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-568::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-568', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 568, '7:b0a92123efaa0cadc9eb0654d2d419a6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-569::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-569', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 569, '7:471fd87488828b19636211c39f5fad85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-570::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-570', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 570, '7:e74231d36f50662a1aa97d4bc67d6f89', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-571::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-571', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 571, '7:ff8adbda63a5816c1ef12c06459a34ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-572::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-572', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 572, '7:0046f7bf20bb3a710c97296d56fcebec', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-573::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-573', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 573, '7:fea619402a29bdbad971a70e58ff56d4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-574::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-574', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 574, '7:faa630794474a9d973126842f7f463e5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-575::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-575', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 575, '7:5480db868afbf58be3f26b57a5b0659c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-576::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-576', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 576, '7:ee4c937628da0a97e524434abef8f7b8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-577::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-577', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 577, '7:0e7bce3041d426340360062f13400d81', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-578::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-578', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 578, '7:b0c6c9fb906768738543475994916aa2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-579::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-579', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 579, '7:ae75a95ce2f64ea295f8dc2a5d81330b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-580::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-580', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 580, '7:749692e678e20accdc05526181ef8d87', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-581::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-581', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 581, '7:540684da909bb530e0cc68db1a6752ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-582::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-582', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 582, '7:0b1d00ff0052921b89e26d16c9ad0969', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-583::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-583', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 583, '7:15acd7a095a51911612a18a27deff643', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-584::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-584', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 584, '7:afba8daff7bf2d9d1f87ef2bba66410f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-585::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-585', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 585, '7:4836fe8fc9dc8dd9308e4ac63339e01a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-586::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_kuestenmeer TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-586', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 586, '7:0a40c62f7ca74f998b4e95dd6a049421', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-587::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_laermpegelbereich TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-587', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 587, '7:9fe4de9b39c7873ef8034adbf0ab914b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-588::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_laermpegelbereichcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-588', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 588, '7:22c05d4e2b444c531f3a1a8ffc4dd5e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-589::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-589', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 589, '7:db10781a2e414efe4f4dce602600670a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-590::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-590', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 590, '7:73ff6708278350a32f2d6a9f6581382f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-591::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-591', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 591, '7:50d795a3d9e367d5cbefd2ebcc8a425e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-592::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-592', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 592, '7:8d3cbb3d72632f87f2bc6d8e7799effd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-593::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-593', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 593, '7:9891a953fd1f059af0560ac4036f1055', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-594::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-594', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 594, '7:2c6d8b2f99e1f3cd2c0a307ab3fc4945', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-595::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-595', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 595, '7:49cdd61b848937b70fb866bda74e7e75', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-596::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-596', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 596, '7:f1f4bf2f63d64ced7d030f032f128e9e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-597::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-597', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 597, '7:f05356c1566e2431d0bb21fd34c0d3c2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-598::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-598', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 598, '7:88749d4fce15568a20d507d1c0f4b5cc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-599::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-599', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 599, '7:3c283cece8ecd889442fd651d161d3eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-600::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-600', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 600, '7:9266d0a1e449516100c797a05242904f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-601::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-601', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 601, '7:9b90cf13f458885ba85b84e3d669e187', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-602::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-602', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 602, '7:79ea2a4981c65a98f70d147a7af3a631', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-603::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-603', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 603, '7:4e3c7664692bfe02c5075d32aaa3feea', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-604::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-604', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 604, '7:c60681b15b363e31d7e2a9c25b402c5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-605::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-605', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 605, '7:14e136aa9593ac027d0462ee1a95d8f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-606::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-606', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 606, '7:39249aacf35e784f5da2991b3cc48841', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-607::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-607', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 607, '7:db8f52b1c7e91b11097c65d1555c0e01', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-608::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-608', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 608, '7:ad4536e8f584c6047b879e69a1d2a237', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-609::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-609', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 609, '7:dd656797aac3e317eb56dcc642adbc10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-610::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-610', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 610, '7:cdaa6c5fc7f8f938ce6dd0d81275362a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-611::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-611', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 611, '7:ae9bbfa41b93f82298ac713479a26317', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-612::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-612', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 612, '7:c0b2b8017235042b66e2b76175e206b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-613::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-613', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 613, '7:174d8f0bab0d384a810fca76763b1a74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-614::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-614', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 614, '7:7466658f6ed697c35c2351bc2c7995a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-615::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-615', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 615, '7:d8e3b4ded0f2f831df25033f37097a76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-616::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-616', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 616, '7:a1d64ea417decb51594d1ae7152b988e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-617::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-617', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 617, '7:2d8f03bd3061ff7c29868ab381b41a7e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-618::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-618', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 618, '7:d6fd2a1e3419941a628c17367b597a58', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-619::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-619', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 619, '7:34fa465db7e2c185b0dde50c82d3a992', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-620::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-620', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 620, '7:82f02712d8a53688c5fd92f495b2ea74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-621::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-621', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 621, '7:bfb4152b5195bdf5de0acb74530d61ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-622::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-622', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 622, '7:116bb8d9dea3baa696c5052958271b35', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-623::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-623', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 623, '7:a4ef23b0e3790d5616660bde901f0bc5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-624::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-624', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 624, '7:deb678fbebce0cb2e4f6ba2d4e590918', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-625::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-625', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 625, '7:9113f0324f5ed6110783f46bb61f04e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-626::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-626', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 626, '7:79a221a621a1a7579b821917fe7bb06e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-627::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-627', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 627, '7:dd01ea95a0e9868f3b7f1cb6a0cbc5e4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-628::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-628', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 628, '7:0f2f265384d76bc8c76cd8995697863a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-629::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-629', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 629, '7:3b4b23d7307267dfdfa19af7a4b28b95', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-630::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-630', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 630, '7:76e512b926865bfb86391505fa0929ae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-631::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-631', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 631, '7:7039d8de5c7d7b3766ceaa773337879e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-632::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-632', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 632, '7:0b8169503f36d596715cbd5b67019442', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-633::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-633', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 633, '7:84f6a21dda937aa5f5e6b641efdbd322', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-634::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-634', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 634, '7:67b5dccb01813f4661c89f91cca12fd1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-635::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-635', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 635, '7:40010db680b13286b6065eb8bcd32bbe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-636::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-636', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 636, '7:84e11f22b1a74b24eeba555ab49e428f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-637::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-637', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 637, '7:2cd321cf7c720b9a6a4fe244a88b1d89', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-638::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-638', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 638, '7:4fd0273c301ab43c044dd34b0d2d81df', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-639::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-639', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 639, '7:d3ec6c29d4c69186278cf3d74a3b6058', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-640::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-640', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 640, '7:3c554d679e301b60481200942c96653f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-641::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-641', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 641, '7:908fe4ffdbe525e421abfebf31546306', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-642::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-642', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 642, '7:15770c3b8ff4689498ae0d3e04af0fc5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-643::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-643', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 643, '7:9c3bddf28129fa057f83f31f9eef3b67', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-644::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-644', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 644, '7:bae500f3e8e83e1aceda293416ec5d4b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-645::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-645', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 645, '7:8060c27351aea9d27bd5727ea1e26254', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-646::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-646', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 646, '7:2246bfc808452a45833c7c625de8bf3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-647::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-647', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 647, '7:a2b69bcf79bd9c925a72adb4f5f81ebc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-648::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_nordwinkel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-648', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 648, '7:394f3ea3f3a65b0831ecb93625f221a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-649::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_abstandsmass ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-649', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 649, '7:e9376d94ab9ca6d4b5c08d5c1bce2323', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-650::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_anpflanzungbindungerhaltung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-650', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 650, '7:8cc7b398a17eb94830428619e8dd678e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-651::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-651', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 651, '7:80d8de8867547942b17406e77fa37409', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-652::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_festsetzungnachlandesrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-652', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 652, '7:6710c5dc22ae2b1b291a40ad987acf32', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-653::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-653', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 653, '7:77f0d996ba7d509194d5b3a908cf327c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-654::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_generischesobjekt ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-654', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 654, '7:bec3fc76fb05c76de548f0875e653c57', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-655::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_hoehenmass ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-655', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 655, '7:2508792a28a9b4a3a787a87be7917568', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-656::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_immissionsschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-656', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 656, '7:80a86e178dca7098fdabc66b38b6caca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-657::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_landwirtschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-657', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 657, '7:7fe0bd3ea3ca1e226be385f4b4868c72', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-658::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-658', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 658, '7:1eae4b5807403f7ce7001ad8f36889b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-659::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_strassenkoerper ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-659', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 659, '7:35118035b62cf6f77979f37c7d10cf52', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-660::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_unverbindlichevormerkung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-660', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 660, '7:97753f34999fa3f148df50d88cd1e8a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-661::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_verentsorgung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-661', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 661, '7:8b5d8202af20f404c2789aa793473d7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-662::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_wegerecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-662', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 662, '7:e3a2293827bcd21093442e8d22844b4f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-663::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-663', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 663, '7:1443296dc618a0308561ec03501e63a9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-664::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-664', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 664, '7:64d68edda5823df985a56343dbcd2c5b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-665::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-665', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 665, '7:5e153c105b1af301595d441554a31664', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-666::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-666', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 666, '7:f60a528e637170431ad13453c07c2e49', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-667::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-667', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 667, '7:fa87b7f92695c616def9a1e6110dd6ba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-668::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-668', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 668, '7:722724886118c71c477df9d7a4e42313', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-669::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-669', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 669, '7:2c00669205f9629961fe516bb26717e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-670::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-670', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 670, '7:df7e35dc98018ee9b512779234040973', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-671::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-671', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 671, '7:0890d83ea6231c179d6e4ad2bbaf20f5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-672::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-672', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 672, '7:6d5293c5e74e6767ee49bb01b2532782', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-673::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-673', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 673, '7:51dd6f0c56509ff67ed9d6aa43a11fe3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-674::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-674', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 674, '7:d6389d25207b167dfbe599c11f3753fe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-675::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-675', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 675, '7:0928d7f694a994d0a02d2cc19b48a856', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-676::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-676', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 676, '7:110515046befa17c0cb44abf7a3bf286', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-677::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-677', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 677, '7:478fa0b809a3574c67b57815963f9289', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-678::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_wasserwirtschaft ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-678', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 678, '7:a1c2910883950cdfccdd363b1008d6ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-679::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-679', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 679, '7:575b75da0fda158984c58d74acf0324f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-680::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-680', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 680, '7:914a011808d054913b0f245df5602a97', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-681::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-681', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 681, '7:3f390939781d8c08cd40de3d75f92be8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-682::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-682', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 682, '7:457fc9b75bf6fd1876df938db0f05a16', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-683::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-683', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 683, '7:b9fb0dea9010001d3f7de99abab3c4c6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-684::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-684', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 684, '7:039c12b7c2585a68ffdfa53edd2c0d35', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-685::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-685', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 685, '7:8f635ae0b91a03e1acd924d39f19155e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-686::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-686', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 686, '7:3de72ab46764cafc17f9411418b1b5e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-687::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-687', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 687, '7:ba847449f1148558421e133939d31995', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-688::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-688', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 688, '7:2ea5d19c8fb2357d17772b9cb0563c93', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-689::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-689', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 689, '7:0274b6f24922b7cb80a1ef1e48613f5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-690::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-690', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 690, '7:af1c61ed8325748f0baf00add14d6e3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-691::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-691', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 691, '7:0292be9332751c614cc9547cbf4f9fde', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-692::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-692', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 692, '7:acf1c6b0b65c8286484f13006497c078', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-693::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-693', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 693, '7:1ce854133ad531f934793995f2ae0dfa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-694::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-694', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 694, '7:fd79b684d6e9f368dcd6c0f510f49f0f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-695::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-695', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 695, '7:ad677019da0c8416cbd59dfa94ac459f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-696::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-696', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 696, '7:61ff60babdf3166561b27f157d3b9bdb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-697::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-697', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 697, '7:356370fbd7cc6c2da19fd511d5f00c03', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-698::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-698', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 698, '7:9a88112b759337645c12cef7c9ad3483', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-699::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-699', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 699, '7:8a6b39b30160533d747b59eb5d6b9ef4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-700::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-700', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 700, '7:1693850732686776372c78c1e5348d2e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-701::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-701', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 701, '7:1a0a526174b01bdabc4b4b2a874a62db', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-702::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-702', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 702, '7:5c6d02a866798d947e4bc3865fcc7e87', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-703::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-703', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 703, '7:a48c01d62dbaeed4f26b2ab149e4b76d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-704::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-704', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 704, '7:26ed367f6784d70bcb7a9d8e3759b40e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-705::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-705', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 705, '7:a932627c236e2491d5089c0b0fd0a4e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-706::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-706', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 706, '7:dffb4e4388d2ae39fd08b73eea2be045', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-707::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-707', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 707, '7:8e301e1131833b29859ad975dbd6f117', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-708::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_nordwinkeluom TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-708', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 708, '7:4af4c480dac2ed06f2f9181b6b5d398b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-709::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_plan ADD xplan_planart TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-709', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 709, '7:0ea41014d4b7efe80cb898a423949b86', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-710::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_plan ADD xplan_planartcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-710', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 710, '7:4393c5df24e5c6aaef2eeaff250783fb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-711::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_primaerenergietyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-711', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 711, '7:a96126413722b2725cba47fe354b0bad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-712::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_primaerenergietypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-712', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 712, '7:bd37ac2737760901d219c94dd2c76197', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-713::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textabschnitt ADD xplan_rechtscharakter TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-713', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 713, '7:a56451ad548ce8a88d78a0e80a515443', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-714::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textabschnitt ADD xplan_rechtscharaktercode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-714', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 714, '7:4a500595b15b79f917bf15ce11acaf68', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-715::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_refgebaeudequerschnitt TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-715', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 715, '7:aecd5337ad36a7a572d117ba8426091d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-716::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_sonstigertyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-716', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 716, '7:6fc24b5ff1f780c95a472ee43c48c957', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-717::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_sonstigertypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-717', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 717, '7:946320bd88deb61771144c67212eef2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-718::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsflaeche ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-718', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 718, '7:84dc226909ef3a5ddfa40aa0a216ca50', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-719::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ausgleichsmassnahme ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-719', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 719, '7:0346d47339e9f2daf1bea555291fe9ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-720::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-720', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 720, '7:7e0bc7778a2284a83fb6a8a55868a451', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-721::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-721', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 721, '7:9228112cd5c324e44ecb80bd0433759d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-722::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-722', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 722, '7:16544a779dcfac680fc03dcfa8cae111', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-723::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_sonstziel TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-723', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 723, '7:994fb8b0dd95e93c0f0c9134413a23ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-724::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_spannung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-724', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 724, '7:9d70da3c2c81d38c45aca342878e3221', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-725::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_spannungcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-725', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 725, '7:348b07e5282d8b361f9831d3e2179bfc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-726::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_spezifischertyp TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-726', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 726, '7:b0367768fcc37425131d04d5ce38d170', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-727::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_spezifischertypcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-727', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 727, '7:d92573894c9d131eb4e0517294ad6e28', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-728::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-728', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 728, '7:816a9a4aac0bd04e013ff83ffe5d20e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-729::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_abgrabungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-729', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 729, '7:13ffe9bdbfcd76748bfda30beb08cc37', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-730::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_anpassungklimawandel ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-730', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 730, '7:9af711d5b8b0bc76aeccbd868152ee50', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-731::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-731', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 731, '7:955a61d028b16cc9f079ef3349ac92d6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-732::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_aufschuettungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-732', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 732, '7:19956b1240b0c778ba6720f9c8557a0b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-733::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_ausgleichsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-733', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 733, '7:cc4f134a06b49f2ea6ee6c4b334caa93', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-734::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bebauungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-734', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 734, '7:345fd5dcda0ff6c121302564f8980a7d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-735::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetze ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-735', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 735, '7:7de87f88eca71b173d56464f97e24570', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-736::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bodenschaetzeflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-736', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 736, '7:8657b146c93cf8337a400c2db9646109', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-737::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gemeinbedarf ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-737', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 737, '7:7dff7ddab603dc719f3afc8599d67ce2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-738::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_generischesobjekt ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-738', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 738, '7:1c5266935f361f53734527e91666064c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-739::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gewaesser ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-739', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 739, '7:ce68aa8c8d9a7e5cb7b36e5a966debc0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-740::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_gruen ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-740', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 740, '7:80bad0646abd364d0ca1d504f2d3f05a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-741::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-741', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 741, '7:2eca45b4cc50b587cad94432a117fc83', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-742::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_kennzeichnung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-742', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 742, '7:3d95fd882303b5a73fe7a06c4cac2340', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-743::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_landwirtschaftsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-743', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 743, '7:be03362095d9198e1a0125154ed245cc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-744::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-744', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 744, '7:68bc6b030b79e0bce57d8ac52b781843', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-745::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_privilegiertesvorhaben ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-745', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 745, '7:da94e2efe40c4c1bf412cb9feca97152', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-746::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_priviligiertesvorhaben ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-746', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 746, '7:ab9c921f09148d6a5e4e3cae446cb5f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-747::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_schutzpflegeentwicklung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-747', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 747, '7:765462f6347b48213f4a6c445930ea2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-748::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_spielsportanlage ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-748', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 748, '7:4e55dff557d5f077f38a12c1b0cd3315', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-749::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_strassenverkehr ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-749', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 749, '7:2e07f7d1d27da12d852b07959f297d12', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-750::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_textlichedarstellungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-750', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 750, '7:0ba13b14d5c8b9305000b53675576ab9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-751::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_unverbindlichevormerkung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-751', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 751, '7:c3fa7d4f1e191418f8e5bee996199091', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-752::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-752', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 752, '7:cc290ececae21cc5c4dfc1ef85e10abd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-753::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_vorbehalteflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-753', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 753, '7:9e3c6d831da0aa3d06460ae4c8da95f5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-754::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_waldflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-754', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 754, '7:61e000a3268c7c3520623a3545bea48d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-755::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_zentralerversorgungsbereich ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-755', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 755, '7:49b3173c0cb157897fd73f159cef8eea', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-756::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_abgrenzung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-756', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 756, '7:cade8d3621319bd28de4acf4be63080f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-757::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_allggruenflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-757', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 757, '7:350e4673f14288afb0fa5592012fffa7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-758::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_anpflanzungbindungerhaltung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-758', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 758, '7:d2fc40fa36095a18027ef60c4e7a4bf0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-759::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_ausgleich ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-759', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 759, '7:336b3e3c0a727fc3f2432d0e2f6b960a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-760::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_biotopverbundflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-760', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 760, '7:bd2b960bf20b997fd193c317345a835a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-761::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_bodenschutzrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-761', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 761, '7:713a73483209dee6867f43151f716cc9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-762::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_denkmalschutzrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-762', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 762, '7:dfba61d32e023a6ba79fb3f221bd6dc1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-763::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_erholungfreizeit ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-763', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 763, '7:5c655e7204306c53563b94fc02ed0cb5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-764::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_forstrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-764', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 764, '7:08eb337692fadebf422dbfe908802f85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-765::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-765', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 765, '7:f4c5b8fbdf74bc8483eb9c407a4b6b66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-766::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_landschaftsbild ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-766', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 766, '7:e2166f72ed7a85565aeeec0a27790877', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-767::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungsausschluss ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-767', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 767, '7:0fc1990894ea0d1e3082bbeeb397cd3c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-768::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_nutzungserfordernisregelung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-768', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 768, '7:5773b5f6213de78a7cdc12a93e1f70d2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-769::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_planerischevertiefung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-769', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 769, '7:29ff6833ed84dd7e0ab02497266a13d0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-770::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektbundesrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-770', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 770, '7:6428b2c96bb945bb3d8be0aefaacf504', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-771::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektinternatrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-771', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 771, '7:5d4a65eee4a6c3fa57cfed1ca19b9357', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-772::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzpflegeentwicklung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-772', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 772, '7:8f75e63cd99259b8e154226e3eaa7d6d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-773::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_sonstigesrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-773', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 773, '7:7f02589be22bf30e210454c9168b2f55', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-774::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_textlichefestsetzungsflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-774', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 774, '7:70bc58fe7aed2d9ee31b505245331500', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-775::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-775', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 775, '7:6c05920a6be5687e63199ff8009fa05c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-776::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtschutzgebiet ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-776', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 776, '7:2fca615402c2b90c2caf2fd6afb41cf3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-777::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtsonstige ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-777', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 777, '7:2448eaf8bdaf1038f90cd5948629cb3a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-778::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-778', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 778, '7:93928c5fa6cf2f37e0d88364195fd918', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-779::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-779', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 779, '7:8b554ea474fa9606dbe64c49f06c77a8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-780::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_zwischennutzung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-780', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 780, '7:ae2749982720a2d1325aa7865cf85d45', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-781::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-781', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 781, '7:d240e4280f97e5185a05768fd1844c3d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-782::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-782', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 782, '7:13d1174871457be428e9193323d12b40', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-783::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_energieversorgung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-783', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 783, '7:015e754e6a8ca2e3620ffd7bbe92863b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-784::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-784', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 784, '7:a434017697149cf1ed45413b01fbb09f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-785::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-785', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 785, '7:bf3ee4bcac16f9870dc6d2b02d29caf3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-786::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freizeiterholung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-786', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 786, '7:e049c2431181ae060897fc1dd60500bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-787::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-787', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 787, '7:c056a2dc2cb13a8efd5ab6aef7d4ef1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-788::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-788', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 788, '7:8aff66b8f53746143f08db7214ccaffb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-789::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gewaesser ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-789', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 789, '7:9173e1217e45f172a32cfb87feb2ee45', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-790::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_grenze ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-790', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 790, '7:37d56a33f4c2e6d02c2624ad576e4a61', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-791::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-791', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 791, '7:dce0c5b47496447081e3e7c72864208f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-792::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-792', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 792, '7:d24257b6349a58c4a358ead77ccf672b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-793::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-793', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 793, '7:3c17769d6abc456d09fc04855200928b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-794::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturellessachgut ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-794', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 794, '7:836569e256c7c32d01431ee1b0efd990', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-795::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbereich ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-795', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 795, '7:6fd11148feace8f62902f6c94884e7a8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-796::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-796', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 796, '7:c74d9ab1eaab8efdda69942e60bb61c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-797::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-797', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 797, '7:8deefbd1c5c47ebf582fde2b9643731f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-798::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-798', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 798, '7:4b60b7dcfc7fb9322d9e348136c161ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-799::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-799', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 799, '7:6e68ea4a6241bfb2865c87d307eddc00', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-800::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoffsicherung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-800', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 800, '7:6846f9db26b3980c26429db2560e03a6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-801::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigeinfrastruktur ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-801', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 801, '7:0cf7cf9b22ede30b5b73c2e1638da8bb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-802::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumstruktur ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-802', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 802, '7:afa9139610bb28fd2bcb1584e4f3568d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-803::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigesiedlungsstruktur ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-803', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 803, '7:3330bd1f4bf6f6f144dcbde667bba385', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-804::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sozialeinfrastruktur ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-804', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 804, '7:32ca7a9a221f730551f32a7020a8df34', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-805::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-805', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 805, '7:8cf9742705cdd3b8d13105e71e65ec59', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-806::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-806', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 806, '7:78b08dda3bc883d15db706a7d193f4e7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-807::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_vorbhochwasserschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-807', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 807, '7:c6e19fd7e7d9a0d16b5eb80d9cb7e6e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-808::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-808', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 808, '7:267e73b75540b3bdec7d287dfcbaf9f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-809::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserwirtschaft ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-809', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 809, '7:ab99faf671c32714c6a913f5c137a934', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-810::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_windenergienutzung ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-810', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 810, '7:39239396d0afaf1c6a902df063d34213', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-811::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-811', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 811, '7:434b23ea7ff7233c3d35254d4f08fcae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-812::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_bodenschutzrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-812', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 812, '7:638d5a565c61d8eac8c089527daa06fa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-813::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_denkmalschutzrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-813', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 813, '7:022f6ba8afc51d05f7068b857424cdfa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-814::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_forstrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-814', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 814, '7:79eff71c83f17672b2f27514adeae8e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-815::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_gebiet ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-815', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 815, '7:0d6059a09331a551e23a4afb33d1f401', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-816::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_grenze ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-816', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 816, '7:68ef6d830c36d2faf73e444fcba01581', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-817::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_linienobjekt ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-817', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 817, '7:566e469cd83c3204855abcec8b0a5976', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-818::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_luftverkehrsrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-818', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 818, '7:922f26639c773b83c617932b1a24717d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-819::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_objekt ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-819', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 819, '7:45a24a5755cc4546edc38bdef2453707', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-820::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schienenverkehrsrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-820', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 820, '7:bf60bfb4d43207515d20bb0e8a08fc00', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-821::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietnaturschutzrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-821', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 821, '7:75bce1e1e0f83fc4ec2f2310439eeca2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-822::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietsonstigesrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-822', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 822, '7:f51bd4af3908b7ac5cc9f0e7614c7dd7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-823::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_schutzgebietwasserrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-823', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 823, '7:4403b72e00af8289c09285f70871cab9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-824::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_sonstigesrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-824', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 824, '7:5112f4bb407f80f1b765317717200f0b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-825::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_strassenverkehrsrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-825', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 825, '7:f26bafa3814545e9233bc061e01e366f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-826::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_so_wasserrecht ADD xplan_startbedingung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-826', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 826, '7:4d9688d685ebc03a03bb9cbb25f025a2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-827::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_status TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-827', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 827, '7:6606fa2832e9ef174e4753619ff2b427', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-828::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_verkehr ADD xplan_statuscode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-828', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 828, '7:2932fe06f741af28ec639e5f896e42f9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-829::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_einfahrtpunkt ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-829', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 829, '7:5bcb912acd2d7b8d07e95eccc25ea453', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-830::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-830', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 830, '7:422524d07098911c8eef7f1a0414f558', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-831::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-831', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 831, '7:4a3c894b63e50f4af7b715d998566203', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-832::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-832', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 832, '7:297810768dbb2ad09f1c40be3ba0e9e5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-833::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-833', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 833, '7:12f87120c5e39c0dab8c73087e87acfc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-834::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-834', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 834, '7:b710aa608a050339673b03c5ba4133c5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-835::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-835', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 835, '7:92c8aa1a77ee485a21d31255979ce5a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-836::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-836', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 836, '7:082ccc3fe1ae932b1244a62837e574a1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-837::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-837', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 837, '7:b0e82d0c156acf46e5c872b3bf195034', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-838::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-838', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 838, '7:1bfd9602e76b16bd96921d4ee6cf082e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-839::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-839', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 839, '7:f9fa4bc2f1942bf2f8d4dc239b1ea78d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-840::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-840', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 840, '7:a2a557e9abc2e2ac9358f04cec1e6a6f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-841::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-841', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 841, '7:74c55ae8454ddbbd8eed467b2427d59d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-842::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-842', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 842, '7:5e2660b0ba0bf091498e2c472efee235', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-843::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_typ TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-843', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 843, '7:8f982dad762827e8cb20bc8b502f7407', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-844::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_typae TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-844', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 844, '7:14f7cdde82d8ac5a8c11d9dfd4946d69', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-845::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_typaecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-845', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 845, '7:a0016b8854608dc8f2d00db7c197b466', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-846::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_typaw TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-846', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 846, '7:73066713a715c576dae13dc90705d564', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-847::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_entsorgung ADD xplan_typawcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-847', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 847, '7:6513693f6717e1fe7e0c7c13be427d4d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-848::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_achse ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-848', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 848, '7:a737d9aa7905e0e4e43c8dbe015c2c38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-849::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_bodenschutz ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-849', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 849, '7:fa97556914babf351825bfb93ba9cf79', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-850::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_forstwirtschaft ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-850', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 850, '7:da2a3248c23afad36d2f1a572496e910', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-851::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_generischesobjekt ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-851', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 851, '7:c915091636b8ca651f1327939ee52c4e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-852::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_gruenzuggruenzaesur ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-852', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 852, '7:eafc843d1cc3496d08fec7cabf457c06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-853::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_klimaschutz ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-853', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 853, '7:18dcb94cc790a520d47fce26abff5653', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-854::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kommunikation ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-854', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 854, '7:431fb4e72db9a48274087618d8fa05ba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-855::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_landwirtschaft ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-855', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 855, '7:dd87764dd3d8ef53095e74dbeb5f5bb4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-856::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturlandschaft ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-856', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 856, '7:61269635b6b47944e31991a1d6be148a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-857::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-857', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 857, '7:c88287d6035356f0a40de500824b0220', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-858::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_raumkategorie ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-858', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 858, '7:1f3c7ad7fc80a953e4fa3ba19af72888', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-859::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sperrgebiet ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-859', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 859, '7:3c8acd675a38becb0739436e45edd43c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-860::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserschutz ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-860', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 860, '7:82f90cf6cea9095b1486c7d83c1aa8e2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-861::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_zentralerort ADD xplan_typcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-861', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 861, '7:229d21d51e8eb863f18b21f15536b9d1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-862::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_verfahren TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-862', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 862, '7:fe7a325151886a1d480174008d6bca49', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-863::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_plan ADD xplan_verfahrencode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-863', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 863, '7:20074dd257112f1595311c3231cfd209', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-864::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereich ADD xplan_versionbaunvodatum date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-864', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 864, '7:08a3c8ebfe8559bfe00d14d4af1290eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-865::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bereich ADD xplan_versionbaunvodatum date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-865', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 865, '7:9aaa22e163c562ced588312f451e9dd4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-866::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereich ADD xplan_versionsonstrechtsgrundlagedatum date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-866', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 866, '7:8ee9eed3c30478d564d42a7173d1a2f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-867::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bereich ADD xplan_versionsonstrechtsgrundlagedatum date;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-867', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 867, '7:e0f74984a4563fb3a156e697603108dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-868::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_bereich ADD xplan_versionsonstrechtsgrundlagetext TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-868', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 868, '7:2081feab6571b1626af851059779c428', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-869::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_bereich ADD xplan_versionsonstrechtsgrundlagetext TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-869', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 869, '7:719bb7abb84bfd9746fff7e94f8224cd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-870::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_vertikaledifferenzierung TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-870', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 870, '7:e08a54a252b7d82dd97cafe2659cf6a7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-871::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_wohnnutzungegstrasse TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-871', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 871, '7:c3de7012098a7ee7dbc92349ecce1061', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-872::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_wohnnutzungegstrassecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-872', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 872, '7:36f478ffd599858c5032f44ff71dfa9d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-873::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_wohnnutzungwgstrasse TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-873', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 873, '7:a67983db21c32bade0ced85f41113c2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-874::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_wohnnutzungwgstrassecode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-874', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 874, '7:69da4c06406d5a7e3f956036f9988107', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-875::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_fp_verentsorgung ADD xplan_zugunstenvon TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-875', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 875, '7:e0b67c860714cb82a3e6e01593708818', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-876::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_generischesobjekt ADD xplan_zweckbestimmungcode TEXT;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-876', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 876, '7:61a3a3a9fdb115e0dfb9ecb9cc26ae7b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-877::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_baugebietsteilflaeche ADD xplan_zwohn INT4;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-877', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 877, '7:4dea68f5ffe6b8479d79aabcc524c0fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-878::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_zwohn INT4;

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-878', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 878, '7:d5cba29f08b4a9a58d9614932397b25d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-879::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_bp_technischemassnahmenflaeche ADD CONSTRAINT xplan_bp_technischemassnahmenflaeche_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-879', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 879, '7:673a6f7daafb2a9e5a381b41ed3e42c6', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-880::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_lp_schutzobjektlandesrecht ADD CONSTRAINT xplan_lp_schutzobjektlandesrecht_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-880', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 880, '7:501326f2b65c9eb8c766d2287cd8be97', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-881::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_einzelhandel ADD CONSTRAINT xplan_rp_einzelhandel_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-881', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 881, '7:49244e1ed7da7cee21a0fb76ef6db4fb', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-882::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_erholung ADD CONSTRAINT xplan_rp_erholung_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-882', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 882, '7:ea99502761b89a4ef9ad3d743911a66a', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-883::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_erneuerbareenergie ADD CONSTRAINT xplan_rp_erneuerbareenergie_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-883', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 883, '7:053932c378943ca88ed1f284f9b87f03', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-884::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_freiraum ADD CONSTRAINT xplan_rp_freiraum_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-884', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 884, '7:4f2571cd77e233f14af6dc950367a7fb', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-885::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_funktionszuweisung ADD CONSTRAINT xplan_rp_funktionszuweisung_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-885', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 885, '7:8cc7a23f7fca2d891edb11a03c3aeea0', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-886::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_hochwasserschutz ADD CONSTRAINT xplan_rp_hochwasserschutz_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-886', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 886, '7:6774bf5e88db95403cf19299a04c0cee', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-887::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_industriegewerbe ADD CONSTRAINT xplan_rp_industriegewerbe_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-887', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 887, '7:8dbd523da8bd52ca22710ce4fb002928', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-888::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_kulturlandschaft ADD CONSTRAINT xplan_rp_kulturlandschaft_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-888', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 888, '7:f0d0547112e1bd8b00d8533aa3c99a59', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-889::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_laermschutzbauschutz ADD CONSTRAINT xplan_rp_laermschutzbauschutz_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-889', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 889, '7:dbbb6502a6e7fbd00384c6f45e0f82df', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-890::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_legendenobjekt ADD CONSTRAINT xplan_rp_legendenobjekt_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-890', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 890, '7:e21582054e85e144d565aa5d96679596', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-891::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_luftverkehr ADD CONSTRAINT xplan_rp_luftverkehr_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-891', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 891, '7:aa7de77a18e80a1137ca670b14fe26bc', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-892::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_planungsraum ADD CONSTRAINT xplan_rp_planungsraum_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-892', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 892, '7:203db33c98c14c61cf4b85fbed7b4b1c', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-893::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_radwegwanderweg ADD CONSTRAINT xplan_rp_radwegwanderweg_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-893', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 893, '7:e53c56c6967f7d0ff0b791b406822d70', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-894::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_rohstoff ADD CONSTRAINT xplan_rp_rohstoff_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-894', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 894, '7:12b2c87af1b3f20093f2ebfaca1416bf', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-895::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_schienenverkehr ADD CONSTRAINT xplan_rp_schienenverkehr_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-895', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 895, '7:eaed94d533c59da81f9f42e92e8b3a56', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-896::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_siedlung ADD CONSTRAINT xplan_rp_siedlung_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-896', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 896, '7:ad3de12eb2fa0c38653e495e7839da2b', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-897::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigerfreiraumschutz ADD CONSTRAINT xplan_rp_sonstigerfreiraumschutz_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-897', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 897, '7:9d61e7dc09355cc153d1112a1a2b2858', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-898::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstigersiedlungsbereich ADD CONSTRAINT xplan_rp_sonstigersiedlungsbereich_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-898', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 898, '7:e3b49743a528b32ce720501333732d00', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-899::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sonstverkehr ADD CONSTRAINT xplan_rp_sonstverkehr_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-899', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 899, '7:f0b4653cf347e1ee9806f9dbd7c33356', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-900::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_sportanlage ADD CONSTRAINT xplan_rp_sportanlage_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-900', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 900, '7:ced47c447ebc8ee6bc9d2707402d3ba3', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-901::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_strassenverkehr ADD CONSTRAINT xplan_rp_strassenverkehr_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-901', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 901, '7:bf448ebe8f6fb426e0aed24267e5935b', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-902::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wasserverkehr ADD CONSTRAINT xplan_rp_wasserverkehr_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-902', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 902, '7:e35d03110c90f3f9413f31ce682ba6de', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-903::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_rp_wohnensiedlung ADD CONSTRAINT xplan_rp_wohnensiedlung_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-903', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 903, '7:1049a51e964fcb9844007f0a9ed1c661', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-904::lgvxplanisk (generated)
ALTER TABLE xplansynpre.xplan_xp_rasterdarstellung ADD CONSTRAINT xplan_xp_rasterdarstellung_pkey PRIMARY KEY (attr_gml_id);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-904', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 904, '7:f008646bb5a124e587e7e40715ded2d4', 'addPrimaryKey', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-905::lgvxplanisk (generated)
CREATE INDEX spatial_idx_465 ON xplansynpre.xplan_rp_einzelhandel(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-905', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 905, '7:1cd246042398b04f52b8fa598f75a317', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-906::lgvxplanisk (generated)
CREATE INDEX spatial_idx_466 ON xplansynpre.xplan_rp_erholung(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-906', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 906, '7:1aa1c3afda7ad5aa0cea1a026f50b733', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-907::lgvxplanisk (generated)
CREATE INDEX spatial_idx_467 ON xplansynpre.xplan_rp_erneuerbareenergie(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-907', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 907, '7:3c532bdbfc4c9708a077436c65ce5cd3', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-908::lgvxplanisk (generated)
CREATE INDEX spatial_idx_468 ON xplansynpre.xplan_rp_freiraum(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-908', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 908, '7:eb2e1fb61453e810781c40b772de7b41', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-909::lgvxplanisk (generated)
CREATE INDEX spatial_idx_469 ON xplansynpre.xplan_rp_funktionszuweisung(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-909', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 909, '7:07e57fcde073dde09e5c0cf7ce0b252d', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-910::lgvxplanisk (generated)
CREATE INDEX spatial_idx_470 ON xplansynpre.xplan_rp_industriegewerbe(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-910', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 910, '7:56036952db21cb8317f863960b97c178', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-911::lgvxplanisk (generated)
CREATE INDEX spatial_idx_472 ON xplansynpre.xplan_rp_kulturlandschaft(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-911', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 911, '7:d8d25944632f92754eecef0ea29b5f73', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-912::lgvxplanisk (generated)
CREATE INDEX spatial_idx_473 ON xplansynpre.xplan_rp_laermschutzbauschutz(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-912', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 912, '7:6622594344ecf47f7a52004bcb5c78d8', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-913::lgvxplanisk (generated)
CREATE INDEX spatial_idx_474 ON xplansynpre.xplan_rp_luftverkehr(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-913', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 913, '7:a6de7efbace2c20ab2de93309d4e77d6', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-914::lgvxplanisk (generated)
CREATE INDEX spatial_idx_475 ON xplansynpre.xplan_rp_planungsraum(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-914', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 914, '7:bae18fcdab33d2ed2798d1ab85d3366a', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-915::lgvxplanisk (generated)
CREATE INDEX spatial_idx_476 ON xplansynpre.xplan_rp_radwegwanderweg(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-915', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 915, '7:d9b17f427bdf505c2395e64802f9b78b', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-916::lgvxplanisk (generated)
CREATE INDEX spatial_idx_477 ON xplansynpre.xplan_rp_rohstoff(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-916', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 916, '7:dccef70c4885cd7345d23c3253ca5382', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-917::lgvxplanisk (generated)
CREATE INDEX spatial_idx_478 ON xplansynpre.xplan_rp_schienenverkehr(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-917', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 917, '7:60f164e9111377b273f04acde60ee3f9', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-918::lgvxplanisk (generated)
CREATE INDEX spatial_idx_479 ON xplansynpre.xplan_rp_sonstigerfreiraumschutz(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-918', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 918, '7:3b55ce524ca8e0c183694293075b06e9', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-919::lgvxplanisk (generated)
CREATE INDEX spatial_idx_480 ON xplansynpre.xplan_rp_sonstverkehr(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-919', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 919, '7:6dc959dd01d61c9114ce763d33259652', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-920::lgvxplanisk (generated)
CREATE INDEX spatial_idx_482 ON xplansynpre.xplan_rp_sportanlage(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-920', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 920, '7:ccbde612fe5443c3dda13d8791978943', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-921::lgvxplanisk (generated)
CREATE INDEX spatial_idx_483 ON xplansynpre.xplan_rp_strassenverkehr(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-921', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 921, '7:71cb072e383cb441f0dd32108ba66de2', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-922::lgvxplanisk (generated)
CREATE INDEX spatial_idx_484 ON xplansynpre.xplan_rp_wasserverkehr(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-922', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 922, '7:09211f1416320c3f2b3293bdbf73cc98', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-923::lgvxplanisk (generated)
CREATE INDEX spatial_idx_485 ON xplansynpre.xplan_rp_wohnensiedlung(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-923', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 923, '7:eda1fa4a3698f894d0bf41a36d0d6894', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-924::lgvxplanisk (generated)
CREATE INDEX spatial_idx_486 ON xplansynpre.xplan_rp_sonstigersiedlungsbereich(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-924', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 924, '7:2a54db0db9949cba425ba02c021349dd', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-925::lgvxplanisk (generated)
CREATE INDEX spatial_idx_487 ON xplansynpre.xplan_rp_siedlung(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-925', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 925, '7:2d235013da184347e246836d220edb06', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-926::lgvxplanisk (generated)
CREATE INDEX spatial_idx_488 ON xplansynpre.xplan_rp_hochwasserschutz(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-926', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 926, '7:4fb684c9995a3c7e574955acd6f80436', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-927::lgvxplanisk (generated)
CREATE INDEX spatial_idx_489 ON xplansynpre.xplan_lp_schutzobjektlandesrecht(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-927', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 927, '7:befc89fb0cbb5622c62a4ec22e4b4171', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansynpre.xml::1540475405117-928::lgvxplanisk (generated)
CREATE INDEX spatial_idx_490 ON xplansynpre.xplan_bp_technischemassnahmenflaeche(xplan_position);

INSERT INTO xplansynpre.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1540475405117-928', 'lgvxplanisk (generated)', 'changelog_xplansynpre.xml', NOW(), 928, '7:74c6ccdeb20ad2fc4edb57a0588dc015', 'createIndex', '', 'EXECUTED', '3.3.2');

-- Release Database Lock
UPDATE xplansynpre.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

