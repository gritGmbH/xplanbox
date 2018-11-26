declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //FP_SpielSportanlage satisfies
  (
    if (
      exists($h/detaillierteZweckbestimmung[1]) and
      not(exists($h/detaillierteZweckbestimmung[2]))
    )
    then (
      not(exists($h/weitereDetailZweckbestimmung1)) and
      not(exists($h/weitereDetailZweckbestimmung2)) and
      not(exists($h/weitereDetailZweckbestimmung3)) and
      not(exists($h/weitereDetailZweckbestimmung4)) and
      not(exists($h/weitereDetailZweckbestimmung5))
    )
    else boolean('false')
  )
)