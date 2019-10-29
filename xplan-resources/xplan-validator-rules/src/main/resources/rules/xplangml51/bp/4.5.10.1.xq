declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_SpezielleBauweise/sonstTyp
where not (
  not ($h/../typ) or $h/../typ/text() = '9999'
)
return $h/../@gml:id/string()