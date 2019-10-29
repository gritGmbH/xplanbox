declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml = 'http://www.opengis.net/gml/3.2';
declare namespace xlink = 'http://www.w3.org/1999/xlink';

for $h in //dientZurDarstellungVon
let $prId := concat('#',$h/../@gml:id/string())
let $oId := substring ($h/@xlink:href/string(),2)
where not ( //*[@gml:id eq $oId]/wirdDargestelltDurch[@xlink:href/string() eq $prId] )
return $oId