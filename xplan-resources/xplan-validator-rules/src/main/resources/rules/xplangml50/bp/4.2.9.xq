declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  not($h/GRmin or $h/GRmax or $h/GR and ($h/GRZmin or $h/GRZmax or $h/GRZ))
)