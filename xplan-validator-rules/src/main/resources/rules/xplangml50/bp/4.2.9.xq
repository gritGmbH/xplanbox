declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)