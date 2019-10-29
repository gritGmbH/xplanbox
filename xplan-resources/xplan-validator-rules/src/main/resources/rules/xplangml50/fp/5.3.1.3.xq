declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //FP_BebauungsFlaeche/detaillierteArtDerBaulNutzung
where not (
  not ($h/../allgArtDerBaulNutzung) or 
  not ($h/../besondereArtDerBaulNutzung) or
  not ($h/../sondernutzung)
)
return $h/../@gml:id/string()