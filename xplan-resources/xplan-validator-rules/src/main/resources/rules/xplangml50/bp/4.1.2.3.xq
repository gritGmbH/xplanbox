declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $h in //BP_Bereich/gehoertZuPlan
let $berId := concat('#',$h/../@gml:id/string())
let $pId := substring ($h/@xlink:href/string(),2)
where
	not ( //*[@gml:id eq $pId]/bereich[@xlink:href/string() eq $berId] )
	or ( count ($h/gehoertZuPlan) > 1 )
return  substring($berId,2)