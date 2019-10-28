declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //LP_Plan
where (
	$h/sonstPlanArt
	and not ($h/planArt/text() = '9999')
)
return $h/@gml:id/string()