declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //RP_Achse[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Energieversorgung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Entsorgung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Freiraum[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Bodenschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Erholung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_ErneuerbareEnergie[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Forstwirtschaft[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Gewaesser[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_GruenzugGruenzaesur[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Hochwasserschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Klimaschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Kulturlandschaft[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Landwirtschaft[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_NaturLandschaft[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_NaturschutzrechtlichesSchutzgebiet[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_RadwegWanderweg[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Rohstoff[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_SonstigerFreiraumschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Sportanlage[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Wasserschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Funktionszuweisung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_GenerischesObjekt[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Grenze[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Kommunikation[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_LaermschutzBauschutz[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Planungsraum[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Raumkategorie[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Siedlung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Einzelhandel[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_IndustrieGewerbe[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_SonstigerSiedlungsbereich[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_WohnenSiedlung[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_SonstigeInfrastruktur[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_SozialeInfrastruktur[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Sperrgebiet[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Verkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Luftverkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Schienenverkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_SonstVerkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Strassenverkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Wasserverkehr[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_Wasserwirtschaft[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)
and
(
  every $h in //RP_ZentralerOrt[ebene != 0] satisfies
  $h/flaechenschluss = 'false'
)