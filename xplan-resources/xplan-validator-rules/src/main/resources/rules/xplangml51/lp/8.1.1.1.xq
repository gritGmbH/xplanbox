declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $id in //LP_Plan/bereich/@xlink:href/string()
where not(//LP_Bereich[@gml:id eq substring($id,2)])
return substring($id,2)