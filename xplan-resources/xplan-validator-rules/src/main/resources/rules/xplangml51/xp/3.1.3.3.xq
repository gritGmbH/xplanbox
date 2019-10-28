declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml = 'http://www.opengis.net/gml/3.2';
declare namespace xlink = 'http://www.w3.org/1999/xlink';

for $h in //planinhalt
let $berId := concat('#',$h/../@gml:id/string())
let $piId := substring ($h/@xlink:href/string(),2)
where
not (
	//*[@gml:id eq $piId]/gehoertZuBereich[@xlink:href/string() eq $berId]
)
or
(
	count (//*[@gml:id eq $piId]/gehoertZuBereich) > 1
)
return $piId