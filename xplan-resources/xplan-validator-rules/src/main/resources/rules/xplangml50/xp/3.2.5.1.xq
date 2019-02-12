declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_Gemeinde[not(ags)] satisfies
  exists($h/rs)