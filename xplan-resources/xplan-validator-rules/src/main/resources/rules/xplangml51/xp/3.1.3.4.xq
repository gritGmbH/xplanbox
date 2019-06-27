declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

declare variable $featuretypes := ('BP_AbgrabungsFlaeche', 'BP_AufschuettungsFlaeche', 'BP_AusgleichsFlaeche', 'BP_BesondererNutzungszweckFlaeche', 'BP_BodenschaetzeFlaeche', 'BP_Flaechenschlussobjekt', 'BP_KennzeichnungsFlaeche', 'BP_RekultivierungsFlaeche', 'BP_SchutzPflegeEntwicklungsFlaeche', 'BP_AbstandsFlaeche', 'BP_EingriffsBereich', 'BP_ErhaltungsBereichFlaeche', 'BP_FoerderungsFlaeche', 'BP_FreiFlaeche', 'BP_GebaeudeFlaeche', 'BP_GemeinschaftsanlagenFlaeche', 'BP_NebenanlagenAusschlussFlaeche', 'BP_NebenanlagenFlaeche', 'BP_PersGruppenBestimmteFlaeche', 'BP_RegelungVergnuegungsstaetten', 'BP_SpezielleBauweise', 'BP_TechnischeMassnahmenFlaeche', 'BP_TextlicheFestsetzungsFlaeche', 'BP_UeberbaubareGrundstuecksFlaeche', 'BP_Veraenderungssperre', 'BP_WasserwirtschaftsFlaeche', 'BP_AbstandsMass', 'BP_AnpflanzungBindungErhaltung', 'BP_AusgleichsMassnahme', 'BP_FestsetzungNachLandesrecht', 'BP_GemeinschaftsanlagenZuordnung', 'BP_GenerischesObjekt', 'BP_HoehenMass', 'BP_Immissionsschutz', 'BP_Landwirtschaft', 'BP_SchutzPflegeEntwicklungsMassnahme', 'BP_Strassenkoerper', 'BP_UnverbindlicheVormerkung', 'BP_VerEntsorgung', 'BP_Wegerecht', 'BP_BaugebietsTeilFlaeche', 'BP_GemeinbedarfsFlaeche', 'BP_GewaesserFlaeche', 'BP_GruenFlaeche', 'BP_KleintierhaltungFlaeche', 'BP_SpielSportanlagenFlaeche', 'BP_SpielSportanlagenFlaeche', 'BP_StrassenVerkehrsFlaeche', 'BP_VerkehrsflaecheBesondererZweckbestimmung', 'BP_WaldFlaeche', 'BP_BauGrenze', 'BP_BauLinie', 'BP_BereichOhneEinAusfahrtLinie', 'BP_EinfahrtsbereichLinie', 'BP_FirstRichtungsLinie', 'BP_NutzungsartenGrenze', 'BP_StrassenbegrenzungsLinie', 'BP_EinfahrtPunkt', 'FP_AusgleichsFlaeche', 'FP_Flaechenschlussobjekt', 'FP_BebauungsFlaeche', 'FP_LandwirtschaftsFlaeche', 'FP_WaldFlaeche', 'FP_KeineZentrAbwasserBeseitigungFlaeche', 'FP_NutzungsbeschraenkungsFlaeche', 'FP_TextlicheDarstellungsFlaeche', 'FP_ZentralerVersorgungsbereich', 'FP_VorbehalteFlaeche', 'FP_Abgrabung', 'FP_AnpassungKlimawandel', 'FP_Aufschuettung', 'FP_Bodenschaetze', 'FP_Gemeinbedarf', 'FP_GenerischesObjekt', 'FP_Gewaesser', 'FP_Gruen', 'FP_Kennzeichnung', 'FP_PrivilegiertesVorhaben', 'FP_SchutzPflegeEntwicklung', 'FP_SpielSportanlage', 'FP_Strassenverkehr', 'FP_UnverbindlicheVormerkung', 'FP_VerEntsorgung', 'FP_Wasserwirtschaft', 'LP_AllgGruenflaeche', 'LP_TextlicheFestsetzungsFlaeche', 'LP_ZuBegruenendeGrundstueckflaeche', 'LP_AnpflanzungBindungErhaltung', 'LP_Ausgleich', 'LP_Biotopverbundflaeche', 'LP_Bodenschutzrecht', 'LP_ErholungFreizeit', 'LP_Forstrecht', 'LP_GenerischesObjekt', 'LP_Landschaftsbild', 'LP_NutzungsAusschluss', 'LP_NutzungserfordernisRegelung', 'LP_PlanerischeVertiefung', 'LP_SchutzobjektInternatRecht', 'LP_SchutzobjektLandesrecht', 'LP_SchutzPflegeEntwicklung', 'LP_SonstigesRecht', 'LP_WasserrechtGemeingebrEinschraenkungNaturschutz', 'LP_WasserrechtSchutzgebiet', 'LP_WasserrechtSonstige', 'LP_WasserrechtWirtschaftAbflussHochwSchutz', 'LP_Zwischennutzung', 'LP_Abgrenzung', 'RP_Achse', 'RP_Energieversorgung', 'RP_Entsorgung', 'RP_Freiraum', 'RP_Bodenschutz', 'RP_Erholung', 'RP_ErneuerbareEnergie', 'RP_Forstwirtschaft', 'RP_Gewaesser', 'RP_GruenzugGruenzaesur', 'RP_Hochwasserschutz', 'RP_Klimaschutz', 'RP_Kulturlandschaft', 'RP_Landwirtschaft', 'RP_NaturLandschaft', 'RP_NaturschutzrechtlichesSchutzgebiet', 'RP_RadwegWanderweg', 'RP_Rohstoff', 'RP_SonstigerFreiraumschutz', 'RP_Sportanlage', 'RP_Wasserschutz', 'RP_Funktionszuweisung', 'RP_GenerischesObjekt', 'RP_Grenze', 'RP_Kommunikation', 'RP_LaermschutzBauschutz', 'RP_Planungsraum', 'RP_Raumkategorie', 'RP_Siedlung', 'RP_Einzelhandel', 'RP_IndustrieGewerbe', 'RP_SonstigerSiedlungsbereich', 'RP_WohnenSiedlung', 'RP_SonstigeInfrastruktur', 'RP_SozialeInfrastruktur', 'RP_Sperrgebiet', 'RP_Verkehr', 'RP_Luftverkehr', 'RP_Schienenverkehr', 'RP_SonstVerkehr', 'RP_Strassenverkehr', 'RP_Wasserverkehr', 'RP_Wasserwirtschaft', 'RP_ZentralerOrt', 'SO_Objekt', 'SO_Gebiet', 'SO_Bodenschutzrecht', 'SO_Denkmalschutzrecht', 'SO_Forstrecht', 'SO_Luftverkehrsrecht', 'SO_Schienenverkehrsrecht', 'SO_SchutzgebietNaturschutzrecht', 'SO_SchutzgebietSonstigesRecht', 'SO_SchutzgebietWasserrecht', 'SO_SonstigesRecht', 'SO_Strassenverkehrsrecht', 'SO_Wasserrecht', 'SO_Grenze');

declare function local:checkRef($rootNode as node(), $objectId as xs:string, $bereichRef as xs:string)
as xs:boolean {
    let $feature := $rootNode//*[@gml:id = $objectId and wirdDargestelltDurch[@xlink:href = $bereichRef]]
    return $feature and index-of($featuretypes, local-name($feature))
};

declare variable $xplanauszug := //XPlanAuszug;

(
    every $fpo in //XP_FPO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($fpo/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $fpo/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $lpo in //XP_LPO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($lpo/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $lpo/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $ppo in //XP_PPO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($ppo/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $ppo/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $praesentationsobjekt in //XP_Praesentationsobjekt[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($praesentationsobjekt/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $praesentationsobjekt/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $tpo in //XP_TPO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($tpo/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $tpo/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $lto in //XP_LTO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($lto/dientZurDarstellungVon/@xlink:href, 2)
        return
            let $bereichRef := concat('#', $lto/@gml:id)
            return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $pto in //XP_PTO[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($pto/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $pto/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
) and (
    every $nutzungsschablone in //XP_Nutzungsschablone[dientZurDarstellungVon] satisfies
    (
        let $objectId := substring($nutzungsschablone/dientZurDarstellungVon/@xlink:href, 2)
        let $bereichRef := concat('#', $nutzungsschablone/@gml:id)
        return local:checkRef($xplanauszug, $objectId, $bereichRef)
    )
)