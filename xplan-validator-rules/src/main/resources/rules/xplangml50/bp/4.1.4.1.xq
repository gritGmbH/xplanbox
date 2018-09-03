declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_Flaechenobjekt[not(ebene/text() = '0')] satisfies
$h/flaechenschluss/text() = 'false'