declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Landwirtschaft satisfies
  (
    if (
      exists($h/detaillierteZweckbestimmung[1]) and
      not(exists($h/detaillierteZweckbestimmung[2]))
    )
    then (
      not(exists($h/weitereDetailZweckbestimmung1)) and
      not(exists($h/weitereDetailZweckbestimmung2)) and
      not(exists($h/weitereDetailZweckbestimmung3))
    )
    else boolean('false')
  )
)
and
(
  every $h in //BP_Landwirtschaft[detaillierteZweckbestimmung[2]] satisfies
  not(exists($h/weitereDetailZweckbestimmung1)) and
  not(exists($h/weitereDetailZweckbestimmung2)) and
  not(exists($h/weitereDetailZweckbestimmung3))
)
and
(
  every $h in //BP_Landwirtschaft satisfies
  (
    if (
      exists($h/weitereDetailZweckbestimmung1) and
      exists($h/weitereDetailZweckbestimmung2) and
      exists($h/weitereDetailZweckbestimmung3)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereDetailZweckbestimmung1) and
      exists($h/weitereDetailZweckbestimmung2)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereDetailZweckbestimmung1)
    )
    then (
      boolean('true')
    )
    else boolean('false')
  )
)