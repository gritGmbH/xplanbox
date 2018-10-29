declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //RP_Achse/refTextinhalt/@xlink:href satisfies
exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Energieversorgung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Entsorgung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Freiraum/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Bodenschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Erholung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_ErneuerbareEnergie/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Forstwirtschaft/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Gewaesser/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_GruenzugGruenzaesur/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Hochwasserschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Klimaschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Kulturlandschaft/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Landwirtschaft/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_NaturLandschaft/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_NaturschutzrechtlichesSchutzgebiet/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_RadwegWanderweg/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Rohstoff/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_SonstigerFreiraumschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Sportanlage/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Wasserschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Funktionszuweisung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_GenerischesObjekt/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Grenze/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Kommunikation/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_LaermschutzBauschutz/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Planungsraum/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Raumkategorie/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Siedlung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Einzelhandel/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_IndustrieGewerbe/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_SonstigerSiedlungsbereich/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_WohnenSiedlung/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_SonstigeInfrastruktur/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_SozialeInfrastruktur/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Sperrgebiet/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Verkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Luftverkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Schienenverkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_SonstVerkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Strassenverkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Wasserverkehr/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Wasserwirtschaft/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_ZentralerOrt/refTextinhalt/@xlink:href satisfies
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)])
)