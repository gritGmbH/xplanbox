declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'BP_AbgrabungsFlaeche|BP_AufschuettungsFlaeche|BP_AusgleichsFlaeche|BP_BesondererNutzungszweckFlaeche|BP_AbgrabungsFlaeche|BP_BodenschaetzeFlaeche|BP_BaugebietsTeilFlaeche|BP_GemeinbedarfsFlaeche|BP_GewaesserFlaeche|BP_GruenFlaeche|BP_KleintierhaltungFlaeche|BP_SpielSportanlagenFlaeche|BP_StrassenVerkehrsFlaeche|BP_VerkehrsflaecheBesondererZweckbestimmung|BP_WaldFlaeche|BP_KennzeichnungsFlaeche|BP_RekultivierungsFlaeche|BP_SchutzPflegeEntwicklungsFlaeche|BP_AbstandsFlaeche|BP_EingriffsBereich|BP_ErhaltungsBereichFlaeche|BP_FoerderungsFlaeche|BP_FreiFlaeche|BP_GebaeudeFlaeche|BP_GemeinschaftsanlagenFlaeche|BP_NebenanlagenAusschlussFlaeche|BP_NebenanlagenFlaeche|BP_PersGruppenBestimmteFlaeche|BP_RegelungVergnuegungsstaetten|BP_SpezielleBauweise|BP_TechnischeMassnahmenFlaeche|BP_TextlicheFestsetzungsFlaeche|BP_UeberbaubareGrundstuecksFlaeche|BP_Veraenderungssperre|BP_WasserwirtschaftsFlaeche')]
where (
  $h/flaechenschluss != 'false'
  and
  $h/ebene != 0
)
return $h/@gml:id/string()