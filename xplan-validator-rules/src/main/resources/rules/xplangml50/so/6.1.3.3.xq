declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
every $id in //SO_Gebiet/refTextinhalt/@xlink:href satisfies
exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Bodenschutzrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Denkmalschutzrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Forstrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Luftverkehrsrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Schienenverkehrsrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_SchutzgebietNaturschutzrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_SchutzgebietSonstigesRecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_SchutzgebietWasserrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_SonstigesRecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Strassenverkehrsrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Wasserrecht/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)
and
(
    every $id in //SO_Grenze/refTextinhalt/@xlink:href satisfies
    exists(//SO_TextAbschnitt[@gml:id = substring($id,2)])
)