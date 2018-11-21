declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GewaesserFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_GruenFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_KleintierhaltungFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)
and
(
  every $h in //BP_WaldFlaeche[not(ebene) or ebene = '0'] satisfies
  $h/flaechenschluss = 'true'
)