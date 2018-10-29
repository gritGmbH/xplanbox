declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //SO_Gebiet[ebene != '0'] satisfies
$h/flaechenschluss = 'false'