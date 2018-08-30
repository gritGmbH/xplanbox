declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

let $ids := (
  for $h in //XP_Bereich/rasterBasis/@xlink:href
  return substring($h,2)
)
return

every $id in $ids satisfies
exists(//XP_Rasterdarstellung[@gml:id = $id])
