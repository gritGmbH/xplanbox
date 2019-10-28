declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //XP_BegruendungAbschnitt
where
  not (
    $h/text
    or
    $h/refText
  )
  or
  (
    $h/text and $h/refText
  )
return $h/@gml:id