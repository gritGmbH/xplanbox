declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
every $h in //FP_AusgleichsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_KeineZentrAbwasserBeseitigungFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_VorbehalteFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_BebauungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_LandwirtschaftsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_WaldFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_NutzungsbeschraenkungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_TextlicheDarstellungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)
and
(
every $h in //FP_ZentralerVersorgungsbereich[ebene != '0'] satisfies
$h/flaechenschluss = 'false'
)