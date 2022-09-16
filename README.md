# xPlanBox

In diesem Repository ist der Quellcode für die Open Source-Version der Anwendung "xPlanBox" der Firma [lat/lon](https://www.lat-lon.de) enthalten. Die Anwendung dient der Abbildung der Bauleit- und der Landschaftsplanung auf Basis des Standards [XPlanung](https://xleitstelle.de/xplanung/ueber_xplanung). Die Anwendung basiert zudem auf den Standards des [Open Geospatial Consortium](https://www.ogc.org/), den technischen Empfehlungen der [INSPIRE Richtlinie](https://inspire.ec.europa.eu/) und insbesondere der Abbildung des deutschen Planungsrechts durch das [GML-Anwendungsschema XPlanGML](https://xleitstelle.de/xplanung/releases-xplanung). Die Komponenten der Anwendung wurden u.a. unter der Verwendung der Open Source-Software [deegree webservices](https://www.deegree.org) implementiert.

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

Für die Erstellung der ausführbaren Binärdateien ist ein [JDK 11](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot) und das Werkzeug [Apache Maven 3.8](https://maven.apache.org/) erforderlich.

#### Installation und Konfiguration von Maven. 

Folgen Sie der [Installationsanleitung von Maven](https://maven.apache.org/install.html) und legen Sie anschliessend im Benutzerverzeichnis die Datei `${user.home}/.m2/settings.xml` mit folgendem Eintrag für einen Spiegelserver (_mirror_) an:
```xml
<settings>
    <mirrors>
        <mirror>
            <id>deegree</id>
            <mirrorOf>latlon</mirrorOf>
            <url>https://repo.deegree.org/repository/public/</url>
        </mirror>
    </mirrors>
</settings>
```
Weitere Informationen zu Konfiguration von Maven stehen in der [Referenzdokumentation zu den Maven Settings](https://maven.apache.org/settings.html).

> **_Hinweis_**: Diese Konfiguration ist zurzeit noch erforderlich, da die verwendetet Bibliotheken von Dritten noch nicht in einem Repository von GitLab auf der OpenCoDE-Plattform verfügbar sind.

#### deegree webservices erstellen

Im ersten Schritt muss der Quellcode von [deegree mit Java 11](https://github.com/lat-lon/deegree3/tree/xplanbox) kompiliert werden. Dazu muss die passende Version von deegree ausgewählt werden. Für Version 5.0 der Anwendung muss deegree **Version 3.4.27** verwendet werden:

```shell
git clone https://github.com/lat-lon/deegree3.git
cd deegree3
git checkout tags/xplanbox-deegree-3.4.27
mvn clean install
```
> **_Hinweis_**: Aktuell kann noch nicht eine offizielle Release-Version von deegree verwendet werden, da diese bisher nur unter Java 1.8 ausführbar sind. Der Quellcode für diese Anwendung erfordert aber Java 11! Sobald eine Version von deegree mit Unterstützung von Java 11 verfügbar ist, entfällt dieser Schritt.

> **_Hinweis_**: Unter dem Betriebssystem Windows kann es bei der Ausführung von Unit-Test zu Fehlern kommen. Es kann dann erforderlich sein, die Tests zu überspringen. Dazu ist die Option `-DskipTests` beim Aufruf von Maven zu ergänzen.

#### XPlanung-Validierungsregeln einbinden

Die Anwendung nutzt die öffentlich verfügbaren XPlanung-Validierungsregeln des XPlanung-Standards aus dem [Bitbucket-Repository der Geowerkstatt Hamburg](https://bitbucket.org/geowerkstatt-hamburg/xplanung).
Dazu muss die passende Version der XPlanung-Validierungsregeln ausgewählt werden. Für Version 5.0 der Anwendung muss **Version 0.11.1** verwendet werden:

* Download der Datei [v0.11.1.zip](https://bitbucket.org/geowerkstatt-hamburg/xplanung/get/v0.11.1.zip) aus dem [Download-Bereich](https://bitbucket.org/geowerkstatt-hamburg/xplanung/downloads/?tab=tags)
* Installieren der Datei im lokalen Maven Repository mit `mvn install:install-file -DgroupId=de.geowerkstatt-hamburg -DartifactId=xplanung -Dversion=0.11.1 -Dpackaging=zip -DgeneratePom=true -Dfile=geowerkstatt-hamburg-xplanung.zip`

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
© 2022 lat/lon gesellschaft für raumbezogene informationssysteme mbH  
Im Ellig 1
53343 Wachtberg  
Tel: +49 +228 24333784  
info@lat-lon.de  
https://www.lat-lon.de  
twitter: https://twitter.com/latlon_de  
GitHub: https://github.com/lat-lon
