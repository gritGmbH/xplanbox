declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_GruenFlaeche[detaillierteZweckbestimmung] satisfies
count($h/zweckbestimmung) >= count($h/detaillierteZweckbestimmung)