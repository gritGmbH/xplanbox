UPDATE xplan3.gml_objects
SET plan_id=subquery.plan_id,
    plan_name=subquery.plan_name,
    internal_id=subquery.internal_id,
    rechtsstand=subquery.rechtsstand
FROM (
SELECT id, 
  (XPATH('/xplan:BP_Plan/xplan:nummer/text() | 
          /xplan:BP_Bereich/xplan:nummer/text()', 
    XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS plan_id,
  (XPATH('/xplan:BP_Plan/xplan:name/text() | 
          /xplan:BP_Bereich/xplan:name/text()', 
    XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS plan_name,
  (XPATH('/xplan:BP_Plan/xplan:internalId/text()',
    XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS internal_id,
  (XPATH('//xplan:rechtsstand/text()',
    XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS rechtsstand
  FROM xplan3.gml_objects ) AS subquery
  WHERE xplan3.gml_objects.id=subquery.id;
