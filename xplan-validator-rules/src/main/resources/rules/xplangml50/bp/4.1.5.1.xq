declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

(
every $h in //BP_AbstandsMass satisfies
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
  every $h in //BP_AnpflanzungBindungErhaltung satisfies
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
  every $h in //BP_AusgleichsMassnahme satisfies
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
  every $h in //BP_FestsetzungNachLandesrecht satisfies
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
  every $h in //BP_GemeinschaftsanlagenZuordnung satisfies
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
  every $h in //BP_GenerischesObjekt satisfies
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
  every $h in //BP_HoehenMass satisfies
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
  every $h in //BP_Immissionsschutz satisfies
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
  every $h in //BP_Landwirtschaft satisfies
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
  every $h in //BP_SchutzPflegeEntwicklungsMassnahme satisfies
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
  every $h in //BP_Strassenkoerper satisfies
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
  every $h in //BP_UnverbindlicheVormerkung satisfies
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
  every $h in //BP_VerEntsorgung satisfies
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
  every $h in //BP_Wegerecht satisfies
  if($h/ebene != '0' and (exists($h/position/gml:Polygon) or
          exists($h/position/gml:MultiSurface) or
          exists($h/position/gml:LinearRing) or
          exists($h/position/gml:PolygonPatch) or
          exists($h/position/gml:Ring)))
  then $h/flaechenschluss = 'false'
  else true()
)