declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1100' or
                                         sondernutzung/text() = '1200' or
                                         sondernutzung/text() = '1300' or
                                         sondernutzung/text() = '1400'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)

(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1500' or
                                         sondernutzung/text() = '1600' or
                                         sondernutzung/text() = '16000' or
                                         sondernutzung/text() = '16001' or
                                         sondernutzung/text() = '16002' or
                                         sondernutzung/text() = '1700' or
                                         sondernutzung/text() = '1800' or
                                         sondernutzung/text() = '1900' or
                                         sondernutzung/text() = '2000' or
                                         sondernutzung/text() = '2100' or
                                         sondernutzung/text() = '2200' or
                                         sondernutzung/text() = '2300' or
                                         sondernutzung/text() = '2400' or
                                         sondernutzung/text() = '2500' or
                                         sondernutzung/text() = '2600' or
                                         sondernutzung/text() = '2700' or
                                         sondernutzung/text() = '2800' or
                                         sondernutzung/text() = '2900' or
                                         sondernutzung/text() = '9999'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)