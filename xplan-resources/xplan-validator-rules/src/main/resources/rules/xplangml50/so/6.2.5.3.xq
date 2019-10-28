declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //SO_Schienenverkehrsrecht
where (
	(
		$h/position/gml:Polygon or
		$h/position/gml:MultiSurface or
		$h/position/gml:LinearRing or
		$h/position/gml:PolygonPatch or
		$h/position/gml:Ring
	)
	and $h/ebene = '0'
	and not($h/flaechenschluss = 'true')
)
return $h/@gml:id/string()