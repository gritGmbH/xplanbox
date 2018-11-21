declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

(
every $h in //BP_AbgrabungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_AufschuettungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_AusgleichsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_BesondererNutzungszweckFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_AbgrabungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_BodenschaetzeFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_BaugebietsTeilFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_GemeinbedarfsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_GewaesserFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_GruenFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_KleintierhaltungFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_SpielSportanlagenFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_StrassenVerkehrsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_VerkehrsflaecheBesondererZweckbestimmung[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_WaldFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_KennzeichnungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_RekultivierungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_SchutzPflegeEntwicklungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_AbstandsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_EingriffsBereich[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_ErhaltungsBereichFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_FoerderungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_FreiFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_GebaeudeFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_GemeinschaftsanlagenFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_NebenanlagenAusschlussFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_NebenanlagenFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_PersGruppenBestimmteFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_RegelungVergnuegungsstaetten[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_SpezielleBauweise[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_TechnischeMassnahmenFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_TextlicheFestsetzungsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_UeberbaubareGrundstuecksFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_Veraenderungssperre[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)
and
(
every $h in //BP_WasserwirtschaftsFlaeche[ebene != '0'] satisfies
$h/flaechenschluss/text() = 'false'
)