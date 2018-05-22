declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

let $ids := (
  for $h in //BP_Bereich/inhaltBPlan/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
exists(//xplan:BP_Baugebiet[@gml:id = $id]) or
exists(//xplan:BP_Flaechenobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbgrabungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_AufschuettungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_AusgleichsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
exists(//xplan:BP_BodenschaetzeFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Flaechenschlussobjekt[@gml:id = $id]) or
exists(//xplan:BP_BaugebietsTeilFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GemeinbedarfsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GewaesserFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GruenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_KleintierhaltungFlaeche[@gml:id = $id]) or
exists(//xplan:BP_SpielSportanlagenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_StrassenVerkehrsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_VerkehrsflaecheBesondererZweckbestimmung[@gml:id = $id]) or
exists(//xplan:BP_WaldFlaeche[@gml:id = $id]) or
exists(//xplan:BP_KennzeichnungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_RekultivierungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Ueberlagerungsobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbstandsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_DenkmalschutzEnsembleFlaeche[@gml:id = $id]) or
exists(//xplan:BP_EingriffsBereich[@gml:id = $id]) or
exists(//xplan:BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
exists(//xplan:BP_ErneuerbareEnergieFlaeche[@gml:id = $id]) or
exists(//xplan:BP_FoerderungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_FreiFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GebaeudeFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_LuftreinhalteFlaeche[@gml:id = $id]) or
exists(//xplan:BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
exists(//xplan:BP_NebenanlagenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
exists(//xplan:BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
exists(//xplan:BP_SpezielleBauweise[@gml:id = $id]) or
exists(//xplan:BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Veraenderungssperre[@gml:id = $id]) or
exists(//xplan:BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbstandsMass[@gml:id = $id]) or
exists(//xplan:BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
exists(//xplan:BP_AusgleichsMassnahme[@gml:id = $id]) or
exists(//xplan:BP_DenkmalschutzEinzelanlage[@gml:id = $id]) or
exists(//xplan:BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
exists(//xplan:BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
exists(//xplan:BP_GenerischesObjekt[@gml:id = $id]) or
exists(//xplan:BP_HoehenMass[@gml:id = $id]) or
exists(//xplan:BP_Immissionsschutz[@gml:id = $id]) or
exists(//xplan:BP_Landwirtschaft[@gml:id = $id]) or
exists(//xplan:BP_Schutzgebiet[@gml:id = $id]) or
exists(//xplan:BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
exists(//xplan:BP_Strassenkoerper[@gml:id = $id]) or
exists(//xplan:BP_UnverbindlicheVormerkung[@gml:id = $id]) or
exists(//xplan:BP_VerEntsorgung[@gml:id = $id]) or
exists(//xplan:BP_Wegerecht[@gml:id = $id]) or
exists(//xplan:BP_Linienobjekt[@gml:id = $id]) or
exists(//xplan:BP_BauGrenze[@gml:id = $id]) or
exists(//xplan:BP_BauLinie[@gml:id = $id]) or
exists(//xplan:BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
exists(//xplan:BP_EinfahrtsbereichLinie[@gml:id = $id]) or
exists(//xplan:BP_FirstRichtungsLinie[@gml:id = $id]) or
exists(//xplan:BP_NutzungsartenGrenze[@gml:id = $id]) or
exists(//xplan:BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
exists(//xplan:BP_Punktobjekt[@gml:id = $id]) or
exists(//xplan:BP_EinfahrtPunkt[@gml:id = $id])