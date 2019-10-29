declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(name(), 'XP_L[PT]O')]
where not (
	$h/position/gml:Curve or
	$h/position/gml:LineString or
	$h/position/gml:MultiCurve or
	$h/position/gml:LineStringSegment or
	$h/position/gml:Arc or
	$h/position/gml:Circle
)
return $h/@gml:id/string()