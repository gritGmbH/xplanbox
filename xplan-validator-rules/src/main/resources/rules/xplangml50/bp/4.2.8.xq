declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

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
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
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