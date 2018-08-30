declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_Hoehenangabe satisfies
  (exists($h/hoehenbezug) or exists($h/abweichenderHoehenbezug)) and
  not(exists($h/hoehenbezug) and exists($h/abweichenderHoehenbezug))