declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'BP_BaugebietsTeilFlaeche|BP_GemeinbedarfsFlaeche|BP_GewaesserFlaeche|BP_GruenFlaeche|BP_KleintierhaltungFlaeche|BP_SpielSportanlagenFlaeche|BP_StrassenVerkehrsFlaeche|BP_VerkehrsflaecheBesondererZweckbestimmung|BP_WaldFlaeche')]
where not (
	(
		not ($h/ebene)
		or $h/ebene = 0
	)
	and ($h/flaechenschluss/text() = 'true')
)
return $h/@gml:id/string()