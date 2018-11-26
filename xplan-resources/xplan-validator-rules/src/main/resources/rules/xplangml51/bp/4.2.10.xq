declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_SpielSportanlagenFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_StrassenVerkehrsFlaeche satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)
and
(
  every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung satisfies
  ($h/Zmin and $h/Zmax and not($h/Z or $h/Zzwingend)) or
  ($h/Z and not($h/Zmin or $h/Zmax or $h/Zzwingend)) or
  ($h/Zzwingend and not($h/Zmin or $h/Zmax or $h/Z)) or
  (not($h/Zzwingend or $h/Zmin or $h/Zmax or $h/Z))
)