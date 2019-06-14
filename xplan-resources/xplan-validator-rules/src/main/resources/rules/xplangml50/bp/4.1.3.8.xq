declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml = 'http://www.opengis.net/gml/3.2';
declare namespace xlink = 'http://www.w3.org/1999/xlink';

declare variable $featuretypes := ('BP_AbgrabungsFlaeche', 'BP_AufschuettungsFlaeche', 'BP_AusgleichsFlaeche', 'BP_BesondererNutzungszweckFlaeche', 'BP_AbgrabungsFlaeche', 'BP_BodenschaetzeFlaeche', 'BP_BaugebietsTeilFlaeche', 'BP_GemeinbedarfsFlaeche', 'BP_GewaesserFlaeche', 'BP_GruenFlaeche', 'BP_KleintierhaltungFlaeche', 'BP_SpielSportanlagenFlaeche', 'BP_StrassenVerkehrsFlaeche', 'BP_VerkehrsflaecheBesondererZweckbestimmung', 'BP_WaldFlaeche', 'BP_KennzeichnungsFlaeche', 'BP_RekultivierungsFlaeche', 'BP_SchutzPflegeEntwicklungsFlaeche', 'BP_AbstandsFlaeche', 'BP_EingriffsBereich', 'BP_ErhaltungsBereichFlaeche', 'BP_FoerderungsFlaeche', 'BP_FreiFlaeche', 'BP_GebaeudeFlaeche', 'BP_GemeinschaftsanlagenFlaeche', 'BP_NebenanlagenAusschlussFlaeche', 'BP_NebenanlagenFlaeche', 'BP_PersGruppenBestimmteFlaeche', 'BP_RegelungVergnuegungsstaetten', 'BP_SpezielleBauweise', 'BP_TechnischeMassnahmenFlaeche', 'BP_TextlicheFestsetzungsFlaeche', 'BP_UeberbaubareGrundstuecksFlaeche', 'BP_Veraenderungssperre', 'BP_WasserwirtschaftsFlaeche', 'BP_AbstandsMass', 'BP_AnpflanzungBindungErhaltung', 'BP_AusgleichsMassnahme', 'BP_FestsetzungNachLandesrecht', 'BP_GemeinschaftsanlagenZuordnung', 'BP_GenerischesObjekt', 'BP_HoehenMass', 'BP_Immissionsschutz', 'BP_Landwirtschaft', 'BP_SchutzPflegeEntwicklungsMassnahme', 'BP_Strassenkoerper', 'BP_UnverbindlicheVormerkung', 'BP_VerEntsorgung', 'BP_Wegerecht', 'BP_BaugebietsTeilFlaeche', 'BP_GemeinbedarfsFlaeche', 'BP_GewaesserFlaeche', 'BP_GruenFlaeche', 'BP_KleintierhaltungFlaeche', 'BP_SpielSportanlagenFlaeche', 'BP_StrassenVerkehrsFlaeche', 'BP_VerkehrsflaecheBesondererZweckbestimmung', 'BP_WaldFlaeche', 'BP_BauGrenze', 'BP_BauLinie', 'BP_BereichOhneEinAusfahrtLinie', 'BP_EinfahrtsbereichLinie', 'BP_FirstRichtungsLinie', 'BP_NutzungsartenGrenze', 'BP_StrassenbegrenzungsLinie', 'BP_EinfahrtPunkt');

(
    every $ref in //refTextinhalt satisfies
    let $id := substring($ref/@xlink:href, 2)
    let $featuretypename := local-name($ref/..)
    return
        index-of($featuretypes, $featuretypename) and exists(//BP_TextAbschnitt[@gml:id = substring($id, 2)])
)