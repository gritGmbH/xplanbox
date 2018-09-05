declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //BP_EinfahrtPunkt[position] satisfies
  exists($h/position/gml:Point) or
  exists($h/position/gml:MultiPoint)