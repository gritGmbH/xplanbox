declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

every $h in //FP_Gemeinbedarf[position] satisfies
(
  exists($h/position/gml:Point) or
  exists($h/position/gml:MultiPoint) or

  exists($h/position/gml:Polygon) or
  exists($h/position/gml:MultiSurface) or
  exists($h/position/gml:LinearRing) or
  exists($h/position/gml:PolygonPatch) or
  exists($h/position/gml:Ring)
)
and

not(exists($h/position/gml:Curve)) and 
not(exists($h/position/gml:LineString)) and 
not(exists($h/position/gml:MultiCurve)) and 
not(exists($h/position/gml:LineStringSegment)) and 
not(exists($h/position/gml:Arc)) and 
not(exists($h/position/gml:Circle))