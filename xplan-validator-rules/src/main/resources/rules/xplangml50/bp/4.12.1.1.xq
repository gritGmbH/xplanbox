declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $id in //BP_StrassenVerkehrsFlaeche/begrenzungslinie/@xlink:href satisfies
exists(//BP_StrassenbegrenzungsLinie[@gml:id = substring($id,2)])