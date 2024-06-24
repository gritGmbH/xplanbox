# xPlanBox

In diesem Repository ist der Quellcode für die Open Source-Version der Anwendung "xPlanBox" der Firma [lat/lon](https://www.lat-lon.de) enthalten. Die Anwendung dient der Abbildung der Bauleit- und der Landschaftsplanung sowie der Raumordnung auf Basis des Standards [XPlanung](https://xleitstelle.de/xplanung/ueber_xplanung). Die Anwendung basiert zudem auf den Standards des [Open Geospatial Consortium](https://www.ogc.org/), den technischen Empfehlungen der [INSPIRE Richtlinie](https://inspire.ec.europa.eu/) und insbesondere der Abbildung des deutschen Planungsrechts durch das [GML-Anwendungsschema XPlanGML](https://xleitstelle.de/xplanung/releases-xplanung). Die Komponenten der Anwendung wurden u.a. unter der Verwendung der Open Source-Software [deegree](https://www.deegree.org) implementiert.

Die Anwendung ist Bestandteil der [DiPlanung](https://diplanung.de)-Plattform zur Umsetzung der [OZG](https://www.onlinezugangsgesetz.de)-Leistungen "Beteiligungsverfahren nach dem Baugesetzbuch, dem Raumordnungsgesetz und in der Planfeststellung" und "Einstellen von raumbezogenen Planwerken in das Internet" im Themenfeld "Bauen und Wohnen". Sie wird in diesem Zusammenhang per Software-as-a-Service (SaaS) als sog. [EfA-Lösung](https://www.digitale-verwaltung.de/Webs/DV/DE/onlinezugangsgesetz/efa/efa-node.html) zur Nachnutzung bereitgestellt. Anfragen zur Nachnutzung können an ozg-buw@bsw.hamburg.de gerichtet werden.

## xPlanBox im OZG-Kontext

Aus nicht-funktionaler Sicht liegt der Fokus der Weiterentwicklung als Bestandteil einer EfA-Lösung auf der Optimierung für eine containerisierte, Cloud-basierte Umgebung. Dazu gehört u.a., dass die Auslieferung von Rasterbildern zu Plänen über die Integration von MapServer erfolgt, und dass Rasterbilder und Begleitdokumente, die Bestandteil eines XPlanArchivs sind, in einem Objektspeicher (aktuell unterstützt: S3-kompatibel) abgelegt werden.

> **_Hinweis_**: Probleme und Fehler, die klassische Installationen oder Betriebsarten (z.B. deegree GeoTIFF/GDAL Tile Store für Rasterbilder) betreffen, können aus Kapazitätsgründen nur nachrangig bearbeitet werden. Supportanfragen können generell nicht beantwortet werden. Bitte wenden Sie sich dazu an den Hersteller.

## Lizenz

Dieses Projekt ist unter der GNU Affero General Public License, Version 3 oder jeder späteren Version veröffentlicht. Weitere Informationen zur Lizenz stehen in [LICENSE.txt](LICENSE.txt).

## Versionierung

Die Versionierung der Software folgt dem Versionierungsschema von [SemVer](http://semver.org/). Eine Übersicht der bisher erstellten Versionen ist unter [Releases](../../releases) zu finden. Die Änderungshistorie ist im [CHANGELOG.md](CHANGELOG.md) dokumentiert.

## Mitarbeit

Regeln für die Mitarbeit finden Sie in [CONTRIBUTING.md](CONTRIBUTING.md).

## Autoren

* **lat/lon GmbH** - **Hersteller der xPlanBox** - [lat/lon](https://github.com/lat-lon)

Personen, die an diesem Projekt mitgearbeitet haben, stehen in [contributors](CONTRIBUTORS.md).

## Sponsoren 

### Initialer Sponsor und Implementierungspartner

<p align="center">
  <a href="https://www.hamburg.de/bsw/" target="_blank">
    <img width="260px" src="sponsor_bsw.png">
  </a>
</p>


### Kooperationspartner

<p align="center">
  <a href="https://geoinfo.hamburg.de/" target="_blank">
    <img width="130px" src="sponsor_lgv.png">
  </a>
</p>

## Dokumentation

### Anwenderdokumentation

Die Dokumentation zur Anwendung liegt im Asciidoc-Format vor und sowohl das Benutzer- als auch Betriebshandbuch mit der Installationsanleitung sind im Quelltext in den Verzeichnissen [Benutzerhandbuch](xplan-documentation/xplan-benutzerhandbuch/src/main/asciidoc) und [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc) abgelegt. Die Erstellung von PDF- und HTML-Ausgabedateien erfolgt mit dem Bauen der Software wie im folgenden Kapitel dokumentiert.

### Entwicklerdokumentation

Für die Erstellung der ausführbaren Binärdateien ist ein [JDK 17](https://adoptium.net/?variant=openjdk17&jvmVariant=hotspot) und das Werkzeug [Apache Maven 3.9](https://maven.apache.org/) erforderlich.

#### Installation und Konfiguration von Maven. 

Folgen Sie der [Installationsanleitung von Maven](https://maven.apache.org/install.html).

Weitere Informationen zur Konfiguration von Maven finden Sie in der [Referenzdokumentation zu den Maven Settings](https://maven.apache.org/settings.html).

#### Anwendung erstellen

Die Erstellung der Binärdateien der Anwendung erfolgt dann im Basisverzeichnis mit folgendem Maven Aufruf:

```shell
git clone git@gitlab.opencode.de:diplanung/ozgxplanung.git
cd ozgxplanung
mvn clean install
```

> **_Hinweis_**: Unter dem Betriebssystem Windows kann es bei der Ausführung von Unit-Test zu Fehlern kommen. Es kann dann erforderlich sein, die Tests zu überspringen. Dazu ist die Option `-DskipTests` beim Aufruf von Maven zu ergänzen.

#### Container Images erstellen

Um Container Images aus dem Source Code auf Open CoDE zu bauen, sind folgende Anpassungen erforderlich. Die gebauten Container Images sind in der Container Registry verfügbar: https://gitlab.opencode.de/diplanung/ozgxplanung/container_registry/.

##### Konfiguration

* Property `docker-image.namePrefix` (Standardwert: `xplanbox`): erlaubt den Namen der generierten Docker Images zu ändern.

##### Images mit Docker Daemon bauen

```
mvn install -P docker
```

Nach dem Build sind die Docker Images vorhanden.

Beispiel:

```
$> docker images
REPOSITORY                          TAG                    IMAGE ID       CREATED        SIZE
xplanbox/xplan-mapserver            7.0-SNAPSHOT           efa010d2acdf   3 days ago     446MB
xplanbox/xplan-mapserver            latest                 efa010d2acdf   3 days ago     446MB
xplanbox/xplan-services             7.0-SNAPSHOT           5c2ec3ab6786   3 days ago     1.15GB
xplanbox/xplan-services             latest                 5c2ec3ab6786   3 days ago     1.15GB
xplanbox/xplan-validator-wms        7.0-SNAPSHOT           d5fc6b671fa9   3 days ago     677MB
xplanbox/xplan-validator-wms        latest                 d5fc6b671fa9   3 days ago     677MB
xplanbox/xplan-inspireplu           7.0-SNAPSHOT           499a240777d3   3 days ago     542MB
...
```

##### Images mit kaniko bauen

Docker in Docker funktioniert auf OpenCoDE nicht, weil dort die Gitlab Runners auf Kubernetes laufen (Stand 31.01.2023). Die Build Pipeline verwendet deswegen stattdessen [kaniko](https://github.com/GoogleContainerTools/kaniko). Der Ablauf ist wie folgt:

* in der Phase `package` werden mit dem Goal [`source` von dem docker-maven-plugin](http://dmp.fabric8.io/#docker:source) die Docker Kontexte in Dateien `docker-build.tar` gespeichert
* nach dem Maven Build werden alle `docker-build.tar` Dateien gzipped und als Artefakte temporär gespeichert
* im nächsten Stage wird kaniko mit jedem gespeicherten Docker Kontext ausgeführt

##### Anforderungen

Diese Vorgehensweise bringt zusätzliche Anforderungen:

* Vorbereitungen für die Docker Images (z.B. Dependencies entpacken) müssen vor der Phase `package` stattfinden, um in der Phase `package` von `docker-maven-plugin:source` verwendet zu werden
* die `docker-build.tar` Dateien sollen so klein wie möglich gehalten werden, sonst wird die Obergrenze für die maximale Größe von Packages überschritten (`413 Request Entity Too Large`). Mit Hilfe einer [`.maven-dockerignore` Datei](http://dmp.fabric8.io/#ex-build-dockerexclude) ist es möglich zu konfigurieren, was nicht in die tar-Datei gelangen soll.
* zur Zeit sind die gezipped `docker-build.tar` Dateien leider zu groß für OpenCoDE, um als Artefakte von einem einzelnen Job gespeichert zu werden. Als Workaround wird das Bauen in mehreren Jobs aufgeteilt, was leider mehr Konfiguration erfordert.

##### Docker Maven Build Image

Um den Build zu beschleunigen wird ein Build Image verwendet, das schon die meisten benötigten Maven Artefakte enthält, die für den Build benötigt werden. Dieses Image sollte regelmäßig neu erzeugt werden, so dass es synchron zu den benötigten Dependencies bleibt. Mit diesem Image ist der Maven Build ein bis zwei Minuten schneller und der OWASP-Dependency-Check mehr als sechs Minuten schneller.

##### Automatische Erzeugung

Das Pipeline Schedule [Create Maven Build Image](https://gitlab.opencode.de/diplanung/ozgxplanung/-/pipeline_schedules) (Login erforderlich) ist konfiguriert, um einmal pro Woche zu laufen.

##### Manuelle Erzeugung

Das Image kann auch manuell gebaut und gepusht werden.

###### Docker Image lokal bauen

```
docker build -t registry.opencode.de/diplanung/ozgxplanung/mvn-build-image:latest -f ci/mvn-build-image.Dockerfile .
```

###### GitLab personal access token erstellen

Auf OpenCoDE unter [User Settings / Access Tokens](https://gitlab.opencode.de/-/profile/personal_access_tokens) ein neues Personal Access Token mit `read_registry` and `write_registry` Scopes erzeugen.

###### Im OpenCoDE Registry einloggen

Mit Benutzernamen und dem _personal access token_ als Passwort einloggen:

```
docker login registry.opencode.de
```

###### Image pushen

```
docker push registry.opencode.de/diplanung/ozgxplanung/mvn-build-image:latest
```

##### Security Check mit Trivy

Issues in den Docker Images mit existierenden Fixes können mit [Trivy](https://trivy.dev/) gesucht werden:

```
mvn -Pdocker exec:exec@trivyScanForFixedIssues
```

Per Default werden nur die Issues mit der Stufe CRITICAL gesucht. Dies kann mit dem Property `trivy.severity` geändert werden, z.B. so:

```
mvn -Pdocker exec:exec@trivyScanForFixedIssues -Dtrivy.severity='CRITICAL,HIGH,MEDIUM'
```

Issues, die ignoriert werden sollen, müssen in `.trivyignore` in den jeweiligen Projekten gepflegt werden.

### Installation und Konfiguration

Die Installation und Konfiguration der Anwendung ist im [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc) dokumentiert.

#### Logging

Die xPlanBox verwendet [Apache Log4j2](https://logging.apache.org/log4j/2.x/) als Logging Framework und unterstützt die Ausgaben von Meldungen im Text- und JSON-Format. Das Ausgabeformat kann über die Umgebungsvariable `LOG4J_LAYOUT` geändert werden: 

* `LOG4J_LAYOUT`:  konfiguriert das Outputformat
    * `plain` (Standardwert): Text-Format
    * `json`: JSON-Format

#### XPlanung-Validierungsregeln einbinden

Die Anwendung nutzt die öffentlich verfügbaren XPlanung-Validierungsregeln des XPlanung-Standards aus dem [OpenCoDE-Repository der XLeitstelle](https://gitlab.opencode.de/xleitstelle/xplanung/validierungsregeln/standard).

> **_Hinweis_**: Um eine andere Version der XPlanung-Validierungsregeln zu installieren, folgen Sie der Anleitung im [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc).

----
© 2024 lat/lon gesellschaft für raumbezogene informationssysteme mbH  
Im Ellig 1
53343 Wachtberg  
Tel: +49 +228 24333784  
info@lat-lon.de  
https://www.lat-lon.de  
twitter: https://twitter.com/latlon_de  
GitHub: https://github.com/lat-lon
