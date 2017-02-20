declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '3000'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1100'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1200'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1300'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1400'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1100'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1200'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1300'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1400'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2000'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1500'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1600'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '16000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '16001'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '16002'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1700'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1800'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '1900'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2100'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2200'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2300'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2400'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2500'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2600'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2700'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2800'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '2900'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_Baugebiet[sondernutzung/text() = '9999'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1500'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1600'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '16000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '16001'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '16002'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1700'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1800'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '1900'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2000'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2100'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2200'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2300'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2400'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2500'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2600'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2700'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2800'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '2900'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[sondernutzung/text() = '9999'] satisfies
  not(exists($h/besondereArtDerBaulNutzung)) or $h/besondereArtDerBaulNutzung/text() = '2100'
)