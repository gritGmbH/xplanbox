declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //BP_GewaesserFlaeche[detaillierteZweckbestimmung] satisfies
exists($h/zweckbestimmung)