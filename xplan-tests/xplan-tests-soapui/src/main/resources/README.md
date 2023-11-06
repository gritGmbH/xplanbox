# SoapUI Projekte

Dieses Dokument beinhaltet Hinweise zur Benutzung der einzelnen SoapUI Projekte.

## Beschreibung der Projekte

Im Folgenden werden die einzelnen Projekte beschrieben.

### xplan-api-manager Projekt

Dieses SoapUI Projekt testet die Komponente XPlanManagerAPI.

#### Ausführung

Die zu testenden Endpunkte können folgendermaßen geändert werden:

1. Projekt in SoapUI laden.
2. Projekt auswählen.
3. Reiter `Custom Properties` öffnen (unten links).
4. Dort aufgelistete Parameterwerte auf die zu testende Umgebung anpassen.
   * Weitere Hinweise zu den einzelnen Parametern gibt es, wenn das Projekt geöffnet wird, `Overview` und anschließend `Description` (ganz unten) ausgewählt werden.
5. Anschließend können die verschiedenen TestSuites über die üblichen Wege ausgeführt werden.

***Hinweise***

- Die TestCases der einzelnen TestSuites bauen teilweise aufeinander auf. Dies bedeutet, dass eine TestSuite immer komplett ausgeführt werden sollte.
- Das SoapUI Projekt kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich dieses zum Verifizieren einer Neuinstallation.

#### TestSuite "XPlanManagerAPI TestSuite"

Diese Testsuite beinhaltet Datenbanktests, die nur verwendet werden können, wenn auf die Datenbank über eine JDBC URL zugegriffen werden kann.

> **_ACHTUNG:_** Wenn die Datenbanktests verwendet werden sollen, muss der PostgreSQL JDBC-Treiber der SoapUI-Installation, wie unter [Datenbanktests](#Datenbanktests) beschrieben, hinzugefügt werden.

Die JDBC URL zu der von der xPlanBox verwendeten Datenbank muss durch Auswahl des Projekts und Öffnen des Reiters `Custom Properties` (unten links) über das Property `jdbcUrl` gesetzt werden.

Beispiel für eine JDBC URL: `jdbc:postgresql://localhost:5433/xplanbox?user=postgres&password=postgres`

Wenn keine JDBC URL konfiguriert ist, werden die Datenbanktests übersprungen.

#### TestSuite "Codelists TestSuite"

Diese TestSuite prüft, ob externe Codelisten beim Import über die XPlanManagerAPI übersetzt werden. Es werden per Default folgende Übersetzungen angenommen:
* BP_SonstPlanArt, Code 11002 => TeilbebauungsPlan
* BP_Status, Code 19999 => 19999

Um diesen Zustand zu erreichen, ist eine entsprechende Konfiguration der externen Codeliste **BP_SonstPlanArt** für die XPlanGML Version 5.2 in der Installation der XPlanManagerAPI notwendig. Für die Codeliste **BP_Status** wird angenommen, dass **keine** externe Codeliste konfiguriert ist.

Die default Werte können durch Auswahl der TestSuite und Öffnen des Reiters `Custom Properties` (unten links) angepasst werden. Es handelt sich um folgende Properties:
* codelistValue_BP_SonstPlanArt_11002
* codelistValue_BP_Status_19999

Für die Ausführung ist auch die Konfiguration der XPlanDienste URL erforderlich. Dies erfolgt durch Auswahl des Projekts und Öffnen des Reiters `Custom Properties` (unten links). Dort sind folgende Properties anzupassen:
* servicesBaseUrl
* username (optional)
* password (optional)

### xplan-api-validator Projekt

Dieses SoapUI Projekt testet die Komponente XPlanValidatorAPI.

Die Ausführung des SoapUI Projekt erfolgt, wie in dem [xplan-api-manager Projekt](#xplan-api-manager-projekt) beschrieben. Es gelten auch die dort beschriebenen Hinweise.

### xplan-api-dokumente Projekt

Dieses SoapUI Projekt testet die Komponente XPlanDokumentenAPI.

Die Ausführung des SoapUI Projekt erfolgt, wie in dem [xplan-api-manager Projekt](#xplan-api-manager-projekt) beschrieben. Es gelten auch die dort beschriebenen Hinweise.

### xplan-webservices Projekt

Dieses SoapUI Projekt testet die Komponente XPlanDienste.

Die Ausführung des SoapUI Projekt erfolgt, wie in dem [xplan-api-manager Projekt](#xplan-api-manager-projekt) beschrieben. Es gelten auch die dort beschriebenen Hinweise.

***Hinweise***

- Die TestCases aller TestSuites können individuell genutzt werden und es gibt keine Abhängigkeiten zwischen diesen. Nur die einzelnen TestSteps bauen teilweise aufeinander auf.
- Das SoapUI Projekt kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich diese zum Verifizieren einer Neuinstallation.

### xplan-manager-web Projekt

Dieses SoapUI Projekt testet Teile der REST API des XPlanManagerWeb.

#### Ausführung

Die zu testenden Endpunkte können folgendermaßen geändert werden:

1. Projekt in SoapUI laden.
2. Service-Komponente (wird mit zwei gegensätzlichen Pfeilen dargestellt) öffnen.
3. Reiter `Service Endpoints` öffnen.
4. Entweder einen der vorkonfigurierten Endpoints auswählen oder einen neuen hinzufügen.
5. `Assign` auswählen.
6. `All Requests and TestRequests` in der Drop-Down-Liste auswählen.
7. Auswahl mit `OK` bestätigen.
8. Nun können die TestSuites über die üblichen Wege ausgeführt werden.

## Generelle Hinweise

### Nutzung von SoapUI mit Windows

Unter Windows kann es zu Encoding-Problemen kommen.
Um diese zu beheben, muss in der Datei _<SoapUI>\bin\SoapUI-<Version>.vmoptions_ folgende Zeile ergänzt werden:

> -Dfile.encoding=utf-8

### Verwendung von HTTP BASIC Authentication

Sind für den Zugriff auf den Server Credentials erforderlich, so müssen diese je Projekt angegeben werden.

Für das Projekt [xplan-manager-web](#xplan-manager-web-projekt) in SoapUI die Ansicht "Show Service Viewer > Service Endpoints" öffnen. Dort müssen Username und Password für den Endpoint eingetragen und dann mit `All Requests and TestRequests` aus der Drop-Down-Liste auf alle Test angewendet werden.

Für die Projekte [xplan-api-manager](#xplan-api-manager-projekt), [xplan-api-validator](#xplan-api-validator-projekt), [xplan-api-dokumente](#xplan-api-dokumente-projekt) und [xplan-webservices](#xplan-webservices-projekt) in SoapUI das Projekt auswählen und den Reiter `Custom Properties` auswählen. Dort die Properties `username` und `password` setzen.

### Datenbanktests

Damit Datenbanktests gegen PostgreSQL ausgeführt werden können, muss der SoapUI-Installation der JDBC-Treiber von PostgreSQL hinzugefügt werden.

Dieser kann auf folgender Seite heruntergeladen werden: https://jdbc.postgresql.org/download/

Die JAR-Datei muss anschließend in das lib-Verzeichnis von der SoapUI-Installation kopiert werden (`<SOAPUI_INSTALLATION>/lib/`).

Nach einem Neustart von SoapUI ist der neu hinzugefügte Datenbanktreiber nutzbar.

### Anpassungen an den SoapUI Projekten durchführen (für Entwickler)

Wenn Änderungen an einem SoapUI Projekt vorgenommen werden, dann muss die Datei gespeichert und anschliessend auf dem aktuellen Entwicklungszweig eingecheckt werden.

> **_ACHTUNG:_** Vor jedem Commit muss geprüft werden, dass keine Passwörter in den SoapUI Projekten gespeichert sind.

Die Namenskonvention für die TestSteps der SoapUI Projekte xplan-api-manager und xplan-api-validator folgen diesem Muster:

> `HTTPMETHODE|PROTOKOLL PLANART XPLANGMLVERSION QUERYPARAMETER ADDITIONALINFORMATION`

Beispiel für ein Pattern: `POST BP 5.3 sG importPlan`

> **_ACHTUNG:_** Jeder einzelne Block (z.B. `ADDITIONALINFORMATION`) darf keine Leerzeichen beinhalten und `QUERYPARAMETER` werden kommasepariert angegeben (z.B. `sG,sF`)

Die zu nutzenden Abkürzungen werden im folgenden Glossar definiert.

## Glossar

### HTTPMETHODE:

| Abkürzung | Beschreibung                                                                                                           |
|-----------|------------------------------------------------------------------------------------------------------------------------|
| **GET**   | Methode um Daten vom Server anzufordern                                                                                |
| **POST**  | Methode um Daten an den Server zu senden und Ressourcen zu erstellen oder zu modifizieren                              |
| **PUT**   | Methode um Daten an den Server zu senden und Ressourcen zu erstellen oder zu modifizieren, entgegen zu POST idempotent |
| **DEL**   | Methode um Daten vom Server zu löschen                                                                                 |

### PROTOKOLL

| Abkürzung | Beschreibung                                 |
|-----------|----------------------------------------------|
| **JDBC**  | Anfragen über das jdbc:postgresql: Protokoll |

### PLANART

| Abkürzung | Beschreibung        |
|-----------|---------------------|
| **BP**    | Bebauungsplan       |
| **FP**    | Flächennutzungsplan |
| **LP**    | Landschaftsplan     |
| **RP**    | Regionalplan        |
| **SO**    | Sonstige Planwerke  |

> **"XX"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### XPLANGMLVERSION

| Abkürzung | Beschreibung         |
|-----------|----------------------|
| **4.0**   | XPlanGML Version 4.0 |
| **4.1**   | XPlanGML Version 4.1 |
| **5.0**   | XPlanGML Version 5.0 |
| **5.1**   | XPlanGML Version 5.1 |
| **5.2**   | XPlanGML Version 5.2 |
| **5.3**   | XPlanGML Version 5.3 |
| **5.4**   | XPlanGML Version 5.4 |
| **6.0**   | XPlanGML Version 6.0 |

> **"X.X"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### QUERYPARAMETER

| Abkürzung | Parametername       |
|-----------|---------------------|
| **nV**    | name (Validierung)  |
| **id**    | id (Objekt)         |
| **sF**    | skipFlaechenschluss |
| **sG**    | skipGeltungsbereich |
| **sGeo**  | skipGeometrisch     |
| **sL**    | skipLaufrichtung    |
| **sS**    | skipSemantisch      |
| **pf**    | profiles            |
| **pN**    | planName            |
| **pI**    | planId              |
| **iI**    | internalId          |

> **"XX"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### ADDITIONALINFORTAMTION

- Derzeit keine genaue Festlegung
