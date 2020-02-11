--DROP VIEW INSPIREPLU_VIEWSERVICE;
CREATE OR REPLACE VIEW VIEWSERVICE_SPATIALPLAN AS 
SELECT 
  sp.attr_gml_id as spatialplan_gml_id, 
  sp.plu_officialtitle as officialtitle,
  sp.plu_extent_value as extent
FROM plu_spatialplan sp;

--DROP VIEW VIEWSERVICE_SUPPLEMENTARY_REGULATION;
CREATE OR REPLACE VIEW VIEWSERVICE_SUPPLEMENTARY_REGULATION AS
SELECT 
  sr.attr_gml_id as supplementaryregulation_gml_id, 
  sr.plu_geometry_value as extent,
  string_agg(DISTINCT srsr.href, '|') as supplementaryregulations
FROM plu_supplementaryregulation sr 
LEFT JOIN plu_supplementaryregulation_plu_supplementaryregulation srsr 
   ON sr.attr_gml_id = srsr.parentfk
GROUP BY supplementaryregulation_gml_id;

--DROP VIEW VIEWSERVICE_ZONING_ELEMENT;
CREATE OR REPLACE VIEW VIEWSERVICE_ZONING_ELEMENT AS
SELECT 
  ze.attr_gml_id as zoningelement_gml_id, 
  ze.plu_geometry_value as extent,
  string_agg(DISTINCT zeh.href, '|') as hilucs
FROM plu_zoningelement ze 
LEFT JOIN plu_zoningelement_plu_hilucslanduse zeh 
   ON ze.attr_gml_id = zeh.parentfk
GROUP BY zoningelement_gml_id;