declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $id in //BP_VerkehrsflaecheBesondererZweckbestimmung/begrenzungslinie/@xlink:href satisfies
exists(//BP_StrassenbegrenzungsLinie[@gml:id = substring($id,2)])
