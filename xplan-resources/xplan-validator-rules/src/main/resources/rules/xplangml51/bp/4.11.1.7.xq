declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_VerEntsorgung
  where ($h/position/gml:Polygon or
            $h/position/gml:MultiSurface or
            $h/position/gml:LinearRing or
            $h/position/gml:PolygonPatch or
            $h/position/gml:Ring)
          and $h/ebene = '0'
          and ($h/flaechenschluss != 'true'
               or
               not($h/flaechenschluss)
               )
return $h/@gml:id/string()