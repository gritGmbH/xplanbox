declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_TextlicheFestsetzungsFlaeche
where not ($h/refTextInhalt)
return $h/@gml:id/string()