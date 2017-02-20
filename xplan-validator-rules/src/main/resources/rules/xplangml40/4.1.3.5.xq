declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

if (
  let $ids := (
    for $h in //BP_Bereich/inhaltBPan/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  exists(//BP_Objekt[@gml:id = $id])
)
then (
  let $ids := (
    for $h in //BP_Objekt/gehoertZuBP_Bereich/@xlink:href
    return substring($h,2)
  )
  return
  
  every $id in $ids satisfies
  count(exists(//BP_Bereich[@gml:id = $id])) = 1
)
else boolean ('false')