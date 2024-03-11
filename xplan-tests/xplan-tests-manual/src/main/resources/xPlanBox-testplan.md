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
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-api-validator-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: XPlanValidatorAPI TestSuite: Alle Tests werden erfolgreich bestanden. Profiles TestSuite: Alle Test werden bestanden, wenn mindestens ein Profil aktiviert ist.

#### Testschritt 1:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-api-manager-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: XPlanManagerAPI TestSuite: Alle Tests werden erfolgreich bestanden. Profiles TestSuite: Alle Test werden bestanden, wenn mindestens ein Profil aktiviert ist. Codelists TestSuite: Alle Tests werden  bestanden, wenn die angegeben Codeliste konfiguriert ist.

#### Testschritt 5:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-api-dokumente-soapui-project.xml in SoapUI aus
- **Erwartetes Ergebnis**: Alle Tests werden erfolgreich bestanden.

#### Testschritt 3:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-webservices-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: xplan-wms: Alle Tests werden erfolgreich bestanden, Ausnahme: RESTAPI wird nur bestanden, wenn /config des xplan-wms erreichbar ist.  xplansyn-wfs, xplan-wfs: Alle Tests werden erfolgreich bestanden. xplan-inspireplu: Tests werden nur bestanden, wenn XPlanInspirePLU erreichbar ist. mapserver-wms, mapproxy-wms, mapproxy-wmts: Alle Tests werden erfolgreich bestanden.

#### Testschritt 4:
- **Aktion**: Der Benutzer führt das SoapUI-Projekt xplan-manager-web-soapui-project.xml in SoapUI aus.
- **Erwartetes Ergebnis**: Alle Tests werden erfolgreich bestanden.

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

