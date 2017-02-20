-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: changelog_xplansyn.xml
-- Ran at: 08.04.15 13:13
-- Against: lgvxplanisk@jdbc:postgresql://localhost:5433/updatemgr011
-- Liquibase version: 3.3.2
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE xplansyn.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM xplansyn.databasechangeloglock;

INSERT INTO xplansyn.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE xplansyn.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'lgvxplanisk (fe80:0:0:0:216:3eff:fe0c:7064%2)', LOCKGRANTED = '2015-04-08 13:13:50.632' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE xplansyn.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20));

-- Changeset changelog_xplansyn.xml::1428411796051-1::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-1', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 1, '7:aa59774aeee68dad73e574bf16173fe8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-2::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-2', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 2, '7:a1c0b604ab714748838ced2188571aac', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-3::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bodenschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-3', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 3, '7:ee00d0b7b3b39402e0b117df56f701d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-4::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_energieversorgung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-4', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 4, '7:2130404ad58aeac5e88fc31737946038', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-5::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entsorgung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-5', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 5, '7:0c0b21d1ded89798ff3426b4c2ce636c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-6::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entwicklungsschwerpunkte ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-6', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 6, '7:d5dd244dff664a446b124c44bdd96f58', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-7::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_forstwirtschaft ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-7', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 7, '7:a0f09c2e08f351353e5b58fdf24033c0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-8::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_freizeiterholung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-8', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 8, '7:889c5abfa818f315c2dbbafeba5ce1eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-9::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-9', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 9, '7:1fbf7e1697e947777895150b5a03f49a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-10::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_generischesobjekt ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-10', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 10, '7:fdcd6372f535849f71cf68a1a954149c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-11::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gewaesser ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-11', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 11, '7:af33f06bb15f32b3a0092fe41247691a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-12::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_grenze ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-12', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 12, '7:0691963d4b0ec3eb3d00b8cb65fa1065', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-13::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gruenzuggruenzaesur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-13', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 13, '7:785d7ba4b27a0eb3c5dd179aa84fe019', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-14::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_klimaschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-14', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 14, '7:3d6d8b35e810d5c831a0511de8570abc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-15::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kommunikation ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-15', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 15, '7:b3b8912e4dec9e9959d2bbacccbe5782', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-16::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kulturellessachgut ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-16', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 16, '7:73933142be3e3c4cd854721470976436', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-17::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_laermschutzbereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-17', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 17, '7:b987e04204eeba644d1df80946a04e4b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-18::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_landwirtschaft ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-18', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 18, '7:b8e062d1c5804f61bfad948aaf4e543f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-19::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturlandschaft ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-19', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 19, '7:0e6d9f94a30e4e759f21c5c501f08860', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-20::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-20', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 20, '7:7334bb6aeea68fd43426b6e5f5a38631', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-21::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_asb ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-21', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 21, '7:ee28eaa3d4230a4f8dcbd9e417c3b940', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-22::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_aufschuettungablagerung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-22', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 22, '7:f0d4b7e2edce6651d3792690c39e0070', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-23::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_forstwirtschaft ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-23', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 23, '7:026fc42c163fc3890c2afbbed13724a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-24::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_freiraumagrarbereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-24', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 24, '7:db2573e0856f46a9cb3ff18f865727fa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-25::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_gib ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-25', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 25, '7:714e1f7431a5d9a14580c7f4057af535', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-26::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_grundwassergewaesserschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-26', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 26, '7:5e68b6a7ef4a689d4d41ef780378c8c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-27::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_laermschutzzone ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-27', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 27, '7:c3abe8c3c695e921916c9714eb8d27b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-28::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_landschaftsschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-28', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 28, '7:753a714efe769a93828ee32b43c04b14', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-29::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_luftverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-29', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 29, '7:7d8794bda1cef1f9c07e7aec8af75847', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-30::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_naturschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-30', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 30, '7:55d39b9800088947bf91a1916de5c500', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-31::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechengewaesser ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-31', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 31, '7:0ec42b13d9b626ce68b90782d2f5a534', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-32::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechennahebodenschaetze ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-32', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 32, '7:5019c10b9bd40afea0b06520cba08493', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-33::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_schienenverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-33', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 33, '7:28eb2966003f649425e3dd9b661e3c39', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-34::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigeinfrastruktur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-34', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 34, '7:b413868556458cee43c82f588b288706', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-35::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigersiedlungsbereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-35', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 35, '7:7bdf4bed1db4392f1e79b77cd457690c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-36::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigezweckbindung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-36', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 36, '7:168e18f49065eb7053629e5b335cd52d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-37::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-37', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 37, '7:27ff1c89a7eb1924d8e1929cdf39f483', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-38::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_strassenverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-38', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 38, '7:0f16271046cf49582a52e323e5a53bb8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-39::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_ueberschwemmungsbereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-39', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 39, '7:471b5e2e0f1abc5c0422f7946d7d8dda', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-40::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_wasserverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-40', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 40, '7:170c040f5467d1836f6774dd9f9f2cc0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-41::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_zeitlinie ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-41', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 41, '7:60ee7f816a577155c40df80a8a24dede', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-42::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abfallentsorgung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-42', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 42, '7:2b20069f3aec9aed97c7087424a9c868', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-43::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abwasser ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-43', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 43, '7:899c0907b3e30fab9c8765c6a08db6f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-44::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_asb ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-44', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 44, '7:0c0a6e91afb9b97105e90cdb53c98443', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-45::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_erholung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-45', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 45, '7:07a60c427db889835c0338404a714c6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-46::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_gib ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-46', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 46, '7:3c44b47d43232e253ff5b1d970e3634c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-47::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_landschaftsschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-47', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 47, '7:ee4efd832af690d4d281f71463b46a0f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-48::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_luftverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-48', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 48, '7:2d1e658a1963c50f175265db0603b5c1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-49::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_naturschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-49', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 49, '7:a4e7b043f9b68be0a7ea4ae4442e837f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-50::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_oberflaechennahebodenschaetze ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-50', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 50, '7:f3c4b652d92792081d8ffd087e0cb43d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-51::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamerwanderweg ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-51', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 51, '7:5a88b0c5b6c48d3859cf26f42452fdf3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-52::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamesportanlage ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-52', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 52, '7:3bd6010aac5e7d85fdec3e6b300c2acf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-53::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_schienenverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-53', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 53, '7:dc6adee7394b24771562032b0d76a374', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-54::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_sonstverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-54', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 54, '7:b99b81958014b3ebe1e1b4ce79e1eb1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-55::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_strassenverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-55', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 55, '7:140021fec27348d162f4fe21b61cab5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-56::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tiefliegenderohstoffe ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-56', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 56, '7:3e31f863d0fa02036a3c0bc6804ca90a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-57::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tourismus ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-57', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 57, '7:770da73c1e15c74b1936ad32e7df881f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-58::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_wasserverkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-58', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 58, '7:295f46a60fc400d5f18dd2d2beb8f3a7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-59::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-59', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 59, '7:6b2be33c78217a0250ded2dc7fd55ad3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-60::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoffsicherung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-60', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 60, '7:01d4a83c7823d1a65b89911cda0dbd48', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-61::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigeinfrastruktur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-61', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 61, '7:f791bce3268210ccd67d1fde68fc4511', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-62::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigerfreiraumstruktur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-62', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 62, '7:79846428873194ff749224d57a87fe84', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-63::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigesiedlungsstruktur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-63', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 63, '7:50d53c669ac474c9133ce33defeef35d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-64::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sozialeinfrastruktur ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-64', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 64, '7:321ceaee441d6a2f20be39209eb96c57', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-65::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-65', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 65, '7:9e74bb1d51f3223abfacc1fe600c8da6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-66::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_textabschnitt ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-66', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 66, '7:557356d090ac3b1cbf5dad3d0e1db11f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-67::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_verkehr ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-67', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 67, '7:9ab3515c90c34b19a88c8db71c8e80e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-68::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_vorbhochwasserschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-68', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 68, '7:598737e60fd357919d0f7354838a9d9c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-69::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserschutz ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-69', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 69, '7:e6f91759b418a3b6a89bc28ef1c25b5a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-70::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserwirtschaft ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-70', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 70, '7:06ba1055e84aeefe53de7ddbc4a9f3d2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-71::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergie ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-71', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 71, '7:d02bbddd12908c379a48d910034211ae', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-72::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergienutzung ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-72', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 72, '7:b101183ee2ec0007295dbce781f0f1e5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-73::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_zentralerort ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-73', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 73, '7:2454f265acdd577cd30e4166d55938a1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-74::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bereich ADD xplan_datumdesinkrafttretens date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-74', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 74, '7:5d5e4403b993e5746cf22fbbed881b07', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-75::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abgrabungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-75', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 75, '7:b00cab197e89ce5c35e7c380ae570a06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-76::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-76', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 76, '7:3a374498c74a30250f5be746f3611f98', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-77::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsmass ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-77', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 77, '7:e9a8e3c8abe2225d3ee8c060bf76bdf2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-78::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-78', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 78, '7:593ef32ea6a5c3ff710d55b44bc22a06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-79::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_aufschuettungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-79', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 79, '7:2d05cd0620b5d6112e1621369e9497da', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-80::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-80', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 80, '7:62d87df099b5c1bac309239d6d118d61', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-81::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-81', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 81, '7:f80de7ec57051f6d0035ed1750d5ad48', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-82::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsmassnahme ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-82', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 82, '7:29047acb1b58a566e5d08833d3391c35', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-83::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bahnverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-83', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 83, '7:f17cfd0de8c7c560ba0144baaefa1b8e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-84::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-84', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 84, '7:2c4575acc42fe651d749982f8c47b3a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-85::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-85', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 85, '7:d4cb6cab7729903dbbb7b3b3f61e055c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-86::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugrenze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-86', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 86, '7:a4e2866fbc6ab2004129a538bf525d4c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-87::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baulinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-87', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 87, '7:9e088a022c85305b2e56178ab8f3ebb0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-88::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bauschutzbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-88', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 88, '7:e62b3b275a9b93f69a47bb2f24093a6a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-89::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-89', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 89, '7:457169e2024bf147ae23eaa065719ef7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-90::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-90', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 90, '7:250c02fb483a543f295efdd8b9a89e7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-91::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-91', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 91, '7:6be8423f43744496aa26a19077b8c645', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-92::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bodenschaetzeflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-92', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 92, '7:d519cc97d90f4af99ca2a339f0cfd917', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-93::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlage ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-93', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 93, '7:76b69ea66f0389f4b7785b111be344e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-94::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlagepunkt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-94', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 94, '7:0512632b577fcc4d1f5a9bc3cf4290a0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-95::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzensembleflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-95', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 95, '7:5c8423ad9f52f7c9fe13b1bce4b54101', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-96::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtpunkt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-96', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 96, '7:da0140e1ab27e923abd974c2cc56f3e5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-97::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtsbereichlinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-97', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 97, '7:e8f6eb1ba478e981a95c488fe1618e8b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-98::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_eingriffsbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-98', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 98, '7:09f956174c0f92d5a8fe8c48b98ff448', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-99::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erhaltungsbereichflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-99', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 99, '7:6161c42754581458b10ee846af63d8aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-100::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erneuerbareenergieflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-100', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 100, '7:23d8db1eddd27edf1eadc137e0dfe039', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-101::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_fachgesetz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-101', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 101, '7:274dce7578215191cc14c5a9a1783022', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-102::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungenbaugebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-102', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 102, '7:85fd035e748a214dcd14c7c7b6e3dc38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-103::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungnachlandesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-103', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 103, '7:aedcf5428ca2c69766ab0a5deb0ab8cd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-104::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_firstrichtungslinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-104', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 104, '7:6abdd205477458d2a9b3061b310045e2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-105::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_foerderungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-105', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 105, '7:08ee9244f9e5ee9136d18a0aa579f5bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-106::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_freiflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-106', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 106, '7:439a977fb7f8da8ebdb9975c23ce8fed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-107::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gebaeudeflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-107', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 107, '7:53b59d57bef5a93127b44e648064e89f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-108::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-108', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 108, '7:e6d09092a673f1b35c467f2302cbb928', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-109::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-109', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 109, '7:20d7d0d05156d6528565c279e6f2b110', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-110::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-110', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 110, '7:92bb07ab2cd10db27c81afc2a102cbdc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-111::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_generischesobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-111', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 111, '7:c3c259293056cf50f152ba31457385d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-112::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gestaltungbaugebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-112', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 112, '7:5cd9d65fdf68c08dad730bc763abf3fb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-113::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gewaesserflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-113', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 113, '7:93c8cedc881d6fcb8d6373b85c7eeb42', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-114::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_grabungsschutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-114', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 114, '7:b0ff3bc3d63b2d400926ab6db646006b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-115::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-115', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 115, '7:99efd375e06292e923a44b712907a549', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-116::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenmass ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-116', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 116, '7:02e2784c84a34c8e4e5c37c3b4f8b91c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-117::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenpunkt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-117', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 117, '7:5c788031ae151e0b96255e14d6ffb18c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-118::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_immissionsschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-118', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 118, '7:08ce324d45538b21dcf8ecc6f97c73f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-119::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kennzeichnungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-119', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 119, '7:a39d3fc1491069d14367a09331198164', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-120::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kleintierhaltungflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-120', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 120, '7:44be28851ec4bbcaf54ac5af2e1a5f9f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-121::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_laermschutzbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-121', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 121, '7:2474a9e5fe11464b1696ac9856a8d388', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-122::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-122', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 122, '7:08a1c141aada1f95e7f5a3155b94bb41', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-123::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-123', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 123, '7:7d0b3b0a5041f1f125a7709494840e08', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-124::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftslinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-124', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 124, '7:025917845c46431a8b5d5da85b9c352b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-125::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftreinhalteflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-125', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 125, '7:a880b9f2ab54b1a16605a30f55348aba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-126::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftverkehrflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-126', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 126, '7:f0099af71d5ab1dec041f6b04ad532c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-127::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-127', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 127, '7:96aff206864c39e4ee5aba472ef825bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-128::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-128', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 128, '7:96f0584297fe3eb334e66717119bf2d2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-129::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nutzungsartengrenze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-129', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 129, '7:6156f8f80c433ca7f8c872b906b91c8e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-130::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_persgruppenbestimmteflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-130', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 130, '7:51c07b49fb3076c75d1c936bb0f3228d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-131::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-131', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 131, '7:a5dbbd6d709f8cf03d94868e72dd378d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-132::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rasterplanaenderung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-132', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 132, '7:2ba38c88872cb97b81371d9381d45824', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-133::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_regelungvergnuegungsstaetten ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-133', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 133, '7:d28b01ba9c6349246b0239be409ad1ad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-134::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rekultivierungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-134', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 134, '7:ef7351435d3c1c8498b68ce1958780b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-135::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-135', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 135, '7:b5fff2602dd0815f4354943fa6ee9af7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-136::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-136', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 136, '7:092ab074a3e52241ef988ba2c9a50866', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-137::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-137', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 137, '7:d7772774ecbd79a356c9c3a8faf5dda7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-138::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-138', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 138, '7:ab29de78adacea01b3d5851082befd80', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-139::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-139', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 139, '7:39a1280da84b0625c42e45980d0c92a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-140::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenbegrenzungslinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-140', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 140, '7:0ba645aa714988ad8e7aac207160d20e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-141::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenkoerper ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-141', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 141, '7:633b3cceda7ca38cd9936103a74624c5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-142::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-142', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 142, '7:cb40835771e8f64453b59209f0fb6d7f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-143::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_technikbestimmteflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-143', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 143, '7:8baa79e6d2666495ad72339e88ccc061', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-144::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-144', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 144, '7:dc38ddc045a72566af9093392a4ca6bb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-145::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textlichefestsetzungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-145', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 145, '7:2cdd188c1caae74455bdb8ebd90eec71', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-146::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-146', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 146, '7:7c61472affdaeace6fce669a77ce8c1c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-147::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_unverbindlichevormerkung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-147', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 147, '7:d310a2627d08a3ccd7067734003b9cbd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-148::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-148', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 148, '7:16861a8847948137cf9f297a2363691b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-149::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-149', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 149, '7:c25d608fd3f61a990134bde1c03bebc9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-150::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-150', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 150, '7:e7f63c6d3676fa7f7219b3bc7be07a9f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-151::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsleitunglinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-151', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 151, '7:b21bc05fd8f16947cb335f7ffd6b97d1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-152::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-152', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 152, '7:3537b73d3c1530a3793a59956cfc04bc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-153::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_vorbhochwschutzflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-153', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 153, '7:2ecddd798eed777c052b15dd7bbc7cbd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-154::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_waldflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-154', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 154, '7:b128c8b881e5d0109d413720c253e682', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-155::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserrechtlichefestsetzungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-155', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 155, '7:baebe3184dff7c05b70f31d3152b5990', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-156::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserwirtschaftsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-156', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 156, '7:be31166300481f60704091bde4c8f69f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-157::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-157', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 157, '7:811e6b523e2aaecc19f53fcda9c5204e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-158::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-158', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 158, '7:e0f52c767b3df02b9d84c827ad788b95', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-159::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-159', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 159, '7:26a6e4a7c302bb60413245937095c389', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-160::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-160', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 160, '7:0c85206cac864fda13df475a232f6efa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-161::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-161', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 161, '7:493e04042bf43dfc5673ad0df74ae4c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-162::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-162', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 162, '7:46afec91165745a81e512152f127a67e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-163::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-163', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 163, '7:1d0293ab41b9ffd631e15ab75c039add', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-164::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bahnverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-164', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 164, '7:e0add12c1bc1c57575329db476702082', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-165::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bauschutzbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-165', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 165, '7:219e2bbfe0c9aae2bb1d9eea65d0bc63', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-166::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-166', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 166, '7:b5ca53496f34caf9dafbec8a012acebd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-167::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-167', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 167, '7:4528d4043d08180007b9bd62d1c02e10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-168::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-168', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 168, '7:0293a584643f87cd49777b30d5bf8571', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-169::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetzeflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-169', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 169, '7:e9f07adf66812ffed2e1cf439cbe6f12', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-170::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_denkmalschutzensemble ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-170', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 170, '7:ba065f793ba7d113889935a37b13d32b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-171::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_erhaltungssatzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-171', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 171, '7:099ad3326bb1791dac90657e1c23c736', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-172::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_fachgesetz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-172', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 172, '7:1e3de5e8b2aa620b70bd1a7a6c628a67', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-173::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-173', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 173, '7:37ff4052d6b4a939e1831fd9d15cd88b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-174::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-174', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 174, '7:a5fd0ad8ff9f72f8ca332ee35ccb5e9f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-175::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-175', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 175, '7:6fb6132162e9f6536b8a07e79346a736', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-176::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_grabungsschutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-176', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 176, '7:f6852f5e9ea62693fe04c1e01a701820', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-177::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gruen ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-177', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 177, '7:62c598c75643152683ebd62f2641dd34', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-178::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-178', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 178, '7:21937349dbebd441c858e7f0ce617123', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-179::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-179', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 179, '7:3e1af323d99c7d3adcda0df6f1cad197', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-180::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_laermschutzbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-180', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 180, '7:428d4a115a75f6e99ee58dd972b81c50', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-181::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-181', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 181, '7:a0ef8d7bba6e7a39cc146284df18eb87', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-182::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_luftverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-182', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 182, '7:6108488897961d1c5efe6cf3db5101f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-183::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-183', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 183, '7:fe062b3b7c352603c966749a741bb4dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-184::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-184', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 184, '7:932ba8880778ab93d4e4833f9566e7ca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-185::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_privilegiertesvorhaben ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-185', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 185, '7:caeaa02c9e2327a42a9015bf3a37b68f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-186::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_priviligiertesvorhaben ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-186', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 186, '7:286e667e9e2e89693606a79c680b2e69', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-187::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_rasterplanaenderung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-187', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 187, '7:8382d437c33129df2984e097aec52a7f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-188::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-188', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 188, '7:742f46b39ca69a0965fd84cac2bbb0bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-189::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-189', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 189, '7:6ebc8a8d4968ae329956b0410ac399fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-190::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-190', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 190, '7:90c2ea9b38bd83091a95e512dd9271ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-191::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-191', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 191, '7:d55e7d7c08162c10e71cbaf5a2db89d0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-192::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-192', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 192, '7:1731978f49aa5ae6c2a2de61b3285c29', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-193::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-193', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 193, '7:9ffe882d41849ffaa0db7f0d5b3f30ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-194::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-194', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 194, '7:98f6e06ccd580edebbbd9ad641b1ab66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-195::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-195', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 195, '7:2d6d8c104b6212e78c690855e713805c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-196::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-196', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 196, '7:5bf2cda652d3a0107e021b3b2e7d84f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-197::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbhochwschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-197', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 197, '7:efafce24ce6d050a4032986937ba3044', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-198::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-198', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 198, '7:430a1aadf580453d2f3d794f3cc918ac', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-199::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-199', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 199, '7:46ee2bef470c95fd0cf0f378ac2852e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-200::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-200', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 200, '7:66de3aab1fb3d8e602d7a7be7cdfe7f9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-201::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_zentralerversorgungsbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-201', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 201, '7:3e9abdfec78f05f504d08acf441fb2ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-202::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_abgrenzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-202', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 202, '7:1e1a7745e6478cf84943f56723c32c19', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-203::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_allggruenflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-203', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 203, '7:0374fe036d2fe72de83f2977056d37e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-204::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-204', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 204, '7:c7a652364d361781954341943791438a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-205::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_ausgleich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-205', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 205, '7:d658936f4e4b8a719785732a6067216e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-206::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-206', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 206, '7:7eeaf33d97c229eae48a06d260b8336a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-207::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_biotopverbundflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-207', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 207, '7:f2d40ab7943f2fd2491c6999cc3a993c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-208::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bodenschutzrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-208', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 208, '7:52f90a2f72610d7cec8eac8d28c962ba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-209::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_denkmalschutzrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-209', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 209, '7:9ece3b910ffe3c96623a11a96f88f224', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-210::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_erholungfreizeit ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-210', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 210, '7:ac28e0eaf62bd77677b48f71f5e99c77', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-211::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_forstrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-211', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 211, '7:ec1c5165220ddae278a0f5fccc8a86ec', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-212::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_generischesobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-212', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 212, '7:6bcf7a4311f33e91fa2f64594f9cf712', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-213::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_landschaftsbild ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-213', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 213, '7:1aaf683d6375ace7e798fae01c547d53', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-214::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_biotopschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-214', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 214, '7:4cf779046e8a53f48b19bab5370bfbaa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-215::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_brachflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-215', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 215, '7:8cf7e9f08b48df304ac3792f0ba24fdf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-216::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_elementekulturlandschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-216', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 216, '7:322395d6d2ea07551aca2fadfe87e3ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-217::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_forstlichefestsetzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-217', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 217, '7:b85e8513b2def761daf1dde8ae72781b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-218::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_herrichtunggrundstueck ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-218', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 218, '7:c9e06355b748c2fcd751a61ec073fe7b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-219::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegeanpflanzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-219', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 219, '7:44f7cdd8b0592fcd7659eac017ee10d0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-220::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegelandschaftsbild ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-220', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 220, '7:31c5f7cfe2192e9535b7c12205eca88b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-221::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_schutzobjektlandesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-221', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 221, '7:6d923f64f3824958e9c1be243f9a155b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-222::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_sonstigemassnahme ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-222', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 222, '7:b038124a0b49f1e793da0eac1a140519', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-223::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_strukturenelementebesiedelterbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-223', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 223, '7:c0f93e62e9d38672f35c6222328ddb8b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-224::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_temporaererlandschaftsschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-224', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 224, '7:325c7162ea8c6e9732a2bb21356e743a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-225::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_verpflichtungwrrl ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-225', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 225, '7:46b167c0ad5fae002f497aac920ef8a1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-226::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungsausschluss ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-226', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 226, '7:ee071fd02742f7d7dea8f5dac0517ad5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-227::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungserfordernisregelung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-227', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 227, '7:c126e50e38fb90a369e5b0a2cd378821', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-228::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_plan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-228', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 228, '7:f392abed9fe64a18bda52920598d4fbd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-229::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_planerischevertiefung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-229', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 229, '7:e2dc159c1dcc75bd167dd2db70a1c5b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-230::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_rasterplanaenderung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-230', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 230, '7:44940361d1b1e7c2186ae66be0d25553', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-231::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektbundesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-231', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 231, '7:cd7823e151265ff7d1a6e3b86a3361d7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-232::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektinternatrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-232', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 232, '7:9542bead4a2f3f142857f12472e4b7f1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-233::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzpflegeentwicklung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-233', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 233, '7:8f89e9106bdf578112f829e4b7e32804', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-234::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigeabgrenzuung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-234', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 234, '7:8fc8d0db75495082726a2215f7417932', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-235::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-235', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 235, '7:54d5804ac9614a9f3e26d7dad592b34a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-236::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-236', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 236, '7:d4ec0afe3290672a44e7bc5c4dabef3a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-237::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textlichefestsetzungsflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-237', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 237, '7:53c21e2c99b26fe59df9514eb4fe2674', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-238::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-238', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 238, '7:7a8ca568e9aab02d72ab8342d13e156e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-239::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtschutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-239', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 239, '7:2437d4b8b4712a5003d0992be7ced806', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-240::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtsonstige ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-240', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 240, '7:a63e7b4ac77d6b7770e2a7cc5dc583b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-241::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-241', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 241, '7:3d4910607e59f1d4ce8fdf84c1e4087e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-242::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-242', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 242, '7:e9e93c723e0b60a4fe542073dc7f625e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-243::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zwischennutzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-243', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 243, '7:0b94fd27bb38c3502b1d25540770fd8b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-244::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-244', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 244, '7:31dee4181715ea1eb0bc9284f3acd16d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-245::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-245', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 245, '7:107490dd98a70f101fe755273b685ae8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-246::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bodenschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-246', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 246, '7:feb843a548f6722f18b75b501544e8e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-247::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_energieversorgung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-247', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 247, '7:fd9b552cab34a58b6d7f22bd5db00c40', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-248::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entsorgung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-248', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 248, '7:e6eef924c9ab34eb794e109121860011', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-249::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entwicklungsschwerpunkte ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-249', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 249, '7:5347249d7b059e1c6dc1d9f59b65c1c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-250::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_forstwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-250', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 250, '7:63437354e3db4a92ef3f9b228b3e7ae3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-251::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_freizeiterholung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-251', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 251, '7:30a0d282f212d5ca4de13269e6d31c22', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-252::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-252', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 252, '7:3667e13151f9d0f844a818050a95828f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-253::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_generischesobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-253', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 253, '7:b5cf9895fa8ad9aca41f2ae76be4d7e6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-254::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gewaesser ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-254', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 254, '7:6c869b744c4a46223e7c1f278c2a8467', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-255::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_grenze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-255', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 255, '7:f067a9ad3993f51cfbca2d991331fd11', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-256::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gruenzuggruenzaesur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-256', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 256, '7:d8f0579b8f63de63a99c3434b93d224a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-257::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_klimaschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-257', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 257, '7:b65d3488c82d62c333154c87bbdb50a7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-258::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kommunikation ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-258', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 258, '7:27a4c6be47090855f8b200e14f584bda', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-259::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kulturellessachgut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-259', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 259, '7:747a7d70c4d308ed1b56af02f8541f05', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-260::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_laermschutzbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-260', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 260, '7:bcd109a23c03b68b46326b206031dc41', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-261::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_landwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-261', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 261, '7:f7aa4edf828a27153d28796a0d4659ca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-262::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturlandschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-262', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 262, '7:aa9600b1134b13c21535b930b64a0b92', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-263::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-263', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 263, '7:2e4dc3e1efc14610fe04b56d4404bdbd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-264::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_asb ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-264', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 264, '7:c006499b3bda95bfabdab49548626ae9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-265::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_aufschuettungablagerung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-265', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 265, '7:65971fe300ba92ea9e7c1c35c054a475', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-266::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_forstwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-266', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 266, '7:859499b2fb5c46062e600e513dbcd731', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-267::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_freiraumagrarbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-267', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 267, '7:bc58b469de58a72efd698516234226c6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-268::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_gib ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-268', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 268, '7:390b4cd75ccbb1f68cce4823e6bd4de2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-269::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_grundwassergewaesserschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-269', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 269, '7:b53b28e1de60123bec36986d2c21eae3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-270::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_laermschutzzone ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-270', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 270, '7:64a25475ca2701fc2108d54b62ae2d88', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-271::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_landschaftsschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-271', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 271, '7:e03d3592877fbdae489944badcc4aea2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-272::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_luftverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-272', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 272, '7:719ad25ade6dc2a26d5a8e83c7f033fe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-273::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_naturschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-273', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 273, '7:b8c775c73ea78a94240d7b6e766f8058', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-274::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechengewaesser ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-274', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 274, '7:2cedb800818aaec5b14898c889e0e757', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-275::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechennahebodenschaetze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-275', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 275, '7:c9d87f329209a2fb1de465b2015ee13c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-276::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_schienenverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-276', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 276, '7:3d9ac7729b8ac1a8d53fef4635bfbc94', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-277::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigeinfrastruktur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-277', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 277, '7:bc8bb82397264905cd0bcaad1bd7aeea', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-278::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigersiedlungsbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-278', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 278, '7:d59233605005ffa8b32b9c95851e9857', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-279::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigezweckbindung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-279', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 279, '7:a0193b6d3a2ccb66f7608e3ac4b16bd7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-280::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-280', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 280, '7:cd24d1e01e8f9a63c553bcb2b6ea3039', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-281::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_strassenverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-281', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 281, '7:8b24b33f25d0bcbc9e6441386315dfd9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-282::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_ueberschwemmungsbereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-282', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 282, '7:cf77c8745a4969fe152bfc8bb124f409', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-283::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_wasserverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-283', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 283, '7:400a341c2262c51df64048995fd1ac0b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-284::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_zeitlinie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-284', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 284, '7:2d874e87e4076f87bff3d8c15e7675ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-285::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abfallentsorgung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-285', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 285, '7:57d45379c87660f066fc55f7cd57e2f6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-286::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abwasser ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-286', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 286, '7:b9e3ca9e8f8dc8ad8d842ee242fe7de1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-287::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_asb ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-287', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 287, '7:221fdd731beafca2a80288909fc0d786', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-288::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_erholung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-288', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 288, '7:d5e0c2032fdfe8fc209babe107ab660d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-289::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_gib ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-289', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 289, '7:30c7d5ae6ab10e899a191254ba003275', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-290::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_landschaftsschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-290', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 290, '7:6eebd8ac36c976b1707214fb352a8273', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-291::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_luftverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-291', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 291, '7:42440f49256a33ef1550caabe845366c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-292::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_naturschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-292', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 292, '7:cb3c5ab0cd85b3b04b34307b36622742', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-293::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_oberflaechennahebodenschaetze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-293', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 293, '7:639ea9222f64326388f3a2309d4b5b1e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-294::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamerwanderweg ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-294', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 294, '7:c46d5ee107ce9e373532a862d7d8787b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-295::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamesportanlage ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-295', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 295, '7:a83661fda73b1845cc4f0868b31ac9f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-296::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_schienenverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-296', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 296, '7:1f79b0cb81df7c66b30add09ae277371', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-297::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_sonstverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-297', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 297, '7:298f84b9df976594229c259351ecb326', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-298::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_strassenverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-298', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 298, '7:19312de28dd96ef52bbda4a53c602ace', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-299::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tiefliegenderohstoffe ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-299', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 299, '7:83258980cb15c187af8be5903e1c114a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-300::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tourismus ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-300', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 300, '7:08077e13308b83118fab4ad84124d21c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-301::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_wasserverkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-301', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 301, '7:1c26a237384021156d3c3e3fa2473054', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-302::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_plan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-302', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 302, '7:6f39a370fbb2e16a4746418841596b3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-303::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rasterplanaenderung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-303', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 303, '7:d834232dac64edd030f041e7ad0270b2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-304::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-304', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 304, '7:4c595fc007493eaaa0a5abe78130e95d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-305::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoffsicherung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-305', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 305, '7:cf6996ea04dbb47bb5ebb20df655e248', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-306::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigeinfrastruktur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-306', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 306, '7:5b7c2c4d85d5b4eb58f7d6884f45ce06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-307::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigerfreiraumstruktur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-307', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 307, '7:d351956b46088d4e84a1bd9f527d18f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-308::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigesiedlungsstruktur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-308', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 308, '7:9b8c3055d3a99057bd2d2d8022e44ff6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-309::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sozialeinfrastruktur ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-309', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 309, '7:9bcbc96f081466947bf85a061d08dd84', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-310::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-310', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 310, '7:36ab85cdb0737895aab6a6e967ecf94e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-311::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-311', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 311, '7:bdbc6dc116487f1997f28dac2c5ee327', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-312::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_verkehr ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-312', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 312, '7:eccfe497779c3c40d327431f9e83e6c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-313::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_vorbhochwasserschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-313', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 313, '7:48b5eb2c85f32a30e802a153358a8cef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-314::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserschutz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-314', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 314, '7:2aca3eb54763de5d2cfd35fd92f041db', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-315::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserwirtschaft ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-315', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 315, '7:fdec6dd603048a7a468005ee1665f0cd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-316::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergie ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-316', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 316, '7:b735c275861fec187d695a5c7e4528d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-317::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergienutzung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-317', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 317, '7:7aa1e21e44ff16a0424ee214a9e73d3c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-318::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_zentralerort ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-318', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 318, '7:506969cf8515331db1c14e45505119c1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-319::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bereich ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-319', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 319, '7:c549cf92b4c6af0fd32ac7c845cb91ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-320::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-320', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 320, '7:a9bd66ffad7e3c8a9a477a9a68b94621', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-321::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-321', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 321, '7:9631604fd56d968728132d49ef2bf8f6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-322::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-322', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 322, '7:a0120fd2770f49fb8a90f6c94a22c84a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-323::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-323', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 323, '7:d8d1dd1bec4fe1663242bac03f4daf14', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-324::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-324', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 324, '7:119129e2b2c6d0bf67e4342666612aa9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-325::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-325', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 325, '7:3ffc145bda458fbfc5e13c820a034e4c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-326::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-326', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 326, '7:f45d4ec6c0435a0af929ba352135daec', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-327::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-327', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 327, '7:0d042054d925fbfc4f8ad24024e67047', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-328::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_plan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-328', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 328, '7:276d36246be8e2fc43dd48196868eb56', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-329::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_rasterplanaenderung ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-329', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 329, '7:73f03b67715a37f8c9b537e5000212f1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-330::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-330', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 330, '7:a88802cfe1f720ac4aa653f4ad829578', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-331::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietnaturschutzrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-331', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 331, '7:02d18220778ed74c61a4f942fd513873', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-332::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietsonstigesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-332', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 332, '7:e90ce7450a83b69ca6f79bb064021b70', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-333::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-333', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 333, '7:08eb284f5d9cbba577c1474501941d71', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-334::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-334', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 334, '7:1c3ea877f420d51ca86905a0c2c5f50d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-335::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_strassenverkehrsrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-335', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 335, '7:2803dad1f779f95968e5f1555731e2ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-336::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-336', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 336, '7:dcb3573595fa59a73c3e60cf3852be0f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-337::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-337', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 337, '7:f8568eaee985b899fe364cf2ba39466d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-338::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_begruendungabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-338', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 338, '7:8189408e94362d1d53c06fceacb46a08', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-339::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_datumattribut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-339', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 339, '7:56ed14b899a01e108f02989e3ca784a2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-340::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_doubleattribut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-340', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 340, '7:e1414bdf2f8e4f6070cdc7e815f0e805', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-341::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_externereferenz ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-341', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 341, '7:d0f37f7029ea56349007af6be02f8786', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-342::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_externereferenzplan ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-342', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 342, '7:6efde1727c177d113e9bb6ca7ccfa784', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-343::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_fpo ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-343', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 343, '7:8109cc8c13fdece4c4638baf546dfa11', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-344::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_grenze ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-344', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 344, '7:2e78afd13db217ef0928ec71af53a778', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-345::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_hoehenangabe ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-345', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 345, '7:9cd57dc3549f2773a9ada988d57713fc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-346::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_integerattribut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-346', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 346, '7:07571a12f149beff59fb235e463c45bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-347::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_lpo ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-347', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 347, '7:504a18762f3921d73b64e656bc85e9f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-348::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_lto ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-348', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 348, '7:70b2f805a3c5cec7fe43351bd934e543', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-349::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_nutzungsschablone ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-349', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 349, '7:d7867aa17e6a030908ef630ae43ade12', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-350::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_ppo ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-350', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 350, '7:1588e45e2a2c1a33a8abc3f5fb64c231', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-351::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_praesentationsobjekt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-351', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 351, '7:f07d486c6b9c197029b75fa1ec3a2f1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-352::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_pto ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-352', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 352, '7:4516e1ab1c4ea14cd9291535069c2389', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-353::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_rasterplanbasis ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-353', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 353, '7:d9952a540ca27321fb2b2b4c012d931f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-354::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_stringattribut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-354', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 354, '7:60dd72a749a87e439bb2c0ea666262d7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-355::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_textabschnitt ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-355', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 355, '7:28ce281fc25932690f561709824e4e64', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-356::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_urlattribut ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-356', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 356, '7:2c80c3ebe3199cb2e6020d719a7fb217', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-357::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_verfahrensmerkmal ADD xplan_gueltigkeitbeginn TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-357', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 357, '7:b0b36b801a333d5b6e0ad32b92506bba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-358::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abgrabungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-358', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 358, '7:2a79ce0c3eba807d5d3741bc333cd770', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-359::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-359', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 359, '7:b0eea0488cf00054d8e63f425945d840', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-360::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsmass ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-360', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 360, '7:05d9ddacba02a6bff5c09c57b2d90e00', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-361::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-361', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 361, '7:7e377b0a2be9296c4a0ca0c18141a7f2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-362::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_aufschuettungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-362', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 362, '7:f8c3d34952565c7fa7b64a895eb3517f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-363::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-363', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 363, '7:4eb7bcfd1a35a838e4612f6a0099eb8f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-364::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-364', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 364, '7:6a1fc72d8daa95e5670f88e53ec33105', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-365::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsmassnahme ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-365', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 365, '7:b6790c47d71d19820a0a440f95dceb74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-366::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bahnverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-366', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 366, '7:6039182fe5aa9709cc6f21df7deae15a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-367::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-367', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 367, '7:f718d6ce972336c2f5d9d2bc12d4600a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-368::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-368', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 368, '7:32593264eda034a3e24bab6b4da2f4e4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-369::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugrenze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-369', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 369, '7:79d9f4364d9aad3170b550e97b35fc15', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-370::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baulinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-370', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 370, '7:0c6422b6de7716b534eb738bc35c9a75', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-371::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bauschutzbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-371', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 371, '7:1e97643d83f5e190ea0c38dc2097c881', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-372::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-372', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 372, '7:ccd0b6cd771e2523acd881755fa0af38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-373::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-373', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 373, '7:8873d6e11e4fd693a287a21c8d2dec4d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-374::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-374', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 374, '7:2c8c9dc48e24baa92620625f746669d4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-375::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bodenschaetzeflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-375', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 375, '7:96780ffc8f322cb36d6fdfa5ac1e4af3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-376::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlage ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-376', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 376, '7:684f57967a8b4cbc235d92c0f738cc16', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-377::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlagepunkt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-377', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 377, '7:ee28544e768bac649bb7a36db4af8152', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-378::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzensembleflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-378', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 378, '7:898968f8ecb0cfc923da43938a112615', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-379::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtpunkt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-379', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 379, '7:f5638fe837c87e57389341b1c287c97f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-380::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtsbereichlinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-380', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 380, '7:89cc7d93d248f73040b1e1904cf4ea19', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-381::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_eingriffsbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-381', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 381, '7:c1e1fbfc306582ef203ad1fec201f202', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-382::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erhaltungsbereichflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-382', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 382, '7:22ad3963d4229c4c68078b911c34a914', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-383::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erneuerbareenergieflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-383', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 383, '7:d05a599f39958fdbcfcedfc8a38936c7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-384::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_fachgesetz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-384', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 384, '7:131823019d3a2e51cc9e2a031383cec5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-385::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungenbaugebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-385', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 385, '7:1343f78496265af67752dbcaa5cd1320', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-386::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungnachlandesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-386', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 386, '7:4d4c576d04f61e4f658c8b1fefd1596a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-387::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_firstrichtungslinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-387', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 387, '7:b881796d9b7dd451dce83a603ec63972', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-388::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_foerderungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-388', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 388, '7:fd459f4a2b6a148a4ef1483e925d55f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-389::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_freiflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-389', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 389, '7:fd3a2cbe3f12658c4516eccab716691d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-390::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gebaeudeflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-390', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 390, '7:fd817b0ee32aad01b523efa729e0c956', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-391::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-391', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 391, '7:68e71a43d85f5e5798be530fc03affe6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-392::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-392', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 392, '7:cddf713d28ea4d33913744690cc589a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-393::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-393', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 393, '7:edc4e7401598c893f0d3bfd2e494f13e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-394::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_generischesobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-394', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 394, '7:952bd1a75d2ad4a9c9f77f6f3c332792', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-395::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gestaltungbaugebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-395', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 395, '7:f0faa14ab785267f1768c40df311db5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-396::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gewaesserflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-396', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 396, '7:34b5cb32affe0fb4c23a3825177ed338', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-397::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_grabungsschutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-397', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 397, '7:11cc27bb562b24031cf5d9f13fc3b096', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-398::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-398', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 398, '7:bba01cdfbe9e88b59d5e397ff9b2bc6a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-399::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenmass ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-399', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 399, '7:dd2f5de53e2fe37e833bab0b92975bd7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-400::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenpunkt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-400', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 400, '7:26a3602bcb429ed8c4d9666331f1149c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-401::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_immissionsschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-401', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 401, '7:aaecdf5f337b66d956a762ce8a071796', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-402::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kennzeichnungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-402', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 402, '7:e6a94ade61a9c746d78b002968c76283', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-403::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kleintierhaltungflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-403', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 403, '7:da6673d6e386cef7a7973b5079d00014', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-404::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_laermschutzbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-404', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 404, '7:9cf0f833c6d3f4ba07bda10abba576c8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-405::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-405', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 405, '7:308b6e9594eb26570cf338e74b9c6d0b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-406::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-406', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 406, '7:33cbae73a04c405396ba2e38cde6acb3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-407::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftslinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-407', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 407, '7:5c0565db4b967bac05cb2148181b50f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-408::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftreinhalteflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-408', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 408, '7:0262f35b76ceb2c95a6991a0bb0ccd83', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-409::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftverkehrflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-409', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 409, '7:36e9452b4b6f0a1a60d82bde64f92e80', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-410::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-410', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 410, '7:d7f43c7842b0c66ad38310ce3a34c642', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-411::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-411', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 411, '7:3fca09f9731f545ab8f27ce1da9e39fe', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-412::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nutzungsartengrenze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-412', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 412, '7:9923078bb6c19e727cd107414630acec', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-413::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_persgruppenbestimmteflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-413', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 413, '7:919e15224a20532a2abf1529f76afd2d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-414::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_plan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-414', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 414, '7:f5085b06687ace19a83319f6ac5d5c15', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-415::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rasterplanaenderung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-415', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 415, '7:a743991bd295b0d4d871cb8bc08673cb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-416::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_regelungvergnuegungsstaetten ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-416', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 416, '7:875882048e8056e7c144d59a7dcc45c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-417::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rekultivierungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-417', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 417, '7:09538962a3a71acdfef49c3932427e85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-418::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-418', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 418, '7:28f20155a491d282d92acc9deea47db0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-419::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-419', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 419, '7:6b04c6624f410b931fcf5fd49a479518', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-420::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-420', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 420, '7:73377259a6f2f07f13ae7879cda0e017', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-421::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-421', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 421, '7:5591686eaffd9f26651cda33176455d6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-422::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-422', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 422, '7:62959db3d96e7c4727a39ddb93c54563', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-423::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenbegrenzungslinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-423', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 423, '7:b9f82c0e8488b52c618be0008d52b04c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-424::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenkoerper ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-424', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 424, '7:ee26b48cde44ad9ac70b39ad2713f5d6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-425::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-425', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 425, '7:6f2af4b7ecd01d6821d6269d017d4d3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-426::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_technikbestimmteflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-426', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 426, '7:ad98d438d885c404c8b6b55250a7b8e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-427::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-427', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 427, '7:30f2d172fd395eb6ac0dc4a482cd8b52', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-428::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textlichefestsetzungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-428', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 428, '7:ff5972ddb774a702f295c9bf2a36afa3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-429::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-429', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 429, '7:f1687eee47dd48fd274d29bf66fd1c82', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-430::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_unverbindlichevormerkung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-430', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 430, '7:78bebfcdf776f19f79c60fafef28ff17', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-431::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-431', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 431, '7:f1d76d169135f99bc18615dd4748ba26', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-432::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-432', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 432, '7:5341bcc4d895f56be044a2c8661620b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-433::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-433', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 433, '7:9e8b9d08779fe4eb625dc96edac52c0e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-434::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsleitunglinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-434', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 434, '7:d07648c4779daad8fa1c4fa4ae77a1c4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-435::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-435', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 435, '7:37471755cf09c232b08b3c2e6c37c927', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-436::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_vorbhochwschutzflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-436', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 436, '7:913d2039e736a44d6c7c8132b385a991', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-437::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_waldflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-437', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 437, '7:2afb9cc276848f83f13c3d5ac50a0597', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-438::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserrechtlichefestsetzungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-438', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 438, '7:9ba56ec4126df39b53faaf3264ad867d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-439::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserwirtschaftsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-439', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 439, '7:f6955c64aa4f96138a03461144697543', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-440::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-440', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 440, '7:580a1491417de3b0aedfa1c6aa7b92aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-441::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-441', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 441, '7:504d24a4a2d07c879ab36f4587222b47', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-442::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-442', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 442, '7:25b9a0f0fe322bbe41d926d7faffa28b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-443::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-443', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 443, '7:bf82a75d39fc9c791787b24753b0bdc5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-444::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-444', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 444, '7:f3cebb64f535c1107b01ed8942e3b277', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-445::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-445', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 445, '7:372c1d8a7f4719b49d32215c378d0723', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-446::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-446', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 446, '7:38dfe6a8cfb89b87ddfda16d6cea9ad6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-447::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bahnverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-447', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 447, '7:3b4a810b1073076a78eb917987b4cfe8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-448::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bauschutzbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-448', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 448, '7:f9c5ae50f180520e838c6fb585fee594', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-449::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-449', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 449, '7:9007070ceed26edcd20f91d77cf1fb36', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-450::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-450', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 450, '7:a802e6299be34ed25dd3a9ed496a33c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-451::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-451', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 451, '7:a5fa8893740400978a0e4ba90727c7a4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-452::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetzeflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-452', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 452, '7:311e87494cf09a88ce10e10b64382940', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-453::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_denkmalschutzensemble ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-453', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 453, '7:47d8de111061a1dc6086eca9f8955a1a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-454::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_erhaltungssatzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-454', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 454, '7:4bf3ea162a7c4a9603f4d73fa00f1181', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-455::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_fachgesetz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-455', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 455, '7:e5fcba304d5e6eaf0944fd3d7bd172d9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-456::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-456', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 456, '7:9b2a9b6bb3c3c813aba373067ae47bfa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-457::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-457', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 457, '7:db4d4ee0ef7cdd7cb6fe4c1711fcc0af', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-458::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-458', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 458, '7:a5f7ec46efa5b5564439ce0c56513217', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-459::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_grabungsschutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-459', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 459, '7:ed06ac07f475ac45a7291a3eed66b87e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-460::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gruen ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-460', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 460, '7:297d4c843e57a3ee42fcd574965dfd6f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-461::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-461', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 461, '7:7bd7e5dc8389e39e5898ee72383078fb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-462::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-462', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 462, '7:80d9a168468f53388de7f00e9e123ff2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-463::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_laermschutzbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-463', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 463, '7:a8b77c49be3ac928bf7a80c6cbdfe1cb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-464::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-464', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 464, '7:b2ec4db90d03048bad965fdf6f6a0228', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-465::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_luftverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-465', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 465, '7:99edeafa6069afef4d27406674f4f377', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-466::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-466', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 466, '7:e47ae82e808b449428895730c3fef613', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-467::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_plan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-467', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 467, '7:38b88bb77133c4f0bbd9a7d8240691c8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-468::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_privilegiertesvorhaben ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-468', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 468, '7:349dedc319545e28e390ec28d17c1aad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-469::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_priviligiertesvorhaben ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-469', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 469, '7:53d898aaeca037d4203ec6e29d1595a8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-470::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_rasterplanaenderung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-470', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 470, '7:6bec3b154b7a2ef2b6390a74e6dfd12d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-471::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-471', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 471, '7:6bfcb3033ba07f51d84c1fae24c38765', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-472::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-472', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 472, '7:64113e3acdc368195f55eb2c572b5a4d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-473::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-473', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 473, '7:7b4d5c2158cf93f8ca72ee6db3764649', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-474::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-474', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 474, '7:323fee4f4491539664ed71f7056e636e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-475::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-475', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 475, '7:c24e1743bd889169f444c5b0125d6fd8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-476::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-476', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 476, '7:24016a147e9e816c7096ecc89f7548e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-477::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-477', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 477, '7:1dc3ea9199bf224b05f5b5ae95fa2a3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-478::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-478', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 478, '7:8e3114c3f56baf08d28efe6f70ca79f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-479::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-479', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 479, '7:d3c5e8209ff7525173c5278bc0c085e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-480::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbhochwschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-480', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 480, '7:f4fdd978eba5177eb637ebb38e4bf51b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-481::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-481', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 481, '7:ca997b3b2067d0641c32a981a0a3069a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-482::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-482', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 482, '7:db343367f2c75bd4644a08036e6796b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-483::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-483', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 483, '7:6fc4a5f8e88c6a8d1c4aa9d459ae3870', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-484::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_zentralerversorgungsbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-484', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 484, '7:086be1679a747ab2388c6a0026a02ec2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-485::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_abgrenzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-485', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 485, '7:73fc24993b18896a6834539097ffb1db', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-486::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_allggruenflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-486', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 486, '7:1921219af05ecf6d46129ec463653641', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-487::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-487', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 487, '7:9f329c70825a1f9b02223fec1e428615', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-488::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_ausgleich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-488', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 488, '7:0280805d0370b6f1c6d834ef2b420c82', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-489::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-489', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 489, '7:1b55ed1441d72376184be6090732c99e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-490::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_biotopverbundflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-490', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 490, '7:a78a6d7c3a1f02f474080f4922a36cf5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-491::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bodenschutzrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-491', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 491, '7:1422696d1612f493b958c95de1560f10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-492::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_denkmalschutzrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-492', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 492, '7:68df7d791b31071a028192d3660e0602', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-493::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_erholungfreizeit ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-493', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 493, '7:87d2c4f576a075ebb16838a693653588', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-494::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_forstrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-494', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 494, '7:eab34346d7a3553640e3668e476ce8dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-495::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_generischesobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-495', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 495, '7:4feef066466a020cf4af60370f735031', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-496::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_landschaftsbild ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-496', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 496, '7:2a043611bd5e49040bac4c8bd4b8769a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-497::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_biotopschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-497', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 497, '7:474d5265bb12376e1fedcf6087d95f50', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-498::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_brachflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-498', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 498, '7:ea575f832ff226ea7c4bc2e58d695d6d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-499::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_elementekulturlandschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-499', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 499, '7:a9961989d0442787441d7da8cd9a5dd6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-500::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_forstlichefestsetzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-500', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 500, '7:9cd2763e7834b0f906542709a39ee754', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-501::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_herrichtunggrundstueck ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-501', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 501, '7:0a762b70c5804f1537da806d25a8f3cc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-502::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegeanpflanzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-502', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 502, '7:8b81f7b610da5d237b8a167cbb003e1c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-503::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegelandschaftsbild ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-503', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 503, '7:ba4f57897272ce3b79530b0c6c9c29f7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-504::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_schutzobjektlandesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-504', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 504, '7:aecd73955915b01f375a5a0341af880c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-505::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_sonstigemassnahme ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-505', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 505, '7:82ffd09f8cd6665094281665a34fc67f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-506::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_strukturenelementebesiedelterbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-506', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 506, '7:22fcf36d7c37fa08431c7e0ce0ad417f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-507::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_temporaererlandschaftsschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-507', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 507, '7:0618f8688a3b5b10684bbd8d240a67bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-508::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_verpflichtungwrrl ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-508', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 508, '7:8b73bbe2177415d8972bbf6963856fc3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-509::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungsausschluss ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-509', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 509, '7:4cc886089627e41718e598f1ce26f767', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-510::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungserfordernisregelung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-510', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 510, '7:33193e30ebf61dec7a99c3cd04afb954', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-511::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_plan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-511', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 511, '7:94cd4b743c45904868da60bdd0cb072e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-512::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_planerischevertiefung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-512', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 512, '7:21bb373b11895a64d56ea57cd3200743', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-513::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_rasterplanaenderung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-513', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 513, '7:49c4d579298de93eb4a4928a5243cf76', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-514::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektbundesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-514', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 514, '7:275ace0d9c569043ea546c815c15d3ce', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-515::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektinternatrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-515', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 515, '7:8da6bd5a252bbe04947d3aac5339fcef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-516::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzpflegeentwicklung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-516', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 516, '7:d55ee3c039f35ae91234692aa28b4951', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-517::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigeabgrenzuung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-517', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 517, '7:d74eb98b7acfacc2c05a7a507d868bef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-518::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-518', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 518, '7:ed86ac0d7d71f81653509f173cf5a3ed', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-519::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-519', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 519, '7:db14cf4f39a4ac3ea93058f01ec6004d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-520::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textlichefestsetzungsflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-520', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 520, '7:1ee2e80f7c89ca4cca5d5b93d9679273', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-521::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-521', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 521, '7:c5822ae50dd4fa711660d4e0635941e0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-522::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtschutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-522', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 522, '7:ac2de3c7fc88b3cc44bccc08e455ea3f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-523::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtsonstige ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-523', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 523, '7:9c4717e453d14936af37c343c51fcf15', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-524::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-524', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 524, '7:f424548d03cabd4969434033b004f7f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-525::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-525', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 525, '7:b39b630268f045ded38593b097830d16', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-526::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zwischennutzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-526', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 526, '7:5c1bf42d43466f716af1ab5169365678', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-527::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-527', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 527, '7:fe55f48cd3eec5e18a8f8730db8cca44', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-528::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-528', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 528, '7:eee99cd308f0a4034037655fb21627a9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-529::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_bodenschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-529', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 529, '7:9c7817605de97361274e95611e7bed21', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-530::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_energieversorgung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-530', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 530, '7:cc46c44bc15513cb75dbee419c9d8e66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-531::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entsorgung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-531', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 531, '7:146969a06899c7b8b89ced818074bc10', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-532::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_entwicklungsschwerpunkte ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-532', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 532, '7:4a7ab5b0042d932078b9d06102fb7880', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-533::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_forstwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-533', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 533, '7:2e8c97c36abb3c188ae4a7b962b910d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-534::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_freizeiterholung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-534', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 534, '7:b3d876af7ff5192dc09e285e66834747', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-535::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gemeindefunktionsiedlungsentwicklung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-535', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 535, '7:cf640a38766aa38a70f02a84e128815f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-536::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_generischesobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-536', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 536, '7:740c4735308c1ab9696872a866745743', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-537::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gewaesser ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-537', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 537, '7:f46492de86e24289bbcea558c4f28474', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-538::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_grenze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-538', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 538, '7:0065b8075a15074886f13823d4bf6690', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-539::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_gruenzuggruenzaesur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-539', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 539, '7:07a1e8f27251ea07e5b937eabf20e430', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-540::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_klimaschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-540', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 540, '7:d60a8fb51a4d8747cb74acb38ea08ae1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-541::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kommunikation ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-541', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 541, '7:b14c8004860ba221ccdc5d045f90dbf9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-542::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_kulturellessachgut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-542', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 542, '7:c536266ab2c22d74adf8cb605381ad97', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-543::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_laermschutzbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-543', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 543, '7:8c739196ebbbe2715b77e3b3fa9990d4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-544::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_landwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-544', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 544, '7:a4e1f5140cea36865b1bd3dac9e75d41', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-545::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturlandschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-545', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 545, '7:b3c48988b96a54b3bc396070cfefce23', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-546::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_naturschutzrechtlichesschutzgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-546', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 546, '7:f57ef99e5001f3ffc3c08676008fb0d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-547::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_asb ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-547', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 547, '7:3a2a70993c54e931d7227f8405122f22', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-548::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_aufschuettungablagerung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-548', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 548, '7:7796b4108a4b2bf983e1d88e74fb6971', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-549::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_forstwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-549', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 549, '7:b522a3e2c4d30e836c31f79836855095', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-550::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_freiraumagrarbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-550', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 550, '7:6f796f0c540eac6ca4a1edf91efc85cc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-551::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_gib ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-551', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 551, '7:10179ef2e663f4dbe1739362f33ba347', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-552::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_grundwassergewaesserschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-552', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 552, '7:319c4587e08d13db28e5fd8f4e9213bd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-553::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_laermschutzzone ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-553', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 553, '7:a748cb3f49c4f6bc642af573b8304d7f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-554::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_landschaftsschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-554', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 554, '7:4f6859b0a5ea03c7675f6059cff072e4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-555::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_luftverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-555', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 555, '7:60a3e0a3072959af0d1fa4089e2dd71f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-556::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_naturschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-556', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 556, '7:d3e6b33cce147754be0a588e811ed849', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-557::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechengewaesser ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-557', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 557, '7:8a0eb610533e3a5d2443c53cd13cb138', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-558::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_oberflaechennahebodenschaetze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-558', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 558, '7:18db306deb22065b6d27532411b723f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-559::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_schienenverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-559', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 559, '7:229ae327d574c80ad01dfab5f0467bc0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-560::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigeinfrastruktur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-560', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 560, '7:76502e3833756e4be26a1f07da0c89f5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-561::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigersiedlungsbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-561', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 561, '7:0fbed728b7a7bc847afc2c20b9a16448', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-562::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstigezweckbindung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-562', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 562, '7:d1431c5b6da8a70c71a0c029aba17d65', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-563::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_sonstverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-563', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 563, '7:ccf39e87efd6e8da442f4ce23ccc3a80', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-564::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_strassenverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-564', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 564, '7:050132c17f9b00fe4edcf48902a0ce4a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-565::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_ueberschwemmungsbereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-565', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 565, '7:f01b024401a6f55efe967081493926d8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-566::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_wasserverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-566', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 566, '7:d98ade239fbb4a71030a085d084ee93c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-567::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nrw_zeitlinie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-567', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 567, '7:35cc79203036b7192f3ee3f484f32ee5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-568::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abfallentsorgung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-568', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 568, '7:dacc448f6f1688a07200d81e25b81fa0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-569::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_abwasser ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-569', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 569, '7:06cd240cabaa2cf31c8ae6e0edd78996', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-570::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_asb ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-570', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 570, '7:56acfc4f72f7030b84a92ae0d86e5164', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-571::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_erholung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-571', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 571, '7:2fb64496d5fa1990a6f4d286e39b6c29', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-572::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_gib ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-572', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 572, '7:95d41ff4411c2637602a892eb9ab5008', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-573::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_landschaftsschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-573', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 573, '7:f1298fbb01d2dad815240baf25598c50', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-574::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_luftverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-574', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 574, '7:0451fd2e4fa6ab017869a129e74c87fa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-575::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_naturschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-575', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 575, '7:e7d75d34a0b43fc1b8eb228c991023f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-576::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_oberflaechennahebodenschaetze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-576', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 576, '7:45f76214569a90b90bf2d5f524d81bad', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-577::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamerwanderweg ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-577', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 577, '7:b99026f541711ee35f19985ec75f8265', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-578::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_regionalbedeutsamesportanlage ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-578', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 578, '7:6aafe69328c2da04fbb01d61558c7a6e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-579::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_schienenverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-579', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 579, '7:1e7d68c5ecdcee02c3d86b8850fcc6ca', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-580::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_sonstverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-580', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 580, '7:d08914f35ac81403dd98c85ca84e3d94', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-581::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_strassenverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-581', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 581, '7:3ecb81c0f72efccddff7e55c84f7d6de', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-582::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tiefliegenderohstoffe ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-582', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 582, '7:070fc48d20581a3754ec4ec17b421d05', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-583::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_tourismus ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-583', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 583, '7:17b3e59f223b899f8c6bd8d6ddf4c2ee', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-584::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_nsm_wasserverkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-584', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 584, '7:7af012f044e1c5abc5f2526f1fdcef97', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-585::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_plan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-585', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 585, '7:8ccfcb116959381cf526d0d72e018272', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-586::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rasterplanaenderung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-586', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 586, '7:0664e4c16d1e2a044858eb74c86eb5c5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-587::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-587', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 587, '7:0ff4794175957ed9fc5714c04c31e863', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-588::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_rohstoffsicherung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-588', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 588, '7:3f62bba123b0279a49cd06333fd50027', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-589::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigeinfrastruktur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-589', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 589, '7:7b3c05df135e8dd1bc7d6b17414002e7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-590::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigerfreiraumstruktur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-590', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 590, '7:ade83e3f189bd5893a584a10e3fa2c7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-591::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sonstigesiedlungsstruktur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-591', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 591, '7:e56c55ce37499247174e23d62c619494', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-592::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sozialeinfrastruktur ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-592', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 592, '7:da8e5419dd9223fa96c7c88f8bce6151', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-593::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-593', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 593, '7:5f2d2bd63d606c32ed9cc3a90afc3655', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-594::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-594', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 594, '7:4103e2034fe35b3fd0177649f7228929', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-595::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_verkehr ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-595', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 595, '7:14daa2f1581c04f91993949c4bb7123d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-596::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_vorbhochwasserschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-596', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 596, '7:c0dad27b8e3322c449af75f0f44468d6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-597::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserschutz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-597', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 597, '7:06d4400ec4a41ee8909351e41276ece4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-598::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_wasserwirtschaft ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-598', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 598, '7:12efca67fb37f2d57b2136e992a25c23', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-599::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergie ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-599', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 599, '7:d4ffa66062eb460fa2cea12f4f305dcc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-600::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_windenergienutzung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-600', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 600, '7:e7ab6753f0603840dbd022948eac49a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-601::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_zentralerort ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-601', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 601, '7:6810452680e71e68d5c8002eea8c350d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-602::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bereich ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-602', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 602, '7:7e8f3e7f3fc230fb5e730075463f6a3d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-603::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_bodenschutzrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-603', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 603, '7:2bf5b21e72fe0aa7c18e2aed8c50fee6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-604::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_denkmalschutzrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-604', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 604, '7:5d668713bbd0756bd8ea9bf21c4670a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-605::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_forstrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-605', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 605, '7:f05b34c990cfa9e0db2d279dc14f875c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-606::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_gebiet ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-606', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 606, '7:b207208a82be69a9034939cdbbb45f68', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-607::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_grenze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-607', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 607, '7:4c9765f4aa4c4380e20b776e86064e3e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-608::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_linienobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-608', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 608, '7:73fa2514ff11156b9aef6c261274db6a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-609::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_luftverkehrsrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-609', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 609, '7:33e5ad3709c8684d318f6075af8970ff', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-610::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_objekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-610', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 610, '7:ab2d74375a6511935b09c03371157199', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-611::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_plan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-611', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 611, '7:fb8430dfb331312ca6cd7db7e0f5b134', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-612::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_rasterplanaenderung ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-612', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 612, '7:f5f291972349db0fb5b5e1f9036f07d3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-613::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schienenverkehrsrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-613', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 613, '7:0bcbe7a09d6a47c3818da7e243c53a06', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-614::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietnaturschutzrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-614', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 614, '7:0d7d61bd1c7b2b7645ea22e8aad9c5f9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-615::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietsonstigesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-615', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 615, '7:aae46153a8074dd9df44de188b45cdbc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-616::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_schutzgebietwasserrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-616', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 616, '7:371a1b7a48909e50a2374a70775accc1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-617::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_sonstigesrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-617', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 617, '7:7e671454c6b64b125fb8f1045820495d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-618::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_strassenverkehrsrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-618', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 618, '7:350ac6992f350933c01e36bf21f02464', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-619::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-619', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 619, '7:cede6de7bed38fd4b9431af51e689f58', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-620::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_so_wasserrecht ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-620', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 620, '7:7a4e564ecd13af8097ff3a053d05854a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-621::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_begruendungabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-621', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 621, '7:69459eb73488a428d7a454781b5853b8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-622::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_datumattribut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-622', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 622, '7:a788234213f0e120ced97a1ee87a400f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-623::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_doubleattribut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-623', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 623, '7:0a430fa51ba55069e8922b77631a4674', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-624::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_externereferenz ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-624', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 624, '7:1664e568ab19fe1089f331db76dc1165', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-625::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_externereferenzplan ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-625', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 625, '7:124e860ac67f2743aec48bc7b5764316', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-626::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_fpo ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-626', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 626, '7:6ef413cda5925fac13a92194aaf45c1d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-627::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_grenze ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-627', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 627, '7:329d3e5705ce7e2ec6e2789ab0d79c36', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-628::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_hoehenangabe ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-628', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 628, '7:a7d2ca20c8894e2796604efec5845b94', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-629::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_integerattribut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-629', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 629, '7:4b8564b29047284368f188f50d4a6db3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-630::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_lpo ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-630', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 630, '7:23d4f87872e9b9df73d5273ee352afea', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-631::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_lto ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-631', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 631, '7:7e780c1804b354e5f8d89282e6027e29', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-632::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_nutzungsschablone ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-632', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 632, '7:a396af0f8c57f0f5fbc89ee5fbf1a349', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-633::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_ppo ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-633', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 633, '7:6ae6ca4c293b1cba77d78ed0cde7c4b7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-634::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_praesentationsobjekt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-634', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 634, '7:2839e05305b3e15fbcfc5b55d11076a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-635::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_pto ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-635', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 635, '7:7b38272d392e257c57321703f7dc3c85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-636::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_rasterplanbasis ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-636', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 636, '7:70732cae68947c66832d22359c999831', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-637::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_stringattribut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-637', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 637, '7:f025f34e39e1a0a0065f272ce8c19760', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-638::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_textabschnitt ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-638', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 638, '7:d9f92837c5cfcb48b1c4c91c4740cbcb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-639::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_urlattribut ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-639', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 639, '7:1ed558ef1a8184a1f6ea41cf4d006b81', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-640::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_xp_verfahrensmerkmal ADD xplan_gueltigkeitende TIMESTAMP WITHOUT TIME ZONE;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-640', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 640, '7:a06ed6022823e69c280de6cee421d0b0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-641::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_abgrenzung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-641', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 641, '7:353914ea322e6bae7fcdc776591823b3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-642::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_allggruenflaeche ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-642', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 642, '7:01940dcdfc38587ca9698adf0325fe4c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-643::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_anpflanzungbindungerhaltung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-643', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 643, '7:f4c2c10258175a195f557b04bf06c018', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-644::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_ausgleich ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-644', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 644, '7:f7c293aca1a57e4c5c50f2fe7dbda96a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-645::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bereich ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-645', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 645, '7:8916f586438b1d98d11b239f37ea55a5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-646::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_biotopverbundflaeche ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-646', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 646, '7:b59b686959ce1cb75a329f6362e3b33e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-647::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_bodenschutzrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-647', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 647, '7:2fe9483b9fd4166ed4c83f4a49bcd95a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-648::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_denkmalschutzrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-648', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 648, '7:4de6d29633dffef13046386674e0c9dd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-649::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_erholungfreizeit ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-649', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 649, '7:05465d7be48755fca38a77039f880649', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-650::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_forstrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-650', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 650, '7:fabda986e21a6354c46838362a4ee9fa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-651::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_generischesobjekt ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-651', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 651, '7:5b455a8f501027031e705f37df7f57bf', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-652::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_landschaftsbild ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-652', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 652, '7:f381bc84f82306b4084deceadfe3f8e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-653::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_biotopschutz ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-653', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 653, '7:b90f4a132121692a0456d8acc33fc23c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-654::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_brachflaeche ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-654', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 654, '7:965e09227302af05ed629c4581f33d1b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-655::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_elementekulturlandschaft ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-655', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 655, '7:3be8c9dd9c11c2526d6a73760c55596f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-656::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_forstlichefestsetzung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-656', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 656, '7:a740e9fe2e71ef80a216520d7cd4b612', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-657::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_herrichtunggrundstueck ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-657', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 657, '7:0bc69c748346636a9458534659c637af', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-658::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegeanpflanzung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-658', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 658, '7:fd75bf3f1393349fdb6794431e3fd15b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-659::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_pflegelandschaftsbild ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-659', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 659, '7:6d409a2e04e511421de295c7ac25ab6b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-660::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_schutzobjektlandesrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-660', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 660, '7:e0433a963ffa51ff94b3c5e51aa93769', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-661::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_sonstigemassnahme ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-661', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 661, '7:ec455f8cbeb70811d44ed36a1834faf8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-662::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_strukturenelementebesiedelterbereich ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-662', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 662, '7:241cd38d6f680d4c5a4a40fea0679c8d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-663::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_temporaererlandschaftsschutz ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-663', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 663, '7:14116a558ffebb1066dfe7809bc635ef', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-664::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nrw_verpflichtungwrrl ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-664', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 664, '7:7e7ebef4ecdb3d5ca82443292da50b2e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-665::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungsausschluss ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-665', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 665, '7:99d1735b2b4a678dfecd0581a0761503', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-666::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_nutzungserfordernisregelung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-666', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 666, '7:f078a3115a9a932e06956d157ed59288', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-667::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_planerischevertiefung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-667', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 667, '7:544e44fccf9507fb1c539f7880bf32e3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-668::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektbundesrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-668', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 668, '7:40e36e758e252098a9edabcb4ebeaa0f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-669::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzobjektinternatrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-669', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 669, '7:8adf0a12d3f249eaa22b1f2d23609a38', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-670::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_schutzpflegeentwicklung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-670', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 670, '7:9cf0d776c98f54a94db42907ebee4bfc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-671::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigeabgrenzuung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-671', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 671, '7:189a01cb7b4099b8bf1637f732a63384', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-672::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_sonstigesrecht ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-672', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 672, '7:1e68afe655877f69e79d28b354745a03', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-673::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textabschnitt ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-673', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 673, '7:b32ceb5440cab22470b616a73d826149', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-674::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_textlichefestsetzungsflaeche ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-674', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 674, '7:a2692041ff7b763c072a90f077379639', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-675::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtgemeingebreinschraenkungnaturschutz ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-675', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 675, '7:790bfcdb3d7310b01a80d7e8e6ca08e6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-676::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtschutzgebiet ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-676', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 676, '7:b5edd0f9e57cc688370ff1b06260f882', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-677::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtsonstige ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-677', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 677, '7:7050659735d54cfa6685f0a28a11249a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-678::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_wasserrechtwirtschaftabflusshochwschutz ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-678', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 678, '7:d57a64c08cd982d752c6c4b4c717f24b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-679::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zubegruenendegrundstueckflaeche ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-679', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 679, '7:7db7ebdbdfd2219a869617041a7e5a7a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-680::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_lp_zwischennutzung ADD xplan_inkrafttretendatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-680', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 680, '7:3275d1c830337f7e3478d568c2be5b6a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-681::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abgrabungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-681', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 681, '7:b817bff388dd1e5766924e46eb1257f0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-682::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-682', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 682, '7:a66940c968b3108bcaa82a92ccb23b0a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-683::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_abstandsmass ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-683', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 683, '7:30bcf16ed19f8411348be6275596d77d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-684::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_anpflanzungbindungerhaltung ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-684', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 684, '7:aa0242c5c88d52452f662d1a9677f502', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-685::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_aufschuettungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-685', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 685, '7:819be970b50948dbf8508fa8f54d7f91', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-686::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleich ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-686', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 686, '7:0b42d28e688afab9ace56b8d62175bdc', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-687::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-687', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 687, '7:e0d599ca7cedd70fb33c31b81ee7d85a', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-688::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ausgleichsmassnahme ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-688', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 688, '7:02da048623439912c167a35b338d2396', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-689::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bahnverkehr ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-689', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 689, '7:5c971c62b0d0292ae1866cb4dbdcb803', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-690::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebiet ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-690', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 690, '7:f434182ea28b193c6e7bc882d474562b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-691::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugebietsteilflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-691', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 691, '7:ffa5ac5b17856544a61cce8623f11a88', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-692::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baugrenze ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-692', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 692, '7:e84bd54423bf0e6ce9929a499f22806e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-693::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_baulinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-693', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 693, '7:5331f219ff97a4657cce3e1d04687478', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-694::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bauschutzbereich ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-694', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 694, '7:29f594203f7e2fadeb5e2e3042e2a498', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-695::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereich ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-695', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 695, '7:f3ce01b9c055718a2aac99796c57d103', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-696::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bereichohneeinausfahrtlinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-696', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 696, '7:17f5f67aa3407cab5d7ba152764bce3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-697::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_besonderernutzungszweckflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-697', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 697, '7:abbe7bbf5cc680a76777c15c10f9c11d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-698::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_bodenschaetzeflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-698', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 698, '7:1284bf314ec5e12ffb12260b80bd7409', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-699::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlage ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-699', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 699, '7:8aa6d54d53d2fc3291893aff9dafe313', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-700::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzeinzelanlagepunkt ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-700', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 700, '7:28b589d405f91ff426c33a6648f2c512', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-701::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_denkmalschutzensembleflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-701', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 701, '7:e4c3765f48a1833912809c10c4b233eb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-702::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtpunkt ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-702', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 702, '7:043d82a7dfbb74867c9c6c4063db353d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-703::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_einfahrtsbereichlinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-703', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 703, '7:a67c6fabe74701ad4377f9ee36c1ef75', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-704::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_eingriffsbereich ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-704', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 704, '7:610716ad35600e7df12dececc555fe5f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-705::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erhaltungsbereichflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-705', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 705, '7:f29d36c3b4ae076626413bd5617d0c84', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-706::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_erneuerbareenergieflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-706', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 706, '7:0c0ddadbd558337fde75806271e73b30', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-707::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_fachgesetz ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-707', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 707, '7:f5a00b2c030fa4ea6d513111c8c36792', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-708::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungenbaugebiet ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-708', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 708, '7:598a6f01ed91ad6b2e06efc581441c25', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-709::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_festsetzungnachlandesrecht ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-709', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 709, '7:24c55795e130d989bea792b3cc727c8c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-710::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_firstrichtungslinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-710', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 710, '7:4b6d11232c16abde4cfa600c5af6f140', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-711::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_foerderungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-711', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 711, '7:dd93e79622b0ca494aa43bc92d204438', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-712::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_freiflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-712', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 712, '7:95d6aaad889ea291e8da6e83b4745467', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-713::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gebaeudeflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-713', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 713, '7:531a963a4999a572a3a858dc10af8df9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-714::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinbedarfsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-714', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 714, '7:b8307f93561c6f9439441badd3e80297', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-715::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-715', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 715, '7:ae81ba088eaa552b2578a0853593fb37', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-716::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gemeinschaftsanlagenzuordnung ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-716', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 716, '7:1c31955f01c3f5b6070573ab57553814', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-717::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_generischesobjekt ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-717', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 717, '7:04d17fbb57e6c8e1acdc75929e45d3e9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-718::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gestaltungbaugebiet ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-718', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 718, '7:9b8a1fa5a354d8baa2d2d947fa8a58b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-719::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gewaesserflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-719', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 719, '7:e718c5bbfff0dfbbe77a35f7fa39d5e8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-720::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_grabungsschutzgebiet ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-720', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 720, '7:872516b9babe99e019c41590861b8fc1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-721::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_gruenflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-721', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 721, '7:1baf0d40d13d098ed92ec0061648d55b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-722::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenmass ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-722', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 722, '7:9d352db3484d08a4c68aca0a5d6f1ba6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-723::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_hoehenpunkt ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-723', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 723, '7:e696fb146f07d63c3974f8474c314396', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-724::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_immissionsschutz ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-724', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 724, '7:98ff74948122bfbb5b3676147ea840e1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-725::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kennzeichnungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-725', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 725, '7:7bc050118bcaa15b1bf2608be943670c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-726::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_kleintierhaltungflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-726', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 726, '7:7c6c9372a15fbd96710c55ce840b1f3f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-727::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_laermschutzbereich ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-727', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 727, '7:6a9c174131a29f983fe7c93a9f9e9075', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-728::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaft ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-728', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 728, '7:e68f05c2fce58153dfc399ca8e2f91b1', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-729::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-729', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 729, '7:327f083ffd7f0f8564e67af8c067a544', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-730::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_landwirtschaftslinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-730', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 730, '7:8ef3e087ca44216cd532fc5d92b55958', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-731::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftreinhalteflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-731', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 731, '7:f72e4cfa5814ee70339752e1f0a1b433', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-732::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_luftverkehrflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-732', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 732, '7:a4df24916307cbc7559e9a7e52cf4b69', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-733::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenausschlussflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-733', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 733, '7:6ae74ded4ee5c493b3b0d08d27d79dc0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-734::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nebenanlagenflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-734', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 734, '7:61544a96ae1a0552b80b5da5866b33f4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-735::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_nutzungsartengrenze ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-735', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 735, '7:2b26352d1ce42d9889dba3e894a6cd25', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-736::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_persgruppenbestimmteflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-736', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 736, '7:08aabc450fd8bd5359359024f758bd66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-737::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_regelungvergnuegungsstaetten ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-737', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 737, '7:07b3ced32af58eabad3328b8e1241ab2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-738::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_rekultivierungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-738', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 738, '7:370b766e32c5e68d1a36c0ebd09c7b08', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-739::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzgebiet ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-739', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 739, '7:de4dc2a407fb8105ad5ac2fe65808afb', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-740::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-740', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 740, '7:2a0a125da2576091a68a108ee85ade32', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-741::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_schutzpflegeentwicklungsmassnahme ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-741', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 741, '7:c3c9c2f19ab3c4a3e8f408475d0d3f8d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-742::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_speziellebauweise ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-742', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 742, '7:64ec48a16bb76e326d8f46ab6119ea25', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-743::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_spielsportanlagenflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-743', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 743, '7:70dc94d3d14e6fa8c04b667d169071d6', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-744::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenbegrenzungslinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-744', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 744, '7:fa7a32159f295399823bb7fd4062663d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-745::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenkoerper ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-745', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 745, '7:15847c324cadd033b68be5b796f1ba44', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-746::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_strassenverkehrsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-746', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 746, '7:8acb69d05a7f87ce4246e2942e6f1852', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-747::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_technikbestimmteflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-747', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 747, '7:c24b9076cbc8202070be80076ab34a8d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-748::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textabschnitt ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-748', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 748, '7:861b5aa457193052bd3a3d26384b923d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-749::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_textlichefestsetzungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-749', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 749, '7:b59c164ea08ec06da550680c1ebac671', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-750::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_ueberbaubaregrundstuecksflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-750', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 750, '7:2c7e2d6ac7912c50e509a572902c29ab', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-751::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_unverbindlichevormerkung ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-751', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 751, '7:11feeb3bd6da2879d3f9d11f6f302189', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-752::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_veraenderungssperre ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-752', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 752, '7:ca1f2c2131532a30fc977b70f0c22094', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-753::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgung ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-753', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 753, '7:418a43716af95ccdf219752ae7f4e495', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-754::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-754', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 754, '7:897c2cdfda01c939af5ff2257d03aa37', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-755::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verentsorgungsleitunglinie ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-755', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 755, '7:3307f1819141fbff4b070b693840382e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-756::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_verkehrsflaechebesondererzweckbestimmung ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-756', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 756, '7:83e6c09ce59c168ab4cc4fef35a0f08e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-757::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_vorbhochwschutzflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-757', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 757, '7:a417b66931bc5ae4a5f4222adcd3c6c3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-758::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_waldflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-758', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 758, '7:fbf5694bb82d75b8971a9bc91609fb09', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-759::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserrechtlichefestsetzungsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-759', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 759, '7:6536f2a548d526da81553ca53ad434a9', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-760::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wasserwirtschaftsflaeche ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-760', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 760, '7:ad37d6a3d030323437c92fdd9298f81f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-761::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_bp_wegerecht ADD xplan_inkrafttretensdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-761', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 761, '7:8f8f590b1b3627673bf1b980f303de39', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-762::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_sperrgebiettyp_nsm TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-762', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 762, '7:9a72388beb1372544bfb2e2cbf7fc4cd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-763::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_sperrgebiet ADD xplan_sperrgebiettyp_nsmcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-763', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 763, '7:2e345e487a50b0426375c6abdaad8fe4', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-764::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_typrk_nsm TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-764', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 764, '7:b599cb4b4ecaba2fbfc15d32fd84cb1e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-765::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_raumkategorie ADD xplan_typrk_nsmcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-765', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 765, '7:51f629b3ff5e43a4676ffb25e44a43b5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-766::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_weitererachsentyp_nsm TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-766', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 766, '7:f48f56d9665caa188264f55015be9248', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-767::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_rp_achse ADD xplan_weitererachsentyp_nsmcode TEXT;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-767', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 767, '7:cd3ba77357dd3e97926a5c95a1b3ec66', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-768::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-768', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 768, '7:61ebb4b1c0d91d50a93d318f59015372', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-769::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_abgrabungsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-769', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 769, '7:5337e146393f45be6076236dbbd752ba', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-770::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_anpassungklimawandel ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-770', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 770, '7:3641e9252d422aeb6a924b02f0c7bcb8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-771::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-771', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 771, '7:0d2506693bf709528e136e967690c1b0', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-772::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_aufschuettungsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-772', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 772, '7:3d7b91938db3887087c0a3c913c8e85b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-773::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_ausgleichsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-773', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 773, '7:aa7a41270d7165c3a17846bd5d56118e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-774::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bahnverkehr ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-774', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 774, '7:bcd89a3cecbff6a5688e2d5d9570a02f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-775::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bauschutzbereich ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-775', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 775, '7:d080825f148a271c1f7d3876e95e6a5e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-776::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bebauungsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-776', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 776, '7:eb3cd536c68b934caeff13d5ac4dd31c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-777::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bereich ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-777', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 777, '7:9a97022b37099e841f599ea85266fcf5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-778::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetze ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-778', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 778, '7:b339f90d53ada91a8696f66d2bc27f25', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-779::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_bodenschaetzeflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-779', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 779, '7:cf712320a7afe2eb3b2e9e6515d99440', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-780::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_denkmalschutzensemble ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-780', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 780, '7:00e2c05291570f7095d4def0b1f4f474', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-781::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_erhaltungssatzung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-781', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 781, '7:18de6c1910369352a2dc1297d6833492', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-782::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_fachgesetz ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-782', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 782, '7:520bdf98d159056526ff42c4edd82605', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-783::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gemeinbedarf ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-783', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 783, '7:a083667ea24dd0a484887245fabfa1c5', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-784::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_generischesobjekt ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-784', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 784, '7:04eec253f161fa7745c42c6c8998d4f8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-785::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gewaesser ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-785', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 785, '7:68c08a1fd1b4586d84dc07141af6fb3b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-786::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_grabungsschutzgebiet ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-786', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 786, '7:6a7f43355a4f7b596743383fcbff27aa', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-787::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_gruen ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-787', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 787, '7:de1810eb075570435946fdfc44d97b2f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-788::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_keinezentrabwasserbeseitigungflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-788', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 788, '7:2cb0cb8caf4c859bf0e9e700e17d437d', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-789::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_kennzeichnung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-789', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 789, '7:39da563875e16670b5ac3bd35c99b7c8', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-790::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_laermschutzbereich ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-790', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 790, '7:6bbbcb6529e2e75cf321adf26b04ea85', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-791::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_landwirtschaftsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-791', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 791, '7:a2b9b7f7b07f7a9d7f3758190d76e05e', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-792::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_luftverkehr ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-792', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 792, '7:41b7fb4e750d6cd1bd1743d90b6c09a3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-793::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_nutzungsbeschraenkungsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-793', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 793, '7:48d4ef5679437eb4d320769de30b092b', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-794::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_privilegiertesvorhaben ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-794', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 794, '7:b36aadf46ab01a9e4d88822c2d2ecef3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-795::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_priviligiertesvorhaben ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-795', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 795, '7:fe4a798e85e4f742d94b7114cd037e74', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-796::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzgebiet ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-796', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 796, '7:7f53b6be28360a2d6826659c732ef846', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-797::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_schutzpflegeentwicklung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-797', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 797, '7:344711fa8843a9adc13ea8903a8ec277', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-798::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_spielsportanlage ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-798', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 798, '7:2c41a54fdc8ba623092a64ba4f3a1152', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-799::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_strassenverkehr ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-799', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 799, '7:7c8b09c15f8a77cdcdf8dd1cc2b65824', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-800::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textabschnitt ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-800', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 800, '7:b2cb296e9c83d655e181a0fe4de5dcb7', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-801::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_textlichedarstellungsflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-801', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 801, '7:8f3e62ab70524b43731bca8b3e558b8f', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-802::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_unverbindlichevormerkung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-802', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 802, '7:0f8ecbbb2771f528d943c990cbfc79fd', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-803::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_verentsorgung ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-803', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 803, '7:bc053fa2dd0e21ee56e524d21d942132', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-804::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbehalteflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-804', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 804, '7:0465e322c4f2daef9277b8611bd084f3', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-805::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_vorbhochwschutz ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-805', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 805, '7:f36f06bb8775f2db6f3da6c969930247', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-806::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_waldflaeche ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-806', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 806, '7:c3e270c01f83da10bbd3fed4b45a801c', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-807::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserrecht ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-807', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 807, '7:6d4a098df558d57dad6bd8a13886a7c2', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-808::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_wasserwirtschaft ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-808', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 808, '7:325aac681983accf3205bc50f85ab548', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Changeset changelog_xplansyn.xml::1428411796051-809::lgvxplanisk (generated)
ALTER TABLE xplansyn.xplan_fp_zentralerversorgungsbereich ADD xplan_wirksamkeitsdatum date;

INSERT INTO xplansyn.databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, LIQUIBASE) VALUES ('1428411796051-809', 'lgvxplanisk (generated)', 'changelog_xplansyn.xml', NOW(), 809, '7:d03e093d47b5f4f0e76cd59682936fda', 'addColumn', '', 'EXECUTED', '3.3.2');

-- Release Database Lock
UPDATE xplansyn.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

