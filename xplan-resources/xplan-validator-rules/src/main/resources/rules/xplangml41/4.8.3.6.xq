declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_GruenFlaeche[detaillierteZweckbestimmung] satisfies
  (count($h/detaillierteZweckbestimmung) = count($h/zweckbestimmung)) or
  (count($h/detaillierteZweckbestimmung) = count($h/besondereZweckbestimmung))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (
    if (
      exists($h/weitereDetailZweckbestimmung1) and
      exists($h/weitereDetailZweckbestimmung2) and
      exists($h/weitereDetailZweckbestimmung3) and
      exists($h/weitereDetailZweckbestimmung4)
    )
    then (
      (
        exists($h/weitereZweckbestimmung1) and
        exists($h/weitereZweckbestimmung2) and
        exists($h/weitereZweckbestimmung3) and
        exists($h/weitereZweckbestimmung4)
      )
      or
      (
        exists($h/weitereBesondZweckbestimmung1) and
        exists($h/weitereBesondZweckbestimmung2) and
        exists($h/weitereBesondZweckbestimmung3) and
        exists($h/weitereBesondZweckbestimmung4)
      )
    )
    
    else if (
      exists($h/weitereDetailZweckbestimmung1) and
      exists($h/weitereDetailZweckbestimmung2) and
      exists($h/weitereDetailZweckbestimmung3)
    )
    then (
      (
        exists($h/weitereZweckbestimmung1) and
        exists($h/weitereZweckbestimmung2) and
        exists($h/weitereZweckbestimmung3)
      )
      or
      (
        exists($h/weitereBesondZweckbestimmung1) and
        exists($h/weitereBesondZweckbestimmung2) and
        exists($h/weitereBesondZweckbestimmung3)
      )
    )
    
    else if (
      exists($h/weitereDetailZweckbestimmung1) and
      exists($h/weitereDetailZweckbestimmung2)
    )
    then (
      (
        exists($h/weitereZweckbestimmung1) and
        exists($h/weitereZweckbestimmung2)
      )
      or
      (
        exists($h/weitereBesondZweckbestimmung1) and
        exists($h/weitereBesondZweckbestimmung2)
      )
    )
    
    else if (
      exists($h/weitereDetailZweckbestimmung1)
    )
    then (
      (
        exists($h/weitereZweckbestimmung1)
      )
      or
      (
        exists($h/weitereBesondZweckbestimmung1)
      )
    )
    else boolean('false')
  )
)