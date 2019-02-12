declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

every $h in //SO_Strassenverkehrsrecht satisfies
  if(($h/position/gml:Polygon or
      $h/position/gml:MultiSurface or
      $h/position/gml:LinearRing or
      $h/position/gml:PolygonPatch or
      $h/position/gml:Ring) and $h/eben = '0')
    then $h/flaechenschluss = 'true'
  else true()