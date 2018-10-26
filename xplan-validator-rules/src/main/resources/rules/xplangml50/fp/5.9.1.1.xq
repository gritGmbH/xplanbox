declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_Gewaesser[detaillierteZweckbestimmung] satisfies
exists($h/zweckbestimmung)