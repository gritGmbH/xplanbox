declare default element namespace 'http://www.xplanung.de/xplangml/4/0';
declare namespace xplan='http://www.xplanung.de/xplangml/4/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_FestsetzungenBaugebiet satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_GemeinbedarfsFlaeche satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_GruenFlaeche satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_VerEntsorgung satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
  (not(exists(($h/BMmin and $h/BMmax) or $h/BM))) and (not(exists(($h/BMZmin and $h/BMZmax) or $h/BMZ))) or (not(exists($h/BMmin)) and not(exists($h/BMmax)) and not(exists($h/BM)) and not(exists($h/BMZ)) and not(exists($h/BMZ)) and not(exists($h/BMZ)))
)