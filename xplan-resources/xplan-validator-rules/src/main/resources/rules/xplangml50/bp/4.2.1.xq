declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin and $h/GFZmax and $h/GFZ)
)