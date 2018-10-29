declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_AbstandsMass[startwinkel] satisfies
  exists($h/endWinkel)
)
and
(
 every $h in //BP_AbstandsMass[endWinkel] satisfies
 exists($h/startwinkel)
)