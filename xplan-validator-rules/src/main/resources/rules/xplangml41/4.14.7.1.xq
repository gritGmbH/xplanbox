declare default element namespace 'http://www.xplanung.de/xplangml/4/1';

every $h in //BP_HoehenMass satisfies
    count(exists($h/hoehenangabe/XP_Hoehenangabe)) = 1 and
    not(exists($h/hoehenangabe/XP_Hoehenangabe/bezugspunkt)) and
    exists($h/hoehenangabe/XP_Hoehenangabe/h)