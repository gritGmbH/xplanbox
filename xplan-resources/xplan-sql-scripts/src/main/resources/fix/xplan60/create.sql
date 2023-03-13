---
-- #%L
-- xplan-sql-scripts - SQL Skripte zum Aufsetzen der Datenhaltung.
-- %%
-- Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
-- %%
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
-- #L%
---
/* --- BEGIN schema setup --- */
CREATE SCHEMA xplan60;
SET search_path TO xplan60,public;

/* --- END schema setup --- */
CREATE TABLE FEATURE_TYPES (id smallint PRIMARY KEY, qname text NOT NULL);
COMMENT ON TABLE FEATURE_TYPES IS 'Ids and bboxes of concrete feature types';
SELECT ADDGEOMETRYCOLUMN('xplan60', 'feature_types','bbox','25832','GEOMETRY',2);
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (0,'{http://www.opengis.net/gml/3.2}AbstractDiscreteCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (1,'{http://www.opengis.net/gml/3.2}MultiPointCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (2,'{http://www.opengis.net/gml/3.2}RectifiedGridCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (3,'{http://www.xplanung.de/xplangml/6/0}BP_AbgrabungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (4,'{http://www.xplanung.de/xplangml/6/0}BP_AbstandsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (5,'{http://www.xplanung.de/xplangml/6/0}BP_AbstandsMass');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (6,'{http://www.xplanung.de/xplangml/6/0}BP_AbweichungVonBaugrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (7,'{http://www.xplanung.de/xplangml/6/0}BP_AbweichungVonUeberbaubarerGrundstuecksFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (8,'{http://www.xplanung.de/xplangml/6/0}BP_AnpflanzungBindungErhaltung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (9,'{http://www.xplanung.de/xplangml/6/0}BP_AufschuettungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (10,'{http://www.xplanung.de/xplangml/6/0}BP_AusgleichsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (11,'{http://www.xplanung.de/xplangml/6/0}BP_AusgleichsMassnahme');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (12,'{http://www.xplanung.de/xplangml/6/0}BP_BauGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (13,'{http://www.xplanung.de/xplangml/6/0}BP_BauLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (14,'{http://www.xplanung.de/xplangml/6/0}BP_BaugebietsTeilFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (15,'{http://www.xplanung.de/xplangml/6/0}BP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (16,'{http://www.xplanung.de/xplangml/6/0}BP_BereichOhneEinAusfahrtLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (17,'{http://www.xplanung.de/xplangml/6/0}BP_BesondererNutzungszweckFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (18,'{http://www.xplanung.de/xplangml/6/0}BP_EinfahrtPunkt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (19,'{http://www.xplanung.de/xplangml/6/0}BP_EinfahrtsbereichLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (20,'{http://www.xplanung.de/xplangml/6/0}BP_EingriffsBereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (21,'{http://www.xplanung.de/xplangml/6/0}BP_FestsetzungNachLandesrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (22,'{http://www.xplanung.de/xplangml/6/0}BP_FlaecheOhneFestsetzung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (23,'{http://www.xplanung.de/xplangml/6/0}BP_FoerderungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (24,'{http://www.xplanung.de/xplangml/6/0}BP_FreiFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (25,'{http://www.xplanung.de/xplangml/6/0}BP_GebaeudeFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (26,'{http://www.xplanung.de/xplangml/6/0}BP_GebaeudeStellung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (27,'{http://www.xplanung.de/xplangml/6/0}BP_GemeinbedarfsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (28,'{http://www.xplanung.de/xplangml/6/0}BP_GemeinschaftsanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (29,'{http://www.xplanung.de/xplangml/6/0}BP_GemeinschaftsanlagenZuordnung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (30,'{http://www.xplanung.de/xplangml/6/0}BP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (31,'{http://www.xplanung.de/xplangml/6/0}BP_GruenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (32,'{http://www.xplanung.de/xplangml/6/0}BP_HoehenMass');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (33,'{http://www.xplanung.de/xplangml/6/0}BP_Immissionsschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (34,'{http://www.xplanung.de/xplangml/6/0}BP_KennzeichnungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (35,'{http://www.xplanung.de/xplangml/6/0}BP_KleintierhaltungFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (36,'{http://www.xplanung.de/xplangml/6/0}BP_LandwirtschaftsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (37,'{http://www.xplanung.de/xplangml/6/0}BP_NebenanlagenAusschlussFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (38,'{http://www.xplanung.de/xplangml/6/0}BP_NebenanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (39,'{http://www.xplanung.de/xplangml/6/0}BP_NichtUeberbaubareGrundstuecksflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (40,'{http://www.xplanung.de/xplangml/6/0}BP_NutzungsartenGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (41,'{http://www.xplanung.de/xplangml/6/0}BP_PersGruppenBestimmteFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (42,'{http://www.xplanung.de/xplangml/6/0}BP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (43,'{http://www.xplanung.de/xplangml/6/0}BP_RegelungVergnuegungsstaetten');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (44,'{http://www.xplanung.de/xplangml/6/0}BP_RichtungssektorGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (45,'{http://www.xplanung.de/xplangml/6/0}BP_SchutzPflegeEntwicklungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (46,'{http://www.xplanung.de/xplangml/6/0}BP_SchutzPflegeEntwicklungsMassnahme');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (47,'{http://www.xplanung.de/xplangml/6/0}BP_SpezielleBauweise');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (48,'{http://www.xplanung.de/xplangml/6/0}BP_SpielSportanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (49,'{http://www.xplanung.de/xplangml/6/0}BP_StrassenbegrenzungsLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (50,'{http://www.xplanung.de/xplangml/6/0}BP_Strassenkoerper');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (51,'{http://www.xplanung.de/xplangml/6/0}BP_TechnischeMassnahmenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (52,'{http://www.xplanung.de/xplangml/6/0}BP_TextAbschnittFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (53,'{http://www.xplanung.de/xplangml/6/0}BP_UeberbaubareGrundstuecksFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (54,'{http://www.xplanung.de/xplangml/6/0}BP_UnverbindlicheVormerkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (55,'{http://www.xplanung.de/xplangml/6/0}BP_VerEntsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (56,'{http://www.xplanung.de/xplangml/6/0}BP_Veraenderungssperre');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (57,'{http://www.xplanung.de/xplangml/6/0}BP_WaldFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (58,'{http://www.xplanung.de/xplangml/6/0}BP_Wegerecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (59,'{http://www.xplanung.de/xplangml/6/0}BP_WohngebaeudeFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (60,'{http://www.xplanung.de/xplangml/6/0}BP_ZentralerVersorgungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (61,'{http://www.xplanung.de/xplangml/6/0}BP_ZusatzkontingentLaerm');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (62,'{http://www.xplanung.de/xplangml/6/0}BP_ZusatzkontingentLaermFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (63,'{http://www.xplanung.de/xplangml/6/0}FP_Abgrabung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (64,'{http://www.xplanung.de/xplangml/6/0}FP_AnpassungKlimawandel');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (65,'{http://www.xplanung.de/xplangml/6/0}FP_Aufschuettung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (66,'{http://www.xplanung.de/xplangml/6/0}FP_AusgleichsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (67,'{http://www.xplanung.de/xplangml/6/0}FP_BebauungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (68,'{http://www.xplanung.de/xplangml/6/0}FP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (69,'{http://www.xplanung.de/xplangml/6/0}FP_DarstellungNachLandesrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (70,'{http://www.xplanung.de/xplangml/6/0}FP_FlaecheOhneDarstellung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (71,'{http://www.xplanung.de/xplangml/6/0}FP_Gemeinbedarf');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (72,'{http://www.xplanung.de/xplangml/6/0}FP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (73,'{http://www.xplanung.de/xplangml/6/0}FP_Gruen');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (74,'{http://www.xplanung.de/xplangml/6/0}FP_KeineZentrAbwasserBeseitigungFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (75,'{http://www.xplanung.de/xplangml/6/0}FP_Kennzeichnung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (76,'{http://www.xplanung.de/xplangml/6/0}FP_Landwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (77,'{http://www.xplanung.de/xplangml/6/0}FP_Nutzungsbeschraenkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (78,'{http://www.xplanung.de/xplangml/6/0}FP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (79,'{http://www.xplanung.de/xplangml/6/0}FP_PrivilegiertesVorhaben');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (80,'{http://www.xplanung.de/xplangml/6/0}FP_SchutzPflegeEntwicklung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (81,'{http://www.xplanung.de/xplangml/6/0}FP_SpielSportanlage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (82,'{http://www.xplanung.de/xplangml/6/0}FP_TextAbschnittFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (83,'{http://www.xplanung.de/xplangml/6/0}FP_UnverbindlicheVormerkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (84,'{http://www.xplanung.de/xplangml/6/0}FP_VerEntsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (85,'{http://www.xplanung.de/xplangml/6/0}FP_VorbehalteFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (86,'{http://www.xplanung.de/xplangml/6/0}FP_WaldFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (87,'{http://www.xplanung.de/xplangml/6/0}FP_ZentralerVersorgungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (88,'{http://www.xplanung.de/xplangml/6/0}LP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (89,'{http://www.xplanung.de/xplangml/6/0}LP_BiotopverbundBiotopvernetzung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (90,'{http://www.xplanung.de/xplangml/6/0}LP_Eingriffsregelung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (91,'{http://www.xplanung.de/xplangml/6/0}LP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (92,'{http://www.xplanung.de/xplangml/6/0}LP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (93,'{http://www.xplanung.de/xplangml/6/0}LP_SchutzBestimmterTeileVonNaturUndLandschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (94,'{http://www.xplanung.de/xplangml/6/0}LP_TextAbschnittObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (95,'{http://www.xplanung.de/xplangml/6/0}LP_ZieleErfordernisseMassnahmen');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (96,'{http://www.xplanung.de/xplangml/6/0}RP_Achse');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (97,'{http://www.xplanung.de/xplangml/6/0}RP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (98,'{http://www.xplanung.de/xplangml/6/0}RP_Bodenschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (99,'{http://www.xplanung.de/xplangml/6/0}RP_Einzelhandel');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (100,'{http://www.xplanung.de/xplangml/6/0}RP_Energieversorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (101,'{http://www.xplanung.de/xplangml/6/0}RP_Entsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (102,'{http://www.xplanung.de/xplangml/6/0}RP_Erholung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (103,'{http://www.xplanung.de/xplangml/6/0}RP_ErneuerbareEnergie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (104,'{http://www.xplanung.de/xplangml/6/0}RP_Forstwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (105,'{http://www.xplanung.de/xplangml/6/0}RP_Freiraum');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (106,'{http://www.xplanung.de/xplangml/6/0}RP_Funktionszuweisung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (107,'{http://www.xplanung.de/xplangml/6/0}RP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (108,'{http://www.xplanung.de/xplangml/6/0}RP_Gewaesser');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (109,'{http://www.xplanung.de/xplangml/6/0}RP_Grenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (110,'{http://www.xplanung.de/xplangml/6/0}RP_GruenzugGruenzaesur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (111,'{http://www.xplanung.de/xplangml/6/0}RP_Hochwasserschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (112,'{http://www.xplanung.de/xplangml/6/0}RP_IndustrieGewerbe');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (113,'{http://www.xplanung.de/xplangml/6/0}RP_Klimaschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (114,'{http://www.xplanung.de/xplangml/6/0}RP_Kommunikation');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (115,'{http://www.xplanung.de/xplangml/6/0}RP_Kulturlandschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (116,'{http://www.xplanung.de/xplangml/6/0}RP_LaermschutzBauschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (117,'{http://www.xplanung.de/xplangml/6/0}RP_Landwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (118,'{http://www.xplanung.de/xplangml/6/0}RP_Luftverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (119,'{http://www.xplanung.de/xplangml/6/0}RP_NaturLandschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (120,'{http://www.xplanung.de/xplangml/6/0}RP_NaturschutzrechtlichesSchutzgebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (121,'{http://www.xplanung.de/xplangml/6/0}RP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (122,'{http://www.xplanung.de/xplangml/6/0}RP_Planungsraum');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (123,'{http://www.xplanung.de/xplangml/6/0}RP_RadwegWanderweg');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (124,'{http://www.xplanung.de/xplangml/6/0}RP_Raumkategorie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (125,'{http://www.xplanung.de/xplangml/6/0}RP_Rohstoff');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (126,'{http://www.xplanung.de/xplangml/6/0}RP_Schienenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (127,'{http://www.xplanung.de/xplangml/6/0}RP_Siedlung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (128,'{http://www.xplanung.de/xplangml/6/0}RP_SonstVerkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (129,'{http://www.xplanung.de/xplangml/6/0}RP_SonstigeInfrastruktur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (130,'{http://www.xplanung.de/xplangml/6/0}RP_SonstigerFreiraumschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (131,'{http://www.xplanung.de/xplangml/6/0}RP_SonstigerSiedlungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (132,'{http://www.xplanung.de/xplangml/6/0}RP_SozialeInfrastruktur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (133,'{http://www.xplanung.de/xplangml/6/0}RP_Sperrgebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (134,'{http://www.xplanung.de/xplangml/6/0}RP_Sportanlage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (135,'{http://www.xplanung.de/xplangml/6/0}RP_Strassenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (136,'{http://www.xplanung.de/xplangml/6/0}RP_Verkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (137,'{http://www.xplanung.de/xplangml/6/0}RP_Wasserschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (138,'{http://www.xplanung.de/xplangml/6/0}RP_Wasserverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (139,'{http://www.xplanung.de/xplangml/6/0}RP_Wasserwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (140,'{http://www.xplanung.de/xplangml/6/0}RP_WohnenSiedlung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (141,'{http://www.xplanung.de/xplangml/6/0}RP_ZentralerOrt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (142,'{http://www.xplanung.de/xplangml/6/0}SO_Baubeschraenkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (143,'{http://www.xplanung.de/xplangml/6/0}SO_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (144,'{http://www.xplanung.de/xplangml/6/0}SO_Bodenschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (145,'{http://www.xplanung.de/xplangml/6/0}SO_Denkmalschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (146,'{http://www.xplanung.de/xplangml/6/0}SO_Forstrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (147,'{http://www.xplanung.de/xplangml/6/0}SO_Gebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (148,'{http://www.xplanung.de/xplangml/6/0}SO_Gelaendemorphologie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (149,'{http://www.xplanung.de/xplangml/6/0}SO_Gewaesser');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (150,'{http://www.xplanung.de/xplangml/6/0}SO_Grenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (151,'{http://www.xplanung.de/xplangml/6/0}SO_Linienobjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (152,'{http://www.xplanung.de/xplangml/6/0}SO_Luftverkehrsrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (153,'{http://www.xplanung.de/xplangml/6/0}SO_Objekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (154,'{http://www.xplanung.de/xplangml/6/0}SO_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (155,'{http://www.xplanung.de/xplangml/6/0}SO_Schienenverkehrsrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (156,'{http://www.xplanung.de/xplangml/6/0}SO_SchutzgebietWasserrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (157,'{http://www.xplanung.de/xplangml/6/0}SO_Sichtflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (158,'{http://www.xplanung.de/xplangml/6/0}SO_SonstigesRecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (159,'{http://www.xplanung.de/xplangml/6/0}SO_Strassenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (160,'{http://www.xplanung.de/xplangml/6/0}SO_TextAbschnittFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (161,'{http://www.xplanung.de/xplangml/6/0}SO_Wasserrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (162,'{http://www.xplanung.de/xplangml/6/0}SO_Wasserwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (163,'{http://www.xplanung.de/xplangml/6/0}XP_BegruendungAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (164,'{http://www.xplanung.de/xplangml/6/0}XP_FPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (165,'{http://www.xplanung.de/xplangml/6/0}XP_LPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (166,'{http://www.xplanung.de/xplangml/6/0}XP_LTO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (167,'{http://www.xplanung.de/xplangml/6/0}XP_Nutzungsschablone');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (168,'{http://www.xplanung.de/xplangml/6/0}XP_PPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (169,'{http://www.xplanung.de/xplangml/6/0}XP_PTO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (170,'{http://www.xplanung.de/xplangml/6/0}XP_Praesentationsobjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (171,'{http://www.xplanung.de/xplangml/6/0}XP_TextAbschnitt');
CREATE TABLE GML_OBJECTS (id serial PRIMARY KEY, gml_id text UNIQUE NOT NULL, ft_type smallint REFERENCES FEATURE_TYPES , binary_object bytea, plan_id text, plan_name text, internal_id text, rechtsstand text);
COMMENT ON TABLE GML_OBJECTS IS 'All objects (features and geometries)';
SELECT ADDGEOMETRYCOLUMN('xplan60', 'gml_objects','gml_bounded_by','25832','GEOMETRY',2);
ALTER TABLE GML_OBJECTS ADD CONSTRAINT gml_objects_geochk CHECK (ST_IsValid(gml_bounded_by));
CREATE INDEX gml_objects_sidx ON GML_OBJECTS  USING GIST (gml_bounded_by);
