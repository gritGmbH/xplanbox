declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/ZUmin and $h/ZUmax and not($h/ZU or $h/ZUzwingend)) or
  ($h/ZU and not($h/ZUmin or $h/ZUmax or $h/ZUzwingend)) or
  ($h/ZUzwingend and not($h/ZUmin or $h/ZUmax or $h/ZU)) or
  not($h/ZUzwingend or $h/ZUmin or $h/ZUmax or $h/ZU)
)