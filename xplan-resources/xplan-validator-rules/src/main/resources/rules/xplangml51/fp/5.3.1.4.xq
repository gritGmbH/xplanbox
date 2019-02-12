declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //FP_BebauungsFlaeche satisfies
($h/GFZmin and $h/GFZmax) or $h/GFZ or not($h/GFZmin or $h/GFZmax or $h/GFZ)