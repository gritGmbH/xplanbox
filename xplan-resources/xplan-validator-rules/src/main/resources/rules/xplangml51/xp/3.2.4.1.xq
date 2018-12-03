declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_ExterneReferenz[art/text() = 'Dokument'] satisfies
  not(exists($h/georefURL)) and not(exists($h/georefMimeType))