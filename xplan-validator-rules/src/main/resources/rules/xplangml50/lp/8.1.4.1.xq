declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $h in //LP_AllgGruenflaeche/position satisfies
  exists($h/position/gml:Polygon) or
  exists($h/position/gml:MultiSurface) or
  exists($h/position/gml:LinearRing) or
  exists($h/position/gml:PolygonPatch) or
  exists($h/position/gml:Ring)
)
and
(
  every $h in //LP_TextlicheFestsetzungsFlaeche/position satisfies
  exists($h/position/gml:Polygon) or
  exists($h/position/gml:MultiSurface) or
  exists($h/position/gml:LinearRing) or
  exists($h/position/gml:PolygonPatch) or
  exists($h/position/gml:Ring)
)
and
(
  every $h in //LP_ZuBegruenendeGrundstueckflaeche/position satisfies
  exists($h/position/gml:Polygon) or
  exists($h/position/gml:MultiSurface) or
  exists($h/position/gml:LinearRing) or
  exists($h/position/gml:PolygonPatch) or
  exists($h/position/gml:Ring)
)