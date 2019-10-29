declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $h in //XP_VerbundenerPlan/verbundenerPlan
let $pId := substring ($h/@xlink:href/string(),2)
where not (
  ends-with(local-name(//*[@gml:id = $pId]),'_Plan')
)
return $pId