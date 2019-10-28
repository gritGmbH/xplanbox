declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'FP_Abgrabung|FP_AnpassungKlimawandel|FP_Aufschuettung|FP_Bodenschaetze|FP_Gemeinbedarf|FP_GenerischesObjekt|FP_Gewaesser|FP_Gruen|FP_Kennzeichnung|FP_PrivilegiertesVorhaben|FP_SchutzPflegeEntwicklung|FP_SpielSportanlage|FP_Strassenverkehr|FP_UnverbindlicheVormerkung|FP_VerEntsorgung|FP_Wasserwirtschaft')]
where (
	(
		$h/position/gml:Polygon or
		$h/position/gml:MultiSurface or
		$h/position/gml:LinearRing or
		$h/position/gml:PolygonPatch or
		$h/position/gml:Ring
	)
	and not ($h/flaechenschluss)
)
return $h/@gml:id/string()
