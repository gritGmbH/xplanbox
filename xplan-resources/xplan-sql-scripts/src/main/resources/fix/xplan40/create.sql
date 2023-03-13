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
CREATE SCHEMA xplan40;
SET search_path TO xplan40,public;
/* --- END schema setup --- */

CREATE TABLE feature_types (id smallint PRIMARY KEY, qname text NOT NULL);
COMMENT ON TABLE feature_types IS 'Ids and bboxes of concrete feature types';
SELECT ADDGEOMETRYCOLUMN('xplan40', 'feature_types','bbox','25832','GEOMETRY',2);
INSERT INTO feature_types  (id,qname) VALUES (0,'{http://www.opengis.net/gml/3.2}AbstractDiscreteCoverage');
INSERT INTO feature_types  (id,qname) VALUES (1,'{http://www.opengis.net/gml/3.2}MultiPointCoverage');
INSERT INTO feature_types  (id,qname) VALUES (2,'{http://www.opengis.net/gml/3.2}RectifiedGridCoverage');
INSERT INTO feature_types  (id,qname) VALUES (3,'{http://www.xplanung.de/xplangml/4/0}BP_AbgrabungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (4,'{http://www.xplanung.de/xplangml/4/0}BP_AbstandsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (5,'{http://www.xplanung.de/xplangml/4/0}BP_AbstandsMass');
INSERT INTO feature_types  (id,qname) VALUES (6,'{http://www.xplanung.de/xplangml/4/0}BP_AnpflanzungBindungErhaltung');
INSERT INTO feature_types  (id,qname) VALUES (7,'{http://www.xplanung.de/xplangml/4/0}BP_AufschuettungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (8,'{http://www.xplanung.de/xplangml/4/0}BP_AusgleichsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (9,'{http://www.xplanung.de/xplangml/4/0}BP_AusgleichsMassnahme');
INSERT INTO feature_types  (id,qname) VALUES (10,'{http://www.xplanung.de/xplangml/4/0}BP_BauGrenze');
INSERT INTO feature_types  (id,qname) VALUES (11,'{http://www.xplanung.de/xplangml/4/0}BP_BauLinie');
INSERT INTO feature_types  (id,qname) VALUES (12,'{http://www.xplanung.de/xplangml/4/0}BP_Baugebiet');
INSERT INTO feature_types  (id,qname) VALUES (13,'{http://www.xplanung.de/xplangml/4/0}BP_BaugebietsTeilFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (14,'{http://www.xplanung.de/xplangml/4/0}BP_Bereich');
INSERT INTO feature_types  (id,qname) VALUES (15,'{http://www.xplanung.de/xplangml/4/0}BP_BereichOhneEinAusfahrtLinie');
INSERT INTO feature_types  (id,qname) VALUES (16,'{http://www.xplanung.de/xplangml/4/0}BP_BesondererNutzungszweckFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (17,'{http://www.xplanung.de/xplangml/4/0}BP_BodenschaetzeFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (18,'{http://www.xplanung.de/xplangml/4/0}BP_DenkmalschutzEinzelanlage');
INSERT INTO feature_types  (id,qname) VALUES (19,'{http://www.xplanung.de/xplangml/4/0}BP_DenkmalschutzEnsembleFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (20,'{http://www.xplanung.de/xplangml/4/0}BP_EinfahrtPunkt');
INSERT INTO feature_types  (id,qname) VALUES (21,'{http://www.xplanung.de/xplangml/4/0}BP_EinfahrtsbereichLinie');
INSERT INTO feature_types  (id,qname) VALUES (22,'{http://www.xplanung.de/xplangml/4/0}BP_EingriffsBereich');
INSERT INTO feature_types  (id,qname) VALUES (23,'{http://www.xplanung.de/xplangml/4/0}BP_ErhaltungsBereichFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (24,'{http://www.xplanung.de/xplangml/4/0}BP_ErneuerbareEnergieFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (25,'{http://www.xplanung.de/xplangml/4/0}BP_FirstRichtungsLinie');
INSERT INTO feature_types  (id,qname) VALUES (26,'{http://www.xplanung.de/xplangml/4/0}BP_FoerderungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (27,'{http://www.xplanung.de/xplangml/4/0}BP_FreiFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (28,'{http://www.xplanung.de/xplangml/4/0}BP_GebaeudeFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (29,'{http://www.xplanung.de/xplangml/4/0}BP_GemeinbedarfsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (30,'{http://www.xplanung.de/xplangml/4/0}BP_GemeinschaftsanlagenFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (31,'{http://www.xplanung.de/xplangml/4/0}BP_GemeinschaftsanlagenZuordnung');
INSERT INTO feature_types  (id,qname) VALUES (32,'{http://www.xplanung.de/xplangml/4/0}BP_GenerischesObjekt');
INSERT INTO feature_types  (id,qname) VALUES (33,'{http://www.xplanung.de/xplangml/4/0}BP_GewaesserFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (34,'{http://www.xplanung.de/xplangml/4/0}BP_GruenFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (35,'{http://www.xplanung.de/xplangml/4/0}BP_HoehenMass');
INSERT INTO feature_types  (id,qname) VALUES (36,'{http://www.xplanung.de/xplangml/4/0}BP_Immissionsschutz');
INSERT INTO feature_types  (id,qname) VALUES (37,'{http://www.xplanung.de/xplangml/4/0}BP_KennzeichnungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (38,'{http://www.xplanung.de/xplangml/4/0}BP_KleintierhaltungFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (39,'{http://www.xplanung.de/xplangml/4/0}BP_Landwirtschaft');
INSERT INTO feature_types  (id,qname) VALUES (40,'{http://www.xplanung.de/xplangml/4/0}BP_LuftreinhalteFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (41,'{http://www.xplanung.de/xplangml/4/0}BP_NebenanlagenAusschlussFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (42,'{http://www.xplanung.de/xplangml/4/0}BP_NebenanlagenFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (43,'{http://www.xplanung.de/xplangml/4/0}BP_NutzungsartenGrenze');
INSERT INTO feature_types  (id,qname) VALUES (44,'{http://www.xplanung.de/xplangml/4/0}BP_PersGruppenBestimmteFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (45,'{http://www.xplanung.de/xplangml/4/0}BP_Plan');
INSERT INTO feature_types  (id,qname) VALUES (46,'{http://www.xplanung.de/xplangml/4/0}BP_RasterplanAenderung');
INSERT INTO feature_types  (id,qname) VALUES (47,'{http://www.xplanung.de/xplangml/4/0}BP_RekultivierungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (48,'{http://www.xplanung.de/xplangml/4/0}BP_SchutzPflegeEntwicklungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (49,'{http://www.xplanung.de/xplangml/4/0}BP_SchutzPflegeEntwicklungsMassnahme');
INSERT INTO feature_types  (id,qname) VALUES (50,'{http://www.xplanung.de/xplangml/4/0}BP_Schutzgebiet');
INSERT INTO feature_types  (id,qname) VALUES (51,'{http://www.xplanung.de/xplangml/4/0}BP_SpezielleBauweise');
INSERT INTO feature_types  (id,qname) VALUES (52,'{http://www.xplanung.de/xplangml/4/0}BP_SpielSportanlagenFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (53,'{http://www.xplanung.de/xplangml/4/0}BP_StrassenVerkehrsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (54,'{http://www.xplanung.de/xplangml/4/0}BP_StrassenbegrenzungsLinie');
INSERT INTO feature_types  (id,qname) VALUES (55,'{http://www.xplanung.de/xplangml/4/0}BP_Strassenkoerper');
INSERT INTO feature_types  (id,qname) VALUES (56,'{http://www.xplanung.de/xplangml/4/0}BP_TextlicheFestsetzungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (57,'{http://www.xplanung.de/xplangml/4/0}BP_UeberbaubareGrundstuecksFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (58,'{http://www.xplanung.de/xplangml/4/0}BP_UnverbindlicheVormerkung');
INSERT INTO feature_types  (id,qname) VALUES (59,'{http://www.xplanung.de/xplangml/4/0}BP_VerEntsorgung');
INSERT INTO feature_types  (id,qname) VALUES (60,'{http://www.xplanung.de/xplangml/4/0}BP_Veraenderungssperre');
INSERT INTO feature_types  (id,qname) VALUES (61,'{http://www.xplanung.de/xplangml/4/0}BP_VerkehrsflaecheBesondererZweckbestimmung');
INSERT INTO feature_types  (id,qname) VALUES (62,'{http://www.xplanung.de/xplangml/4/0}BP_WaldFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (63,'{http://www.xplanung.de/xplangml/4/0}BP_WasserwirtschaftsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (64,'{http://www.xplanung.de/xplangml/4/0}BP_Wegerecht');
INSERT INTO feature_types  (id,qname) VALUES (65,'{http://www.xplanung.de/xplangml/4/0}FP_AbgrabungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (66,'{http://www.xplanung.de/xplangml/4/0}FP_AufschuettungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (67,'{http://www.xplanung.de/xplangml/4/0}FP_AusgleichsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (68,'{http://www.xplanung.de/xplangml/4/0}FP_BebauungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (69,'{http://www.xplanung.de/xplangml/4/0}FP_Bereich');
INSERT INTO feature_types  (id,qname) VALUES (70,'{http://www.xplanung.de/xplangml/4/0}FP_BodenschaetzeFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (71,'{http://www.xplanung.de/xplangml/4/0}FP_Gemeinbedarf');
INSERT INTO feature_types  (id,qname) VALUES (72,'{http://www.xplanung.de/xplangml/4/0}FP_GenerischesObjekt');
INSERT INTO feature_types  (id,qname) VALUES (73,'{http://www.xplanung.de/xplangml/4/0}FP_Gewaesser');
INSERT INTO feature_types  (id,qname) VALUES (74,'{http://www.xplanung.de/xplangml/4/0}FP_Gruen');
INSERT INTO feature_types  (id,qname) VALUES (75,'{http://www.xplanung.de/xplangml/4/0}FP_KeineZentrAbwasserBeseitigungFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (76,'{http://www.xplanung.de/xplangml/4/0}FP_Kennzeichnung');
INSERT INTO feature_types  (id,qname) VALUES (77,'{http://www.xplanung.de/xplangml/4/0}FP_LandwirtschaftsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (78,'{http://www.xplanung.de/xplangml/4/0}FP_NutzungsbeschraenkungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (79,'{http://www.xplanung.de/xplangml/4/0}FP_Plan');
INSERT INTO feature_types  (id,qname) VALUES (80,'{http://www.xplanung.de/xplangml/4/0}FP_PrivilegiertesVorhaben');
INSERT INTO feature_types  (id,qname) VALUES (81,'{http://www.xplanung.de/xplangml/4/0}FP_RasterplanAenderung');
INSERT INTO feature_types  (id,qname) VALUES (82,'{http://www.xplanung.de/xplangml/4/0}FP_SchutzPflegeEntwicklung');
INSERT INTO feature_types  (id,qname) VALUES (83,'{http://www.xplanung.de/xplangml/4/0}FP_SpielSportanlage');
INSERT INTO feature_types  (id,qname) VALUES (84,'{http://www.xplanung.de/xplangml/4/0}FP_Strassenverkehr');
INSERT INTO feature_types  (id,qname) VALUES (85,'{http://www.xplanung.de/xplangml/4/0}FP_TextlicheDarstellungsFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (86,'{http://www.xplanung.de/xplangml/4/0}FP_UnverbindlicheVormerkung');
INSERT INTO feature_types  (id,qname) VALUES (87,'{http://www.xplanung.de/xplangml/4/0}FP_VerEntsorgung');
INSERT INTO feature_types  (id,qname) VALUES (88,'{http://www.xplanung.de/xplangml/4/0}FP_VorbehalteFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (89,'{http://www.xplanung.de/xplangml/4/0}FP_WaldFlaeche');
INSERT INTO feature_types  (id,qname) VALUES (90,'{http://www.xplanung.de/xplangml/4/0}FP_Wasserwirtschaft');
INSERT INTO feature_types  (id,qname) VALUES (91,'{http://www.xplanung.de/xplangml/4/0}LP_Abgrenzung');
INSERT INTO feature_types  (id,qname) VALUES (92,'{http://www.xplanung.de/xplangml/4/0}LP_AllgGruenflaeche');
INSERT INTO feature_types  (id,qname) VALUES (93,'{http://www.xplanung.de/xplangml/4/0}LP_Ausgleich');
INSERT INTO feature_types  (id,qname) VALUES (94,'{http://www.xplanung.de/xplangml/4/0}LP_Bereich');
INSERT INTO feature_types  (id,qname) VALUES (95,'{http://www.xplanung.de/xplangml/4/0}LP_Biotopverbundflaeche');
INSERT INTO feature_types  (id,qname) VALUES (96,'{http://www.xplanung.de/xplangml/4/0}LP_Bodenschutzrecht');
INSERT INTO feature_types  (id,qname) VALUES (97,'{http://www.xplanung.de/xplangml/4/0}LP_Denkmalschutzrecht');
INSERT INTO feature_types  (id,qname) VALUES (98,'{http://www.xplanung.de/xplangml/4/0}LP_ErholungFreizeit');
INSERT INTO feature_types  (id,qname) VALUES (99,'{http://www.xplanung.de/xplangml/4/0}LP_Forstrecht');
INSERT INTO feature_types  (id,qname) VALUES (100,'{http://www.xplanung.de/xplangml/4/0}LP_GenerischesObjekt');
INSERT INTO feature_types  (id,qname) VALUES (101,'{http://www.xplanung.de/xplangml/4/0}LP_NutzungsAusschluss');
INSERT INTO feature_types  (id,qname) VALUES (102,'{http://www.xplanung.de/xplangml/4/0}LP_NutzungserfordernisRegelung');
INSERT INTO feature_types  (id,qname) VALUES (103,'{http://www.xplanung.de/xplangml/4/0}LP_Plan');
INSERT INTO feature_types  (id,qname) VALUES (104,'{http://www.xplanung.de/xplangml/4/0}LP_PlanerischeVertiefung');
INSERT INTO feature_types  (id,qname) VALUES (105,'{http://www.xplanung.de/xplangml/4/0}LP_SchutzPflegeEntwicklung');
INSERT INTO feature_types  (id,qname) VALUES (106,'{http://www.xplanung.de/xplangml/4/0}LP_SchutzobjektBundesrecht');
INSERT INTO feature_types  (id,qname) VALUES (107,'{http://www.xplanung.de/xplangml/4/0}LP_SchutzobjektInternatRecht');
INSERT INTO feature_types  (id,qname) VALUES (108,'{http://www.xplanung.de/xplangml/4/0}LP_SonstigesRecht');
INSERT INTO feature_types  (id,qname) VALUES (109,'{http://www.xplanung.de/xplangml/4/0}LP_WasserrechtGemeingebrEinschraenkungNaturschutz');
INSERT INTO feature_types  (id,qname) VALUES (110,'{http://www.xplanung.de/xplangml/4/0}LP_WasserrechtSchutzgebiet');
INSERT INTO feature_types  (id,qname) VALUES (111,'{http://www.xplanung.de/xplangml/4/0}LP_WasserrechtSonstige');
INSERT INTO feature_types  (id,qname) VALUES (112,'{http://www.xplanung.de/xplangml/4/0}LP_WasserrechtWirtschaftAbflussHochwSchutz');
INSERT INTO feature_types  (id,qname) VALUES (113,'{http://www.xplanung.de/xplangml/4/0}LP_Zwischennutzung');
INSERT INTO feature_types  (id,qname) VALUES (114,'{http://www.xplanung.de/xplangml/4/0}RP_Achse');
INSERT INTO feature_types  (id,qname) VALUES (115,'{http://www.xplanung.de/xplangml/4/0}RP_Bereich');
INSERT INTO feature_types  (id,qname) VALUES (116,'{http://www.xplanung.de/xplangml/4/0}RP_Bodenschutz');
INSERT INTO feature_types  (id,qname) VALUES (117,'{http://www.xplanung.de/xplangml/4/0}RP_Energieversorgung');
INSERT INTO feature_types  (id,qname) VALUES (118,'{http://www.xplanung.de/xplangml/4/0}RP_Entsorgung');
INSERT INTO feature_types  (id,qname) VALUES (119,'{http://www.xplanung.de/xplangml/4/0}RP_Forstwirtschaft');
INSERT INTO feature_types  (id,qname) VALUES (120,'{http://www.xplanung.de/xplangml/4/0}RP_FreizeitErholung');
INSERT INTO feature_types  (id,qname) VALUES (121,'{http://www.xplanung.de/xplangml/4/0}RP_GemeindeFunktionSiedlungsentwicklung');
INSERT INTO feature_types  (id,qname) VALUES (122,'{http://www.xplanung.de/xplangml/4/0}RP_GenerischesObjekt');
INSERT INTO feature_types  (id,qname) VALUES (123,'{http://www.xplanung.de/xplangml/4/0}RP_Gewaesser');
INSERT INTO feature_types  (id,qname) VALUES (124,'{http://www.xplanung.de/xplangml/4/0}RP_Grenze');
INSERT INTO feature_types  (id,qname) VALUES (125,'{http://www.xplanung.de/xplangml/4/0}RP_GruenzugGruenzaesur');
INSERT INTO feature_types  (id,qname) VALUES (126,'{http://www.xplanung.de/xplangml/4/0}RP_Klimaschutz');
INSERT INTO feature_types  (id,qname) VALUES (127,'{http://www.xplanung.de/xplangml/4/0}RP_Kommunikation');
INSERT INTO feature_types  (id,qname) VALUES (128,'{http://www.xplanung.de/xplangml/4/0}RP_KulturellesSachgut');
INSERT INTO feature_types  (id,qname) VALUES (129,'{http://www.xplanung.de/xplangml/4/0}RP_Laermschutzbereich');
INSERT INTO feature_types  (id,qname) VALUES (130,'{http://www.xplanung.de/xplangml/4/0}RP_Landwirtschaft');
INSERT INTO feature_types  (id,qname) VALUES (131,'{http://www.xplanung.de/xplangml/4/0}RP_NaturLandschaft');
INSERT INTO feature_types  (id,qname) VALUES (132,'{http://www.xplanung.de/xplangml/4/0}RP_NaturschutzrechtlichesSchutzgebiet');
INSERT INTO feature_types  (id,qname) VALUES (133,'{http://www.xplanung.de/xplangml/4/0}RP_Plan');
INSERT INTO feature_types  (id,qname) VALUES (134,'{http://www.xplanung.de/xplangml/4/0}RP_Raumkategorie');
INSERT INTO feature_types  (id,qname) VALUES (135,'{http://www.xplanung.de/xplangml/4/0}RP_Rohstoffsicherung');
INSERT INTO feature_types  (id,qname) VALUES (136,'{http://www.xplanung.de/xplangml/4/0}RP_SonstigeInfrastruktur');
INSERT INTO feature_types  (id,qname) VALUES (137,'{http://www.xplanung.de/xplangml/4/0}RP_SonstigeSiedlungsstruktur');
INSERT INTO feature_types  (id,qname) VALUES (138,'{http://www.xplanung.de/xplangml/4/0}RP_SonstigerFreiraumstruktur');
INSERT INTO feature_types  (id,qname) VALUES (139,'{http://www.xplanung.de/xplangml/4/0}RP_SozialeInfrastruktur');
INSERT INTO feature_types  (id,qname) VALUES (140,'{http://www.xplanung.de/xplangml/4/0}RP_Sperrgebiet');
INSERT INTO feature_types  (id,qname) VALUES (141,'{http://www.xplanung.de/xplangml/4/0}RP_Verkehr');
INSERT INTO feature_types  (id,qname) VALUES (142,'{http://www.xplanung.de/xplangml/4/0}RP_VorbHochwasserschutz');
INSERT INTO feature_types  (id,qname) VALUES (143,'{http://www.xplanung.de/xplangml/4/0}RP_Wasserschutz');
INSERT INTO feature_types  (id,qname) VALUES (144,'{http://www.xplanung.de/xplangml/4/0}RP_Wasserwirtschaft');
INSERT INTO feature_types  (id,qname) VALUES (145,'{http://www.xplanung.de/xplangml/4/0}RP_Windenergienutzung');
INSERT INTO feature_types  (id,qname) VALUES (146,'{http://www.xplanung.de/xplangml/4/0}RP_ZentralerOrt');
INSERT INTO feature_types  (id,qname) VALUES (147,'{http://www.xplanung.de/xplangml/4/0}SO_Bereich');
INSERT INTO feature_types  (id,qname) VALUES (148,'{http://www.xplanung.de/xplangml/4/0}SO_Bodenschutzrecht');
INSERT INTO feature_types  (id,qname) VALUES (149,'{http://www.xplanung.de/xplangml/4/0}SO_Denkmalschutzrecht');
INSERT INTO feature_types  (id,qname) VALUES (150,'{http://www.xplanung.de/xplangml/4/0}SO_Forstrecht');
INSERT INTO feature_types  (id,qname) VALUES (151,'{http://www.xplanung.de/xplangml/4/0}SO_Gebiet');
INSERT INTO feature_types  (id,qname) VALUES (152,'{http://www.xplanung.de/xplangml/4/0}SO_Grenze');
INSERT INTO feature_types  (id,qname) VALUES (153,'{http://www.xplanung.de/xplangml/4/0}SO_Linienobjekt');
INSERT INTO feature_types  (id,qname) VALUES (154,'{http://www.xplanung.de/xplangml/4/0}SO_Luftverkehrsrecht');
INSERT INTO feature_types  (id,qname) VALUES (155,'{http://www.xplanung.de/xplangml/4/0}SO_Objekt');
INSERT INTO feature_types  (id,qname) VALUES (156,'{http://www.xplanung.de/xplangml/4/0}SO_Plan');
INSERT INTO feature_types  (id,qname) VALUES (157,'{http://www.xplanung.de/xplangml/4/0}SO_RasterplanAenderung');
INSERT INTO feature_types  (id,qname) VALUES (158,'{http://www.xplanung.de/xplangml/4/0}SO_Schienenverkehrsrecht');
INSERT INTO feature_types  (id,qname) VALUES (159,'{http://www.xplanung.de/xplangml/4/0}SO_SchutzgebietNaturschutzrecht');
INSERT INTO feature_types  (id,qname) VALUES (160,'{http://www.xplanung.de/xplangml/4/0}SO_SchutzgebietSonstigesRecht');
INSERT INTO feature_types  (id,qname) VALUES (161,'{http://www.xplanung.de/xplangml/4/0}SO_SchutzgebietWasserrecht');
INSERT INTO feature_types  (id,qname) VALUES (162,'{http://www.xplanung.de/xplangml/4/0}SO_SonstigesRecht');
INSERT INTO feature_types  (id,qname) VALUES (163,'{http://www.xplanung.de/xplangml/4/0}SO_Strassenverkehrsrecht');
INSERT INTO feature_types  (id,qname) VALUES (164,'{http://www.xplanung.de/xplangml/4/0}SO_Wasserrecht');
INSERT INTO feature_types  (id,qname) VALUES (165,'{http://www.xplanung.de/xplangml/4/0}XP_BegruendungAbschnitt');
INSERT INTO feature_types  (id,qname) VALUES (166,'{http://www.xplanung.de/xplangml/4/0}XP_FPO');
INSERT INTO feature_types  (id,qname) VALUES (167,'{http://www.xplanung.de/xplangml/4/0}XP_LPO');
INSERT INTO feature_types  (id,qname) VALUES (168,'{http://www.xplanung.de/xplangml/4/0}XP_LTO');
INSERT INTO feature_types  (id,qname) VALUES (169,'{http://www.xplanung.de/xplangml/4/0}XP_PPO');
INSERT INTO feature_types  (id,qname) VALUES (170,'{http://www.xplanung.de/xplangml/4/0}XP_PTO');
INSERT INTO feature_types  (id,qname) VALUES (171,'{http://www.xplanung.de/xplangml/4/0}XP_Praesentationsobjekt');
INSERT INTO feature_types  (id,qname) VALUES (172,'{http://www.xplanung.de/xplangml/4/0}XP_RasterplanBasis');
INSERT INTO feature_types  (id,qname) VALUES (173,'{http://www.xplanung.de/xplangml/4/0}XP_TextAbschnitt');
CREATE TABLE gml_objects (id serial PRIMARY KEY, gml_id text UNIQUE NOT NULL, ft_type smallint REFERENCES feature_types , binary_object bytea, plan_id text, plan_name text, internal_id text, rechtsstand text);
COMMENT ON TABLE gml_objects IS 'All objects (features and geometries)';
SELECT ADDGEOMETRYCOLUMN('xplan40', 'gml_objects','gml_bounded_by','25832','GEOMETRY',2);
ALTER TABLE gml_objects ADD CONSTRAINT gml_objects_geochk CHECK (ST_isValid(gml_bounded_by));
CREATE INDEX gml_objects_sidx ON gml_objects  USING GIST (gml_bounded_by);
