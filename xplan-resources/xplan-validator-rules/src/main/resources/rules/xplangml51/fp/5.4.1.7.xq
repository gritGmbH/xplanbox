declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //FP_Gemeinbedarf/position
where not (
	$h/gml:Polygon or
	$h/gml:MultiSurface or
	$h/gml:LinearRing or
	$h/gml:PolygonPatch or
	$h/gml:Ring or
	$h/gml:Point or
	$h/gml:MultiPoint
)
return $h/../@gml:id/string()