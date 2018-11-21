declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AufschuettungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AusgleichsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BesondererNutzungszweckFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbgrabungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BodenschaetzeFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KennzeichnungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RekultivierungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SchutzPflegeEntwicklungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EingriffsBereich/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_ErhaltungsBereichFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FoerderungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FreiFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GebaeudeFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenAusschlussFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NebenanlagenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_PersGruppenBestimmteFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_RegelungVergnuegungsstaetten/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpezielleBauweise/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TechnischeMassnahmenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_TextlicheFestsetzungsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UeberbaubareGrundstuecksFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Veraenderungssperre/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WasserwirtschaftsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AbstandsMass/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FestsetzungNachLandesrecht/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinschaftsanlagenZuordnung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GenerischesObjekt/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_HoehenMass/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Immissionsschutz/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Landwirtschaft/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_AnpflanzungBindungErhaltung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Strassenkoerper/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_UnverbindlicheVormerkung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerEntsorgung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_Wegerecht/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BaugebietsTeilFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GemeinbedarfsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GewaesserFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_GruenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_KleintierhaltungFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_SpielSportanlagenFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenVerkehrsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_WaldFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauGrenze/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BauLinie/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_BereichOhneEinAusfahrtLinie/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtsbereichLinie/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_FirstRichtungsLinie/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_NutzungsartenGrenze/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_StrassenbegrenzungsLinie/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_EinfahrtPunkt/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)
and
(
every $id in //BP_LandwirtschaftsFlaeche/wirdAusgeglichenDurchABE/@xlink:href satisfies
exists(//BP_AnpflanzungBindungErhaltung[@gml:id = substring($id,2)])
)