declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(name(), 'XP_Nutzungsschablone|XP_P[PT]O')]
where not (
	$h/position/gml:Point or
	$h/position/gml:MultiPoint
)
return $h/@gml:id/string()