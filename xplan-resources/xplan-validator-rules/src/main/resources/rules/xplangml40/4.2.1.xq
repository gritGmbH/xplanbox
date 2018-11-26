declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or (not(exists($h/GFZmin)) and not(exists($h/GFZmax)) and not(exists($h/GFZ)))
)
