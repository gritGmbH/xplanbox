declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //FP_VerEntsorgung satisfies
  (
    if (
      exists($h/zweckbestimmung[1]) and
      not(exists($h/zweckbestimmung[2]))
    )
    then (
      not(exists($h/weitereZweckbestimmung1)) and
      not(exists($h/weitereZweckbestimmung2)) and
      not(exists($h/weitereZweckbestimmung3))
    )
    else boolean('false')
  )
)
and
(
  every $h in //FP_VerEntsorgung satisfies
  (
    if (
      exists($h/zweckbestimmung[1]) and
      exists($h/zweckbestimmung[2])
    )
    then (
      not(exists($h/weitereZweckbestimmung1)) and
      not(exists($h/weitereZweckbestimmung2)) and
      not(exists($h/weitereZweckbestimmung3))
    )
    else boolean('false')
  )
)
and
(
  every $h in //FP_VerEntsorgung satisfies
  (
    if (
      exists($h/weitereZweckbestimmung1) and
      exists($h/weitereZweckbestimmung2) and
      exists($h/weitereZweckbestimmung3)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereZweckbestimmung1) and
      exists($h/weitereZweckbestimmung2)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereZweckbestimmung1)
    )
    then (
      boolean('true')
    )
    else boolean('false')
  )
)