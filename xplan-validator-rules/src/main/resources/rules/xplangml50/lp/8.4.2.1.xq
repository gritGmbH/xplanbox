declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //LP_Bodenschutzrecht[detailTyp] satisfies
exists($h/typ)