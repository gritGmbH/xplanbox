declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

(
  every $h in //FP_Abgrabung satisfies
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
  every $h in //FP_AnpassungKlimawandel satisfies
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
  every $h in //FP_Aufschuettung satisfies
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
  every $h in //FP_Bodenschaetze satisfies
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
  every $h in //FP_Gemeinbedarf satisfies
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
  every $h in //FP_GenerischesObjekt satisfies
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
  every $h in //FP_Gewaesser satisfies
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
  every $h in //FP_Gruen satisfies
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
  every $h in //FP_Kennzeichnung satisfies
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
  every $h in //FP_PrivilegiertesVorhaben satisfies
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
  every $h in //FP_SchutzPflegeEntwicklung satisfies
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
  every $h in //FP_SpielSportanlage satisfies
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
  every $h in //FP_Strassenverkehr satisfies
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
  every $h in //FP_UnverbindlicheVormerkung satisfies
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
  every $h in //FP_VerEntsorgung satisfies
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
  every $h in //FP_Wasserwirtschaft satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)