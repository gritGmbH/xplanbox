.. _installation-configuration:

================================================================
Konfiguration der Applikationsserver
================================================================

Wie im Abschnitt :ref:`Installation <_installation-install>` beschrieben, werden zum Betreiben aller Komponenten zwei Tomcat-Instanzen empfohlen, der *Dienste-Tomcat* sowie *Anwendungs-Tomcat*. Im Folgenden wird die Konfiguration beider Tomcats beschrieben.  

-----------------------------------
Anwendungs-Tomcat
-----------------------------------

Im Anwendungs-Tomcat müssen folgende Konfigurationen vorgenommen werden:

 #. Die Konfiguration der XPlanPortale müssen dem Tomcat bekannt gegeben werden. Dazu muss der Classpath um die Referenzen auf die Konfigurations-JARs der Portale erweitert werden. Wurde die Anleitung in Abschnitt :ref:`Installation <installation-install>` befolgt, befinden sich die drei jar-Dateien im Verzeichnis *.deegree* im Home-Verzeichnis des Nutzers.
 
 #. Für den XPlanManager Web muss die Variable *MANAGER_WEB* als Java Property gesetzt werden. Das mit Hilfe dieser Variable referenzierte Verzeichnis muss die Konfiguration des XPlanManager Web enthalten (es handelt sich um die Dateien managerConfiguration.properties und managerWebConfiguration.properties). Wurde der Anleitung in Abschnitt :ref:`Installation <installation-install>` gefolgt, befindet sich das Verzeichnis mit dem Namen *manager-configuration* im Verzeichnis *.deegree* im Home-Verzeichnis des Nutzers. 

 #. Um das Logging der Anwendungen XPlanManager Web und XPlanValidator Web in ein gesondertes Verzeichnis zu aktivieren (s. Details im Abschnitt :ref:`Logging <configuration-logging>`) muss die Variable *xplan.logdir* als Java Property gesetzt werden. In dem so konfigurierten Verzeichnis werden die log-Dateien der beiden Anwendungen abgelegt.

Linux
-----

Für die Konfiguration der oben genannten Punkte muss zunächst im Verzeichnis *<tomcat-root>/bin* eine neue Datei mit dem Namen *setenv.sh* angelegt werden. 

1) Für die Konfiguration der XPlanPortale werden die Verweise auf die Konfigurations-JARs der Portale eingetragen, getrennt mit einem Doppelpunkt (*:*): ::
   
   CLASSPATH=<root>/.deegree/xplan-bplan-config-1.0.jar:<root>/.deegree/xplan-fplan-config-1.0.jar:<root>/.deegree/xplan-lplan-config-1.0.jar
   
   .. hint:: Es müssen ggf. Zeilenumbrüche entfernt werden.

2) und 3. In der Datei *setenv.sh* erfolgt das Setzen der Variable *MANAGER_WEB* sowie *xplan.logdir* durch das Hinzufügen von: ::
   
   export JAVA_OPTS='-DMANAGER_WEB=/home/xplanbox/.deegree/manager-configuration -Dxplan.logdir=/home/xplanbox/logs'

Falls es bereits einen Export von JAVA_OPTS in dieser Datei gibt, müssen **'-DMANAGER_WEB=/home/xplanbox/.deegree/manager-configuration'** und **'-Dxplan.logdir=/home/xplanbox/logs'** in die bestehenden JAVA_OPTS integriert werden.
 
Windows
-------

1) Für die Konfiguration der **XPlanPortale** wird im Verzeichnis *<tomcat-root>/conf* die Datei mit dem Namen *catalina.properties* angepasst. Der Verweis auf die Konfigurations-JARs der XPlanPortale muss unter *common.loader* eingetragen werden, getrennt mit einem Komma (*,*). Die Tomcat eigenen JAR Dateien, müssen unbedingt beibehalten werden. ::

   common.loader=C:\\.deegree\\xplan-bplan-config-1.0.jar;,C:\\.deegree\\xplan-fplan-config-1.0.jar;,C:\\.deegree\\xplan-lplan-config-1.0.jar;,${catalina.base}\\lib,${catalina.base}\\lib\\*.jar,${catalina.home}\\lib,${catalina.home}\\lib\\*.jar

   .. hint:: Es müssen ggf. Zeilenumbrüche entfernt werden.

2) und 3. Für das Setzen der Variable *MANAGER_WEB* sowie *xplan.logdir* muss zunächst eine neue Datei mit dem Namen *setenv.bat* im Verzeichnis *<tomcat-root>/bin* angelegt werden. Dort werden die Variablen wie folgt hinzugefügt: ::
   
   export JAVA_OPTS='-DMANAGER_WEB=C:\\.deegree\\manager-configuration -Dxplan.logdir=C:\\logs'

Falls es bereits einen Export von JAVA_OPTS in dieser Datei gibt, müssen **'-DMANAGER_WEB=C:\\.deegree\\manager-configuration'** und **'-Dxplan.logdir=C:\\logs'** in die bestehenden JAVA_OPTS integriert werden.

.. hint:: Der Tomcat-Server sollte mindestens über 4GB Arbeitsspeicher verfügen, dies kann durch setzen der Umgebungsvariable: *export JAVA_OPTS="-Xmx4096m"* erfolgen.


-----------------------------------
Dienste-Tomcat
-----------------------------------

Falls der XPlanManager nach jedem Einfügen und Löschen eines Plan den XPlanWMS aktualisieren soll, muss ein Tomcat-Nutzer angelegt werden. Ansonsten ist ein manueller Workspace-Reload nötig, um die neu importieren bzw. gelöschten Rasterpläne im XPlanWMS darzustellen bzw. zu entfernen.

Dafür müssen in der Datei *<tomcat-root>/conf/tomcat-users.xml* folgende Zeilen hinzugefügt werden. ::

   <role rolename="deegree"/>
   <user username="<NUTZERNAME>" password="<PASSWORT>" roles="deegree"/>

.. hint:: Der Nutzername und das Passwort müssen in die managerConfiguration.properties eingetragen werden (s. Kapitel :ref:`Konfiguration - Workspace Reload <configuration-workspacereload>`).