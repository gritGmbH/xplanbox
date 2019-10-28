declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), '^DN$|^DNmin$|^DNmax$|^DNzwingend$')]
where not (
  ($h/../DNmin and not ($h/../DNmax) and not ($h/../DN) and not ($h/../DNzwingend)) or
  ($h/../DNmin and $h/../DNmax and not ($h/../DN) and not ($h/../DNzwingend)) or
  ($h/../DN and not ($h/../DNmin or $h/../DNmax) and not ($h/../DNzwingend)) or
  ($h/../DNzwingend and not ($h/../DNmin or $h/../DNmax) and not ($h/../DN))
)
group by $oId := $h/../@gml:id/string()
return $oId