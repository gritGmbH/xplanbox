.. _overview:

Systemüberblick
===============

In diesem Betriebshandbuch befinden sich Informationen zu Systemvoraussetzungen, Schnittstellen und Installation für den Betrieb der xPlanBox.
Die Webanwendungen basieren auf den Komponenten der Open-Source-Projekte `deegree <http://www.deegree.org/>`_ und `Geomajas <http://www.geomajas.org/>`_.
Als zugrunde liegende Basistechnologien werden Java, Apache Tomcat und PostgreSQL / PostGIS verwendet.

Zur erfolgreichen Installation der Software-Komponenten müssen die in diesem Kapitel beschriebenen Voraussetzungen erfüllt werden.

==============
Betriebssystem
==============
Als Betriebssystem kann ein beliebiges 64-bit Betriebssystem verwendet werden, das Java SE 7 (`Oracle JRE 1.7 <http://www.oracle.com/technetwork/java/javase/downloads/index.html>`_) unterstützt.

.. hint:: Empfohlen wird die Nutzung eines der folgenden Betriebssysteme: Debian Wheezy, SLES 11 SP4, Oracle Linux 7, CentOS 7.x, Windows Server 2008 R2/2012 R2.

.. hint:: Das Gebietsschema des Betriebssystems ("Locale") sollte auf deutsch konfiguriert sein, da einzelne Fehlerbeschreibungen in dieser Sprache ausgegeben werden (z.B. im Validator-Report). Unter Linux kann das Gebietsschema beispielsweise in der Datei "/etc/default/locale" angepasst werden.

.. hint:: Empfohlen wird Oracle JDK 1.7_45+.

=======================
Java Applikationsserver
=======================
Als Applikationsserver wird Apache Tomcat in der Version 7 empfohlen. Das Installationsarchiv findet sich bei der `Apache Foundation <http://tomcat.apache.org>`_. Diese Dokumentation bezieht sich grundsätzlich auf den Apache Tomcat.
Um eine spätere Trennung der Dienste (Web-Anwendungen) zu gewährleisten, werden zwei Tomcat-Installationen empfohlen.

.. hint:: Die minimale Anforderung an den Applikationsserver ist das Java EE 6 Web Profil. Empfohlen wird Apache Tomcat Version 7.0.32+.

=========
Datenbank
=========
Als Datenbanksystem ist eine Installation von PostgreSQL 8.4 mit der Erweiterung PostGIS 1.5.4 erforderlich. Ab der xPlanBox Produktversion 1.10 werden auch PostgreSQL 9.3 mit der Erweiterung PostGIS 2.1.6 unterstützt.

=====================
Systemvoraussetzungen
=====================

Server
------
Minimale Systemvoraussetzung
++++++++++++++++++++++++++++
 * CPU: 2 CPUs mit je 2 Kernen (2x DualCore) oder 1 CPU mit 4 Kernen (1x QuadCore) - minimal 2,0-GHz-Prozessor mit 64 Bit
 * Arbeitsspeicher: 8 GB RAM
 * Netzwerkadapter: Gigabit-Ethernet-Adapter (10/100/1000baseT PHY/MAC)
 * Festplattensystem: Journaling-Dateisystem (Kapazität nach Bedarf, Minimum 500 GB)

.. hint:: Die Anwendung xPlanBox benötigt min. 800 MB Festplattenspeicherplatz. Wachsende Daten über die Laufzeit sind die Datenhaltung für die Pläne inkl. der Anhänge und die Systemlogdateien. Zu der Kapazitätsnutzung der xPlanBox können folgende Abschätzung gegeben werden: Größe eines Planarchivs (inkl. der Anhänge) * Anzahl der Planarchive * 2. Wenn also 100 Pläne mit einer durchschnittlichen Größe von 10 MB inkl. der Anhänge importiert werden sollen, dann sind ca. 2 GB Festplattenspeicherplatz notwendig.

Empfohlene Systemkonfiguration
++++++++++++++++++++++++++++++
 * CPU: 2 CPUs mit je 4 Kernen (2x QuadCore) or 1 CPU mit 8 Kernen (1x OctaCore) - 3,0-GHz-Prozessor mit 64 Bit
 * Arbeitsspeicher: 32 GB RAM
 * Netzwerkadapter: Gigabit-Ethernet-Adapter (10/100/1000baseT PHY/MAC)
 * Festplattensystem: RAID-10 (Kapazität nach Bedarf, Minimum 500 GB) optional mit SSD

.. hint::  Hohe Datentransferraten sind bei Bereitstellung von großen Datenbeständen sowohl Rasterdaten als auch Vektordaten aus Datenbanken notwendig, u.a. um die Vorgaben der INSPIRE-Richtlinien zu erfüllen.

Client
------
 * CPU: 1 CPU mit mindestens 1,4-GHz-Prozessor
 * Arbeitsspeicher: 4 GB RAM

.. hint:: Weitere Informationen zu den Systemvoraussetzungen bei den unterstützten Browsern: Microsoft Internet Explorer (9, 10, 11), Mozilla Firefox: 36+, Google Chrome: 40+