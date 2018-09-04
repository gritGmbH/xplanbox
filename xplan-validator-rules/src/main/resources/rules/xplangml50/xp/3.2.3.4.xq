declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_Hoehenangabe satisfies
  (exists($h/bezugspunkt) or exists($h/abweichenderBezugspunkt)) and
  not(exists($h/bezugspunkt) and exists($h/abweichenderBezugspunkt))