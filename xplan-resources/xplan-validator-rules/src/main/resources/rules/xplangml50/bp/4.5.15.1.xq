declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_GemeinschaftsanlagenZuordnung
where (
	(
		$h/position/gml:Polygon or
		$h/position/gml:MultiSurface or
		$h/position/gml:LinearRing or
		$h/position/gml:PolygonPatch or
		$h/position/gml:Ring
	)
	and not($h/flaechenschluss = 'false')
)
return $h/@gml:id/string()
