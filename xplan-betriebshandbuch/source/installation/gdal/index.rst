.. _installation-gdal:

=======================================
Installation und Konfiguration von GDAL
=======================================

Die xPlanBox unterstützt die Darstellung von Rasterdaten mittels GDAL-Integration. Es wird GDAL in der Version 1.10.1 empfohlen (s. auch `deegree Handbook <https://github.com/deegree/deegree3/blob/master/deegree-services/deegree-webservices-handbook/src/main/sphinx/gdal.rst>`_). Die GDAL Unterstützung ist optional, die Konfiguration im XPlanManager ist unter :ref:`Konfiguration <configuration-gdal>` beschrieben.

.. important:: Der Zugriff auf die GDAL-Bibliothek erfolgt über JNI. Je JVM kann die GDAL-Bibliothek nur einmal geladen werden. Deswegen muss jede Webapp der xPlanBox, die GDAL verwendet (``xplan-wms`` und ``xplan-manager-web``) in getrennten Tomcat Instanzen deployt werden.

Linux
-----
Für Ubuntu (14.04) kann GDAL aus einer Paketquelle installiert werden: ::

   sudo apt-get install libgdal-java
   
Damit GDAL über die JNI-Schnittstelle angesprochen werden kann, muss die Umgebungsvariable zur GDAL-Bibliothek gesetzt werden, z.B.: ::

   export LD_LIBRARY_PATH=/usr/lib/jni/

.. hint:: Die Umgebungsvariable "LD_LIBRARY_PATH" muss auf das Verzeichnis zeigen, in dem sich die Datei libgdaljni.so befindet.

Windows
-------
GDAL für Windows kann bei `GISInternals <http://www.gisinternals.com/>`_ heruntergeladen werden. Die empfohlene Version 1.10.1 befindet sich unter "Archive Versions". Die Architektur der GDAL-Bibliothek (32bit oder 64bit) muss der Architektur von Java und `Windows <http://windows.microsoft.com/de-de/windows/32-bit-and-64-bit-windows>`_ entsprechen. Bei den Downloads sollten die kompilierten Binaries in einem einzelnen Zip-Archiv gewählt werden (oberster Link).

.. hint:: Die MSVC-Version mit der GDAL kompiliert wurde, setzt die jeweilige Version des Microsoft C/C++ Runtime Library (CRT) voraus. Ab der Version MSVC 11.0 (1700) werden Windows XP and Windows Server 2003 nicht mehr unterstützt.

Dieses Zip-Archiv kann an einem beliebigen Ort entpackt werden.

Unter ``Systemsteuerung->System und Sicherheit->System->Erweiterte Systemeinstellungen->Erweitert->Umgebungsvariablen`` müssen folgende Einstellungen vorgenommen werden:

* Setzen der Variable ``JAVA_HOME`` mit der Referenz zur Java Installation (JDK), z.B.: ``C:\Program Files (x86)\Java\jdk1.7.0_45``
* Setzen der Variable ``GDAL_DATA`` mit der Referenz auf den Ordner, der Datei "gcs.csv" enthält, z.B.: ``C:\Program Files (x86)\gdal\bin\gdal-data``
* Der bestehenden Umgebungsvariable *PATH* müssen zwei Pfade hinzugefügt werden (Annahme: Entpacktes Zip-Archiv von GISInternals wurde genutzt): ``<GDAL-INSTALLATIONSORDNER>\bin;<GDAL-INSTALLATIONSORDNER>\bin\gdal\java``

Die Datei ``<GDAL-INSTALLATIONSORDNER>\bin\gdal\java\gdal.jar`` muss in das Verzeichnis ``lib`` der Webapps (``xplan-wms`` und ``xplan-manager-web``) kopiert werden.
