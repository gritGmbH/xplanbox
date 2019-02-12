declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //BP_UeberbaubareGrundstuecksFlaeche[abweichendeBauweise] satisfies
not($h/bauweise) or $h/bauweise = 3000