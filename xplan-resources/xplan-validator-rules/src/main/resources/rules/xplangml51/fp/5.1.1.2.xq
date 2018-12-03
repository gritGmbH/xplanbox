declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //FP_Plan[sonstPlanArt] satisfies
$h/planArt/text() = '9999'