declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(name(), 'BP_.*[fF]laeche|BP_VerkehrsflaecheBesondererZweckbestimmung|BP_EingriffsBereich|BP_RegelungVergnuegungsstaetten|BP_Veraenderungssperre')]
where not (
	$h/position/gml:Polygon or
	$h/position/gml:MultiSurface or
	$h/position/gml:LinearRing or
	$h/position/gml:PolygonPatch or
	$h/position/gml:Ring
)
return $h/@gml:id/string()