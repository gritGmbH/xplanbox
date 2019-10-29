declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //FP_BebauungsFlaeche
where not (
  ($h/GFZmin and $h/GFZmax and not ($h/GFZ)) or
  ($h/GFZ and not ($h/GFZmin or $h/GFZmax))
)
group by $oId := $h/@gml:id/string()
return $oId