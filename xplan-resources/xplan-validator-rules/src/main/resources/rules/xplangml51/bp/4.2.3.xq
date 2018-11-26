declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  not($h/GFmin or $h/GFmax or $h/GF and ($h/GFZmin or $h/GFZmax or $h/GFZ))
)