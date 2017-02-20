.. _configuration-xplanresources:

==============
XPlanResources
==============

Die Einstiegsseite der Komponente XPlanResources enthält Referenzen zu den anderen Komponenten der xPlanBox, die Referenzen müssen ggf. an die 
Umgebung der Installation angepasst werden. In der Datei *index.html* müssen die URLs zu den Diensten modifiziert werden:
 *  http://localhost:8080/xplan-wms/services/wms?service=WMS&request=GetCapabilities
 *  http://localhost:8080/xplansyn-wfs/services/xplansynwfs?service=WFS&request=GetCapabilities
 *  http://localhost:8080/xplan-wfs/services/wfs110?service=WFS&request=GetCapabilities
 *  http://localhost:8080/xplan-wfs/services/dls?service=WFS&request=GetCapabilities
 
 Weiterhin kann es notwendig sein, die relativ angegeben Referenzen zu den Komponenten XPlanPortal, XPlanManager Web und XPlanValidator Web 
 sowie der Benutzerdokumentation und dem Betriebshandbuch anzupassen.  