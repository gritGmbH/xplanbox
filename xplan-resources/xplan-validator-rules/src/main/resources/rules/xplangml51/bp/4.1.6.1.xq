declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'BP_AbstandsFlaeche|BP_EingriffsBereich|BP_ErhaltungsBereichFlaeche|BP_FoerderungsFlaeche|BP_FreiFlaeche|BP_GebaeudeFlaeche|BP_GemeinschaftsanlagenFlaeche|BP_NebenanlagenAusschlussFlaeche|BP_NebenanlagenFlaeche|BP_PersGruppenBestimmteFlaeche|BP_RegelungVergnuegungsstaetten|BP_SpezielleBauweise|BP_TechnischeMassnahmenFlaeche|BP_TextlicheFestsetzungsFlaeche|BP_UeberbaubareGrundstuecksFlaeche|BP_Veraenderungssperre')]
where not ($h/flaechenschluss/text() = 'false')
return $h/@gml:id/string()