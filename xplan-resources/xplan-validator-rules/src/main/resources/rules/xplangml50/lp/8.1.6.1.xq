declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $h in //LP_Abgrenzung/position satisfies
exists($h/position/gml:Curve) or
exists($h/position/gml:LineString) or
exists($h/position/gml:MultiCurve) or
exists($h/position/gml:LineStringSegment) or
exists($h/position/gml:Arc) or
exists($h/position/gml:Circle)