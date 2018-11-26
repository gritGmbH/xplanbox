declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $h in //XP_BegruendungAbschnitt satisfies
  (exists($h/text) or exists($h/refText/@xlink:href)) and
  (not(exists($h/text)) and not(exists($h/refText/@xlink:href)))