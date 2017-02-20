declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1100'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1200'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1300'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1100'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1200'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1300'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '1000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1400'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1500'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1600'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1400'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1500'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1600'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1700'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '1800'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1700'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '1800'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '2000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '2100'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '3000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaeche[besondereArtDerBaulNutzung/text() = '4000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '2000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '2100'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '3000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)
and
(
  every $h in //FP_BebauungsFlaechesTeilFlaeche[besondereArtDerBaulNutzung/text() = '4000'] satisfies
  not(exists($h/allgArtDerBaulNutzung)) or $h/allgArtDerBaulNutzung/text() = '4000'
)