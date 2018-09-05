declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //BP_AbgrabungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AufschuettungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BesondererNutzungszweckFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbgrabungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BodenschaetzeFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KennzeichnungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RekultivierungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EingriffsBereich/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_ErhaltungsBereichFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FoerderungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FreiFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GebaeudeFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenAusschlussFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_PersGruppenBestimmteFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RegelungVergnuegungsstaetten/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpezielleBauweise/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TechnischeMassnahmenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextlicheFestsetzungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UeberbaubareGrundstuecksFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Veraenderungssperre/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WasserwirtschaftsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsMass/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextAbschnitt/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextAbschnitt/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FestsetzungNachLandesrecht/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenZuordnung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GenerischesObjekt/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_HoehenMass/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Immissionsschutz/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Landwirtschaft/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Strassenkoerper/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UnverbindlicheVormerkung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerEntsorgung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Wegerecht/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauGrenze/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauLinie/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BereichOhneEinAusfahrtLinie/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtsbereichLinie/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FirstRichtungsLinie/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NutzungsartenGrenze/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenbegrenzungsLinie/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtPunkt/refTextinhalt/@xlink:href satisfies
exists(//BP_TextAbschnitt[@gml:id = substring($id,2)])
)