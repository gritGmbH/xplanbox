declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //BP_AbstandsMass/position satisfies
(
  not(exists($h/gml:Polygon)) and
  not(exists($h/gml:MultiSurface)) and
  not(exists($h/gml:LinearRing)) and
  not(exists($h/gml:PolygonPatch)) and
  not(exists($h/gml:Ring))
)