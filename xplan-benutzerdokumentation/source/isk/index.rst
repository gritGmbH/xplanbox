.. _anchor-isk:

=========
Übersicht
=========

Die xPlanBox dient der Abbildung der Bauleit- und der Landschaftsplanung. Die
xPlanBox basiert auf den Standards des `Open Geospatial Consortium <http://http://www.opengeospatial.org>`_,
der `INSPIRE Richtlinie <http://inspire.ec.europa.eu>`_ und insbesondere dem Standard `XPlanung <http://www.xplanung.de>`_
zur Abbildung des deutschen Planungsrechts durch ein GML Anwendungsschema.

Zur Implementierung der Komponenten der xPlanBox wurden die Open Source
Software-Pakete `deegree <http://www.deegree.org>`_ und
`geomajas <http://www.geomajas.org>`_ eingesetzt, die beide ebenfalls Projekte
der OSGeo Foundation sind. Die xPlanBox besteht aus den folgenden Komponenten:

 - XPlanManager: Web-Oberfläche für das Datenmanagement
 - XPlanValidator: Web-Oberfläche für den Validator
 - XPlanPortal: Geo-Viewer für die Auskunft
 - XPlanWMS: Standard-Karten-Dienst für die Auskunft
 - XPlanWFS: Standard-Daten-Dienst für die Auskunft (originale Datenstruktur)
 - XPlanSynWFS: Standard-Daten-Dienst für die Auskunft (vereinfachte Datenstruktur)

Die Komponenten der xPlanBox werden in dieser Benutzerdokumentation im Einzelnen
erläutert.