declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (
    if (
      exists($h/besondereZweckbestimmung[1]) and
      not(exists($h/besondereZweckbestimmung[2]))
    )
    then (
      not(exists($h/weitereBesondereZweckbestimmung1)) and
      not(exists($h/weitereBesondereZweckbestimmung2)) and
      not(exists($h/weitereBesondereZweckbestimmung3)) and
      not(exists($h/weitereBesondereZweckbestimmung4))
    )
    else boolean('false')
  )
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche[besondereZweckbestimmung[2]] satisfies
  not(exists($h/weitereBesondereZweckbestimmung1)) and
  not(exists($h/weitereBesondereZweckbestimmung2)) and
  not(exists($h/weitereBesondereZweckbestimmung3)) and
  not(exists($h/weitereBesondereZweckbestimmung4))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (
    if (
      exists($h/weitereBesondereZweckbestimmung1) and
      exists($h/weitereBesondereZweckbestimmung2) and
      exists($h/weitereBesondereZweckbestimmung3) and
      exists($h/weitereBesondereZweckbestimmung4)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondereZweckbestimmung1) and
      exists($h/weitereBesondereZweckbestimmung2) and
      exists($h/weitereBesondereZweckbestimmung3)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondereZweckbestimmung1) and
      exists($h/weitereBesondereZweckbestimmung2)
    )
    then (
      boolean('true')
    )
    else if (
      exists($h/weitereBesondereZweckbestimmung1)
    )
    then (
      boolean('true')
    )
    else boolean('false')
  )
)