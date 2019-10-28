declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(name(), 'XP_Praesentationsobjekt|XP_.{1}[PT]O|XP_Nutzungsschablone')]
where (
	count($h/art) > 1
	and
	count($h/art) != count($h/index)
)
return $h/@gml:id/string()