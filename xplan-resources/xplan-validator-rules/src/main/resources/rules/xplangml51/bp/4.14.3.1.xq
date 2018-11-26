declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_AbstandsMass[startwinkel] satisfies
  $h/endWinkel
)
and
(
 every $h in //BP_AbstandsMass[endWinkel] satisfies
 $h/startwinkel
)