declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Denkmalschutzrecht satisfies
if(exists($h/position/gml:Polygon) or
        exists($h/position/gml:MultiSurface) or
        exists($h/position/gml:LinearRing) or
        exists($h/position/gml:PolygonPatch) or
        exists($h/position/gml:Ring))
then $h/flaechenschluss = 'false'
else true()