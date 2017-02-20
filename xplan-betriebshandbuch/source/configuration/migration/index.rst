.. _configuration-migration:

===================================
Migration der Anwendung (Beispiele)
===================================

---------
Hostumzug
---------

Bei einem Hostumzug, bei dem sich der Hostname des Servers ändert, muss die URL in mehreren Konfigurationen angepasst werden,
damit die Funktionalität der Anwendungen gewährleistet ist.
Dabei müssen folgende Anpassungen erfolgen:

+++++++++++++++++++++++++
Für die Planungs-Portale:
+++++++++++++++++++++++++

 * *layer[B|F|L]Plan.xml*, *layer[B|F|L]PlanWFS.xml*, *layer[B|F|L]PRaster.xml* und *featureHitsRetriever[B|F|L]P.xml* (in der jeweiligen jar-Datei der XPlanPortal-Webkomponenten *.deegree/portal-configuration/<portal>-config.jar*)
 * *dropDownList.xml* (im Ordner der jeweiligen XPlanPortal-Webkomponente *<tomcat>/webapps/<portal-portalname>*)

**Anpassung in den Dateien layer[B|F|L]Plan.xml, layer[B|F|L]PlanWFS.xml, layer[B|F|L]PRaster.xml und featureHitsRetriever[B|F|L]P.xml:**

Für die Planungs-Portale müssen in den genannten Dateien alle Werte der Properties *baseWmsUrl* (layer[B|F|L]Plan.xml und layer[B|F|L]PRaster.xml) bzw. *values* (layer[B|F|L]PlanWFS.xml) und *wfsRequestUrlForBboxFeatureHits* (featureHitsRetriever[B|F|L]P.xml), die einen der deegree Dienste referenzieren, angepasst werden.

Beispiel *layerBPlan.xml*: ::

    ...
    <bean name="bp_plan" class="de.latlon.xplanung.layer.wms.XPlanWmsLayer">
        <property name="layerInfo" ref="layer_bp_plan_ref" />
        <property name="baseWmsUrl" value="http://<host>:<port>/<service>/service/wms?" />
        <property name="version" value="1.1.1" />
    ...

Für mehr Details sowie Hinweise zur Bearbeitung von jar-Dateien siehe Kapitel :ref:`XPlanPortal <configuration-portal>`


**Anpassungen in der Datei dropDownList.xml:**

Damit ein Wechsel zwischen den Planungs-Portalen über die Dropdown Liste möglich ist, muss die <link>-Referenz der jeweiligen Portale angepasst werden. ::

    <List>
      <entry>
        <label>Bebauungspläne</label>
        <link>http://<host>:<port>/portal-<portalname>/</link>
      </entry>
      ...
    </List>

Für mehr Details siehe Kapitel :ref:`XPlanPortal <configuration-portal>`

+++++++++++++++++++++
Für den XPlanManager:
+++++++++++++++++++++

 * *managerWebConfiguration.properties*  (im Verzeichnis *.deegree/manager-configuration* im Home-Verzeichnis des Nutzers)
 * *managerConfiguration.properties* (im Verzeichnis *.deegree/manager-configuration* im Home-Verzeichnis des Nutzers)


**Anpassungen in der Datei managerWebConfiguration.properties:**

In dieser Datei müssen Anpassungen an der URL erfolgen, damit die Kartenvorschau im XPlanManager funktioniert.
Dazu muss der Wert des Schlüssels *wmsUrl* angepasst werden. ::

   wmsUrl=http://<host>:<port>/xplan-wms/services

Für mehr Details siehe Kapitel :ref:`Kartenvorschau <configuration-mappreview>`


**Anpassungen in der Datei managerConfiguration.properties:**

Für einen Workspace-Reload, muss der XPlanManager den deegree-Dienst erreichen können. Es muss daher der Wert des Schlüssels *workspaceReloadUrls* angepasst werden ::

    #workspace reloader configuration
    workspaceReloadUrls=http://<host>:<port>/<service>/
    workspaceReloadUser=<user>
    workspaceReloadPassword=<passwort>

.. hint:: Werden der deegree-Dienst und der XPlanmanager auf dem selben Server betrieben, so können die Dienste über den Hostnamen *localhost* kommunizieren.

Für mehr Details siehe Kapitel :ref:`Automatischer Workspace-Reload <configuration-workspacereload>`


+++++++++++++++++
Für den XPlanWMS:
+++++++++++++++++

 * *html.gfi* (im Verzeichnis *.deegree/xplansyn-wms-workspace* unter *services*)


**Anpassungen in der Datei html.gfi:**

Damit die Links auf Dokumente in der GetFeatureInfo Ausgabe funktionsfähig ist, muss eine Anpassung der URL in der Datei *html.gfi* erfolgen. ::

    <?template makeitalink>
    <tr>
      <td <?odd:maybegray>><?name:map propname></td>
      <td <?odd:maybegray>><?link:http://<host>:<port>/<service>:Dokument herunterladen></td>
    </tr>

