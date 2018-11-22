declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

declare variable $weitereBesondweitereZweckbestimmung as xs:string := '0';
declare variable $weitereZweckbestimmung as xs:string := '0';

(
  every $h in //FP_PrivilegiertesVorhaben[weitereBesondweitereZweckbestimmung1] satisfies
  
  (: Wenn weitereBesondweitereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondweitereZweckbestimmung in $h/weitereBesondweitereZweckbestimmung1/text() satisfies
    matches($weitereBesondweitereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung1/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondweitereZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondweitereZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung1))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //FP_PrivilegiertesVorhaben[weitereBesondweitereZweckbestimmung2] satisfies
  
  (: Wenn weitereBesondweitereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $weitereBesondweitereZweckbestimmung in $h/weitereBesondweitereZweckbestimmung2/text() satisfies
    matches($weitereBesondweitereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob weitereZweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn weitereZweckbestimmung aus Ziffern besteht, :)
    if (
      every $weitereZweckbestimmung in $h/weitereZweckbestimmung2/text() satisfies
      matches($weitereZweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob weitereZweckbestimmung in weitereBesondweitereZweckbestimmung enthalten ist. :)
    then
      contains($weitereBesondweitereZweckbestimmung, $weitereZweckbestimmung) or not(exists($h/weitereZweckbestimmung2))
    else boolean('false')
    
  else boolean('false')
)