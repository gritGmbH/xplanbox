declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
  every $h in //BP_BaugebietsTeilFlaeche[detaillierteDachform] satisfies
  count($h/detaillierteDachform) = count($h/dachform)
)
and
(
  every $h in //BP_BesondererNutzungszweckFlaeche[detaillierteDachform] satisfies
  count($h/detaillierteDachform) = count($h/dachform)
)
and
(
  every $h in //BP_UeberbaubareGrundstuecksFlaeche[detaillierteDachform] satisfies
  count($h/detaillierteDachform) = count($h/dachform)
)
