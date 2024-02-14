# Änderungshistorie

Alle nennenswerten Änderungen an diesem Projekt werden in dieser Datei dokumentiert.

## Version 7.1.1 (2024-02-09)

### Fehlerbehebungen
- Fehler bei der Installation der XPlanManagerAPI, XPlanValidatorAPI und XPlanDokumenteAPI im Tomcat behoben
- Fehler beim Editieren eines Plans, bei dem sonstPlanArt gesetzt ist und keine Ganzzahl beinhaltet behoben
- Fehler bei der Flächenschlussprüfung, wenn der Geltungsbereich von Bereich und PlanFeature nicht übereinstimmen behoben

## Version 7.1 (2023-12-13)

### Erweiterungen
- Unterstützung für MapProxy 1.16.0 zur Bereitstellung von Raster- und Vektordaten über WMTS und WMS mit S3-Objektspeicher als Cache
- Aktualisierung der Validierungsregeln auf v1.1.6
- Ausgabe des Plannamen und der Planart im Validierungsbericht der XPlanValidatorAPI und XPlanManagerAPI
- Erweiterung von XPlanValidatorAPI und XPlanManagerAPI zur Absicherung der REST-Schnittstellen

### Sicherheitsupdates
- Aktualisierung von Bibliotheken mit bekannten Sicherheitsmängeln

### Fehlerbehebungen
- Fehler im Editiermodus des XPlanManagerWeb für XPlanGML 6.0 behoben
- Ergänzung fehlender Properties aus XPlanGML 6.0.2 in XPlanDB für den XPlanValidatorWMS
- Fehler bei der Sortierung von Rasterdaten nach dem Editieren bei Nutzung von deegree WMS für die Bereitstellung von Rasterdaten behoben

### Veraltete Funktionen

Die folgenden Funktionen sind veraltet und werden in einer zukünftigen Version der xPlanBox entfernt:

- Setzen eines Gültigkeitszeitraums beim Import eines Planwerk über den XPlanManagerWeb ist veraltet (deprecated)
- Konfigurationsoptionen `defaultCRS` und `chooseCrs` zur Auswahl eines Koordinatenreferenzsystems für die Vektordaten eines Planwerks sind veraltet (deprecated)
- XPlanTransformCLI zur Transformation von XPlanGML 4.1 nach XPlanGML 5.1 ist veraltet (deprecated)

## Version 7.0.2 (2023-10-26)

### Erweiterungen
- Zugriff auf externe Schemas für die syntaktische Validierung nicht mehr erforderlich

### Fehlerbehebungen
- Fehler beim Aufruf des Kommandozeilentools XPlanUpdateDataCLI bei Aktualisierung eines größeren Datenbestands behoben (OutOfMemory-Error)
- Fehlendes SQL-Statement für das initiale Aufsetzen der XPlanDB ergänzt
- Korrekturen im Betriebshandbuch für die MapServer Installation vorgenommen

## Version 7.0.1 (2023-10-05)

### Erweiterungen
- Aktualisierung der Validierungsregeln auf v1.1.5
- Das Validierungsprofil Berlin ist in den Komponenten XPlanValidator und XPlanManager enthalten und kann aktiviert werden
- Einführung der Datei _xqueryregeln.txt_ für Validierungsregeln und Profile
- Verbesserung der Konfigurierbarkeit des MapServer-Container-Images
- OCI-Labels für alle Container-Images hinzugefügt

### Fehlerbehebungen
- Fehler bei der Ausführung des XPlanValidator unter Windows behoben
- Fehler bei der Ausführung in Container-Images auf Basis von Bitnami/Tomcat behoben
- Fehler im Editiermodus des XPlanManagerWeb für XPlanGML 6.0 behoben
- Korrekturen in den Zeichenvorschriften für den XPlanWMS vorgenommen
- Fehlermeldung im XPlanValidator bei der Validierung von XPlanArchiven (ZIP-Datei) verbessert
- Fehler im XPlanManagerCLI, XPlanTransformCLI und XPlanUpdateDataCLI bei der Ermittlung des Konfigurationsverzeichnisses _etc/_ bei der Auswertung der Konfigurationsdatei _managerConfiguration.properties_ behoben
- Korrekturen im Betriebshandbuch für die Kommandozeilenprogramme (CLI) vorgenommen

## Version 7.0 (2023-08-16)

### Erweiterungen
- Unterstützung für MapServer 8.0 zur Bereitstellung von Rasterdaten für XPlanWMS hergestellt
- Ablage von Rasterdaten in einem AWS S3-kompatiblen Objektspeicher wird unterstützt
- Ablage von Begleitdokumenten in einem AWS S3-kompatiblen Objektspeicher wird unterstützt
- Abruf von Dokumenten und Rasterdaten über die neue Schnittstelle XPlanDokumentenAPI hergestellt
- Verbesserter Abruf von Dokumenten und Rasterdaten in der GetFeatureInfo-Abfrage des XPlanWMS und GetFeature-Abfrage des XPlanSynWFS und XPlanWFS
- Verbesserung der Geltungsbereichsprüfung im XPlanValidator
- Prüfung der externen Referenzen bei Validierung eines Plans und Ausgabe des Ergebnisses im Validierungsbericht
- Der Import von XPlanGML-Dateien über den XPlanManagerWeb und XPlanManagerAPI wird unterstützt; eine ZIP-Datei ist nicht mehr erforderlich
- Der XPlanValidator kann Daten für den XPlanValidatorWMS temporär in der XPlanDB speichern
- Konfiguration der XPlanDB mit der Angabe des `srid` für alle Geometriespalten hinzugefügt
- Unterstützung einer neuer StoredQuery mit Filter auf planName und eingegrenzten FeatureType im XPlanSynWFS
- Unterstützung der StoredQuery mit Filter auf planName im XPlanSynWFS für alle Datenhaltungen
- Absicherung der deegree REST-API über ApiKeys
- Unterstützung des vereinfachten Downloads eines XPlanArchiv über die XPlanManagerAPI
- Erweiterung der Sortierung von Textabschnitten
- Entfernen des XML-Namespace Präfixes aus dem FeatureType-Namen in den Capabilities des XPlanWFS
- Verbesserung der Transaktionalität bei Auftreten unerwarteter Fehler beim Import im XPlanManager
- Neuer Vorgabewert für Verzeichnisse mit Konfigurationsdateien der xPlanBox

### Sicherheitsupdates
- Schwachstelle im XML-Prozessor von XPlanValidator und XPlanManager behoben, sodass nur lokale statische DTD verwendet werden können; jede externe und jede deklarierte DTD wird nicht mehr zugelassen
- Schwachstelle im XPlanValidator und XPlanManager gegen persistentes Cross-Site-Scripting (XSS) behoben
- Schwachstelle im XPlanValidator und XPlanManager beim Upload schadhafter Dateien behoben
- Aktualisierung von Bibliotheken mit bekannten Sicherheitsmängeln

### Fehlerbehebungen
- Fehler in XPlanManagerAPI bei Aufruf von HTTP DELETE für einen Plan behoben
- Fehler in XPlanValidatorAPI bei der Validierung syntaktisch invalider Pläne behoben
- Fehler in XPlanWMS bei Aufruf mit GetMap-Anfrage mit WMS 1.1.1 und EPSG:4326 behoben
- Fehler in XPlanWFS bei Aufruf mit GetFeature-Anfrage mit WFS 1.1.0 behoben
- Fehler im Encoding der Attributtabelle von Shapefiles aus dem Validierungsreport des XPlanValidators behoben
- Fehler im XPlanWMS bei Aufruf mit GetMap-Anfrage mit WMS 1.1.1 und EPSG:4326 behoben
- Fehlende Ausgabe der detaillierten Zweckbestimmung in komplexen Attributen im XPlanSynWFS ergänzt

### Veraltete Funktionen

Die folgenden Funktionen sind veraltet und werden in einer zukünftigen Version der xPlanBox entfernt:

- LDAP-Schnittstelle ist veraltet (deprecated)
- Schnittstelle zur Verfahrensdatenbank ist veraltet (deprecated)
- Filterkategorien im XPlanManagerWeb sind veraltet (deprecated)

## Version 6.0.3 (2023-06-15)

### Fehlerbehebungen
- Aktualisierung der Validierungsregeln auf v1.1.4 mit Korrektur der Regel 5.3.1.2 für Flächennutzungspläne in der Version XPlanGML 5.1
- Externe Codes werden beim Import über den XPlanManagerWeb nicht übersetzt

## Version 6.0.2 (2023-04-13)

### Fehlerbehebungen
- Aktualisierung der Validierungsregeln auf v1.1.3 mit Korrekturen der Regeln 4.5.1.3 und 5.3.1.2
- Fehlerkorrektur in der Geltungsbereichsprüfung

## Version 6.0.1 (2023-03-30)

### Erweiterungen
- Aktualisierung der XPlanGML-Schemadateien auf Version 6.0.2

### Fehlerbehebungen
- Aktualisierung der Validierungsregeln auf v1.1.2 für die XPlanGML-Version 6.0.2
- Korrektur der Reihenfolge der Textabschnitte im XPlanSynWMS und GFI des XPlanWMS, wenn kein Schlüssel angegeben ist
- Wiederherstellung der Bearbeitungsmöglichkeit des Gültigkeitszeitraums im Editiermodus des XPlanManagerWeb
- Verbesserung der geometrischen Validierung bzgl. inkorrekten Meldungen von Selbstüberschneidungen
- Hinzufügen fehlender Layer im XPlanWMS und FeatureTypes im XPlanSynWFS
- Warnung XPlanWMS "Error while trying to repair an expression" im Log behoben
- Fehlerkorrektur bei der parallelen Ausführung von Validierungen

## Version 6.0 (2022-10-28)

### Erweiterungen
- Unterstützung für XPlanGML 6.0 in allen Komponenten der xPlanBox
- Unterstützung von Profilen mit zusätzlichen Validierungsregeln für den XPlanValidator
- Neuer Dienst XPlanArtWMS eingeführt, für jede spezifische Planklasse ein eigener WMS
- Verfahren kann nicht mehr über die Editierfunktion im XPlanManagerWeb für XPlanGML 6.0 geändert werden
- Im XPlanManagerWeb können über die Editierfunktion nun auch Flächennutzungspläne, Regionalpläne, Landschaftspläne und Sonstigen Pläne geändert werden
- Versionierung des Datenbankschemas mit Liquibase
- Aktualisierung der XPlanGML-Schemadateien auf Version 6.0.1
- Aktualisierung der Validierungsregeln auf v1.0 für XPlanGML Version 6.0.1
- Datei _VERSION.txt_ durch _version.properties_ für Standard-Validierungsregeln ersetzt
- Validierung eines XPlanGML mit `xsi:type` ermöglicht
- Verbesserungen am XPlanSyn-Schema
- Langfassung für Übersetzung von Enumerationswerten im XPlanSynWFS und XPlanWMS
- Vereinheitlichen der Layernamen im XPlanWMS und XPlanWerkWMS
- Verbesserungen der Zeichenvorschriften für Layer aus dem Modellbereichen BP und FP im XPlanWMS
- Verbesserungen der Behandlung von Präsentationsobjekten mit einer Auswahl an Zeichenvorschriften
- Verbesserung der Fehlermeldung beim Import eines XPlanArchiv mit mehreren Instanzdokumenten mit uneindeutigen Bereichs-Nummern
- Verbesserung der Fehlermeldung beim Import eines XPlanArchiv mit mehreren Instanzdokumenten und Referenzierung über verbundenerPlan@xlink:href
- Verbessern der Fehlermeldung beim Editieren eines Plans ohne Bereich
- Unterstützung von XPlanGML 3.0 aus allen Komponenten entfernt
- Konfigurationsparameter `defaultBboxIn4326` entfernt
- Hinzufügen der Tabelle _planslog_ in der XPlanDB
- Aktualisierung auf deegree 3.5
- Aktualisierung auf JTS 1.19.0

### Fehlerbehebungen
- Fehler bei der Veröffentlichung von Bebauungsplänen als INSPIRE PLU behoben
- Fehler beim Editieren der Rasterbasis (XPlanGML 4.1) behoben
- Fehler in der XPlanWFS ListStoredQueries Antwort behoben
- Fehler beim wiederholten Import eines Plans mit mehreren Instanzen behoben
- Fehlerbehandlung für Anfrage von nicht vorhandenen Ressource über XPlanManagerAPI verbessert
- Fehlende Zeichenvorschriften ergänzt
- Fehler in der Flächenschlussprüfung für Änderungspläne und bei vollständiger Überlappung behoben

## Version 5.0.3 (2022-07-11)

### Fehlerbehebungen
- Verbesserung der Geltungsbereichsprüfung im XPlanValidator
    - Ausgabe von Schnittpunkten verbessert
    - Prüfung von linien- oder punktförmigen Geometrien korrigiert
- Verbesserungen der Flächenschlussprüfung im XPlanValidator
    - Abweichungen von Stützpunkten unterhalb von 2mm im Bereich von Lücken werden erkannt
    - Verbesserte Ausgabe von potenziellen Lücken als Warnungen
- Fehlermeldung bei Abbruch der geometrischen Validierung im XPlanValidator verbessert
- Verbesserte Darstellung von geometrischen Warnungen im HTML-Format des Reports des XPlanValidator
- Korrekturen in der Benutzerdokumentation für den XPlanValidator vorgenommen

## Version 5.0.2 (2022-05-24)

### Fehlerbehebungen
- Fehler in XPlanManagerWeb und XPlanManagerAPI bei Änderungen von Rasterdaten in Instanzdokumenten mit mehreren Bereichen behoben

## Version 5.0.1 (2022-04-19)

### Fehlerbehebungen
- Fehler in OpenAPI-Dokument für XPlanManagerAPI und XPlanValidatorAPI behoben

## Version 5.0 (2022-03-28)

Veröffentlichung des Quellcodes unter einer Open Source Lizenz auf der Open CoDE-Plattform.

### Erweiterungen
- Umstellung von Java 8 auf Java 11 sowie Tomcat 8.5 auf Tomcat 9.0
- Unterstützung für XPlanGML 5.4 in allen Komponenten der xPlanBox
- Import von Instanzdokumenten mit mehreren XP_Plan-Objekten
- Editieren von BPlänen über XPlanManagerAPI
- Unterstützung von Links für externe Dokumente in XPlanManager und XPlanWMS GetFeatureInfo
- Sortierung von Textschlüsseln in XPlanManagerWeb und XPlanWMS GFI-Antwort
- Konfiguration des XPlanWMS verbessert
- Dokumenttyp in der GetFeatureInfo-Ausgabe des XPlanWMS ergänzt
- Editieren von externen Dokumenten über eine vollqualifizierte URL ermöglicht
- Umstellung der Referenz von Rasterbasis auf XP_Bereich.refScan in der Editierfunktion
- Unterstützung von externen Dokumenten über eine vollqualifizierte URL
- Klammern im Dateinamen erlaubt
- Ausgabe und Reihenfolge der Textschlüssel in der HTML GFI verbessert
- Verstöße gegen 2.2.2.1 (fehlerhafte Laufrichtung) als Fehler deklariert
- Option zum Ignorieren/Korrigieren von Verstößen gegen KB 2.2.2.1 (Laufrichtung) ergänzt
- Verbesserung der Darstellung des XPlanWMS und XPlanwerkWMS
- Optimierung der geometrischen Validierung: Geltungsbereich (2.2.3.1)
- Optimierung der geometrischen Validierung: Flächenschlussbedingung (2.2.1.1)
- Geometrischen Fehler "Die XLink-Integrität konnte nicht sichergestellt werden" als Warnung gekennzeichnet
- Berührungspunkte zwischen Polygon-Membern von Multiflächen erlaubt
- Berührungspunkt zwischen Außen- und Innenkontur erlaubt
- Gemeldete Fehler von Überschneidung zwischen äußeren und inneren Ring verbessert
- Ausgeben einer Warnung für Konformitätsregel 3.2.6.2
- Hinzufügen des Kommandozeilenwerkzeugs XPlanValidateDB
- Hinzufügen des Kommandozeilenwerkzeugs XPlanAuswerteschemaCLI
- Alternativen Betriebsmodus aus XPlanManagerCLI und Handbuch entfernt
- Entfernen der Erweiterung für ADE/NSM
- Log-Warnungen und Fehlermeldungen verbessert
- Aufrechterhaltung der HTTP Verbindung bei langlaufenden Validierungsprozessen
- Aktualisierung der Validierungsregeln auf v0.11.1
- Aktualisierung auf deegree 3.4.27
- Aktualisierung auf GDAL 3.0
- Aktualisieren der Apache Log4J Abhängigkeiten

### Fehlerbehebungen
- Fehler in der Workspace Konfiguration des XPlanWMS behoben
- Fehler "org.deegree.geometry.standard.multi.DefaultMultiPoint cannot be cast to class org.deegree.geometry.primitive.Point" beim Import eines Plans behoben

## Version 4.3 (2021-09-03)

### Erweiterungen
- (TT_10410) Erweiterung des Betriebshandbuchs um das Datenbankschema der XPlanDB
- (TT_10495) URL des XPlanwerkWMS unterstützt nun auch eine Auswahl von Sonderzeichen
- (TT_10491) XPlanValidatorAPI um Ausgabe der BBox in Validierungsergebnis erweitert

### Fehlerbehebungen
- (TT_10435) Korrektur der REST-API URL im Benutzerhandbuch
- (TT_10405) Doppelte CRS Angaben in XPlanWMS entfernt
- (TT_10458, TT_10465) Korrektur von Validierungsregeln (Aktualisierung auf v0.9.19)
- Editieren von Referenzen in XPlanGML 5.3 vervollständigt
- Links zu Konformitätsregeln aktualisiert
- Editieren von Rasterbasis Daten im XPlanManagerWeb deaktiviert, wenn ein BP_Plan kein BP_Bereich referenziert
- Fehlerbehebungen und Erweiterungen der Docker Images

## Version 4.2 (2021-02-18)

### Erweiterungen
- Unterstützung für XPlanGML 5.3 in allen Komponenten der xPlanBox
- (TT_10407) Unterstützung für GDAL 3.0 (GDAL 2 wird nicht mehr unterstützt)
- Konfigurationsmöglichkeiten der REST API erweitern

### Fehlerbehebungen
- (TT_10430) Verbesserung der Aktualisierungsanleitung im Betriebshandbuch
- (TT_10422) Behebung eines Fehlers bei StoredQuery Anfragen, die zu unvollständigen Antworten führen können
- (TT_10429) Ergänzen der Keywords im XPlanSynWFS für die Version 5.2
- Version der REST API auf 1.0 aktualisiert

## Version 4.1 (2021-01-20)

### Erweiterungen
- Verbesserungen des XPlanValidator:
    - Anzeige der XPlanGML Version im Validierungsbericht
    - Angabe der fehlerhaften Koordinate bei einem Geltungsbereichfehler
    - Geometrische Validierung von Multigeometrien
    - Ausgabe der Koordinate(n) des/der Schnittpunkt(e) bei einer Selbstüberschneidung

### Fehlerbehebungen
- (TT_10420) Beibehalten der Übersetzungen externer Codelisten beim Ausführen des reSynthesizer
- (TT_10405) Entfernen des doppelten Koordinatensystems EPSG:25832 aus den Konfigurationen des XPlanWMS
- (TT_10402) Ausgabe doppelter Stützpunkte durch den XPlanValidator

## Version 4.0 (2020-11-13)

### Erweiterungen
- REST-Schnittstelle zum Validieren und Importierten von XPlanGML (XPlanManagerAPI und XPlanValidatorAPI)

### Fehlerbehebungen
- (TT_10393, TT_10376, TT_10382) Verbesserung des Editierens von TextAbschnitten und externen Referenzen und der Ausgabe im GFI
- (TT_10378) Editieren von Rasterdaten referenziert über XP_Bereich.refScan
- (TT_10392) Gesetzliche Grundlage aus GFI zu TextAbschnitt entfernen
- (TT_10377, TT_10354, TT_10353) Fehler in der semantischen Validierung behoben und semantischen Validierung erweitert
- (TT_10382) Verbesserung der Behandlung unerwarteter Fehler im XPlanValidator
- (TT_10366) Fehlerhafte Warnung über falsche Laufrichtungen bei interior Rings entfernt
- (TT_10382) Prüfung geschlossener Linien auf Laufrichtung verhindert
- (TT_10356) Verbesserung der Fehlermeldung im XPlanValidator, wenn ein Polygon nicht geschlossen ist
- (TT_10355) Zeichenvorschrift für urbanes Gebiet hinzugefügt
- (TT_10323) Verbesserung der Transformationsregeln von XPlanGML 4.1 nach XPlanGML 5.1
- (TT_10348) XPlanValidator akzeptiert XPlanGML mit mehreren XP_Plan-Elementen
- (TT_10327) __reSynthesizer__-Tool entfernt internalId aus synthetisierten Schema
- (TT_10320) Aufruf des __reSynthesizer__-Tool mit großen Planwerken für zu einem Fehler
- (TT_10339, TT_10323) Verbesserung der Transformationsregeln von XPlanGML nach INSPIRE PLU
- (TT_10332, TT_10324) Verbesserung des Loggings
- Ausgabe von Anhängen in v5.2 BP_Plan/externeReferenz/XP_SpezExterneReferenz ermöglicht
- Umstrukturierung des XPlanWMS Workspaces
- Aktualisierung von externen Abhängigkeiten
- Allgemeine Verbesserungen von Benutzer- und Betriebshandbuch Dokumentation

## Version 3.3 (2020-04-21)

### Erweiterungen

- Unterstützung für XPlanGML 5.2 in allen Komponenten der xPlanBox
- Erweiterung der Transformation nach INSPIRE PLU um HILUCS und SupplementaryRegulationValue
- Integration einer Kartenvorschau im XPlanValidator
- Unterstützung für XPlanGML Version 2.0 aus der xPlanBox entfernen
- Diverse Verbesserungen im XPlanValidator
    - Angabe der GML Id bei semantischen Fehlern (ab XPlanGML 5.0)
    - Verbesserung der geometrischen Validierung
- Verbesserung der Darstellung von Präsentationsobjekten
- Verbesserung der Sachdatenabfrage im XPlanWMS (HTML)
- Verbesserung der Performanz des XPlanInspirePluWFS
- Verbesserung der Interoperabilität von XPlanWMS und XPlanSynWFS mit GIS-Clienten

### Fehlerbehebungen

- (TT_10228) Synthetisierung mehrfacher Datumsfelder anpassen
- (TT_10249) Korrektur des Drehwinkels von Texten (Präsentationsobjekten)
- (TT_10263) Fehler beim Publizieren von PLU bei Curves im Originalplan
- (TT_10285) Fehler bei Transformation von XPlanGML 4.1 nach 5.1 (gehoertZuBereich)
- (TT_10291) Fehler bei DescribeFeatureType Response für XPlanSynWFS (2.0)
- (TT_10304) BP|FP|LP|RR|SO_TextAbschnitte werden nicht synthetisiert
- (TT_10304) TextAbschnitte durch den ReSythesizer unvollständig
- (TT_10269) Validierungsreport wird im XPlanManagerWeb nicht korrekt angezeigt
- (TT_10312) Darstellung von FP_Bodenschaetze im XPlanWMS
- (TT_10313) Darstellung von Schienenverkehrsrecht (FP Plan)
- Verbesserung des Betriebs- und Benutzerhandbuchs
- Warnungen beim Start des XPlanWMS behoben
- Verbesserung des Loggings

## Version 3.0 (2020-11-04)

### Erweiterungen

- Unterstützung für XPlanGML 5.0 und 5.1 in allen Komponenten der xPlanBox
- Unterstützung für PostgreSQL 11 mit PostGIS 2.5
- Unterstützung für Apache Tomcat 9.0
- Neuer WMS für die Darstellung einzelner Planwerke (Planwerke-WMS)
- Automatische Erstellung von Metadaten für die Daten-Dienste-Kopplung des XPlanWMS
- Unterstützung von SO-Plänen im XPlanWMS
- Transformation und Bereitstellung von XPlanGML 4.1 Plänen als XPlanGML 5.1 mit dem XPlanTransformCLI Kommandozeilenwerkzeug
- Hinzufügen von Rasterdaten im Editiermodus des XPlanManager

### Fehlerbehebungen

- (TT_10046) Abbildung von Kreisbögen in der xPlanBox verbessern
- (TT_10180) Fehler bei Anfrage mit BBOX Filter ohne PropertyName beheben
- Anzeige der Planliste im XPlanManager beschleunigen
- (TT_10163) Gemeinsamer Austausch von Rasterdatei und Georeferenz erforderlich
- Zeichenvorschriften für BP_, FP_, und SO_ Objekte vervollständigen
- Zeichenreihenfolge im WMS um weitere Regeln ergänzen
- Plan mit mehreren Ortsteilnummern genau einem Bezirk zuordnen
- Modifizierung der Speicherung der textlichen Festsetzungen in dem synthetisierten Datenbankschema
- Exception Handling beim Löschen von Dateien aus dem WMS-Workspace verbessern
- Entfernen eines Plans mit Rasterdaten über den XPlanManager führt zum Fehler beim Update/Reload des XPlanWMS
- Verbesserung der Ausgabe der Validierungsergebnisse
- (TT_10222) Korrektur der Konformitätsregeln 4.5.2.1
- Multiline-Geometrien in F-Plänen werden als Fehler markiert (Regel 3.3.3.1)
- Verbesserung der geometrischen Validierungsregeln des XPlanValidator
- (TT_10183) Geometrische Validierungsregel für den Flächenschluss implementieren
- (TT_10223) Ausgabe der GML ID für die allgemeinen Regeln 2.1.2.1, 2.1.3.1, 2.2.1.1
- (TT_10208) Korrektur der Validierungsregeln für BP_LandwirtschaftsFlaeche (XPlanGMl 5.1)
- (TT_10209) Korrektur der StoredQueries für XPlanGML 5.0 und 5.1
- Benutzerhandbuch: XPlanValidatorCLI: Beispielaufrufe aktualisieren
- Ausgetauschte Rasterdateien werden nicht aus dem Workspace entfernt
- Einträge aus den Themes-Dateien Xplanpreraster und Xplanarchiveraster werden nicht gelöscht
- (TT_10173) GetFeatureInfo Response enthält eine leere Referenz für TextAbschnitte
- Regressionsfehler: Option removeAbandoned fehlt in ConnectionPool-Konfigurationen
- Darstellung von Fehlern in den Regeln im Geometrie-Report
- Verbesserung der Dokumentation der StoredQueries Dokumentation
- Korrektur des Verhaltens von GFI auf BP_Planvektor
- (TT_10205) GFI für Layer mit mehreren Geometriespalten korrigieren
- Probleme mit mehreren Geometriespalten im XPlanSynWFS
- Ausgetauschte Rasterdaten werden erst nach dem Löschen des Browser-Cache in der Kartenvorschau angezeigt Dokumentation
- Voraussetzung an das XPlanArchiv im Benutzerhandbuch dokumentieren Dokumentation
- Anzeige der XPlanGML Version in der Planliste des XPlanManager-Web
- Anzeige von Gemeinde statt Bezirk im XPlanManager
- Spalte mit Gemeinde im XPlanManager hinzufügen
- Anzeige der Version der xPlanBox in allen Komponenten ergänzen
- Verbesserung des Loggings im XPlanManagerWeb
- Konfiguration für xplansearch aus xplansyn-wfs-workspace entfernen
- Aktualisierung der deegree Version auf 3.4.10
- GDAL Version und Installation in xPlanBox auf 2.4 aktualisieren
- Saxon-HE Bibliothek für XPlanValidator auf Version 9.8 aktualisieren
- Aktualisierung von Spring 4.3 und Spring Security 4.2
- Aktualisierung der deegree Schemaversion auf 3.4.0 in allen Workspaces
- Unit-Tests schlagen fehl, wenn Java 8 und 11 installiert sind

## Version 2.8.2 (2019-02-19)

### Erweiterungen

- XPlanManager um Spalte für BP_Plan:nummer erweitert
- XPlanManager um Statusanzeige ob INSPIRE-PLU-Dienst freigeschaltet ist erweitert

### Fehlerbehebungen

- (#1484) In der security-configuration.xml werden Platzhalter fälschlicherweise ersetzt
- (#1256) Tooltipp für Gültigkeitszeitraum funktioniert nur auf erster Seite
- (#1461) Umlaute im AD-Passwort ermöglichen
- (#1543) Konformitätsbedingungen bzgl. Flächenschluss überprüfen
- (#1054) XPlanManagerCLI: DEEGREE_WORKSPACE_ROOT wird nicht ausgewertet
- (TT_10004) Syntaktischer Fehler wird vom Validator nicht korrekt erkannt
- (TT_10003) Ergebnisse von StoredQuery im XPlanSynWFS
- (TT_10004) Fehler beim Schreiben des SHP-Files behoben
- (TT_10004) Geometriefehler bei Plänen ohne Koordinatensystem abfangen
- (TT_10109) Die Datei „manager-configuration“ für xPlanBox 2.7.1 fehlt
- (TT_10112) Korrektur der semantischen Regeln im XPlanValidator (u.a. 2.2.1.1, 4.1.2.1, 4.2.3, 4.2.9, 4.5.13.1, 4.9.6.1, 4.14.2.1 )
- (TT_10136) Verfahrens-ID wird nach dem Editieren aus der XPlanSyn-Datensatz entfernt
- Default Hintergrundkarte für Kartenvorschau ist nicht mehr erreichbar
