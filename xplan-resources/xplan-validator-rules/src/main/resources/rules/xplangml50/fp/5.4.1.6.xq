declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_Gemeinbedarf[detaillierteZweckbestimmung] satisfies
count($h/zweckbestimmung) >= count($h/detaillierteZweckbestimmung)