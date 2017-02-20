.. _anchor-validator-web:

==================
XPlanValidator Web
==================
Die Komponente XPlanValidator Web ist eine Web-Oberfläche, welche dem Fachadministrator der xPlanBox ermöglicht,
XPlanGML Instanzdokumente zu validieren. Der XPlanValidator Web ist auf dem Kommandozeilenwerkzeug aufgebaut.

Benutzungsanleitung
-------------------
Die Webanwendung XPlanValidator dient der Validierung von XPlanGML und ist unter:

.. code-block:: text

   http://<host>:<port>/xplanvalidator/

verfügbar.

Hinzufügen
++++++++++
Das Planarchiv kann über den Button *Durchsuchen* ausgewählt und über die Auswahl *Hochladen und Validierungsoption einstellen*
zur Web-Anwendung hinzugefügt werden.

Der Benutzer erhält einen Hinweis, dass der Plan in die Web-Anwendung geladen wurde.

Bezeichnung der Validation
++++++++++++++++++++++++++
Für jede Validation kann eine Bezeichnung vergeben werden, sodass der Durchlauf zu einem späterem Zeitpunkt eindeutig zugeordnet werden kann.

Validierungsart
+++++++++++++++
Die folgenden Validierungsarten können über ein Menü ausgewählt werden:
 * Syntaktische Validierung
 * Geometrische Validierung
 * Semantische Validierung

Die Auswahl ist obligatorisch.

Validierungsoption
++++++++++++++++++
Die folgenden Validierungsoptionen können über den Button *weitere Validierungsoptionen* ausgewählt werden:
 * falsche Laufrichtung von Polygonen ignorieren
 * Selbstüberschneidungen ignorieren
 * Toleranz für Stützpunktabstände in m
 * Sonstige Planwerke ignorieren
 * Präsentationsobjekte ignorieren

Die Änderungen können über den Button *Speichern* für den aktuellen Validationsdurchlauf gespeichert werden. Die Auswahl ist optional.

Validierung
+++++++++++
Die Validation kann über den Button *Validierung starten* gestartet werden.

Validierungsergebnis
++++++++++++++++++++
Das Ergebnis der Validierung kann im Anschluss als HTML, PDF und XML Report herunter geladen werden. Je nachdem ob ein Geometriefehler enthalten ist,
können die Geometriefehler als Shapefile und als Grafik gespeichert werden.

Alle Ergebnisdateien werden in einem Zip Archiv gespeichert. Der Dateiname enthält die Bezeichnung der Validation.
