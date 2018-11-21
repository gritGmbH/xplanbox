declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //SO_Bodenschutzrecht[detailArtDerFestlegung] satisfies
exists($h/artDerFestlegung)