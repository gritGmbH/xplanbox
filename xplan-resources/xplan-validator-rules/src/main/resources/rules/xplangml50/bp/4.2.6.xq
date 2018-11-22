declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_BMuenFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  not($h/BMZ and $h/BM)
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  not($h/BMZ and $h/BM)
)