declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), '^GR$|^GRmin$|^GRmax$')]
where not (
  ($h/../GRmin and $h/../GRmax and not ($h/../GR)) or
  ($h/../GR and not ($h/../GRmin or $h/../GRmax))
)
group by $oId := $h/../@gml:id/string()
return $oId