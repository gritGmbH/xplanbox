declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AufschuettungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AusgleichsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BesondererNutzungszweckFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BodenschaetzeFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_KennzeichnungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_RekultivierungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AbstandsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_EingriffsBereich/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_ErhaltungsBereichFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_FoerderungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_FreiFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GebaeudeFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GemeinschaftsanlagenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_NebenanlagenAusschlussFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_NebenanlagenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_PersGruppenBestimmteFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_RegelungVergnuegungsstaetten/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_SpezielleBauweise/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_TechnischeMassnahmenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_TextlicheFestsetzungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_UeberbaubareGrundstuecksFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_Veraenderungssperre/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_WasserwirtschaftsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AbstandsMass/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_AusgleichsMassnahme/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_FestsetzungNachLandesrecht/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GemeinschaftsanlagenZuordnung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GenerischesObjekt/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_HoehenMass/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_Immissionsschutz/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_Landwirtschaft/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_SchutzPflegeEntwicklungsMassnahme/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_Strassenkoerper/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_UnverbindlicheVormerkung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_VerEntsorgung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_Wegerecht/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BauGrenze/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BauLinie/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_BereichOhneEinAusfahrtLinie/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_EinfahrtsbereichLinie/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_FirstRichtungsLinie/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_NutzungsartenGrenze/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_StrassenbegrenzungsLinie/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_EinfahrtPunkt/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_LandwirtschaftsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
  every $id in //BP_NichtUeberbaubareGrundstuecksflaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
  exists(//BP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)