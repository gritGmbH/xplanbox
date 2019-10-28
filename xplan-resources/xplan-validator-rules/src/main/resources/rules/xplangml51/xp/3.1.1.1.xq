declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $id in //texte/@xlink:href/string()
where not (
  (//BP_TextAbschnitt[@gml:id eq substring($id,2)]) or
  (//FP_TextAbschnitt[@gml:id eq substring($id,2)]) or
  (//LP_TextAbschnitt[@gml:id eq substring($id,2)]) or
  (//RP_TextAbschnitt[@gml:id eq substring($id,2)]) or
  (//SO_TextAbschnitt[@gml:id eq substring($id,2)])
)
return substring($id,2)