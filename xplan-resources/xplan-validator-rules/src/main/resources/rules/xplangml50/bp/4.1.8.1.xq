declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'BP_BauGrenze|BP_BauLinie|BP_BereichOhneEinAusfahrtLinie|BP_EinfahrtsbereichLinie|BP_FirstRichtungsLinie|BP_NutzungsartenGrenze|BP_StrassenbegrenzungsLinie')]
where not (
	$h/position/gml:Curve or
	$h/position/gml:LineString or
	$h/position/gml:MultiCurve or
	$h/position/gml:LineStringSegment or
	$h/position/gml:Arc or
	$h/position/gml:Circle
)
return $h/@gml:id/string()