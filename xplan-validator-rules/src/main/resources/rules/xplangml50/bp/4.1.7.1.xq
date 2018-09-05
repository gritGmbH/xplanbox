declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GewaesserFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GruenFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_KleintierhaltungFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_WaldFlaeche[not(exists(ebene)) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)