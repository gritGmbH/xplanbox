.. _anchor-manager-cli:

================
XPlanManager CLI
================
Die Komponente XPlanManager CLI ist ein Kommandozeilenwerkzeug, welches dem Fachadministrator der xPlanBox ermöglicht,
die Datenhaltung zu kontrollieren. Dabei ist das Kommandozeilenwerkzeug in der Lage, XPlanGML Instanzdokumente in die Datenhaltung zu Laden, zu Löschen,
Listenausgaben zu erzeugen sowie eingebettete Rasterpläne zu importieren.

Benutzungsanleitung
-------------------
Beim XPlanManager CLI handelt es ich um ein Kommandozeilentool, das parametrisiert aufgerufen wird. Da diese Anwendung bei der Installation in die PATH Variable aufgenommen wird, ist diese von einem beliebigen Ort aufrufbar.

.. _anchor-manager-cli-managerConfigFile:

Konfiguration über Datei
++++++++++++++++++++++++
In dem Verzeichnis *<manager-cli-directory>/etc/* befindet sich die Konfigurationsdatei *managerConfiguration.properties*, welche genutzt werden kann, um generelle Konfigurationen an dem Kommandozeilentool durchzuführen.

Falls der Parameter *--managerconfiguration* während eines Aufrufs des Kommandozeilentools nicht mitgegeben wird, nutzt das Tool die unter *etc* abgelegte Datei. Wenn der Parameter mitgegeben wird, muss sich die Konfigurationsdatei in dem referenzierten Verzeichnis befinden.

So kann über das Property *defaultBboxIn4326* für den 'Alternativen Betriebsmodus' konfiguriert werden, welche default Bounding-Box während der Konfigurationserzeugung für einen neuen Services genutzt werden soll. Dieser Wert wird genutzt, falls von dem zu importierenden GML keine Bounding-Box ermittelt werden kann. Häufige Gründe dafür sind zum Beispiel das Fehlen der CRS-Definition oder das Vorkommen von invaliden Geometrien. Das CRS der Bounding-Box muss in EPSG:4326 angegeben werden. Falls dieser Parameter nicht gesetzt oder leer ist, wird eine default Bounding-Box genutzt, welche die gesamte Welt abdeckt.

Hilfe
+++++
Die Hilfe mit den Angaben zu den möglichen Eingabeparametern lässt sich mit dem Parameter *-help* ausgeben.

Aufruf:

.. code-block:: text
 
  XPlanManager -help
 
Ausgabe:

.. code-block:: text

  Usage: XPlanManager <options>

   -help
   -list
   -delete   <planid>
   -import   [--force] <xplanarchiv> [--crs <CRS>] [--workspace <workspace verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]
   -export   <planid> [<verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]

  Alternativer Betriebsmodus:

   -deletewithconfig   <planid> [--workspace <workspace verzeichnis>]
   -importmakeconfig   [--force] <xplanarchiv> [--workspace <workspace verzeichnis>] [--crs <CRS>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]
   -createdb <DB Name> <JDBC-Connection> -u <user> -p <passwort> [-t <PostGis Template>]
   -updatewmssortdate [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]
   
  Raster-Operationen:

   -addlayer <bplan|lplan|rplan|fplan> <rasterplanid> <tiffid> <layername> <layertitle> [<categoryname>]
   -removelayer <bplan|lplan|rplan|fplan> <layername>
   -addcategory <bplan|lplan|rplan|fplan> [<uppercategory>] <categoryname> <categorytitle>
   -removecategory <bplan|lplan|rplan|fplan> <categoryname>
   -movelayer <bplan|lplan|rplan|fplan> <layername> <categoryname>

Auflistung
++++++++++
Der *-list* Parameter gibt die Liste der geladenen Pläne aus.

Aufruf:

.. code-block:: text
 
  XPlanManager -list
 
Beispiel Ausgabe:

.. code-block:: text

  Id: 3, Version: XPLAN_3, Typ: FP_Plan, Name: Gesamtgeltungsbereich Flächennutzungsplan, Nummer: 12062024, GKZ: 12062024, Features: 2808, Importiert: 2010-02-18 17:57:11.669
  Id: 5, Version: XPLAN_2, Typ: BP_Plan, Name: Gewerbe- und Industriepark Massen, Nummer: 01, GKZ: 111111, Features: 15, Importiert: 2010-02-18 17:58:21.583
  Id: 6, Version: XPLAN_3, Typ: BP_Plan, Name: Lokstedt, Nummer: 56, GKZ: 02000000, Features: 251, Importiert: 2010-02-18 17:58:57.2
  Id: 7, Version: XPLAN_3, Typ: BP_Plan, Name: Unbenannter Plan (3d099551-1f49-4b77-9008-68237a60426b), Nummer: -, GKZ: 4011000, Features: 351, Importiert: 2010-02-18 17:59:38.704
  Id: 8, Version: XPLAN_3, Typ: BP_Plan, Name: Bebauungsplan 2135, Nummer: 2135, GKZ: 4011000, Features: 241, Importiert: 2010-02-18 18:00:45.077
  Id: 9, Version: XPLAN_3, Typ: BP_Plan, Name: Bebauungsplan LA 22, Nummer: LA 22, GKZ: 1234567, Features: 146, Importiert: 2010-02-18 18:01:41.563
  Id: 10, Version: XPLAN_3, Typ: RP_Plan, Name: Regionalplanbeispiel Bereich Siedlungsstruktur, Nummer: Siedlungsstruktur 1, Features: 282, Importiert: 2010-02-18 18:02:25.616
  Id: 11, Version: XPLAN_3, Typ: LP_Plan, Name: Landschaftsplan 16 Gemeinde XYZ", Nummer: 16, Features: 1659, Importiert: 2010-02-18 18:03:22.091
  Id: 12, Version: XPLAN_3, Typ: BP_Plan, Name: Bebauungsplan LA 22, Nummer: LA 22, GKZ: 02000000, Features: 1350, Importiert: 2010-02-18 21:16:06.753 Anzahl Pläne: 11


Import
++++++
Ein Import kann durch Angabe des *-import* Parameters gefolgt vom Pfad zum Planarchiv angestoßen werden. Bei dem Import können XPlanGML-Vektordaten und XPlanGML-Rasterdaten ohne zusätzlichen Parameter in die Datenbasis geladen werden.

Beispiel Aufruf:

.. code-block:: text

  XPlanManager -import ../Input-Planverzeichnis/Infrastruktur.zip

Während des Imports finden zahlreiche Konsistenz- und Korrektheitsüberprüfungen statt. Dies betrifft u.a. Schemavalidität, Geometrievalidität, Korrektheit von Links, Angabe von Koordinatenreferenzsystemen, u.v.m.

Folgende Zusatzparameter können mit angegeben werden, um z.B. fehlende Informationen anzugeben.

.. code-block:: text

   --force
   --crs
   --workspace
   --managerconfiguration

Bitte beachten Sie die korrekte Reihenfolge der Parameterangabe.

*--force*

Enthält das XPlanGML Instanzdokument Geometriefehler o.ä., ist es dringend angeraten, diese vor einem Import zu bereinigen. Unter Inkaufnahme von unerwartetem Diensteverhalten ist es dennoch möglich, den Import eines Planes zu erzwingen. Das Erzwingen eines Imports erfolgt mit dem Zusatzparameter *--force*.

.. caution:: Bitte beachten Sie, dass dabei vorhandene Geometriefehler o.ä. übernommen werden und der importierte Plan dadurch fehlerhaft ist. Die Auswirkungen können von einer fehlerhaften Darstellung des Plans bis hin zu unerwarteten Verhalten der xPlanBox reichen.

.. code-block:: text

  XPlanManager -import --force ../Input-Planverzeichnis/Infrastruktur.zip

*--crs*

Fehlt die Angabe des Koordinatenreferenzsystem in den Daten, so kann diese mit dem Parameter *--crs* übergeben werden.

Beispiel Aufruf:

.. code-block:: text

  XPlanManager -import ../Input-Planverzeichnis/Infrastruktur.zip --crs EPSG:31467

*--workspace*

Sind mehrere Workspac Verzeichnisse vorhanden, so kann durch den Zusatzparameter *--workspace* das entsprechende Verzeichnis übergeben werden.

.. code-block:: text

  XPlanManager -import ../Input-Planverzeichnis/Infrastruktur.zip --workspace ~/.deegree/xplansyn-wms-workspace-test

*--managerconfiguration*

Sind mehrere Manager Konfigurationen vorhanden, so kann durch den Zusatzparameter *--managerconfiguration* die entsprechende Konfiguration übergeben werden.

.. code-block:: text

  XPlanManager -import ../Input-Planverzeichnis/Infrastruktur.zip --managerconfiguration ~/.deegree/managerConfiguration/

Beispiel Ausgabe für erfolgreichen Import

.. code-block:: text

  Analyse des XPlan-Archivs
  ('../../resources/testdata/XPlanGML_3_0/Infrastruktur.zip')...OK.
  - Analyse des Dokuments...OK [1167 ms]: XPLAN_3, RP_Plan, EPSG:31466
  - Schema-Validierung...OK [5135 ms]
  - Einlesen der Features (+ Geometrievalidierung)...OK [6486 ms]: 492 Features

  Geometrie-Warnungen: 20
  - LineString (Ende in Zeile 33698, Spalte 26): Geschlossene Kurve verwendet falsche Laufrichtung (CW).

  - Überprüfung der XLink-Integrität...OK [3 ms]
  - Überprüfung der externen Referenzen...OK [1 ms]
  - Erzeugen der XPlan-Syn Features...Keine Beschreibung für externen Code 'RpTextDefaultSymbol' (CodeList XP_StylesheetListe) gefunden. Verwende Code als Beschreibung. Keine Beschreibung für externen Code 'RpTextDefaultSymbol' (CodeList XP_StylesheetListe) gefunden. Verwende Code als Beschreibung.
  ...
  OK [6376 ms]
  - Einfügen der Features in den FeatureStore (XPLAN_3)...OK [9873 ms].
  - Einfügen der Features in den FeatureStore (XPLAN_SYN)...OK [9217 ms].
  - Einfügen in Manager-DB...OK [49 ms].
  - Einfügen von Plan-Artefakt 'xplan.gml'...OK.
  - Persistierung...OK [109 ms].
  Plan wurde eingefügt. Zugewiesene Id: 13


Rasterdatenanalyse
++++++++++++++++++
Die Rasterdaten können beim Import auf Nutzbarkeit überprüft werden, damit sichergestellt ist, dass diese korrekt in
den XPlan-WMS eingebettet werden können.

Beim Import wird das CRS des Rasterplans überprüft.

Beispiel Aufruf: ::

   ./XPlanManager -importmakeconfig ~/test-data/V4_1_ID_103-25832.zip --managerconfiguration .

Beispiel Ausgabe: ::

   Evaluationsergebniss von referenzierten Rasterdaten:
     - Name: B-Plan_Klingmuehl_Heideweg_Karte.tif Unterstütztes CRS: Ja Unterstütztes Bildformat: Ja
   Es existieren keine invaliden Rasterdaten
   - Einlesen der Features (+ Geometrievalidierung)...OK [839 ms]: 500 Features
   - Überprüfung der XLink-Integrität...OK [2 ms]

   - Erzeugen/Einsortieren der Rasterkonfigurationen (Veröffentlichungsdatum: 01.02.2002)...Succeeding plan id: null
   73_B-Plan_Klingmuehl_Heideweg_Karte
   77_B-Plan_Klingmuehl_Heideweg_Karte
   79_B-Plan_Klingmuehl_Heideweg_Karte
   OK [1591 ms]

   Rasterscans:
    - B-Plan_Klingmuehl_Heideweg_Karte.tif
   WMS Konfiguration für Id 79 nach /home/lgvxplanisk/.deegree/xplansyn-wms-workspace geschrieben.
   XPlan-Archiv wurde erfolgreich importiert. Zugewiesene Id: 79

Passt das CRS nicht mit dem CRS der Rasterdatenhaltung überein, so
erhält der Nutzer die Option, den Plan ohne Erzeugung der Rasterkonfiguration zu importieren: ::

   Evaluationsergebniss von referenzierten Rasterdaten:
     - Name: Abrundungssatzung_Gruhno_ergb.tif Unterstütztes CRS: Kein Unterstütztes Bildformat: Ja
   Aufgrund invalider Rasterdaten wird der Import abgebrochen. Sie können den Import ohne die Erzeugung von Rasterkonfigurationen erzwingen, indem Sie die Option --force angeben.

Bearbeitung von Ebenenbäumen
++++++++++++++++++++++++++++
Die Bearbeitung von Ebenenbäumen wird als Erweiterung des XPlanManagers bereitgestellt. Hiermit ist es möglich, Rasterlayer zusätzlich zur sortierten Kategorieebene auch noch thematisch zu organisieren. Die sortierte Kategorieebene kann nicht manuell bearbeitet werden. Die bereitgestellten Funktionen ergeben sich aus folgender Spezifikation:

1. XPlanManager fügt eine Ebene in den Ebenenbaum ein. Wird der *<categoryname>* weggelassen, wird die Ebene direkt unter der Wurzelebene eingefügt. Die tiffid ist hierbei der Datei-Basisname der gewünschten .tiff-Datei von dem Rasterplan.

.. code-block:: text

  XPlanManager -addlayer <bplan|rplan|fplan|lplan> <rasterplanid> <tiffid> <layername> <layertitle> [<categoryname>]
  
2. XPlanManager entfernt eine Ebene aus der Ebenenkonfiguration. 

.. code-block:: text

  XPlanManager -removelayer <bplan|rplan|fplan|lplan> <layername>

3. XPlanManager fügt eine Kategorieebene hinzu. Wird der *<uppercategory>* weggelassen, wird die Ebene direkt unter der Wurzelebene eingefügt, andernfalls wird diese unterhalb der mit *<uppercategory>* angegebenen Kategorieebene eingefügt. Das Verhalten ist rekursiv, d.h. die Verschachtelung der Kategorieebenen kann beliebig tief erfolgen.

.. code-block:: text

  XPlanManager -addcategory <bplan|rplan|fplan|lplan> [<uppercategory>] <categoryname> <categorytitle>

4. XPlanManager löscht eine Kategorieebene. Achtung: Handelt es sich bei der zu löschenden Kategorieebene um eine Ebene mit untergeordneten Kategorien, werden diese ebenfalls gelöscht!

.. code-block:: text

  XPlanManager -removecategory <bplan|rplan|fplan|lplan> <categoryname>

5. XPlanManager bewegt eine Ebene in eine andere Kategorieebene.

.. code-block:: text

  XPlanManager -movelayer <bplan|rplan|fplan|lplan> <layername> <categoryname>
  
Export
++++++
Der Export eines Planes erfolgt unter Angabe des *-export* Parameters gefolgt von der PlanID (kann zuvor mit *-list* herausgefunden werden) und dem Ausgabeverzeichnis.

Beispiel Aufruf:

.. code-block:: text

  XPlanManager -export 9 outputverzeichnis
 
Beispiel Ausgabe für erfolgreichen Export:

.. code-block:: text

  - Schreibe Artefakt 'xplan.gml'...OK.
  Plan 9 wurde nach 'xplan-exported-9.zip' exportiert.

Löschen
+++++++
Beim Löschen wird dem *-delete* Parameter die PlanID (kann zuvor mit *-list* herausgefunden werden) übergeben.

Beispiel Aufruf:

.. code-block:: text

  XPlanManager -delete 1

Beispiel Ausgabe:

.. code-block:: text

  - Entferne Plan 1 aus dem FeatureStore (XPLAN_3)...OK
  - Entferne Plan 1 aus dem FeatureStore (XPLAN_SYN)...OK
  - Entferne Plan 1 aus der Manager-DB...OK
  - Persistierung...OK
  Plan 1 wurde gelöscht. 

Alternativer Betriebsmodus
++++++++++++++++++++++++++
Alternativ zum normalen Import von Plänen bietet der XPlanManager die Möglichkeit, für jeden Plan eigene WMS
Konfigurationen zu verwalten, sodass planspezifische WMS Dienste möglich sind. Dazu gibt es insgesamt 3 verschiedene
Optionen für den XPlanManager.

Datenbank erzeugen:
  Mit dem XPlanManager kann die Datenhaltung für Xplan-Archive erzeugt werden. Der XPlanManager wird beim Erzeugen der
  Datenhaltung auf diese neue Datenbank eingestellt.

Bei Nutzung von PostgreSQL-Datenbanken mit PostGIS bis Version 1.5 muss die Datenbank über ein Template mit installierten
postgis-Erweiterungen erzeugt werden.

.. code-block:: text

  XPlanManager -createdb 'test' jdbc:postgresql://localhost:5432 -u postgres -p postgres -t template_postgis

In PostgreSQL-Datenbanken mit PostGIS ab Version 2.0 wird die Erweiterung vom Manager automatisch installiert. Daher
entfällt die Angabe eines Templates mit PostGIS-Erweiterung.

.. code-block:: text

  XPlanManager -createdb 'test' jdbc:postgresql://localhost:5432 -u postgres -p postgres

Aktualisierung des Sortierfeldes für die Visualisierung:
  Mit dem XPlanManager können die Werte der Sortierfelder in der Datenbank anhand einer bestehenden managerConfiguration.properties 
  Datei aktualisiert werden. Der Aufruf kann ohne Parameter oder mit dem optionalen Parameter *--managerconfiguration* erfolgen. 
  Details zu diesem Parameter sind im Abschnitt :ref:`Konfiguration über Datei <_anchor-manager-cli-managerConfigFile>` zu finden. 
  
.. code-block:: text

  XPlanManager -updatewmssortdate 
  
Konfiguration erzeugen:
  Soll beim Import eine WMS Konfiguration erzeugt werden, so muss der Import-Befehl folgendermaßen aussehen:

.. code-block:: text

   XPlanManager -importmakeconfig ../../resources/testdata/XPlanGML_4_0/Infrastruktur.zip

Über das Property *defaultBboxIn4326* in der Konfigurationsdatei *managerConfiguration.properties* kann angegeben werden, welche default Bounding-Box während der Konfigurationserzeugung für einen neuen Services genutzt werden soll (für weitere Details siehe *Konfiguration über Datei* weiter oben).

Folgende Zusatzparameter können mit angegeben werden:

.. code-block:: text

   --force
   --crs
   --workspace
   --managerconfiguration

Konfiguration löschen:
  Die entsprechende Konfiguration wird im Workspace *xplansyn-wms-workspace* im *.deegree* Verzeichnis des entsprechenden
  Nutzers abgelegt. Soll ein so importierter Plan gelöscht werden, ist folgender Befehl notwendig:

.. code-block:: text

   XPlanManager -deletewithconfig 1

Troubleshooting
+++++++++++++++
Beim Import sehr großer Archive, kann es zu einem *OutOfMemoryError* Laufzeitfehler kommen, da die Java Virtual Machine keinen weiteren freien Speicher allokieren kann.
Wenn der Server noch über freien Arbeitspeicher verfügt, dann kann dieser über die Umgebungsvariable *JAVA_OPTS* unter Linux wie folgt erhöht werden:

.. code-block:: text

  export JAVA_OPTS="-Xmx4096m"

Weitere Informationen zur Konfiguration des Servers im Kapitel :ref:`Bekannte Probleme - Kapazitätsbezogene Einschränkungen <_known-bugs>` und im Betriebshandbuch.