declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //LP_SonstigesRecht
where (
	$h/detailTyp
	and not ($h/typ)
)
return $h/@gml:id/string()