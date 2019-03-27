declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

(
  every $h in //XP_LPO[position] satisfies
    exists($h/position/gml:Curve)
  or
    exists($h/position/gml:LineString)
  or
    exists($h/position/gml:MultiCurve)
  or
    exists($h/position/gml:LineStringSegment)
  or
    exists($h/position/gml:Arc)
  or
    exists($h/position/gml:Circle)
) 
and
(
  every $h in //XP_LTO[position] satisfies
    exists($h/position/gml:Curve)
  or
    exists($h/position/gml:LineString)
  or
    exists($h/position/gml:MultiCurve)
  or
    exists($h/position/gml:LineStringSegment)
  or
    exists($h/position/gml:Arc)
  or
    exists($h/position/gml:Circle)
)