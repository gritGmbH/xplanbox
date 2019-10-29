declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //XP_Hoehenangabe
where
  not (
    $h/hoehenbezug
    or
    $h/abweichenderHoehenbezug
  )
  or
  (
    $h/hoehenbezug and $h/abweichenderHoehenbezug
  )
return $h/../../@gml:id/string()