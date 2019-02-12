declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_VerEntsorgung[detaillierteZweckbestimmung] satisfies
count($h/zweckbestimmung) >= count($h/detaillierteZweckbestimmung)