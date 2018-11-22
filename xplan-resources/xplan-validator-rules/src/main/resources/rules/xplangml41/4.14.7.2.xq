declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

let $ids := (
  for $h in //BP_HoehenMass/position/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
(
  not(exists(//BP_Geometrieobjekt/gml:Polygon[@gml:id = $id])) and
  not(exists(//BP_Geometrieobjekt/gml:MultiSurface[@gml:id = $id])) and
  not(exists(//BP_Geometrieobjekt/gml:LinearRing[@gml:id = $id])) and
  not(exists(//BP_Geometrieobjekt/gml:PolygonPatch[@gml:id = $id])) and
  not(exists(//BP_Geometrieobjekt/gml:Ring[@gml:id = $id]))
)