declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //BP_BaugebietsTeilFlaeche[detaillierteArtDerBaulNutzung] satisfies
 $h/allgArtDerBaulNutzung or $h/besondereArtDerBaulNutzung or $h/sondernutzung

