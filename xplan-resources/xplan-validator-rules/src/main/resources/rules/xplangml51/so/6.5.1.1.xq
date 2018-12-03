declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //SO_Grenze[sonstTyp] satisfies
not($h/typ) or $h/typ/text() = '9999'