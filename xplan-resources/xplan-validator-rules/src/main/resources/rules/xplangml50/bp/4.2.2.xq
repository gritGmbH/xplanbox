declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), '^GF$|^GFmin$|^GFmax$')]
where not (
  ($h/../GFmin and $h/../GFmax and not ($h/../GF)) or
  ($h/../GF and not ($h/../GFmin or $h/../GFmax))
)
group by $oId := $h/../@gml:id/string()
return $oId