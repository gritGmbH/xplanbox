.. _configuration-workspacereload:

==============================
Automatischer Workspace-Reload
==============================
Der XPlanManager kann nach jedem Import, jeder Aktualiserung und jedem Löschvorgang automatisch einen Workspace-Reload von XPlanWMS-Diensten anstoßen. Dadurch werden neu importierte, editierte und gelöschte Rasterpläne sofort im WMS aktualisiert.

In der Datei *managerConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* müssen folgende Felder gefüllt werden: ::

   workspaceReloadUrls=<XPLANWMS-URLS>
   workspaceReloadUser=<TOMCAT-NUTZERNAME>
   workspaceReloadPassword=<TOMCAT-PASSWORT>

Es können mehrere URLs kommasepariert eingetragen werden. In dem Fall wird für alle eingetragenen XPlanWMS-Dienste eine Reload durchgeführt. Achtung: Alle XPlanWMS-Dienste müssen über die gleiche Nutzer/Passwort-Kombination angesprochen werden können.

Beispiel: workspaceReloadUrls=http://server1/xplan-wms/,http://server2/xplan-wms/

.. hint:: Der Nutzername und das Passwort müssen in der *tomcat-users.xml* eingetragen werden (siehe dazu das Kapitel :ref:`Installation <installation-configuration>`).

.. hint:: Diese Konfigurations-Optionen sind optional. Sind sie nicht gesetzt, muss der Workspace-Reload des XPlanWMS manuell durchgeführt werden, damit die Rasterpläne aktualisiert werden.
