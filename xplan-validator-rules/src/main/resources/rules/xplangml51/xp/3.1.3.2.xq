declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $h in //BP_AbgrabungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AufschuettungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AusgleichsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BodenschaetzeFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Flaechenschlussobjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_KennzeichnungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_RekultivierungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_SchutzPflegeEntwicklungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AbstandsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_EingriffsBereich/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_ErhaltungsBereichFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_FoerderungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_FreiFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GebaeudeFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GemeinschaftsanlagenFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_NebenanlagenAusschlussFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_NebenanlagenFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_PersGruppenBestimmteFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_RegelungVergnuegungsstaetten/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_SpezielleBauweise/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_TechnischeMassnahmenFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_TextlicheFestsetzungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Veraenderungssperre/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_WasserwirtschaftsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AbstandsMass/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AnpflanzungBindungErhaltung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_AusgleichsMassnahme/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_FestsetzungNachLandesrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GemeinschaftsanlagenZuordnung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GenerischesObjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_HoehenMass/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Immissionsschutz/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Landwirtschaft/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_SchutzPflegeEntwicklungsMassnahme/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Strassenkoerper/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_UnverbindlicheVormerkung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_VerEntsorgung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_Wegerecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GewaesserFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_GruenFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_KleintierhaltungFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_WaldFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BauGrenze/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BauLinie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_BereichOhneEinAusfahrtLinie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_EinfahrtsbereichLinie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_FirstRichtungsLinie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_NutzungsartenGrenze/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_StrassenbegrenzungsLinie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_EinfahrtPunkt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_AusgleichsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Flaechenschlussobjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_BebauungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_LandwirtschaftsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_WaldFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_KeineZentrAbwasserBeseitigungFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_NutzungsbeschraenkungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_TextlicheDarstellungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_ZentralerVersorgungsbereich/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_VorbehalteFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Abgrabung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_AnpassungKlimawandel/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Aufschuettung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Bodenschaetze/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Gemeinbedarf/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_GenerischesObjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Gewaesser/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Gruen/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Kennzeichnung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_PrivilegiertesVorhaben/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_SchutzPflegeEntwicklung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_SpielSportanlage/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Strassenverkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_UnverbindlicheVormerkung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_VerEntsorgung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Wasserwirtschaft/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_AllgGruenflaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_TextlicheFestsetzungsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_ZuBegruenendeGrundstueckflaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_AnpflanzungBindungErhaltung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Ausgleich/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Biotopverbundflaeche/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Bodenschutzrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_ErholungFreizeit/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Forstrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_GenerischesObjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Landschaftsbild/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_NutzungsAusschluss/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_NutzungserfordernisRegelung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_PlanerischeVertiefung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_SchutzobjektInternatRecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_SchutzobjektLandesrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_SchutzPflegeEntwicklung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_SonstigesRecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_WasserrechtGemeingebrEinschraenkungNaturschutz/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_WasserrechtSchutzgebiet/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_WasserrechtSonstige/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_WasserrechtWirtschaftAbflussHochwSchutz/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Zwischennutzung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //LP_Abgrenzung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Achse/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Energieversorgung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Entsorgung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Freiraum/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Funktionszuweisung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_GenerischesObjekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Grenze/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Kommunikation/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_LaermschutzBauschutz/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Planungsraum/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Raumkategorie/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Siedlung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Einzelhandel/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_IndustrieGewerbe/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_SonstigerSiedlungsbereich/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_WohnenSiedlung/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_SonstigeInfrastruktur/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_SozialeInfrastruktur/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Sperrgebiet/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Verkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Luftverkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Schienenverkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_SonstVerkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Strassenverkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Wasserverkehr/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_Wasserwirtschaft/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //RP_ZentralerOrt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Objekt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Gebiet/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Bodenschutzrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Denkmalschutzrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Forstrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Luftverkehrsrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Schienenverkehrsrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_SchutzgebietNaturschutzrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_SchutzgebietSonstigesRecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_SchutzgebietWasserrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_SonstigesRecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Strassenverkehrsrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Wasserrecht/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //SO_Grenze/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //FP_Landwirtschaft/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)
and
(
  every $h in //BP_LandwirtschaftsFlaeche/@xlink:href satisfies
  let $id := substring($h,2) return
    exists(//XP_BegruendungAbschnitt[@gml:id = $id])
)