declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/GFmin and $h/GFmax) or $h/GF or not($h/GFmin and $h/GFmax and $h/GF)
)