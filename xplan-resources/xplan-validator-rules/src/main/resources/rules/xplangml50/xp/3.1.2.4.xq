declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

for $h in //detaillierteBedeutung
where not ($h/../bedeutung)
return $h/../@gml:id/string()