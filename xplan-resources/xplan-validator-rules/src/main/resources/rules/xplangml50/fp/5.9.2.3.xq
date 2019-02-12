declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //FP_Wasserwirtschaft satisfies
if($h/ebene = 0 and ($h/position/gml:Polygon or
                     $h/position/gml:MultiSurface or
                     $h/position/gml:LinearRing or
                     $h/position/gml:PolygonPatch or
                     $h/position/gml:Ring))
then $h/flaechenschluss = 'true'
else true()