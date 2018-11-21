declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_Hoehenangabe satisfies
  not($h/bezugspunkt and $h/abweichenderBezugspunkt)