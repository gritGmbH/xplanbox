# SoapUI TestSuites

Dieses Dokument beinhaltet Hinweise zur Benutzung der einzelnen SoapUI TestSuites.

## xplan-api-manager TestSuite

Diese TestSuite testet die Komponente XPlanManagerAPI.

Die zu testenden Endpunkte können folgendermaßen geändert werden:

1. Projekt in SoapUI laden.
2. Service-Komponente (wird mit zwei gegensätzlichen Pfeilen dargestellt) öffnen.
3. Reiter `Service Endpoints` öffnen.
4. Entweder einen der vorkonfigurierten Endpoints auswählen oder einen neuen hinzufügen.
5. `Assign` auswählen.
6. `All Requests and TestRequests` in der Drop-Down-Liste auswählen.
7. Auswahl mit `OK` bestätigen.
8. Nun kann die `XPlanManagerAPI TestSuite` über die üblichen Wege ausgeführt werden.

**Hinweise zur Nutzung der TestSuite:**

- Die TestCases dieser TestSuite bauen teilweise aufeinander auf. Dies bedeutet, dass die TestSuite immer komplett ausgeführt werden sollte.
- Die TestSuite kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich diese zum Verifizieren einer Neuinstallation.

---

## xplan-api-validator TestSuite

Diese TestSuite testet die Komponente XPlanValidatorAPI.

Die TestSuite teilt alle dokumentierten Eigenschaften der [xplan-api-manager TestSuite](#xplan-api-manager-testsuite).

### xplan-webservices TestSuite

Diese TestSuite testet die Komponente XPlanDienste.

Die zu testenden Endpunkte können folgendermaßen geändert werden:

1. Projekt in SoapUI laden.
2. Project auswählen.
3. Reiter `Custom Properties` öffnen (unten links).
4. Dort aufgelistete Parameterwerte auf die zu testende Umgebung anpassen.
   * Weitere Hinweise zu den einzelnen Parametern gibt es, wenn das Projekt geöffnet wird, `Overview` und anschließend `Description` (ganz unten) ausgewählt werden.
5. Anschließend können die verschiedenen TestSuites über die üblichen Wege ausgeführt werden.

**Hinweise zur Nutzung der TestSuite:**

- Die TestCases aller TestSuites können individuell genutzt werden und es gibt keine Abhängigkeiten zwischen diesen. Nur die einzelnen TestSteps bauen teilweise aufeinander auf.
- Die TestSuite kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich diese zum Verifizieren einer Neuinstallation.

## xplan-manager-web TestSuite

Diese TestSuite testet Teile der REST API des XPlanManagerWeb.

Die Ausführung der TestSuite erfolgt, wie in der [xplan-api-manager TestSuite](#xplan-api-manager-testsuite) beschrieben.


# Generelle Hinweise

### Nutzung von SoapUI mit Windows

Unter Windows kann es zu Encoding-Problemen kommen.
Um diese zu beheben, muss in der Datei _<SoapUI>\bin\SoapUI-<Version>.vmoptions_ folgende Zeile ergänzt werden:

> -Dfile.encoding=utf-8

---

### Verwendung von HTTP BASIC Authentication

Sind für den Zugriff auf den Server Credentials erforderlich, so müssen diese je TestSuite angegeben werden.

Für die TestSuites [xplan-api-manager](#xplan-api-manager-testsuite) und [xplan-api-validator](#xplan-api-validator-testsuite) in SoapUI die Ansicht "Show Service Viewer > Service Endpoints" öffnen. Dort müssen Username und Password für den Endpoint eingetragen und dann mit `All Requests and TestRequests` aus der Drop-Down-Liste auf alle Test angewendet werden. 

Für die TestSuite [xplan-webservices](#xplan-webservices-testsuite) in SoapUI das Projekt auswählen und den Reiter `Custom Properties` auswählen. Dort die Properties `username` und `password` setzen.

---

### Anpassungen an den SoapUI Projekten durchführen (für Entwickler)

Wenn Änderungen an einem SoapUI Projekt vorgenommen werden, dann muss die Datei gespeichert und anschliessend auf dem aktuellen Entwicklungszweig eingecheckt werden.

> **_ACHTUNG:_** Vor jedem Commit muss geprüft werden, dass keine Passwörter in den SoapUI Projekten gespeichert sind.

Die Namenskonvention für die TestSteps der SoapUI Projekte xplan-api-manager und xplan-api-validator folgen diesem Pattern:

> `HTTPMETHODE PLANART XPLANGMLVERSION QUERYPARAMETER ADDITIONALINFORMATION`

Beispiel für ein Pattern: `POST BP 5.3 sG importPlan`

> **_ACHTUNG:_** Jeder einzelne Block (z.B. `ADDITIONALINFORMATION`) darf keine Leerzeichen beinhalten und `QUERYPARAMETER` werden kommasepariert angegeben (z.B. `sG,sF`)

Die zu nutzenden Abkürzungen werden im folgenden Glossar definiert.

## Glossar

### HTTPMETHODE:

Abkürzung | Beschreibung 
----------- |------------------
**GET** | Methode um Daten vom Server anzufordern
**POST** | Methode um Daten an den Server zu senden und Ressourcen zu erstellen oder zu modifizieren
**PUT** | Methode um Daten an den Server zu senden und Ressourcen zu erstellen oder zu modifizieren, entgegen zu POST idempotent
**DEL** | Methode um Daten vom Server zu löschen

### PLANART

Abkürzung | Beschreibung 
----------- |------------------
**BP** | Bebauungsplan
**FP** | Flächennutzungsplan
**LP** | Landschaftsplan 
**RP** | Regionalplan
**SO** | Sonstige Planwerke
> **"XX"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### XPLANGMLVERSION

Abkürzung | Beschreibung 
----------- |------------------
**4.0** | XPlanGML Version 4.0
**4.1** | XPlanGML Version 4.1
**5.0** | XPlanGML Version 5.0 
**5.1** | XPlanGML Version 5.1
**5.2** | XPlanGML Version 5.2
**5.3** | XPlanGML Version 5.3
**5.4** | XPlanGML Version 5.4
**6.0** | XPlanGML Version 6.0
> **"X.X"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### QUERYPARAMETER

Abkürzung | Parametername 
----------- |------------------
**nV** | name (Validierung)
**id** | id (Objekt)
**sF** | skipFlaechenschluss
**sG** | skipGeltungsbereich 
**sGeo** | skipGeometrisch
**sL** | skipLaufrichtung 
**sS** | skipSemantisch
**pf** | profiles
**pN** | planName
**pI** | planId
> **"XX"** ist ein Platzhalter bei nichtvorhandensein eines dieser Werte

### ADDITIONALINFORTAMTION

- Derzeit keine genaue Festlegung

