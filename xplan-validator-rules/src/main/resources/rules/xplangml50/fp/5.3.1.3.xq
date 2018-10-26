declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_BebauungsFlaeche[detaillierteArtDerBaulNutzung] satisfies
exists($h/allgArtDerBaulNutzung) or exists($h/besondereArtDerBaulNutzung) or exists($h/sonderNutzung)