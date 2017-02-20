declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

every $h in //SO_Flaechenobjekt[position] satisfies
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