declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_BaugebietsTeilFlaeche[detaillierteArtDerBaulNutzung] satisfies
 $h/allgArtDerBaulNutzung or $h/besondereArtDerBaulNutzung or $h/sondernutzung

