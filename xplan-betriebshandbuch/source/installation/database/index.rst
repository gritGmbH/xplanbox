.. _installation-database:

=======================================
 Konfiguration der Datenbank
=======================================

Wie in Abschitt *Systemüberblick* beschrieben, ist mindestens die Installation von PostgreSQL 8.4 mit der Erweiterung PostGIS 1.5.4 erforderlich.

Für die Konfiguration der Datenbank-Anbindung muss die *xplan-manager-cli*-Datei entpackt sein. Dabei spielt das Verzeichnis in dem die Datei entpackt wurde keine Rolle.
Ist diese entpackt, kann die Datenbankerzeugung mittels des XPlanmanger-CLIs durchgeführt werden.
Informationen zu den Befehlen und der Erzeugung der Datenbank finden sich im Abschnitt XPlanManager CLI in der Benutzerdokumentation.
Die erforderlichen Informationen sind in Abschnitt *Alternativer Betriebsmodus* beschrieben.


Datenbank Verbindung in xplansyn-wms-workspace manuell anpassen:
Nach der Erzeugung der Datenbank in PostgreSQL muss die *xplan.xml*-Datei im XPlanmanger Workspace *.deegree/xplan-manager-workspace/jdbc/* kopiert und
in das Verzeichnis *.deegree/xplansyn-wms-workspace/jdbc/* eingefügt werden. Dabei wird die bereits vorhandene Konfiguration ausgetauscht.
Alternativ ist auch eine direkte Anpassung in der bereits vorhandenen *xplan.xml*-Datei im *xplansyn-wms-workspace* möglich.
