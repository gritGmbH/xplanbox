# xPlanBox

In diesem Repository ist der Quellcode für die Open Source-Version der Anwendung "xPlanBox" der Firma [lat/lon](https://www.lat-lon.de) enthalten. Die Anwendung dient der Abbildung der Bauleit- und der Landschaftsplanung auf Basis des Standards [XPlanung](https://xleitstelle.de/xplanung/ueber_xplanung). Die Anwendung basiert zudem auf den Standards des [Open Geospatial Consortium](https://www.ogc.org/), den technischen Empfehlungen der [INSPIRE Richtlinie](https://inspire.ec.europa.eu/) und insbesondere der Abbildung des deutschen Planungsrechts durch das [GML-Anwendungsschema XPlanGML](https://xleitstelle.de/xplanung/releases-xplanung). Die Komponenten der Anwendung wurden u.a. unter der Verwendung der Open Source-Software [deegree webservices](https://www.deegree.org) implementiert.

## Lizenz

Dieses Projekt ist unter der GNU Affero General Public License, Version 3 oder jeder späteren Version veröffentlicht. Weitere Informationen zur Lizenz stehen in [LICENSE.txt](LICENSE.txt).

## Mitarbeit

Regeln für die Mitarbeit finden Sie in [CONTRIBUTING.md](CONTRIBUTING.md).

## Autoren

* **lat/lon GmbH** - **Hersteller der xPlanBox** - [lat/lon](https://github.com/lat-lon)

Personen, die an diesem Projekt mitgearbeitet haben, stehen in [contributors](CONTRIBUTORS.md).

## Versionierung

Die Versionierung der Software folgt dem Versionierungsschema von [SemVer](http://semver.org/). Eine Übersicht der bisher erstellten Versionen ist unter [Releases](../../releases) zu finden. 

## Dokumentation

### Anwenderdokumentation

Die Dokumentation zur Anwendung liegt im Asciidoc-Format vor und sowohl das Benutzer- als auch Betriebshandbuch mit der Installationsanleitung sind im Quelltext in den Verzeichnissen [Benutzerhandbuch](xplan-documentation/xplan-benutzerhandbuch/src/main/asciidoc) und [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc) abgelegt. Die Erstellung von PDF- und HTML-Ausgabedateien erfolgt mit dem Bauen der Software wie im folgenden Kapitel dokumentiert.

### Entwicklerdokumentation

Für die Erstellung der ausführbaren Binärdateien ist ein [JDK 11](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot) und das Werkzeug [Apache Maven 3.8](https://maven.apache.org/) erforderlich.

Im ersten Schritt muss der Quellcode von [deegree mit Java 11](https://github.com/lat-lon/deegree3/tree/xplanbox) kompiliert werden:

```shell
git clone https://github.com/lat-lon/deegree3.git
cd deegree3
git switch xplanbox
mvn clean install
```
> **_Hinweis_**: Aktuell kann nicht eine offizielle Release Version von deegree verwendet werden, da diese bisher nur unter Java 1.8 ausführbar sind. Der Quellcode für diese Anwendung erfordert aber Java 11.

Die Erstellung der Binärdateien der Anwendung erfolgt dann im Basisverzeichnis mit folgendem Maven Aufruf:

```shell
git clone git@gitlab.opencode.de:diplanung/ozgxplanung.git
cd ozgxplanung
mvn clean install
```

### Installation und Konfiguration

Die Installation und Konfiguration der Anwendung ist im [Betriebshandbuch](xplan-documentation/xplan-betriebshandbuch/src/main/asciidoc) dokumentiert.

----
© 2022 lat/lon gesellschaft für raumbezogene informationssysteme mbH  
Im Ellig 1
53343 Wachtberg  
Tel: +49 +228 24333784  
info@lat-lon.de  
https://www.lat-lon.de  
twitter: https://twitter.com/latlon_de  
GitHub: https://github.com/lat-lon