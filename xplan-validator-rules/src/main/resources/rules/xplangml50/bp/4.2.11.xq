declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
          (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  (exists($h/ZUmin) and exists($h/ZUmax) and not(exists($h/ZU)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZU) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZUzwingend))) or
  (exists($h/ZUzwingend) and not(exists($h/ZUmin)) and not(exists($h/ZUmax)) and not(exists($h/ZU)))
)