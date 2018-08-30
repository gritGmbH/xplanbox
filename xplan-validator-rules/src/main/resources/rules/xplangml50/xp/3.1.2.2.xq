declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

let $ids := (
  for $h in //XP_Bereich/planinhalt/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
exists(//BP_Objekt[@gml:id = $id]) or
exists(//xplan:BP_Flaechenobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbgrabungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_AufschuettungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_AusgleichsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_BesondererNutzungszweckFlaeche[@gml:id = $id]) or
exists(//xplan:BP_BodenschaetzeFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Flaechenschlussobjekt[@gml:id = $id]) or
exists(//xplan:BP_KennzeichnungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_RekultivierungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_SchutzPflegeEntwicklungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Ueberlagerungsobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbstandsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_EingriffsBereich[@gml:id = $id]) or
exists(//xplan:BP_ErhaltungsBereichFlaeche[@gml:id = $id]) or
exists(//xplan:BP_FoerderungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_FreiFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GebaeudeFlaeche[@gml:id = $id]) or
exists(//xplan:BP_GemeinschaftsanlagenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_NebenanlagenAusschlussFlaeche[@gml:id = $id]) or
exists(//xplan:BP_NebenanlagenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_PersGruppenBestimmteFlaeche[@gml:id = $id]) or
exists(//xplan:BP_RegelungVergnuegungsstaetten[@gml:id = $id]) or
exists(//xplan:BP_SpezielleBauweise[@gml:id = $id]) or
exists(//xplan:BP_TechnischeMassnahmenFlaeche[@gml:id = $id]) or
exists(//xplan:BP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_UeberbaubareGrundstuecksFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Veraenderungssperre[@gml:id = $id]) or
exists(//xplan:BP_WasserwirtschaftsFlaeche[@gml:id = $id]) or
exists(//xplan:BP_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:BP_AbstandsMass[@gml:id = $id]) or
exists(//xplan:BP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
exists(//xplan:BP_AusgleichsMassnahme[@gml:id = $id]) or
exists(//xplan:BP_FestsetzungNachLandesrecht[@gml:id = $id]) or
exists(//xplan:BP_GemeinschaftsanlagenZuordnung[@gml:id = $id]) or
exists(//xplan:BP_GenerischesObjekt[@gml:id = $id]) or
exists(//xplan:BP_HoehenMass[@gml:id = $id]) or
exists(//xplan:BP_Immissionsschutz[@gml:id = $id]) or
exists(//xplan:BP_Landwirtschaft[@gml:id = $id]) or
exists(//xplan:BP_SchutzPflegeEntwicklungsMassnahme[@gml:id = $id]) or
exists(//xplan:BP_Strassenkoerper[@gml:id = $id]) or
exists(//xplan:BP_UnverbindlicheVormerkung[@gml:id = $id]) or
exists(//xplan:BP_VerEntsorgung[@gml:id = $id]) or
exists(//xplan:BP_Wegerecht[@gml:id = $id]) or
exists(//xplan:BP_Linienobjekt[@gml:id = $id]) or
exists(//xplan:BP_BauGrenze[@gml:id = $id]) or
exists(//xplan:BP_BauLinie[@gml:id = $id]) or
exists(//xplan:BP_BereichOhneEinAusfahrtLinie[@gml:id = $id]) or
exists(//xplan:BP_EinfahrtsbereichLinie[@gml:id = $id]) or
exists(//xplan:BP_FirstRichtungsLinie[@gml:id = $id]) or
exists(//xplan:BP_NutzungsartenGrenze[@gml:id = $id]) or
exists(//xplan:BP_StrassenbegrenzungsLinie[@gml:id = $id]) or
exists(//xplan:BP_Punktobjekt[@gml:id = $id]) or
exists(//xplan:BP_EinfahrtPunkt[@gml:id = $id]) or

exists(//FP_Objekt[@gml:id = $id]) or
exists(//xplan:FP_Flaechenobjekt[@gml:id = $id]) or
exists(//xplan:FP_AusgleichsFlaeche[@gml:id = $id]) or
exists(//xplan:FP_Flaechenschlussobjekt[@gml:id = $id]) or
exists(//xplan:FP_BebauungsFlaeche[@gml:id = $id]) or
exists(//xplan:FP_LandwirtschaftsFlaeche[@gml:id = $id]) or
exists(//xplan:FP_WaldFlaeche[@gml:id = $id]) or
exists(//xplan:FP_KeineZentrAbwasserBeseitigungFlaeche[@gml:id = $id]) or
exists(//xplan:FP_Ueberlagerungsobjekt[@gml:id = $id]) or
exists(//xplan:FP_NutzungsbeschraenkungsFlaeche[@gml:id = $id]) or
exists(//xplan:FP_TextlicheDarstellungsFlaeche[@gml:id = $id]) or
exists(//xplan:FP_ZentralerVersorgungsbereich[@gml:id = $id]) or
exists(//xplan:FP_VorbehalteFlaeche[@gml:id = $id]) or
exists(//xplan:FP_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:FP_Abgrabung[@gml:id = $id]) or
exists(//xplan:FP_AnpassungKlimawandel[@gml:id = $id]) or
exists(//xplan:FP_Aufschuettung[@gml:id = $id]) or
exists(//xplan:FP_Bodenschaetze[@gml:id = $id]) or
exists(//xplan:FP_Gemeinbedarf[@gml:id = $id]) or
exists(//xplan:FP_GenerischesObjekt[@gml:id = $id]) or
exists(//xplan:FP_Gewaesser[@gml:id = $id]) or
exists(//xplan:FP_Gruen[@gml:id = $id]) or
exists(//xplan:FP_Kennzeichnung[@gml:id = $id]) or
exists(//xplan:FP_PrivilegiertesVorhaben[@gml:id = $id]) or
exists(//xplan:FP_SchutzPflegeEntwicklung[@gml:id = $id]) or
exists(//xplan:FP_SpielSportanlage[@gml:id = $id]) or
exists(//xplan:FP_Strassenverkehr[@gml:id = $id]) or
exists(//xplan:FP_UnverbindlicheVormerkung[@gml:id = $id]) or
exists(//xplan:FP_VerEntsorgung[@gml:id = $id]) or
exists(//xplan:FP_Wasserwirtschaft[@gml:id = $id]) or
exists(//xplan:FP_Linienobjekt[@gml:id = $id]) or
exists(//xplan:FP_Punktobjekt[@gml:id = $id]) or


exists(//LP_Objekt[@gml:id = $id]) or
exists(//xplan:LP_Flaechenobjekt[@gml:id = $id]) or
exists(//xplan:LP_AllgGruenflaeche[@gml:id = $id]) or
exists(//xplan:LP_TextlicheFestsetzungsFlaeche[@gml:id = $id]) or
exists(//xplan:LP_ZuBegruenendeGrundstueckflaeche[@gml:id = $id]) or
exists(//xplan:LP_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:LP_AnpflanzungBindungErhaltung[@gml:id = $id]) or
exists(//xplan:LP_Ausgleich[@gml:id = $id]) or
exists(//xplan:LP_Biotopverbundflaeche[@gml:id = $id]) or
exists(//xplan:LP_Bodenschutzrecht[@gml:id = $id]) or
exists(//xplan:LP_ErholungFreizeit[@gml:id = $id]) or
exists(//xplan:LP_Forstrecht[@gml:id = $id]) or
exists(//xplan:LP_GenerischesObjekt[@gml:id = $id]) or
exists(//xplan:LP_Landschaftsbild[@gml:id = $id]) or
exists(//xplan:LP_NutzungsAusschluss[@gml:id = $id]) or
exists(//xplan:LP_NutzungserfordernisRegelung[@gml:id = $id]) or
exists(//xplan:LP_PlanerischeVertiefung[@gml:id = $id]) or
exists(//xplan:LP_SchutzobjektInternatRecht[@gml:id = $id]) or
exists(//xplan:LP_SchutzobjektLandesrecht[@gml:id = $id]) or
exists(//xplan:LP_SchutzPflegeEntwicklung[@gml:id = $id]) or
exists(//xplan:LP_SonstigesRecht[@gml:id = $id]) or
exists(//xplan:LP_WasserrechtGemeingebrEinschraenkungNaturschutz[@gml:id = $id]) or
exists(//xplan:LP_WasserrechtSchutzgebiet[@gml:id = $id]) or
exists(//xplan:LP_WasserrechtSonstige[@gml:id = $id]) or
exists(//xplan:LP_WasserrechtWirtschaftAbflussHochwSchutz[@gml:id = $id]) or
exists(//xplan:LP_Zwischennutzung[@gml:id = $id]) or
exists(//xplan:LP_Linienobjekt[@gml:id = $id]) or
exists(//xplan:LP_Abgrenzung[@gml:id = $id]) or
exists(//xplan:LP_Punktobjekt[@gml:id = $id]) or

exists(//RP_Objekt[@gml:id = $id]) or
exists(//xplan:RP_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:RP_Achse[@gml:id = $id]) or
exists(//xplan:RP_Energieversorgung[@gml:id = $id]) or
exists(//xplan:RP_Entsorgung[@gml:id = $id]) or
exists(//xplan:RP_Freiraum[@gml:id = $id]) or
exists(//xplan:RP_Funktionszuweisung[@gml:id = $id]) or
exists(//xplan:RP_GenerischesObjekt[@gml:id = $id]) or
exists(//xplan:RP_Grenze[@gml:id = $id]) or
exists(//xplan:RP_Kommunikation[@gml:id = $id]) or
exists(//xplan:RP_LaermschutzBauschutz[@gml:id = $id]) or
exists(//xplan:RP_Planungsraum[@gml:id = $id]) or
exists(//xplan:RP_Raumkategorie[@gml:id = $id]) or
exists(//xplan:RP_Siedlung[@gml:id = $id]) or
exists(//xplan:RP_Einzelhandel[@gml:id = $id]) or
exists(//xplan:RP_IndustrieGewerbe[@gml:id = $id]) or
exists(//xplan:RP_SonstigerSiedlungsbereich[@gml:id = $id]) or
exists(//xplan:RP_WohnenSiedlung[@gml:id = $id]) or
exists(//xplan:RP_SonstigeInfrastruktur[@gml:id = $id]) or
exists(//xplan:RP_SozialeInfrastruktur[@gml:id = $id]) or
exists(//xplan:RP_Sperrgebiet[@gml:id = $id]) or
exists(//xplan:RP_Verkehr[@gml:id = $id]) or
exists(//xplan:RP_Luftverkehr[@gml:id = $id]) or
exists(//xplan:RP_Schienenverkehr[@gml:id = $id]) or
exists(//xplan:RP_SonstVerkehr[@gml:id = $id]) or
exists(//xplan:RP_Strassenverkehr[@gml:id = $id]) or
exists(//xplan:RP_Wasserverkehr[@gml:id = $id]) or
exists(//xplan:RP_Wasserwirtschaft[@gml:id = $id]) or
exists(//xplan:RP_ZentralerOrt[@gml:id = $id]) or

exists(//SO_Objekt[@gml:id = $id]) or
exists(//xplan:SO_Flaechenobjekt[@gml:id = $id]) or
exists(//xplan:SO_Gebiet[@gml:id = $id]) or
exists(//xplan:SO_Geometrieobjekt[@gml:id = $id]) or
exists(//xplan:SO_Bodenschutzrecht[@gml:id = $id]) or
exists(//xplan:SO_Denkmalschutzrecht[@gml:id = $id]) or
exists(//xplan:SO_Forstrecht[@gml:id = $id]) or
exists(//xplan:SO_Luftverkehrsrecht[@gml:id = $id]) or
exists(//xplan:SO_Schienenverkehrsrecht[@gml:id = $id]) or
exists(//xplan:SO_SchutzgebietNaturschutzrecht[@gml:id = $id]) or
exists(//xplan:SO_SchutzgebietSonstigesRecht[@gml:id = $id]) or
exists(//xplan:SO_SchutzgebietWasserrecht[@gml:id = $id]) or
exists(//xplan:SO_SonstigesRecht[@gml:id = $id]) or
exists(//xplan:SO_Strassenverkehrsrecht[@gml:id = $id]) or
exists(//xplan:SO_Wasserrecht[@gml:id = $id]) or
exists(//xplan:SO_Linienobjekt[@gml:id = $id]) or
exists(//xplan:SO_Grenze[@gml:id = $id]) or
exists(//xplan:SO_Punktobjekt[@gml:id = $id])