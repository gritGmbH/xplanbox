declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_GruenFlaeche satisfies
  (
    if (
      exists($h/besondereZweckbestimmung[1]) and
      not(exists($h/besondereZweckbestimmung[2]))
    )
    then (
      not(exists($h/weitereBesondZweckbestimmung1)) and
      not(exists($h/weitereBesondZweckbestimmung2)) and
      not(exists($h/weitereBesondZweckbestimmung3)) and
      not(exists($h/weitereBesondZweckbestimmung4))
    )
    else boolean('false')
  )
)
and
(
  every $h in //BP_GruenFlaeche[besondereZweckbestimmung[2]] satisfies
  not(exists($h/weitereBesondZweckbestimmung1)) and
  not(exists($h/weitereBesondZweckbestimmung2)) and
  not(exists($h/weitereBesondZweckbestimmung3)) and
  not(exists($h/weitereBesondZweckbestimmung4))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (
    if (
      exists($h/weitereBesondZweckbestimmung1) and
      exists($h/weitereBesondZweckbestimmung2) and
      exists($h/weitereBesondZweckbestimmung3) and
      exists($h/weitereBesondZweckbestimmung4)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondZweckbestimmung1) and
      exists($h/weitereBesondZweckbestimmung2) and
      exists($h/weitereBesondZweckbestimmung3)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondZweckbestimmung1) and
      exists($h/weitereBesondZweckbestimmung2)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondZweckbestimmung1)
    )
    then (
      boolean('true')
    )
    else boolean('false')
  )
)