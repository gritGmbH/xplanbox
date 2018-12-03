declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung[detaillierteZweckbestimmung] satisfies
exists($h/zweckbestimmung)