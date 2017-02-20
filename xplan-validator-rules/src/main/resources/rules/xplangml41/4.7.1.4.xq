declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

declare variable $besondereZweckbestimmung as xs:string := '0';
declare variable $zweckbestimmung as xs:string := '0';

(
  every $h in //BP_GemeinbedarfsFlaeche[besondereZweckbestimmung] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche[besondereZweckbestimmung[2]] satisfies
  (count($h/besondereZweckbestimmung) = count ($h/zweckbestimmung)) or not(exists($h/zweckbestimmung))
)