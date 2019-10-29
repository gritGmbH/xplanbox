declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $h in //*[ends-with(name(), '_Bereich')]/praesentationsobjekt
let $berId := concat('#',$h/../@gml:id/string())
let $pId := substring ($h/@xlink:href/string(),2)
where
not (
	//*[@gml:id eq $pId]/gehoertZuBereich[@xlink:href/string() eq $berId]
)
or
(
	count (//*[@gml:id eq $pId]/gehoertZuBereich) > 1
)
return $pId