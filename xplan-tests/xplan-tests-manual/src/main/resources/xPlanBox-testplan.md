# Testfälle für die xPlanBox 

Die xPlanBox setzt sich aus folgenden Komponenten zusammen, für die verschiedene Testfälle definiert wurden.


* [XPlanManagerCLI](#xplanmanagercli)
* [XPlanManagerWeb](#xplanmanagerweb)
* [XPlanValidatorCLI](#xplanvalidatorcli)
* [XPlanValidatorWeb](#xplanvalidatorweb)
* [XPlanValidateDB-CLI](#xplanvalidatedb-cli)         
* [XPlanTransformCLI](#xplantransformcli)
* [XPlanAuswerteschemaCLI](#xplanauswerteschemacli)
* [XPlanWMS](#xplanwms)
* [XPlanWFS/XPlanSynWFS](#xplanwfsxplansynwfs)
* [XPlanRessourcen](#xplanresssourcen)


# XPlanManagerCLI 

### Prüffall-01: Hilfe aufrufen

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanManagerCLI mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-manager-cli-$VERSION/bin`.
**01** | Der Benutzer ruft die Hilfe mit dem Befehl in [1] auf. | Die Ausgabe gibt Auskunft über alle möglichen Eingabeparameter des XPlanManagerCLI. 

**Hinweis**

* [1] `~/xplan-manager-cli-$VERSION/bin`
* [1] `./XPlanManager -help `

---

### Prüffall-02: Erzeugung der DB Schemata 

#### Vorbedingungen 
 * Die DB-Tabelle der Datenbasis ist leer, beinhaltet somit kein Datenbank-Schema.
 * Der Nutzer hat die Rechte, eine Datenbank anzulegen.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**02** | Der Benutzer führt den in [1] aufgelisteten Befehl in der Kommandozeile aus. | Der XPlanManager kann das benötigte Datenbankschema selbst erzeugen. 
**03** | Der Benutzer überprüft, ob das Datenbank-Schema erstellt worden ist. | Das Datenbank-Schema wurde erzeugt. 

**Hinweis**

* [1]  ` ./XPlanManager -createdb <dbname> jdbc:postgresql://<host>:<port> -u <username> -p <password> -t template_postgis `
  
---

### Prüffall-03: DB Anbindung 

#### Vorbedingungen 

 * Der Prüffall-02 wurde erfolgreich ausgeführt.

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft in dem Manager Workspace [1], ob die im Prüffall-02 verwendeten Einstellungen in der Datenbank-Verbindung geschrieben wurden | Die Datenbank-Einstellungen entsprechen den Eingaben von Prüffall-02. 

**Hinweis**

 * [1]  ` .deegree/xplan-manager-workspace/jdbc/xplan.xml`
   * Der Pfad kann variieren.

---

### Prüffall-04: Planverwaltung

#### Vorbedingungen 

 * Der Prüffall-03  wurde erfolgreich ausgeführt.

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert mit dem Befehl [1] einen Plan in den XPlanManager. | Der Plan wird in den XPlanManager importiert, je nach Konfiguration auch mit Geometriefehlern (Nutzung von [--force]).
**02** | Der Benutzer ruft mit dem Befehl [2] eine Auflistung der im XPlanManager vorliegenden Pläne auf und überprüft somit, ob der in Schritt 01 importierte Plan vorhanden ist. |  Die vorliegenden Pläne werden aufgelistet und der in Schritt 01 importierte Plan wird angezeigt.
**03** | Der Benutzer exportiert einen Plan mit Hilfe des Befehls in [3] aus dem XPlanManager. | Der exportierte Plan wird im ausgewählten Verzeichnis angezeigt.
**04** | Der Benutzer löscht mit dem Befehl [4] einen Plan aus dem XPlanManager. | Der Plan wird aus dem XPlanManager gelöscht, geprüft werden kann dies mit erneuter Ausführung des Befehls [2].

**Hinweis**

* Korrekte Reihenfolge der Parameter beachten!
* [1] `./XPlanManager -import [--force] <xplanarchiv> [--crs <CRS>] [--workspace <workspace verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]`
* [2] `./XPlanManager -list`
* [3] `./XPlanManager -export   <planid> [<verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]`
* [4] `./XPlanManager -delete   <planid>`
 
---

# Prüffall-05: Aktualisierung des Sortierfeldes für die Visualisierung im XPlanwerkWMS

#### Vorbedingungen 

 * Der Prüffall-03  wurde erfolgreich ausgeführt.
 * Das zur Sortierung genutzte Datumsfeld wurde in der Datei <XPLANBOX_CONFIG>/managerConfiguration.properties erfolgreich konfiguriert.

#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt mit dem Befehl [1] eine Aktualisierung des WMS-Sortierfeldes durch. | Die Sortierung der Pläne im XPlanwerkWMS ändert sich.
**02** | Der Benutzer wecheselt in den deegree XPlanwerkWMS Workspace und überprüft das Ergebnis in den Themes, siehe [2]. | Die Sortierung der Pläne im XPlanwerkWMS hat sich mit der gewählten Konfiguration geändert.

**Hinweis**

* [1]  ` ./XPlanManager -updatewmssortdate [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]` 
* [2]  ` .deegree/xplansyn-wms-workspace/themes/bplanraster.xml`
  * Der Pfad kann variieren. 

---

# Prüffall-06: Bearbeitung von Ebenenbäumen

#### Vorbedingungen 

 * Der Prüffall-03  wurde erfolgreich ausgeführt.

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer fügt mit dem Befehl [1] eine Ebene in den Ebenenbaum ein. | Die neue Ebene wird eingefügt, beim weglassen des Parameters <categoryname> wird die Ebene direkt unter der Wurzelebene eingefügt.
**02** | Der Benutzer entfernt mit dem Befehl [2] eine Ebene aus dem Ebenenbaum. | Die Ebene wird aus der Ebenenkonfiguration entfernt.
**03** | Der Benutzer fügt mit dem Befehl [3] eine Katergorieebene in den Ebenenbaum ein. | Die neue Kategorieebene wird eingefügt. Beim angeben des Parameters <uppercategory> wird die neue Kategorieebene direkt unter die angegebene Kategorieebene eingefügt. Das Verhalten ist rekursiv, d.h. die Verschachtelung der Kategorieebenen kann beliebig tief erfolgen.
**04** | Der Benutzer entfernt mit dem Befehl [4] eine Kategorieebene aus dem Ebenenbaum. | Die Kategorieebene wird aus der Ebenenkonfiguration entfernt. Enthält die gewählte Kategorieebene untergeordneten Kategorien, werden diese ebenfalls gelöscht.
**05** | Der Benutzer bewegt mit dem Befehl [5] eine Ebene in eine andere Kategorieebene des Ebenenbaums. | Die Ebene wird in die andere Kategoriebene bewegt.

**Hinweis**

* [1]  ` ./XPlanManager -addlayer <bplan|rplan|fplan|lplan> <rasterplanid> <tiffid> <layername> <layertitle> [<categoryname>]` 
* [2]  ` ./XPlanManager -removelayer <bplan|rplan|fplan|lplan> <layername>`
* [3]  ` ./XPlanManager -addcategory <bplan|rplan|fplan|lplan> [<uppercategory>] <categoryname> <categorytitle>`  
* [4]  ` ./XPlanManager -removecategory <bplan|rplan|fplan|lplan> <categoryname>`
* [5]  ` ./XPlanManager -movelayer <bplan|rplan|fplan|lplan> <layername> <categoryname>`

---

# Prüffall-07: Visualisierung von importierten XPlanGML-Rasterdaten 

#### Vorbedingungen 

 * Der Prüffall-03  wurde erfolgreich ausgeführt.
 
 Beispielhaft werden anhand der Testdaten XPlanGML-Pläne (Rasterdaten) mit verschiedenen Versionen über den XPlanManager in die Datenbasis geladen und mittels GetMap-Request über den XPlanWMS wieder abgerufen.
 
#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert mit dem Befehl in [1] einen Plan mit Rasterdaten in den XPlanManager. | Der Plan wird in den XPlanManager importiert.
**02** | Der Benutzer lässt sich den importierten Plan mit dem Befehl in [2] auflisten. | Der importierte Plan wird aufgelistet.
**03** | Der Benutzer führt eine GetMap-Anfrage wie in [3] durch. | Der importierte Rasterplan wird dargestellt. 

**Hinweis**

* [1]  ` ./XPlanManager -import [--force] <xplanarchiv> [--crs <CRS>] [--workspace <workspace verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]` 
* [2]  ` ./XPlanManager -list `
* [3] <http://<host:port>/xplan-wms/services/wms?REQUEST=GetMap&SERVICE=WMS&VERSION=1.1.1&WIDTH=1081&HEIGHT=725&LAYERS=bplanraster_sortiert&TRANSPARENT=TRUE&FORMAT=image%2Fpng&BBOX=417326.9138990595,5715257.490169556,418938.9357000923,5716338.633375614&SRS=EPSG:25833&STYLES=default>


# XPlanManagerWeb

### Prüffall-01: Plan-Funktion: Hinzufügen

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * XPlanArchive sind verfügbar.
 * Der Benutzer ist auf dem System angemeldet.
 
#### Prüffall 
Schritt | Beschreibung | Erwartetes Ergebnis 
----------- |------------------|-------------------------
**01** | Der Benutzer klickt auf den Button **Datei auswählen**. | Es öffnet sich ein neues Fenster zur Auswahl von Plänen. 
**02** | Der Benutzer wählt eine beliebige Datei (außer ein Plan im `Zip`-Format) aus klickt auf **Öffnen**. | Die beliebige Datei wird zwischengelagert in der Weboberfläche angezeigt.
**02.1** | Der Benutzer klickt auf den Button **Hinzufügen**. | Es wird ein Fenster geöffnet. Die Datei wird abgelehnt und nicht hoch geladen. 
**02.3** | Der Benutzer klickt auf den Button **Schließen**. | Das Fenster wird geschlossen.
**03** | Der Benutzer wählt ein Plan im `Zip`-Format aus. | Der Plan wird markiert im Fenster dargestellt. 
**03.1** | Der Benutzer klickt auf den Button **Datei auswählen**. | Das Fenster schließt sich. Der Planname wird zwischengelagert in der Weboberfläche angezeigt. 
**03.2** | Der Benutzer klickt auf den Button **Hinzufügen**. | Der Plan wird hoch geladen. Nach Ende des Uploads wird ein neues Fenster geöffnet, mit der Meldung, dass der Upload abgeschlossen ist. 
**03.3** | Der Benutzer klickt auf **Ok**. | Das Fenster wird geschlossen. Der Plan wird angezeigt. 
**04** | Der Benutzer wählt ein Plan im `Zip`-Format aus. | Der Plan wird markiert im Fenster dargestellt. 
**04.1** | Der Benutzer klickt auf den Button **Datei auswählen**. | Das Fenster schließt sich. Der Planname wird angezeigt. 
**04.2** | Der Benutzer klickt auf den Button **Hinzufügen**. | Der Plan wird hoch geladen. Nach Ende des Uploads wird ein neues Fenster geöffnet, mit der Meldung, dass der Upload abgeschlossen ist. 
**04.3** | Der Benutzer klickt auf **Ok**. | Das Fenster wird geschlossen. Der Plan wird angezeigt. 
**04.4** | Der Benutzer klickt auf **Entfernen**. | Es wird ein neues Fenster geöffnet, mit der Meldung, ob der Plan wirklich entfernt werden soll.
**04.5** | Der Benutzer klickt auf **Ok**. | Es wird ein neues Fenster geöffnet, mit der Meldung, dass das Entfernen abgeschlossen ist.
**04.6** | Der Benutzer klickt auf **Ok**. | Es ist kein Plan aufgelistet, welcher zur Validation aussteht.

---

### Prüffall-02: Plan-Funktion: Validieren
  
#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen hoch geladenen Plan auf den Button **Validieren**. | Ein neues Fenster mit dem XPlanValidator öffnet sich. 
**02** | Der Benutzer vergibt eine _Bezeichnung_. | Die Bezeichnung wird im Feld dargestellt. 
**03** | Der Benutzer wählt einen _Validierungstyp_ aus. | Der Validierungstyp wird im Feld dargestellt. 
**04** | Der Benutzer startet den XPlanValidator. | Das Validierungsergebnis wird dargestellt. 
**05** | Der Benutzer kehrt zu dem XPlanManager zurück. | Die Web-Oberfläche des XPlanManagers wird angezeigt. 
**06.1** | Die Schaltfläche **Validieren** je nach Ergebnis rot (Validierung fehlgeschlagen). | Bei erfolgreicher Validierung wird die Schaltfläche **Import** freigegeben. 
**06.2** | Die Schaltfläche **Validieren** je nach Ergebnis grün (Validierung erfolgreich) eingefärbt. | Bei fehlgeschlagener Validierung wird die Schaltfläche **Import** nicht freigegeben. 

**Hinweis**

 * Der Prüffall muss insgesamt zwei mal ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
---
 
### Prüffall-03: Plan-Funktion: Import

### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt (XPlanArchive sind in der Datenbasis vorhanden).
 * Der Prüffall-02 wurde erfolgreich ausgeführt (Es sind valide und invalide XPlanArchive vorhanden).
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen syntaktisch, semmantisch oder geometrisch invaliden Plan auf den Button **Import**. | Der Button ist deaktiviert und kann nicht angeklickt werden.
**02** | Der Benutzer klickt neben einen validen Plan auf den Button **Import**. | Es öffnet sich ein Fenster zur Angabe des Gültigkeitszeitraums.  
**02.1** | Der Benutzer wählt einen _Gültigkeitszeitraum_ aus oder klickt ohne Angaben den Button **Weiter**. | Es öffnet sich ein weiteres Fenster um den Rechtsstand auszuwählen.
**02.2** | Der Benutzer wählt einen _Rechtsstand_ aus oder klickt ohne Änderung den Button **Weiter**. | Es öffnet sich ein weiteres Fenster für die Analyse der Rasterdaten.
**02.3** | Der Benutzer wählt klickt den Button **Weiter mit Rasterdaten**, klickt den Button **Weiter ohne Rasterdaten** oder bricht den Import ab mit dem Button **Abbrechen**. | Beim klicken von den Button **Weiter mit/ohne Rasterdaten** wird Plan importiert, es öffnet sich ein Fenster mit der Meldung, dass der Import abgeschlossen ist.
**02.4** | Der Benutzer wählt klickt den Button **Ok**. | Der Plan wird in der Plan-Liste angezeigt.
**03** | Der Benutzer überprüft mit dem XPlanManager CLI (Parameter: `-list`), ob ein Plan importiert wurde. | Der zuvor ausgewählte Plan wurde importiert. 

---

### Prüffall-04: Plan-Liste


#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Oberfläche des XPlanManagers auf die in [1] aufgelisteten Punkte. | Die in [1] aufgelisteten Punkte sind vorhanden. 
**02** | Der Benutzer überprüft die tabellarische Anzeige des XPlanManagers auf die in [2] aufgelisteten Punkte. | Die in [2] aufgelisteten Punkte sind vorhanden. 

**Hinweis**

* [1] Zur Unterstützung des Arbeitsablaufs bietet die Oberfläche dem Nutzer:

  * eine tabellarische Anzeige aller XPlanGML-Dokumente.

* [2] Die Liste enthält die folgenden Eigenschaften:
    
  * Name [sortierfähig]
  * ID [sortierfähig]
  * Nummer [sortierfähig]
  * Gemeinde [sortierfähig]  
  * XPlan GML Version [sortierfähig]
  * Planart [sortierfähig]
  * sonstige Planart [sortierfähig]
  * Rechtsstand [sortierfähig]
  * Datum Veröffentlichung [sortierfähig]
  * Datum Import [sortierfähig]
  * Planstatus [sortierfähig]
  * Gültigkeit (rot, grün)
  * Aktionen: Plan editieren, Kartenvorschau, Plan publizieren, Herunterladen, Entfernen.
 
--- 
  
### Prüffall-05: Plan-Funktion: Kartenvorschau

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen Plan auf den Button **Kartenvorschau**. | Es öffnet sich ein neues Fenster. Der Plan wird in einer Vorschau angezeigt. 

---

### Prüffall-06: Plan-Funktion: Plan publizieren (Transformation nach INSPIRE PLU)

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 
 
Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen Plan auf den Button **Plan publizieren**. | Es öffnet sich ein Pop-up, welches den Vorgang bestätigt. 
**02** | Der Benutzer geht auf die xPlanBox Landingpage und überprüft, ob der Plan in den XPlanInspirePluDiensten aufgelistet wird. | Der publizierte Plan wird in den XPlanInspirePluDiensten aufgelistet.  

---

### Prüffall-07: Plan-Funktion: Export

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 
 
Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen Plan auf den Button **Herunterladen**. | Es öffnet sich ein neues Fenster zur Auswahl des Speicherplatzes. 
**02** | Der Benutzer wählt ein Verzeichnis zum Speichern des Plans. | Das Verzeichnis wird im Fenster dargestellt. 
**03** | Der Benutzer klickt auf den Button **Speichern**. | Das Fenster schließt sich. Der Plan wird von der Datenbasis lokal gespeichert. 

---

### Prüffall-08: Plan-Funktion: Entfernen

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 * Der Benutzer ist auf dem System angemeldet.
 
#### Prüffall 
 
Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt neben einen Plan auf den Button **Entfernen**. | Es öffnet sich ein neues Fenster zur Bestätigung des Vorgangs. 
**02** | Der Benutzer bestätigt mit **OK**. | Es öffnet sich ein Dialog mit dem Inhalt `Plan wird entfernt...`. 
**03** | Der Benutzer wartet während der Löschvorgang durchgeführt wird. | Das Dialog-Fenster schließt sich. Der Plan wird aus der Datenbasis gelöscht und nicht mehr in der Plan-Liste dargestellt. 
**04** | Der Benutzer überprüft mit dem XPlanManager CLI (Parameter: `-list`), ob der zuvor ausgewählte Plan gelöscht wurde. | Der zuvor ausgewählte Plan wurde gelöscht. 

---

### Prüffall-09: Plan-Funktion: Editieren

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
* Der Benutzer ist am XPlanManagerWeb angemeldet und hat die Berechtigung zum Editieren von Planstammdaten.

#### Prüffall 1

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Möglichkeit der Editierbarkeit.| Hinter den Plänen wird je eine Schaltfläche **editieren**  angezeigt.
**02** | Der Benutzer drückt auf die Schaltfläche **editieren**.| Es öffnet sich ein neues Fenster mit einem Formular.
**03** | Der Benutzer editiert die _Stammdaten_ in den **Basisdaten** und **Gültigkeitszeitraum** valide und bestätigt die Änderung durch die Schaltfläche **Speichern**.| Speicherung der Veränderungen.
**04** | Der Benutzer editiert die _Stammdaten_ in den **Basisdaten** und **Gültigkeitszeitraum** nicht valide und bestätigt die Änderung durch die Schaltfläche **Speichern**.| Es öffnet sich eine Fehlermeldung mit dem Vermerk auf den Fehler.

#### Prüffall 2 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.| Es öffnet sich ein neues Fenster mit einem Formular.
**02** | Der Benutzer klickt auf **Änderung hinzufügen**.| Es öffnet sich ein Dialog mit einem Formular.
**03** | Der Benutzer editiert die Daten unter **Neue Änderung anlegen** ohne dabei falsche Eingaben zu machen und bestätigt das Ergebnis durch die Schaltfläche **Speichern**.| Die geänderten Daten werden in die Liste der Änderungen übernommen.
**04** | Der Benutzer editiert die Daten unter **Neue Änderung anlegen**, wobei kein Planname angegeben wird, und bestätigt die Änderung durch die Schaltfläche **Speichern**.| Es wird eine Fehlermeldung mit dem Vermerk auf den fehlenden Plannamen angezeigt.
**05** | Der Benutzer fügt einen _Plannamen_ ein und bestätigt die Änderung durch die Schaltfläche **Speichern**.| Die geänderten Daten werden in die Liste der Änderungen übernommen.

#### Prüffall 3 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** |Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.|Es öffnet sich ein neues Fenster mit einem Formular.
**02** |Der Benutzer folgt unter „Dokumente“ den Schritten 02 und 03 von Prüffall 2 entsprechend.| Ergebnis siehe Prüffall 2 Schritt 02 und 03.

#### Prüffall 4 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.| Es öffnet sich ein neues Fenster mit einem Formular.
**02** | Der Benutzer editiert die Angaben unter **Rasterbasis** ohne dabei falsche Eingaben zu machen und bestätigt das Ergebnis durch die Schaltfläche **Speichern**.| Speicherung der Veränderungen.


#### Prüffall 5

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** |  Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.| Es öffnet sich ein neues Fenster mit einem Formular.
**02** | Der Benutzer editiert lediglich das _Datum der Rechtsverordnung_ in den **Basisdaten** und bestätigt die Änderung durch die Schaltfläche **Speichern**. Die Eingabe ist valide.| Die Reihenfolge der aufgelisteten Pläne auf der Web-basierten Benutzeroberfläche des XPlanManagers hat sich verändert.
**03** | Der Benutzer editiert lediglich das _Datum der Rechtsverordnung_ in den **Basisdaten** und bestätigt die Änderung durch die Schaltfläche **Speichern**. Die Eingabe ist nicht valide.| Es wird eine Fehlermeldung mit dem Vermerk auf den Fehler angezeigt.

#### Prüffall 6

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.| Es öffnet sich ein neues Fenster mit einem Formular.
**02** | Der Benutzer editiert lediglich den _Rechtsstand_ in den **Basisdaten** und bestätigt die Änderung durch die Schaltfläche **Speichern**.| Der bearbeitete Plan wird in der entsprechende Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.

#### Prüffall 7

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer folgt den Schritten 01 bis 02 von Prüffall 1.| Es öffnet sich ein neues Fenster mit einem Formular.
**02** | Der Benutzer editiert bzw. löscht beliebig viele Stammdaten und bricht das Editieren durch **Abbruch** ab. | Die ursprünglichen Plandaten sind nicht verändert.


#### Prüfall 8

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01**| Der Benutzer exportiert einen zuvor bearbeiteten Plan. | Das exportierte Planarchiv enthält die geänderten Daten.

---

### Prüffall-10: XPlanManager-Funktion: Anzeigefilter

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.

#### Prüffall 1 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer sucht die Pläne einer bestimmten Gemeinde (Suchfilter **Gemeindeauswahl**) mit uneingeschränktem Planstatus. | Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.

#### Prüffall 2 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer sucht die Pläne eines bestimmten Status (Suchfilter **Planstatus**) in allen Gemeinden. | Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.


#### Prüffall 3 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer sucht die Pläne einer bestimmten Gemeinde (Suchfilter **Gemeindeauswahl**) und einem bestimmten Planstatus (Suchfilter **Planstatus**). | Es werden alle der Suchanfrage entsprechenden Pläne angezeigt.
**02** | Der Benutzer wählt **Name** und gibt einen _Namen(steil)_ eines in der Planliste angezeigten Plans an. | Die zuvor angezeigte Liste ist auf die Pläne eingeschränkt, deren Namen mit der Nutzereingabe übereinstimmen.
**03** | Der Benutzer wählt **Alle Pläne anzeigen** | Die zuvor gesetzten Auswahlkriterien werden zurückgesetzt, und es werden alle Pläne angezeigt.




### Prüffall-11: AD mit Gruppenhierarchien anbinden

### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
 * Es existiert ein Nutzer „A", dieser ist Mitglied der Gruppe „B-Plan“. Gruppe „B-Plan“ ist Mitglied der Gruppe „C“. Gruppe C ist berechtigt ausschließlich Pläne aus der Gemeinde XY zu importieren.

#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Web-basierte Benutzeroberfläche des XPlanManagers. | Es wird ein Anmeldefenster angezeigt. 
**02** | Der Benutzer meldet sich als Nutzer „A" an. | Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet. 
**03** | Der Benutzer importiert einen Plan aus der Gemeinde „XY". | Der Plan wird der Liste hinzugefügt. 
**04** | Der Benutzer importiert einen Plan aus einer anderen Gemeinde (z.B. „YZ"). | Der Plan kann nicht importiert werden. 

---


### Prüffall-12: Kopplung der Editierfunktion an das Active Directory


#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar. 
* Es existiert ein Nutzer „A“, dieser ist Mitglied der Gruppe „Editor“ (oder ähnliches). Die Gruppe „Editor“ (oder ähnliches) ist berechtigt Pläne des Bezirkes „XY“ zu editieren.
* Es existiert ein Nutzer „B“, dieser ist Mitglied der Gruppe „B“. Die Gruppe „B“ ist nicht berechtigt zu editieren.


#### Prüffall 1 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Web-basierte Benutzeroberfläche des XPlanManagers.| Es wird ein Anmeldefenster angezeigt.
**02** | Der Benutzer meldet sich als Nutzer „A“ an.|Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet.
**03** | Der Benutzer überprüft die Schaltfläche „editieren“ der  Gemeinde „XY“ zugehörigen Pläne.| Die Schaltfläche ist farblich gefüllt und beim klicken auf die Schaltfläche öffnet sich ein neues Fenster mit einem Formular.
**04** | Der Benutzer überprüft die Schaltfläche „editieren“ von Plänen einer anderen Gemeinde.| Die Schaltfläche ist ausgegraut und beim Klick auf die Schaltfläche passiert nichts.


#### Prüffall 2 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Web-basierte Benutzeroberfläche des XPlanManagers. | Es wird ein Anmeldefenster angezeigt.
**02** | Der Benutzer meldet sich als Nutzer „B“ an.| Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet.
**03** | Der Benutzer überprüft die Möglichkeit der Editierbarkeit.| Hinter den Plänen wird je eine ausgegraute Schaltfläche „editieren“  angezeigt.


#### Prüffall 3 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Server-Schnittstelle des XPlanManagers.| Es öffnet sich ein Anmeldefenster.

---

### Prüffall-13: Design der XPlanManager UI für unterschiedliche Bildschirmgrößen optimieren

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.

#### Prüffall 
 
Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**-** | - | Die vertikalen Abstände zwischen den Dialogboxen und den Tabellen sowie zwischen dem Header und dem Bereich **Plan hinzufügen** sind reduziert. 
**01** | Die Größe des Fensters wird auf die Breite von 1080 Pixeln verändert. | Es wird kein horizontaler Rollbalken angezeigt. 
**02** | Das Fenster wird beliebig in der Breite vergrößert. | Es wird ein horizontaler Rollbalken angezeigt. 

---

### Prüffall-14: Optimierte Anbindung der Verfahrensdatenbank

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar. 
* Es existiert ein Nutzer „A“, dieser ist Mitglied der Gruppe „Super-Administrator“. Die Gruppe „Super-Administrator“ ist berechtigt zu importieren und beim Import das Planverfahren manuell zu bestimmen, wenn keine automatische Zuordnung statt findet.
* Es existiert ein Nutzer „B“, dieser ist Mitglied der Gruppe „B“. Die Gruppe „B“ ist berechtigt zu importieren jedoch können diese, beim Import, das Planverfahren nicht manuell bestimmen, wenn keine automatische Zuordnung statt findet.

#### Prüffall 1

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Web-basierte Benutzeroberfläche des XPlanManagers.| Es wird ein Anmeldefenster angezeigt.
**02** | Der Benutzer meldet sich als Nutzer „A“ an.|Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet.
**03** | Der Benutzer importiert einen Plan mit automatischer Zuordnung des Planverfahrens.| Es muss nicht manuell eingegriffen werden und der Plan wird importiert.
**04** | Der Benutzer importiert einen Plan ohne automatische Zuordnung des Planverfahrens.| Es öffnet sich ein neues Fenster mit Auswahlmöglichkeiten des Planverfahrens.

#### Prüffall 2 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die Web-basierte Benutzeroberfläche des XPlanManagers.| Es wird ein Anmeldefenster angezeigt.
**02** | Der Benutzer meldet sich als Nutzer „B“ an.| Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet.
**03** | Der Benutzer importiert einen Plan mit automatischer Zuordnung des Planverfahrens.| Der Plan wird importiert.
**04** | Der Benutzer importiert einen Plan ohne automatische Zuordnung des Planverfahrens.| Der Importvorgang wird abgebrochen. Es wird eine entsprechende Fehlermeldung angezeigt.

#### Prüffall 3 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt die Schritte 01 und 02 von Prüffall 2 aus.| Es öffnet sich die Web-basierte Benutzeroberfläche des XPlanManagers, bereits importierte Pläne werden aufgelistet.
**02** | Der Benutzer importiert einen Plan.| Es öffnet sich ein neues Fenster.
**03** | Der Benutzer überprüft die Schaltflächen der Fenster.| In jedem Fenster ist eine Schaltfläche „Abbruch“ zu finden.

---

### Prüffall-15: Gültigkeitszeitraum für Rasterdaten in XPlanWMS unterstützen


#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
* Der Benutzer ist am XPlanManager-Web angemeldet und hat die Berechtigung zum Import von Planarchiven. 

#### Prüffall 

Schritt |Beschreibung |Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert einen Rasterplan mit unbegrenztem Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung". | Der Plan wird importiert. Der Gültigkeitszeitraum ist grün.  
**02** | Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird) | Der Workspace wird neu geladen. 
**03** | Der Benutzer öffnet die Kartenvorschau | Der Rasterplan wird abgebildet. 
**04** | Der Benutzer löscht den zuvor importierten Plan. | Der Plan wurde gelöscht. 
**05** | Der Benutzer importiert den gleichen Rasterplan erneut mit einem in der Vergangenheit liegenden Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung". |Der Plan wird importiert. Der Gültigkeitszeitraum ist rot.
**06** | Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird) | Der Workspace wird neu geladen. 
**07** | Der Benutzer öffnet die Kartenvorschau | Es wird lediglich der Umring des Vektorplans abgebildet. 
**08** | Der Benutzer löscht den zuvor importierten Plan. | Der Plan wurde gelöscht. 
**09** | Der Benutzer importiert den gleichen Plan erneut mit einem in der Zukunft liegenden Gültigkeitszeitraum. Der Plan befindet sich "In Aufstellung". | Der Plan wird importiert. Der Gültigkeitszeitraum ist rot. 
**10** | Der Benutzer öffnet [1] und führt einen Workspace-Reload durch (wenn dieser nicht automatisch durchgeführt wird) | Der Workspace wird neu geladen. 
**11** | Der Benutzer öffnet die Kartenvorschau. | Es wird lediglich der Umring des Vektorplans abgebildet. 
**12** | Der Benutzer klickt auf den Button **Editieren**. | Es öffnet sich ein neues Fenster mit dem editier Formular. 
**13** | Der Benutzer verändert den Gültigkeitszeitraum auf einen unbegrenzten Zeitraum und drückt auf speichern. | Der Gültigkeitszeitraum wird grün dargestellt.
**14** | Der Benutzer öffnet die Kartenvorschau. | Der Rasterplan wird abgebildet. 


**Hinweis:**

Dieser Test wird sowohl für !GeoTiff-Rasterplänen als auch für PNG- Rasterplänen durchgeführt.

[1] `http://<host>:<port>/xplan-wms`


---

### Prüffall-16: Unterstützung von externen Codelisten in der XPlanBox

#### Vorbedingungen

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
* Der Benutzer ist am XPlanManager-Web angemeldet und hat die Berechtigung zum Import von Planarchiven. 

#### Prüffall

Schritt |Beschreibung |Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert einen Plan. | Der Plan wird erfolgreich importiert. 
**02** | Der Benutzer bindet den WMS in QGIS ein. | Der importierte Plan wird angezeigt. 
**03** | Der Benutzer führt eine Objektinformations-Abfrage in QGIS durch (!GetFeatureInfo Operation). | Die Objektinformationen werden angezeigt. Der Wert für die im nächsten Schritt zu übersetzende Codeliste ist leer oder enthält den nicht übersetzten Code. 
**04** | Der Benutzer navigiert in den Ordner [1] und legt dort die Dateien `xplan[VERSION].syn` und `xplan_[CODELIST_NAME].xml` ab. 
**05** | Der Benutzer löscht den zuvor importierten Plan. | Der Plan wird gelöscht. 
**06** | Der Benutzer importiert den Plan erneut. | Der Plan wird erfolgreich importiert. 
**07** | Der Benutzer bewegt das Kartenbild in QGIS. | Der WMS wird neu geladen. 
**08** | Der Benutzer führt eine Objektinformations-Abfrage in QGIS durch (!GetFeatureInfo Operation). | Es werden die Objektinformationen der neuen Codeliste im entsprechenden Feld angezeigt. Dieser Wert stellt die Übersetzung des Codes dar. 
**09** | Der Benutzer navigiert in den Ordner [1] und editiert dort in {{{xplan_[CODELIST_NAME].xml}}} den übersetzten Wert (gml:description) des in Schritt 8 überprüften Codes in einen beliebigen anderen Wert.
**10** | Der Benutzer löscht den zuvor importierten Plan. | Der Plan wird gelöscht. 
**11** | Der Benutzer importiert den Plan erneut. |Der Plan wird erfolgreich importiert. 
**12** | Der Benutzer bewegt das Kartenbild in QGIS. | Der WMS wird neu geladen. 
**13** | Der Benutzer führt eine Objektinformations-Abfrage in QGIS durch (!GetFeatureInfo Operation). | Es werden die Objektinformationen der editierten Codeliste im entsprechenden Feld angezeigt. Dieser Wert stellt die in Schritt 09 geänderte Übersetzung des Codes dar. 


**Hinweis**

* [1] ~/.deegree/mananger-configuration/synthesizer 
* Gibt es diesen Ordner noch nicht muss dieser noch angelegt werden.

**Beispiel einer xplan41.syn-Datei:**

`
BP_Baugebiet/detaillierteArtDerBaulNutzung=xplanExternalCodeLookup(xpath('xplan:detaillierteArtDerBaulNutzung'), 'xplan_BP_DetailArtDerBaulNutzung.xml')
BP_BaugebietsTeilFlaeche/detaillierteArtDerBaulNutzung=xplanExternalCodeLookup(xpath('xplan:detaillierteArtDerBaulNutzung'), 'xplan_BP_DetailArtDerBaulNutzung.xml')
`

**Beispiel einer xplan_BP_DetailArtDerBaulNutzung.xml-Datei:**
```
<?xml version="1.0" encoding="utf-8"?>
<gml:Dictionary xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" gml:id="xplan_BP_DetailArtDerBaulNutzung" xmlns:gml="http://www.opengis.net/gml">
  <gml:name>xplan:BP_DetailArtDerBaulNutzung</gml:name>
  <gml:dictionaryEntry>
    <gml:Definition gml:id="id_11000">
      <gml:description>Wohngebiet</gml:description>
      <gml:name codeSpace="www.mysynergis.com/XPlanungR/4.1/0">11000</gml:name>
    </gml:Definition>
  </gml:dictionaryEntry>
  <gml:dictionaryEntry>
    <gml:Definition gml:id="id_13000">
      <gml:description>BesondersGeschütztesWohngebiet</gml:description>
      <gml:name codeSpace="www.mysynergis.com/XPlanungR/4.1/0">13000</gml:name>
    </gml:Definition>
  </gml:dictionaryEntry>
  <gml:dictionaryEntry>
    <gml:Definition gml:id="id_17000">
      <gml:description>Geschäftsgebiet</gml:description>
      <gml:name codeSpace="www.mysynergis.com/XPlanungR/4.1/0">17000</gml:name>
    </gml:Definition>
  </gml:dictionaryEntry>
  <gml:dictionaryEntry>
    <gml:Definition gml:id="id_17100">
      <gml:description>Ladengebiet</gml:description>
      <gml:name codeSpace="www.mysynergis.com/XPlanungR/4.1/0">17100</gml:name>
    </gml:Definition>
  </gml:dictionaryEntry>
</gml:Dictionary>
```



# XPlanValidatorCLI

### Prüffall-01: Validierungsart

#### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Valide und invalide XPlanArchive sind verfügbar.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanValidators mit Hilfe des Befehls [1] | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-validator-cli-$VERSION/bin`.
**02** | Der Benutzer führt den Befehl [2] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument valide ist. 
**03** | Der Benutzer führt den Befehl [2] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument invalide ist. 
**04** | Der Benutzer führt den Befehl [3] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument valide ist. 
**05** | Der Benutzer führt den Befehl [3] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument invalide ist. 
**06** | Der Benutzer führt den Befehl [4] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument valide ist. 
**07** | Der Benutzer führt den Befehl [4] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument invalide ist. 
**08** | Der Benutzer führt den Befehl [5] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument valide ist. 
**09** | Der Benutzer führt den Befehl [5] aus. | Der Benutzer erhält eine Validationsausgabe, dass das XPlan-Dokument invalide ist. 

**Hinweis**

* [1] ` cd ~/xplan-validator-cli-$VERSION/bin `
  * Der Pfad kann variieren.
* [2] Ohne Angabe einer Validierungsart: ` ./XPlanValidator -validate Plan.zip [-name Bezeichnung] `
  * `Plan.zip` muss ggf. ersetzt werden.
* [3] Syntaktische Überprüfung: `./XPlanValidator -validate Plan.zip [-name Bezeichnung] -vtype syntax`
  * `Plan.zip` muss ggf. ersetzt werden.
* [4] Geometrische Überprüfung: `./XPlanValidator -validate Plan.zip [-name Bezeichnung] -vtype geometric`
  * ` Plan.zip` muss ggf. ersetzt werden.
 * [5] Semantische Überprüfung: `./XPlanValidator -validate Plan.zip [-name Bezeichnung] -vtype semantic`
   * ` Plan.zip` muss ggf. ersetzt werden.

---
 
### Prüffall-02: Validierungsoptionen

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * XPlanArchive sind verfügbar.
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt den Befehl [1] aus. | Der Benutzer erhält eine Validationsausgabe. Die geometrische Überprüfung der Flächenschlussbedingung wird übersprungen.
**02** | Der Benutzer führt den Befehl [2] aus. | Der Benutzer erhält eine Validationsausgabe. Die geometrische Überprüfung des Geltungsbereich wird übersprungen.


**Hinweis**

* [1] `./XPlanValidator -validate Plan.zip [-name Name] -vo skip-flaechenschluss`
  * `Plan.zip` muss ggf. ersetzt werden.
* [2] `./XPlanValidator -validate Plan.zip [-name Name] -vo skip-geltungsbereich`
  * `Plan.zip` muss ggf. ersetzt werden.
  
---

### Prüffall-03: Speichern der Validierungsergebnisse

#### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet. 
 * Der Prüffall-01 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanValidatorCLI mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-validator-cli-$VERSION/etc/`. 
**02** | Der Benutzer überprüft, ob in der Datei [2] das Verzeichnis [3] für die erstellten Validierungsergebnisse angegeben ist. | Ein Verzeichnis ist nicht gesetzt, daher befinden sich die Validierungsergebnisse unter [4].
**04** | Der Benutzer wechselt in das Default-Verzeichnis mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-validator-cli-$VERSION/tmp/`.
**05** | Der Benutzer überprüft, ob das Validierungsergebnis als Archiv (HTML, XML und PDF) dort abgelegt worden ist. | Das Validierungsergebnis wurde als Archiv angelegt.	 

**Hinweis**

 * [1] `cd ~/xplan-validator-cli-$VERSION/etc/`
   * Der Pfad kann variieren.
 * [2] `validatorConfiguration.properties`
 * [3] `validationReportDirectory=<directory>`
 * [4] `cd ~/xplan-validator-cli-$VERSION/tmp/` 
   * Der Pfad kann variieren.


# XPlanValidatorWeb
  
### Prüffall-01: Webschnittstelle XPlanValidator

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet. 
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft, ob die Web-basierte Benutzeroberfläche des XPlanValidators geöffnet ist. | Die Web-basierte Benutzeroberfläche des XPlanValidators ist geöffnet. 

---

### Prüffall-02: Planarchiv auswählen


#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators. | Die Eingabesicht hat eine Möglichkeit, ein Planarchiv auszuwählen.  
**02** | Der Benutzer klickt auf **Datei auswählen**. | Ein neues Fenster öffnet sich. 
**03** | Der Benutzer wählt ein Planarchiv (`*.zip`) aus und klickt auf **OK**. | Das Fenster schließt sich. Der Planname wird in der Web-basierten Benutzeroberfläche des XPlanValidators angezeigt. |

---


### Prüffall-03: Eingabe einer Bezeichnung für den Validierungsdurchlauf 

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 
#### Prüffall 1

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoptionen einstellen**. | Es öffnet sich ein Fenster mit _Plan hochladen_ und dem _Namen_ des ausgewählten Planarchivs.
**02** | Der Benutzer klickt auf **Abbrechen**. | Das Fenster schließt sich, das Planarchiv ist aber immer noch ausgewählt.

#### Prüffall 2

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoptionen einstellen**. | Es öffnet sich ein Fenster mit _Plan hochladen_ und dem _Namen_ des ausgewählten Planarchivs.
**02** | Der Benutzer klickt auf **Zur Validierung**. | Es öffnet sich ein neues Fenster mit den Validierungsoptionen.
**03** | Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators. | Die Eingabesicht hat ein Eingabefeld **Bezeichnung für den Report** 
**04** | Der Benutzer gibt eine _Bezeichnung_ in das Eingabefeld ein. | Das Eingabefeld enthält die Bezeichnung. 


---

### Prüffall-04: Auswahl der Validierungsart

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators. | Die Eingabesicht hat eine Auswahl **Validierungstyp**. 
**02** | Der Benutzer wählt durch das anklicken eines Kästchens einen _Validierungstyp_ aus. | Der ausgewählte Validierungstyp wird anhand eines _Häckchens_ im Kästchen angezeigt.


---

### Prüffall-05: Auswahl von Validierungsoptionen

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Web-Schnittstelle (Eingabesicht) des XPlanValidators. | Die Eingabesicht hat eine Möglichkeit, weitere Validierungsoptionen auszuwählen. 
**02** | Der Benutzer klickt auf **weitere Validierungsoption**. | Ein neues Fenster öffnet sich. 
**03** | Der Benutzer wählt einige _Validierungsoptionen_ aus. | Die Validierungsoptionen werden markiert. 
**04** | Der Benutzer klickt auf **Speichern**. | Das Fenster schließt sich. 

---

### Prüffall-06: Validierung starten und abbrechen


#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Die Prüffälle (Prüffall-02, Prüffall-03, Prüffall-04, Prüffall-05) wurden erfolgreich ausgeführt.

### Prüffall 1

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt auf den Button **Validierung abbrechen**. | Die Validierungsoptionen werden geschlossen, das Planarchiv gelöscht.

#### Prüffall 2

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt auf den Button **Validierung starten**. | Ein neues Fenster mit dem Validierungsergebnis öffnet sich. 


---


### Prüffall-07: Dynamische Titelzeile

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Der Prüffall-06 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Titelzeile. | Die Titelzeile enthält die Bezeichnung des Validierungsdurchlaufs. 

---


### Prüffall-08: Download der Validierungsergebnisse

#### Vorbedingungen 
 * Der Benutzer hat eine Validierung über die Web-basierte Benutzeroberfläche des XPlanValidators durchgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt in der Rubrik **Downloads** den _HTML Report_ aus. | Der HTML Report ist ausgewählt. 
**02** | Der Benutzer klickt auf **Download** | Der Report wird als `Zip`-Datei zum Herunterladen angeboten. 
**03** | Der Benutzer wählt in der Rubrik **Downloads** den _PDF Report_ aus. | Der PDF Report ist ausgewählt. 
**04** | Der Benutzer klickt auf **Download** | Der Report wird als `Zip`-Datei zum Herunterladen angeboten. 
**05** | Der Benutzer wählt in der Rubrik **Downloads** den _XML Report_ aus. | Der XML Report ist ausgewählt. 
**06** | Der Benutzer klickt auf **Download** | Der Report wird als `Zip`-Datei zum Herunterladen angeboten. 
**07** | Der Benutzer wählt in der Rubrik **Downloads** den Geometriefehler _Shapefile_ aus. | Der Geometriefehler Shapefile ist ausgewählt. 
**08** | Der Benutzer klickt auf **Download** | Das Shapefile wird als `Zip`-Datei zum Herunterladen angeboten. 
**09** | Der Benutzer lädt das exportiere Shapefile in ein beliebiges GIS Tool. | Das Shapefile wird im GIS angezeigt. 
**10** | Der Benutzer wählt in der Rubrik **Downloads** den Geometriefehler _Grafik_ aus. | Der Geometriefehler Grafik ist ausgewählt. 
**11** | Der Benutzer klickt auf **Download** | Die Grafik wird als `Zip`-Datei zum Herunterladen angeboten. 
**12** | Der Benutzer öffnet die exportiere Grafik | Die Grafik wird angezeigt. 
**13** | Der Benutzer wählt in der Rubrik **Downloads** den _HTML Report_, _PDF Report_ und _XML Report_ aus. | Der HTML Report, PDF Report und XML Report ist ausgewählt. 
**14** | Der Benutzer klickt auf **Download** | Die Reporte werden als `Zip`-Datei zum Herunterladen angeboten. 
**15** | Der Benutzer wählt in der Rubrik **Downloads** den Geometriefehler _Shapefile_ und _Grafik_ aus. | Die Geometriefehler Shapefile und Grafik sind ausgewählt. 
**16** | Der Benutzer klickt auf **Download**. | Die Reporte werden als `Zip`-Datei zum Herunterladen angeboten. 


---


### Prüffall-09: Schaltfläche um einen weiteren Plan zu validieren

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Der Prüffall-06 wurde erfolgreich ausgeführt.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Benutzeroberfläche. | Die Benutzeroberfläche enthält einen Button **weiteren Plan validieren** 
**02** | Der Benutzer klickt auf den Button **weitere Plan validieren**. | Der Benutzer wird auf die Eingabesicht weitergeleitet. 


---



### Prüffall-10: Validierungsregeln für XPlanGML 4.0 BPlan


#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.0, BPlan) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen BPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.


---


### Prüffall-11: Validierungsregeln für XPlanGML 4.0 FPlan



#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.0, FPlan) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen FPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.


---


### Prüffall-12: Validierungsregeln für XPlanGML 4.0 Basisschema 

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.0, Basisschema XP_) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen XPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
 
### Prüffall-13: Validierungsregeln für XPlanGML 4.0 SO

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.0, Sonstige Planwerk SO_) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen XPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
 
### Prüffall-14: Validierungsregeln für XPlanGML 4.1 BPlan

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.1, BPlan) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen BPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
 
### Prüffall-15: Validierungsregeln für XPlanGML 4.1 FPlan

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.1, FPlan) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen FPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
 
### Prüffall-16: Validierungsregeln für XPlanGML 4.1 Basisschema

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.1, Basisschema XP) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen XPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
 
### Prüffall-17: Validierungsregeln für XPlanGML 4.1 SO

#### Vorbedingungen 
 * Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar und geöffnet.
 * Es wurde erfolgreich ein XPlan-Dokument (Planart: XPlanGML 4.1, Sonstige Planwerk SO) importiert.
 
#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wählt einen XPlan über **Hinzufügen** aus. | Der Dateiname wird dargestellt. 
**02** | Der Benutzer klickt auf den Button **Hochladen und Validierungsoption einstellen** aus. | Ein neues Fenster wird geöffnet. 
**03** | Der Benutzer klickt auf den Button **Zur Validierung**. | Der Benutzer wird weiter geleitet. 
**04** | Der Benutzer wählt die Einstellungen wie in Prüffall-03, Prüffall-04 beschrieben und klickt auf den Button **Validierung starten**. | Das Validierungsergebnis wird dargestellt. 

**Hinweis**

 * Der Prüffall muss insgesamt drei mal mit je einem anderen Plan ausgeführt werden, sodass alle möglichen Ergebnisse eintreten können.
 
 ---
 
### Prüffall-18: Optimierte Validatorreports

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanValidators ist verfügbar.
* Ein Planarchiv mit Syntaxfehlern, geometrischen und semantischen Fehlern in xplan.gml ist verfügbar.

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01**| Der Benutzer fügt ein neues Planarchiv hinzu.| Es öffnet sich eine neue Benutzeroberfläche.
**02**| Der Benutzer wählt den Validierungstyp „Semantische Validierung“ und bestätigt die Angabe durch die Schaltfläche „Validierung starten“.| Es öffnet sich ein neues Fenster mit dem Ergebnis der semantischen-, geometrischen- und syntaktischen Validierung. 	
**03**| Der Benutzer überprüft die Ausgabe der Syntaxfehler.| Syntaxfehler enthalten Zeilenangaben und den Hinweis, dass das Instanzobjekt nicht zum XPlanGML Schema passt und überprüft werden sollte.
**04**| Der Benutzer überprüft die Ausgabe der geometrischen Fehler.| Geometriewarnungen werden im Validatorreport nicht ausgegeben.
**05**| Der Benutzer überprüft die Ausgabe der semantischen Fehler.| Semantische Fehler enthalten aussagekräftige Texte.

**Hinweis** 

Die Ausgabe der Validierungsergebnisse erfolgt bei den folgenden Komponenten entsprechend:
* XPlanValidatorCLI
* XPlanManagerWeb
* XPlanManagerCLI.

# XPlanValidateDB-CLI  

### Prüffall-01: Hilfe aufrufen

### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanValidateDB-CLI mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-validatedb-cli-$VERSION/bin`.
**02** | Der Benutzer führt mit dem Befehl in [2] die Hilfe aus. | Die Ausgabe gibt Auskunft über alle möglichen Eingabeparameter des XPlanValidateDB-CLI.

**Hinweis**

 * [1] ` cd ~/xplan-validatedb-cli-$VERSION/bin` 
   * Der Pfad kann variieren.
 * [2] `./XPlanValidateDB-CLI -h [oder -help und --help]`

---

### Prüffall-02: Eingabeparameter

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Prüffall-01 wurde erfolgreich ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt den Befehl [1] aus. | Alle in der Datenbasis enthaltenen Pläne werden validiert, anschließend wird das Ergebnis der Validierung in einer CSV-Datei zusammengefasst. Die erstellte Ergebnisdatei liegt unter /tmp.

**Hinweis**

 * [1] `./XPlanValidateDB -jdbcurl= <jdbc:postgresql://hostadresse:port/xplanbox> -user= <dbuser>  -password= <dbpassword> -rulesDirectory= <../xplan-validatedb-cli-$VERSION/etc/rules>`

# XPlanTransformCLI  

### Prüffall-01: Hilfe aufrufen

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Die Installation von HALE wurde erfolgreich abgeschlossen.
 * Die im Verzeichnis `~/xplan-transform-cli-$VERSION/scripts` (Pfad kann variieren) liegenden SQL-Skripte wurden erfolgreich und in richtiger Reihenfolge ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanTransformCLI mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-transform-cli-$VERSION/bin`.
**02** | Der Benutzer führt mit dem Befehl in [2] die Hilfe aus. | Die Ausgabe gibt Auskunft über alle möglichen Eingabeparameter des XPlanTransformCLI.

**Hinweis**

 * [1] ` cd ~/xplan-transform-cli-$VERSION/bin` 
   * Der Pfad kann variieren.
 * [2] `./XPlanTransformCLI -? [oder --help]`

---

### Prüffall-02: Eingabeparameter

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Prüffall-01 wurde erfolgreich ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt den Befehl [1] aus. | Alle in der Datenbasis enthaltenen Pläne werden transformiert und daraufhin validiert, anschließend wird das Ergebnis in einer CSV-Datei zusammengefasst. Es erfolgt keine Übertragung der transformierten Pläne in die Datenbasis.
**02** | Der Benutzer führt den Befehl [2] aus.| Alle in der Datenbasis enthaltenen Pläne werden transformiert und in die Datenbasis übertragen.
**03** | Der Benutzer führt den Befehl [3] aus. | Alle in der Tabelle "xplanmgr.transformToolPlanTableLog" enthaltenen Pläne werden transformiert, die validen Pläne werden draufhin in die Datenbasis übertragen. 
**04** | Der Benutzer führt den Befehl [4] aus. | Ergebnis aus Schritt 01; der Output wird in das aufgeführte Verzeichnis ausgegeben. 
**05** | Der Benutzer führt den Befehl [5] aus. | Ergebnis aus Schritt 01; das Logging für mögliche Fehler wird ausführlicher angezeigt.
**06** | Der Benutzer führt den Befehl [6] aus. | Ergebnis aus Schritt 01; der aufgeführte Workspace wird verwendet.

**Hinweis**

 * [1] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -t [oder --type] VALIDATE`

 * [2] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -t [oder --type] ALL`

 * [3] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -t [oder --type] SYNC`

 * [4] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -f [oder --output] <PFAD/ZU/OUTPUTVERZEICHNIS>`

 * [5] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -v [oder --verbose]`

 * [6] `./XPlanTransformCLI -c [oder --outputDirectory] <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION> -w [oder --workspaceName] <PFAD/ZU/VERZEICHNIS/DES/WORKSPACE>`

# XPlanAuswerteschemaCLI

### Prüffall-01: Hilfe aufrufen

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Die im Verzeichnis `~/xplan-evaluation-schema-synchronize-cli-$VERSION/scripts` (Pfad kann variieren) liegenden SQL-Skripte wurden erfolgreich und in richtiger Reihenfolge ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer wechselt in das Verzeichnis des XPlanAuswerteschemaCLI mit Hilfe des Befehls [1]. | Der Benutzer befindet sich in dem Verzeichnis `~/xplan-evaluation-schema-synchronize-cli-$VERSION/bin`.
**02** | Der Benutzer führt mit dem Befehl in [2] die Hilfe aus. | Die Ausgabe gibt Auskunft über alle möglichen Eingabeparameter des XPlanAuswerteschemaCLI.

**Hinweis**

 * [1] ` cd ~/xplan-evaluation-schema-synchronize-cli-$VERSION/bin` 
   * Der Pfad kann variieren.
 * [2] `./EvaluationSchemaSynchronizer -? [oder --help]`

---

### Prüffall-02: Eingabeparameter

### Vorbedingungen 
 * Der Benutzer ist auf dem System angemeldet.
 * Prüffall-01 wurde erfolgreich ausgeführt.
 
### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt den Befehl [1] aus. | Alle in der Datenbasis enthaltenen Pläne werden aus dem XPlanSyn-Schema in das Auswerteschema der xPlanBox überführt.
**02** | Der Benutzer importiert einen neuen Plan in die xPlanBox und führt anschließend den Befehl [2] aus. | Alle in der Datenbasis enthaltenen Pläne, die seit der letzten Ausführung des XPlanAuswerteschemaCLI verändert oder hinzugefügt wurden, werden mit den Auswerteschmema synchronisiert und überführt.


**Hinweis**

 * [1] `./EvaluationSchemaSynchronizer -d [oder --database] <arg> -h [oder --host] <arg> -p [oder --port] <arg> -t [oder --type] ALL -u [oder --user] <arg> -w [oder --password] <arg>`
   * Der Befehl in [1] darf nur einmal, initial, ausgeführt werden!
 * [2] `../EvaluationSchemaSynchronizer -d [oder --database] <arg> -h [oder --host] <arg> -p [oder --port] <arg> -t [oder --type] SYNC -u [oder --user] <arg> -w [oder --password] <arg>`

# XPlanWMS

### Prüffall-01: Transparente Zeichenvorschriften im XPlanWMS ermöglichen

#### Vorbedingungen 

* Eine geeignete !GetMap-Anfrage steht zur Verfügung.
* Im WMSpre und im WMSarchive ist als default Style eine transparente Darstellung eingestellt.
* Im WMS-Endpoint ist als default Style die vollflächige Darstellung eingestellt.


#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer führt im Browser die !GetMap-Anfrage aus.|Die Kartengraphik wird angezeigt.|
**02** | Der Benutzer tauscht in der URL den WMS-Endpoint von WMS in WMSpre aus. Der Style ist default.| Die angezeigte Kartengraphik enthält eine transparente Darstellung.|
**03** | Der Benutzer tauscht in der URL den WMS-Endpoint von WMS in WMSpre aus. Der Style ist vollflaechig.| Die angezeigte Kartengraphik enthält eine vollflächige Darstellung.|
**04** | Der Benutzer tauscht in der URL den WMS-Endpoint von WMSpre in WMS aus. Der Style ist default.| Die angezeigte Kartengraphik enthält eine vollflächige Darstellung.|
**05** | Der Benutzer tauscht in der URL den WMS-Endpoint von WMSpre in WMS aus. Der Style ist transparent.| Die angezeigte Kartengraphik enthält eine transparente Darstellung.|


#### Beispiel Prüffall 2: 


* GetMap-Anfrage mit `Service = WMSpre` und `Style = / Style = default / Style = transparent`
* **Hinweis:** Alle drei Styles entsprechen dem default des wmspre-Endpoint

http://<host>:<Port>/xplan-wms/services/wmspre?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750

bzw.

http://<host>:<Port>/xplan-wms/services/wmspre?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=default&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750

bzw.

http://<host>:<Port>/xplan-wms/services/wmspre?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=transparent&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750

**Antwort**: Eine transparente Kartendartsellung. Es erfolgt nur eine Darstellung der Geltungsbereiche der jeweiligen Planwerke.


#### Beispiel Prüffall 3 


Der Benutzer tauscht lediglich den Style aus.


* GetMap-Anfrage mit `Service = WMSpre` und `Style= vollflächig`


http://<host>:<Port>/xplan-wms/services/wmspre?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=vollflaechig&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750

**Antwort**: Eine vollflächige Kartendartsellung



#### Beispiel Prüffall 4: 


* GetMap-Anfrage mit `Service = WMS` und `Style = / Style = default / Style = vollflaechig`

* **Hinweis:** Alle drei Styles entsprechen dem default des wms-Endpoint

http://<host>:<Port>/xplan-wms/services/wms?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750


bzw.

http://<host>:<Port>/xplan-wms/services/wms?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=default&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750

bzw.

http://<host>:<Port>/xplan-wms/services/wms?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=vollflaechig&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750


**Antwort**: Eine vollflächige Kartendartsellung


#### Beispiel Prüffall 5: 



Der Benutzer tauscht lediglich den Style aus.


* GetMap-Anfrage mit `Service = WMS` und `Style = transparent`

http://<host>:<Port>/xplan-wms/services/wms?FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=BP_Planvektor&STYLES=transparent&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&EXCEPTIONS=application%2Fvnd.ogc.se_inimage&SRS=EPSG%3A25832&BBOX=559063.243051755,5938015.832686279,560684.741896508,5939637.331531033&WIDTH=750&HEIGHT=750


**Antwort:** 

Eine transparente Kartendarstellung. Es erfolgt nur eine Darstellung der Geltungsbereiche der jeweiligen Planwerke.



---


### Prüffall-02: Unterstützung von Planarchivierung

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
* Der Benutzer ist am XPlanManagerWeb angemeldet und hat die Berechtigung zum Import von Planarchiven.

#### Prüffall 1 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01**| Der Benutzer importiert einen Plan. | Es öffnet sich ein neues Fenster. 
**02**| Der Benutzer gibt einen Rechtsstand an.| Der Plan wird in der entsprechenden Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.
**03**| Der Benutzer führt die Schritte 01 und 02 mit unterschiedlichen Rechtsstand- Angaben durch.| Der Plan wird in der entsprechenden Datenhaltung abgelegt und nur in der Kartenansicht des entsprechenden WMS-Dienstes angezeigt.


---

### Prüffall-03: Umringe immer sichtbar


#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar. 
* Der Benutzer ist am XPlanManagerWeb angemeldet und hat die Berechtigung zum Import von Planarchiven.

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert einen Plan mit einem unbegrenzten Gültigkeitszeitraum.| Der Plan wird importiert. Der Gültigkeitszeitraum ist grün.
**02** | Der Benutzer importiert einen Plan mit einem bereits abgelaufenen Gültigkeitszeitraum.|Der Plan wird importiert. Der Gültigkeitszeitraum ist rot.
**03** | Der Benutzer führt eine GetMap-Anfrage mit den Layer-Angaben bp_plan und bp_XYZ für das Gebiet des importierten Plans aus Schritt 01 aus.| Die Umringe des Plans sowie die Daten von bp_XYZ werden angezeigt.
**04** | Der Benutzer führt eine GetMap-Anfrage mit den Layer-Angaben bp_plan und bp_XYZ für das Gebiet des importierten Plans aus Schritt 02 aus.| Es werden nur die Umringe des Plans angezeigt.

**Hinweis:**

* Zum Layer bp_XYZ müssen in der xplan.gml-Datei Daten enthalten sein.


---
### Prüffall-04: GetMap URL für spezifischen Plan über GetFeature zusammenstellen


#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar und geöffnet. 

#### Prüffall 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer klickt hinter einem beliebigen Plan auf die Schaltfläche „Kartenvorschau“.|Es öffnet sich ein neues Fenster.
**02** | Der Benutzer klickt im neu geöffneten Fenster auf „Plan in neuem Fenster öffnen“ (GetMap-Anfrage).|Es öffnet sich ein neues Browserfenster mit der entsprechenden Karte.
**03** | Der Benutzer überprüft die Form der URL.|Die URL ist OGC-konform. (http...LAYERS=...)
**04** | Der Benutzer überprüft die Anzahl der Layer in der URL.|Die Anzahl stimmt mit den im jeweiligen Plan enthaltenen Layern überein.


---

### Prüffall-05: Sortierung der Visualisierung nach anderem Datumsfeld

#### Vorbedingungen 

* Die Web-basierte Benutzeroberfläche des XPlanManagers ist verfügbar.
* Als Kriterium für die Sortierung in der Kartenansicht ist das Rechtsverordnungsdatum eingestellt.
* Der Benutzer ist am XPlanManagerWeb angemeldet und hat die Berechtigung zum Editieren von Planstammdaten. 
* Es ist bereits mindestens ein Plan importiert.

#### Prüffall 1 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert einen bereits vorhandene Plan erneut.| Der Plan wird importiert.
**02** | Der Benutzer drückt auf die Schaltfläche „editieren“ des neu importierten Plans.| Es öffnet sich ein neues Fenster mit einem Formular.
**03** | Der Benutzer verändert das „Rechtsverordungsdatum“ in ein zurückliegendes Datum und verändert ein Attribut. Die Änderung ist valide.| Die geänderten Daten sind gespeichert.
**04** | Der Benutzer überprüft die Änderung mit Hilfe einer GetMap-Anfrage.|Der geänderte Plan wird auf der Karte im Hintergrund angezeigt. 


#### Prüffall 2 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer importiert einen bereits vorhandene Plan erneut.| Der Plan wird importiert.
**02** | Der Benutzer drückt auf die Schaltfläche „editieren“ des neu importierten Plans.| Es öffnet sich ein neues Fenster mit einem Formular.
**03** | Der Benutzer verändert das „Rechtsverordungsdatum“ in ein zukünftiges Datum und verändert ein Attribut. Die Änderung erfolgt valide.| Speicherung der Änderung.
**04** | Der Benutzer überprüft die Änderung mit Hilfe eines GetMap-Anfrage.| Der geänderte Plan wird auf der Karte im Vordergrund angezeigt. 


---

### Prüffall-06: Betrieb der Geo-Dienste mit Load Balancer

#### Vorbedingungen 
* Eine Instanz der Komponente XPlanManagerWeb und zwei Instanzen der Komponente XPlanWMS stehen zur Verfügung.
* Die beiden WMS-Instanzen nutzen ein gemeinsames Workspace-Verzeichnis und ein gemeinsames Verzeichnis für die Speicherung der Rasterdaten in einem Netzwerk-Dateisystem.
* Der Benutzer ist am XPlanManagerWeb angemeldet und hat die Berechtigung zum Import von Planarchiven. 
* Der Systemadministrator hat Zugriff auf die Protokolldateien der o.g. Komponenten.

#### Prüffall 1 

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Systemadministrator beobachtet das Verhalten des Systems, während ein Benutzer einen Plan mit Rasterdaten importiert.| In beiden WMS-Instanzen wird ein Workspace-Reload ausgeführt.  
**02** | Der Benutzer überprüft die Kartenansichten beider WMS-Dienste im Bereich des importierten Plans mit einer geeigneten GetMap-Anfrage.| Beide WMS-Dienste geben die gleiche Kartenansicht aus. 


---


### Prüffall-07: GetFeatureInfo-Ausgaben des WMS


#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer öffnet die html.gfi - Datei [1]. | Die Datei wird geöffnet. 
**02** | Der Benutzer ändert die Ausgabereihenfolge der Objektattribute in der GFI-Vorlage [2]. 
**03** | Der Benutzer fügt eine neue Zeile hinzu [3] und speichert die Änderungen.
**04** | Der Benutzer ruft [4] auf und kontrolliert die Ausgabe auf Lesbarkeit, richtige Wiedergabe der gewünschten Infos und Vollständigkeit. | Die Ausgabe ist lesbar und es werden die gewünschten Infos wiedergegeben.



**Hinweis:**

* [1] html.gfi ist unter » /.deegree/xplansyn-wms-workspace/services zufinden
* [2] Auskommentieren von » <?property not(gmlId,raster*,ref*,fachobjekt,flaechenteil,*Code,gehoert*,begrenzungslinie,uuid,bereich):property>
* [3] Hinzufügen von » <?property *:property>
* [4] Eidelstedt_4: http://lgvxplanisk:8080/xplan-wms/services/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&QUery_Layers=BP_Planvektor&LAYERS=BP_Planvektor&STYLES=&FORMAT=image/png&TRANSPARENT=true&EXCEPTIONS=text/xml&SRS=epsg:25832&BBOX=559333.4928592138,5938286.082493738,560414.4920890492,5939367.081723574&WIDTH=750&HEIGHT=750&X=559&Y=593&INFO_FORMAT=text/html


---


# XPlanWFS/XPlanSynWFS


### Prüffall-01: Unterstützung der Spezifikation WFS 1.1.0 zur Abgabe von GML 3.1.1 und GML 3.2.1


#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer ruft [1] (XPlanGML 2) auf. | Es öffnet sich ein neues Fenster, mit dem der Request gespeichert bzw. geöffnet werden kann.
**02** | Der Benutzer startet ein Validations-tool.
**03** | Der Benutzer ersetzt die SchemaLocation.
**04** | Der Benutzer importiert die Response aus Schritt 01.
**05** | Der Benutzer führt eine Schemavalidation durch. | Der GetFeature Request ist GML 3.1.1 konform.
**06** | Der Benutzer ruft [2] (XPlanGML 3) auf. | Es öffnet sich ein neues Fenster, mit dem der Request gespeichert bzw. geöffnet werden kann.
**07** | Der Benutzer startet ein Validations-tool.
**08** | Der Benutzer ersetzt die SchemaLocation.
**09** | Der Benutzer importiert die Response aus Schritt 06.
**10** | Der Benutzer führt eine Schemavalidation durch. | Der GetFeature Request ist GML 3.1.1 konform.


**Hinweis:**

* [1] http://<host:port>/xplan-wfs/services/wfs110?SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&OUTPUTFORMAT=text%2Fxml%3B+subtype%3Dgml%2F3.1.1&TYPENAME=xplan2:BP_Plan&NAMESPACE=xmlns%28xplan=http://www.xplanung.de/xplangml%29

* [2] http://<host:port>/xplan-wfs/services/wfs110?SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&OUTPUTFORMAT=text%2Fxml%3B+subtype%3Dgml%2F3.1.1&TYPENAME=xplan3:BP_Plan&NAMESPACE=xmlns%28xplan3=http:%2F%2Fwww.xplanung.de%2Fxplangml%2F3%2F0%29



---

### Prüffall-02: Unterstützung der Spezifikation WFS 2.0.0 zur Abgabe von GML 3.2.1


#### Prüffall

Schritt | Beschreibung | Erwartetes Ergebnis
----------- |------------------|-------------------------
**01** | Der Benutzer ruft [1] auf. | Es öffnet sich ein neues Fenster, mit dem der Request gespeichert bzw. geöffnet werden kann.
**02** | Der Benutzer startet ein Validations-tool.
**03** | Der Benutzer ersetzt die SchemaLocation.
**04** | Der Benutzer importiert die Response aus Schritt 01.
**05** | Der Benutzer führt eine Schemavalidation durch. | Der GetFeature Request ist GML konform.
**06** | Der Benutzer ruft [2] auf. | Es öffnet sich ein neues Fenster, mit dem der Request gespeichert bzw. geöffnet werden kann.
**07** | Der Benutzer startet ein Validations-tool.
**08** | Der Benutzer ersetzt die SchemaLocation. 
**09** | Der Benutzer importiert die Response aus Schritt 05.
**10** | Der Benutzer führt eine Schemavalidation durch. | Der GetFeature Request ist GML konform.

**Hinweis:**

* [1] http://<host:port>/xplan-wfs/services/dls?SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&OUTPUTFORMAT=text%2Fxml%3B+subtype%3Dgml%2F3.2.1&TYPENAME=xplan3:BP_Plan&NAMESPACE=xmlns%28xplan=http://www.xplanung.de/xplangml/4/1%29

* [2] http://<host:port>/xplan-wfs/services/dls?SERVICE=WFS&VERSION=2.0.0&REQUEST=GetFeature&OUTPUTFORMAT=text%2Fxml%3B+subtype%3Dgml%2F3.2.1&TYPENAME=xplan4:BP_Plan&NAMESPACE=xmlns%28xplan=http://www.xplanung.de/xplangml/4/1%29


# XPlanRessourcen (Landingpage)

### Prüffall-01: Links aufrufen

#### Prüffall 
Schritt | Beschreibung | Erwartetes Ergebnis 
----------- |------------------|-------------------------
**01** | Der Benutzer überprüft die Ordnung sowie Rechtsschreibung der Linkvorschau auf der gesamten Landingpage | Alle Links sind richtig geordnet und weisen keine Rechtsschreibfehler auf. 
**02** | Der Benutzer klickt auf jeden Link der Oberpunkte "XPlanManager und XPlanValidator", "XPlanDienste", "XPlanInspirePluDienste", "XPlanDokumentation" sowie "Weiterführende Informationen" und überprüft die Funktionalität | Alle Links funktionieren. 
**03** | Der Benutzer klickt unter Oberpunkt "Demodatensätze" auf jeden Demodatensatz und lädt diese herunter | Alle Testdatensätze werden erfolgreich heruntergeladen.