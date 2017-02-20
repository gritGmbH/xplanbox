declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //BP_Baugebiet[abweichendeBauweise] satisfies
  not(exists($h/bauweise)) or $h/bauweise/text() = '3000'
)
and
(
  every $h in //BP_BaugebietsTeilFlaeche[abweichendeBauweise] satisfies
  not(exists($h/bauweise)) or $h/bauweise/text() = '3000'
)