declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'BP_AbstandsMass|BP_AnpflanzungBindungErhaltung|BP_AusgleichsMassnahme|BP_FestsetzungNachLandesrecht|BP_GemeinschaftsanlagenZuordnung|BP_GenerischesObjekt|BP_HoehenMass|BP_Immissionsschutz|BP_Landwirtschaft|BP_SchutzPflegeEntwicklungsMassnahme|BP_Strassenkoerper|BP_UnverbindlicheVormerkung|BP_VerEntsorgung|BP_Wegerecht')]
where (
	(
		$h/position/gml:Polygon or
		$h/position/gml:MultiSurface or
		$h/position/gml:LinearRing or
		$h/position/gml:PolygonPatch or
		$h/position/gml:Ring
	)
	and $h/ebene != '0'
	and not($h/flaechenschluss = 'false')
)
return $h/@gml:id/string()