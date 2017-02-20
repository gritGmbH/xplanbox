.. _installation-install:

======================
Anwendung installieren
======================

----------------------
Konfiguration
----------------------

Die im Verzeichnis *config* enthaltenen *zip*-Archive müssen in das Verzeichnis *.deegree* im Home-Verzeichnis des Nutzers kopiert und entpackt werden:

 * *xplan-wfs-workspace*
 
 * *xplansyn-wfs-workspace*
 
 * *xplansyn-wms-workspace*
 
 * *xplan-manager-workspace*

 * *xplan-manager-config* (Name des Verzeichnis nach dem Entpacken: *manager-configuration*)
 
.. hint:: Alle Workspaces müssen in dem selben Verzeichnis liegen.

Die ebenfalls im Verzeichnis *config* enthaltenen *jar*-Archive müssen ebenfalls in das Verzeichnis *.deegree/portal-configuration* im Home-Verzeichnis des Nutzers kopiert (aber nicht entpackt) werden:
 
 * *xplan-bplan-config*
 
 * *xplan-fplan-config* 
 
 * *xplan-lplan-config*
 
----------------------
Web-Anwendungen
----------------------
 
Um das korrekte Zusammenarbeiten der verschiedenen im Abschnitt :ref:`Systemarchitektur und Schnittstellen <architecture>` beschriebenen Komponenten zu gewährleisten, ist die Reihenfolge der Verfügbarkeit der Komponenten wichtig. Daher wird eine Trennung der Dienste und XPlanPortal-/Anwendungskomponenten empfohlen. So kann durch die Reihenfolge des Hochfahrens der Webapplikationen sichergestellt werden, dass die XPlanPortale beim Starten den benötigten Zugriff auf die Dienste haben. 
 
Die folgenden WAR-Archive aus dem Ordner *web* müssen in das Verzeichnis der Tomcat-Installation (*Dienste-Tomcat*, z.B. Port: 8080) kopiert werden:

 * *xplan-wms.war*

 * *xplan-wfs.war*

 * *xplansyn-wfs.war*

Die WAR-Archive der XPlanPortal-/Anwendungskomponenten werden über eine weitere Tomcat-Installation (*Anwendungs-Tomcat*, z.B. Port: 8081) bereitgestellt: 

 * *xplanmanager.war*

 * *xplanvalidator.war*
 
 * *portal-bplan.war*
 
 * *portal-fplan.war*
 
 * *portal-lplan.war* 
 
 * *xplan-root.war*

 .. note:: Da die Komponente XPlanResources eine Einstiegsseite bereit stellt, bietet es sich an, diese als ROOT-Webapp des *Anwendungs-Tomcat* zu installieren. Das war-Archiv muss dafür im Verzeichnis ROOT entpackt werden.

----------------------
Kommandozeilen-Anwendungen
----------------------

Die Kommandozeilenkomponenten aus dem Ordner *cli* können an beliebiger Stelle entpackt werden. Im jeweiligen *bin* Verzeichnis der beiden Kommandozeilenwerkzeuge findet sich das entsprechende Ausführungsskript *XPlanManager* bzw. *XPlanValidator*.

----------------------
Dokumentation
----------------------

Im Verzeichnis *doc* findet sich eine gezippte Version der Dokumentation. Dieses Archiv enthält die Benutzerdokumentation zu den Komponenten in den Formaten HTML und PDF.

.. _installation-install_script:


------------------------------------------------------
Installation über ein Install-Skript
------------------------------------------------------
Das Ablegen bzw. der Austausch der Web-Archive sowie das Ablegen bzw. die Aktualisierung der betroffenen Konfigurationsdateien in den Workspaces kann auf Linux-Umgebungen auch über das angehängte :download:`Installations-Skript <../../downloads/install.sh>` ausgeführt werden: ::

   sh ${WORK_DIR}/install.sh


Installationswerkzeuge und -skripte werden ohne Gewähr bereitgestellt. Für professionelle Unterstützung wenden Sie sich bitte an die `lat/lon GmbH <http://www.lat-lon.de>`_.
