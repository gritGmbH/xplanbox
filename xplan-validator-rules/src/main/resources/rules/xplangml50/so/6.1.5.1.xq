declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

(
  every $h in //SO_Bodenschutzrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Denkmalschutzrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Forstrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Luftverkehrsrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Schienenverkehrsrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_SchutzgebietNaturschutzrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_SchutzgebietSonstigesRecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_SchutzgebietWasserrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_SonstigesRecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Strassenverkehrsrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)
and
(
  every $h in //SO_Wasserrecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)