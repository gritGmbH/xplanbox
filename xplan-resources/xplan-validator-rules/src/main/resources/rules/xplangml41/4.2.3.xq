declare default element namespace 'http://www.xplanung.de/xplangml/4/1';

(
  every $h in //BP_Baugebiet satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  not((exists($h/GFmin) or exists($h/GFmax) or exists($h/GF) and
      (exists($h/GFZmin) or exists($h/GFZmax) or exists($h/GFZ))))
)