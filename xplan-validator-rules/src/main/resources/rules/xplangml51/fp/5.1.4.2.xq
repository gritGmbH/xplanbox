declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml = 'http://www.opengis.net/gml/3.2';

(
    every $h in //FP_AusgleichsFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_KeineZentrAbwasserBeseitigungFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_VorbehalteFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_BebauungsFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_LandwirtschaftsFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_WaldFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_NutzungsbeschraenkungsFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_TextlicheDarstellungsFlaeche/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)
and
(
    every $h in //FP_ZentralerVersorgungsbereich/position satisfies
    exists($h/gml:Polygon) or
            exists($h/gml:MultiSurface) or
            exists($h/gml:LinearRing) or
            exists($h/gml:PolygonPatch) or
            exists($h/gml:Ring)
)