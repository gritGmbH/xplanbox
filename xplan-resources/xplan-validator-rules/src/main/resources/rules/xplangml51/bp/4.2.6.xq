declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[starts-with(local-name(), 'BM')]
where (
  $h/../BM and $h/../BMZ
)
group by $oId := $h/../@gml:id/string()
return $oId