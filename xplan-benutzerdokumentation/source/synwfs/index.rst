.. _anchor-synwfs:

===========
XPlanSynWFS
===========

Der XPlanSynWFS (xplansyn-wfs) dient der Abbildung des synthetisierten
XPlanung GML Anwendungsschemas (XPlanSynGML). Dieses stellt eine vereinfachte und
zusammenfassende Form der verschiedenen XPlanGML Versionen dar.

Benutzung des XPlanSynWFS
-------------------------

Die folgende Tabelle fasst die vom XPlanSynWFS unterstützten Operationen
zusammen.
Der XPlanSynWFS unterstützt den Zugriff über die OGC WFS Protokollversionen 1.1.0
und 2.0.0. Dabei ist es möglich, Anfragen per HTTP GET und POST über einen beliebigen
Webbrowser an den Dienst zu senden. Ebenfalls ist die Einbindung in WFS Client
Anwendungen möglich, die die o.g. Versionen für OGC WFS Dienste unterstützen. 

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
   
   http://<host>:<port>/xplansyn-wfs/services/xplansynwfs?

Beispielanfragen
++++++++++++++++

GetCapabilities
***************

.. code-block:: text
   
   http://<host>:<port>/xplansyn-wfs/services/xplansynwfs?request=GetCapabilities&Service=WFS&Version=1.1.0

GetFeature
**********

.. code-block:: text
   
   http://<host>:<port>/xplansyn-wfs/services/xplansynwfs?request=GetFeature&Service=WFS&Version=1.1.0&typename=xplan:BP_Bereich


DescribeFeatureType
*******************

.. code-block:: text
   
   http://<host>:<port>/xplansyn-wfs/services/xplansynwfs?request=DescribeFeatureType&Service=WFS&Version=1.1.0


Inhalte des Datendienstes
-------------------------

Operationen
-----------

Das folgende Kapitel beschreibt die Operationen, die mit dem XPlanSynWFS durchführbar sind.

GetCapabilities
+++++++++++++++

Die GetCapabilities Abfrage dient der Auskunft über die Fähigkeiten des WFS Dienstes. Dabei handelt es sich beispielsweise um Informationen zum Dienstbetreiber,
zu den unterstützten Operationen sowie zu den durch den WFS angebotenen WFS Objektarten.

GetFeature
++++++++++

Die Operation GetFeature stellt die Kernfunktionalität des XPlanSynWFS dar. Die Operation ermöglicht es, die angebotenen Ebene zu den Planinhalten mit GIS Clients zu nutzen, die
die Schnittstellen WFS 1.1.0 bzw. WFS 2.0.0 unterstützen.

DescribeFeatureType
+++++++++++++++++++

Die Operation DescribeFeatureType gibt Informationen zur Struktur der einzelnen Feature Types wieder.

Koordinatenreferenzsysteme
---------------------------

Der XPlanSynWFS (xplansyn-wfs) unterstützt die folgenden Koordinatenreferenzsysteme:
 
 * EPSG:25833
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
