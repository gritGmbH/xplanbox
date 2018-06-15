declare default element namespace 'http://www.xplanung.de/xplangml/4/0';

(
  every $h in //BP_Baugebiet satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
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
  every $h in //BP_VerEntsorgung satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  not((exists($h/GRmin) or exists($h/GRmax) or exists($h/GR) and
      (exists($h/GRZmin) or exists($h/GRZmax) or exists($h/GRZ))))
)