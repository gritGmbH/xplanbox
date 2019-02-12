declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $h in //BP_Bereich/rasterBasis/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_Rasterdarstellung[@gml:id = $id])
)
and
(
  every $h in //FP_Bereich/rasterBasis/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_Rasterdarstellung[@gml:id = $id])
)
and
(
  every $h in //LP_Bereich/rasterBasis/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_Rasterdarstellung[@gml:id = $id])
)
and
(
  every $h in //RP_Bereich/rasterBasis/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_Rasterdarstellung[@gml:id = $id])
)
and
(
  every $h in //SO_Bereich/rasterBasis/@xlink:href satisfies
  let $id := substring($h,2) return
  exists(//XP_Rasterdarstellung[@gml:id = $id])
)