---
-- #%L
-- xplan-sql-scripts - SQL Skripte zum Aufsetzen der Datenhaltung.
-- %%
-- Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
CREATE SCHEMA xplan53;
SET search_path TO xplan53,public;

/* --- END schema setup --- */
CREATE TABLE FEATURE_TYPES (id smallint PRIMARY KEY, qname text NOT NULL);
COMMENT ON TABLE FEATURE_TYPES IS 'Ids and bboxes of concrete feature types';
SELECT ADDGEOMETRYCOLUMN('xplan53', 'feature_types','bbox','0','GEOMETRY',2);
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (0,'{http://www.opengis.net/gml/3.2}AbstractDiscreteCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (1,'{http://www.opengis.net/gml/3.2}MultiPointCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (2,'{http://www.opengis.net/gml/3.2}RectifiedGridCoverage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (3,'{http://www.xplanung.de/xplangml/5/3}BP_AbgrabungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (4,'{http://www.xplanung.de/xplangml/5/3}BP_AbstandsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (5,'{http://www.xplanung.de/xplangml/5/3}BP_AbstandsMass');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (6,'{http://www.xplanung.de/xplangml/5/3}BP_AbweichungVonBaugrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (7,'{http://www.xplanung.de/xplangml/5/3}BP_AbweichungVonUeberbaubererGrundstuecksFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (8,'{http://www.xplanung.de/xplangml/5/3}BP_AnpflanzungBindungErhaltung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (9,'{http://www.xplanung.de/xplangml/5/3}BP_AufschuettungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (10,'{http://www.xplanung.de/xplangml/5/3}BP_AusgleichsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (11,'{http://www.xplanung.de/xplangml/5/3}BP_AusgleichsMassnahme');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (12,'{http://www.xplanung.de/xplangml/5/3}BP_BauGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (13,'{http://www.xplanung.de/xplangml/5/3}BP_BauLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (14,'{http://www.xplanung.de/xplangml/5/3}BP_BaugebietsTeilFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (15,'{http://www.xplanung.de/xplangml/5/3}BP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (16,'{http://www.xplanung.de/xplangml/5/3}BP_BereichOhneEinAusfahrtLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (17,'{http://www.xplanung.de/xplangml/5/3}BP_BesondererNutzungszweckFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (18,'{http://www.xplanung.de/xplangml/5/3}BP_BodenschaetzeFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (19,'{http://www.xplanung.de/xplangml/5/3}BP_EinfahrtPunkt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (20,'{http://www.xplanung.de/xplangml/5/3}BP_EinfahrtsbereichLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (21,'{http://www.xplanung.de/xplangml/5/3}BP_EingriffsBereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (22,'{http://www.xplanung.de/xplangml/5/3}BP_ErhaltungsBereichFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (23,'{http://www.xplanung.de/xplangml/5/3}BP_FestsetzungNachLandesrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (24,'{http://www.xplanung.de/xplangml/5/3}BP_FirstRichtungsLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (25,'{http://www.xplanung.de/xplangml/5/3}BP_FlaecheOhneFestsetzung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (26,'{http://www.xplanung.de/xplangml/5/3}BP_FoerderungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (27,'{http://www.xplanung.de/xplangml/5/3}BP_FreiFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (28,'{http://www.xplanung.de/xplangml/5/3}BP_GebaeudeFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (29,'{http://www.xplanung.de/xplangml/5/3}BP_GemeinbedarfsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (30,'{http://www.xplanung.de/xplangml/5/3}BP_GemeinschaftsanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (31,'{http://www.xplanung.de/xplangml/5/3}BP_GemeinschaftsanlagenZuordnung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (32,'{http://www.xplanung.de/xplangml/5/3}BP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (33,'{http://www.xplanung.de/xplangml/5/3}BP_GewaesserFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (34,'{http://www.xplanung.de/xplangml/5/3}BP_GruenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (35,'{http://www.xplanung.de/xplangml/5/3}BP_HoehenMass');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (36,'{http://www.xplanung.de/xplangml/5/3}BP_Immissionsschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (37,'{http://www.xplanung.de/xplangml/5/3}BP_KennzeichnungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (38,'{http://www.xplanung.de/xplangml/5/3}BP_KleintierhaltungFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (39,'{http://www.xplanung.de/xplangml/5/3}BP_Landwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (40,'{http://www.xplanung.de/xplangml/5/3}BP_LandwirtschaftsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (41,'{http://www.xplanung.de/xplangml/5/3}BP_NebenanlagenAusschlussFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (42,'{http://www.xplanung.de/xplangml/5/3}BP_NebenanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (43,'{http://www.xplanung.de/xplangml/5/3}BP_NichtUeberbaubareGrundstuecksflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (44,'{http://www.xplanung.de/xplangml/5/3}BP_NutzungsartenGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (45,'{http://www.xplanung.de/xplangml/5/3}BP_PersGruppenBestimmteFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (46,'{http://www.xplanung.de/xplangml/5/3}BP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (47,'{http://www.xplanung.de/xplangml/5/3}BP_RegelungVergnuegungsstaetten');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (48,'{http://www.xplanung.de/xplangml/5/3}BP_RekultivierungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (49,'{http://www.xplanung.de/xplangml/5/3}BP_RichtungssektorGrenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (50,'{http://www.xplanung.de/xplangml/5/3}BP_SchutzPflegeEntwicklungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (51,'{http://www.xplanung.de/xplangml/5/3}BP_SchutzPflegeEntwicklungsMassnahme');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (52,'{http://www.xplanung.de/xplangml/5/3}BP_Sichtflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (53,'{http://www.xplanung.de/xplangml/5/3}BP_SpezielleBauweise');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (54,'{http://www.xplanung.de/xplangml/5/3}BP_SpielSportanlagenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (55,'{http://www.xplanung.de/xplangml/5/3}BP_StrassenVerkehrsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (56,'{http://www.xplanung.de/xplangml/5/3}BP_StrassenbegrenzungsLinie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (57,'{http://www.xplanung.de/xplangml/5/3}BP_Strassenkoerper');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (58,'{http://www.xplanung.de/xplangml/5/3}BP_TechnischeMassnahmenFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (59,'{http://www.xplanung.de/xplangml/5/3}BP_TextAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (60,'{http://www.xplanung.de/xplangml/5/3}BP_TextlicheFestsetzungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (61,'{http://www.xplanung.de/xplangml/5/3}BP_UeberbaubareGrundstuecksFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (62,'{http://www.xplanung.de/xplangml/5/3}BP_UnverbindlicheVormerkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (63,'{http://www.xplanung.de/xplangml/5/3}BP_VerEntsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (64,'{http://www.xplanung.de/xplangml/5/3}BP_Veraenderungssperre');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (65,'{http://www.xplanung.de/xplangml/5/3}BP_VerkehrsflaecheBesondererZweckbestimmung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (66,'{http://www.xplanung.de/xplangml/5/3}BP_WaldFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (67,'{http://www.xplanung.de/xplangml/5/3}BP_WasserwirtschaftsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (68,'{http://www.xplanung.de/xplangml/5/3}BP_Wegerecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (69,'{http://www.xplanung.de/xplangml/5/3}BP_ZentralerVersorgungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (70,'{http://www.xplanung.de/xplangml/5/3}BP_ZusatzkontingentLaerm');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (71,'{http://www.xplanung.de/xplangml/5/3}BP_ZusatzkontingentLaermFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (72,'{http://www.xplanung.de/xplangml/5/3}FP_Abgrabung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (73,'{http://www.xplanung.de/xplangml/5/3}FP_AnpassungKlimawandel');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (74,'{http://www.xplanung.de/xplangml/5/3}FP_Aufschuettung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (75,'{http://www.xplanung.de/xplangml/5/3}FP_AusgleichsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (76,'{http://www.xplanung.de/xplangml/5/3}FP_BebauungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (77,'{http://www.xplanung.de/xplangml/5/3}FP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (78,'{http://www.xplanung.de/xplangml/5/3}FP_Bodenschaetze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (79,'{http://www.xplanung.de/xplangml/5/3}FP_DarstellungNachLandesrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (80,'{http://www.xplanung.de/xplangml/5/3}FP_FlaecheOhneDarstellung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (81,'{http://www.xplanung.de/xplangml/5/3}FP_Gemeinbedarf');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (82,'{http://www.xplanung.de/xplangml/5/3}FP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (83,'{http://www.xplanung.de/xplangml/5/3}FP_Gewaesser');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (84,'{http://www.xplanung.de/xplangml/5/3}FP_Gruen');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (85,'{http://www.xplanung.de/xplangml/5/3}FP_KeineZentrAbwasserBeseitigungFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (86,'{http://www.xplanung.de/xplangml/5/3}FP_Kennzeichnung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (87,'{http://www.xplanung.de/xplangml/5/3}FP_Landwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (88,'{http://www.xplanung.de/xplangml/5/3}FP_LandwirtschaftsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (89,'{http://www.xplanung.de/xplangml/5/3}FP_NutzungsbeschraenkungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (90,'{http://www.xplanung.de/xplangml/5/3}FP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (91,'{http://www.xplanung.de/xplangml/5/3}FP_PrivilegiertesVorhaben');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (92,'{http://www.xplanung.de/xplangml/5/3}FP_SchutzPflegeEntwicklung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (93,'{http://www.xplanung.de/xplangml/5/3}FP_SpielSportanlage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (94,'{http://www.xplanung.de/xplangml/5/3}FP_Strassenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (95,'{http://www.xplanung.de/xplangml/5/3}FP_TextAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (96,'{http://www.xplanung.de/xplangml/5/3}FP_TextlicheDarstellungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (97,'{http://www.xplanung.de/xplangml/5/3}FP_UnverbindlicheVormerkung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (98,'{http://www.xplanung.de/xplangml/5/3}FP_VerEntsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (99,'{http://www.xplanung.de/xplangml/5/3}FP_VorbehalteFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (100,'{http://www.xplanung.de/xplangml/5/3}FP_WaldFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (101,'{http://www.xplanung.de/xplangml/5/3}FP_Wasserwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (102,'{http://www.xplanung.de/xplangml/5/3}FP_ZentralerVersorgungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (103,'{http://www.xplanung.de/xplangml/5/3}LP_Abgrenzung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (104,'{http://www.xplanung.de/xplangml/5/3}LP_AllgGruenflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (105,'{http://www.xplanung.de/xplangml/5/3}LP_AnpflanzungBindungErhaltung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (106,'{http://www.xplanung.de/xplangml/5/3}LP_Ausgleich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (107,'{http://www.xplanung.de/xplangml/5/3}LP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (108,'{http://www.xplanung.de/xplangml/5/3}LP_Biotopverbundflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (109,'{http://www.xplanung.de/xplangml/5/3}LP_Bodenschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (110,'{http://www.xplanung.de/xplangml/5/3}LP_ErholungFreizeit');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (111,'{http://www.xplanung.de/xplangml/5/3}LP_Forstrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (112,'{http://www.xplanung.de/xplangml/5/3}LP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (113,'{http://www.xplanung.de/xplangml/5/3}LP_Landschaftsbild');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (114,'{http://www.xplanung.de/xplangml/5/3}LP_NutzungsAusschluss');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (115,'{http://www.xplanung.de/xplangml/5/3}LP_NutzungserfordernisRegelung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (116,'{http://www.xplanung.de/xplangml/5/3}LP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (117,'{http://www.xplanung.de/xplangml/5/3}LP_PlanerischeVertiefung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (118,'{http://www.xplanung.de/xplangml/5/3}LP_SchutzPflegeEntwicklung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (119,'{http://www.xplanung.de/xplangml/5/3}LP_SchutzobjektInternatRecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (120,'{http://www.xplanung.de/xplangml/5/3}LP_SonstigesRecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (121,'{http://www.xplanung.de/xplangml/5/3}LP_TextAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (122,'{http://www.xplanung.de/xplangml/5/3}LP_TextlicheFestsetzungsFlaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (123,'{http://www.xplanung.de/xplangml/5/3}LP_WasserrechtGemeingebrEinschraenkungNaturschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (124,'{http://www.xplanung.de/xplangml/5/3}LP_WasserrechtSchutzgebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (125,'{http://www.xplanung.de/xplangml/5/3}LP_WasserrechtSonstige');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (126,'{http://www.xplanung.de/xplangml/5/3}LP_WasserrechtWirtschaftAbflussHochwSchutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (127,'{http://www.xplanung.de/xplangml/5/3}LP_ZuBegruenendeGrundstueckflaeche');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (128,'{http://www.xplanung.de/xplangml/5/3}LP_Zwischennutzung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (129,'{http://www.xplanung.de/xplangml/5/3}RP_Achse');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (130,'{http://www.xplanung.de/xplangml/5/3}RP_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (131,'{http://www.xplanung.de/xplangml/5/3}RP_Bodenschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (132,'{http://www.xplanung.de/xplangml/5/3}RP_Einzelhandel');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (133,'{http://www.xplanung.de/xplangml/5/3}RP_Energieversorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (134,'{http://www.xplanung.de/xplangml/5/3}RP_Entsorgung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (135,'{http://www.xplanung.de/xplangml/5/3}RP_Erholung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (136,'{http://www.xplanung.de/xplangml/5/3}RP_ErneuerbareEnergie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (137,'{http://www.xplanung.de/xplangml/5/3}RP_Forstwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (138,'{http://www.xplanung.de/xplangml/5/3}RP_Freiraum');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (139,'{http://www.xplanung.de/xplangml/5/3}RP_Funktionszuweisung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (140,'{http://www.xplanung.de/xplangml/5/3}RP_GenerischesObjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (141,'{http://www.xplanung.de/xplangml/5/3}RP_Gewaesser');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (142,'{http://www.xplanung.de/xplangml/5/3}RP_Grenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (143,'{http://www.xplanung.de/xplangml/5/3}RP_GruenzugGruenzaesur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (144,'{http://www.xplanung.de/xplangml/5/3}RP_Hochwasserschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (145,'{http://www.xplanung.de/xplangml/5/3}RP_IndustrieGewerbe');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (146,'{http://www.xplanung.de/xplangml/5/3}RP_Klimaschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (147,'{http://www.xplanung.de/xplangml/5/3}RP_Kommunikation');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (148,'{http://www.xplanung.de/xplangml/5/3}RP_Kulturlandschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (149,'{http://www.xplanung.de/xplangml/5/3}RP_LaermschutzBauschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (150,'{http://www.xplanung.de/xplangml/5/3}RP_Landwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (151,'{http://www.xplanung.de/xplangml/5/3}RP_Legendenobjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (152,'{http://www.xplanung.de/xplangml/5/3}RP_Luftverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (153,'{http://www.xplanung.de/xplangml/5/3}RP_NaturLandschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (154,'{http://www.xplanung.de/xplangml/5/3}RP_NaturschutzrechtlichesSchutzgebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (155,'{http://www.xplanung.de/xplangml/5/3}RP_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (156,'{http://www.xplanung.de/xplangml/5/3}RP_Planungsraum');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (157,'{http://www.xplanung.de/xplangml/5/3}RP_RadwegWanderweg');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (158,'{http://www.xplanung.de/xplangml/5/3}RP_Raumkategorie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (159,'{http://www.xplanung.de/xplangml/5/3}RP_Rohstoff');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (160,'{http://www.xplanung.de/xplangml/5/3}RP_Schienenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (161,'{http://www.xplanung.de/xplangml/5/3}RP_Siedlung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (162,'{http://www.xplanung.de/xplangml/5/3}RP_SonstVerkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (163,'{http://www.xplanung.de/xplangml/5/3}RP_SonstigeInfrastruktur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (164,'{http://www.xplanung.de/xplangml/5/3}RP_SonstigerFreiraumschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (165,'{http://www.xplanung.de/xplangml/5/3}RP_SonstigerSiedlungsbereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (166,'{http://www.xplanung.de/xplangml/5/3}RP_SozialeInfrastruktur');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (167,'{http://www.xplanung.de/xplangml/5/3}RP_Sperrgebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (168,'{http://www.xplanung.de/xplangml/5/3}RP_Sportanlage');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (169,'{http://www.xplanung.de/xplangml/5/3}RP_Strassenverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (170,'{http://www.xplanung.de/xplangml/5/3}RP_TextAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (171,'{http://www.xplanung.de/xplangml/5/3}RP_Verkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (172,'{http://www.xplanung.de/xplangml/5/3}RP_Wasserschutz');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (173,'{http://www.xplanung.de/xplangml/5/3}RP_Wasserverkehr');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (174,'{http://www.xplanung.de/xplangml/5/3}RP_Wasserwirtschaft');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (175,'{http://www.xplanung.de/xplangml/5/3}RP_WohnenSiedlung');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (176,'{http://www.xplanung.de/xplangml/5/3}RP_ZentralerOrt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (177,'{http://www.xplanung.de/xplangml/5/3}SO_Bauverbotszone');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (178,'{http://www.xplanung.de/xplangml/5/3}SO_Bereich');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (179,'{http://www.xplanung.de/xplangml/5/3}SO_Bodenschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (180,'{http://www.xplanung.de/xplangml/5/3}SO_Denkmalschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (181,'{http://www.xplanung.de/xplangml/5/3}SO_Forstrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (182,'{http://www.xplanung.de/xplangml/5/3}SO_Gebiet');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (183,'{http://www.xplanung.de/xplangml/5/3}SO_Gelaendemorphologie');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (184,'{http://www.xplanung.de/xplangml/5/3}SO_Gewaesser');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (185,'{http://www.xplanung.de/xplangml/5/3}SO_Grenze');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (186,'{http://www.xplanung.de/xplangml/5/3}SO_Linienobjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (187,'{http://www.xplanung.de/xplangml/5/3}SO_Luftverkehrsrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (188,'{http://www.xplanung.de/xplangml/5/3}SO_Objekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (189,'{http://www.xplanung.de/xplangml/5/3}SO_Plan');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (190,'{http://www.xplanung.de/xplangml/5/3}SO_Schienenverkehrsrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (191,'{http://www.xplanung.de/xplangml/5/3}SO_SchutzgebietNaturschutzrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (192,'{http://www.xplanung.de/xplangml/5/3}SO_SchutzgebietSonstigesRecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (193,'{http://www.xplanung.de/xplangml/5/3}SO_SchutzgebietWasserrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (194,'{http://www.xplanung.de/xplangml/5/3}SO_SonstigesRecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (195,'{http://www.xplanung.de/xplangml/5/3}SO_Strassenverkehrsrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (196,'{http://www.xplanung.de/xplangml/5/3}SO_TextAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (197,'{http://www.xplanung.de/xplangml/5/3}SO_Wasserrecht');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (198,'{http://www.xplanung.de/xplangml/5/3}XP_BegruendungAbschnitt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (199,'{http://www.xplanung.de/xplangml/5/3}XP_FPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (200,'{http://www.xplanung.de/xplangml/5/3}XP_LPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (201,'{http://www.xplanung.de/xplangml/5/3}XP_LTO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (202,'{http://www.xplanung.de/xplangml/5/3}XP_Nutzungsschablone');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (203,'{http://www.xplanung.de/xplangml/5/3}XP_PPO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (204,'{http://www.xplanung.de/xplangml/5/3}XP_PTO');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (205,'{http://www.xplanung.de/xplangml/5/3}XP_Praesentationsobjekt');
INSERT INTO FEATURE_TYPES  (id,qname) VALUES (206,'{http://www.xplanung.de/xplangml/5/3}XP_Rasterdarstellung');
CREATE TABLE GML_OBJECTS (id serial PRIMARY KEY, gml_id text UNIQUE NOT NULL, ft_type smallint REFERENCES FEATURE_TYPES , binary_object bytea, plan_id text, plan_name text, internal_id text, rechtsstand text);
COMMENT ON TABLE GML_OBJECTS IS 'All objects (features and geometries)';
SELECT ADDGEOMETRYCOLUMN('xplan53', 'gml_objects','gml_bounded_by','0','GEOMETRY',2);
ALTER TABLE GML_OBJECTS ADD CONSTRAINT gml_objects_geochk CHECK (ST_IsValid(gml_bounded_by));
CREATE INDEX gml_objects_sidx ON GML_OBJECTS  USING GIST (gml_bounded_by);
