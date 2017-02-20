declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

declare variable $weitereBesondZweckbestimmung as xs:string := '0';
declare variable $weitereZweckbestimmung as xs:string := '0';

(
  every $h in //BP_GruenFlaeche[weitereBesondZweckbestimmung1] satisfies
  
  (: Wenn weitereBesondZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondZweckbestimmung in $h/weitereBesondZweckbestimmung1/text() satisfies
    matches($weitereBesondZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung1/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung1))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //BP_GruenFlaeche[weitereBesondZweckbestimmung2] satisfies
  
  (: Wenn weitereBesondZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondZweckbestimmung in $h/weitereBesondZweckbestimmung2/text() satisfies
    matches($weitereBesondZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung2/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung2))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //BP_GruenFlaeche[weitereBesondZweckbestimmung3] satisfies
  
  (: Wenn weitereBesondZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondZweckbestimmung in $h/weitereBesondZweckbestimmung3/text() satisfies
    matches($weitereBesondZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung3/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung3))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //BP_GruenFlaeche[weitereBesondZweckbestimmung4] satisfies
  
  (: Wenn weitereBesondZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondZweckbestimmung in $h/weitereBesondZweckbestimmung4/text() satisfies
    matches($weitereBesondZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung4/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung4))
    else boolean('false')
    
  else boolean('false')
)