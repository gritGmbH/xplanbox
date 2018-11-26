declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_Gemeinde[not(ags)] satisfies
  exists($h/rs)