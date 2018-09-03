declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

if (
  let $ids := (
    for $h in //BP_Landwirtschaft/position/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  (
    exists(//BP_Geometrieobjekt/gml:Polygon[@gml:id = $id]) and
    exists(//BP_Landwirtschaft/ebene[text() = '0'])
  ) or
  (
    exists(//BP_Geometrieobjekt/gml:MultiSurface[@gml:id = $id]) and
    exists(//BP_Landwirtschaft/ebene[text() = '0'])
  ) or
  (
    exists(//BP_Geometrieobjekt/gml:LinearRing[@gml:id = $id]) and
    exists(//BP_Landwirtschaft/ebene[text() = '0'])
  ) or
  (
    exists(//BP_Geometrieobjekt/gml:PolygonPatch[@gml:id = $id]) and
    exists(//BP_Landwirtschaft/ebene[text() = '0'])
  ) or
  (
    exists(//BP_Geometrieobjekt/gml:Ring[@gml:id = $id]) and
    exists(//BP_Landwirtschaft/ebene[text() = '0'])
  )  
)
then (
  every $h in //BP_Landwirtschaft satisfies
  exists(//$h/flaechenschluss[text() = 'true'])
)
else boolean ('false')