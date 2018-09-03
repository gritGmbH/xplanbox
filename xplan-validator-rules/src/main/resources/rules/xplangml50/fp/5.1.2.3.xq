declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $id in //FP_Bereich/gehoertZuPlan/@xlink:href satisfies
(
count(//FP_Plan[@gml:id = substring($id,2)]) = 1
)
and
(
let $bereichsId := //FP_Bereich[gehoertZuPlan/@xlink:href = $id]/@gml:id
return

exists(//FP_Plan/bereich[@xlink:href = concat('#',$bereichsId)])
)