.. _configuration-export:

======
Export
======
Importierte Pläne können über den XPlanMananger exportiert werden. Dabei kann die Erzeugung einer Datei xplan-reexported.gml erfolgen.
Es handelt sich dabei um die wiederhergestellte Datei xplan.gml aus dem XPlan-WFS. In dieser können zum Beispiel Geometriefehler korrigiert sein.
Ob der Export dieser Datei erfolgen soll, kann in der Datei *managerConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* konfiguriert werden: ::

   activateExportOfReexported=[true|false]