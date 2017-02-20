.. _anchor-manager-web:

================
XPlanManager Web
================

Die Komponente XPlanManager Web ist eine Web-Oberfläche, die dem Fachadministrator der xPlanBox ermöglicht,
die Datenhaltung zu kontrollieren. Die Web-Oberfläche ist auf dem Kommandozeilenwerkzeug aufgebaut. Der XPlanManager Web ist in der Lage,
XPlanGML Instanzdokumente in die Datenhaltung zu Laden, zu Löschen sowie importiere Pläne in einer Liste anzuzeigen.

Benutzungsanleitung
-------------------
Die Webanwendung XPlanManager dient der Verwaltung von XPlanGML konformen Plänen und ist unter:

.. code-block:: text

   http://<host>:<port>/xplanmanager/

verfügbar.

Hilfe
+++++
Die Hilfe mit den Angaben zu den möglichen Funktionen lässt sich durch Betätigen des Buttons *Hilfe* ausgeben.


Hinzufügen
++++++++++
Vor dem Importieren eines XPlanGML Instanzdokumentes in die Datenbasis, muss dieser zur Web-Oberfläche hinzugefügt werden. Hierfür kann der
Plan über den Button *Durchsuchen* ausgewählt und durch einen Klick auf *Import* in die Web-Oberfläche hinzugefügt werden. Der Benutzer
wird über das erfolgreiche bzw. fehlerhafte Hinzufügen des Plans in Form eines PopUps informiert.

Nachdem der Plan zur Web-Oberfläche hinzugefügt wurde, kann dieser validiert bzw. entfernt werden. Erst nachdem der Plan erfolgreich
validiert wurde, kann der Plan in die Datenbasis importiert werden. Andernfalls ist der Button *Import* deaktiviert.

Import
++++++
Nachdem der Plan erfolgreich validiert wurde, kann der Plan durch einen Klick auf den Button *Import* in die Datenbasis importiert werden.
Der Benutzer wird über den erfolgreichen bzw. fehlerhaften Import des Plans in Form eines PopUps informiert.

Auflistung
++++++++++
Standardmäßig werden alle importierten Pläne in einer Liste angezeigt. Diese Liste kann nach dem *Namen*, der *ID*, dem *Plantyp*,
der *sonstige Planart*, dem *Rechtsstand*, dem *Veröffentlichungsdatum*, dem *Importdatum* oder nach dem *Planstatus* auf- oder absteigend sortiert werden.

Kartenvorschau
++++++++++++++
Durch den Klick auf den Button *Kartenvorschau* öffnet sich ein neues Fenster, indem der XPlan visualisiert wird. Im Hintergrund befindet sich
eine OpenStreetMap Karte. Durch den Klick auf *Schließen* wird die Kartenvorschau wieder geschlossen.

Herunterladen
+++++++++++++
Die importierten Pläne können vom Benutzer durch den Klick auf den Button *Herunterladen* wieder heruntergeladen werden.

Löschen
+++++++
Je nach Benutzerrechten können die importierten Pläne durch den Klick auf den Button *Löschen* aus der Datenbasis gelöscht werden. Hat ein Benutzer keine Rechte,
so wird der Button grau dargestellt.

Editieren
+++++++
Abhängig von den Benutzerrechten können Pläne editiert (Funktion aktiviert) bzw. nicht editiert (Funktion nicht aktiviert) werden. Das Editieren ist beschränkt auf BPläne in der
XPlanGML Version 3.0 und 4.1.
Die Editieransicht öffnet sich durch den Klick auf den Button *Editieren*. Die Ansicht ist in verschiedene Bereiche aufgeteilt, die insgesamt die
Bearbeitung folgender Elemente zulässt: 

**Basisdaten**
 
  * name
  * beschreibung
  * technHerstellDatum
  * untergangsDatum
  * planArt
  * sonstPlanArt
  * verfahren
  * rechtsstand
  * rechtsverordnungsDatum

Das Editieren des Rechtsstands kann dazu führen, dass eine Zustandsänderung des Plans eintritt Damit verbunden ist das Verschieben der Vektor- und 
Rasterdaten in die entsprechende Datenhaltung. Übergänge sind beliebig zwischen den drei Zuständen  'Festgestellt', 'In Aufstellung' und 'Archiviert' 
möglich. Eine Zustandsänderung von 'Festgestellt' nach 'In Aufstellung' tritt beispielsweise beim Ändern des Rechtsstands von 'InkraftGetreten' nach 
'Untergegangen' ein.

Achtung: Beim Import kann der Nutzer den Zustand eines Planes über eine Auswahlliste mit den Optionen 'Festgestellt, 'In Aufstellung' und 'Archiviert'  ändern. 
Abhängig von dem im Plan gesetzten Rechtsstand wird eine Vorauswahl getroffen, die der Nutzer aber überschreiben kann. Beim Öffnen der Editieransicht 
wird der Rechtsstand aus dem Plan berücksichtigt, der vom Nutzer ausgewählte Zustand bleibt dagegen unberücksichtigt. Somit entspricht die Auswahl des 
Rechtsstands nicht dem Zustand des Plans, falls dieser beim Import manipuliert wurde. Beim Abspeichern eines Editiervorgangs ist hier somit Vorsicht geboten, da so unbeabsichtigt eine Zustandsänderung vorgenommen werden kann.

 
**Gültigkeitszeitraum**
 
  * Der Zeitraum in dem ein Plan gültig ist. Für dieses Feld gibt es keine Entsprechung im XPlan-GML. Die Einschränkung, wie sie beim Import erfolgt ist, wird überschrieben.  
 
**Änderungen**
 
  * aendert
  * wurdeGeaendertVon
 
**Texte**
 
  * texte
 
**Referenzen**
 
  * refBegruendung
  * refRechtsplan
  * refGruenordnungsplan
 
**Rasterbasis***
 
  * rasterbasis
 
**Änderungen von Rasterplänen**
 
  * rasterAenderung 
  
Werden Referenzen entfernt oder verändert, so werden die nicht mehr referenzierten Dateien aus der Datenhaltung entfernt. In den Bereichen 'Rasterbasis'
und 'Änderungen von Rasterplänen' kann lediglich ein Austausch bereits referenzierter Dateien erfolgen. Änderungen führen zu einer Aktualisierung der
WMS-Konfiguration. Klickt der Nutzer auf *Speichern*, wird zunächst eine Validierung der Rasterdaten vorgenommen. Bei invaliden Dateien bekommt der
Nutzer eine Entscheidungsoption wie mit diesen Daten umgegangen werden soll. Anschließend erfolgt die Aktualisierung der Daten.

.. hint:: Die Editierfunktion steht nur für BPläne in der XPlanGML Version 3.0 und 4.1 zur Verfügung.