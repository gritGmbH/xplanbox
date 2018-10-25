declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Schienenverkehrsrecht satisfies
if((exists($h/position/gml:Polygon) or
        exists($h/position/gml:MultiSurface) or
        exists($h/position/gml:LinearRing) or
        exists($h/position/gml:PolygonPatch) or
        exists($h/position/gml:Ring)) and $h/ebene = 'true')
then $h/flaechenschluss = 'true'
else true()