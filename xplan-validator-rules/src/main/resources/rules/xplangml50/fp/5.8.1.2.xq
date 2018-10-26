declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_Strassenverkehr[detaillierteZweckbestimmung] satisfies
exists($h/zweckbestimmung)