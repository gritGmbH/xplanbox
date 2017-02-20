.. _configuration-defaultcrs:

===========================================
Standard-Koordinatenreferenzsystem (Import)
===========================================

-----------
Vektordaten
-----------
Die nachfolgenden Konfigurationen müssen in der Datei *managerWebConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* angepasst werden.

Das Standard-Koordinatenreferenzsystem kann über den Schlüssel *defaultCrs* konfiguriert werden: ::

   defaultCrs=EPSG:25832

Der Benutzer kann ein weiteres Koordinatenreferenzsystem aus einer Liste auswählen. Die Koordinatenreferenzsystem können über den Schlüssel *chooseCrs* konfiguriert werden: ::

   chooseCrs=EPSG:4326,EPSG:25833,EPSG:31466,EPSG:31467,EPSG:31468,EPSG:31469

-----------
Rasterdaten
-----------
Die nachfolgende Konfiguration muss in der Datei *managerConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* angepasst werden.

Das Standard-Koordinatenreferenzsystem kann über den Schlüssel *rasterConfigurationCrs* konfiguriert werden: ::

   rasterConfigurationCrs=epsg:25832
