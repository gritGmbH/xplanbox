declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_AbstraktesPraesentationsobjekt[art[2]] satisfies
  count($h/art) = count($h/index)