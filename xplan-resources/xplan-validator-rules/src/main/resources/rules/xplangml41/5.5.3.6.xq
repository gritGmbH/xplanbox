declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //FP_Gruen[detaillierteZweckbestimmung] satisfies
  (count($h/detaillierteZweckbestimmung) = count($h/zweckbestimmung))
)
and
(
  every $h in //FP_Gruen[weitereDetailZweckbestimmung1] satisfies
  exists($h/weitereZweckbestimmung1)
)
and
(
  every $h in //FP_Gruen[weitereDetailZweckbestimmung2] satisfies
  exists($h/weitereZweckbestimmung2)
)
and
(
  every $h in //FP_Gruen[weitereDetailZweckbestimmung3] satisfies
  exists($h/weitereZweckbestimmung2)
)
and
(
  every $h in //FP_Gruen[weitereDetailZweckbestimmung4] satisfies
  exists($h/weitereZweckbestimmung2)
)