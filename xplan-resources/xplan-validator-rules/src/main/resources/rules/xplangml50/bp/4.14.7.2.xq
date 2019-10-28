declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_HoehenMass/position
where (
	$h/gml:Polygon or
	$h/gml:MultiSurface or
	$h/gml:LinearRing or
	$h/gml:PolygonPatch or
	$h/gml:Ring
)
return $h/@gml:id/string()