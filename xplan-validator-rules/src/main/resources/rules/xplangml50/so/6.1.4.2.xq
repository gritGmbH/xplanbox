declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Gebiet/position satisfies
  exists($h/gml:Polygon) or
  exists($h/gml:MultiSurface) or
  exists($h/gml:LinearRing) or
  exists($h/gml:PolygonPatch) or
  exists($h/gml:Ring)