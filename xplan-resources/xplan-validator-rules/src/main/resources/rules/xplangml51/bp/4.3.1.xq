declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  $h/DNmin or ($h/DNmin and $h/DNmax) or $h/DN or $h/DNzwingend or not($h/DNmin and $h/DNmax and $h/DN and $h/DNzwingend)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche satisfies
  $h/DNmin or ($h/DNmin and $h/DNmax) or $h/DN or $h/DNzwingend or not($h/DNmin and $h/DNmax and $h/DN and $h/DNzwingend)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  $h/DNmin or ($h/DNmin and $h/DNmax) or $h/DN or $h/DNzwingend or not($h/DNmin and $h/DNmax and $h/DN and $h/DNzwingend)
)