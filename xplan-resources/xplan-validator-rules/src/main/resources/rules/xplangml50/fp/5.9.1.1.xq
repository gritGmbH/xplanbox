declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //FP_Gewaesser
where (
	$h/detaillierteZweckbestimmung
	and not ($h/zweckbestimmung)
)
return $h/@gml:id/string()