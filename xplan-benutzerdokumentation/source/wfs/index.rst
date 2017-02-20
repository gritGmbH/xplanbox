.. _anchor-wfs:

========
XPlanWFS
========

Der XPlanWFS (xplan-wfs) ist ein auf dem Standard
Web Feature Service (Version 1.1.0 und 2.0.0) des Open Geospatial Consortium
(OGC) basierender Dienst zur Abfrage von Vektordaten.


Benutzung des XPlanWFS
------------------------------

Der XPlanWFS basiert auf der Open Source Software `deegree <http://www.deegree.org>`_
und ist konform zu den OGC Standards WFS 1.1.0 und WFS 2.0.0. Darüber hinaus ist
der XPlanWFS als INSPIRE Download Service konfiguriert (Service Endpoint *dls*).

Der XPlanWFS unterstützt den Zugriff über die OGC WFS Protokollversionen 1.1.0
und 2.0.0. Dabei ist es möglich, Anfragen per HTTP GET und POST über einen beliebigen
Webbrowser an den Dienst zu senden. Ebenfalls ist die Einbindung in WFS Client
Anwendungen möglich, die die o.g. Versionen für OGC WFS Dienste unterstützen.

Der XPlanWFS bietet zwei Dienste zur Abfrage von XPlanGML an:

 * XPlanGML (Versionen: 2, 3)

* Service Endpoint *wfs110*

  * WFS 1.1.0

 * XPlanGML (Version: 4)

* Service Endpoint *dls*

  * WFS 1.1.0 und 2.0.0

Die folgende Tabelle fasst die vom XPlanWFS unterstützten Operationen zusammen.

+--------------------+------------------------------------------------+ 
| WFS Operation      | Inhalt                                         | 
+====================+================================================+ 
| GetCapabilities    | Abfrage der Fähigkeiten des Dienstes           |
+--------------------+------------------------------------------------+ 
| GetFeature         | Abfrage von Planobjekten                       |
+--------------------+------------------------------------------------+ 
| DescribeFeatureType| Abfrage der Struktur von Objektarten           |
+--------------------+------------------------------------------------+

Adresse des Dienstes
++++++++++++++++++++

.. code-block:: text
   
   http://<host>:<port>/xplan-wfs/services/wfs110?
   http://<host>:<port>/xplan-wfs/services/dls?

Beispielanfragen
++++++++++++++++

GetCapabilities
***************

.. code-block:: text
   
   http://<host>:<port>/xplan-wfs/services/wfs110?request=GetCapabilities&Service=WFS&Version=1.1.0
   http://<host>:<port>/xplan-wfs/services/dls?request=GetCapabilities&Service=WFS&Version=2.0.0

GetFeature
**********

.. code-block:: text

   http://<host>:<port>/xplan-wfs/services/wfs110?request=GetFeature&Service=WFS&Version=1.1.0&typename=xplan:BP_Bereich
   http://<host>:<port>/xplan-wfs/services/dls?request=GetFeature&Service=WFS&Version=2.0.0&typename=xplan:BP_GruenFlaeche

DescribeFeatureType
*******************

.. code-block:: text
 
   http://<host>:<port>/xplan-wfs/services/wfs110?request=DescribeFeatureType&Service=WFS&Version=1.1.0
   http://<host>:<port>/xplan-wfs/services/dls?request=DescribeFeatureType&Service=WFS&Version=2.0.0


Inhalte des Datendienstes
-------------------------

Operationen
-----------

Das folgende Kapitel beschreibt die Operationen, die mit dem XPlanWFS durchführbar sind.

GetCapabilities
+++++++++++++++

Die GetCapabilities Abfrage dient der Auskunft über die Fähigkeiten des WFS Dienstes. Dabei handelt es sich beispielsweise um Informationen zum Dienstbetreiber,
zu den unterstützten Operationen sowie zu den durch den WFS angebotenen WFS Objektarten.

GetFeature
++++++++++

Die Operation GetFeature stellt die Kernfunktionalität des XPlanWFS dar. Die Operation ermöglicht es, die angebotenen Ebene zu den Planinhalten mit GIS Clients zu nutzen, die
die Schnittstellen WFS 1.1.0 bzw. WFS 2.0.0 unterstützen.

DescribeFeatureType
+++++++++++++++++++

Die Operation DescribeFeatureType gibt Informationen zur Struktur der einzelnen Feature Types wieder.

Koordinatenreferenzsysteme
---------------------------

Der XPlanWFS (xplan-wfs) unterstützt die folgenden Koordinatenreferenzsysteme:
 
 * EPSG:25833,
 * EPSG:25832,
 * EPSG:325833,
 * EPSG:31466,
 * EPSG:31467,
 * EPSG:31468,
 * EPSG:31469,
 * EPSG:4258,
 * EPSG:4326,
 * EPSG:4839,
 * CRS:84.
