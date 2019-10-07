declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //XP_Hoehenangabe[hoehenbezug/text() = '3000']
where not (
	//*[ends-with(name(), '_Plan')]/bezugshoehe
)
return $h/../../@gml:id/string()