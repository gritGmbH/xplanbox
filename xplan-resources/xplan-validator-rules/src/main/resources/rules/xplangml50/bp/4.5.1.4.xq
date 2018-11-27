declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_BaugebietsTeilFlaeche[abweichendeBauweise] satisfies
not(exists($h/bauweise)) or $h/bauweise/text() = '3000'
