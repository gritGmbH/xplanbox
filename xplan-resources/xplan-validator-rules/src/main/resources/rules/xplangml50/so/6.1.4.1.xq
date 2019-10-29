declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //SO_Gebiet
where not (
	$h/ebene != '0'
	and $h/flaechenschluss = 'false'
)
return $h/@gml:id/string()