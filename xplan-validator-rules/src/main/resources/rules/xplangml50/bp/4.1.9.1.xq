declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //BP_Punktobjekt[position] satisfies
(
  exists($h/position/gml:Point) and 
  exists($h/position/gml:Point/descendant-or-self::gml:pos) and
  not(exists($h/position/gml:Point/descendant-or-self::gml:coordinates))
)
or
(
  exists($h/position/gml:MultiPoint) and
  exists($h/position/gml:MultiPoint/descendant-or-self::gml:pos) and
  not(exists($h/position/gml:Point/descendant-or-self::gml:coordinates))
)