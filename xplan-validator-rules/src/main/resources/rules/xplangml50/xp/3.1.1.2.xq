declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $id in //BP_Plan/begruendungsTexte/@xlink:href satisfies
  exists(//XP_BegruendungAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //FP_Plan/begruendungsTexte/@xlink:href satisfies
  exists(//XP_BegruendungAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Plan/begruendungsTexte/@xlink:href satisfies
  exists(//XP_BegruendungAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Plan/begruendungsTexte/@xlink:href satisfies
  exists(//XP_BegruendungAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //SO_Plan/begruendungsTexte/@xlink:href satisfies
  exists(//XP_BegruendungAbschnitt[@gml:id = substring($id,2)])
)