.. _configuration-validation:

=========================================================
Weiterführende Informationen zur semantischen Validierung
=========================================================
Bei der Ausgabe des Ergebnisses der semantischen Validerung kann eine Referenz auf eine externe Resource gesetzt werden, die weiterführenden Informationen bereitstellt.
Die Referenzen können in der Datei *managerConfiguration.properties* im Verzeichnis *~/.deegree/manager-configuration/* konfiguriert werden. ::

   linkSemanticConformity_XPLAN_2=
   linkSemanticConformity_XPLAN_3=
   linkSemanticConformity_XPLAN_40=http://localhost:8080/external/documentation/Konformitaetsbedingungen_XPlanGML_4.pdf
   linkSemanticConformity_XPLAN_41=http://localhost:8080/external/documentation/Konformitaetsbedingungen-XPlanGML_4_1.pdf

.. hint:: Es müssen ggf. Zeilenumbrüche entfernt werden.

Für jede XPlan-GML-Version kann hier die Referenz auf eine externe Resource konfiguriert werden.
Ist für eine Version keine Resource vorhanden, kann der Eintrag vollständig entfernt oder der Wert zu dem Schlüssel leer gelassen werdem (wie im Beispiel für XPLAN_2 und XPLAN_3). Es wird dann kein Hinweis im Validierungs-Report ausgegeben.

.. note:: Es wird empfohlen die Dokumente über die Komponente XPlanResources bereit zu stellen, um die Unabhängigkeit zu externen Resourcen sicherzustellen.