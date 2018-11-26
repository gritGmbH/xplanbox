declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_SpezielleBauweise[sonstTyp] satisfies
not($h/typ) or $h/typ/text() = '9999'