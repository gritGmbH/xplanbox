declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

declare variable $besondereArtDerFestlegung as xs:string := '0';
declare variable $artDerFestlegung as xs:string := '0';

(
  every $h in //SO_Schienenverkehrsrecht[besondereArtDerFestlegung] satisfies
  
  (: Wenn besondereArtDerFestlegung aus Ziffern besteht, :)
  if (
    every $besondereArtDerFestlegung in $h/besondereArtDerFestlegung/text() satisfies
    matches($besondereArtDerFestlegung,"[0-9]")
  )
  (: dann überprüfe, ob artDerFestlegung ebenfalls aus Ziffern besteht. :)
  then
  
    (: Wenn artDerFestlegung aus Ziffern besteht, :)
    if (
      every $artDerFestlegung in $h/artDerFestlegung/text() satisfies
      matches($artDerFestlegung,"[0-9]")
    )
    (: dann überprüfe, ob artDerFestlegung in besondereArtDerFestlegung enthalten ist. :)
    then
      contains($besondereArtDerFestlegung, $artDerFestlegung) or not(exists($h/artDerFestlegung))
    else boolean('false')
    
  else boolean('false')
)