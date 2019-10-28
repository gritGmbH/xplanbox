declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(name(), 'GRZ')]
where not (
  ($h/../GRZmin and $h/../GRZmax and not ($h/../GRZ)) or
  ($h/../GRZ and not ($h/../GRZmin or $h/../GRZmax))
)
group by $oId := $h/../@gml:id/string()
return $oId