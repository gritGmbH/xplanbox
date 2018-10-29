declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_BaugebietsTeilFlaeche[detaillierteArtDerBaulNutzung] satisfies
exists($h/allgArtDerBaulNutzung) or
exists($h/besondereArtDerBaulNutzung) or
exists($h/sondernutzung)

