declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml = 'http://www.opengis.net/gml/3.2';
declare namespace xlink = 'http://www.w3.org/1999/xlink';

for $id in //wirdAusgeglichenDurchSPEFlaeche/@xlink:href/string()
where not (//BP_SchutzPflegeEntwicklungsFlaeche[@gml:id eq substring($id,2)])
return substring($id,2)