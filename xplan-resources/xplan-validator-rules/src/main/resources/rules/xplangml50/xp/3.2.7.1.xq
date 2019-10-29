declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[ends-with(name(), 'Attribut')]
where ($h/name = '')
return $h/../../@gml:id/string()