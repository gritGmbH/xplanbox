declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

(
  every $h in //XP_Flaechenschlussobjekt[ebene/text()= '0'] satisfies
  $h/flaechenschluss='true'
)
or
(
  every $h in //XP_Flaechenschlussobjekt[not(ebene)] satisfies
  $h/flaechenschluss='true'
)