declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_GemeinschaftsanlagenFlaeche
where (
	count($h/detaillierteZweckbestimmung) > 1
	and
	count($h/detaillierteZweckbestimmung) != count($h/zweckbestimmung)
)
return $h/@gml:id/string()