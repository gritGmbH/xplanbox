declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $id in //BP_Plan/texte/@xlink:href satisfies
  exists(//BP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//FP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //FP_Plan/texte/@xlink:href satisfies
  exists(//BP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//FP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Plan/texte/@xlink:href satisfies
  exists(//BP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//FP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //RP_Plan/texte/@xlink:href satisfies
  exists(//BP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//FP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //SO_Plan/texte/@xlink:href satisfies
  exists(//BP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//FP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//RP_TextAbschnitt[@gml:id = substring($id,2)]) or
  exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)