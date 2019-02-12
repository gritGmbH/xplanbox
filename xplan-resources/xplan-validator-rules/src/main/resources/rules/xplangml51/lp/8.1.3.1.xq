declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $id in //LP_AllgGruenflaeche/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_TextlicheFestsetzungsFlaeche/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_ZuBegruenendeGrundstueckflaeche/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_AnpflanzungBindungErhaltung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Ausgleich/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Biotopverbundflaeche/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Bodenschutzrecht/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_ErholungFreizeit/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Forstrecht/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_GenerischesObjekt/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Landschaftsbild/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_NutzungsAusschluss/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_NutzungserfordernisRegelung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_PlanerischeVertiefung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_SchutzobjektInternatRecht/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_SchutzPflegeEntwicklung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_SonstigesRecht/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_WasserrechtGemeingebrEinschraenkungNaturschutz/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_WasserrechtSchutzgebiet/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_WasserrechtSonstige/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_WasserrechtWirtschaftAbflussHochwSchutz/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Zwischennutzung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
  every $id in //LP_Abgrenzung/refTextinhalt/@xlink:href satisfies
  exists(//LP_TextAbschnitt[@gml:id = substring($id,2)])
)