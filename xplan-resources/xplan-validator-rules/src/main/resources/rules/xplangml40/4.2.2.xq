declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or (not(exists($h/GFmin)) and not(exists($h/GFmax)) and not(exists($h/GF)))
)