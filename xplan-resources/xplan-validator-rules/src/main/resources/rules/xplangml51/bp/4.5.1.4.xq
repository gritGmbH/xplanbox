declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_BaugebietsTeilFlaeche/abweichendeBauweise
where not (
  not ($h/../bauweise) or $h/../bauweise/text() = '3000'
)
return $h/../@gml:id/string()
