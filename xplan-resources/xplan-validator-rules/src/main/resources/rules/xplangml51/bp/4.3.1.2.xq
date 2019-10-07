declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //detaillierteDachform
where (
	count($h/../detaillierteDachform) > 1
	and
	count($h/../detaillierteDachform) != count($h/../dachform)
)
return $h/@gml:id/string()