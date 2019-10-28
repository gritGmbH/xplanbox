declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //flaechenschluss
where (
  $h != 'false'
  and
  $h/../ebene != 0
)
return $h/../@gml:id/string()