declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_WasserwirtschaftsFlaeche[detaillierteZweckbestimmung] satisfies
exists($h/zweckbestimmung)