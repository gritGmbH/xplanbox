.. _configuration-category:

==========
Kategorien
==========
Die Kategorien, auf die ein Benutzer im XPlanManager Web filtern kann, kann in der Datei *managerWebConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* konfiguriert werden.

-------
Bezirke
-------
Es besteht die Möglichkeit nach einzelnen Bezirken zu filtern: ::

   categoryFilterValues=Altona,Bergedorf,Eimsb\u00fcttel,Hamburg-Mitte,Hamburg-Nord,Harburg,Wandsbek

.. hint:: Es müssen ggf. Zeilenumbrüche entfernt werden.

Es muss folgende Syntax beachtet werden: ::

   categoryFilterValues=Bezirk1,Bezirk2

.. note:: Umlaute müssen codiert werden.

---------
Ortsteile
---------
Ortsteile können zusätzlich in der Datei *managerConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* konfiguriert werden: ::

   categoriesToParts=Hamburg-Mitte(HafenCity,Hamburg-Altstadt,Neustadt,St.Georg,
   St.Pauli);Altona(Altona-Altstadt,Altona-Nord);Hamburg-Nord(Alsterdorf,
   Barmbek-Nord,Barmbek-S\u00FCd)

.. hint:: Es müssen ggf. Zeilenumbrüche entfernt werden.

Es muss folgende Syntax beachtet werden: ::

   categoriesToParts=Bezirk1(Ortsteil1,Ortsteil2);Bezirk2(Ortsteil3,Ortsteil4)

.. note:: Umlaute müssen codiert werden.
