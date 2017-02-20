.. _configuration-internalid:

===================
Verfahrensdatenbank
===================
Die Verfahrensdatenbank kann in der Datei *managerWebConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* aktiviert oder deaktiviert werden: ::

   activateInternalIdDialog=[true|false]

Die Anfrage der Verfahrensdatenbank kann an den Aufbau der Datenbank angepasst werden. Dies erfolgt in der  Datei *managerConfiguration.properties*: ::

   workspaceName=xplan-manager-workspace
   jdbcConnectionId=vfdb
   internalIdLabel=verfahrensid
   internalNameLabel=verfahrensname
   selectMatchingIdsSql=SELECT verfahrensid, verfahrensname FROM planverfahren WHERE lower(verfahrensname) LIKE lower(?) ORDER BY verfahrensname ASC
   selectAllIdsSql=SELECT verfahrensid, verfahrensname FROM planverfahren ORDER BY verfahrensname ASC

Über die Schlüssel *workspaceName* und *jdbcConnectionId* wird der Workspace und die Id der Datei in der die Datenbank-Verbindung angepasst werden kann 
angegeben. Die Standardwerte können in den meisten Fällen übernommen werden. 

Die Schlüssel *internalIdLabel* und *internalNameLabel* geben die Spalten an unter denen die Verfahrensid und der Verfahrensname adressiert werden. 
Diese müssen zwingend über die Schlüssel *selectMatchingIdsSql* und *selectAllIdsSql* konfigurierten SQL-Statments selektiert werden.
Das SQL-Statment *selectMatchingIdsSql* erlaubt die Anfrage der Verfahren, die dem Plan über den Plan-Namen zugeordnet sind. Der Plan-Name wird an der Stelle des '?' eingesetzt.
Das SQL-Statement *selectAllIdsSql* ermöglicht dagegen die Anfrage aller Verfahren.

.. note:: Anforderungen an die Konfiguration im Überblick:

  - *selectMatchingIdsSql* und *selectAllIdsSql* müssen die mit *internalIdLabel* und *internalNameLabel* angegeben Spalten selektierten.
  - *selectMatchingIdsSql* muss exakt einen Platzhalten '?' für den Plan-Namen beinhalten.
  - *selectAllIdsSql* darf keinen Platzhalter beinhalten.

Die Datenbank-Anbindung kann in der Datei *vfdb.xml* im XPlanManager Workspace konfiguriert werden (der Name des workspaces und die ID der Konfigurationsdatei können wie zuvor beschrieben geändert werden): ::

  <Property name="driverClassName" value="org.postgresql.Driver"/>
  <Property name="url" value="jdbc:postgresql://<host>:<port>/<dbname>"/>
  <Property name="username" value="postgres"/>
  <Property name="password" value="postgres"/>
  <Property name="maxActive" value="10"/>
