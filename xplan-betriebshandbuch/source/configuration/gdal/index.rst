.. _configuration-gdal:

====
GDAL
====
Die GDAL Unterstützung erfordert die Installation von GDAL, diese ist im Abschnitt :ref:`Installation <installation-gdal>` beschrieben.

Um die GDAL Unterstützung zu aktivieren, muss die GDAL-Hauptkonfiguration erzeugt werden. Dazu wird im Root-Ordner des Workspaces
*xplansyn-wms-workspace* eine Datei mit dem Namen *gdal.xml*  und mit folgendem Inhalt abgelegt: ::

   <GDALSettings configVersion="3.4.0"
     xmlns="http://www.deegree.org/gdal" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.deegree.org/gdal http://schemas.deegree.org/commons/gdal/3.4.0/gdal.xsd">
     <OpenDatasets>5</OpenDatasets>
   </GDALSettings>

Damit beim Import von Rasterdaten eine GDAL Konfiguration erzeugt wird, muss in der Datei *managerConfiguration.properties*
im Verzeichnis *manager-configuration* die folgende Option aktiviert sein: ::

   #must be gdal or geotiff, default is gdal
   rasterConfigurationType=gdal

Ist die GDAL-Bibliothek nicht installiert, kann stattdessen der Konfigurationstyp für das GeoTiff Format konfiguriert werden: ::

   #must be gdal or geotiff, default is gdal
   rasterConfigurationType=geotiff