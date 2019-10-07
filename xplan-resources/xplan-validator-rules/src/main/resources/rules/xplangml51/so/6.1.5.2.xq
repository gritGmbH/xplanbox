declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'SO_Bodenschutzrecht|SO_Denkmalschutzrecht|SO_Forstrecht|SO_Luftverkehrsrecht|SO_Schienenverkehrsrecht|SO_SchutzgebietNaturschutzrecht|SO_SchutzgebietSonstigesRecht|SO_SchutzgebietWasserrecht|SO_SonstigesRecht|SO_Strassenverkehrsrecht|SO_Wasserrecht')]
where not (
	(
		$h/position/gml:Polygon or
		$h/position/gml:MultiSurface or
		$h/position/gml:LinearRing or
		$h/position/gml:PolygonPatch or
		$h/position/gml:Ring
	)
	and not($h/flaechenschluss)
)
return $h/@gml:id/string()