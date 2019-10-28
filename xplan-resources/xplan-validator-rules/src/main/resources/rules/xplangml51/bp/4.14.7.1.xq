declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $h in //BP_HoehenMass
where not (
  count($h/hoehenangabe/XP_Hoehenangabe) = 1 and
  ($h/hoehenangabe/XP_Hoehenangabe/h) and
  not ($h/hoehenangabe/XP_Hoehenangabe/bezugspunkt) and
  not ($h/hoehenangabe/XP_Hoehenangabe/abweichenderBezugspunkt)
)
return $h/@gml:id/string()