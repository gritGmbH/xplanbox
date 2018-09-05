declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Punktobjekt/position satisfies
  exists($h/gml:Point) or
  exists($h/gml:MultiPoint)