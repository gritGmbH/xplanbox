declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
every $h in //BP_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))
)
and
(
  every $h in //FP_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))
)
and

(
  every $h in //LP_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))
)
and

(
  every $h in //RP_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))
)
and

(
  every $h in //SO_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))
)