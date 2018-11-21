declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //FP_NutzungsbeschraenkungsFlaeche satisfies
  $h/flaechenschluss/text() ='false'
)
and
(
  every $h in //FP_TextlicheDarstellungsFlaeche satisfies
  $h/flaechenschluss/text() ='false'
)
and
(
  every $h in //FP_ZentralerVersorgungsbereich satisfies
  $h/flaechenschluss/text() ='false'
)