declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
declare namespace xplan='http://www.xplanung.de/xplangml/4/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace wfs='http://www.opengis.net/wfs';
declare namespace xlink='http://www.w3.org/1999/xlink';
declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';

(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14000'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14001'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14002'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14003'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14004'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14005'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14006'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14007'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14008'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14009'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '14010'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)
and
(
  every $h in //FP_Strassenverkehr[besondereZweckbestimmung/text() = '1401'] satisfies
  not(exists($h/zweckbestimmung)) or $h/zweckbestimmung/text() = '1400'
)