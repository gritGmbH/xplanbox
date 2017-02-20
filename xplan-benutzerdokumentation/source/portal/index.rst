.. _anchor-portal:

===========
XPlanPortal
===========

Die Portalkomponenten der xPlanBox dienen als zentrale Anlaufstelle zur Information und Recherche der bereitgestellten Planwerke. Im Folgenden werden der Aufbau sowie die wesentlichen Funktionen erläutert.

Aufbau des XPlanPortals
-----------------------

Das XPlanPortal besteht aus drei Bereiche. Auf der linken Seite befindet sich die Ebenenübersicht. Dort sind alle Ebenen strukturiert aufgelistet, welche im Portal dargestellt werden können. Auf der rechten Seite befindet sich eine Übersichtskarte, eine Maßstabsauswahl, eine
Koordinatenanzeige sowie eine Legende.

Die zwei Bereiche lassen sich mit der Maus vergrößern, verkleinern sowie komplett schließen und wieder öffnen. Es besteht weiterhin die Möglichkeit, die Ebenenübersicht, die Übersichtskarte, usw. zu minimieren.

Zwischen den zwei Bereichen befindet sich das Kartenfenster zur Darstellung der Ebenen mit Maßstabsleiste und einem Navigationswerkzeug. In der Kopfleiste des Kartenfenster befinden sich weitere Werkzeuge, welche im weiteren Verlauf beschrieben werden.

Funktionen des XPlanPortals
---------------------------

Wechseln der Planart
++++++++++++++++++++

Über die Drop-Down-Liste kann in eine andere Planart (Bebauungspläne, Flächennutzungspläne und Landschaftspläne) gewechselt werden. Alternativ kann über die Startseite in das jeweilige Planungsportal gewechselt werden.

Die Portale können auch direkt aufgerufen werden:

.. code-block:: text
 
   http://<host>:<port>/portal-bplan/
   http://<host>:<port>/portal-fplan/
   http://<host>:<port>/portal-lplan/

Struktur und Funktion der Ebenenübersicht 
++++++++++++++++++++++++++++++++++++++++++++++++++

In der Ebenenübersicht werden alle Ebenen und Kategorien aufgelistet. Kategorien enthalten mehrere Ebenen. Sie sind mit einem Ordner gekennzeichnet und können aufgeklappt werden. Jede Kategorie und jede Ebene kann einzeln sichtbar (*offenes Auge*) und nicht sichtbar (*geschlossenes Auge*) geschaltet werden.
Um eine Kategorie bzw. Ebene zu bearbeiten, muss diese markiert werden. Die markierte Ebene wird nun als *Aktive Ebene* gekennzeichnet.

Überlappen sich Ebenen, wird die im Ebenenbaum oben liegende Ebene als erstes im Kartenfenster dargestellt. Die Zeichenreihenfolge kann manuell über den Button *Zeichenreihenfolge verändern* geändert werden. Die ausgewählte Ebene kann per Drag&Drop an die gewünschte Stelle verschoben werden. Die Zeichenreihenfolge ist absteigend.

Weitere externe WMS Ebenen können über des Button *WMS-Layer hinzufügen* in das Kartenfenster geladen werden. Nach Betätigen des Buttons öffnet sich ein Fenster, in dem der Nutzer eine WMS Adresse sowie den gewünschten Layer Namen eintragen kann. Mit dem Klick auf *Hinzufügen* wird die WMS Ebene im Kartenfenster dargestellt.

Über das Zahnrad kann die Sichtbarkeit bzw. die Deckkraft (Transparenz) für jede Ebene (außer die ROOT Ebene) einzeln eingestellt werden. Das Zahnrad wird eingeblendet, sobald der Mauszeiger über eine Ebene führt.

Die Deckkraft der Ebenen ist manuell regulierbar und die Sichtbarkeit kann per Häkchen aktiv / passiv geschaltet werden.

Navigation im XPlanPortal
+++++++++++++++++++++++++

Das Portal bietet dem Nutzer eine Vielzahl von Navigationsmöglichkeiten. Anhand der Übersichtskarte kann dieser sich orientieren. Es ist außerdem möglich, an eine gewünschte Stelle zu navigieren, indem das Rechteck bzw. Kreuz (abhängig von dem Maßstab) verschoben wird.

Um in ein bestimmtes Gebiet zu navigieren, kann der Nutzer das Werkzeug *Zoom Rechteck* (Lupe) aktivieren. Mit der Lupe kann ein Kartenausschnitt aufgezogen werden. Hierbei wird die Karte automatisch vergrößert.

Anschließend kann das Gebiet im Kartenfenster durch das Werkzeug *Verschieben*, welches vorher aktiviert werden muss, mit gedrückter Maustaste verschoben werden.

Möchte der Nutzer zu einem zuvor ausgewählten Bereich, kann dieser durch Aktivieren der Werkzeuge *Vorheriger Kartenausschnitt* und *Nächster Kartenausschnitt* zu dem jeweiligen Kartenausschnitt gelangen.

Innerhalb des Kartenfensters sind weitere Möglichkeiten, wie die Verschiebung des Kartenfensters in eine Himmelsrichtung und das Hinein- bzw. Hinauszoomen durch die Button *+* und *-* möglich. Zum Darstellen der maximalen Ausdehnung kann der Button zwischen *+* und *-* verwendet werden.

Das erneute Laden des Kartenausschnitts kann über *Karte aktualisieren* erfolgen.

Bedienung der Maßstabsauswahl
++++++++++++++++++++++++++++++

Die Maßstabsauswahl beinhaltet vordefinierte Maßstäbe, welche in einer Drop-Down-Liste ausgewählt werden können. Der Maßstab kann außerdem mittels Mausrad (*Scrollen*) verändert werden. Beim Zoom In und Zoom Out verändert sich die Maßstabsanzeige.

Koordinatenanzeige
++++++++++++++++++

Die Koordinatenanzeige zeigt die Koordinaten der Kartenposition, an der sich der Mauszeiger befindet. Als Koordinatensystem wird UTM Zone 33N (EPSG:25833) verwendet.

Bedienung des Messwerkzeuges
++++++++++++++++++++++++++++

Es besteht die Möglichkeit Entfernungen und Flächen zu messen. Hierfür muss das jeweilige Bedienelement (*Entfernung messen* bzw. *Fläche messen*) in der Kopfleiste aktiviert werden. Danach kann mit der Maus ein Startpunkt angelegt werden. Nun kann mit der Maus eine Linie 
bzw. eine Fläche aufgezogen werden. Dabei ist die Erstellung von mehreren Zwischenpunkten möglich. Die Entfernung wird in einem kleinem Fenster ausgegeben. Das Messen kann mit der rechten Maustaste abgebrochen werden.

Bedienung der Suchfunktion
++++++++++++++++++++++++++

Das XPlanPortal hat drei Suchfunktionen (*Räumliche Suche*, *Freie Suche* und *Kombinierte Suche*).

Räumliche Suche
^^^^^^^^^^^^^^^^

Die *Räumliche Suche* grenzt die Suche auf ein vorher festgelegtes Gebiet ein. Nachdem ein Geometrietyp (Punkt, Linie oder Fläche) ausgewählt wurde, kann das Gebiet ausgewählt bzw. eingegrenzt werden. Die Ergebnisse werden nach dem Betätigen des Buttons *Suche* in einer
Liste angezeigt. Nach Auswahl eines Suchergebnisses, kann dieses als *CSV*-Datei heruntergeladen werden. Des weiteren kann eine Detailansicht aufgerufen und auf das Objekt gezoomt werden.

Freie Suche
^^^^^^^^^^^

Die *Freie Suche* wird ohne räumliche Einschränkung ausgeführt. Zunächst muss eine Ebene sowie ein Attribut ausgewählt werden, welche durchsucht werden sollen. Zur Eingrenzung von Suchergebnissen können die Operatoren *enthält*, *ist gleich* oder *ist ungleich*
verwendet werden. Nach Eingabe eines Wertes in das dafür vorgesehene Feld und Betätigen des Button *Suche*, werden die Ergebnisse aufgelistet. Bei der *Freien Suche* können mehrere Bedingungen über das Zeichen *+* angegeben werden.

Kombinierte Suche
^^^^^^^^^^^^^^^^^

Die *Kombinierte Suche* beinhaltet die *Räumliche Suche* und *Freie Suche* und dient für komplexerer Abfragen. Die Auswahl von Suchbedingungen ist analog zur *Räumlichen Suche* und *Freien Suche*. Es können beliebig viele Verknüpfungen von Suchbedingungen
angegeben werden. Mit anschließendem Betätigen des Buttons *Suchen* werden die Suchergebnisse aufgelistet.

Bedienung der Objektinformationsanzeige
+++++++++++++++++++++++++++++++++++++++

Zum Abfragen von z.B. planspezifischen Eigenschaften eines Objektes eignet sich das Werkzeug *Sachinformation abfragen*. Zunächst muss das Werkzeug aktiviert werden. Nachdem zu einem Objekt navigiert wurde, kann durch Drücken der linken Maustaste auf das Objekt die
Objektinformationen in einem Fenster dargestellt werden. Liefern mehrere Ebenen ein Ergebnis zurück, werden diese aufgelistet.

Bedienung des Druckmoduls
+++++++++++++++++++++++++++++++++++++

Nach Aktivierung des Bedienelements zum Drucken, öffnet sich ein Fenster mit einem Dialogmenü. Dort können verschiedene Einstellungen, z.B.:

 * Titel,
 * Größe,
 * Ausrichtung und
 * Dateiname
 
vorgenommen werden. Zusätzlich besteht die Möglichkeit, die Auflösung manuell oder über einen Regler anzugeben.
