declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_AbstraktesPraesentationsobjekt[art[2]] satisfies
  count($h/art) = count($h/index)