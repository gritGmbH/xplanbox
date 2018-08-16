declare default element namespace 'http://www.xplanung.de/xplangml/4/1';

every $h in //XP_Hoehenangabe satisfies
  (exists($h/hoehenbezug) or exists($h/abweichenderHoehenbezug)) and
  not(exists($h/hoehenbezug) and exists($h/abweichenderHoehenbezug))