declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //LP_ErholungFreizeit
where (
	count($h/detaillierteFunktion) >= 1
	and not ( 
		count($h/funktion) >= count($h/detaillierteFunktion)
	)
)
return $h/@gml:id/string()