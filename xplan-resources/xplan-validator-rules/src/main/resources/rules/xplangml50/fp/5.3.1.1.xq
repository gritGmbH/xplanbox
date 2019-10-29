declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //FP_BebauungsFlaeche/besondereArtDerBaulNutzung
where not (
	(
		(
			$h/text() = '1000' or 
			$h/text() = '1100' or 
			$h/text() = '1200' or 
			$h/text() = '1300'
		)
		and not (
			$h/../allgArtDerBaulNutzung or
			$h/../allgArtDerBaulNutzung/text() = '1000'
		)
	)
	or
	(
		(
			$h/text() = '1400' or 
			$h/text() = '1500' or 
			$h/text() = '1550' or 
			$h/text() = '1600'
		)
		and not (
			$h/../allgArtDerBaulNutzung or
			$h/../allgArtDerBaulNutzung/text() = '2000'
		)
	)
	or
	(
		(
			$h/text() = '1700' or 
			$h/text() = '1800' 
		)
		and not (
			$h/../allgArtDerBaulNutzung or
			$h/../allgArtDerBaulNutzung/text() = '3000'
		)
	)	
	or
	(
		(
			$h/text() = '2000' or 
			$h/text() = '2100' or 
			$h/text() = '3000' or 
			$h/text() = '4000'
		)
		and not (
			$h/../allgArtDerBaulNutzung or
			$h/../allgArtDerBaulNutzung/text() = '4000'
		)
	)  
)
return $h/../@gml:id/string()