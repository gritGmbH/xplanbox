declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //XP_FPO[position] satisfies
(
  exists($h/position/gml:Polygon) and
  exists($h/position/gml:Polygon/descendant-or-self::gml:posList) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:arcString)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:segment)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:segments)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:coordinates)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:pos)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:patches))
)
or
(
  exists($h/position/gml:MultiSurface) and
  exists($h/position/gml:MultiSurface/descendant-or-self::gml:posList) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:arcString)) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:segment)) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:segments)) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:coordinates)) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:pos)) and
  not(exists($h/position/gml:MultiSurface/descendant-or-self::gml:patches))
)
or
(
  exists($h/position/gml:LinearRing) and
  exists($h/position/gml:LinearRing/descendant-or-self::gml:posList) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:arcString)) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:segment)) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:segments)) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:coordinates)) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:pos)) and
  not(exists($h/position/gml:LinearRing/descendant-or-self::gml:patches))
)
or
(
  exists($h/position/gml:PolygonPatch) and
  exists($h/position/gml:PolygonPatch/descendant-or-self::gml:patches) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:arcString)) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:segment)) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:segments)) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:coordinates)) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:pos)) and
  not(exists($h/position/gml:PolygonPatch/descendant-or-self::gml:posList))
)
or
(
  exists($h/position/gml:Ring) and
  exists($h/position/gml:Ring/descendant-or-self::gml:posList) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:arcString)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:segment)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:segments)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:coordinates)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:pos)) and
  not(exists($h/position/gml:Polygon/descendant-or-self::gml:patches))
)