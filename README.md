# xPlanBox

In diesem Repository ist der Quellcode für die Open Source-Version der Anwendung "xPlanBox" der Firma [lat/lon](https://www.lat-lon.de) enthalten. Die Anwendung dient der Abbildung der Bauleit- und der Landschaftsplanung sowie der Raumordnung auf Basis des Standards [XPlanung](https://xleitstelle.de/xplanung/ueber_xplanung). Die Anwendung basiert zudem auf den Standards des [Open Geospatial Consortium](https://www.ogc.org/), den technischen Empfehlungen der [INSPIRE Richtlinie](https://inspire.ec.europa.eu/) und insbesondere der Abbildung des deutschen Planungsrechts durch das [GML-Anwendungsschema XPlanGML](https://xleitstelle.de/xplanung/releases-xplanung). Die Komponenten der Anwendung wurden u.a. unter der Verwendung der Open Source-Software [deegree](https://www.deegree.org) implementiert.

Die Anwendung ist Bestandteil der [DiPlanung](https://diplanung.de)-Plattform zur Umsetzung der [OZG](https://www.onlinezugangsgesetz.de)-Leistungen "Beteiligungsverfahren nach dem Baugesetzbuch, dem Raumordnungsgesetz und in der Planfeststellung" und "Einstellen von raumbezogenen Planwerken in das Internet" im Themenfeld "Bauen und Wohnen". Sie wird in diesem Zusammenhang per Software-as-a-Service (SaaS) als sog. [EfA-Lösung](https://www.digitale-verwaltung.de/Webs/DV/DE/onlinezugangsgesetz/efa/efa-node.html) zur Nachnutzung bereitgestellt. Anfragen zur Nachnutzung können an ozg-buw@bsw.hamburg.de gerichtet werden.

## xPlanBox im OZG-Kontext

Aus nicht-funktionaler Sicht liegt der Fokus der Weiterentwicklung als Bestandteil einer EfA-Lösung auf der Optimierung für eine containerisierte, Cloud-basierte Umgebung. Dazu gehört u.a., dass die Auslieferung von Rasterbildern zu Plänen über die Integration von Mapserver erfolgt, und dass Rasterbilder und Begleitdokumente, die Bestandteil eines XPlanArchivs sind, in einem Objektspeicher (aktuell unterstützt: S3-kompatibel) abgelegt werden.

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

Für die Erstellung der ausführbaren Binärdateien ist ein [JDK 11](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot) und das Werkzeug [Apache Maven 3.9](https://maven.apache.org/) erforderlich.

#### Installation und Konfiguration von Maven. 

Folgen Sie der [Installationsanleitung von Maven](https://maven.apache.org/install.html).

Weitere Informationen zur Konfiguration von Maven finden Sie in der [Referenzdokumentation zu den Maven Settings](https://maven.apache.org/settings.html).

#### deegree erstellen

Im ersten Schritt muss der Quellcode von [deegree mit Java 11](https://github.com/lat-lon/deegree3/tree/xplanbox-deegree3.5) kompiliert werden. Dazu muss die passende Version von deegree ausgewählt werden. Für Version 7.0+ der Anwendung muss deegree **Version 3.5.0.2** verwendet werden:

```shell
git clone https://github.com/lat-lon/deegree3.git
cd deegree3
git checkout tags/xplanbox-deegree-3.5.0.2
mvn clean install
```
> **_Hinweis_**: Aktuell kann noch nicht eine offizielle Release-Version von deegree verwendet werden, da für das Fachdatenschema XPlanung noch spezifische Erweiterungen an deegree vorgenommen wurden. Sobald eine Version von deegree mit Unterstützung dieser Erweiterungen verfügbar ist, entfällt dieser Schritt.

> **_Hinweis_**: Unter dem Betriebssystem Windows kann es bei der Ausführung von Unit-Test zu Fehlern kommen. Es kann dann erforderlich sein, die Tests zu überspringen. Dazu ist die Option `-DskipTests` beim Aufruf von Maven zu ergänzen.

#### XPlanung-Validierungsregeln einbinden

Die Anwendung nutzt die öffentlich verfügbaren XPlanung-Validierungsregeln des XPlanung-Standards aus dem [OpenCoDE-Repository der XLeitstelle](https://gitlab.opencode.de/xleitstelle/xplanung/validierungsregeln/standard).

> **_Hinweis_**: Um eine andere Version der XPlanung-Validierungsregeln zu installieren, folgen Sie der Anleitung im [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc).

#### Anwendung erstellen

Die Erstellung der Binärdateien der Anwendung erfolgt dann im Basisverzeichnis mit folgendem Maven Aufruf:

```shell
git clone git@gitlab.opencode.de:diplanung/ozgxplanung.git
cd ozgxplanung
mvn clean install
```

### Installation und Konfiguration

Die Installation und Konfiguration der Anwendung ist im [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc) dokumentiert.

----
© 2023 lat/lon gesellschaft für raumbezogene informationssysteme mbH  
Im Ellig 1
53343 Wachtberg  
Tel: +49 +228 24333784  
info@lat-lon.de  
https://www.lat-lon.de  
twitter: https://twitter.com/latlon_de  
GitHub: https://github.com/lat-lon
