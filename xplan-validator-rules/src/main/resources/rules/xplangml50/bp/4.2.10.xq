declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  (exists($h/Zmin) and exists($h/Zmax) and not(exists($h/Z)) and not(exists($h/Zzwingend))) or
  (exists($h/Z) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Zzwingend))) or
  (exists($h/Zzwingend) and not(exists($h/Zmin)) and not(exists($h/Zmax)) and not(exists($h/Z)))
)