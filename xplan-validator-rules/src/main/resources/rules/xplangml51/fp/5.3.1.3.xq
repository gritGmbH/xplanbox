declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //FP_BebauungsFlaeche[detaillierteArtDerBaulNutzung] satisfies
$h/allgArtDerBaulNutzung or $h/besondereArtDerBaulNutzung or $h/sonderNutzung