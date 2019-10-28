declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //XP_Hoehenangabe
where not (
	$h/bezugspunkt and $h/h or
	$h/bezugspunkt and $h/hMin or
	$h/bezugspunkt and $h/hMin and $h/hMax or
	$h/bezugspunkt and $h/hZwingend or
	not ($h/bezugspunkt) and not ($h/../../../BP_HoehenMass) and $h/hMin or
	not ($h/bezugspunkt) and not ($h/../../../BP_HoehenMass) and $h/hMax or
	not ($h/bezugspunkt) and not ($h/../../../BP_HoehenMass) and $h/hMin and $h/hMax or 
	not ($h/bezugspunkt) and $h/../../../BP_HoehenMass and $h/h
)
return $h/../../@gml:id/string()