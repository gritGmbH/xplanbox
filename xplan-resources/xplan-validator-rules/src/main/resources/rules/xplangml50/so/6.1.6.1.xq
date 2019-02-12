declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Grenze/position satisfies
  exists($h/gml:Curve) or
  exists($h/gml:LineString) or
  exists($h/gml:MultiCurve) or
  exists($h/gml:LineStringSegment) or
  exists($h/gml:Arc) or
  exists($h/gml:Circle)