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
--DROP VIEW VIEWSERVICE_SPATIALPLAN;

SET search_path TO inspireplu,public;

CREATE OR REPLACE VIEW VIEWSERVICE_SPATIALPLAN AS
SELECT
  sp.attr_gml_id as spatialplan_gml_id,
  sp.plu_extent_value as extent,
  concat('{',
		 sp.plu_inspireid_base_identifier_base_namespace,
		 '}',
		 sp.plu_inspireid_base_identifier_base_localid) as inspireid,
  sp.plu_levelofspatialplan_href as levelofspatialplan,
  sp.plu_officialtitle as officialtitle,
  sp.plu_plantypename_href as plantypename,
  sp.plu_alternativetitle as alternativetitle,
  array_to_string(
	   array_agg(plu_ordinancevalue_plu_ordinancereference || ' (' || to_char(spo.plu_ordinancevalue_plu_ordinancedate, 'YYYY-MM-DD HH24:MI:SS') || ')'),
				 ', ', '') as ordinances,
  sp.plu_processstepgeneral_href as processstepgeneral,
  sp.plu_validfrom as validfrom,
  sp.plu_validto as validto,
  sp.plu_beginlifespanversion as beginlifespanversion,
  sp.plu_endlifespanversion as endlifespanversion
FROM plu_spatialplan sp
LEFT JOIN plu_spatialplan_plu_ordinance spo
   ON sp.attr_gml_id = spo.parentfk
GROUP BY spatialplan_gml_id;

--DROP VIEW VIEWSERVICE_SUPPLEMENTARY_REGULATION;
CREATE OR REPLACE VIEW VIEWSERVICE_SUPPLEMENTARY_REGULATION AS
SELECT
  sr.attr_gml_id as supplementaryregulation_gml_id,
  sr.plu_geometry_value as geometry,
  concat('{',
		 sr.plu_inspireid_base_identifier_base_namespace,
		 '}',
		 sr.plu_inspireid_base_identifier_base_localid) as inspireid,
  sr.plu_regulationnature_href as regulationnature,
  string_agg(DISTINCT srsr.href, '|') as supplementaryregulations,
  array_to_string( array_agg( DISTINCT CASE
									 WHEN srdi.plu_dimensioningindicationvalue_plu_indicationreference is not null
									 	THEN srdi.plu_dimensioningindicationvalue_plu_indicationreference
									 WHEN srdi.plu_dimensioningindicationcharactervalue_plu_indicationrefe_117 is not null
									 	THEN srdi.plu_dimensioningindicationcharactervalue_plu_indicationrefe_117 || ': ' || srdi.plu_dimensioningindicationcharactervalue_plu_value
									 WHEN srdi.plu_dimensioningindicationmeasurevalue_plu_indicationreference is not null
									 	THEN srdi.plu_dimensioningindicationmeasurevalue_plu_indicationreference || ': ' || srdi.plu_dimensioningindicationmeasurevalue_plu_value || ' [' || srdi.plu_dimensioningindicationmeasurevalue_plu_value_attr_uom || ']'
									 WHEN srdi.plu_dimensioningindicationintegervalue_plu_indicationreference is not null
									 	THEN srdi.plu_dimensioningindicationintegervalue_plu_indicationreference || ': ' || srdi.plu_dimensioningindicationintegervalue_plu_value
									 WHEN srdi.plu_dimensioningindicationrealvalue_plu_indicationreference is not null
									 	THEN srdi.plu_dimensioningindicationrealvalue_plu_indicationreference || ': ' || srdi.plu_dimensioningindicationrealvalue_plu_value
            						 ELSE NULL
                               END), ', ', '') as dimensioningindications,
  sr.plu_inheritedfromotherplans as inheritedfromotherplans,
  string_agg(DISTINCT srn.value, '|') as names,
  sr.plu_processstepgeneral_href as processstepgeneral,
  sr.plu_specificregulationnature as specificregulationnature,
  string_agg(DISTINCT srssr.href, '|') as specificsupplementaryregulations,
  sr.plu_validfrom as validfrom,
  sr.plu_validto as validto,
  sr.plu_beginlifespanversion as beginlifespanversion,
  sr.plu_endlifespanversion as endlifespanversion
FROM plu_supplementaryregulation sr
LEFT JOIN plu_supplementaryregulation_plu_supplementaryregulation srsr
   ON sr.attr_gml_id = srsr.parentfk
LEFT JOIN plu_supplementaryregulation_plu_specificsupplementaryregula_113 srssr
   ON sr.attr_gml_id = srssr.parentfk
LEFT JOIN plu_supplementaryregulation_plu_name srn
   ON sr.attr_gml_id = srn.parentfk
LEFT JOIN plu_supplementaryregulation_plu_dimensioningindication srdi
   ON sr.attr_gml_id = srdi.parentfk
GROUP BY supplementaryregulation_gml_id;

--DROP VIEW VIEWSERVICE_ZONING_ELEMENT;
CREATE OR REPLACE VIEW VIEWSERVICE_ZONING_ELEMENT AS
SELECT 
  ze.attr_gml_id as zoningelement_gml_id, 
  ze.plu_geometry_value as geometry,
  string_agg(DISTINCT zeh.href, '|') as hilucs,
  concat('{',
		 ze.plu_inspireid_base_identifier_base_namespace,
		 '}',
		 ze.plu_inspireid_base_identifier_base_localid) as inspireid,
  ze.plu_regulationnature_href as regulationnature,
  array_to_string( array_agg( DISTINCT CASE
									 WHEN zedi.plu_dimensioningindicationvalue_plu_indicationreference is not null
									 	THEN zedi.plu_dimensioningindicationvalue_plu_indicationreference
									 WHEN zedi.plu_dimensioningindicationcharactervalue_plu_indicationr_184027 is not null
									 	THEN zedi.plu_dimensioningindicationcharactervalue_plu_indicationr_184027 || ': ' || zedi.plu_dimensioningindicationcharactervalue_plu_value
									 WHEN zedi.plu_dimensioningindicationmeasurevalue_plu_indicationreference is not null
									 	THEN zedi.plu_dimensioningindicationmeasurevalue_plu_indicationreference || ': ' || zedi.plu_dimensioningindicationmeasurevalue_plu_value || ' [' || zedi.plu_dimensioningindicationmeasurevalue_plu_value_attr_uom || ']'
									 WHEN zedi.plu_dimensioningindicationintegervalue_plu_indicationreference is not null
									 	THEN zedi.plu_dimensioningindicationintegervalue_plu_indicationreference || ': ' || zedi.plu_dimensioningindicationintegervalue_plu_value
									 WHEN zedi.plu_dimensioningindicationrealvalue_plu_indicationreference is not null
									 	THEN zedi.plu_dimensioningindicationrealvalue_plu_indicationreference || ': ' || zedi.plu_dimensioningindicationrealvalue_plu_value
            						 ELSE NULL
                               END), ', ', '') as dimensioningindications,
  ze.plu_processstepgeneral_href as processstepgeneral,
  -- nil in XPlanGML 5.0 -> INSPIRE PLU 4.0: hilucsPresence -->
  string_agg(DISTINCT zeslu.href, '|') as specificlanduse,
  -- nil in XPlanGML 5.0 -> INSPIRE PLU 4.0: specificPresence -->
  ze.plu_validfrom as validfrom,
  ze.plu_validto as validto,
  ze.plu_beginlifespanversion as beginlifespanversion,
  ze.plu_endlifespanversion as endlifespanversion
FROM plu_zoningelement ze
LEFT JOIN plu_zoningelement_plu_hilucslanduse zeh
   ON ze.attr_gml_id = zeh.parentfk
LEFT JOIN plu_zoningelement_plu_specificlanduse zeslu
   ON ze.attr_gml_id = zeslu.parentfk
LEFT JOIN plu_zoningelement_plu_dimensioningindication zedi
   ON ze.attr_gml_id = zedi.parentfk
GROUP BY zoningelement_gml_id;
