declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/BMZmin and $h/BMZmax) or $h/BMZ or (not(exists($h/BMZmin)) and not(exists($h/BMZmax)) and not(exists($h/BMZ)))
)