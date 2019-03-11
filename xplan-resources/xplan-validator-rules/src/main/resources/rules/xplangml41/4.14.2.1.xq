declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace gml = 'http://www.opengis.net/gml/3.2';

(
  every $h in //BP_UnverbindlicheVormerkung satisfies
  if (
    exists(//$h/position/gml:Polygon) or
      exists(//$h/position/gml:MultiSurface) or
      exists(//$h/position/gml:LinearRing) or
      exists(//$h/position/gml:PolygonPatch) or
      exists(//$h/position/gml:Ring)
  )
  then (
    exists(//$h/flaechenschluss[text() = 'false'])
  )
  else true()
)
and
(
  every $h in //BP_Wegerecht satisfies
  if (
    exists(//$h/position/gml:Polygon) or
      exists(//$h/position/gml:MultiSurface) or
      exists(//$h/position/gml:LinearRing) or
      exists(//$h/position/gml:PolygonPatch) or
      exists(//$h/position/gml:Ring)
  )
  then (
    exists(//$h/flaechenschluss[text() = 'false'])
  )
  else true()
)