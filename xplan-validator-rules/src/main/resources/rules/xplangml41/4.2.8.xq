declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/GRmin and $h/GRmax) or $h/GR or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)))
)