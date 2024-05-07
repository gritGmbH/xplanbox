# Erstellung der SoapUI-Testpläne

Dieses Dokument beinhaltet Hinweise zur automatisierten Ableitung von Testplänen aus den SoapUI-Projekten.

## XMLSTARLET Skript

Beispielkonfiguration für das XPlanManagerAPI-SoapUI-Projekt:

>```xmlstarlet sel -N con="http://eviware.com/soapui/config" -t -m '//con:testSuite' -o '# ' -v '@name' -n -m 'con:testCase' -o '## ' -v '@name' -n -m 'con:testStep' -o '* ' -v '@name' -n xplan-manager-api-soapui-project.xml > xplan-manager-api-soapui-project-testplan.md```

Die Ausführung des Skripts muss im Verzeichnis der SoapUI-Projekte vorgenommen werden, dieses ist standardmäßig: 

> ```xplanbox/xplan-tests/xplan-tests-soapui/src/main/resources```

Die aus dem Skript resultierenden Ausgabedateien werden dann in folgendem Verzeichnis abgelegt:

> ```xplanbox/xplan-tests/xplan-tests-manual/src/main/resources``` 

## Namen der Eingabe- und Ausgabedateien:

Eingabe:

1. xplan-manager-api-soapui-project.xml
2. xplan-validator-api-soapui-project.xml
3. xplan-webservices-soapui-project.xml
4. xplan-dokumente-api-soapui-project.xml

Ausgabe:

1. xplan-manager-api-soapui-project-testplan.md
2. xplan-validator-api-soapui-project-testplan.md
3. xplan-webservices-soapui-project-testplan.md
4. xplan-dokumente-api-soapui-project-testplan.md

---

**Hinweise zur Nutzung**

* Die erstellten Ausgabedateien werden **nicht** manuell bearbeitet.
