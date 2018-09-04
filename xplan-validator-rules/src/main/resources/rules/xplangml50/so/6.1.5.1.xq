declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

if (
  every $h in //SO_Geometrieobjekt[ebene] satisfies
  exists($h/ebene != '0') and
  (
    exists($h/position/gml:Polygon) or
    exists($h/position/gml:MultiSurface) or
    exists($h/position/gml:LinearRing) or
    exists($h/position/gml:PolygonPatch) or
    exists($h/position/gml:Ring)
      )
)
then (
  every $h in //SO_Geometrieobjekt satisfies
  exists($h/flaechenschluss = 'false')
)
else boolean('false')