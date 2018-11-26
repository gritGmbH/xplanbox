declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
every $h in //FP_BebauungsFlaeche[ebene/text() = '0' or not(ebene)] satisfies
  $h/flaechenschluss='true'
)
and
(
every $h in //FP_LandwirtschaftsFlaeche[ebene/text() = '0' or not(ebene)] satisfies
  $h/flaechenschluss='true'
)
and
(
every $h in //FP_WaldFlaeche[ebene/text() = '0' or not(ebene)] satisfies
  $h/flaechenschluss='true'
)