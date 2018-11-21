declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AufschuettungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BesondererNutzungszweckFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BodenschaetzeFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KennzeichnungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RekultivierungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EingriffsBereich/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_ErhaltungsBereichFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FoerderungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FreiFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GebaeudeFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenAusschlussFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_PersGruppenBestimmteFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RegelungVergnuegungsstaetten/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpezielleBauweise/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TechnischeMassnahmenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextlicheFestsetzungsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UeberbaubareGrundstuecksFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Veraenderungssperre/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WasserwirtschaftsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsMass/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsMassnahme/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FestsetzungNachLandesrecht/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenZuordnung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GenerischesObjekt/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_HoehenMass/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Immissionsschutz/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Landwirtschaft/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsMassnahme/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Strassenkoerper/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UnverbindlicheVormerkung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerEntsorgung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Wegerecht/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauGrenze/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauLinie/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BereichOhneEinAusfahrtLinie/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtsbereichLinie/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FirstRichtungsLinie/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NutzungsartenGrenze/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenbegrenzungsLinie/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtPunkt/wirdAusgeglichenDurchSPEMassnahme/@xlink:href satisfies
exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = substring($id,2)])
)