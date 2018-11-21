declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //SO_Gebiet[sonstGebietsArt] satisfies
not($h/gebietsArt) or $h/gebietsArt/text() = '9999'