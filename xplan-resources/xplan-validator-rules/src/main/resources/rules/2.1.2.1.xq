declare default element namespace 'http://www.xplanung.de/xplangml/3/0';
declare namespace xplan='http://www.xplanung.de/xplangml/3/0';
declare namespace gml='http://www.opengis.net/gml';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

if (exists(.//*/@uom))
then (
  exists(.//*/@uom = "m") or exists(.//*/@uom = "urn:adv:uom:m") or 
  exists(.//*/@uom = "m2") or exists(.//*/@uom = "urn:adv:uom:m2") or 
  exists(.//*/@uom = "m3") or exists(.//*/@uom = "urn:adv:uom:m3") or 
  exists(.//*/@uom = "grad") or exists(.//*/@uom = "urn:adv:uom:grad")
)
else boolean('false')