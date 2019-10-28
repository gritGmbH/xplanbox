declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //SO_Grenze[sonstTyp] 
where not (
  not ($h/typ) or $h/typ/text() = '9999'
)
return $h/@gml:id/string()