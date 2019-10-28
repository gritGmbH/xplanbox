declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), '^Z$|^Zmin$|^Zmax$|^Zzwingend$')]
where not (
  ($h/../Zmin and $h/../Zmax and not ($h/../Z) and not ($h/../Zzwingend)) or
  ($h/../Z and not ($h/../Zmin or $h/../Zmax) and not ($h/../Zzwingend)) or
  ($h/../Zzwingend and not ($h/../Zmin or $h/../Zmax) and not ($h/../Z))
)
group by $oId := $h/../@gml:id/string()
return $oId