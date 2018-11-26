declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //BP_HoehenMass satisfies
    count($h/hoehenangabe/XP_Hoehenangabe) = 1 and
    not(exists($h/hoehenangabe/XP_Hoehenangabe/bezugspunkt)) and
    not(exists($h/hoehenangabe/XP_Hoehenangabe/abweichenderBezugspunkt)) and
    exists($h/hoehenangabe/XP_Hoehenangabe/h)