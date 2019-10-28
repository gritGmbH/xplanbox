declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[starts-with(local-name(), 'GR')]
where (
  ($h/../GRZmin or $h/../GRZmax or ($h/../GRZ)) and
  ($h/../GR or $h/../GRmin or $h/../GRmax)
)
group by $oId := $h/../@gml:id/string()
return $oId