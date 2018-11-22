declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

let $ids := (
  for $h in //BP_UeberbaubareGrundstuecksFlaeche/baulinie/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
exists(//BP_Baulinie[@gml:id = $id])