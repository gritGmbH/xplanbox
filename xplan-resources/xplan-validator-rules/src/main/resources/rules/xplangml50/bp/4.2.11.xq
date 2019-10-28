declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), '^ZU$|^ZUmin$|^ZUmax$|^ZUzwingend$')]
where not (
  ($h/../ZUmin and $h/../ZUmax and not ($h/../ZU) and not ($h/../ZUzwingend)) or
  ($h/../ZU and not ($h/../ZUmin or $h/../ZUmax) and not ($h/../ZUzwingend)) or
  ($h/../ZUzwingend and not ($h/../ZUmin or $h/../ZUmax) and not ($h/../ZU))
)
group by $oId := $h/../@gml:id/string()
return $oId