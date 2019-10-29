declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //BP_BaugebietsTeilFlaeche/sondernutzung
where not (
	(
		(
			$h/text() = '1000'
		)
		and not (
			$h/../besondereArtDerBaulNutzung or
			$h/../besondereArtDerBaulNutzung/text() = '3000'
		)
	)
	or
	(
		(
			$h/text() = '1100' or 
			$h/text() = '1200' or 
			$h/text() = '1300' or 
			$h/text() = '1400'
		)
		and not (
			$h/../besondereArtDerBaulNutzung or
			$h/../besondereArtDerBaulNutzung/text() = '2000'
		)
	)
	or
	(
		(
			/text() = '1500' or 
			/text() = '1600' or 
			/text() = '16000' or 
			/text() = '16001' or 
			/text() = '16002' or 
			/text() = '1700' or 
			/text() = '1800' or 
			/text() = '1900' or 
			/text() = '2000' or 
			/text() = '2100' or 
			/text() = '2200' or 
			/text() = '2300' or 
			/text() = '2400' or 
			/text() = '2500' or 
			/text() = '2600' or 
			/text() = '2700' or 
			/text() = '2800' or 
			/text() = '2900' or 
			/text() = '9999'
		)
		and not (
			$h/../besondereArtDerBaulNutzung or
			$h/../besondereArtDerBaulNutzung/text() = '2100'
		)
	)
)
return $h/../@gml:id/string()