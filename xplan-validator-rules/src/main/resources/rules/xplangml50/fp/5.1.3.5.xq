declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //FP_AusgleichsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_KeineZentrAbwasserBeseitigungFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_VorbehalteFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_BebauungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_LandwirtschaftsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_WaldFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_NutzungsbeschraenkungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_TextlicheDarstellungsFlaeche/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_ZentralerVersorgungsbereich/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Abgrabung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_AnpassungKlimawandel/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Aufschuettung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Bodenschaetze/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gemeinbedarf/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_GenerischesObjekt/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gewaesser/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gruen/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Kennzeichnung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_PrivilegiertesVorhaben/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_SchutzPflegeEntwicklung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_SpielSportanlage/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Strassenverkehr/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_UnverbindlicheVormerkung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_VerEntsorgung/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Wasserwirtschaft/refTextinhalt/@xlink:href satisfies
exists(//FP_TextAbschnitt[@gml:id = substring($id,2)])
)