declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

(
every $h in //BP_BauGrenze/position satisfies
  exists($h/gml:Curve) or
  exists($h/gml:LineString) or
  exists($h/gml:MultiCurve) or
  exists($h/gml:LineStringSegment) or
  exists($h/gml:Arc)  or
  exists($h/gml:Circle)
)
and
(
  every $h in //BP_BauLinie/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)
and
(
  every $h in //BP_BereichOhneEinAusfahrtLinie/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)
and
(
  every $h in //BP_EinfahrtsbereichLinie/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)
and
(
  every $h in //BP_FirstRichtungsLinie/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)
and
(
  every $h in //BP_NutzungsartenGrenze/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)
and
(
  every $h in //BP_StrassenbegrenzungsLinie/position satisfies
  exists($h/gml:Curve) or
          exists($h/gml:LineString) or
          exists($h/gml:MultiCurve) or
          exists($h/gml:LineStringSegment) or
          exists($h/gml:Arc)  or
          exists($h/gml:Circle)
)