declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $h in //BP_Bereich/planinhalt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//BP_AbgrabungsFlaeche[@gml:id = $id]) or
  exists(//BP_AufschuettungsFlaeche[@gml:id = $id]) or
  exists(//BP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
  exists(//BP_BodenschaetzeFlaeche[@gml:id = $id]) or
  exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//BP_KennzeichnungsFlaeche[@gml:id = $id]) or
  exists(//BP_RekultivierungsFlaeche[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsFlaeche[@gml:id = $id]) or
  exists(//BP_EingriffsBereich[@gml:id = $id]) or
  exists(//BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
  exists(//BP_FoerderungsFlaeche[@gml:id = $id]) or
  exists(//BP_FreiFlaeche[@gml:id = $id]) or
  exists(//BP_GebaeudeFlaeche[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
  exists(//BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
  exists(//BP_SpezielleBauweise[@gml:id = $id]) or
  exists(//BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
  exists(//BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
  exists(//BP_Veraenderungssperre[@gml:id = $id]) or
  exists(//BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsMass[@gml:id = $id]) or
  exists(//BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//BP_AusgleichsMassnahme[@gml:id = $id]) or
  exists(//BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
  exists(//BP_GenerischesObjekt[@gml:id = $id]) or
  exists(//BP_HoehenMass[@gml:id = $id]) or
  exists(//BP_Immissionsschutz[@gml:id = $id]) or
  exists(//BP_Landwirtschaft[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
  exists(//BP_Strassenkoerper[@gml:id = $id]) or
  exists(//BP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//BP_VerEntsorgung[@gml:id = $id]) or
  exists(//BP_Wegerecht[@gml:id = $id]) or
  exists(//BP_BauGrenze[@gml:id = $id]) or
  exists(//BP_BauLinie[@gml:id = $id]) or
  exists(//BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtsbereichLinie[@gml:id = $id]) or
  exists(//BP_FirstRichtungsLinie[@gml:id = $id]) or
  exists(//BP_NutzungsartenGrenze[@gml:id = $id]) or
  exists(//BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtPunkt[@gml:id = $id]) or

  exists(//FP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//FP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//FP_BebauungsFlaeche[@gml:id = $id]) or
  exists(//FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//FP_WaldFlaeche[@gml:id = $id]) or
  exists(//FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
  exists(//FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
  exists(//FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
  exists(//FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
  exists(//FP_VorbehalteFlaeche[@gml:id = $id]) or
  exists(//FP_Abgrabung[@gml:id = $id]) or
  exists(//FP_AnpassungKlimawandel[@gml:id = $id]) or
  exists(//FP_Aufschuettung[@gml:id = $id]) or
  exists(//FP_Bodenschaetze[@gml:id = $id]) or
  exists(//FP_Gemeinbedarf[@gml:id = $id]) or
  exists(//FP_GenerischesObjekt[@gml:id = $id]) or
  exists(//FP_Gewaesser[@gml:id = $id]) or
  exists(//FP_Gruen[@gml:id = $id]) or
  exists(//FP_Kennzeichnung[@gml:id = $id]) or
  exists(//FP_PrivilegiertesVorhaben[@gml:id = $id]) or
  exists(//FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//FP_SpielSportanlage[@gml:id = $id]) or
  exists(//FP_Strassenverkehr[@gml:id = $id]) or
  exists(//FP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//FP_VerEntsorgung[@gml:id = $id]) or
  exists(//FP_Wasserwirtschaft[@gml:id = $id]) or

  exists(//LP_AllgGruenflaeche[@gml:id = $id]) or
  exists(//LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
  exists(//LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//LP_Ausgleich[@gml:id = $id]) or
  exists(//LP_Biotopverbundflaeche[@gml:id = $id]) or
  exists(//LP_Bodenschutzrecht[@gml:id = $id]) or
  exists(//LP_ErholungFreizeit[@gml:id = $id]) or
  exists(//LP_Forstrecht[@gml:id = $id]) or
  exists(//LP_GenerischesObjekt[@gml:id = $id]) or
  exists(//LP_Landschaftsbild[@gml:id = $id]) or
  exists(//LP_NutzungsAusschluss[@gml:id = $id]) or
  exists(//LP_NutzungserfordernisRegelung[@gml:id = $id]) or
  exists(//LP_PlanerischeVertiefung[@gml:id = $id]) or
  exists(//LP_SchutzobjektInternatRecht[@gml:id = $id]) or
  exists(//LP_SchutzobjektLandesrecht[@gml:id = $id]) or
  exists(//LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//LP_SonstigesRecht[@gml:id = $id]) or
  exists(//LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
  exists(//LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
  exists(//LP_WasserrechtSonstige[@gml:id = $id]) or
  exists(//LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
  exists(//LP_Zwischennutzung[@gml:id = $id]) or
  exists(//LP_Abgrenzung[@gml:id = $id]) or

  exists(//RP_Achse[@gml:id = $id]) or
  exists(//RP_Energieversorgung[@gml:id = $id]) or
  exists(//RP_Entsorgung[@gml:id = $id]) or
  exists(//RP_Freiraum[@gml:id = $id]) or
  exists(//RP_Funktionszuweisung[@gml:id = $id]) or
  exists(//RP_GenerischesObjekt[@gml:id = $id]) or
  exists(//RP_Grenze[@gml:id = $id]) or
  exists(//RP_Kommunikation[@gml:id = $id]) or
  exists(//RP_LaermschutzBauschutz[@gml:id = $id]) or
  exists(//RP_Planungsraum[@gml:id = $id]) or
  exists(//RP_Raumkategorie[@gml:id = $id]) or
  exists(//RP_Siedlung[@gml:id = $id]) or
  exists(//RP_Einzelhandel[@gml:id = $id]) or
  exists(//RP_IndustrieGewerbe[@gml:id = $id]) or
  exists(//RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
  exists(//RP_WohnenSiedlung[@gml:id = $id]) or
  exists(//RP_SonstigeInfrastruktur[@gml:id = $id]) or
  exists(//RP_SozialeInfrastruktur[@gml:id = $id]) or
  exists(//RP_Sperrgebiet[@gml:id = $id]) or
  exists(//RP_Verkehr[@gml:id = $id]) or
  exists(//RP_Luftverkehr[@gml:id = $id]) or
  exists(//RP_Schienenverkehr[@gml:id = $id]) or
  exists(//RP_SonstVerkehr[@gml:id = $id]) or
  exists(//RP_Strassenverkehr[@gml:id = $id]) or
  exists(//RP_Wasserverkehr[@gml:id = $id]) or
  exists(//RP_Wasserwirtschaft[@gml:id = $id]) or
  exists(//RP_ZentralerOrt[@gml:id = $id]) or

  exists(//SO_Objekt[@gml:id = $id]) or
  exists(//SO_Gebiet[@gml:id = $id]) or
  exists(//SO_Bodenschutzrecht[@gml:id = $id]) or
  exists(//SO_Denkmalschutzrecht[@gml:id = $id]) or
  exists(//SO_Forstrecht[@gml:id = $id]) or
  exists(//SO_Luftverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Schienenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietWasserrecht[@gml:id = $id]) or
  exists(//SO_SonstigesRecht[@gml:id = $id]) or
  exists(//SO_Strassenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Wasserrecht[@gml:id = $id]) or
  exists(//SO_Grenze[@gml:id = $id])
)
and
(
  every $h in //FP_Bereich/planinhalt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//BP_AbgrabungsFlaeche[@gml:id = $id]) or
  exists(//BP_AufschuettungsFlaeche[@gml:id = $id]) or
  exists(//BP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
  exists(//BP_BodenschaetzeFlaeche[@gml:id = $id]) or
  exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//BP_KennzeichnungsFlaeche[@gml:id = $id]) or
  exists(//BP_RekultivierungsFlaeche[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsFlaeche[@gml:id = $id]) or
  exists(//BP_EingriffsBereich[@gml:id = $id]) or
  exists(//BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
  exists(//BP_FoerderungsFlaeche[@gml:id = $id]) or
  exists(//BP_FreiFlaeche[@gml:id = $id]) or
  exists(//BP_GebaeudeFlaeche[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
  exists(//BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
  exists(//BP_SpezielleBauweise[@gml:id = $id]) or
  exists(//BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
  exists(//BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
  exists(//BP_Veraenderungssperre[@gml:id = $id]) or
  exists(//BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsMass[@gml:id = $id]) or
  exists(//BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//BP_AusgleichsMassnahme[@gml:id = $id]) or
  exists(//BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
  exists(//BP_GenerischesObjekt[@gml:id = $id]) or
  exists(//BP_HoehenMass[@gml:id = $id]) or
  exists(//BP_Immissionsschutz[@gml:id = $id]) or
  exists(//BP_Landwirtschaft[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
  exists(//BP_Strassenkoerper[@gml:id = $id]) or
  exists(//BP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//BP_VerEntsorgung[@gml:id = $id]) or
  exists(//BP_Wegerecht[@gml:id = $id]) or
  exists(//BP_BauGrenze[@gml:id = $id]) or
  exists(//BP_BauLinie[@gml:id = $id]) or
  exists(//BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtsbereichLinie[@gml:id = $id]) or
  exists(//BP_FirstRichtungsLinie[@gml:id = $id]) or
  exists(//BP_NutzungsartenGrenze[@gml:id = $id]) or
  exists(//BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtPunkt[@gml:id = $id]) or

  exists(//FP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//FP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//FP_BebauungsFlaeche[@gml:id = $id]) or
  exists(//FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//FP_WaldFlaeche[@gml:id = $id]) or
  exists(//FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
  exists(//FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
  exists(//FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
  exists(//FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
  exists(//FP_VorbehalteFlaeche[@gml:id = $id]) or
  exists(//FP_Abgrabung[@gml:id = $id]) or
  exists(//FP_AnpassungKlimawandel[@gml:id = $id]) or
  exists(//FP_Aufschuettung[@gml:id = $id]) or
  exists(//FP_Bodenschaetze[@gml:id = $id]) or
  exists(//FP_Gemeinbedarf[@gml:id = $id]) or
  exists(//FP_GenerischesObjekt[@gml:id = $id]) or
  exists(//FP_Gewaesser[@gml:id = $id]) or
  exists(//FP_Gruen[@gml:id = $id]) or
  exists(//FP_Kennzeichnung[@gml:id = $id]) or
  exists(//FP_PrivilegiertesVorhaben[@gml:id = $id]) or
  exists(//FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//FP_SpielSportanlage[@gml:id = $id]) or
  exists(//FP_Strassenverkehr[@gml:id = $id]) or
  exists(//FP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//FP_VerEntsorgung[@gml:id = $id]) or
  exists(//FP_Wasserwirtschaft[@gml:id = $id]) or

  exists(//LP_AllgGruenflaeche[@gml:id = $id]) or
  exists(//LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
  exists(//LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//LP_Ausgleich[@gml:id = $id]) or
  exists(//LP_Biotopverbundflaeche[@gml:id = $id]) or
  exists(//LP_Bodenschutzrecht[@gml:id = $id]) or
  exists(//LP_ErholungFreizeit[@gml:id = $id]) or
  exists(//LP_Forstrecht[@gml:id = $id]) or
  exists(//LP_GenerischesObjekt[@gml:id = $id]) or
  exists(//LP_Landschaftsbild[@gml:id = $id]) or
  exists(//LP_NutzungsAusschluss[@gml:id = $id]) or
  exists(//LP_NutzungserfordernisRegelung[@gml:id = $id]) or
  exists(//LP_PlanerischeVertiefung[@gml:id = $id]) or
  exists(//LP_SchutzobjektInternatRecht[@gml:id = $id]) or
  exists(//LP_SchutzobjektLandesrecht[@gml:id = $id]) or
  exists(//LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//LP_SonstigesRecht[@gml:id = $id]) or
  exists(//LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
  exists(//LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
  exists(//LP_WasserrechtSonstige[@gml:id = $id]) or
  exists(//LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
  exists(//LP_Zwischennutzung[@gml:id = $id]) or
  exists(//LP_Abgrenzung[@gml:id = $id]) or

  exists(//RP_Achse[@gml:id = $id]) or
  exists(//RP_Energieversorgung[@gml:id = $id]) or
  exists(//RP_Entsorgung[@gml:id = $id]) or
  exists(//RP_Freiraum[@gml:id = $id]) or
  exists(//RP_Funktionszuweisung[@gml:id = $id]) or
  exists(//RP_GenerischesObjekt[@gml:id = $id]) or
  exists(//RP_Grenze[@gml:id = $id]) or
  exists(//RP_Kommunikation[@gml:id = $id]) or
  exists(//RP_LaermschutzBauschutz[@gml:id = $id]) or
  exists(//RP_Planungsraum[@gml:id = $id]) or
  exists(//RP_Raumkategorie[@gml:id = $id]) or
  exists(//RP_Siedlung[@gml:id = $id]) or
  exists(//RP_Einzelhandel[@gml:id = $id]) or
  exists(//RP_IndustrieGewerbe[@gml:id = $id]) or
  exists(//RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
  exists(//RP_WohnenSiedlung[@gml:id = $id]) or
  exists(//RP_SonstigeInfrastruktur[@gml:id = $id]) or
  exists(//RP_SozialeInfrastruktur[@gml:id = $id]) or
  exists(//RP_Sperrgebiet[@gml:id = $id]) or
  exists(//RP_Verkehr[@gml:id = $id]) or
  exists(//RP_Luftverkehr[@gml:id = $id]) or
  exists(//RP_Schienenverkehr[@gml:id = $id]) or
  exists(//RP_SonstVerkehr[@gml:id = $id]) or
  exists(//RP_Strassenverkehr[@gml:id = $id]) or
  exists(//RP_Wasserverkehr[@gml:id = $id]) or
  exists(//RP_Wasserwirtschaft[@gml:id = $id]) or
  exists(//RP_ZentralerOrt[@gml:id = $id]) or

  exists(//SO_Objekt[@gml:id = $id]) or
  exists(//SO_Gebiet[@gml:id = $id]) or
  exists(//SO_Bodenschutzrecht[@gml:id = $id]) or
  exists(//SO_Denkmalschutzrecht[@gml:id = $id]) or
  exists(//SO_Forstrecht[@gml:id = $id]) or
  exists(//SO_Luftverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Schienenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietWasserrecht[@gml:id = $id]) or
  exists(//SO_SonstigesRecht[@gml:id = $id]) or
  exists(//SO_Strassenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Wasserrecht[@gml:id = $id]) or
  exists(//SO_Grenze[@gml:id = $id])
)
and
(
  every $h in //LP_Bereich/planinhalt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//BP_AbgrabungsFlaeche[@gml:id = $id]) or
  exists(//BP_AufschuettungsFlaeche[@gml:id = $id]) or
  exists(//BP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
  exists(//BP_BodenschaetzeFlaeche[@gml:id = $id]) or
  exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//BP_KennzeichnungsFlaeche[@gml:id = $id]) or
  exists(//BP_RekultivierungsFlaeche[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsFlaeche[@gml:id = $id]) or
  exists(//BP_EingriffsBereich[@gml:id = $id]) or
  exists(//BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
  exists(//BP_FoerderungsFlaeche[@gml:id = $id]) or
  exists(//BP_FreiFlaeche[@gml:id = $id]) or
  exists(//BP_GebaeudeFlaeche[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
  exists(//BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
  exists(//BP_SpezielleBauweise[@gml:id = $id]) or
  exists(//BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
  exists(//BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
  exists(//BP_Veraenderungssperre[@gml:id = $id]) or
  exists(//BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsMass[@gml:id = $id]) or
  exists(//BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//BP_AusgleichsMassnahme[@gml:id = $id]) or
  exists(//BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
  exists(//BP_GenerischesObjekt[@gml:id = $id]) or
  exists(//BP_HoehenMass[@gml:id = $id]) or
  exists(//BP_Immissionsschutz[@gml:id = $id]) or
  exists(//BP_Landwirtschaft[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
  exists(//BP_Strassenkoerper[@gml:id = $id]) or
  exists(//BP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//BP_VerEntsorgung[@gml:id = $id]) or
  exists(//BP_Wegerecht[@gml:id = $id]) or
  exists(//BP_BauGrenze[@gml:id = $id]) or
  exists(//BP_BauLinie[@gml:id = $id]) or
  exists(//BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtsbereichLinie[@gml:id = $id]) or
  exists(//BP_FirstRichtungsLinie[@gml:id = $id]) or
  exists(//BP_NutzungsartenGrenze[@gml:id = $id]) or
  exists(//BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtPunkt[@gml:id = $id]) or

  exists(//FP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//FP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//FP_BebauungsFlaeche[@gml:id = $id]) or
  exists(//FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//FP_WaldFlaeche[@gml:id = $id]) or
  exists(//FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
  exists(//FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
  exists(//FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
  exists(//FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
  exists(//FP_VorbehalteFlaeche[@gml:id = $id]) or
  exists(//FP_Abgrabung[@gml:id = $id]) or
  exists(//FP_AnpassungKlimawandel[@gml:id = $id]) or
  exists(//FP_Aufschuettung[@gml:id = $id]) or
  exists(//FP_Bodenschaetze[@gml:id = $id]) or
  exists(//FP_Gemeinbedarf[@gml:id = $id]) or
  exists(//FP_GenerischesObjekt[@gml:id = $id]) or
  exists(//FP_Gewaesser[@gml:id = $id]) or
  exists(//FP_Gruen[@gml:id = $id]) or
  exists(//FP_Kennzeichnung[@gml:id = $id]) or
  exists(//FP_PrivilegiertesVorhaben[@gml:id = $id]) or
  exists(//FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//FP_SpielSportanlage[@gml:id = $id]) or
  exists(//FP_Strassenverkehr[@gml:id = $id]) or
  exists(//FP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//FP_VerEntsorgung[@gml:id = $id]) or
  exists(//FP_Wasserwirtschaft[@gml:id = $id]) or

  exists(//LP_AllgGruenflaeche[@gml:id = $id]) or
  exists(//LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
  exists(//LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//LP_Ausgleich[@gml:id = $id]) or
  exists(//LP_Biotopverbundflaeche[@gml:id = $id]) or
  exists(//LP_Bodenschutzrecht[@gml:id = $id]) or
  exists(//LP_ErholungFreizeit[@gml:id = $id]) or
  exists(//LP_Forstrecht[@gml:id = $id]) or
  exists(//LP_GenerischesObjekt[@gml:id = $id]) or
  exists(//LP_Landschaftsbild[@gml:id = $id]) or
  exists(//LP_NutzungsAusschluss[@gml:id = $id]) or
  exists(//LP_NutzungserfordernisRegelung[@gml:id = $id]) or
  exists(//LP_PlanerischeVertiefung[@gml:id = $id]) or
  exists(//LP_SchutzobjektInternatRecht[@gml:id = $id]) or
  exists(//LP_SchutzobjektLandesrecht[@gml:id = $id]) or
  exists(//LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//LP_SonstigesRecht[@gml:id = $id]) or
  exists(//LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
  exists(//LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
  exists(//LP_WasserrechtSonstige[@gml:id = $id]) or
  exists(//LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
  exists(//LP_Zwischennutzung[@gml:id = $id]) or
  exists(//LP_Abgrenzung[@gml:id = $id]) or

  exists(//RP_Achse[@gml:id = $id]) or
  exists(//RP_Energieversorgung[@gml:id = $id]) or
  exists(//RP_Entsorgung[@gml:id = $id]) or
  exists(//RP_Freiraum[@gml:id = $id]) or
  exists(//RP_Funktionszuweisung[@gml:id = $id]) or
  exists(//RP_GenerischesObjekt[@gml:id = $id]) or
  exists(//RP_Grenze[@gml:id = $id]) or
  exists(//RP_Kommunikation[@gml:id = $id]) or
  exists(//RP_LaermschutzBauschutz[@gml:id = $id]) or
  exists(//RP_Planungsraum[@gml:id = $id]) or
  exists(//RP_Raumkategorie[@gml:id = $id]) or
  exists(//RP_Siedlung[@gml:id = $id]) or
  exists(//RP_Einzelhandel[@gml:id = $id]) or
  exists(//RP_IndustrieGewerbe[@gml:id = $id]) or
  exists(//RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
  exists(//RP_WohnenSiedlung[@gml:id = $id]) or
  exists(//RP_SonstigeInfrastruktur[@gml:id = $id]) or
  exists(//RP_SozialeInfrastruktur[@gml:id = $id]) or
  exists(//RP_Sperrgebiet[@gml:id = $id]) or
  exists(//RP_Verkehr[@gml:id = $id]) or
  exists(//RP_Luftverkehr[@gml:id = $id]) or
  exists(//RP_Schienenverkehr[@gml:id = $id]) or
  exists(//RP_SonstVerkehr[@gml:id = $id]) or
  exists(//RP_Strassenverkehr[@gml:id = $id]) or
  exists(//RP_Wasserverkehr[@gml:id = $id]) or
  exists(//RP_Wasserwirtschaft[@gml:id = $id]) or
  exists(//RP_ZentralerOrt[@gml:id = $id]) or

  exists(//SO_Objekt[@gml:id = $id]) or
  exists(//SO_Gebiet[@gml:id = $id]) or
  exists(//SO_Bodenschutzrecht[@gml:id = $id]) or
  exists(//SO_Denkmalschutzrecht[@gml:id = $id]) or
  exists(//SO_Forstrecht[@gml:id = $id]) or
  exists(//SO_Luftverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Schienenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietWasserrecht[@gml:id = $id]) or
  exists(//SO_SonstigesRecht[@gml:id = $id]) or
  exists(//SO_Strassenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Wasserrecht[@gml:id = $id]) or
  exists(//SO_Grenze[@gml:id = $id])
)
and
(
  every $h in //RP_Bereich/planinhalt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//BP_AbgrabungsFlaeche[@gml:id = $id]) or
  exists(//BP_AufschuettungsFlaeche[@gml:id = $id]) or
  exists(//BP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
  exists(//BP_BodenschaetzeFlaeche[@gml:id = $id]) or
  exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//BP_KennzeichnungsFlaeche[@gml:id = $id]) or
  exists(//BP_RekultivierungsFlaeche[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsFlaeche[@gml:id = $id]) or
  exists(//BP_EingriffsBereich[@gml:id = $id]) or
  exists(//BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
  exists(//BP_FoerderungsFlaeche[@gml:id = $id]) or
  exists(//BP_FreiFlaeche[@gml:id = $id]) or
  exists(//BP_GebaeudeFlaeche[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
  exists(//BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
  exists(//BP_SpezielleBauweise[@gml:id = $id]) or
  exists(//BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
  exists(//BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
  exists(//BP_Veraenderungssperre[@gml:id = $id]) or
  exists(//BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsMass[@gml:id = $id]) or
  exists(//BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//BP_AusgleichsMassnahme[@gml:id = $id]) or
  exists(//BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
  exists(//BP_GenerischesObjekt[@gml:id = $id]) or
  exists(//BP_HoehenMass[@gml:id = $id]) or
  exists(//BP_Immissionsschutz[@gml:id = $id]) or
  exists(//BP_Landwirtschaft[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
  exists(//BP_Strassenkoerper[@gml:id = $id]) or
  exists(//BP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//BP_VerEntsorgung[@gml:id = $id]) or
  exists(//BP_Wegerecht[@gml:id = $id]) or
  exists(//BP_BauGrenze[@gml:id = $id]) or
  exists(//BP_BauLinie[@gml:id = $id]) or
  exists(//BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtsbereichLinie[@gml:id = $id]) or
  exists(//BP_FirstRichtungsLinie[@gml:id = $id]) or
  exists(//BP_NutzungsartenGrenze[@gml:id = $id]) or
  exists(//BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtPunkt[@gml:id = $id]) or

  exists(//FP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//FP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//FP_BebauungsFlaeche[@gml:id = $id]) or
  exists(//FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//FP_WaldFlaeche[@gml:id = $id]) or
  exists(//FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
  exists(//FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
  exists(//FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
  exists(//FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
  exists(//FP_VorbehalteFlaeche[@gml:id = $id]) or
  exists(//FP_Abgrabung[@gml:id = $id]) or
  exists(//FP_AnpassungKlimawandel[@gml:id = $id]) or
  exists(//FP_Aufschuettung[@gml:id = $id]) or
  exists(//FP_Bodenschaetze[@gml:id = $id]) or
  exists(//FP_Gemeinbedarf[@gml:id = $id]) or
  exists(//FP_GenerischesObjekt[@gml:id = $id]) or
  exists(//FP_Gewaesser[@gml:id = $id]) or
  exists(//FP_Gruen[@gml:id = $id]) or
  exists(//FP_Kennzeichnung[@gml:id = $id]) or
  exists(//FP_PrivilegiertesVorhaben[@gml:id = $id]) or
  exists(//FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//FP_SpielSportanlage[@gml:id = $id]) or
  exists(//FP_Strassenverkehr[@gml:id = $id]) or
  exists(//FP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//FP_VerEntsorgung[@gml:id = $id]) or
  exists(//FP_Wasserwirtschaft[@gml:id = $id]) or

  exists(//LP_AllgGruenflaeche[@gml:id = $id]) or
  exists(//LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
  exists(//LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//LP_Ausgleich[@gml:id = $id]) or
  exists(//LP_Biotopverbundflaeche[@gml:id = $id]) or
  exists(//LP_Bodenschutzrecht[@gml:id = $id]) or
  exists(//LP_ErholungFreizeit[@gml:id = $id]) or
  exists(//LP_Forstrecht[@gml:id = $id]) or
  exists(//LP_GenerischesObjekt[@gml:id = $id]) or
  exists(//LP_Landschaftsbild[@gml:id = $id]) or
  exists(//LP_NutzungsAusschluss[@gml:id = $id]) or
  exists(//LP_NutzungserfordernisRegelung[@gml:id = $id]) or
  exists(//LP_PlanerischeVertiefung[@gml:id = $id]) or
  exists(//LP_SchutzobjektInternatRecht[@gml:id = $id]) or
  exists(//LP_SchutzobjektLandesrecht[@gml:id = $id]) or
  exists(//LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//LP_SonstigesRecht[@gml:id = $id]) or
  exists(//LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
  exists(//LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
  exists(//LP_WasserrechtSonstige[@gml:id = $id]) or
  exists(//LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
  exists(//LP_Zwischennutzung[@gml:id = $id]) or
  exists(//LP_Abgrenzung[@gml:id = $id]) or

  exists(//RP_Achse[@gml:id = $id]) or
  exists(//RP_Energieversorgung[@gml:id = $id]) or
  exists(//RP_Entsorgung[@gml:id = $id]) or
  exists(//RP_Freiraum[@gml:id = $id]) or
  exists(//RP_Funktionszuweisung[@gml:id = $id]) or
  exists(//RP_GenerischesObjekt[@gml:id = $id]) or
  exists(//RP_Grenze[@gml:id = $id]) or
  exists(//RP_Kommunikation[@gml:id = $id]) or
  exists(//RP_LaermschutzBauschutz[@gml:id = $id]) or
  exists(//RP_Planungsraum[@gml:id = $id]) or
  exists(//RP_Raumkategorie[@gml:id = $id]) or
  exists(//RP_Siedlung[@gml:id = $id]) or
  exists(//RP_Einzelhandel[@gml:id = $id]) or
  exists(//RP_IndustrieGewerbe[@gml:id = $id]) or
  exists(//RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
  exists(//RP_WohnenSiedlung[@gml:id = $id]) or
  exists(//RP_SonstigeInfrastruktur[@gml:id = $id]) or
  exists(//RP_SozialeInfrastruktur[@gml:id = $id]) or
  exists(//RP_Sperrgebiet[@gml:id = $id]) or
  exists(//RP_Verkehr[@gml:id = $id]) or
  exists(//RP_Luftverkehr[@gml:id = $id]) or
  exists(//RP_Schienenverkehr[@gml:id = $id]) or
  exists(//RP_SonstVerkehr[@gml:id = $id]) or
  exists(//RP_Strassenverkehr[@gml:id = $id]) or
  exists(//RP_Wasserverkehr[@gml:id = $id]) or
  exists(//RP_Wasserwirtschaft[@gml:id = $id]) or
  exists(//RP_ZentralerOrt[@gml:id = $id]) or

  exists(//SO_Objekt[@gml:id = $id]) or
  exists(//SO_Gebiet[@gml:id = $id]) or
  exists(//SO_Bodenschutzrecht[@gml:id = $id]) or
  exists(//SO_Denkmalschutzrecht[@gml:id = $id]) or
  exists(//SO_Forstrecht[@gml:id = $id]) or
  exists(//SO_Luftverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Schienenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietWasserrecht[@gml:id = $id]) or
  exists(//SO_SonstigesRecht[@gml:id = $id]) or
  exists(//SO_Strassenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Wasserrecht[@gml:id = $id]) or
  exists(//SO_Grenze[@gml:id = $id])
)
and
(
  every $h in //SO_Bereich/planinhalt/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//BP_AbgrabungsFlaeche[@gml:id = $id]) or
  exists(//BP_AufschuettungsFlaeche[@gml:id = $id]) or
  exists(//BP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
  exists(//BP_BodenschaetzeFlaeche[@gml:id = $id]) or
  exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//BP_KennzeichnungsFlaeche[@gml:id = $id]) or
  exists(//BP_RekultivierungsFlaeche[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsFlaeche[@gml:id = $id]) or
  exists(//BP_EingriffsBereich[@gml:id = $id]) or
  exists(//BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
  exists(//BP_FoerderungsFlaeche[@gml:id = $id]) or
  exists(//BP_FreiFlaeche[@gml:id = $id]) or
  exists(//BP_GebaeudeFlaeche[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
  exists(//BP_NebenanlagenFlaeche[@gml:id = $id]) or
  exists(//BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
  exists(//BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
  exists(//BP_SpezielleBauweise[@gml:id = $id]) or
  exists(//BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
  exists(//BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
  exists(//BP_Veraenderungssperre[@gml:id = $id]) or
  exists(//BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//BP_AbstandsMass[@gml:id = $id]) or
  exists(//BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//BP_AusgleichsMassnahme[@gml:id = $id]) or
  exists(//BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
  exists(//BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
  exists(//BP_GenerischesObjekt[@gml:id = $id]) or
  exists(//BP_HoehenMass[@gml:id = $id]) or
  exists(//BP_Immissionsschutz[@gml:id = $id]) or
  exists(//BP_Landwirtschaft[@gml:id = $id]) or
  exists(//BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
  exists(//BP_Strassenkoerper[@gml:id = $id]) or
  exists(//BP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//BP_VerEntsorgung[@gml:id = $id]) or
  exists(//BP_Wegerecht[@gml:id = $id]) or
  exists(//BP_BauGrenze[@gml:id = $id]) or
  exists(//BP_BauLinie[@gml:id = $id]) or
  exists(//BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtsbereichLinie[@gml:id = $id]) or
  exists(//BP_FirstRichtungsLinie[@gml:id = $id]) or
  exists(//BP_NutzungsartenGrenze[@gml:id = $id]) or
  exists(//BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
  exists(//BP_EinfahrtPunkt[@gml:id = $id]) or

  exists(//FP_AusgleichsFlaeche[@gml:id = $id]) or
  exists(//FP_Flaechenschlussobjekt[@gml:id = $id]) or
  exists(//FP_BebauungsFlaeche[@gml:id = $id]) or
  exists(//FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
  exists(//FP_WaldFlaeche[@gml:id = $id]) or
  exists(//FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
  exists(//FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
  exists(//FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
  exists(//FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
  exists(//FP_VorbehalteFlaeche[@gml:id = $id]) or
  exists(//FP_Abgrabung[@gml:id = $id]) or
  exists(//FP_AnpassungKlimawandel[@gml:id = $id]) or
  exists(//FP_Aufschuettung[@gml:id = $id]) or
  exists(//FP_Bodenschaetze[@gml:id = $id]) or
  exists(//FP_Gemeinbedarf[@gml:id = $id]) or
  exists(//FP_GenerischesObjekt[@gml:id = $id]) or
  exists(//FP_Gewaesser[@gml:id = $id]) or
  exists(//FP_Gruen[@gml:id = $id]) or
  exists(//FP_Kennzeichnung[@gml:id = $id]) or
  exists(//FP_PrivilegiertesVorhaben[@gml:id = $id]) or
  exists(//FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//FP_SpielSportanlage[@gml:id = $id]) or
  exists(//FP_Strassenverkehr[@gml:id = $id]) or
  exists(//FP_UnverbindlicheVormerkung[@gml:id = $id]) or
  exists(//FP_VerEntsorgung[@gml:id = $id]) or
  exists(//FP_Wasserwirtschaft[@gml:id = $id]) or

  exists(//LP_AllgGruenflaeche[@gml:id = $id]) or
  exists(//LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
  exists(//LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
  exists(//LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
  exists(//LP_Ausgleich[@gml:id = $id]) or
  exists(//LP_Biotopverbundflaeche[@gml:id = $id]) or
  exists(//LP_Bodenschutzrecht[@gml:id = $id]) or
  exists(//LP_ErholungFreizeit[@gml:id = $id]) or
  exists(//LP_Forstrecht[@gml:id = $id]) or
  exists(//LP_GenerischesObjekt[@gml:id = $id]) or
  exists(//LP_Landschaftsbild[@gml:id = $id]) or
  exists(//LP_NutzungsAusschluss[@gml:id = $id]) or
  exists(//LP_NutzungserfordernisRegelung[@gml:id = $id]) or
  exists(//LP_PlanerischeVertiefung[@gml:id = $id]) or
  exists(//LP_SchutzobjektInternatRecht[@gml:id = $id]) or
  exists(//LP_SchutzobjektLandesrecht[@gml:id = $id]) or
  exists(//LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
  exists(//LP_SonstigesRecht[@gml:id = $id]) or
  exists(//LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
  exists(//LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
  exists(//LP_WasserrechtSonstige[@gml:id = $id]) or
  exists(//LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
  exists(//LP_Zwischennutzung[@gml:id = $id]) or
  exists(//LP_Abgrenzung[@gml:id = $id]) or

  exists(//RP_Achse[@gml:id = $id]) or
  exists(//RP_Energieversorgung[@gml:id = $id]) or
  exists(//RP_Entsorgung[@gml:id = $id]) or
  exists(//RP_Freiraum[@gml:id = $id]) or
  exists(//RP_Funktionszuweisung[@gml:id = $id]) or
  exists(//RP_GenerischesObjekt[@gml:id = $id]) or
  exists(//RP_Grenze[@gml:id = $id]) or
  exists(//RP_Kommunikation[@gml:id = $id]) or
  exists(//RP_LaermschutzBauschutz[@gml:id = $id]) or
  exists(//RP_Planungsraum[@gml:id = $id]) or
  exists(//RP_Raumkategorie[@gml:id = $id]) or
  exists(//RP_Siedlung[@gml:id = $id]) or
  exists(//RP_Einzelhandel[@gml:id = $id]) or
  exists(//RP_IndustrieGewerbe[@gml:id = $id]) or
  exists(//RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
  exists(//RP_WohnenSiedlung[@gml:id = $id]) or
  exists(//RP_SonstigeInfrastruktur[@gml:id = $id]) or
  exists(//RP_SozialeInfrastruktur[@gml:id = $id]) or
  exists(//RP_Sperrgebiet[@gml:id = $id]) or
  exists(//RP_Verkehr[@gml:id = $id]) or
  exists(//RP_Luftverkehr[@gml:id = $id]) or
  exists(//RP_Schienenverkehr[@gml:id = $id]) or
  exists(//RP_SonstVerkehr[@gml:id = $id]) or
  exists(//RP_Strassenverkehr[@gml:id = $id]) or
  exists(//RP_Wasserverkehr[@gml:id = $id]) or
  exists(//RP_Wasserwirtschaft[@gml:id = $id]) or
  exists(//RP_ZentralerOrt[@gml:id = $id]) or

  exists(//SO_Objekt[@gml:id = $id]) or
  exists(//SO_Gebiet[@gml:id = $id]) or
  exists(//SO_Bodenschutzrecht[@gml:id = $id]) or
  exists(//SO_Denkmalschutzrecht[@gml:id = $id]) or
  exists(//SO_Forstrecht[@gml:id = $id]) or
  exists(//SO_Luftverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Schienenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
  exists(//SO_SchutzgebietWasserrecht[@gml:id = $id]) or
  exists(//SO_SonstigesRecht[@gml:id = $id]) or
  exists(//SO_Strassenverkehrsrecht[@gml:id = $id]) or
  exists(//SO_Wasserrecht[@gml:id = $id]) or
  exists(//SO_Grenze[@gml:id = $id])
)