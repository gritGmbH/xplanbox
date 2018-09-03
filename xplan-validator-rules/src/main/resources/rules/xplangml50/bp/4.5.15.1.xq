declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

if (
  let $ids := (
    for $h in //BP_GemeinschaftsanlagenZuordnung/position/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  exists(//BP_Geometrieobjekt/gml:Polygon[@gml:id = $id]) or
  exists(//BP_Geometrieobjekt/gml:MultiSurface[@gml:id = $id]) or
  exists(//BP_Geometrieobjekt/gml:LinearRing[@gml:id = $id]) or
  exists(//BP_Geometrieobjekt/gml:PolygonPatch[@gml:id = $id]) or
  exists(//BP_Geometrieobjekt/gml:Ring[@gml:id = $id])
)
then (
  every $h in //BP_GemeinschaftsanlagenZuordnung satisfies
  exists(//$h/flaechenschluss[text() = 'false'])
)
else boolean ('false')