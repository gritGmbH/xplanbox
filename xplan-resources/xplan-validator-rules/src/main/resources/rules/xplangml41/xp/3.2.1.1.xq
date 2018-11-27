declare default element namespace 'http://www.xplanung.de/xplangml/4/1';

every $h in //XP_TextAbschnitt satisfies
(exists($h/text) or exists($h/refText)) and
not(exists($h/text) and exists($h/refText))