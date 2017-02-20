declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

let $ids := (
  for $h in //BP_Bereich/inhaltBPlan/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
exists(//BP_Punktobjekt[@gml:id = $id]) or
exists(//BP_Linienobjekt[@gml:id = $id]) or
exists(//BP_Flaechenobjekt[@gml:id = $id]) or
exists(//BP_Geometrieobjekt[@gml:id = $id]) or
exists(//BP_Flaechenschlussobjekt[@gml:id = $id]) or
exists(//BP_Ueberlagerungsobjekt[@gml:id = $id])