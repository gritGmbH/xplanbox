declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //FP_Flaechenschlussobjekt[ebene/text() = '0' or not(exists(ebene))] satisfies
$h/flaechenschluss='true'