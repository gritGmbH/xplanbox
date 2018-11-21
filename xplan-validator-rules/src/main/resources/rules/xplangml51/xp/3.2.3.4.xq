declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_Hoehenangabe satisfies
  ($h/bezugspunkt or $h/abweichenderBezugspunkt) and
  not($h/bezugspunkt and $h/abweichenderBezugspunkt)