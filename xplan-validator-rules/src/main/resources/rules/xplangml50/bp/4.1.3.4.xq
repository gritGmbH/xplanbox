declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AufschuettungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BesondererNutzungszweckFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BodenschaetzeFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KennzeichnungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RekultivierungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EingriffsBereich/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_ErhaltungsBereichFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FoerderungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FreiFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GebaeudeFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenAusschlussFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_PersGruppenBestimmteFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RegelungVergnuegungsstaetten/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpezielleBauweise/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TechnischeMassnahmenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextlicheFestsetzungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UeberbaubareGrundstuecksFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Veraenderungssperre/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WasserwirtschaftsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsMass/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FestsetzungNachLandesrecht/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenZuordnung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GenerischesObjekt/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_HoehenMass/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Immissionsschutz/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Landwirtschaft/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Strassenkoerper/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UnverbindlicheVormerkung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerEntsorgung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Wegerecht/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauGrenze/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauLinie/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BereichOhneEinAusfahrtLinie/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtsbereichLinie/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FirstRichtungsLinie/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NutzungsartenGrenze/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenbegrenzungsLinie/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtPunkt/wirdAusgeglichenDurchSPEFlaeche/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = substring($id,2)])
)