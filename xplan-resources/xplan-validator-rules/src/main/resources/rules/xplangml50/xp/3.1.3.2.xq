declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml = 'http://www.opengis.net/gml/3.2';
declare namespace xlink = 'http://www.w3.org/1999/xlink';

for $h in //refBegruendungInhalt
let $id := substring($h/@xlink:href/string(),2)
where not (
	//XP_BegruendungAbschnitt[@gml:id eq $id]
)
return $h/../@gml:id/string()