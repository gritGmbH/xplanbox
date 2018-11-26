declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or (not(exists($h/GRZmin)) and not(exists($h/GRZmax)) and not(exists($h/GRZ)))
)