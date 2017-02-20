declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //XP_LPO[position] satisfies
  (
    exists($h/position/gml:Curve) and 
    exists($h/position/gml:Curve/descendant-or-self::gml:segments) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:posList)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:LineString) and 
    exists($h/position/gml:LineString/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:MultiCurve) and 
    exists($h/position/gml:MultiCurve/descendant-or-self::gml:segments) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:posList)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:pos))
  ) 
  or
  (
    exists($h/position/gml:LineStringSegment) and 
    exists($h/position/gml:LineStringSegment/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:pos))
  ) 
  or
  (
    exists($h/position/gml:Arc) and 
    exists($h/position/gml:Arc/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:Circle) and 
    exists($h/position/gml:Circle/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:pos))
  )
) 
and
(
  every $h in //XP_LTO[position] satisfies
  (
    exists($h/position/gml:Curve) and 
    exists($h/position/gml:Curve/descendant-or-self::gml:segments) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:posList)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Curve/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:LineString) and 
    exists($h/position/gml:LineString/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:LineString/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:MultiCurve) and 
    exists($h/position/gml:MultiCurve/descendant-or-self::gml:segments) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:posList)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:MultiCurve/descendant-or-self::gml:pos))
  ) 
  or
  (
    exists($h/position/gml:LineStringSegment) and 
    exists($h/position/gml:LineStringSegment/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:LineStringSegment/descendant-or-self::gml:pos))
  ) 
  or
  (
    exists($h/position/gml:Arc) and 
    exists($h/position/gml:Arc/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Arc/descendant-or-self::gml:pos))
  )
  or
  (
    exists($h/position/gml:Circle) and 
    exists($h/position/gml:Circle/descendant-or-self::gml:posList) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:arcString)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:segment)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:segments)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:coordinates)) and
    not(exists($h/position/gml:Circle/descendant-or-self::gml:pos))
  )
)