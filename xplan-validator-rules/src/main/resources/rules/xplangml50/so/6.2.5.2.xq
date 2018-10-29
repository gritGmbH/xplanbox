declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //SO_Schienenverkehrsrecht[detailArtDerFestlegung] satisfies
exists($h/artDerFestlegung)