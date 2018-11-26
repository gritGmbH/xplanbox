declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
    every $h in //BP_AbstandsFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_EingriffsBereich satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_ErhaltungsBereichFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_FoerderungsFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_FreiFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_GebaeudeFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_GemeinschaftsanlagenFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_NebenanlagenAusschlussFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_NebenanlagenFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_PersGruppenBestimmteFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_RegelungVergnuegungsstaetten satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_SpezielleBauweise satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_TechnischeMassnahmenFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_TextlicheFestsetzungsFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_UeberbaubareGrundstuecksFlaeche satisfies
    $h/flaechenschluss/text() ='false'
)
and
(
    every $h in //BP_Veraenderungssperre satisfies
    $h/flaechenschluss/text() ='false'
)