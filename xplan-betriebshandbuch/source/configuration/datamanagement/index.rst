.. _configuration-datamanagement:

======================
Getrennte Datenhaltung
======================
Der XPlanMananger kann auf eine getrennte Datenhaltung zugreifen. Es wird beim Import ermittelt, ob sich ein Plan *In Aufstellung* befindet, 
*Festgestellt* oder bereits *Archiviert* ist. Ob eine getrennte Datenhaltung verwendet wird, kann in der Datei *managerConfiguration.properties* 
im Verzeichnis *~/.deegree/manager-configuration/* konfiguriert werden: ::

   activateSeparatedDataManagement=[true|false]
