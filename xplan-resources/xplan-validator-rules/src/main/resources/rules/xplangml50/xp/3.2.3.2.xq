declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_Hoehenangabe[hoehenbezug/text() = '3000'] satisfies
  exists($h/bezugshoehe)