declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //BP_GruenFlaeche[detaillierteZweckbestimmung] satisfies
count($h/zweckbestimmung) >= count($h/detaillierteZweckbestimmung)