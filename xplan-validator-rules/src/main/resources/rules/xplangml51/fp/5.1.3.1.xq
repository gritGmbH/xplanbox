declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
    every $id in //FP_AusgleichsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_KeineZentrAbwasserBeseitigungFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_VorbehalteFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_BebauungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_LandwirtschaftsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_WaldFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_NutzungsbeschraenkungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_TextlicheDarstellungsFlaeche/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_ZentralerVersorgungsbereich/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Abgrabung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_AnpassungKlimawandel/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Aufschuettung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Bodenschaetze/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Gemeinbedarf/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_GenerischesObjekt/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Gewaesser/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Gruen/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Kennzeichnung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_PrivilegiertesVorhaben/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_SchutzPflegeEntwicklung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_SpielSportanlage/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Strassenverkehr/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_UnverbindlicheVormerkung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_VerEntsorgung/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)
and
(
    every $id in //FP_Wasserwirtschaft/wirdAusgeglichenDurchFlaeche/@xlink:href satisfies
    exists(//FP_AusgleichsFlaeche[@gml:id = substring($id,2)])
)