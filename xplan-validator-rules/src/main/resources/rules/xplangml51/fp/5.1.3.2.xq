declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //FP_AusgleichsFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_KeineZentrAbwasserBeseitigungFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_VorbehalteFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_BebauungsFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_LandwirtschaftsFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_WaldFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_NutzungsbeschraenkungsFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_TextlicheDarstellungsFlaeche/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_ZentralerVersorgungsbereich/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Abgrabung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_AnpassungKlimawandel/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Aufschuettung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Bodenschaetze/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gemeinbedarf/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_GenerischesObjekt/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gewaesser/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Gruen/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Kennzeichnung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_PrivilegiertesVorhaben/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_SchutzPflegeEntwicklung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_SpielSportanlage/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Strassenverkehr/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_UnverbindlicheVormerkung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_VerEntsorgung/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Wasserwirtschaft/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)
and
(
every $id in //FP_Landwirtschaft/wirdAusgeglichenDurchSPE/@xlink:href satisfies
exists(//FP_SchutzPflegeEntwicklung[@gml:id = substring($id,2)])
)