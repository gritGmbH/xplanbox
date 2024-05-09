# Testplan der xPlanBox

## Komponente XPlanWMS

### Transparente Zeichenvorschriften im XPlanWMS ermöglichen

#### Testschritt 1:
- **Aktion**: Der Benutzer führt im Browser die !GetMap-Anfrage aus.
- **Erwartetes Ergebnis**: Die Kartengraphik wird angezeigt.

#### Testschritt 2:
- **Aktion**: Der Benutzer tauscht in der URL den WMS-Endpoint von WMS in WMSpre aus. Der Style ist default.
- **Erwartetes Ergebnis**: Die angezeigte Kartengraphik enthält eine transparente Darstellung.

#### Testschritt 3:
- **Aktion**: Der Benutzer tauscht in der URL den WMS-Endpoint von WMS in WMSpre aus. Der Style ist vollflaechig.
- **Erwartetes Ergebnis**: Die angezeigte Kartengraphik enthält eine vollflächige Darstellung.

#### Testschritt 4:
- **Aktion**: Der Benutzer tauscht in der URL den WMS-Endpoint von WMSpre in WMS aus. Der Style ist default.
- **Erwartetes Ergebnis**: Die angezeigte Kartengraphik enthält eine vollflächige Darstellung.

#### Testschritt 5:
- **Aktion**: Der Benutzer tauscht in der URL den WMS-Endpoint von WMSpre in WMS aus. Der Style ist transparent.
- **Erwartetes Ergebnis**: Die angezeigte Kartengraphik enthält eine transparente Darstellung.

## Komponente XPlanWMS

### Unterstützung von Planarchivierung

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert einen Plan.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster.

#### Testschritt 2:
- **Aktion**: Der Benutzer gibt einen Rechtsstand an.
- **Erwartetes Ergebnis**: Der Plan wird in der entsprechenden Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt die Schritte 01 und 02 mit unterschiedlichen Rechtsstand- Angaben durch.
- **Erwartetes Ergebnis**: Der Plan wird in der entsprechenden Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.

## Komponente XPlanWMS

### Umringe immer sichtbar

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert einen Plan mit einem unbegrenzten Gültigkeitszeitraum.
- **Erwartetes Ergebnis**: Der Plan wird importiert. Der Gültigkeitszeitraum ist grün.

#### Testschritt 2:
- **Aktion**: Der Benutzer importiert einen Plan mit einem bereits abgelaufenen Gültigkeitszeitraum.
- **Erwartetes Ergebnis**: Der Plan wird importiert. Der Gültigkeitszeitraum ist rot.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt eine GetMap-Anfrage mit den Layer-Angaben bp_plan und bp_XYZ für das Gebiet des importierten Plans aus Schritt 01 aus.
- **Erwartetes Ergebnis**: Die Umringe des Plans sowie die Daten von bp_XYZ werden angezeigt.

#### Testschritt 4:
- **Aktion**: Der Benutzer führt eine GetMap-Anfrage mit den Layer-Angaben bp_plan und bp_XYZ für das Gebiet des importierten Plans aus Schritt 02 aus.
- **Erwartetes Ergebnis**: Es werden nur die Umringe des Plans angezeigt.

## Komponente XPlanWMS

### GetMap URL für spezifischen Plan über GetFeature zusammenstellen

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt hinter einem beliebigen Plan auf die Schaltfläche „Kartenvorschau“.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt im neu geöffneten Fenster auf „Plan in neuem Fenster öffnen“ (GetMap-Anfrage).
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Browserfenster mit der entsprechenden Karte.

#### Testschritt 3:
- **Aktion**: Der Benutzer überprüft die Form der URL.
- **Erwartetes Ergebnis**: Die URL ist OGC-konform. (http...LAYERS=...)

#### Testschritt 4:
- **Aktion**: Der Benutzer überprüft die Anzahl der Layer in der URL.
- **Erwartetes Ergebnis**: Die Anzahl stimmt mit den im jeweiligen Plan enthaltenen Layern überein.

## Komponente XPlanWMS

### Sortierung der Visualisierung nach anderem Datumsfeld

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert einen bereits vorhandene Plan erneut.
- **Erwartetes Ergebnis**: Der Plan wird importiert.

#### Testschritt 2:
- **Aktion**: Der Benutzer drückt auf die Schaltfläche „editieren“ des neu importierten Plans.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 3:
- **Aktion**: Der Benutzer verändert das „Rechtsverordungsdatum“ in ein zurückliegendes Datum und verändert ein Attribut. Die Änderung ist valide.
- **Erwartetes Ergebnis**: Die geänderten Daten sind gespeichert.

#### Testschritt 4:
- **Aktion**: Der Benutzer überprüft die Änderung mit Hilfe einer GetMap-Anfrage.
- **Erwartetes Ergebnis**: Der geänderte Plan wird auf der Karte im Hintergrund angezeigt.

#### Testschritt 5:
- **Aktion**: Der Benutzer importiert einen bereits vorhandene Plan erneut.
- **Erwartetes Ergebnis**: Der Plan wird importiert.

#### Testschritt 6:
- **Aktion**: Der Benutzer drückt auf die Schaltfläche „editieren“ des neu importierten Plans.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 7:
- **Aktion**: Der Benutzer verändert das „Rechtsverordungsdatum“ in ein zukünftiges Datum und verändert ein Attribut. Die Änderung erfolgt valide.
- **Erwartetes Ergebnis**: Speicherung der Änderung.

#### Testschritt 8:
- **Aktion**: Der Benutzer überprüft die Änderung mit Hilfe eines GetMap-Anfrage.
- **Erwartetes Ergebnis**: Der geänderte Plan wird auf der Karte im Vordergrund angezeigt.

## Komponente XPlanWMS

### Betrieb der Geo-Dienste mit Load Balancer

#### Testschritt 1:
- **Aktion**: Der Systemadministrator beobachtet das Verhalten des Systems, während ein Benutzer einen Plan mit Rasterdaten importiert.
- **Erwartetes Ergebnis**: In beiden WMS-Instanzen wird ein Workspace-Reload ausgeführt.

#### Testschritt 2:
- **Aktion**: Der Benutzer überprüft die Kartenansichten beider WMS-Dienste im Bereich des importierten Plans mit einer geeigneten GetMap-Anfrage.
- **Erwartetes Ergebnis**: Beide WMS-Dienste geben die gleiche Kartenansicht aus.

## Komponente XPlanWMS

### GetFeatureInfo-Ausgaben des WMS

#### Testschritt 1:
- **Aktion**: Der Benutzer wählt die GetFeatureInfo-Funktion in QGIS aus.
- **Erwartetes Ergebnis**: Die GetFeatureInfo-Funktion ist in QGIS ausgewählt.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt in der Karte auf sichtbares Fachobjekt.
- **Erwartetes Ergebnis**: Ein sichtbares Fachobjekt ist ausgewählt.

#### Testschritt 3:
- **Aktion**: Der Benutzer kontrolliert die Ausgabe auf Lesbarkeit, richtige Wiedergabe der gewünschten Infos und Vollständigkeit.
- **Erwartetes Ergebnis**: Die Ausgabe ist lesbar und es werden die gewünschten Infos wiedergegeben.

## Komponente XPlanWMS

### Visualisierung von importierten XPlanGML-Rasterdaten

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert mit dem Befehl in [1] einen Plan mit Rasterdaten in den XPlanManager.
- **Erwartetes Ergebnis**: Der Plan wird in den XPlanManager importiert.

#### Testschritt 2:
- **Aktion**: Der Benutzer lässt sich den importierten Plan mit dem Befehl in [2] auflisten.
- **Erwartetes Ergebnis**: Der importierte Plan wird aufgelistet.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt eine GetMap-Anfrage wie in [3] durch.
- **Erwartetes Ergebnis**: Der importierte Rasterplan wird dargestellt.

## Komponente XPlanWMS

### Gültigkeitszeitraum für Rasterdaten in XPlanWMS unterstützen

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert einen Rasterplan mit unbegrenztem Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung".
- **Erwartetes Ergebnis**: Der Plan wird importiert. Der Gültigkeitszeitraum ist grün.

#### Testschritt 2:
- **Aktion**: Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird)
- **Erwartetes Ergebnis**: Der Workspace wird neu geladen.

#### Testschritt 3:
- **Aktion**: Der Benutzer öffnet die Kartenvorschau
- **Erwartetes Ergebnis**: Der Rasterplan wird abgebildet.

#### Testschritt 4:
- **Aktion**: Der Benutzer löscht den zuvor importierten Plan.
- **Erwartetes Ergebnis**: Der Plan wurde gelöscht.

#### Testschritt 5:
- **Aktion**: Der Benutzer importiert den gleichen Rasterplan erneut mit einem in der Vergangenheit liegenden Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung".
- **Erwartetes Ergebnis**: Der Plan wird importiert. Der Gültigkeitszeitraum ist rot.

#### Testschritt 6:
- **Aktion**: Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird)
- **Erwartetes Ergebnis**: Der Workspace wird neu geladen.

#### Testschritt 7:
- **Aktion**: Der Benutzer öffnet die Kartenvorschau
- **Erwartetes Ergebnis**: Es wird lediglich der Umring des Vektorplans abgebildet.

#### Testschritt 8:
- **Aktion**: Der Benutzer löscht den zuvor importierten Plan.
- **Erwartetes Ergebnis**: Der Plan wurde gelöscht.

#### Testschritt 9:
- **Aktion**: Der Benutzer importiert den gleichen Plan erneut mit einem in der Zukunft liegenden Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung".
- **Erwartetes Ergebnis**: Der Plan wird importiert. Der Gültigkeitszeitraum ist rot.

#### Testschritt 10:
- **Aktion**: Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird)
- **Erwartetes Ergebnis**: Der Workspace wird neu geladen.

#### Testschritt 11:
- **Aktion**: Der Benutzer öffnet die Kartenvorschau.
- **Erwartetes Ergebnis**: Es wird lediglich der Umring des Vektorplans abgebildet.

#### Testschritt 12:
- **Aktion**: Der Benutzer klickt auf den Button Editieren.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit dem editier Formular.

#### Testschritt 13:
- **Aktion**: Der Benutzer verändert den Gültigkeitszeitraum auf einen unbegrenzten Zeitraum und drückt auf speichern.
- **Erwartetes Ergebnis**: Der Gültigkeitszeitraum wird grün dargestellt.

#### Testschritt 14:
- **Aktion**: Der Benutzer öffnet die Kartenvorschau.
- **Erwartetes Ergebnis**: Der Rasterplan wird abgebildet.

## Komponente DB-Aktualisierung

### Ausführung der SQL-Skripte zur Aktualisierung des Datenbankschemas

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den SQL-Befehl SELECT tag FROM databasechangelog WHERE versionid='neu'
- **Erwartetes Ergebnis**: Die SQL-Abfrage liefert kein Ergebnis, da es die Tabelle databasechangelog in der Version 'neu' noch nicht gibt.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt die DB-Skripte zur Aktualisierung des Datenbankschemas zur XPlanBox Version 'neu' aus.
- **Erwartetes Ergebnis**: Es treten keine Fehlermeldungen auf.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den SQL-Befehl SELECT tag FROM databasechangelog WHERE versionid='neu'
- **Erwartetes Ergebnis**: Die SQL-Abfrage liefert genau einen Treffer mit der Version 'neu'.

## Komponente DB-Aktualisierung

### (Optional) Ausführen des Kommandozeilenwerkzeug reSynthesizer

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den reSynthesiser aus (siehe 6. XPlanUpdateDataCLI)

- **Erwartetes Ergebnis**: Es treten keine Fehlermeldungen auf.

#### Testschritt 2:
- **Aktion**: Der Benutzer kontrolliert die in der Datenhaltung vorliegenden Daten auf Vollständigkeit.
- **Erwartetes Ergebnis**: Alle vorherigen Daten sind auch im neuen XPlanSyn-Schema vorhanden.

## Komponente DB-Aktualisierung

### (Optional) Ausführen des Kommandozeilenwerkzeug EvaluationSchemaSynchronizer

#### Testschritt 1:
- **Aktion**: Falls vorhanden, muss der Benutzer die Datenbankschemas 'xplanevaluationxplansynpre', 'xplanevaluationxplansyn' und 'xplanevaluationxplansynarchive' löschen.
- **Erwartetes Ergebnis**: Die Datenbankschemas können erfolgreich gelöscht werden.

#### Testschritt 2:
- **Aktion**: Der Benutzer legt die Datenbankschemas mit Hilfe der SQL-Skipte im EvaluationSchemaSynchronizer an.
- **Erwartetes Ergebnis**: Es treten keine Fehlermeldungen auf, die Datenbankschemas existieren.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den EvaluationSchemaSynchronizer mit der Option --type ALL aus (siehe 8. XPlanAuswerteschemaCLI).
- **Erwartetes Ergebnis**: Es treten keine Fehlermeldungen auf.

#### Testschritt 4:
- **Aktion**: Der Benutzer kontrolliert die in der Datenhaltung vorliegenden Daten darauf, dass die im jeweiligen XPlanSyn-Schema gespeicherten Daten auch dem neu erstellten Auswerteschema gleichen.
- **Erwartetes Ergebnis**: Die im XPlanSyn-Schema vorliegenden Daten gleichen dem jeweiligen Auswerteschema.

## Komponente XPlanValidatorWeb

### Webschnittstelle XPlanValidator

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft, ob die Web-basierte Benutzeroberfläche des XPlanValidators geöffnet ist.
- **Erwartetes Ergebnis**: Die Web-basierte Benutzeroberfläche des XPlanValidators ist geöffnet.

## Komponente XPlanValidatorWeb

### Planarchiv auswählen

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators.
- **Erwartetes Ergebnis**: Die Eingabesicht hat eine Möglichkeit, ein Planarchiv auszuwählen.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt auf Datei auswählen.
- **Erwartetes Ergebnis**: Ein neues Fenster öffnet sich.

#### Testschritt 3:
- **Aktion**: Der Benutzer wählt ein Planarchiv (*.zip) aus und klickt auf OK.
- **Erwartetes Ergebnis**: Das Fenster schließt sich. Der Planname wird in der Web-basierten Benutzeroberfläche des XPlanValidators angezeigt.

#### Testschritt 4:
- **Aktion**: Der Benutzer wiederholt Schritt 01-04 mit einem Plan im Format *.gml.
- **Erwartetes Ergebnis**: Der Planname wird in der Web-basierten Benutzeroberfläche des XPlanValidators angezeigt.

## Komponente XPlanValidatorWeb

### Eingabe einer Bezeichnung für den Validierungsdurchlauf

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt auf den Button Hochladen und Validierungsoptionen einstellen.
- **Erwartetes Ergebnis**: Es öffnet sich ein Fenster mit Plan hochladen und dem Namen des ausgewählten Planarchivs.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt auf Abbrechen.
- **Erwartetes Ergebnis**: Das Fenster schließt sich, das Planarchiv ist aber immer noch ausgewählt.

#### Testschritt 3:
- **Aktion**: Der Benutzer klickt auf den Button Hochladen und Validierungsoptionen einstellen.
- **Erwartetes Ergebnis**: Es öffnet sich ein Fenster mit Plan hochladen und dem Namen des ausgewählten Planarchivs.

#### Testschritt 4:
- **Aktion**: Der Benutzer klickt auf Zur Validierung.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit den Validierungsoptionen.

#### Testschritt 5:
- **Aktion**: Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators.
- **Erwartetes Ergebnis**: Die Eingabesicht hat ein Eingabefeld Bezeichnung für den Report

#### Testschritt 6:
- **Aktion**: Der Benutzer gibt eine Bezeichnung in das Eingabefeld ein.
- **Erwartetes Ergebnis**: Im Validierungsbericht, sowie in den Reports, steht unter "Name" die eingegebene Bezeichnung.

## Komponente XPlanValidatorWeb

### Auswahl eines Validierungstyps

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators.
- **Erwartetes Ergebnis**: Die Eingabesicht hat eine Auswahl an Validierungstypen.

#### Testschritt 2:
- **Aktion**: Der Benutzer wählt durch das anklicken eines Kästchens einen Validierungstyp aus.
- **Erwartetes Ergebnis**: Der ausgewählte Validierungstyp wird anhand eines Häckchens im Kästchen angezeigt.

## Komponente XPlanValidatorWeb

### Auswahl eines Profils

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators.
- **Erwartetes Ergebnis**: Die Eingabesicht hat eine Auswahl an Profilen.

#### Testschritt 2:
- **Aktion**: Der Benutzer wählt durch das anklicken eines Kästchens ein Profil aus.
- **Erwartetes Ergebnis**: Das ausgewählte Profil wird anhand eines Häckchens im Kästchen angezeigt.

## Komponente XPlanValidatorWeb

### Validierung starten und abbrechen

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt auf den Button Validierung abbrechen.
- **Erwartetes Ergebnis**: Die Validierungsoptionen werden geschlossen, das Planarchiv gelöscht.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt auf den Button Validierung starten.
- **Erwartetes Ergebnis**: Ein neues Fenster mit dem Validierungsergebnis öffnet sich.

## Komponente XPlanValidatorWeb

### Download der Validierungsergebnisse

#### Testschritt 1:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den HTML Report aus.
- **Erwartetes Ergebnis**: Der HTML Report ist ausgewählt.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Der Report wird als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 3:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den PDF Report aus.
- **Erwartetes Ergebnis**: Der PDF Report ist ausgewählt.

#### Testschritt 4:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Der Report wird als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 5:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den XML Report aus.
- **Erwartetes Ergebnis**: Der XML Report ist ausgewählt.

#### Testschritt 6:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Der Report wird als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 7:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den Geometriefehler Shapefile aus.
- **Erwartetes Ergebnis**: Der Geometriefehler Shapefile ist ausgewählt.

#### Testschritt 8:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Das Shapefile wird als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 9:
- **Aktion**: Der Benutzer lädt das exportiere Shapefile in ein beliebiges GIS Tool.
- **Erwartetes Ergebnis**: Das Shapefile wird im GIS angezeigt.

#### Testschritt 10:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den Geometriefehler Grafik aus.
- **Erwartetes Ergebnis**: Der Geometriefehler Grafik ist ausgewählt.

#### Testschritt 11:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Die Grafik wird als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 12:
- **Aktion**: Der Benutzer öffnet die exportiere Grafik
- **Erwartetes Ergebnis**: Der Benutzer öffnet die exportiere Grafik

#### Testschritt 13:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den HTML Report, PDF Report und XML Report aus.
- **Erwartetes Ergebnis**: Der HTML Report, PDF Report und XML Report ist ausgewählt.

#### Testschritt 14:
- **Aktion**: Der Benutzer klickt auf Download
- **Erwartetes Ergebnis**: Die Reporte werden als Zip-Datei zum Herunterladen angeboten.

#### Testschritt 15:
- **Aktion**: Der Benutzer wählt in der Rubrik Downloads den Geometriefehler Shapefile und Grafik aus.
- **Erwartetes Ergebnis**: Die Geometriefehler Shapefile und Grafik sind ausgewählt.

#### Testschritt 16:
- **Aktion**: Der Benutzer klickt auf Download.
- **Erwartetes Ergebnis**: Die Reporte werden als Zip-Datei zum Herunterladen angeboten.

## Komponente XPlanValidatorWeb

### Schaltfläche um einen weiteren Plan zu validieren

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Benutzeroberfläche.
- **Erwartetes Ergebnis**: Die Benutzeroberfläche enthält einen Button weiteren Plan validieren

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt auf den Button weitere Plan validieren.
- **Erwartetes Ergebnis**: Der Benutzer wird auf die Eingabesicht weitergeleitet.

## Komponente XPlanValidatorWeb

### Ausgabe von Syntaxfehlern

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt auf Datei auswählen.
- **Erwartetes Ergebnis**: Ein neues Fenster öffnet sich.

#### Testschritt 2:
- **Aktion**: Der Benutzer wählt ein ein Planarchiv mit Syntaxfehlern im xplan.gml aus und klickt auf OK.
- **Erwartetes Ergebnis**: Das Fenster schließt sich. Der Planname wird in der Web-basierten Benutzeroberfläche des XPlanValidators angezeigt.

#### Testschritt 3:
- **Aktion**: Der Benutzer startet die Validerung durch das Drücken des Buttons Validierung starten.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit dem Ergebnis der Validierung.

#### Testschritt 4:
- **Aktion**: Der Benutzer überprüft die Ausgabe der Syntaxfehler.
- **Erwartetes Ergebnis**: Die Syntaxfehler enthalten Zeilenangaben und den Hinweis, dass das Instanzobjekt nicht zum XPlanGML Schema passt und überprüft werden sollte.

## Komponente Automatisierte SoapUI-Tests

### Ausführung der automatisierten SoapUI-Tests

#### Testschritt 2:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-validator-api-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: XPlanValidatorAPI TestSuite: Alle Tests werden erfolgreich bestanden. Profiles TestSuite: Alle Test werden bestanden, wenn mindestens ein Profil aktiviert ist.

#### Testschritt 1:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-manager-api-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: XPlanManagerAPI TestSuite: Alle Tests werden erfolgreich bestanden. Profiles TestSuite: Alle Test werden bestanden, wenn mindestens ein Profil aktiviert ist. Codelists TestSuite: Alle Tests werden  bestanden, wenn die angegeben Codeliste konfiguriert ist.

#### Testschritt 5:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-dokumente-api-soapui-project.xml in SoapUI aus
- **Erwartetes Ergebnis**: Alle Tests werden erfolgreich bestanden.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-webservices-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: xplan-wms: Alle Tests werden erfolgreich bestanden, Ausnahme: RESTAPI wird nur bestanden, wenn /config des xplan-wms erreichbar ist.  xplansyn-wfs, xplan-wfs: Alle Tests werden erfolgreich bestanden. xplan-inspireplu: Tests werden nur bestanden, wenn XPlanInspirePLU erreichbar ist. mapserver-wms, mapproxy-wms, mapproxy-wmts: Alle Tests werden erfolgreich bestanden.

## Komponente XPlanManagerWeb

### Plan-Funktion: Hinzufügen

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt auf den Button Datei auswählen.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster zur Auswahl eines Planarchivs.

#### Testschritt 2:
- **Aktion**: Der Benutzer wählt eine beliebige Datei (außer ein Plan im Zip-Format) aus klickt auf Öffnen.
- **Erwartetes Ergebnis**: Das Fenster schließt sich. Die beliebige Datei wird zwischengelagert in der Weboberfläche angezeigt.

#### Testschritt 3:
- **Aktion**: Der Benutzer klickt auf den Button Hinzufügen.
- **Erwartetes Ergebnis**: Es wird ein Fenster geöffnet. Die Datei wird abgelehnt und nicht hoch geladen.

#### Testschritt 4:
- **Aktion**: Der Benutzer klickt auf den Button Schließen.
- **Erwartetes Ergebnis**: Das Fenster wird geschlossen.

#### Testschritt 5:
- **Aktion**: Der Benutzer klickt auf den Button Datei auswählen.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster zur Auswahl eines Planarchivs.

#### Testschritt 6:
- **Aktion**: Der Benutzer wählt einen Plan im Zip-Format aus.
- **Erwartetes Ergebnis**: Das Fenster schließt sich. Der Planname wird zwischengelagert in der Weboberfläche angezeigt.

#### Testschritt 7:
- **Aktion**: Der Benutzer klickt auf den Button Hinzufügen.
- **Erwartetes Ergebnis**: Der Plan wird hoch geladen. Nach Ende des Uploads wird ein neues Fenster geöffnet, mit der Meldung, dass der Upload abgeschlossen ist.

#### Testschritt 8:
- **Aktion**: Der Benutzer klickt auf Ok.
- **Erwartetes Ergebnis**: Das Fenster wird geschlossen. Der Plan wird angezeigt.

#### Testschritt 9:
- **Aktion**: Der Benutzer klickt auf Entfernen.
- **Erwartetes Ergebnis**: Es wird ein neues Fenster geöffnet, mit der Meldung, ob der Plan wirklich entfernt werden soll.

#### Testschritt 10:
- **Aktion**: Der Benutzer klickt auf Ok.
- **Erwartetes Ergebnis**: Es wird ein neues Fenster geöffnet, mit der Meldung, dass das Entfernen abgeschlossen ist.

#### Testschritt 11:
- **Aktion**: Der Benutzer klickt auf Ok.
- **Erwartetes Ergebnis**: Es ist kein Plan aufgelistet, welcher zur Validation aussteht.

## Komponente XPlanManagerWeb

### Plan-Funktion: Validieren

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einem hochgeladenen Plan auf den Button Validieren.
- **Erwartetes Ergebnis**: Ein neues Fenster mit dem XPlanValidator öffnet sich.

#### Testschritt 2:
- **Aktion**: Der Benutzer vergibt eine Bezeichnung.
- **Erwartetes Ergebnis**: Die Bezeichnung wird im Feld dargestellt.

#### Testschritt 3:
- **Aktion**: Der Benutzer wählt einen Validierungstyp aus.
- **Erwartetes Ergebnis**: Der Validierungstyp wird im Feld dargestellt.

#### Testschritt 4:
- **Aktion**: Der Benutzer wählt ein Profil aus.
- **Erwartetes Ergebnis**: Das Profil wird im Feld dargestellt.

#### Testschritt 5:
- **Aktion**: Der Benutzer startet die Validierung.
- **Erwartetes Ergebnis**: Das Validierungsergebnis wird dargestellt.

#### Testschritt 6:
- **Aktion**: Der Benutzer kehrt zu der Seite mit dem importierten Plan zurück.
- **Erwartetes Ergebnis**: Die Web-Oberfläche des XPlanManagers wird angezeigt.

#### Testschritt 7:
- **Aktion**: Die Schaltfläche Validieren je nach Ergebnis rot (Validierung fehlgeschlagen).
- **Erwartetes Ergebnis**: Bei erfolgreicher Validierung wird die Schaltfläche Import freigegeben.

#### Testschritt 8:
- **Aktion**: Die Schaltfläche Validieren je nach Ergebnis grün (Validierung erfolgreich) eingefärbt.
- **Erwartetes Ergebnis**: Bei fehlgeschlagener Validierung wird die Schaltfläche Import nicht freigegeben.

## Komponente XPlanManagerWeb

### Plan-Funktion: Import

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einen syntaktisch, semmantisch oder geometrisch invaliden Plan auf den Button Import.
- **Erwartetes Ergebnis**: Der Button ist deaktiviert und kann nicht angeklickt werden.

#### Testschritt 2:
- **Aktion**: Der Benutzer klickt neben einen validen Plan mit Rasterdaten auf den Button Import.
- **Erwartetes Ergebnis**: Es öffnet sich ein Fenster zur Angabe des Gültigkeitszeitraums.

#### Testschritt 3:
- **Aktion**: Der Benutzer wählt einen Gültigkeitszeitraum aus oder klickt ohne Angaben den Button Weiter.
- **Erwartetes Ergebnis**: Es öffnet sich ein weiteres Fenster um den Rechtsstand auszuwählen.

#### Testschritt 4:
- **Aktion**: Der Benutzer wählt einen Rechtsstand aus oder klickt ohne Änderung den Button Weiter.
- **Erwartetes Ergebnis**: Es öffnet sich ein weiteres Fenster für die Analyse der Rasterdaten.

#### Testschritt 5:
- **Aktion**: Der Benutzer klickt den Button Weiter mit Rasterdaten, klickt den Button Weiter ohne Rasterdaten oder bricht den Import ab mit dem Button Abbrechen.
- **Erwartetes Ergebnis**: Beim klicken von den Button Weiter mit/ohne Rasterdaten wird Plan importiert, es öffnet sich ein Fenster mit der Meldung, dass der Import abgeschlossen ist.

#### Testschritt 6:
- **Aktion**: Der Benutzer wählt klickt den Button Ok.
- **Erwartetes Ergebnis**: Der Plan wird in der Plan-Liste angezeigt.

#### Testschritt 7:
- **Aktion**: Der Benutzer klickt neben einen validen Plan (im Format *.gml) auf Import.
- **Erwartetes Ergebnis**: Es öffnet sich ein Fenster zur Angabe des Gültigkeitszeitraums.

#### Testschritt 8:
- **Aktion**: Der Benutzer wählt einen Gültigkeitszeitraum aus oder klickt ohne Angaben den Button Weiter.
- **Erwartetes Ergebnis**: Es öffnet sich ein weiteres Fenster um den Rechtsstand auszuwählen.

#### Testschritt 9:
- **Aktion**: Der Benutzer wählt einen Rechtsstand aus oder klickt ohne Änderung den Button Weiter.
- **Erwartetes Ergebnis**: Beim klicken von des Button Weiter wird der Plan importiert; es öffnet sich ein Fenster mit der Meldung, dass der Import abgeschlossen ist.

#### Testschritt 10:
- **Aktion**: 
Der Benutzer wählt klickt den Button Ok.
- **Erwartetes Ergebnis**: Der Plan wird in der Plan-Liste angezeigt.

## Komponente XPlanManagerWeb

### Plan-Liste

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Oberfläche des XPlanManagers auf die in [1] aufgelisteten Punkte.
- **Erwartetes Ergebnis**: Die in [1] aufgelisteten Punkte sind vorhanden.

#### Testschritt 2:
- **Aktion**: Der Benutzer überprüft die tabellarische Anzeige des XPlanManagers auf die in [2] aufgelisteten Punkte.
- **Erwartetes Ergebnis**: Die in [2] aufgelisteten Punkte sind vorhanden.

## Komponente XPlanManagerWeb

### Plan-Funktion: Kartenvorschau

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einen Plan auf den Button Kartenvorschau.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster. Der Plan wird in einer Vorschau angezeigt.

## Komponente XPlanManagerWeb

### Plan-Funktion: Plan publizieren (Transformation nach INSPIRE PLU)

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einen Plan auf den Button Plan publizieren.
- **Erwartetes Ergebnis**: Es öffnet sich ein Pop-up, welches den Vorgang bestätigt.

#### Testschritt 2:
- **Aktion**: Der Benutzer geht auf die xPlanBox Landingpage und öffnet die Capabilities der XPlanInspirePluDienste.
- **Erwartetes Ergebnis**: Die Capabilities des XPlanInspirePluWFS und XPlanInspirePluWMS werden erfolgreich angezeigt.

#### Testschritt 3:
- **Aktion**: Der Benutzer testet mit einer Geoinformationssystemssoftware wie z.B. QGIS, ob der in Testschritt 01 publizierte Plan durch die Dienste dargestellt wird.
- **Erwartetes Ergebnis**: Der publizierte Plan wird erfolgreich durch die Dienste dargestellt.

## Komponente XPlanManagerWeb

### Plan-Funktion: Editieren

#### Testschritt 1:
- **Aktion**: Der Benutzer überprüft die Möglichkeit der Editierbarkeit.
- **Erwartetes Ergebnis**: Hinter den Plänen wird je eine Schaltfläche editieren  angezeigt.

#### Testschritt 2:
- **Aktion**: Der Benutzer drückt auf die Schaltfläche editieren.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 3:
- **Aktion**: Der Benutzer editiert die Stammdaten in den Basisdaten und Gültigkeitszeitraum valide und bestätigt die Änderung durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Speicherung der Veränderungen.

#### Testschritt 4:
- **Aktion**: Der Benutzer editiert die Stammdaten in den Basisdaten und Gültigkeitszeitraum nicht valide und bestätigt die Änderung durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Es öffnet sich eine Fehlermeldung mit dem Vermerk auf den Fehler.

#### Testschritt 5:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 6:
- **Aktion**: Der Benutzer klickt auf Änderung hinzufügen.
- **Erwartetes Ergebnis**: Es öffnet sich ein Dialog mit einem Formular.

#### Testschritt 7:
- **Aktion**: Der Benutzer editiert die Daten unter Neue Änderung anlegen ohne dabei falsche Eingaben zu machen und bestätigt das Ergebnis durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Die geänderten Daten werden in die Liste der Änderungen übernommen.

#### Testschritt 8:
- **Aktion**: Der Benutzer editiert die Daten unter Neue Änderung anlegen, wobei kein Planname angegeben wird, und bestätigt die Änderung durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Es wird eine Fehlermeldung mit dem Vermerk auf den fehlenden Plannamen angezeigt.

#### Testschritt 9:
- **Aktion**: Der Benutzer fügt einen Plannamen ein und bestätigt die Änderung durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Die geänderten Daten werden in die Liste der Änderungen übernommen.

#### Testschritt 22:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 23:
- **Aktion**: Der Benutzer klickt auf Text hinzufügen.
- **Erwartetes Ergebnis**: Es öffnet sich ein Dialog mit einem Formular.

#### Testschritt 24:
- **Aktion**: Der Benutzer editiert die Daten unter Neuen Text anlegen ohne dabei falsche Eingaben zu machen und bestätigt das Ergebnis durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Die geänderten Daten werden in die Liste der Texte übernommen.

#### Testschritt 10:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 11:
- **Aktion**: Der Benutzer folgt unter Dokumente den Schritten 02 und 03 von Prüffall 2 entsprechend.
- **Erwartetes Ergebnis**: Ergebnis siehe Prüffall 2 Schritt 02 und 03.

#### Testschritt 12:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 13:
- **Aktion**: Der Benutzer editiert die Angaben unter Rasterbasis ohne dabei falsche Eingaben zu machen und bestätigt das Ergebnis durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Speicherung der Veränderungen.

#### Testschritt 14:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 15:
- **Aktion**: Der Benutzer editiert lediglich das Datum der Rechtsverordnung in den Basisdaten und bestätigt die Änderung durch die Schaltfläche Speichern. Die Eingabe ist valide.
- **Erwartetes Ergebnis**: Die Reihenfolge der aufgelisteten Pläne auf der Web-basierten Benutzeroberfläche des XPlanManagers hat sich verändert.

#### Testschritt 16:
- **Aktion**: Der Benutzer editiert lediglich das Datum der Rechtsverordnung in den Basisdaten und bestätigt die Änderung durch die Schaltfläche Speichern. Die Eingabe ist nicht valide.
- **Erwartetes Ergebnis**: Es wird eine Fehlermeldung mit dem Vermerk auf den Fehler angezeigt.

#### Testschritt 17:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 18:
- **Aktion**: Der Benutzer editiert lediglich den Rechtsstand in den Basisdaten und bestätigt die Änderung durch die Schaltfläche Speichern.
- **Erwartetes Ergebnis**: Der bearbeitete Plan wird in der entsprechende Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.

#### Testschritt 19:
- **Aktion**: Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster mit einem Formular.

#### Testschritt 20:
- **Aktion**: Der Benutzer editiert bzw. löscht beliebig viele Stammdaten und bricht das Editieren durch Abbruch ab.
- **Erwartetes Ergebnis**: Die ursprünglichen Plandaten sind nicht verändert.

#### Testschritt 21:
- **Aktion**: Der Benutzer exportiert einen zuvor bearbeiteten Plan.
- **Erwartetes Ergebnis**: Das exportierte Planarchiv enthält die geänderten Daten.

## Komponente XPlanManagerWeb

### Plan-Funktion: Export

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einen Plan auf den Button Herunterladen.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster zur Auswahl des Speicherplatzes.

#### Testschritt 2:
- **Aktion**: Der Benutzer wählt ein Verzeichnis zum Speichern des Plans.
- **Erwartetes Ergebnis**: Das Verzeichnis wird im Fenster dargestellt.

#### Testschritt 3:
- **Aktion**: Der Benutzer klickt auf den Button Speichern.
- **Erwartetes Ergebnis**: Das Fenster schließt sich. Der Plan wird von der Datenbasis lokal gespeichert.

## Komponente XPlanManagerWeb

### Plan-Funktion: Entfernen

#### Testschritt 1:
- **Aktion**: Der Benutzer klickt neben einen Plan auf den Button Entfernen.
- **Erwartetes Ergebnis**: Es öffnet sich ein neues Fenster zur Bestätigung des Vorgangs.

#### Testschritt 2:
- **Aktion**: Der Benutzer bestätigt mit OK.
- **Erwartetes Ergebnis**: Es öffnet sich ein Dialog mit dem Inhalt Plan wird entfernt....

#### Testschritt 3:
- **Aktion**: Der Benutzer wartet während der Löschvorgang durchgeführt wird.
- **Erwartetes Ergebnis**: Das Dialog-Fenster schließt sich. Der Plan wird aus der Datenbasis gelöscht und nicht mehr in der Plan-Liste dargestellt.

#### Testschritt 4:
- **Aktion**: Der Benutzer überprüft mit dem XPlanManager CLI (Parameter: -list), ob der zuvor ausgewählte Plan gelöscht wurde.
- **Erwartetes Ergebnis**: Der zuvor ausgewählte Plan wurde gelöscht.

## Komponente XPlanManagerWeb

### XPlanManager-Funktion: Anzeigefilter

#### Testschritt 1:
- **Aktion**: Der Benutzer sucht die Pläne einer bestimmten Gemeinde (Suchfilter Gemeindeauswahl) mit uneingeschränktem Planstatus.
- **Erwartetes Ergebnis**: Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.

#### Testschritt 2:
- **Aktion**: Der Benutzer sucht die Pläne eines bestimmten Status (Suchfilter Planstatus) in allen Gemeinden.
- **Erwartetes Ergebnis**: Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.

#### Testschritt 3:
- **Aktion**: Der Benutzer sucht die Pläne einer bestimmten Gemeinde (Suchfilter Gemeindeauswahl) und einem bestimmten Planstatus (Suchfilter Planstatus).
- **Erwartetes Ergebnis**: Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.

#### Testschritt 4:
- **Aktion**: Der Benutzer wählt Name und gibt einen Namen(steil) eines in der Planliste angezeigten Plans an.
- **Erwartetes Ergebnis**: Die zuvor angezeigte Liste ist auf die Pläne eingeschränkt, deren Namen mit der Nutzereingabe übereinstimmen.

#### Testschritt 5:
- **Aktion**: Der Benutzer wählt Alle Pläne anzeigen
- **Erwartetes Ergebnis**: Die zuvor gesetzten Auswahlkriterien werden zurückgesetzt, und es werden alle Pläne angezeigt.

## Komponente XPlanTransformCLI (deprecated mit xPlanBox Version 7.1)

### Hilfe aufrufen

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanTransformCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-transform-cli-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt mit dem Befehl in [2] die Hilfe aus.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über alle möglichen Eingabeparameter des XPlanTransformCLI.

## Komponente XPlanTransformCLI (deprecated mit xPlanBox Version 7.1)

### Eingabeparameter

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den Befehl [1] aus.
- **Erwartetes Ergebnis**: Alle in der Datenbasis enthaltenen Pläne werden transformiert und daraufhin validiert, anschließend wird das Ergebnis in einer CSV-Datei zusammengefasst. Es erfolgt keine Übertragung der transformierten Pläne in die Datenbasis.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Alle in der Datenbasis enthaltenen Pläne werden transformiert und in die Datenbasis übertragen.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den Befehl [3] aus.
- **Erwartetes Ergebnis**: Alle in der Tabelle "xplanmgr.transformToolPlanTableLog" enthaltenen Pläne werden transformiert, die validen Pläne werden draufhin in die Datenbasis übertragen.

#### Testschritt 4:
- **Aktion**: Der Benutzer führt den Befehl [4] aus.
- **Erwartetes Ergebnis**: Ergebnis aus Schritt 01; der Output wird in das aufgeführte Verzeichnis ausgegeben.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die verfügbaren 

'commands' des XPlanCLI:

help:      
- Display help information about the specified command.
validate:
- Validate a plan or all plans in a database
manage: 
- Manage plans
admin:
- Administrate xPlanBox

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'validate'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Command 'validate' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die verfügbaren 

'subcommands' des Command 'validate':

help:      
- Display help information about the specified command.
file:
- Validate a XPlanArchive or XPlanGML file.
db: 
- Validate XPlanGML in xPlanBox database.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'validate' - Subcommand 'file'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'file' von Command 'validate' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die Nutzung des Subcommand 'file':

Usage: xpb validate file -f=&lt;file&gt; [-n=&lt;validationName&gt;] [-o=&lt;option&gt;[,&lt;option&gt;...]]... [-t=&lt;type&gt;[,&lt;type&gt;...]]... [COMMAND]
&nbsp;
Validate a XPlanArchive or XPlanGML file.
  -f, --file=&lt;file&gt;
  -n, --name=&lt;validationName&gt;
  -o, --option=&lt;option&gt;[,&lt;option&gt;...]
validation options, possible values are: skip-flaechenschluss, skip-geltungsbereich, skip-laufrichtung
  -t, --type=&lt;type&gt;[,&lt;type&gt;...]
values: syntax, geometric, semantic

## Komponente XPlanCLI

### Ausführung (Validierungsart) - XPlanCLI - Command 'validate' - Subcommand 'file'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt den Befehl [2] mit einem validen Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument valide ist.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den Befehl [2] mit einem invaliden Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument invalide ist.

#### Testschritt 4:
- **Aktion**: Der Benutzer führt den Befehl [3] mit einem validen Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument syntaktisch valide ist.

#### Testschritt 5:
- **Aktion**: Der Benutzer führt den Befehl [3] mit einem syntaktisch invaliden Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument syntaktisch invalide ist.

#### Testschritt 6:
- **Aktion**: Der Benutzer führt den Befehl [4] mit einem validen Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument geometrisch valide ist.

#### Testschritt 7:
- **Aktion**: Der Benutzer führt den Befehl [4] mit einem geometrisch invaliden Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument geometrisch invalide ist.

#### Testschritt 8:
- **Aktion**: Der Benutzer führt den Befehl [5] mit einem validen Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument semantisch valide ist.

#### Testschritt 9:
- **Aktion**: Der Benutzer führt den Befehl [5] mit einem semantisch invaliden Planwerk aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe, dass das XPlan-Dokument semantisch invalide ist.

## Komponente XPlanCLI

### Ausführung (Validierungsoptionen) - XPlanCLI - Command 'validate' - Subcommand 'file'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe. Die geometrische Überprüfung der Flächenschlussbedingung wird übersprungen.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den Befehl [3] aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe. Die geometrische Überprüfung des Geltungsbereich wird übersprungen.

#### Testschritt 4:
- **Aktion**: Der Benutzer führt den Befehl [4] aus.
- **Erwartetes Ergebnis**: Der Benutzer erhält eine Validierungsausgabe. Die geometrische Überprüfung der Laufrichtung wird übersprungen.

## Komponente XPlanCLI

### Ablage der Validierungsergebnisse - XPlanCLI - Command 'validate' - Subcommand 'file'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-validator-cli-$VERSION/etc/.

#### Testschritt 2:
- **Aktion**: Der Benutzer überprüft, ob in der Datei [2] das Verzeichnis [3] für die erstellten Validierungsergebnisse angegeben ist.
- **Erwartetes Ergebnis**: Ein Verzeichnis ist nicht gesetzt, daher befinden sich die Validierungsergebnisse unter [4].

#### Testschritt 3:
- **Aktion**: Der Benutzer wechselt in das Temp-Verzeichnis mit Hilfe des Befehls [4].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-validator-cli-$VERSION/tmp/.

#### Testschritt 4:
- **Aktion**: Der Benutzer überprüft, ob das Validierungsergebnis als Archiv (HTML, XML und PDF) dort abgelegt worden ist.
- **Erwartetes Ergebnis**: Das Validierungsergebnis wurde als Archiv angelegt.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'validate' - Subcommand 'db'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'db' von Command 'validate' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die Nutzung des Subcommand 'file':
&nbsp;
Usage: xpb validate db [-d=&lt;database&gt;] [-h=&lt;host&gt;] [-p=&lt;port&gt;] [-P=&lt;password&gt;] [-r=&lt;rules&gt;] [-u=&lt;user&gt;] [COMMAND]

&nbsp;Validate XPlanGML in xPlanBox database.
  -d, --database=&lt;database&gt;   name of the xplanbox database (default: xplanbox)
  -h, --host=&lt;host&gt;   hostname of the database server (default: localhost)
  -p, --port=&lt;port&gt;   port of the database server (default: 5432)
  -P, --password=&lt;password&gt;   database user password
  -r, --rules=&lt;rules&gt;   directory containing the rules
  -u, --user=&lt;user&gt;   database user (default: postgres)

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'validate' - Subcommand 'db'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Alle in der Datenhaltung enthaltenen Planwerke werden validiert, anschließend wird das Ergebnis der Validierung in einer CSV-Datei zusammengefasst. Die erstellte CSV-Datei liegt unter /tmp.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'manage'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Command 'validate' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die verfügbaren 

'subcommands' des Command 'validate':

help:      
- Display help information about the specified command.
list:
- List all plans that are available in the data storage.
import: 
- Import a single or multiple XPlanArchive(s) or XPlanGML file(s).
export:
- Export a single or multiple plan(s).
delete:
- Delete a single or multiple plan(s).
create-metadata:
- Create service metadata records.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'manage' - Subcommand 'import'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'import' von Command 'manage' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb manage import [-ov] [-c=&lt;crs&gt;] [--config=&lt;config&gt;][--workspace=&lt;workspace&gt;] -f=&lt;files&gt;[, &lt;files&gt;...][-f=&lt;files&gt;[, &lt;files&gt;...]]... [COMMAND]
&nbsp;
Import a single or multiple XPlanArchive(s) or XPlanGML file(s).
  -c, --crs=&lt;crs&gt;
       --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -f, --file=&lt;files&gt;[, &lt;files&gt;...]   File(s) to import.
  -o, --force   Force import, ignores invalid or unknown raster format or CRS (default: false).
  -v, --verbose   Print the system log (default: false)
       --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'manage' - Subcommand 'import'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer importiert mit dem Befehl [2] ein Planwerk in die Datenhaltung der xPlanBox.
- **Erwartetes Ergebnis**: Das Planwerk wird in die Datenhaltung der xPlanBox importiert, je nach Konfiguration auch mit Geometriefehlern (unter Nutzung von [--force]).

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'manage' - Subcommand 'list'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'list' von Command 'manage' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb manage list [-v] [--config=&lt;config&gt;] [--workspace=&lt;workspace&gt;] [COMMAND]
&nbsp;
List all plans that are available in the data storage.
       --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -v, --verbose   Print the system log (default: false)
       --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

#### Testschritt 3:
- **Aktion**: Der Benutzer ruft mit dem Befehl [3] eine Auflistung der in der Datenhaltung vorliegenden Planwerke auf und überprüft somit, ob das durch Subcommand 'import'
importierte Planwerk vorhanden ist.
- **Erwartetes Ergebnis**: Die in der Datenhaltung der xPlanBox vorliegenden Planwerke werden aufgelistet und das durch Subcommand 'import'  importierte Planwerk wird angezeigt.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'manage' - Subcommand 'list'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer ruft mit dem Befehl [2] eine Auflistung der in der Datenhaltung vorliegenden Planwerke auf und überprüft somit, ob das durch Subcommand 'import'
importierte Planwerk vorhanden ist.
- **Erwartetes Ergebnis**: Die in der Datenhaltung der xPlanBox vorliegenden Planwerke werden aufgelistet und das durch Subcommand 'import'  importierte Planwerk wird angezeigt.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'manage' - Subcommand 'export'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'export' von Command 'manage' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb manage export [-v] [--config=&lt;config&gt;] [-t=&lt;target&gt;] [--workspace=&lt;workspace&gt;] -i=&lt;ids&gt;[,&lt;ids&gt;...] [-i=&lt;ids&gt; [,&lt;ids&gt;...]]... [COMMAND]
&nbsp;
Export a single or multiple plan(s).
      --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -i, --id=&lt;ids&gt;[,&lt;ids&gt;...]   Die ID des Plans der exportiert werden soll.
  -t, --target=&lt;target&gt;   Angabe des Verzeichnis in dem die exportierten XPlanArchive abgelegt werden sollen.
  -v, --verbose   Print the system log (default: false)
       --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'manage' - Subcommand 'export'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer exportiert ein Planwerk mit Hilfe des Befehls in [2] aus der Datenhaltung der xPlanBox.
- **Erwartetes Ergebnis**: Das Planwerk wird erfolgreich exportiert und im angegebenen Verzeichnis angezeigt.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'manage' - Subcommand 'delete'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'delete' von Command 'manage' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb manage delete [-v] [--config=&lt;config&gt;] [--workspace=&lt;workspace&gt;]
                         -i=&lt;ids&gt;[,&lt;ids&gt;...] [-i=&lt;ids&gt;[,&lt;ids&gt;...]]... [COMMAND]

Delete a single or multiple plan(s).
      --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -i, --id=&lt;ids&gt;[,&lt;ids&gt;...]  Die ID des Plans der geloescht werden soll.
  -v, --verbose  Print the system log (default: false)
      --workspace=&lt;workspace&gt;  Path to the DEEGREE_WORKSPACE_ROOT directory.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'manage' - Subcommand 'delete'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer löscht mit dem Befehl [2] ein Planwerk aus der Datenhaltung der xPlanBox.
- **Erwartetes Ergebnis**: Das Planwerk wird aus der Datenhaltung der xPlanBox gelöscht, geprüft werden kann dies mit erneuter Ausführung des Subcommand 'list'.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'admin'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Command 'validate' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Die Ausgabe gibt Auskunft über die verfügbaren 

'subcommands' des Command 'admin':

help:      
- Display help information about the specified command.
district-update:
- Update column district of table xplanmgr.plans.
evaluation-db-update: 
- EvaluationSchemaSynchronizer
resynthesize:
- Reads the XPlanGML data and updates the re-synthesized data in the xplansyn schema.
sortdate-update:
- Update sort date.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'admin' - Subcommand 'district-update'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'district-update' von Command 'admin' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb admin district-update [-v] [--config=&lt;config&gt;] [--workspace=&lt;workspace&gt;] [COMMAND]

Update column district of table xplanmgr.plans.
      --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -v, --verbose   Print the system log (default: false)
      --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'admin' - Subcommand 'district-update'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Aktualisiert die Spalte 'district' der Tabelle 'xplanmgr.plans'.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'admin' - Subcommand 'evaluation-db-update'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'evaluation-db-update' von Command 'admin' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb admin evaluation-db-update [-d=&lt;database&gt;] [-h=&lt;host&gt;] [-p=&lt;port&gt;] [-P=&lt;password&gt;] [-t=&lt;type&gt;] [-u=&lt;user&gt;] [COMMAND]
&nbsp;
EvaluationSchemaSynchronizer
  -d, --database=&lt;database&gt;   name of the xplanbox database (default: xplanbox)
  -h, --host=&lt;host&gt;   hostname of the database server (default: localhost)
  -p, --port=&lt;port&gt;   port of the database server (default: 5432)
  -P, --password=&lt;password&gt;   database user password
  -t, --type=&lt;type&gt;   one of 'ALL' or 'SYNC' (default: SYNC); 'SYNC' synchronizes plans logged in xplanevaluation. planTableLog, 'ALL' synchronizes all available plans.
  -u, --user=&lt;user&gt;   database user

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'admin' - Subcommand 'evaluation-db-update'

#### Testschritt 3:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Alle in der Datenbasis enthaltenen Planwerke werden aus dem XPlanSyn-Schema in das Auswerteschema der xPlanBox überführt.

#### Testschritt 2:
- **Aktion**: Der Benutzer importiert ein neues Planwerk in die Datenhaltung der xPlanBox und führt anschließend den Befehl [3] aus.
- **Erwartetes Ergebnis**: Alle in der Datenbasis enthaltenen Planwerke, die seit der letzten Ausführung des XPlanAuswerteschemaCLI verändert oder hinzugefügt wurden, werden mit dem Auswerteschmema synchronisiert und überführt.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'admin' - Subcommand 'resynthesize'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'resynthesize' von Command 'admin' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb admin resynthesize [-v] [--config=&lt;config&gt;] [--workspace=&lt;workspace&gt;] [-i=&lt;ids&gt;[,&lt;ids&gt;...]]... [COMMAND]

&nbsp;Reads the XPlanGML data and updates the re-synthesized data in the xplansyn schema.
      --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -i, --id=&lt;ids&gt;[,&lt;ids&gt;...]   The ID of a plan in the XPlanManager of the plan to re-synthesize. If missing all plans are re-synthesized.
  -v, --verbose   Print the system log (default: false)
      --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt den Befehl [3] aus.
- **Erwartetes Ergebnis**: Liest die XPlanGMLs und speichert die resynthetisierten Planwerke im Schema 'xplansyn'.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'admin' - Subcommand 'resynthesize'

#### Testschritt 2:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 1:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Liest die XPlanGMLs und speichert die resynthetisierten Planwerke im Schema 'xplansyn'.

## Komponente XPlanCLI

### Hilfe aufrufen - XPlanCLI - Command 'admin' - Subcommand 'sortdate-update'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer ruft die Hilfe des Subcommand 'sortdate-update' von Command 'admin' mit dem Befehl in [2] auf.
- **Erwartetes Ergebnis**: Usage: xpb admin sortdate-update [-v] [--config=&lt;config&gt;] [--workspace=&lt;workspace&gt;] [COMMAND]

Update sort date.
      --config=&lt;config&gt;   Path to the XPLANBOX_CONFIG directory.
  -v, --verbose   Print the system log (default: false)
      --workspace=&lt;workspace&gt;   Path to the DEEGREE_WORKSPACE_ROOT directory.

## Komponente XPlanCLI

### Ausführung - XPlanCLI - Command 'admin' - Subcommand 'sortdate-update'

#### Testschritt 1:
- **Aktion**: Der Benutzer wechselt in das Verzeichnis des XPlanCLI mit Hilfe des Befehls [1].
- **Erwartetes Ergebnis**: Der Benutzer befindet sich in dem Verzeichnis ~/xplan-cli-tools-$VERSION/bin.

#### Testschritt 2:
- **Aktion**: Der Benutzer führt den Befehl [2] aus.
- **Erwartetes Ergebnis**: Aktualisiert die Spalte 'sortDateUpdate' der Tabelle 'xplanmgr.plans'.

