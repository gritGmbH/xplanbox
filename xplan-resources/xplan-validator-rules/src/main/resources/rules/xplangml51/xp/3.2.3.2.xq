declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_Hoehenangabe[hoehenbezug/text() = '3000'] satisfies
  exists($h/bezugshoehe)