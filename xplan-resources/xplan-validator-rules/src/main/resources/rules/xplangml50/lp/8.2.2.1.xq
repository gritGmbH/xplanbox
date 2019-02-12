declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //LP_ErholungFreizeit[detaillierteFunktion] satisfies
count($h/funktion) >= count ($h/detaillierteFunktion)