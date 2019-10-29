declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';

for $h in //*[matches(local-name(), 'FP_NutzungsbeschraenkungsFlaeche|FP_TextlicheDarstellungsFlaeche|FP_ZentralerVersorgungsbereich')]
where not ($h/flaechenschluss/text() = 'false')
return $h/@gml:id/string()