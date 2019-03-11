declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

if (
  let $ids := (
    for $h in //FP_UnverbindlicheVormerkung/position/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  exists(//FP_Geometrieobjekt/gml:Polygon[@gml:id = $id]) or
  exists(//FP_Geometrieobjekt/gml:MultiSurface[@gml:id = $id]) or
  exists(//FP_Geometrieobjekt/gml:LinearRing[@gml:id = $id]) or
  exists(//FP_Geometrieobjekt/gml:PolygonPatch[@gml:id = $id]) or
  exists(//FP_Geometrieobjekt/gml:Ring[@gml:id = $id])
)
then (
  every $h in //FP_UnverbindlicheVormerkung satisfies
  exists(//$h/flaechenschluss[text() = 'false'])
)
else boolean ('false')