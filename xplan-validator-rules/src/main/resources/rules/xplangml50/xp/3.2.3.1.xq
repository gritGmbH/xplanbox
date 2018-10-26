declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //XP_Hoehenangabe[bezugspunkt] satisfies
    $h/h or $h/hMin or ($h/hMin and $h/hMax) or $h/hZwingend
)
and
(
  every $h in //XP_Hoehenangabe[not(bezugspunkt) and not(parent::BP_HoehenMass)] satisfies
    $h/hMin or $h/hMax or ($h/hMin and $h/hMax)
)
and
(
  every $h in //XP_Hoehenangabe[not(bezugspunkt) and parent::BP_HoehenMass] satisfies
    $h/h
)