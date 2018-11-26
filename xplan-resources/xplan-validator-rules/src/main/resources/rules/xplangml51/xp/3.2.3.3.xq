declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_Hoehenangabe satisfies
  ($h/hoehenbezug or $h/abweichenderHoehenbezug) and
  not($h/hoehenbezug and $h/abweichenderHoehenbezug)