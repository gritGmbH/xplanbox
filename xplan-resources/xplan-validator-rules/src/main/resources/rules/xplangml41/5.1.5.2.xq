declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

if (
  every $h in //FP_Geometrieobjekt satisfies
  exists($h/position/gml:Polygon) or
  exists($h/position/gml:MultiSurface) or
  exists($h/position/gml:LinearRing) or
  exists($h/position/gml:PolygonPatch) or
  exists($h/position/gml:Ring)
)
then (
  every $h in //FP_Geometrieobjekt satisfies
  exists($h/flaechenschluss)
)
else boolean('false')