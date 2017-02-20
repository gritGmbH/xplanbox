declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  (not(exists(($h/GRmin and $h/GRmax) or $h/GR))) and (not(exists(($h/GRZmin and $h/GRZmax) or $h/GRZ))) or (not(exists($h/GRmin)) and not(exists($h/GRmax)) and not(exists($h/GR)) and not(exists($h/GRZ)) and not(exists($h/GRZ)) and not(exists($h/GRZ)))
)