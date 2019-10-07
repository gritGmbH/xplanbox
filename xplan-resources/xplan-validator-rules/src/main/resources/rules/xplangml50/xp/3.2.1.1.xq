declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[ends-with(name(), '_TextAbschnitt')]
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