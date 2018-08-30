declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //XP_TextAbschnitt satisfies
  (exists($h/text) or exists($h/refText)) and
  not(exists($h/text) and exists($h/refText))