declare default element namespace 'http://www.xplanung.de/xplangml/5/0';

every $h in //SO_Gebiet[sonstGebietsArt] satisfies
not(exists($h/gebietsArt)) or $h/gebietsArt/text() = '9999'