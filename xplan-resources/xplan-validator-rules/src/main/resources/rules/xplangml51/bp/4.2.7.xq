declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/GRZmin and $h/GRZmax) or $h/GRZ or not($h/GRZmin and $h/GRZmax and $h/GRZ)
)