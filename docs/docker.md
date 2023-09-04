# xPlanBox & docker

In diesem Dokument geht es um das Bauen von docker Images aus dem Source Code von ozgxplanung. Die gebauten docker Images sind in ozgxplanung Container Registry verfügbar: https://gitlab.opencode.de/diplanung/ozgxplanung/container_registry/.

## Konfiguration

* Property `docker-image.namePrefix` (Standardwert: `xplanbox`): erlaubt den Name der generierten Docker Images zu ändern.


## Images bauen mit dem docker Daemon

```
mvn install -Pdocker
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

## Mit kaniko

Docker in Docker funktioniert auf OpenCoDE nicht, weil dort die Gitlab Runners auf Kubernetes laufen (Stand 31.01.2023). Die Build Pipeline verwendet deswegen stattdessen [kaniko](https://github.com/GoogleContainerTools/kaniko). Der Ablauf ist dann wie folgt:

* in der Phase `package` werden mit dem Goal [`source` von dem docker-maven-plugin](http://dmp.fabric8.io/#docker:source) die Docker Kontexte in Dateien `docker-build.tar` gespeichert
* nach dem Maven Build werden alle `docker-build.tar` Dateien gzipped und als Artefakte temporär gespeichert
* im nächsten Stage wird kaniko mit jedem gespeicherten Docker Kontext ausgeführt

### Anforderungen

Diese Vorangehensweise bringt zusätzliche Anforderungen:

* Vorbereitungen für die Docker Images (z.B. Dependencies entpacken) müssen vor der Phase `package` stattfinden, um in der Phase `package` von `docker-maven-plugin:source` verwendet zu werden
* die `docker-build.tar` Dateien sollen so klein wie möglich gehalten werden, sonst wird die Obergrenze für die maximale Größe von Packages überschritten (`413 Request Entity Too Large`). Mit Hilfe einer [`.maven-dockerignore` Datei](http://dmp.fabric8.io/#ex-build-dockerexclude) ist es möglich zu konfigurieren, was nicht in die tar-Datei gelangen soll.
* zur Zeit sind die gezipped `docker-build.tar` Dateien leider zu groß für die OpenCoDE zu groß, um als Artefakte von einem einzelnen Job gespeichert zu werden. Als Workaround wird das Bauen in mehreren Jobs aufgeteilt, was leider mehr Konfiguration erfordert.
