declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AufschuettungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BesondererNutzungszweckFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BodenschaetzeFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KennzeichnungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RekultivierungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EingriffsBereich/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_ErhaltungsBereichFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FoerderungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FreiFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GebaeudeFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenAusschlussFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_PersGruppenBestimmteFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RegelungVergnuegungsstaetten/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpezielleBauweise/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TechnischeMassnahmenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextlicheFestsetzungsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UeberbaubareGrundstuecksFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Veraenderungssperre/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WasserwirtschaftsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsMass/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsMassnahme/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FestsetzungNachLandesrecht/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenZuordnung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GenerischesObjekt/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_HoehenMass/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Immissionsschutz/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Landwirtschaft/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsMassnahme/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Strassenkoerper/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UnverbindlicheVormerkung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerEntsorgung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Wegerecht/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauGrenze/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauLinie/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BereichOhneEinAusfahrtLinie/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtsbereichLinie/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FirstRichtungsLinie/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NutzungsartenGrenze/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenbegrenzungsLinie/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtPunkt/wirdAusgeglichenDurchMassnahme/@xlink:href satisfies
exists(//BP_AusgleichsMassnahme[@gml:id = substring($id,2)])
)