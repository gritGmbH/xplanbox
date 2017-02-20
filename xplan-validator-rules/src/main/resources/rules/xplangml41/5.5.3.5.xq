declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

declare variable $besondereZweckbestimmung as xs:string := '0';
declare variable $zweckbestimmung as xs:string := '0';

(
  every $h in //FP_Gruen[besondereZweckbestimmung1] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung1/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung1/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung1))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //FP_Gruen[besondereZweckbestimmung2] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung2/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung2/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung2))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //FP_Gruen[besondereZweckbestimmung3] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung3/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung3/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung3))
    else boolean('false')
    
  else boolean('false')
)
and
(
  every $h in //FP_Gruen[besondereZweckbestimmung4] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung4/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung4/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung4))
    else boolean('false')
    
  else boolean('false')
)

and
(
  every $h in //FP_Gruen[besondereZweckbestimmung5] satisfies
  
  (: Wenn besondereZweckbestimmung aus Ziffern besteht, :)
  if (
    every $besondereZweckbestimmung in $h/besondereZweckbestimmung5/text() satisfies
    matches($besondereZweckbestimmung,"[0-9]")
  )
  (: dann überprüfe, ob zweckbestimmung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn zweckbestimmung aus Ziffern besteht, :)
    if (
      every $zweckbestimmung in $h/zweckbestimmung5/text() satisfies
      matches($zweckbestimmung,"[0-9]")
    )
    (: dann überprüfe, ob zweckbestimmung in besondereZweckbestimmung enthalten ist. :)
    then
      contains($besondereZweckbestimmung, $zweckbestimmung) or not(exists($h/zweckbestimmung5))
    else boolean('false')
    
  else boolean('false')
)