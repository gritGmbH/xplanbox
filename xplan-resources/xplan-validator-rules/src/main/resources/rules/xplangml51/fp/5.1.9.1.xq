declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //FP_Punktobjekt[position] satisfies
  exists($h/position/gml:Point) or
  exists($h/position/gml:MultiPoint)