declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[ends-with(name(), 'ExterneReferenz')]
where (
  $h/art/text() = 'Dokument'
  and
  (
    $h/georefURL or $h/georefMimeType
  )
)
return $h/../../@gml:id/string()