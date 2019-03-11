declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $id in //LP_Bereich/gehoertZuPlan/@xlink:href satisfies
(
  count(//LP_Plan[@gml:id = substring($id,2)]) = 1
)
and
(
  let $bereichsId := //LP_Bereich[gehoertZuPlan/@xlink:href = $id]/@gml:id
    return

  exists(//LP_Plan/bereich[@xlink:href = concat('#',$bereichsId)])
)