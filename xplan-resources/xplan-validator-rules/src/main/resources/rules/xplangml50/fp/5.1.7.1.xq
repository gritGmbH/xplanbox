declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'FP_BebauungsFlaeche|FP_LandwirtschaftsFlaeche|FP_WaldFlaeche')]
where not (
	(
		not ($h/ebene)
		or $h/ebene = 0
	)
	and ($h/flaechenschluss/text() = 'true')
)
return $h/@gml:id/string()