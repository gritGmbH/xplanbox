declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung/text() = '1000' or
                                         besondereArtDerBaulNutzung/text() = '1100' or
                                         besondereArtDerBaulNutzung/text() = '1200' or
                                         besondereArtDerBaulNutzung/text() = '1300'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung/text() = '1400' or
                                         besondereArtDerBaulNutzung/text() = '1500' or
                                         besondereArtDerBaulNutzung/text() = '1550' or
                                         besondereArtDerBaulNutzung/text() = '1600'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung/text() = '1700' or
                                         besondereArtDerBaulNutzung/text() = '1800'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung/text() = '2000' or
                                         besondereArtDerBaulNutzung/text() = '2100' or
                                         besondereArtDerBaulNutzung/text() = '3000' or
                                         besondereArtDerBaulNutzung/text() = '4000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)