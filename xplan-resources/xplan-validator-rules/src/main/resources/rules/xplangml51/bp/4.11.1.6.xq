declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_VerEntsorgung
where (
	count($h/detaillierteZweckbestimmung) >= 1
	and not ( 
		count($h/zweckbestimmung) >= count($h/detaillierteZweckbestimmung)
	)
)
return $h/@gml:id/string()