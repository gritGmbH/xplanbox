# Erstellung der SoapUI-Testpläne

Dieses Dokument beinhaltet Hinweise zur automatisierten Erstellung der einzelnen SoapUI-Testpläne.

## XMLSTARLET Skript

Beispeilkonfiguration für das XPlanManagerAPI-SoapUI-Projekt:

>```xmlstarlet sel -N con="http://eviware.com/soapui/config" -t -m '//con:testSuite' -o '# ' -v '@name' -n -m 'con:testCase' -o '## ' -v '@name' -n -m 'con:testStep' -o '* ' -v '@name' -n xplan-api-manager-soapui-project.xml > xam-soapui-project-testplan.md```

Die Ausführung des Skript muss im Verzeichnes der SoapUI-Projekte vorgenommen werden, dieses ist standardmäßig: 

> ```xplanbox/xplan-tests/xplan-tests-soapui/src/main/resources```

Die aus dem Skript resultierenden Ausgabedateien werden dann in folgendem Verzeichnis abgelegt:

> ```xplanbox/xplan-tests/xplan-tests-manual/src/main/resources``` 

## Namen der Eingabe- und Ausgabedateien:

Eingabe:

1. xplan-api-manager-soapui-project.xml
2. xplan-api-validator-soapui-project.xml
3. xplan-webservices-soapui-project.xml

Ausgabe:

1. xam-soapui-project-testplan.md
2. xav-soapui-project-testplan.md
3. xw-soapui-project-testplan.md

---

**Hinweise zur Nutzung**

* Um die Dateilänge zu minimieren wurde folgende Namenskonvention für die erstellten Ausgabedateien genutzt:

  * >```xam = xplan-api-manager```
  * >```xav = xplan-api-validator```
  * >```xw = xplan-webservices```

* Die erstellten Ausgabedateien werden **nicht** manuell bearbeitet.
