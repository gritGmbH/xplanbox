declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

if (
  let $ids := (
    for $h in //XP_Objekt/planinhalt/@xlink:href
    return substring($h,2)
  )
  return
  
every $id in $ids satisfies
  exists(//XP_Bereich[@gml:id = $id])
)
then (
  let $ids := (
    for $h in //XP_Bereich/gehoertNachrichtlichZuBereich/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  count(exists(//XP_Objekt[@gml:id = $id])) = 1
)
else boolean ('false')