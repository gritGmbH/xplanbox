.. _configuration-mappreview:

==============
Kartenvorschau
==============
Die nachfolgenden Konfigurationen müssen in der Datei *managerWebConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* angepasst werden.

--------
XPlanWMS
--------
Um den XPlanWMS zu konfigurieren, muss der Wert des Schlüssels *wmsUrl* angepasst werden. Über die Schlüssel *vectorWmsName* und *rasterWmsName* können die 
in der Kartenvorschau dargestellte Namen für Vektor- und Rasterlayer konfiguriert werden: ::

   wmsUrl=http://<host>:<port>/xplan-wms/services
   vectorWmsName=XPlan-WMS: Vektorkarten
   rasterWmsName=XPlan-WMS: Rasterkarten
   wmsEndpoint=wms
   wmsPreEndpoint=wmspre
   wmsArchiveEndpoint=wmsarchive
   
Über die Schlüssel *wmsEndpoint*, *wmsPreEndpoint* und *wmsArchiveEndpoint* wird angegeben welcher XPlanWMS für die Kartenvorschau verwendet wird, 
wenn ein Plan 'In Aufstellung', 'Festgestellt' oder 'Archiviert' ist. Wird nur eine Datenhaltungskomponente verwendet, sollte die *wmsUrl* konfiguriert sein 
(mit einem '?' am Ende). Andernfalls wird bei Plänen mit dem Status 'Festgestellt' der mit dem Schlüssel *wmsEndpoint* konfigurierte Endpunkt 
an die wms url angehängt, bei Plänen mit dem Status 'In Aufstellung' der mit dem Schlüssel *wmsPreEndpoint* konfigurierte Endpunkt oder 
bei Plänen mit dem Status 'Archiviert' der mit dem Schlüssel *wmsArchiveEndpoint* konfigurierte Endpunkt.

*Beispiel 1 - Konfiguration bei Verwendung einer Datenhaltungskomponente:* ::

   wmsUrl=http://<host>:<port>/xplan-wms/services[/wms]?
   vectorWmsName=XPlan-WMS: Vektorkarten
   rasterWmsName=XPlan-WMS: Rasterkarten
   wmsEndpoint=
   wmsPreEndpoint=
   wmsArchiveEndpoint=

*Beispiel 2 - Konfiguration bei Verwendung von zwei Datenhaltungskomponenten:* ::

   wmsUrl=http://<host>:<port>/xplan-wms/services
   vectorWmsName=XPlan-WMS: Vektorkarten
   rasterWmsName=XPlan-WMS: Rasterkarten
   wmsEndpoint=wms
   wmsPreEndpoint=wmspre
   wmsArchiveEndpoint=wmsarchive

----------------
Hintergrundkarte
----------------

Um die Hintergrundkarte zu konfigurieren, muss der Wert des Schlüssels *basemapUrl* angepasst werden. Über den Schlüssel *basemapName* kann der in der Kartenvorschau dargestellte Name konfiguriert werden: ::

   basemapUrl=http://<host>:<port>/basemap/service
   basemapName=Basiskarte

-----------------
Koordinatensystem
-----------------

Das Koordinatensystem, indem die Kartenvorschau angezeigt werden soll, kann über den Schlüssel *mapCrs* und dem dazugehörigen Wert konfiguriert werden: ::

   mapCrs=epsg:25832

----------------
Kartenausschnitt
----------------

Kartenausschnitt: ::

   mapExtent=464540.516097552,4294418.79680585,760676.555028607,9320086.20690937

------
Ebenen
------

Die in der Kartenvorschau dargestellten Ebenen können ebenfalls über Schlüssel-Wert-Paare konfiguriert werden. Für die Hintergrundkarte wird der Schlüssel *basemapLayer* benötigt: ::

   basemapLayer=Basemap

Des Weiteren können die Layer für die Bebauungspläne, Flächennutzungspläne, Landschaftspläne und Raumordnungspläne (Vektor- und Rasterlayer) einzeln in einer kommaseparierten Liste konfiguriert werden.

Vektorlayer: ::

   bpLayer=BP_Planvektor
   fpLayer=FP_Planvektor
   lpLayer=LP_Planvektor
   rpLayer=RP_Planvektor

Rasterlayer: ::

   bpRasterLayer=BP_Planraster
   fpRasterLayer=FP_Planraster
   lpRasterLayer=LP_Planraster
   rpRasterLayer=RP_Planraster
