declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
every $h in //XP_DatumAttribut/name satisfies
$h != ''
)
and
(
every $h in //XP_DoubleAttribut/name satisfies
$h != ''
)
and
(
every $h in //XP_IntegerAttribut/name satisfies
$h != ''
)
and
(
every $h in //XP_StringAttribut/name satisfies
$h != ''
)
and
(
every $h in //XP_URLAttribut/name satisfies
$h != ''
)