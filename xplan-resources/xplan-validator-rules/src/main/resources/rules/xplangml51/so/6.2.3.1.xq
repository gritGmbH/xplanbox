declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //SO_Forstrecht[detailArtDerFestlegung]
where not ($h/artDerFestlegung)
return $h/@gml:id/string()