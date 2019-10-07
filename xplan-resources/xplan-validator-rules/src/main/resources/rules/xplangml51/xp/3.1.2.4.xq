declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

for $h in //detaillierteBedeutung
where not ($h/../bedeutung)
return $h/../@gml:id/string()