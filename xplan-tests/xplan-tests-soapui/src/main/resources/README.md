## SoapUI TestSuites

Dieses Dokument beinhaltet Hinweise zur Benutzung der einzelnen SoapUI TestSuites.

### xplan-api-manager TestSuite

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

Hinweise zur Nutzung der TestSuite:

- Die TestCases dieser TestSuite bauen teilweise aufeinander auf. Dies bedeutet, dass die TestSuite immer komplett ausgeführt werden sollte.
- Die TestSuite kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich diese zum Verifizieren einer Neuinstallation.

### xplan-api-validator TestSuite

Diese TestSuite testet die Komponente XPlanValidatorAPI.

Die TestSuite teilt alle dokumentierten Eigenschaften der xplan-api-manager TestSuite.

### xplan-webservices TestSuite

Diese TestSuite testet die Komponente XPlanDienste.

Die zu testenden Endpunkte können folgendermaßen geändert werden:

1. Projekt in SoapUI laden.
2. Project auswählen.
3. Reiter `Custom Properties` öffnen (unten links).
4. Dort aufgelistete Parameterwerte auf die zu testende Umgebung anpassen.
   * Weitere Hinweise zu den einzelnen Parametern gibt es, wenn das Projekt geöffnet wird, `Overview` und anschließend `Description` (ganz unten) ausgewählt werden.
5. Anschließend können die verschiedenen TestSuites über die üblichen Wege ausgeführt werden.

Hinweise zur Nutzung der TestSuite:

- Die TestCases aller TestSuites können individuell genutzt werden und es gibt keine Abhängigkeiten zwischen diesen. Nur die einzelnen TestSteps bauen teilweise aufeinander auf.
- Die TestSuite kann gegen eine frische Installation ausgeführt werden, welche keinerlei Daten beinhaltet. Somit eignet sich diese zum Verifizieren einer Neuinstallation.

### xplan-manager-web TestSuite

Diese TestSuite testet Teile der REST API des XPlanManagerWeb.

Die Ausführung der TestSuite erfolgt, wie in der xplan-api-manager TestSuite beschrieben.

## Hinweise

### Nutzung von SoapUI mit Windows

Unter Windows kann es zu Encoding-Problemen kommen.
Um diese zu beheben, muss in der <SoapUI>\bin\SoapUI-<Version>.vmoptions folgende Zeile ergänzt werden:

> -Dfile.encoding=utf-8
