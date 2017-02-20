.. _configuration-portal:

===========
XPlanPortal
===========
Alle Konfigurationsdateien der XPlanPortale liegen im *.deegree* des Benutzers und werden im Folgenden als *<portal-config-jar>* benannt. Für die allgemeine Konfiguration der XPlanPortale wird außerdem auf die geomajas  Dokumentation (http://geomajas.org/gis-documentation) verwiesen.

--------------------
WMS-Ebene hinzufügen
--------------------
Um eine neue WMS-Ebene hinzuzufügen, muss diese im entsprechenden XPlanPortal konfiguriert werden. Neue Ebenen werden in folgenden Dateien konfiguriert:

Konfiguration der Ebene für den Server ``<portal-config-jar>\<portalname>\layerWms.xml`` ::

   <bean name="layerNeu" class="org.geomajas.layer.wms.WmsLayer">
     <property name="layerInfo" ref="layerNeuInfo" />
     <property name="baseWmsUrl" value="http://<host>:<port>/services" />
     <property name="version" value="1.1.1" />
     <property name="format" value="image/png" />
     <property name="styles" value="default" />
     <property name="enableFeatureInfoAsGmlSupport" value="true" />
     <property name="enableFeatureInfoAsHtmlSupport" value="true" />
     <property name="parameters">
       <list>
         <bean class="org.geomajas.configuration.Parameter">
           <property name="name" value="TRANSPARENT" />
           <property name="value" value="true" />
         </bean>
       </list>
     </property>
   </bean>

   <bean name="layerNeuInfo" class="org.geomajas.configuration.RasterLayerInfo">
     <property name="crs" value="EPSG:31468" />
     <property name="maxExtent">
       <bean class="org.geomajas.geometry.Bbox">
         <property name="x" value="4336546" />
         <property name="y" value="5580793" />
         <property name="width" value="197215" />
         <property name="height" value="140023" />
       </bean>
     </property>
     <property name="dataSourceName" value="layerNeuName" />
     <property name="tileWidth" value="512" />
     <property name="tileHeight" value="512" />
   </bean>

Konfiguration der Ebene für den Client ``<portal-config-jar>\<portalname>\clientLayerWms.xml`` ::

   <bean class="org.geomajas.configuration.client.ClientRasterLayerInfo" id="clientLayerNeu">
     <property name="serverLayerId" value="layerNeu" />
     <property name="label" value="Kreise" />
     <property name="visible" value="true" />
     <property name="style" value="1" />
   </bean>

Hinzufügen der Ebene zur Karte ``<portal-config-jar>\<portalname>\mapMain.xml`` ::

   <property name="layers">
     <list>
       ...
       <ref bean="clientLayerNeu" />
       ...
     </list>
   </property>

   ...

   <property name="treeNode">
     <bean class="org.geomajas.widget.layer.configuration.client.ClientBranchNodeInfo">
       <property name="treeNodes">
         <list>
           ...
           <bean class="org.geomajas.widget.layer.configuration.client.ClientLayerNodeInfo">
             <property name="layerId" value="clientLayerNeu" />
           </bean>
           ...
         </list>
       </property>
     </bean>
   </property>

----------------------------------------------------
Zeichenreihenfolge der Layer im Kartenfenster ändern
----------------------------------------------------
Um die Zeichenreihenfolge der Layer in der Ebenenübersicht im Kartenfenster zu ändern, muss in der Datei ::

   <portal-config-jar>\<portalname>\mapMain.xml

die Reihenfolge der Layer (``<ref bean.../>``) geändert werden. Die Reihenfolge der Layer ist umgekehrt zur tatsächlichen Zeichenreihenfolge: ::

   <property name="layers">
     <list>
        <ref bean="clientLayerTopoSachsenGrau" />
        <ref bean="clientLayerTopoSachsen" />
        <ref bean="clientLayerFaunaFloraHabitat" />
        <ref bean="clientLayerVogelschutzgebieteEu" />
        <ref bean="clientLayerKreiseSachsen" />
        <ref bean="clientLayerBPRaster" />
        <ref bean="clientlayer_bp_gembedarfsfl_ref" />
       ...

------------------------------------------------
Statische Legenden zu einer WMS-Ebene hinzufügen
------------------------------------------------
Für alle WMS-Ebenen kann ein statisches Legendenbild konfiguriert werden. Dazu muss in der Bean der Ebene eine Eigenschaft hinzugefügt werden. ::

   <portal-config-jar>\<portalname>\layerTldaWms.xml

Dabei kann der Pfad zu der gewünschten Legende angegeben werden. ::

   <bean name="kreiseSachsen" class="org.geomajas.layer.wms.WmsLayer">
     ...
           </bean>
         </list>
       </property>
     </bean>
     <property name="staticLegendImagePath" value="legends/legende.png"/>
     ...
   </bean>

Neue Legendenbilder können im zugehörigen Verzeichnis abgelegt werden: ::

   <portal-config-jar>\<portalname>\legends


.. hint:: Für die Aktualisierung der Portal-Konfiguration sollte das *jar-Tool* aus der JDK verwendet werden.
          Um eine Konfigurationsdatei anzupassen, müssen folgende Schritt ausgeführt werden:
        Linux-Betriebssystem:
           * Über die Konsole in das Verzeichnis *.deegree/portal-configuration* navigieren
           * Ausführen des Befehls (Entpacken der Konfiguration): *jar xvf xplan-bplan-config.jar xplan-bplan-config/<Dateiname>*
           * Entpackte-Datei mit einem beliebigen Editor bearbeiten
           * Ausführen des Befehls (Einpacken der aktualisierten Konfiguration): *jar uvf xplan-bplan-config.jar xplan-bplan-config/<Dateiname>*
        Windows-Betriebssystem:
           * Über die Konsole in das Verzeichnis *.deegree/portal-configuration* navigieren
           * Ausführen des Befehls (Entpacken der Konfiguration): *<Pfad-zur-JDK>/bin/jar.exe xvf xplan-bplan-config.jar xplan-bplan-config/<Dateiname>*
           * Entpackte-Datei mit einem beliebigen Editor bearbeiten
           * Ausführen des Befehls (Einpacken der aktualisierten Konfiguration): *<Pfad-zur-JDK>/bin/jar.exe uvf xplan-bplan-config.jar xplan-bplan-config/<Dateiname>*



------------------------------------------------
Dropdown-Menü zum wechseln der Portale anpassen
------------------------------------------------

Innerhalb der Planungs-Portale ist es möglich, mit einem Dropdown-Menü zwischen den einzelnen Portalen zu wechseln.
Damit dies ausgeführt werden kann, muss die URL der einzelnen Portale in der Datei *dropDownList.xml* angepasst werden.
Diese Datei liegt im Gegensatz zu den vorherigen beschriebenen Konfigurationen im Ordner der jeweiligen XPlanPortal-Webkomponente *<tomcat>/webapps/<portal-portalname>*. ::

    <List>
      <entry>
        <label>Bebauungspläne</label>
        <link>http://<host>:<port>/portal-bplan/</link>
      </entry>
      <entry>
        <label>Flächennutzungspläne</label>
        <link>http://<host>:<port>/portal-fplan/</link>
      </entry>
      <entry>
        <label>Landschaftspläne</label>
        <link>http://<host>:<port>/portal-lplan/</link>
      </entry>
    </List>




