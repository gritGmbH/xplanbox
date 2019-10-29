declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[starts-with(local-name(), 'GF')]
where (
  ($h/../GFZmin or $h/../GFZmax or ($h/../GFZ)) and
  ($h/../GF or $h/../GFmin or $h/../GFmax)
)
group by $oId := $h/../@gml:id/string()
return $oId