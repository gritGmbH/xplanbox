declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
every $h in //BP_Bereich[detaillierteBedeutung] satisfies
exists($h/bedeutung)
)
and
(
  every $h in //FP_Bereich[detaillierteBedeutung] satisfies
  exists($h/bedeutung)
)
and
(
  every $h in //LP_Bereich[detaillierteBedeutung] satisfies
  exists($h/bedeutung)
)
and
(
  every $h in //RP_Bereich[detaillierteBedeutung] satisfies
  exists($h/bedeutung)
)
and
(
  every $h in //SO_Bereich[detaillierteBedeutung] satisfies
  exists($h/bedeutung)
)